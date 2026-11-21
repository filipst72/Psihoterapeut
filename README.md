# Psihoterapeut – Counseling Center Management App
 
A desktop application for managing a psychotherapy counseling center, built with **Java 11**, **JavaFX** and **MySQL** (accessed through plain **JDBC**). The application window is titled *Savetovalište "Novi početak"* ("New Beginning" Counseling Center).
 
It lets you register certified psychotherapists, browse them, log in as a therapist to see their client applications, past and upcoming sessions and session notes, track client payments and outstanding debts, and record when data from a session was disclosed to a third party.
 
> The UI, the database schema and the code identifiers are in Serbian. A short glossary is at the end of this file.
 
## Features
 
When the app starts, five tabs are available:
 
| Tab | What it does |
|---|---|
| **Pregled Psihoterapeuta** (Therapist overview) | A table of all certified psychotherapists in the database. |
| **Uplate** (Payments) | All client payments, with currency, installment number, commission, total price, remaining amount, a 30-day deadline check, and a validity check for each payment. Right-click a row and choose *Prikaži detalje* to see full details. |
| **Publikacija** (Publication) | A form for recording that data from a session was disclosed: you pick the session and enter the date, the reason, and who it was disclosed to. |
| **Registracija** (Registration) | Registers a new certified psychotherapist: personal data, certification date, whether they are a psychologist, area of psychotherapy, and training center. The overview table refreshes after a successful registration. |
| **Login** | Log in as a therapist with first and last name. |
 
After a successful login, five more tabs are added for that therapist:
 
| Tab | What it does |
|---|---|
| **Profil** (Profile) | The therapist's personal and certification data. |
| **Prijave** (Applications) | Clients who applied for a session with this therapist, with their details and the communication channel they used. |
| **Odrzane seanse** (Past sessions) | Sessions held up to today, newest first. |
| **Buduce seanse** (Upcoming sessions) | Scheduled sessions after today. |
| **Pregled beleski** (Notes overview) | Every session with its notes, the tests taken and their results, the total price (session time plus tests), publication status, application channel, and whether it was a regular session or a supervised session with a candidate in training. |
 
### Payment rules
 
The payments view is driven by a single SQL query in `JDBCUtils.getUplateIDugovanjaKlijenata2()`. It works out:
 
- **Total price**: hourly rate × (duration / 60), plus the price of any tests done during the session.
- **Remaining amount**: total price minus what has been paid, with foreign-currency payments converted to RSD at the latest exchange rate in the `Kurs` table.
- **Deadline**: a first installment is overdue if more than 30 days have passed since it was paid. The view also shows how many days are left.
- **Validity**: a payment is marked invalid if:
  - it is paid in installments in a foreign currency other than EUR;
  - its first installment is less than 30% of the total price;
  - more has been paid than the total price.
## Tech stack
 
- Java 11
- JavaFX 20 (`javafx-controls`)
- MySQL with `mysql-connector-java` 8.0.33
- Maven
## Project structure
 
```
person-jdbc/
├── pom.xml
└── src/main/java/person/
    ├── Main.java                 # Entry point, launches App
    ├── App.java                  # JavaFX Application: builds the window and tabs
    ├── model/
    │   ├── tabele/               # One plain Java class per database table + view models
    │   │   ├── Psihoterapeut, Kandidat, Klijent, Nalog
    │   │   ├── Seansa, Prijava_za_seansu, Nacin_komunikacije, Cena_seanse
    │   │   ├── Test, Test_sa_seanse, Publikacija
    │   │   ├── Uplata, Valuta, Kurs
    │   │   ├── Oblast_psihoterapije, Centar_za_obuku
    │   │   ├── Univerzitet, Univerzitet_specijalizacija, Fakultet
    │   │   └── PrijavaPrikaz, DugovanjePrikaz, PregledBeleski   # display rows that join several tables
    │   └── utility/
    │       └── JDBCUtils.java    # Database connection and every SQL query
    └── view/                     # JavaFX screens, one per tab
        ├── PregledPsihoterapeutaView, RegistracijaView, LoginView
        ├── ProfilView, PrijavaKlijentView
        ├── OdrzaneSeanseView, BuduceSeanseView, PrikazBeleskiView
        ├── DugovanjaView
        └── PublikacijaForm
```
 
## Data model
 
The app expects an existing MySQL database. Its main tables:
 
- **sertifikovani_psihoterapeut**: certified therapists, each linked to an area of psychotherapy (`Oblasti_psihoterapije`) and a training center (`Centar_za_obuku`).
- **Kandidat**: therapists in training, each with a supervising therapist, a training center and a faculty (`Fakultet` → `Univerzitet`).
- **Klijent**: clients, with a problem description and whether they have had therapy before.
- **Prijava_za_seansu**: a client's application for a session, with the communication channel used (`Nacin_komunikacije`).
- **Seansa**: a session with date, time, duration, notes, price (`Cena_seanse`), client, therapist, an optional candidate, and the payment (`Uplata`).
- **Test** / **Test_sa_seanse**: psychological tests and their results for a session.
- **Publikacija**: a record of session data being disclosed (date, reason, recipient).
- **Uplata**, **Valuta**, **Kurs**: payments, currencies, and exchange rates to RSD.
- **Nalog**: user accounts (username/password) linked to a therapist.
The SQL script that creates this schema is **not included** in the repository.
 
## Getting started
 
### Prerequisites
 
- JDK 11 or newer
- Maven
- A running MySQL server with the schema above
### Database configuration
 
The connection is hardcoded in `JDBCUtils.connect()`:
 
```java
URL:      jdbc:mysql://localhost:3306/uciliste
user:     root
password: 1234
```
 
Create a database named `uciliste` with the tables above, or change these values to match your setup.
 
### Running
 
The `pom.xml` doesn't configure the JavaFX Maven plugin, so the simplest way to run the app is from an IDE such as IntelliJ IDEA: open `person-jdbc` as a Maven project and run `person.Main`.
 
To run it with Maven, add the JavaFX plugin to `pom.xml`:
 
```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.openjfx</groupId>
      <artifactId>javafx-maven-plugin</artifactId>
      <version>0.0.8</version>
      <configuration>
        <mainClass>person.Main</mainClass>
      </configuration>
    </plugin>
  </plugins>
</build>
```
 
Then run:
 
```bash
cd person-jdbc
mvn clean javafx:run
```
 
## Known limitations
 
- Database credentials are hardcoded in the source code.
- Login checks only the therapist's first and last name. There is a username/password check in `JDBCUtils.prijaviNalog()`, but nothing calls it.
- The repository has no database schema or seed data script.
- Compiled `.class` files under `target/` are committed. They should be excluded with a `.gitignore`.
## Glossary (Serbian → English)
 
| Serbian | English |
|---|---|
| Psihoterapeut | Psychotherapist |
| Kandidat | Candidate (therapist in training) |
| Klijent | Client |
| Seansa | Session |
| Prijava za seansu | Session application |
| Beleške | Notes |
| Uplata / Dugovanje | Payment / Debt |
| Rata | Installment |
| Valuta / Kurs | Currency / Exchange rate |
| Publikacija | Publication (data disclosure) |
| Oblast psihoterapije | Area of psychotherapy |
| Centar za obuku | Training center |
| Savetovalište | Counseling center |
 
