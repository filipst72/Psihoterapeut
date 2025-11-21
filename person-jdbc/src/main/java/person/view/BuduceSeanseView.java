package person.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import person.model.tabele.Psihoterapeut;
import person.model.tabele.Seansa;
import person.model.utility.JDBCUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class BuduceSeanseView extends BorderPane {

    private TableView<Map.Entry<Seansa, String>> tabela;

    public BuduceSeanseView(Psihoterapeut psihoterapeut) {
        tabela = new TableView<>();
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Map.Entry<Seansa, String>, String> klijentCol = new TableColumn<>("Klijent");
        klijentCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getValue()));

        TableColumn<Map.Entry<Seansa, String>, LocalDate> datumCol = new TableColumn<>("Datum");
        datumCol.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getKey().getDan()));

        TableColumn<Map.Entry<Seansa, String>, LocalTime> vremeCol = new TableColumn<>("Vreme");
        vremeCol.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getKey().getVreme()));

        TableColumn<Map.Entry<Seansa, String>, Number> trajanjeCol = new TableColumn<>("Trajanje (min)");
        trajanjeCol.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getKey().getTrajanje()));

        tabela.getColumns().addAll(klijentCol, datumCol, vremeCol, trajanjeCol);

        Map<Seansa, String> seanse = JDBCUtils.vratiBuduceSeanseSaImenomKlijenta(psihoterapeut.getPsihoterapeut_id());
        ObservableList<Map.Entry<Seansa, String>> podaci = FXCollections.observableArrayList(seanse.entrySet());

        tabela.setItems(podaci);

        VBox vbox = new VBox(10, tabela);
        vbox.setPadding(new Insets(15));
        this.setCenter(vbox);
    }
}