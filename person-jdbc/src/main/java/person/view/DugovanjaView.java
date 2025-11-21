package person.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import person.model.tabele.DugovanjePrikaz;
import person.model.utility.JDBCUtils;

import java.time.LocalDate;

public class DugovanjaView extends BorderPane {

    private final TableView<DugovanjePrikaz> tabela;

    public DugovanjaView() {
        tabela = new TableView<>();

        TableColumn<DugovanjePrikaz, Integer> colKlijentId = new TableColumn<>("Klijent ID");
        colKlijentId.setCellValueFactory(new PropertyValueFactory<>("klijentId"));

        TableColumn<DugovanjePrikaz, String> colKlijent = new TableColumn<>("Klijent");
        colKlijent.setCellValueFactory(new PropertyValueFactory<>("klijent"));
        TableColumn<DugovanjePrikaz, String> colKontakt = new TableColumn<>("Kontakt");
        colKontakt.setCellValueFactory(new PropertyValueFactory<>("kontakt"));

        TableColumn<DugovanjePrikaz, Integer> colUplataId = new TableColumn<>("Uplata ID");
        colUplataId.setCellValueFactory(new PropertyValueFactory<>("uplataId"));

        TableColumn<DugovanjePrikaz, String> colSvrhaUplate = new TableColumn<>("Svrha uplate");
        colSvrhaUplate.setCellValueFactory(new PropertyValueFactory<>("svrhaUplate"));

        TableColumn<DugovanjePrikaz, LocalDate> colDatumUplate = new TableColumn<>("Datum uplate");
        colDatumUplate.setCellValueFactory(new PropertyValueFactory<>("datumUplate"));

        TableColumn<DugovanjePrikaz, Double> colUplaceniIznos = new TableColumn<>("Uplaćeni iznos");
        colUplaceniIznos.setCellValueFactory(new PropertyValueFactory<>("uplaceniIznos"));

        TableColumn<DugovanjePrikaz, String> colValuta = new TableColumn<>("Valuta");
        colValuta.setCellValueFactory(new PropertyValueFactory<>("valuta"));

        TableColumn<DugovanjePrikaz, String> colValutaNaziv = new TableColumn<>("Naziv valute");
        colValutaNaziv.setCellValueFactory(new PropertyValueFactory<>("valutaNaziv"));

        TableColumn<DugovanjePrikaz, String> colNacinUplate = new TableColumn<>("Način uplate");
        colNacinUplate.setCellValueFactory(new PropertyValueFactory<>("nacinUplate"));

        TableColumn<DugovanjePrikaz, Integer> colRata = new TableColumn<>("Rata");
        colRata.setCellValueFactory(new PropertyValueFactory<>("rata"));

        TableColumn<DugovanjePrikaz, Double> colProvizija = new TableColumn<>("Provizija");
        colProvizija.setCellValueFactory(new PropertyValueFactory<>("provizija"));

        TableColumn<DugovanjePrikaz, String> colTipUsluge = new TableColumn<>("Tip usluge");
        colTipUsluge.setCellValueFactory(new PropertyValueFactory<>("tipUsluge"));

        TableColumn<DugovanjePrikaz, String> colPsihoterapeut = new TableColumn<>("Psihoterapeut");
        colPsihoterapeut.setCellValueFactory(new PropertyValueFactory<>("psihoterapeut"));

        TableColumn<DugovanjePrikaz, LocalDate> colDatumSeanse = new TableColumn<>("Datum seanse");
        colDatumSeanse.setCellValueFactory(new PropertyValueFactory<>("datumSeanse"));

        TableColumn<DugovanjePrikaz, Double> colUkupnaCena = new TableColumn<>("Ukupna cena");
        colUkupnaCena.setCellValueFactory(new PropertyValueFactory<>("ukupnaCena"));

        TableColumn<DugovanjePrikaz, Double> colPreostaloZaUplatu = new TableColumn<>("Preostalo za uplatu");
        colPreostaloZaUplatu.setCellValueFactory(new PropertyValueFactory<>("preostaloZaUplatu"));

        TableColumn<DugovanjePrikaz, String> colPrekoracenRok = new TableColumn<>("Prekoračen rok");
        colPrekoracenRok.setCellValueFactory(new PropertyValueFactory<>("prekoracenRok"));

        TableColumn<DugovanjePrikaz, Integer> colDaniDoPrekoracenja = new TableColumn<>("Dani do prekoračenja");
        colDaniDoPrekoracenja.setCellValueFactory(new PropertyValueFactory<>("daniDoPrekoracenja"));

        TableColumn<DugovanjePrikaz, String> colValidnaUplata = new TableColumn<>("Validna uplata");
        colValidnaUplata.setCellValueFactory(new PropertyValueFactory<>("validnaUplata"));

        tabela.getColumns().addAll(
                colKlijentId, colKlijent, colKontakt,
                colUplataId, colSvrhaUplate, colDatumUplate, colUplaceniIznos,
                colValuta, colValutaNaziv, colNacinUplate, colRata, colProvizija,
                colTipUsluge, colPsihoterapeut, colDatumSeanse,
                colUkupnaCena, colPreostaloZaUplatu,
                colPrekoracenRok, colDaniDoPrekoracenja, colValidnaUplata
        );

        ObservableList<DugovanjePrikaz> podaci = JDBCUtils.getUplateIDugovanjaKlijenata2();
        tabela.setItems(podaci);

        VBox vbox = new VBox(10, tabela);
        vbox.setPadding(new Insets(15));
        this.setCenter(vbox);
        setupContextMenu();
    }

