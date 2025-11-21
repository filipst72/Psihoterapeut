package person.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import person.model.tabele.Publikacija;
import person.model.tabele.Seansa;
import person.model.utility.JDBCUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PublikacijaForm extends VBox {

    private ComboBox<Seansa> seansaComboBox;
    private DatePicker datumPicker;
    private TextField razlogField;
    private TextField otkrivenoZaField;
    private Button sacuvajButton;
    private Map<Integer, String> klijentiMapa = new HashMap<>();
    private static PublikacijaListener publikacijaListener;
    public interface PublikacijaListener {
        void onPublikacijaSacuvana();
    }
    public static void setPublikacijaListener(PublikacijaListener listener) {
        publikacijaListener = listener;
    }

    public PublikacijaForm() {
        Label naslov = new Label("OBJAVLJIVANJE PODATAKA SA SEANSE");

        seansaComboBox = new ComboBox<>();
        seansaComboBox.setPromptText("Izaberite seansu");
        popuniComboBox();

        datumPicker = new DatePicker();
        datumPicker.setPromptText("Datum objavljivanja");
        datumPicker.setValue(LocalDate.now());

        razlogField = new TextField();
        razlogField.setPromptText("Razlog objavljivanja (npr. sudski postupak)");

        otkrivenoZaField = new TextField();
        otkrivenoZaField.setPromptText("Kom su podaci otkriveni (npr. Sud, Policija)");

        sacuvajButton = new Button("Sačuvaj podatke o objavljivanju");
        sacuvajButton.setOnAction(e -> sacuvajPublikaciju());


        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));

        grid.add(new Label("Seansa:"), 0, 0);
        grid.add(seansaComboBox, 1, 0);
        grid.add(new Label("Datum objavljivanja:"), 0, 1);
        grid.add(datumPicker, 1, 1);
        grid.add(new Label("Razlog objavljivanja:"), 0, 2);
        grid.add(razlogField, 1, 2);
        grid.add(new Label("Otkriveno za:"), 0, 3);
        grid.add(otkrivenoZaField, 1, 3);
        grid.add(sacuvajButton, 0, 4, 2, 1);

        this.getChildren().addAll(naslov, grid);
        this.setSpacing(15);
        this.setPadding(new Insets(20));
    }

    private void popuniComboBox() {
        try {
            ObservableList<Seansa> sveSeanse = JDBCUtils.getSveSeanseZaPublikaciju();
            ucitajKlijente();
            seansaComboBox.setItems(sveSeanse);
            seansaComboBox.setCellFactory(param -> new ListCell<Seansa>() {
                @Override
                protected void updateItem(Seansa seansa, boolean empty) {
                    super.updateItem(seansa, empty);
                    if (empty || seansa == null) {
                        setText(null);
                    } else {
                        String klijentIme = klijentiMapa.getOrDefault(seansa.getKlijent_id(), "Nepoznat klijent");
                        setText(String.format("%s - %s %s - %s min -%s",
                                seansa.getDan(),
                                seansa.getVreme(),
                                klijentIme,
                                seansa.getTrajanje()));
                    }
                }
            });

            seansaComboBox.setButtonCell(new ListCell<Seansa>() {
                @Override
                protected void updateItem(Seansa seansa, boolean empty) {
                    super.updateItem(seansa, empty);
                    if (empty || seansa == null) {
                        setText("Izaberite seansu");
                    } else {
                        String klijentIme = klijentiMapa.getOrDefault(seansa.getKlijent_id(), "Nepoznat klijent");
                        setText(String.format("%s - %s %s",
                                seansa.getDan(),
                                seansa.getVreme(),
                                klijentIme));
                    }
                }
            });

        } catch (SQLException e) {
            prikaziGresku("Greška pri učitavanju seansi", e.getMessage());
            e.printStackTrace();
        }
    }

    private void ucitajKlijente() throws SQLException {
        String query = "SELECT klijent_id, ime, prezime FROM Klijent";
        try (var stmt = JDBCUtils.connection.createStatement();
             var rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("klijent_id");
                String ime = rs.getString("ime") + " " + rs.getString("prezime");
                klijentiMapa.put(id, ime);
            }
        }
    }

    private void sacuvajPublikaciju() {
        try {
            Seansa izabranaSeansa = seansaComboBox.getValue();
            LocalDate datum = datumPicker.getValue();
            String razlog = razlogField.getText().trim();
            String otkrivenoZa = otkrivenoZaField.getText().trim();

            if (izabranaSeansa == null) {
                prikaziUpozorenje("Morate izabrati seansu");
                return;
            }

            if (datum == null) {
                prikaziUpozorenje("Morate uneti datum objavljivanja");
                return;
            }

            if (razlog.isEmpty()) {
                prikaziUpozorenje("Morate uneti razlog objavljivanja");
                return;
            }

            if (otkrivenoZa.isEmpty()) {
                prikaziUpozorenje("Morate uneti kome su podaci otkriveni");
                return;
            }

            Publikacija publikacija = new Publikacija(
                    0,
                    razlog,
                    datum,
                    otkrivenoZa,
                    izabranaSeansa.getSeansa_id()
            );

            JDBCUtils.sacuvajPublikaciju(publikacija);

            prikaziObavestenje("Uspešno sačuvano",
                    "Podaci o objavljivanju su uspešno sačuvani u bazi.");
            ocistiFormu();
            if (publikacijaListener != null) {
                publikacijaListener.onPublikacijaSacuvana();
            }
        } catch (Exception ex) {
            prikaziGresku("Greška prilikom čuvanja", ex.getMessage());
        }
    }

    private void ocistiFormu() {
        seansaComboBox.getSelectionModel().clearSelection();
        datumPicker.setValue(LocalDate.now());
        razlogField.clear();
        otkrivenoZaField.clear();
    }

    private void prikaziUpozorenje(String poruka) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Upozorenje");
        alert.setHeaderText(null);
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    private void prikaziGresku(String naslov, String poruka) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(naslov);
        alert.setHeaderText(null);
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    private void prikaziObavestenje(String naslov, String poruka) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(naslov);
        alert.setHeaderText(null);
        alert.setContentText(poruka);
        alert.showAndWait();
    }
}