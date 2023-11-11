package z3;

public class Pracownik extends Osoba {
    private String stanowisko;
    private double pensja;

    public Pracownik(String imie, String nazwisko, String pesel, String miasto, String stanowisko, double pensja) {
        super(imie, nazwisko, pesel, miasto);
        this.stanowisko = stanowisko;
        this.pensja = pensja;
    }

    @Override
    public double getDochod() {
        return pensja;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public double getPensja() {
        return pensja;
    }

    public void setPensja(double pensja) {
        this.pensja = pensja;
    }
}
