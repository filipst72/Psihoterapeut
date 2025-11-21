package person.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import person.App;
import person.model.tabele.Psihoterapeut;
import person.model.utility.JDBCUtils;

public class LoginView {

    private final VBox view;

    public LoginView() {
        this.view = new VBox(10);
        this.view.setPadding(new Insets(20));

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        Label imeLabel = new Label("Ime psihoterapeuta:");
        TextField imeField = new TextField();

        Label prezimeLabel = new Label("Prezime:");
        TextField prezimeField = new TextField();

        Button loginButton = new Button("Prijavi se");
        Label poruka = new Label();

        form.add(imeLabel, 0, 0);
        form.add(imeField, 1, 0);
        form.add(prezimeLabel, 0, 1);
        form.add(prezimeField, 1, 1);
        form.add(loginButton, 1, 2);
        form.add(poruka, 1, 3);

        loginButton.setOnAction(e -> {
            String ime = imeField.getText().trim();
            String prezime = prezimeField.getText().trim();

            if (ime.isEmpty() || prezime.isEmpty()) {
                poruka.setText("Unesite ime i prezime.");
            } else {
                Psihoterapeut psihoterapeut = JDBCUtils.pronadjiPsihoterapeuta(ime, prezime);
                if (psihoterapeut != null) {
                    poruka.setText("Uspešna prijava.");
                    imeField.clear();
                    prezimeField.clear();
                    App.tabovi(psihoterapeut);
                } else {
                    poruka.setText("Psihoterapeut nije pronađen.");
                }
            }
        });

        view.getChildren().add(form);
    }

    public VBox getView() {
        return view;
    }
}
