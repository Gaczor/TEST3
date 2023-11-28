package z4;

import java.util.Objects;

public class Kwadrat extends Figura {
    private int bok;

    public Kwadrat(int bok) {
        this.bok = bok;
    }

    @Override
    double obwod() {
        return 4 * bok;
    }

    @Override
    double pole() {
        return bok * bok;
    }

    @Override
    public String toString() {
        return "Figura nr " + numer + ": Kwadrat o boku " + bok + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kwadrat kwadrat = (Kwadrat) o;
        return bok == kwadrat.bok;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bok);
    }
}
