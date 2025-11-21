package person.model.tabele;

public class Test_sa_seanse {
    private final int rezultat_id;
    private int rezultat;
    private int seansa_id;
    private int test_id;

    public Test_sa_seanse(int rezultat_id, int rezultat, int seansa_id, int test_id) {
        this.rezultat_id = rezultat_id;
        this.rezultat = rezultat;
        this.seansa_id = seansa_id;
        this.test_id = test_id;
    }

    public int getRezultat_id() {
        return rezultat_id;
    }

    public int getRezultat() {
        return rezultat;
    }

    public void setRezultat(int rezultat) {
        this.rezultat = rezultat;
    }

    public int getSeansa_id() {
        return seansa_id;
    }

    public void setSeansa_id(int seansa_id) {
        this.seansa_id = seansa_id;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }
}
