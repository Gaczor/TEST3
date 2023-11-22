package z4;

public abstract class Figura {
    private static int licznik = 0;
    protected final int numer;

    protected Figura(int numer) {
        this.numer = 0;
    }


    public abstract double obwod();

    public abstract double pole();

    @Override
    public String toString() {
        return "Figura nr " + numer + ": " + opis();
    }

    protected abstract String opis();

    public static Figura stworzKwadrat(double bok) {
        return new Kwadrat(bok, ++licznik);
    }

    public static Figura stworzKolo(double promien) {
        return new Kolo(promien, ++licznik);
    }

    public static Figura stworzProstokat(double szerokosc, double wysokosc) {
        return new Prostokat(szerokosc, wysokosc, ++licznik);
    }

}
