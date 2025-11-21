package person.model.tabele;

public class Centar_za_obuku {
    private final int centar_id;
    private String naziv;
    private String email;
    private String br_telefona;
    private String ulica;
    private int br_ulice;
    private String opstina;

    public Centar_za_obuku(int centar_id, String naziv, String email, String br_telefona, String ulica, int br_ulice, String opstina) {
        this.centar_id = centar_id;
        this.naziv = naziv;
        this.email = email;
        this.br_telefona = br_telefona;
        this.ulica = ulica;
        this.br_ulice = br_ulice;
        this.opstina = opstina;
    }

    public int getCentar_id() {
        return centar_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBr_telefona() {
        return br_telefona;
    }

    public void setBr_telefona(String br_telefona) {
        this.br_telefona = br_telefona;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getBr_ulice() {
        return br_ulice;
    }

    public void setBr_ulice(int br_ulice) {
        this.br_ulice = br_ulice;
    }

    public String getOpstina() {
        return opstina;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }
}
