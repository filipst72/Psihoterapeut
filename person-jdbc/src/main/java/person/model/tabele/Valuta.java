package person.model.tabele;

public class Valuta {
    private final int valuta_id;
    private String skracenica;
    private String puno_ime;

    public Valuta(int valuta_id, String skracenica, String puno_ime) {
        this.valuta_id = valuta_id;
        this.skracenica = skracenica;
        this.puno_ime = puno_ime;
    }

    public int getValuta_id() {
        return valuta_id;
    }

    public String getSkracenica() {
        return skracenica;
    }

    public void setSkracenica(String skracenica) {
        this.skracenica = skracenica;
    }

    public String getPuno_ime() {
        return puno_ime;
    }

    public void setPuno_ime(String puno_ime) {
        this.puno_ime = puno_ime;
    }
}
