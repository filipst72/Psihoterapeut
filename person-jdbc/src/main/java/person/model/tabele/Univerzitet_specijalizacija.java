package person.model.tabele;

public class Univerzitet_specijalizacija {
    private final int specijalizacija_id;
    private String srodne_oblasti;
    private int univerzitet_id;

    public Univerzitet_specijalizacija(int specijalizacija_id, String srodne_oblasti, int univerzitet_id) {
        this.specijalizacija_id = specijalizacija_id;
        this.srodne_oblasti = srodne_oblasti;
        this.univerzitet_id = univerzitet_id;
    }

    public int getSpecijalizacija_id() {
        return specijalizacija_id;
    }

    public String getSrodne_oblasti() {
        return srodne_oblasti;
    }

    public void setSrodne_oblasti(String srodne_oblasti) {
        this.srodne_oblasti = srodne_oblasti;
    }

    public int getUniverzitet_id() {
        return univerzitet_id;
    }

    public void setUniverzitet_id(int univerzitet_id) {
        this.univerzitet_id = univerzitet_id;
    }
}
