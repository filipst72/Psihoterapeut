package person.model.tabele;

public class Prijava_za_seansu {
    private final int prijava_id;
    private int tip_id;
    private int psihoterapeut_id;
    private int kandidat_id;
    private int klijent_id;

    public Prijava_za_seansu(int prijava_id, int tip_id, int psihoterapeut_id, int kandidat_id, int klijent_id) {
        this.prijava_id = prijava_id;
        this.tip_id = tip_id;
        this.psihoterapeut_id = psihoterapeut_id;
        this.kandidat_id = kandidat_id;
        this.klijent_id = klijent_id;
    }

    public int getPrijava_id() {
        return prijava_id;
    }

    public int getTip_id() {
        return tip_id;
    }

    public int getPsihoterapeut_id() {
        return psihoterapeut_id;
    }

    public int getKandidat_id() {
        return kandidat_id;
    }

    public int getKlijent_id() {
        return klijent_id;
    }
}
