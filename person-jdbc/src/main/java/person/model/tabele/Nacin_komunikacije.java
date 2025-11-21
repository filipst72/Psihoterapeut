package person.model.tabele;

public class Nacin_komunikacije {
    private final int tip_id;
    private String kanal;

    public Nacin_komunikacije(int tip_id, String kanal) {
        this.tip_id = tip_id;
        this.kanal = kanal;
    }

    public int getTip_id() {
        return tip_id;
    }

    public String getKanal() {
        return kanal;
    }

    public void setKanal(String kanal) {
        this.kanal = kanal;
    }
}
