package person.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import person.model.tabele.*;
import person.model.utility.JDBCUtils;

public class PrijavaKlijentView extends BorderPane {

    private TableView<PrijavaPrikaz> tabela;

    public PrijavaKlijentView(Psihoterapeut psihoterapeut) {
        tabela = new TableView<>();
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<PrijavaPrikaz, Number> idCol = new TableColumn<>("ID prijave");
        idCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getPrijava().getPrijava_id()));

        TableColumn<PrijavaPrikaz, String> klijentCol = new TableColumn<>("Klijent");
        klijentCol.setCellValueFactory(cell -> cell.getValue().klijentImePrezimeProperty());

        TableColumn<PrijavaPrikaz, String> komunikacijaCol = new TableColumn<>("Način komunikacije");
        komunikacijaCol.setCellValueFactory(cell -> cell.getValue().komunikacijaKanalProperty());

        tabela.getColumns().addAll(idCol, klijentCol, komunikacijaCol);

        ObservableList<PrijavaPrikaz> podaci = JDBCUtils.pronadjiPrijave(psihoterapeut.getPsihoterapeut_id());
        tabela.setItems(podaci);

        VBox vbox = new VBox(10, tabela);
        vbox.setPadding(new Insets(15));
        this.setCenter(vbox);
    }
}
