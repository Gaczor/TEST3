package z3;

public abstract class Osoba {
    private String imie;
    private String nazwisko;
    private String pesel;
    private String miasto;

    public Osoba(String imie, String nazwisko, String pesel, String miasto) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.miasto = miasto;
    }

    public char getPlec() {
        int przedostatniaCyfra = Character.getNumericValue(pesel.charAt(pesel.length() - 2));
        return (przedostatniaCyfra % 2 == 0) ? 'K' : 'M';
    }

    public abstract double getDochod();

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    @Override
    public String toString() {
        return imie + " " + nazwisko;
    }
}
