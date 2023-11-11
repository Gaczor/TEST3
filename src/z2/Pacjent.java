package z2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pacjent {
    private int idPacjenta;
    private String nazwisko;
    private String imie;
    private String pesel;
    private Date dataUrodzenia;

    public Pacjent(int idPacjenta, String nazwisko, String imie, String pesel, String dataUrodzenia) throws ParseException {
        this.idPacjenta = idPacjenta;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.pesel = pesel;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.dataUrodzenia = sdf.parse(dataUrodzenia);
    }

    @Override
    public String toString() {
        return nazwisko + " " + imie;
    }

    public int getIdPacjenta() {
        return idPacjenta;
    }

    public void setIdPacjenta(int idPacjenta) {
        this.idPacjenta = idPacjenta;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }
}
