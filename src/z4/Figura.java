package z4;

public abstract class Figura {
    private static int licznik = 0;
    protected int numer;

    public Figura() {
        licznik++;
        this.numer = licznik;
    }


    public static Figura stworzKwadrat(int bok) {
        return new Kwadrat(bok);
    }

    public static Figura stworzKolo(int promien) {
        return new Kolo(promien);
    }

    public static Figura stworzProstokat(int dlugosc, int szerokosc) {
        return new Prostokat(dlugosc, szerokosc);
    }

    public Figura(int numer) {
        this.numer = numer;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    abstract double obwod();
    abstract double pole();

    @Override
    public abstract String toString();
}