package person.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import person.model.tabele.Nalog;
import person.model.tabele.Psihoterapeut;
import person.model.utility.JDBCUtils;

public class ProfilView {
    private final VBox view;

    public ProfilView(Psihoterapeut psihoterapeut) {
        this.view = new VBox(10);
        this.view.setPadding(new Insets(20));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Ime:"), 0, 0);
        grid.add(new Label(psihoterapeut.getIme()), 1, 0);

        grid.add(new Label("Prezime:"), 0, 1);
        grid.add(new Label(psihoterapeut.getPrezime()), 1, 1);

        grid.add(new Label("JMBG:"), 0, 2);
        grid.add(new Label(psihoterapeut.getJmbg()), 1, 2);

        grid.add(new Label("Datum rodjenja:"), 0, 3);
        grid.add(new Label(psihoterapeut.getDatum_rodjenja().toString()), 1, 3);

        grid.add(new Label("Prebivaliste:"), 0, 4);
        grid.add(new Label(psihoterapeut.getPrebivaliste()), 1, 4);

        grid.add(new Label("Broj telefona:"), 0, 5);
        grid.add(new Label(psihoterapeut.getBr_telefona()), 1, 5);

        grid.add(new Label("Email:"), 0, 6);
        grid.add(new Label(psihoterapeut.getEmail()), 1, 6);

        grid.add(new Label("Datum sertifikacije:"), 0, 7);
        grid.add(new Label(psihoterapeut.getDatum_sertifikacije().toString()), 1, 7);

        String ps="Nije";
        grid.add(new Label("Da li je psiholog:"), 0, 8);
        if(psihoterapeut.isPsiholog()){
            ps="Jeste";
        }
        grid.add(new Label(ps), 1, 8);

        grid.add(new Label("Oblast id:"), 0, 9);
        String oblas=String.valueOf(psihoterapeut.getOblast_id());
        grid.add(new Label(oblas),1,9);

        grid.add(new Label("Centar id:"), 0, 10);
        String centar=String.valueOf(psihoterapeut.getCentar_id());
        grid.add(new Label(centar), 1, 10);

        view.getChildren().add(grid);
    }

    public VBox getView() {
        return view;
    }
}

