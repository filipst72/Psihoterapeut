package person.model.tabele;

public class Nalog {
    private final int nalog_id;
    private String username;
    private String sifra;
    private int psihoterapeut_id;

    public Nalog(int nalog_id, String username, String sifra, int psihoterapeut_id) {
        this.nalog_id = nalog_id;
        this.username = username;
        this.sifra = sifra;
        this.psihoterapeut_id = psihoterapeut_id;
    }

    public int getNalog_id() {
        return nalog_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public int getPsihoterapeut_id() {
        return psihoterapeut_id;
    }

    public void setPsihoterapeut_id(int psihoterapeut_id) {
        this.psihoterapeut_id = psihoterapeut_id;
    }
}