    private void setupContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem detaljiItem = new MenuItem("Prikaži detalje");

        detaljiItem.setOnAction(e -> {
            DugovanjePrikaz selektovano = tabela.getSelectionModel().getSelectedItem();
            if (selektovano != null) {
                prikaziDetalje(selektovano);
            }
        });

        contextMenu.getItems().add(detaljiItem);
        tabela.setContextMenu(contextMenu);
    }

    private void prikaziDetalje(DugovanjePrikaz dugovanje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalji o uplati");
        alert.setHeaderText("Detalji za klijenta: " + dugovanje.getKlijent());

        String content = String.format(
                "Klijent: %s\n" +
                        "Kontakt: %s\n\n" +
                        "Uplata ID: %d\n" +
                        "Svrha uplate: %s\n" +
                        "Datum uplate: %s\n" +
                        "Uplaćeni iznos: %.2f %s (%s)\n" +
                        "Način uplate: %s\n" +
                        "Rata: %d\n" +
                        "Provizija: %.2f\n\n" +
                        "Tip usluge: %s\n" +
                        "Psihoterapeut: %s\n" +
                        "Datum seanse: %s\n\n" +
                        "Ukupna cena: %.2f\n" +
                        "Preostalo za uplatu: %.2f\n\n" +
                        "Status: %s\n" +
                        "Prekoračen rok: %s\n" +
                        "Dani do prekoračenja: %d",
                dugovanje.getKlijent(),
                dugovanje.getKontakt(),
                dugovanje.getUplataId(),
                dugovanje.getSvrhaUplate(),
                dugovanje.getDatumUplate(),
                dugovanje.getUplaceniIznos(),
                dugovanje.getValuta(),
                dugovanje.getValutaNaziv(),
                dugovanje.getNacinUplate(),
                dugovanje.getRata(),
                dugovanje.getProvizija(),
                dugovanje.getTipUsluge(),
                dugovanje.getPsihoterapeut(),
                dugovanje.getDatumSeanse(),
                dugovanje.getUkupnaCena(),
                dugovanje.getPreostaloZaUplatu(),
                dugovanje.getValidnaUplata(),
                dugovanje.getPrekoracenRok(),
                dugovanje.getDaniDoPrekoracenja()
        );

        alert.setContentText(content);
        alert.showAndWait();
    }
}