package z4;

public abstract class Figura {
    private static int licznik = 0;
    protected final int numer;

    protected Figura(boolean czyNadacNumer) {
        this.numer = czyNadacNumer ? ++licznik : 0;
    }

    public abstract double obwod();
    public abstract double pole();

    @Override
    public String toString() {
        return "Figura nr " + numer + ": " + opis();
    }

    protected abstract String opis();

    public static Figura stworzKwadrat(double bok) {
        return new Kwadrat(bok, true);
    }

    public static Figura stworzKolo(double promien) {
        return new Kolo(promien, true);
    }

    public static Figura stworzProstokat(double szerokosc, double wysokosc) {
        return new Prostokat(szerokosc, wysokosc, true);
    }
}
