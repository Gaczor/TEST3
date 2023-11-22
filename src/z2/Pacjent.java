package z2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pacjent {
    private int idPacjenta;
    private String nazwisko;
    private String imie;
    private String pesel;
    private Date dataUrodzenia;
    private List<Wizyta> listaWizytPacjenta;

    public Pacjent(int idPacjenta, String nazwisko, String imie, String pesel, Date dataUrodzenia) {
        this.idPacjenta = idPacjenta;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
        this.listaWizytPacjenta = new ArrayList<>();
    }

    public void dodajWizytePacjenta(Wizyta wizyta) {
        if (!listaWizytPacjenta.contains(wizyta)) {
            this.listaWizytPacjenta.add(wizyta);
        }
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

    public List<Wizyta> getListaWizytPacjenta() {
        return listaWizytPacjenta;
    }

    public void setListaWizytPacjenta(List<Wizyta> listaWizytPacjenta) {
        this.listaWizytPacjenta = listaWizytPacjenta;
    }

    @Override
    public String toString() {
        return "nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'';
    }
}