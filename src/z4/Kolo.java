package z4;

import java.util.Objects;

public class Kolo extends Figura {
    private double promien;

    public Kolo(double promien, boolean czyNadacNumer) {
        super(czyNadacNumer);
        this.promien = promien;
    }

    @Override
    public double obwod() {
        return 2 * Math.PI * promien;
    }

    @Override
    public double pole() {
        return Math.PI * promien * promien;
    }

    @Override
    protected String opis() {
        return "Koło o promieniu " + promien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kolo kolo = (Kolo) o;
        return Double.compare(kolo.promien, promien) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(promien);
    }
}
