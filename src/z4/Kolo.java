package z4;

import java.util.Objects;

public class Kolo extends Figura {
    private int promien;

    public Kolo(int promien) {
        this.promien = promien;
    }

    @Override
    double obwod() {
        return 2 * Math.PI * promien;
    }

    @Override
    double pole() {
        return Math.PI * promien * promien;
    }

    @Override
    public String toString() {
        return "Figura nr " + numer + ": Koło o promienu " + promien + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kolo kolo = (Kolo) o;
        return promien == kolo.promien;
    }

    @Override
    public int hashCode() {
        return Objects.hash(promien);
    }
}



