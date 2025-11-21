package person.model.utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import person.model.tabele.*;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class JDBCUtils {

    public static Connection connection = null;

    public static void connect() {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "1234");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/uciliste", properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Psihoterapeut> selectAllFromPsihoterapeut() {
       List<Psihoterapeut> lista = new ArrayList<>();
       String query = "SELECT * FROM sertifikovani_psihoterapeut";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int psihoterpeutid = resultSet.getInt(1);
                String ime = resultSet.getString(2);
                String prezime = resultSet.getString(3);
                String jmbg= resultSet.getString(4);
                LocalDate dateOfBirth = resultSet.getDate(5).toLocalDate();
                String prebivaliste= resultSet.getString(6);
                String brtelefona=resultSet.getString(7);
                String email=resultSet.getString(8);
                LocalDate datumSertifikacije=resultSet.getDate(9).toLocalDate();
                boolean psiholog=resultSet.getBoolean(10);
                int oblast_id=resultSet.getInt(11);
                int centar_id=resultSet.getInt(12);
                Psihoterapeut person = new Psihoterapeut(psihoterpeutid, ime, prezime, jmbg,dateOfBirth,prebivaliste,brtelefona,email,datumSertifikacije,psiholog,oblast_id,centar_id);
                lista.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //  e.printStackTrace();
        }

       return lista;
    }

    public static boolean registrujPsihologa(String ime,String prezime,String jmbg,LocalDate datumRodjenja,String prebivaliste,String brTelefona,String email,
                                             LocalDate datumSertifikacije,boolean psiholog,int oblastId,int centarId) {
        String sql = "INSERT INTO sertifikovani_psihoterapeut (ime, prezime, jmbg, datum_rodjenja, prebivaliste, br_telefona, email, datum_sertifikacije, psiholog, oblast_id, centar_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            stmt.setString(1, ime);
            stmt.setString(2, prezime);
            stmt.setString(3, jmbg);
            stmt.setDate(4, java.sql.Date.valueOf(datumRodjenja));
            stmt.setString(5, prebivaliste);
            stmt.setString(6, brTelefona);
            stmt.setString(7, email);
            stmt.setDate(8, java.sql.Date.valueOf(datumSertifikacije));
            stmt.setBoolean(9, psiholog);
            stmt.setInt(10, oblastId);
            stmt.setInt(11, centarId);

            int rowsInserted = stmt.executeUpdate();
            connection.commit();
            return rowsInserted > 0;

        }catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Unikatno ograničenje povređeno: " + e.getMessage());
            return false; }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean prijaviNalog(String username, String sifra) {
        String sql = "SELECT * FROM nalog WHERE username = ? AND sifra = ?";

        try {
            Connection conn = connection;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, sifra);

            return stmt.executeQuery().next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Psihoterapeut pronadjiPsihoterapeuta(String ime, String prezime) {
        String sql = "SELECT * FROM sertifikovani_psihoterapeut WHERE ime = ? AND prezime = ?";
        try {
            Connection conn = connection;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ime);
            stmt.setString(2, prezime);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Psihoterapeut(rs.getInt("psihoterapeut_id"),rs.getString("ime"),rs.getString("prezime"),rs.getString("jmbg"),
                        rs.getDate("datum_rodjenja").toLocalDate(),rs.getString("prebivaliste"),rs.getString("br_telefona"),rs.getString("email"),
                        rs.getDate("datum_sertifikacije").toLocalDate(),rs.getBoolean("psiholog"),rs.getInt("oblast_id"),rs.getInt("centar_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ObservableList<PrijavaPrikaz> pronadjiPrijave(int psihoterapeutId) {
        ObservableList<PrijavaPrikaz> podaci = FXCollections.observableArrayList();
        try {
            String upit = "SELECT p.prijava_id, p.tip_id, p.kandidat_id, p.klijent_id, " +
                    "k.klijent_id, k.ime, k.prezime, k.datum_rodjenja, k.pol, k.email, k.br_telefona, " +
                    "k.prethodno_iskustvo, k.opis_problema, nk.kanal " +
                    "FROM Prijava_za_seansu p " +
                    "JOIN Klijent k ON p.klijent_id = k.klijent_id " +
                    "JOIN Nacin_komunikacije nk ON p.tip_id = nk.tip_id " +
                    "WHERE p.psihoterapeut_id = ?";

            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, psihoterapeutId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prijava_za_seansu prijava = new Prijava_za_seansu(
                        rs.getInt("prijava_id"),
                        rs.getInt("tip_id"),
                        psihoterapeutId,
                        rs.getInt("kandidat_id"),
                        rs.getInt("klijent_id")
                );

                Klijent klijent = new Klijent(
                        rs.getInt("klijent_id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getDate("datum_rodjenja").toLocalDate(),
                        rs.getString("pol"),
                        rs.getString("email"),
                        rs.getString("br_telefona"),
                        rs.getBoolean("prethodno_iskustvo"),
                        rs.getString("opis_problema")
                );

                Nacin_komunikacije komunikacija = new Nacin_komunikacije(
                        rs.getInt("tip_id"),
                        rs.getString("kanal")
                );

                podaci.add(new PrijavaPrikaz(prijava, klijent, komunikacija));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return podaci;
    }

    public static Map<Seansa, String> vratiOdrzaneSeanseSaImenomKlijenta(int psihoterapeutId) {
        Map<Seansa, String> rezultat = new LinkedHashMap<>();
        String upit = " SELECT s.seansa_id, s.dan, s.vreme, s.trajanje, s.beleske, s.cena_seanse_id, s.kandidat_id, s.psihoterapeut_id,"+
                " s.klijent_id, s.uplata_id, k.ime, k.prezime FROM Seansa s JOIN Klijent k ON s.klijent_id = k.klijent_id "+
                "WHERE s.psihoterapeut_id = ? AND s.dan <= CURRENT_DATE ORDER BY s.dan DESC ";
        try {
            Connection conn = connection;
            PreparedStatement stmt = conn.prepareStatement(upit);
            stmt.setInt(1, psihoterapeutId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Seansa seansa = new Seansa(
                        rs.getInt("seansa_id"),
                        rs.getDate("dan").toLocalDate(),
                        rs.getTime("vreme").toLocalTime(),
                        rs.getInt("trajanje"),
                        rs.getString("beleske"),
                        rs.getInt("cena_seanse_id"),
                        rs.getInt("kandidat_id"),
                        rs.getInt("psihoterapeut_id"),
                        rs.getInt("klijent_id"),
                        rs.getInt("uplata_id")
                );

                String imeKlijenta = rs.getString("ime") + " " + rs.getString("prezime");
                rezultat.put(seansa, imeKlijenta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezultat;
    }

    public static Map<Seansa, String> vratiBuduceSeanseSaImenomKlijenta(int psihoterapeutId) {
        Map<Seansa, String> rezultat = new LinkedHashMap<>();
        String upit = "SELECT s.seansa_id, s.dan, s.vreme, s.trajanje, s.beleske, s.cena_seanse_id,"+
                " s.kandidat_id, s.psihoterapeut_id, s.klijent_id, s.uplata_id,"+
                " k.ime, k.prezime"+
                " FROM Seansa s"+
                "  JOIN Klijent k ON s.klijent_id = k.klijent_id"+
                " WHERE s.psihoterapeut_id = ? AND s.dan > CURRENT_DATE"+
                "  ORDER BY s.dan ASC";

        try{
            PreparedStatement stmt = connection.prepareStatement(upit);
            stmt.setInt(1, psihoterapeutId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Seansa seansa = new Seansa(
                        rs.getInt("seansa_id"),
                        rs.getDate("dan").toLocalDate(),
                        rs.getTime("vreme").toLocalTime(),
                        rs.getInt("trajanje"),
                        rs.getString("beleske"),
                        rs.getInt("cena_seanse_id"),
                        rs.getInt("kandidat_id"),
                        rs.getInt("psihoterapeut_id"),
                        rs.getInt("klijent_id"),
                        rs.getInt("uplata_id")
                );

                String imeKlijenta = rs.getString("ime") + " " + rs.getString("prezime");
                rezultat.put(seansa, imeKlijenta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rezultat;
    }

    /*public static ObservableList<DugovanjePrikaz> getUplateIDugovanjaKlijenata() {
        ObservableList<DugovanjePrikaz> lista = FXCollections.observableArrayList();

        String query = "SELECT " +
                "k.klijent_id, " +
                "CONCAT(k.ime, ' ', k.prezime) AS klijent, " +
                "CONCAT(k.br_telefona, ' / ', k.email) AS kontakt, " +
                "u.uplata_id, " +
                "u.svrha_uplate, " +
                "u.datum_uplate, " +
                "u.iznos AS uplaceni_iznos, " +
                "v.skracenica AS valuta, " +
                "v.puno_ime AS valuta_naziv, " +
                "u.nacin_uplate, " +
                "u.rata, " +
                "u.provizija, " +
                "CASE " +
                "    WHEN s.seansa_id IS NOT NULL THEN cs.cena_po_satu * (s.trajanje/60) " +
                "    WHEN ts.rezultat_id IS NOT NULL THEN t.cena " +
                "    ELSE 0 " +
                "END AS ukupna_cena, " +
                "CASE " +
                "    WHEN s.seansa_id IS NOT NULL THEN " +
                "        (cs.cena_po_satu * (s.trajanje/60)) - " +
                "        (SELECT COALESCE(SUM(u2.iznos), 0) " +
                "         FROM Uplata u2 " +
                "         WHERE u2.klijent_id = k.klijent_id AND u2.uplata_id = u.uplata_id) " +
                "    WHEN ts.rezultat_id IS NOT NULL THEN " +
                "        t.cena - " +
                "        (SELECT COALESCE(SUM(u2.iznos), 0) " +
                "         FROM Uplata u2 " +
                "         WHERE u2.klijent_id = k.klijent_id AND u2.svrha_uplate LIKE CONCAT('%Test:', t.naziv, '%')) " +
                "    ELSE 0 " +
                "END AS preostalo_za_uplatu, " +
                "CASE " +
                "    WHEN u.rata = 1 AND s.seansa_id IS NOT NULL THEN " +
                "        CASE " +
                "            WHEN DATE_ADD(u.datum_uplate, INTERVAL 30 DAY) < CURDATE() THEN 'DA' " +
                "            ELSE 'NE' " +
                "        END " +
                "    ELSE 'NE' " +
                "END AS prekoracen_rok, " +
                "CASE " +
                "    WHEN u.rata = 1 AND s.seansa_id IS NOT NULL THEN " +
                "        DATEDIFF(DATE_ADD(u.datum_uplate, INTERVAL 30 DAY), CURDATE()) " +
                "    ELSE NULL " +
                "END AS dani_do_prekoracenja, " +
                "CASE " +
                "    WHEN v.skracenica != 'RSD' AND u.rata > 1 AND v.skracenica != 'EUR' THEN 'NE - Strana valuta sa ratama (osim EUR)' " +
                "    WHEN u.rata = 1 AND s.seansa_id IS NOT NULL AND " +
                "         u.iznos < 0.3 * (cs.cena_po_satu * (s.trajanje/60)) THEN 'NE - Prva rata manja od 30%' " +
                "    WHEN (SELECT COALESCE(SUM(u2.iznos), 0) " +
                "          FROM Uplata u2 " +
                "          WHERE u2.klijent_id = k.klijent_id AND " +
                "                ((s.seansa_id IS NOT NULL AND u2.uplata_id = s.uplata_id) OR " +
                "                 (ts.rezultat_id IS NOT NULL AND u2.svrha_uplate LIKE CONCAT('%Test:', t.naziv, '%')))) > " +
                "         CASE " +
                "             WHEN s.seansa_id IS NOT NULL THEN cs.cena_po_satu * (s.trajanje/60) " +
                "             WHEN ts.rezultat_id IS NOT NULL THEN t.cena " +
                "             ELSE 0 " +
                "         END THEN 'NE - Uplaćeno više od ukupne cene' " +
                "    ELSE 'DA' " +
                "END AS validna_uplata, " +
                "CASE " +
                "    WHEN s.seansa_id IS NOT NULL THEN CONCAT('Seansa (', s.trajanje, ' min)') " +
                "    WHEN ts.rezultat_id IS NOT NULL THEN CONCAT('Test: ', t.naziv) " +
                "    ELSE 'Ostalo' " +
                "END AS tip_usluge, " +
                "CASE " +
                "    WHEN s.seansa_id IS NOT NULL THEN CONCAT(p.ime, ' ', p.prezime) " +
                "    ELSE NULL " +
                "END AS psihoterapeut, " +
                "s.dan AS datum_seanse " +
                "FROM " +
                "Klijent k " +
                "JOIN Uplata u ON k.klijent_id = u.klijent_id " +
                "JOIN Valuta v ON u.valuta_id = v.valuta_id " +
                "LEFT JOIN Seansa s ON u.uplata_id = s.uplata_id " +
                "LEFT JOIN Cena_seanse cs ON s.cena_seanse_id = cs.cena_seanse_id " +
                "LEFT JOIN Sertifikovani_psihoterapeut p ON s.psihoterapeut_id = p.psihoterapeut_id " +
                "LEFT JOIN Test_sa_seanse ts ON s.seansa_id = ts.seansa_id " +
                "LEFT JOIN Test t ON ts.test_id = t.test_id " +
                "ORDER BY k.prezime, k.ime, u.datum_uplate DESC";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                DugovanjePrikaz podaci = new DugovanjePrikaz(
                        resultSet.getInt("klijent_id"),
                        resultSet.getString("klijent"),
                        resultSet.getString("kontakt"),
                        resultSet.getInt("uplata_id"),
                        resultSet.getString("svrha_uplate"),
                        resultSet.getDate("datum_uplate").toLocalDate(),
                        resultSet.getDouble("uplaceni_iznos"),
                        resultSet.getString("valuta"),
                        resultSet.getString("valuta_naziv"),
                        resultSet.getString("nacin_uplate"),
                        resultSet.getInt("rata"),
                        resultSet.getDouble("provizija"),
                        resultSet.getDouble("ukupna_cena"),
                        resultSet.getDouble("preostalo_za_uplatu"),
                        resultSet.getString("prekoracen_rok"),
                        resultSet.getInt("dani_do_prekoracenja"),
                        resultSet.getString("validna_uplata"),
                        resultSet.getString("tip_usluge"),
                        resultSet.getString("psihoterapeut"),
                        resultSet.getDate("datum_seanse") != null ?
                                resultSet.getDate("datum_seanse").toLocalDate() : null
                );
                lista.add(podaci);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }*/
    public static ObservableList<DugovanjePrikaz> getUplateIDugovanjaKlijenata2() {
        ObservableList<DugovanjePrikaz> lista = FXCollections.observableArrayList();

        String query = "SELECT " +
                "k.klijent_id, " +
                "CONCAT(k.ime, ' ', k.prezime) AS klijent, " +
                "CONCAT(k.br_telefona, ' / ', k.email) AS kontakt, " +
                "u.uplata_id, " +
                "u.svrha_uplate, " +
                "u.datum_uplate, " +
                "u.iznos AS uplaceni_iznos, " +
                "v.skracenica AS valuta, " +
                "v.puno_ime AS valuta_naziv, " +
                "u.nacin_uplate, " +
                "u.rata, " +
                "u.provizija, " +
                "CASE " +
                "    WHEN s.seansa_id IS NOT NULL THEN " +
                "        cs.cena_po_satu * (s.trajanje/60) + " +
                "        IFNULL((SELECT SUM(t.cena) FROM Test_sa_seanse tss JOIN Test t ON tss.test_id = t.test_id WHERE tss.seansa_id = s.seansa_id), 0) " +
                "    ELSE " +
                "        IFNULL(t.cena, 0) " +
                "END AS ukupna_cena, " +
                "CASE " +
                "    WHEN s.seansa_id IS NOT NULL THEN " +
                "        (cs.cena_po_satu * (s.trajanje/60) + " +
                "        IFNULL((SELECT SUM(t.cena) FROM Test_sa_seanse tss JOIN Test t ON tss.test_id = t.test_id WHERE tss.seansa_id = s.seansa_id), 0)) - " +
                "        (SELECT IFNULL(SUM(CASE WHEN u2.valuta_id = (SELECT valuta_id FROM Valuta WHERE skracenica = 'RSD') THEN u2.iznos ELSE u2.iznos * (SELECT kurs_prema_din FROM Kurs WHERE valuta_id = u2.valuta_id ORDER BY datum_promene DESC LIMIT 1) END), 0) " +
                "         FROM Uplata u2 " +
                "         WHERE u2.klijent_id = k.klijent_id AND u2.uplata_id = u.uplata_id) " +
                "    ELSE " +
                "        IFNULL(t.cena, 0) - " +
                "        (SELECT IFNULL(SUM(CASE WHEN u2.valuta_id = (SELECT valuta_id FROM Valuta WHERE skracenica = 'RSD') THEN u2.iznos ELSE u2.iznos * (SELECT kurs_prema_din FROM Kurs WHERE valuta_id = u2.valuta_id ORDER BY datum_promene DESC LIMIT 1) END), 0) " +
                "         FROM Uplata u2 " +
                "         WHERE u2.klijent_id = k.klijent_id AND u2.svrha_uplate LIKE CONCAT('%Test:', IFNULL(t.naziv, ''), '%')) " +
                "END AS preostalo_za_uplatu, " +
                "CASE " +
                "    WHEN u.rata = 1 AND s.seansa_id IS NOT NULL THEN " +
                "        CASE " +
                "            WHEN DATE_ADD(u.datum_uplate, INTERVAL 30 DAY) < CURDATE() THEN 'DA' " +
                "            ELSE 'NE' " +
                "        END " +
                "    ELSE 'NE' " +
                "END AS prekoracen_rok, " +
                "CASE " +
                "    WHEN u.rata = 1 AND s.seansa_id IS NOT NULL THEN " +
                "        DATEDIFF(DATE_ADD(u.datum_uplate, INTERVAL 30 DAY), CURDATE()) " +
                "    ELSE NULL " +
                "END AS dani_do_prekoracenja, " +
                "CASE " +
                "    WHEN v.skracenica != 'RSD' AND u.rata > 1 AND v.skracenica != 'EUR' THEN 'NE - Strana valuta sa ratama (osim EUR)' " +
                "    WHEN u.rata = 1 AND s.seansa_id IS NOT NULL AND " +
                "         (CASE WHEN u.valuta_id = (SELECT valuta_id FROM Valuta WHERE skracenica = 'RSD') THEN u.iznos ELSE u.iznos * (SELECT kurs_prema_din FROM Kurs WHERE valuta_id = u.valuta_id ORDER BY datum_promene DESC LIMIT 1) END) < " +
                "         0.3 * (cs.cena_po_satu * (s.trajanje/60) + " +
                "         IFNULL((SELECT SUM(t.cena) FROM Test_sa_seanse tss JOIN Test t ON tss.test_id = t.test_id WHERE tss.seansa_id = s.seansa_id), 0)) THEN 'NE - Prva rata manja od 30%' " +
                "    WHEN (SELECT IFNULL(SUM(CASE WHEN u2.valuta_id = (SELECT valuta_id FROM Valuta WHERE skracenica = 'RSD') THEN u2.iznos ELSE u2.iznos * (SELECT kurs_prema_din FROM Kurs WHERE valuta_id = u2.valuta_id ORDER BY datum_promene DESC LIMIT 1) END), 0) " +
                "          FROM Uplata u2 " +
                "          WHERE u2.klijent_id = k.klijent_id AND " +
                "                ((s.seansa_id IS NOT NULL AND u2.uplata_id = s.uplata_id) OR " +
                "                 (ts.rezultat_id IS NOT NULL AND u2.svrha_uplate LIKE CONCAT('%Test:', IFNULL(t.naziv, ''), '%')))) > " +
                "         CASE " +
                "             WHEN s.seansa_id IS NOT NULL THEN " +
                "                 cs.cena_po_satu * (s.trajanje/60) + " +
                "                 IFNULL((SELECT SUM(t.cena) FROM Test_sa_seanse tss JOIN Test t ON tss.test_id = t.test_id WHERE tss.seansa_id = s.seansa_id), 0) " +
                "             ELSE " +
                "                 IFNULL(t.cena, 0) " +
                "         END THEN 'NE - Uplaćeno više od ukupne cene' " +
                "    ELSE 'DA' " +
                "END AS validna_uplata, " +
                "CASE " +
                "    WHEN s.seansa_id IS NOT NULL THEN CONCAT('Seansa (', s.trajanje, ' min)') " +
                "    WHEN ts.rezultat_id IS NOT NULL THEN CONCAT('Test: ', t.naziv) " +
                "    ELSE 'Ostalo' " +
                "END AS tip_usluge, " +
                "CASE " +
                "    WHEN s.seansa_id IS NOT NULL THEN CONCAT(p.ime, ' ', p.prezime) " +
                "    ELSE NULL " +
                "END AS psihoterapeut, " +
                "s.dan AS datum_seanse " +
                "FROM " +
                "Klijent k " +
                "JOIN Uplata u ON k.klijent_id = u.klijent_id " +
                "JOIN Valuta v ON u.valuta_id = v.valuta_id " +
                "LEFT JOIN Seansa s ON u.uplata_id = s.uplata_id " +
                "LEFT JOIN Cena_seanse cs ON s.cena_seanse_id = cs.cena_seanse_id " +
                "LEFT JOIN Sertifikovani_psihoterapeut p ON s.psihoterapeut_id = p.psihoterapeut_id " +
                "LEFT JOIN Test_sa_seanse ts ON s.seansa_id = ts.seansa_id " +
                "LEFT JOIN Test t ON ts.test_id = t.test_id " +
                "ORDER BY k.prezime, k.ime, u.datum_uplate DESC";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                DugovanjePrikaz podaci = new DugovanjePrikaz(
                        resultSet.getInt("klijent_id"),
                        resultSet.getString("klijent"),
                        resultSet.getString("kontakt"),
                        resultSet.getInt("uplata_id"),
                        resultSet.getString("svrha_uplate"),
                        resultSet.getDate("datum_uplate").toLocalDate(),
                        resultSet.getDouble("uplaceni_iznos"),
                        resultSet.getString("valuta"),
                        resultSet.getString("valuta_naziv"),
                        resultSet.getString("nacin_uplate"),
                        resultSet.getInt("rata"),
                        resultSet.getDouble("provizija"),
                        resultSet.getDouble("ukupna_cena"),
                        resultSet.getDouble("preostalo_za_uplatu"),
                        resultSet.getString("prekoracen_rok"),
                        resultSet.getInt("dani_do_prekoracenja"),
                        resultSet.getString("validna_uplata"),
                        resultSet.getString("tip_usluge"),
                        resultSet.getString("psihoterapeut"),
                        resultSet.getDate("datum_seanse") != null ?
                                resultSet.getDate("datum_seanse").toLocalDate() : null
                );
                lista.add(podaci);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static ObservableList<PregledBeleski> getPregledSeansi(int psihoterapeutId) {
        ObservableList<PregledBeleski> lista = FXCollections.observableArrayList();
        String query =
                "SELECT " +
                        "    s.seansa_id, " +
                        "    s.dan AS datum_seanse, " +
                        "    s.vreme AS vreme_seanse, " +
                        "    s.trajanje AS trajanje_minuti, " +
                        "    CONCAT(k.ime, ' ', k.prezime) AS klijent, " +
                        "    CONCAT(k.email, ' / ', k.br_telefona) AS klijent_kontakt, " +
                        "    s.beleske, " +
                        "    (SELECT GROUP_CONCAT(CONCAT(t2.naziv, ' (', t2.oblast, ') - rezultat: ', ts2.rezultat) ORDER BY t2.naziv SEPARATOR ', ') " +
                        "     FROM Test_sa_seanse ts2 " +
                        "     JOIN Test t2 ON ts2.test_id = t2.test_id " +
                        "     WHERE ts2.seansa_id = s.seansa_id) AS testovi, " +
                        "    ROUND((IFNULL((SELECT SUM(t3.cena) FROM Test_sa_seanse ts3 JOIN Test t3 ON ts3.test_id = t3.test_id WHERE ts3.seansa_id = s.seansa_id), 0) + " +
                        "    (s.trajanje/60 * cs.cena_po_satu)),2) AS ukupna_cena_seanse, " +
                        "    IFNULL(CONCAT('Objavljeno: ', p.datum, ' - Razlog: ', p.razlog, ' - Za: ', p.otkriveno_za), 'Nije objavljeno') AS status_publikacije, " +
                        "    nk.kanal AS nacin_prijave_za_seansu, " +
                        "    IF(kand.kandidat_id IS NOT NULL, " +
                        "       CONCAT('Supervizija: ', sp.ime, ' ', sp.prezime), " +
                        "       'Regularna seansa') AS tip_seanse " +
                        "FROM " +
                        "    Seansa s " +
                        "JOIN " +
                        "    Klijent k ON s.klijent_id = k.klijent_id " +
                        "JOIN " +
                        "    Cena_seanse cs ON s.cena_seanse_id = cs.cena_seanse_id " +
                        "JOIN " +
                        "    Sertifikovani_psihoterapeut ps ON s.psihoterapeut_id = ps.psihoterapeut_id " +
                        "LEFT JOIN " +
                        "    Publikacija p ON s.seansa_id = p.seansa_id " +
                        "LEFT JOIN " +
                        "    Prijava_za_seansu pzs ON s.prijava_id = pzs.prijava_id " +
                        "LEFT JOIN " +
                        "    Nacin_komunikacije nk ON pzs.tip_id = nk.tip_id " +
                        "LEFT JOIN " +
                        "    Kandidat kand ON s.kandidat_id = kand.kandidat_id " +
                        "LEFT JOIN " +
                        "    Sertifikovani_psihoterapeut sp ON kand.psihoterapeut_id = sp.psihoterapeut_id " +
                        "WHERE " +
                        "    s.psihoterapeut_id = ? " +
                        "ORDER BY " +
                        "    s.dan DESC, s.vreme DESC";


        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, psihoterapeutId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PregledBeleski seansa = new PregledBeleski(
                        resultSet.getInt("seansa_id"),
                        resultSet.getDate("datum_seanse").toLocalDate(),
                        resultSet.getTime("vreme_seanse").toLocalTime(),
                        resultSet.getInt("trajanje_minuti"),
                        resultSet.getString("klijent"),
                        resultSet.getString("klijent_kontakt"),
                        resultSet.getString("beleske"),
                        resultSet.getString("testovi"),
                        resultSet.getDouble("ukupna_cena_seanse"),
                        resultSet.getString("status_publikacije"),
                        resultSet.getString("nacin_prijave_za_seansu"),
                        resultSet.getString("tip_seanse")
                );
                lista.add(seansa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static ObservableList<Seansa> getSveSeanseZaPublikaciju() {
        ObservableList<Seansa> lista = FXCollections.observableArrayList();
        String query = "SELECT * FROM Seansa ORDER BY seansa_id";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Seansa seansa = new Seansa(
                        resultSet.getInt("seansa_id"),
                        resultSet.getDate("dan").toLocalDate(),
                        resultSet.getTime("vreme").toLocalTime(),
                        resultSet.getInt("trajanje"),
                        resultSet.getString("beleske"),
                        resultSet.getInt("cena_seanse_id"),
                        resultSet.getInt("kandidat_id"),
                        resultSet.getInt("psihoterapeut_id"),
                        resultSet.getInt("klijent_id"),
                        resultSet.getInt("uplata_id")
                );
                lista.add(seansa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static void sacuvajPublikaciju(Publikacija publikacija) {
        String query = "INSERT INTO Publikacija (razlog, datum, otkriveno_za, seansa_id) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, publikacija.getRazlog());
            statement.setDate(2, java.sql.Date.valueOf(publikacija.getDatum()));
            statement.setString(3, publikacija.getOtkriveno_za());
            statement.setInt(4, publikacija.getSeansa_id());

            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Oblast_psihoterapije> getSveOblasti(){
        List<Oblast_psihoterapije> oblasti = new ArrayList<>();
        String query = "SELECT * FROM Oblasti_psihoterapije";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                oblasti.add(new Oblast_psihoterapije(
                        rs.getInt("oblast_id"),
                        rs.getString("naziv")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return oblasti;
    }

    public static List<Centar_za_obuku> getSveCentre() {
        List<Centar_za_obuku> centri = new ArrayList<>();
        String query = "SELECT * FROM Centar_za_obuku";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                centri.add(new Centar_za_obuku(
                        rs.getInt("centar_id"),
                        rs.getString("naziv"),
                        rs.getString("email"),
                        rs.getString("br_telefona"),
                        rs.getString("ulica"),
                        rs.getInt("br_ulice"),
                        rs.getString("opstina")
                ));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return centri;
    }

    private JDBCUtils() {

    }
}