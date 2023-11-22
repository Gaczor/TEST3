package z2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lekarz {
    private int idLekarza;
    private String nazwisko;
    private String imie;
    private String specjalnosc;
    private Date dataUrodzenia;
    private String nip;
    private String pesel;
    private List<Wizyta> listaWizyt;

    public Lekarz(int idLekarza, String nazwisko, String imie, String specjalnosc, Date dataUrodzenia, String nip, String pesel, List<Wizyta> listaWizyt) {
        this.idLekarza = idLekarza;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.specjalnosc = specjalnosc;
        this.dataUrodzenia = dataUrodzenia;
        this.nip = nip;
        this.pesel = pesel;
        this.listaWizyt = new ArrayList<>();
    }

    public void dodajWizyte(Wizyta wizyta) {
        if (!listaWizyt.contains(wizyta)) {
            this.listaWizyt.add(wizyta);
        }
    }

    @Override
    public String toString() {
        return "nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'';
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

    public List<Wizyta> getListaWizyt() {
        return listaWizyt;
    }

    public void setListaWizyt(List<Wizyta> listaWizyt) {
        this.listaWizyt = listaWizyt;
    }


}