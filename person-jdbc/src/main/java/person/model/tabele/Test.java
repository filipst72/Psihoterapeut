package person.model.tabele;

import java.math.BigDecimal;

public class Test {
    private final int test_id;
    private String oblast;
    private String naziv;
    private BigDecimal cena;

    public Test(int test_id, String oblast, String naziv, BigDecimal cena) {
        this.test_id = test_id;
        this.oblast = oblast;
        this.naziv = naziv;
        this.cena = cena;
    }

    public int getTest_id() {
        return test_id;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }
}
