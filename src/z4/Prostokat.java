package z4;

import java.util.Objects;

public class Prostokat extends Figura {
    private int dlugosc, szerokosc;

    public Prostokat(int dlugosc, int szerokosc) {
        this.dlugosc = dlugosc;
        this.szerokosc = szerokosc;
    }

    @Override
    double obwod() {
        return 2 * (dlugosc + szerokosc);
    }

    @Override
    double pole() {
        return dlugosc * szerokosc;
    }

    @Override
    public String toString() {
        return "Figura nr " + numer + ": Prostokat o bokach " + dlugosc + "x" + szerokosc + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prostokat prostokat = (Prostokat) o;
        return dlugosc == prostokat.dlugosc && szerokosc == prostokat.szerokosc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dlugosc, szerokosc);
    }
}
