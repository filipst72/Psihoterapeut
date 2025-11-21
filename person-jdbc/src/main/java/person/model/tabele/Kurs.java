package person.model.tabele;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Kurs {
    private final int kurs_id;
    private BigDecimal kurs_prema_din;
    private LocalDate datum_promene;
    private int valuta_id;

    public Kurs(int kurs_id, BigDecimal kurs_prema_din, LocalDate datum_promene, int valuta_id) {
        this.kurs_id = kurs_id;
        this.kurs_prema_din = kurs_prema_din;
        this.datum_promene = datum_promene;
        this.valuta_id = valuta_id;
    }

    public int getKurs_id() {
        return kurs_id;
    }

    public BigDecimal getKurs_prema_din() {
        return kurs_prema_din;
    }

    public void setKurs_prema_din(BigDecimal kurs_prema_din) {
        this.kurs_prema_din = kurs_prema_din;
    }

    public LocalDate getDatum_promene() {
        return datum_promene;
    }

    public void setDatum_promene(LocalDate datum_promene) {
        this.datum_promene = datum_promene;
    }

    public int getValuta_id() {
        return valuta_id;
    }

    public void setValuta_id(int valuta_id) {
        this.valuta_id = valuta_id;
    }
}
