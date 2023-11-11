package z2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lekarz {
    private int idLekarza;
    private String nazwisko;
    private String imie;
    private String specjalnosc;
    private Date dataUrodzenia;
    private String nip;
    private String pesel;

    public Lekarz(int idLekarza, String nazwisko, String imie, String specjalnosc, String dataUrodzenia, String nip, String pesel) throws ParseException {
        this.idLekarza = idLekarza;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.specjalnosc = specjalnosc;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.dataUrodzenia = sdf.parse(dataUrodzenia);
        this.nip = nip;
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return nazwisko + " " + imie + " " + " - " + specjalnosc;
    }

    public int getIdLekarza() {
        return idLekarza;
    }

    public void setIdLekarza(int idLekarza) {
        this.idLekarza = idLekarza;
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

    public String getSpecjalnosc() {
        return specjalnosc;
    }

    public void setSpecjalnosc(String specjalnosc) {
        this.specjalnosc = specjalnosc;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
