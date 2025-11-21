package person.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import person.model.tabele.Psihoterapeut;
import person.model.tabele.PregledBeleski;
import person.model.utility.JDBCUtils;

public class PrikazBeleskiView extends BorderPane {

    private static TableView<PregledBeleski> tabela;
    public static void osveziTabelu(Psihoterapeut psihoterapeut) {
        ObservableList<PregledBeleski> noviPodaci = JDBCUtils.getPregledSeansi(psihoterapeut.getPsihoterapeut_id());
        tabela.setItems(noviPodaci);
    }
    public PrikazBeleskiView(Psihoterapeut psihoterapeut) {
        tabela = new TableView<>();

        TableColumn<PregledBeleski, java.time.LocalDate> datumCol = new TableColumn<>("Datum");
        datumCol.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getDatum()));

        TableColumn<PregledBeleski, java.time.LocalTime> vremeCol = new TableColumn<>("Vreme");
        vremeCol.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getVreme()));

        TableColumn<PregledBeleski, Number> trajanjeCol = new TableColumn<>("Trajanje (min)");
        trajanjeCol.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getTrajanje()));

        TableColumn<PregledBeleski, String> klijentCol = new TableColumn<>("Klijent");
        klijentCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getKlijent()));

        TableColumn<PregledBeleski, String> kontaktCol = new TableColumn<>("Kontakt");
        kontaktCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getKlijentKontakt()));

        TableColumn<PregledBeleski, String> beleskeCol = new TableColumn<>("Beleške");
        beleskeCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getBeleske()));

        TableColumn<PregledBeleski, String> testoviCol = new TableColumn<>("Testovi");
        testoviCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTestovi()));

        TableColumn<PregledBeleski, Number> cenaCol = new TableColumn<>("Cena (RSD)");
        cenaCol.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getCenaSeanse()));

        TableColumn<PregledBeleski, String> publikacijaCol = new TableColumn<>("Status publikacije");
        publikacijaCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getStatusPublikacije()));

        TableColumn<PregledBeleski, String> nacinPrijaveCol = new TableColumn<>("Način prijave");
        nacinPrijaveCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNacinPrijave()));

        TableColumn<PregledBeleski, String> tipSeanseCol = new TableColumn<>("Tip seanse");
        tipSeanseCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTipSeanse()));

        tabela.getColumns().addAll(
                datumCol, vremeCol, trajanjeCol, klijentCol, kontaktCol,
                beleskeCol, testoviCol, cenaCol, publikacijaCol,
                nacinPrijaveCol, tipSeanseCol
        );
        ObservableList<PregledBeleski> podaci = JDBCUtils.getPregledSeansi(psihoterapeut.getPsihoterapeut_id());
        tabela.setItems(podaci);
        tabela.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        ScrollPane scrollPane = new ScrollPane(tabela);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(5));
        this.setCenter(scrollPane);
    }
}