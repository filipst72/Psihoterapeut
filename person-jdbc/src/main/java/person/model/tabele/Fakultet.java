package person.model.tabele;

public class Fakultet {
    private final int fakultet_id;
    private String naziv;
    private String oblasti;
    private int univerzitet_id;

    public Fakultet(int fakultet_id, String naziv, String oblasti, int univerzitet_id) {
        this.fakultet_id = fakultet_id;
        this.naziv = naziv;
        this.oblasti = oblasti;
        this.univerzitet_id = univerzitet_id;
    }

    public int getFakultet_id() {
        return fakultet_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOblasti() {
        return oblasti;
    }

    public void setOblasti(String oblasti) {
        this.oblasti = oblasti;
    }

    public int getUniverzitet_id() {
        return univerzitet_id;
    }

    public void setUniverzitet_id(int univerzitet_id) {
        this.univerzitet_id = univerzitet_id;
    }
}
