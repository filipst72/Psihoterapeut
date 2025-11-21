package person.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import person.model.tabele.Centar_za_obuku;
import person.model.tabele.Oblast_psihoterapije;
import person.model.utility.JDBCUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class RegistracijaView {
    GridPane form;
    private VBox view;
    Label imeLabel;
    TextField imeField;
    Label prezimeLabel;
    TextField prezimeField;
    Label jmbgLabel;
    TextField jmbgField;
    Label datumRodjLabel;
    DatePicker datumRodj;
    Label prebivalisteLabel;
    TextField prebivalisteField;
    Button registrujButton;
    Label brTelLabel;
    TextField brTelField;
    Label emailLabel;
    TextField emailField;
    Label datumSertLabel;
    DatePicker datumSert;
    Label psihologLabel;
    RadioButton jeste;
    RadioButton nije;
    ToggleGroup psihologGroup;
    Label oblastLabel;
    ComboBox<Oblast_psihoterapije> oblastCombo;
    Label centarLabel;
    ComboBox<Centar_za_obuku> centarCombo;
    Label poruka;

    public RegistracijaView(Runnable onRegistracijaUspesna) {
        init();
        registrujButton.setOnAction(e -> {
            String ime = imeField.getText();
            String prezime = prezimeField.getText();
            String jmbg = jmbgField.getText();
            String prebivaliste = prebivalisteField.getText();
            String brTel = brTelField.getText();
            String email = emailField.getText();
            LocalDate datumRodjValue = datumRodj.getValue();
            LocalDate datumSertValue = datumSert.getValue();
            Toggle selectedPsiholog = psihologGroup.getSelectedToggle();
            Oblast_psihoterapije izabranaOblast = oblastCombo.getValue();
            Centar_za_obuku izabraniCentar = centarCombo.getValue();

            if (ime.isEmpty() || prezime.isEmpty() || jmbg.isEmpty() ||
                    prebivaliste.isEmpty() || brTel.isEmpty() || email.isEmpty() ||
                    datumRodjValue == null || datumSertValue == null ||
                    selectedPsiholog == null || izabranaOblast == null || izabraniCentar == null) {
                poruka.setText("Sva polja su obavezna.");
                return;
            }
            try {
                int oblastId= izabranaOblast.getOblast_id();
                int centarId = izabraniCentar.getCentar_id();
                boolean psiholog = selectedPsiholog == jeste;
                boolean uspeh = JDBCUtils.registrujPsihologa(
                        ime, prezime, jmbg, datumRodjValue, prebivaliste,
                        brTel, email, datumSertValue, psiholog, oblastId, centarId);

                if (uspeh) {
                    poruka.setText("Registracija uspešna!");
                    resetujFormu();
                    if (onRegistracijaUspesna != null) {
                        onRegistracijaUspesna.run();
                    }
                } else {
                    poruka.setText("Greška prilikom registracije. Proverite podatke.");
                }
            } catch (Exception ex) {
                poruka.setText("Došlo je do greške: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        view.getChildren().add(form);
    }

    public void init() {
        this.view = new VBox(10);
        this.view.setPadding(new Insets(20));

        form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        imeLabel = new Label("Ime psihoterapeuta:");
        imeField = new TextField();
        prezimeLabel = new Label("Prezime:");
        prezimeField = new TextField();
        jmbgLabel = new Label("JMBG:");
        jmbgField = new TextField();
        datumRodjLabel = new Label("Datum rodjenja:");
        datumRodj = new DatePicker();
        prebivalisteLabel = new Label("Prebivaliste:");
        prebivalisteField = new TextField();
        brTelLabel = new Label("Broj telefona:");
        brTelField = new TextField();
        emailLabel = new Label("Email:");
        emailField = new TextField();
        datumSertLabel = new Label("Datum sertifikacije:");
        datumSert = new DatePicker();
        psihologLabel = new Label("Psiholog:");
        jeste = new RadioButton("Da");
        nije = new RadioButton("Ne");
        psihologGroup = new ToggleGroup();
        jeste.setToggleGroup(psihologGroup);
        nije.setToggleGroup(psihologGroup);
        oblastLabel = new Label("Oblast:");
        oblastCombo = new ComboBox<>();
        centarLabel = new Label("Centar:");
        centarCombo = new ComboBox<>();
        registrujButton = new Button("Registruj se");
        poruka = new Label();

        popuniOblasti();
        popuniCentre();

        form.add(imeLabel, 0, 0);
        form.add(imeField, 1, 0);
        form.add(prezimeLabel, 0, 1);
        form.add(prezimeField, 1, 1);
        form.add(jmbgLabel, 0, 2);
        form.add(jmbgField, 1, 2);
        form.add(datumRodjLabel, 0, 3);
        form.add(datumRodj, 1, 3);
        form.add(prebivalisteLabel, 0, 4);
        form.add(prebivalisteField, 1, 4);
        form.add(brTelLabel, 0, 5);
        form.add(brTelField, 1, 5);
        form.add(emailLabel, 0, 6);
        form.add(emailField, 1, 6);
        form.add(datumSertLabel, 0, 7);
        form.add(datumSert, 1, 7);
        form.add(psihologLabel, 0, 8);
        form.add(jeste, 1, 8);
        form.add(nije, 2, 8);
        form.add(oblastLabel, 0, 9);
        form.add(oblastCombo, 1, 9);
        form.add(centarLabel, 0, 10);
        form.add(centarCombo, 1, 10);
        form.add(registrujButton, 1, 11);
        form.add(poruka, 1, 12);
    }

    private void popuniOblasti() {
        try {
            List<Oblast_psihoterapije> oblasti = JDBCUtils.getSveOblasti();
            oblastCombo.setItems(FXCollections.observableArrayList(oblasti));
            oblastCombo.setCellFactory(param -> new ListCell<Oblast_psihoterapije>() {
                @Override
                protected void updateItem(Oblast_psihoterapije oblast, boolean empty) {
                    super.updateItem(oblast, empty);
                    if (empty || oblast == null) {
                        setText(null);
                    } else {
                        setText(oblast.getNaziv());
                    }
                }
            });
            oblastCombo.setButtonCell(new ListCell<Oblast_psihoterapije>() {
                @Override
                protected void updateItem(Oblast_psihoterapije oblast, boolean empty) {
                    super.updateItem(oblast, empty);
                    if (empty || oblast == null) {
                        setText("Izaberite oblast");
                    } else {
                        setText(oblast.getNaziv());
                    }
                }
            });
        } catch (Exception e) {
            poruka.setText("Greška pri učitavanju oblasti");
            e.printStackTrace();
        }
    }

    private void popuniCentre() {
        try {
            List<Centar_za_obuku> centri = JDBCUtils.getSveCentre();
            centarCombo.setItems(FXCollections.observableArrayList(centri));
            centarCombo.setCellFactory(param -> new ListCell<Centar_za_obuku>() {
                @Override
                protected void updateItem(Centar_za_obuku centar, boolean empty) {
                    super.updateItem(centar, empty);
                    if (empty || centar == null) {
                        setText(null);
                    } else {
                        setText(centar.getNaziv());
                    }
                }
            });
            centarCombo.setButtonCell(new ListCell<Centar_za_obuku>() {
                @Override
                protected void updateItem(Centar_za_obuku centar, boolean empty) {
                    super.updateItem(centar, empty);
                    if (empty || centar == null) {
                        setText("Izaberite centar");
                    } else {
                        setText(centar.getNaziv());
                    }
                }
            });
        } catch (Exception e) {
            poruka.setText("Greška pri učitavanju centara");
            e.printStackTrace();
        }
    }

    private void resetujFormu() {
        imeField.clear();
        prezimeField.clear();
        jmbgField.clear();
        prebivalisteField.clear();
        brTelField.clear();
        emailField.clear();
        datumRodj.setValue(null);
        datumSert.setValue(null);
        psihologGroup.selectToggle(null);
        oblastCombo.getSelectionModel().clearSelection();
        centarCombo.getSelectionModel().clearSelection();
    }

    public VBox getView() {
        return view;
    }
}