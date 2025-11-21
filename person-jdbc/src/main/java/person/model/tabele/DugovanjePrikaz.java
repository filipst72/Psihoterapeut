package person.model.tabele;
import com.mysql.cj.conf.IntegerProperty;
import com.mysql.cj.conf.StringProperty;
import javafx.beans.property.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DugovanjePrikaz {
        private final int klijentId;
        private String klijent;
        private String kontakt;
        private int uplataId ;
        private String svrhaUplate ;
        private LocalDate datumUplate ;
        private double uplaceniIznos;
        private String valuta;
        private String valutaNaziv;
        private String nacinUplate ;
        private int rata ;
        private double provizija;
        private double ukupnaCena;
        private double preostaloZaUplatu ;
        private String prekoracenRok ;
        private int daniDoPrekoracenja ;
        private String validnaUplata ;
        private String tipUsluge ;
        private String psihoterapeut ;
        private LocalDate datumSeanse ;

    public DugovanjePrikaz(int klijentId, String klijent, String kontakt, int uplataId, String svrhaUplate, LocalDate datumUplate, double uplaceniIznos, String valuta, String valutaNaziv, String nacinUplate, int rata, double provizija, double ukupnaCena, double preostaloZaUplatu, String prekoracenRok, int daniDoPrekoracenja, String validnaUplata, String tipUsluge, String psihoterapeut, LocalDate datumSeanse) {
        this.klijentId = klijentId;
        this.klijent = klijent;
        this.kontakt = kontakt;
        this.uplataId = uplataId;
        this.svrhaUplate = svrhaUplate;
        this.datumUplate = datumUplate;
        this.uplaceniIznos = uplaceniIznos;
        this.valuta = valuta;
        this.valutaNaziv = valutaNaziv;
        this.nacinUplate = nacinUplate;
        this.rata = rata;
        this.provizija = provizija;
        this.ukupnaCena = ukupnaCena;
        this.preostaloZaUplatu = preostaloZaUplatu;
        this.prekoracenRok = prekoracenRok;
        this.daniDoPrekoracenja = daniDoPrekoracenja;
        this.validnaUplata = validnaUplata;
        this.tipUsluge = tipUsluge;
        this.psihoterapeut = psihoterapeut;
        this.datumSeanse = datumSeanse;
    }

    public int getKlijentId() {
        return klijentId;
    }

    public String getKlijent() {
        return klijent;
    }

    public void setKlijent(String klijent) {
        this.klijent = klijent;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public int getUplataId() {
        return uplataId;
    }

    public void setUplataId(int uplataId) {
        this.uplataId = uplataId;
    }

    public String getSvrhaUplate() {
        return svrhaUplate;
    }

    public void setSvrhaUplate(String svrhaUplate) {
        this.svrhaUplate = svrhaUplate;
    }

    public LocalDate getDatumUplate() {
        return datumUplate;
    }

    public void setDatumUplate(LocalDate datumUplate) {
        this.datumUplate = datumUplate;
    }

    public double getUplaceniIznos() {
        return uplaceniIznos;
    }

    public void setUplaceniIznos(double uplaceniIznos) {
        this.uplaceniIznos = uplaceniIznos;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getValutaNaziv() {
        return valutaNaziv;
    }

    public void setValutaNaziv(String valutaNaziv) {
        this.valutaNaziv = valutaNaziv;
    }

    public String getNacinUplate() {
        return nacinUplate;
    }

    public void setNacinUplate(String nacinUplate) {
        this.nacinUplate = nacinUplate;
    }

    public int getRata() {
        return rata;
    }

    public void setRata(int rata) {
        this.rata = rata;
    }

    public double getProvizija() {
        return provizija;
    }

    public void setProvizija(double provizija) {
        this.provizija = provizija;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public double getPreostaloZaUplatu() {
        return preostaloZaUplatu;
    }

    public void setPreostaloZaUplatu(double preostaloZaUplatu) {
        this.preostaloZaUplatu = preostaloZaUplatu;
    }

    public String getPrekoracenRok() {
        return prekoracenRok;
    }

    public void setPrekoracenRok(String prekoracenRok) {
        this.prekoracenRok = prekoracenRok;
    }

    public int getDaniDoPrekoracenja() {
        return daniDoPrekoracenja;
    }

    public void setDaniDoPrekoracenja(int daniDoPrekoracenja) {
        this.daniDoPrekoracenja = daniDoPrekoracenja;
    }

    public String getValidnaUplata() {
        return validnaUplata;
    }

    public void setValidnaUplata(String validnaUplata) {
        this.validnaUplata = validnaUplata;
    }

    public String getTipUsluge() {
        return tipUsluge;
    }

    public void setTipUsluge(String tipUsluge) {
        this.tipUsluge = tipUsluge;
    }

    public String getPsihoterapeut() {
        return psihoterapeut;
    }

    public void setPsihoterapeut(String psihoterapeut) {
        this.psihoterapeut = psihoterapeut;
    }

    public LocalDate getDatumSeanse() {
        return datumSeanse;
    }

    public void setDatumSeanse(LocalDate datumSeanse) {
        this.datumSeanse = datumSeanse;
    }
}
