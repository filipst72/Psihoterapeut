package person.model.tabele;

import java.time.LocalDate;

public class Publikacija {
    private final int publikacija_id;
    private String razlog;
    private LocalDate datum;
    private String otkriveno_za;
    private int seansa_id;

    public Publikacija(int publikacija_id, String razlog, LocalDate datum, String otkriveno_za, int seansa_id) {
        this.publikacija_id = publikacija_id;
        this.razlog = razlog;
        this.datum = datum;
        this.otkriveno_za = otkriveno_za;
        this.seansa_id = seansa_id;
    }

    public int getPublikacija_id() {
        return publikacija_id;
    }

    public String getRazlog() {
        return razlog;
    }

    public void setRazlog(String razlog) {
        this.razlog = razlog;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getOtkriveno_za() {
        return otkriveno_za;
    }

    public void setOtkriveno_za(String otkriveno_za) {
        this.otkriveno_za = otkriveno_za;
    }

    public int getSeansa_id() {
        return seansa_id;
    }

    public void setSeansa_id(int seansa_id) {
        this.seansa_id = seansa_id;
    }
}
