package z4;

public abstract class Figura {
    private static int licznik = 0;
    protected int numer;

    public Figura() {
    }

    public static Figura stworzKwadrat(int bok) {
        Kwadrat kwadrat = new Kwadrat(bok);
        kwadrat.setNumer(++licznik);
        return kwadrat;
    }

    public static Figura stworzKolo(int promien) {
        Kolo kolo = new Kolo(promien);
        kolo.setNumer(++licznik);
        return kolo;
    }

    public static Figura stworzProstokat(int dlugosc, int szerokosc) {
        Prostokat prostokat = new Prostokat(dlugosc, szerokosc);
        prostokat.setNumer(++licznik);
        return prostokat;
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