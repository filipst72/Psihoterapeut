package person;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import person.model.tabele.PregledBeleski;
import person.model.tabele.Psihoterapeut;
import person.view.*;
import person.model.utility.JDBCUtils;

public class App extends Application {
    static TabPane tabPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        JDBCUtils.connect();
        tabPane = new TabPane();

        PregledPsihoterapeutaView pregledView = new PregledPsihoterapeutaView();
        Tab pregledTab = new Tab("Pregled Psihoterapeuta", pregledView);
        Tab registracijaTab = new Tab("Registracija", new RegistracijaView(() -> pregledView.osveziTabelu()).getView());
        Tab loginTab = new Tab("Login", new LoginView().getView());
        Tab uplateTab = new Tab("Uplate",new DugovanjaView());
        Tab publikacijaTab=new Tab("Publikacija",new PublikacijaForm());
        tabPane.getTabs().addAll(pregledTab,uplateTab,publikacijaTab,registracijaTab,loginTab);
        tabPane.getTabs().forEach(tab -> tab.setClosable(false));

        Scene scene = new Scene(tabPane, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Savetovalište \"Novi početak\"");
        primaryStage.show();
    }

    public static void tabovi(Psihoterapeut psihoterapeut){
        Tab profilTab = new Tab("Profil", new ProfilView(psihoterapeut).getView());
        Tab prijaveTab = new Tab("Prijave",new PrijavaKlijentView(psihoterapeut));
        Tab seanseTab = new Tab("Odrzane seanse",new OdrzaneSeanseView(psihoterapeut));
        Tab buduceTab = new Tab("Buduce seanse",new BuduceSeanseView(psihoterapeut));
        Tab beleskeTab=new Tab("Pregled beleski",new PrikazBeleskiView(psihoterapeut));
        /*Tab publikacijaTab=new Tab("Publikacija",new PublikacijaForm());
        PublikacijaForm.setPublikacijaListener(() -> {
            PrikazBeleskiView.osveziTabelu(psihoterapeut);
        });*/
        tabPane.getTabs().addAll(profilTab,prijaveTab,seanseTab,buduceTab,beleskeTab);//,publikacijaTab
    }

}
