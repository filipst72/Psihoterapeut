package person.model.tabele;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cena_seanse {
    private final int cena_seanse_id;
    private BigDecimal cena_po_satu;
    private LocalDate vazi_od;
    private LocalDate vazi_do;

    public Cena_seanse(int cena_seanse_id, BigDecimal cena_po_satu, LocalDate vazi_od, LocalDate vazi_do) {
        this.cena_seanse_id = cena_seanse_id;
        this.cena_po_satu = cena_po_satu;
        this.vazi_od = vazi_od;
        this.vazi_do = vazi_do;
    }

    public int getCena_seanse_id() {
        return cena_seanse_id;
    }

    public BigDecimal getCena_po_satu() {
        return cena_po_satu;
    }

    public void setCena_po_satu(BigDecimal cena_po_satu) {
        this.cena_po_satu = cena_po_satu;
    }

    public LocalDate getVazi_od() {
        return vazi_od;
    }

    public void setVazi_od(LocalDate vazi_od) {
        this.vazi_od = vazi_od;
    }

    public LocalDate getVazi_do() {
        return vazi_do;
    }

    public void setVazi_do(LocalDate vazi_do) {
        this.vazi_do = vazi_do;
    }
}
