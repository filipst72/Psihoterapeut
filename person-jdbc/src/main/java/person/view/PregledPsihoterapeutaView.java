package person.view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import person.model.tabele.Psihoterapeut;
import person.model.utility.JDBCUtils;

public class PregledPsihoterapeutaView extends VBox {
    private final TableView<Psihoterapeut> table;
    public PregledPsihoterapeutaView() {
        table = new TableView<>();

        TableColumn<Psihoterapeut, Integer> idCol = new TableColumn<>("ID");
        TableColumn<Psihoterapeut, String> imeCol = new TableColumn<>("Ime");
        TableColumn<Psihoterapeut, String> prezimeCol = new TableColumn<>("Prezime");
        TableColumn<Psihoterapeut, String> jmbgCol = new TableColumn<>("JMBG");
        TableColumn<Psihoterapeut, Integer> rodjCol = new TableColumn<>("Datum rodjenja");
        TableColumn<Psihoterapeut, String> prebivalisteCol = new TableColumn<>("Prebivaliste");
        TableColumn<Psihoterapeut, String> brtelCol = new TableColumn<>("Broj telefona");
        TableColumn<Psihoterapeut, String> emailCol = new TableColumn<>("Email");
        TableColumn<Psihoterapeut, Integer> sertifCol = new TableColumn<>("Datum sertifikacije");
        TableColumn<Psihoterapeut, String> psihologCOl = new TableColumn<>("Psiholog");
        TableColumn<Psihoterapeut, String> oblastCol = new TableColumn<>("Oblast id");
        TableColumn<Psihoterapeut, String> centarCol = new TableColumn<>("Centar id");

        idCol.setCellValueFactory(new PropertyValueFactory<>("psihoterapeut_id"));
        imeCol.setCellValueFactory(new PropertyValueFactory<>("ime"));
        prezimeCol.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        jmbgCol.setCellValueFactory(new PropertyValueFactory<>("jmbg"));
        rodjCol.setCellValueFactory(new PropertyValueFactory<>("datum_rodjenja"));
        prebivalisteCol.setCellValueFactory(new PropertyValueFactory<>("prebivaliste"));
        brtelCol.setCellValueFactory(new PropertyValueFactory<>("br_telefona"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        sertifCol.setCellValueFactory(new PropertyValueFactory<>("datum_sertifikacije"));
        psihologCOl.setCellValueFactory(new PropertyValueFactory<>("psiholog"));
        oblastCol.setCellValueFactory(new PropertyValueFactory<>("oblast_id"));
        centarCol.setCellValueFactory(new PropertyValueFactory<>("centar_id"));



        table.getColumns().addAll(idCol, imeCol, prezimeCol, jmbgCol,rodjCol,prebivalisteCol,brtelCol,emailCol,sertifCol,psihologCOl,oblastCol,centarCol);


        table.getItems().addAll(JDBCUtils.selectAllFromPsihoterapeut());

        this.getChildren().add(table);
        this.setSpacing(10);
        osveziTabelu();
    }
    public void osveziTabelu() {
        table.getItems().setAll(JDBCUtils.selectAllFromPsihoterapeut());
    }
}
