package person.model.tabele;

public class Oblast_psihoterapije {
    private final int oblast_id;
    private String naziv;

    public Oblast_psihoterapije(int oblast_id, String naziv) {
        this.oblast_id = oblast_id;
        this.naziv = naziv;
    }

    public int getOblast_id() {
        return oblast_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
