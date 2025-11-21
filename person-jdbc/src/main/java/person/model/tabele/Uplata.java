package person.model.tabele;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Uplata {
    private final int uplata_id;
    private boolean prva_seansa;
    private String svrha_uplate;
    private int rata;
    private String nacin_uplate;
    private BigDecimal iznos;
    private LocalDate datum_uplate;
    private BigDecimal provizija;
    private int valuta_id;
    private int klijent_id;

    public Uplata(int uplata_id, boolean prva_seansa, String svrha_uplate, int rata, String nacin_uplate, BigDecimal iznos, LocalDate datum_uplate, BigDecimal provizija, int valuta_id, int klijent_id) {
        this.uplata_id = uplata_id;
        this.prva_seansa = prva_seansa;
        this.svrha_uplate = svrha_uplate;
        this.rata = rata;
        this.nacin_uplate = nacin_uplate;
        this.iznos = iznos;
        this.datum_uplate = datum_uplate;
        this.provizija = provizija;
        this.valuta_id = valuta_id;
        this.klijent_id = klijent_id;
    }

    public int getUplata_id() {
        return uplata_id;
    }

    public boolean isPrva_seansa() {
        return prva_seansa;
    }

    public void setPrva_seansa(boolean prva_seansa) {
        this.prva_seansa = prva_seansa;
    }

    public String getSvrha_uplate() {
        return svrha_uplate;
    }

    public void setSvrha_uplate(String svrha_uplate) {
        this.svrha_uplate = svrha_uplate;
    }

    public int getRata() {
        return rata;
    }

    public void setRata(int rata) {
        this.rata = rata;
    }

    public String getNacin_uplate() {
        return nacin_uplate;
    }

    public void setNacin_uplate(String nacin_uplate) {
        this.nacin_uplate = nacin_uplate;
    }

    public BigDecimal getIznos() {
        return iznos;
    }

    public void setIznos(BigDecimal iznos) {
        this.iznos = iznos;
    }

    public LocalDate getDatum_uplate() {
        return datum_uplate;
    }

    public void setDatum_uplate(LocalDate datum_uplate) {
        this.datum_uplate = datum_uplate;
    }

    public BigDecimal getProvizija() {
        return provizija;
    }

    public void setProvizija(BigDecimal provizija) {
        this.provizija = provizija;
    }

    public int getValuta_id() {
        return valuta_id;
    }

    public void setValuta_id(int valuta_id) {
        this.valuta_id = valuta_id;
    }

    public int getKlijent_id() {
        return klijent_id;
    }

    public void setKlijent_id(int klijent_id) {
        this.klijent_id = klijent_id;
    }
}
