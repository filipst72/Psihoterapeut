package person.model.tabele;

public class Univerzitet {
    private final int univerzitet_id;
    private String naziv;
    private String uze_usmerenje;

    public Univerzitet(int univerzitet_id, String naziv, String uze_usmerenje) {
        this.univerzitet_id = univerzitet_id;
        this.naziv = naziv;
        this.uze_usmerenje = uze_usmerenje;
    }

    public int getUniverzitet_id() {
        return univerzitet_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getUze_usmerenje() {
        return uze_usmerenje;
    }

    public void setUze_usmerenje(String uze_usmerenje) {
        this.uze_usmerenje = uze_usmerenje;
    }
}
