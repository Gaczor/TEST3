package z4;

import java.util.Objects;

public class Prostokat extends Figura {
    private double szerokosc;
    private double wysokosc;

    public Prostokat(double szerokosc, double wysokosc) {
        this(szerokosc, wysokosc, false);
    }

    public Prostokat(double szerokosc, double wysokosc, boolean czyNadacNumer) {
        super(czyNadacNumer);
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
    }

    @Override
    public double obwod() {
        return 2 * (szerokosc + wysokosc);
    }

    @Override
    public double pole() {
        return szerokosc * wysokosc;
    }

    @Override
    protected String opis() {
        return "Prostokąt o bokach " + szerokosc + "x" + wysokosc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prostokat prostokat = (Prostokat) o;
        return Double.compare(prostokat.szerokosc, szerokosc) == 0 && Double.compare(prostokat.wysokosc, wysokosc) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(szerokosc, wysokosc);
    }
}
