package z4;

import java.util.Objects;

public class Kwadrat extends Figura {
    private double bok;

    public Kwadrat(double bok) {
        this(bok, false);
    }

    public Kwadrat(double bok, boolean czyNadacNumer) {
        super(czyNadacNumer);
        this.bok = bok;
    }

    @Override
    public double obwod() {
        return 4 * bok;
    }

    @Override
    public double pole() {
        return bok * bok;
    }

    @Override
    protected String opis() {
        return "Kwadrat o boku " + bok + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kwadrat kwadrat = (Kwadrat) o;
        return Double.compare(kwadrat.bok, bok) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bok);
    }
}

