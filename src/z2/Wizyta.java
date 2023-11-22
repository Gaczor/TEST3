package z2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Wizyta {
    private Lekarz lekarz;
    private Pacjent pacjent;
    private Date dataWizyty;

    public Wizyta(Lekarz lekarz, Pacjent pacjent, Date dataWizyty) {
        this.lekarz = lekarz;
        this.pacjent = pacjent;
        this.dataWizyty = dataWizyty;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Wizyta wizyta = (Wizyta) obj;
        return Objects.equals(lekarz, wizyta.lekarz) &&
                Objects.equals(pacjent, wizyta.pacjent) &&
                Objects.equals(dataWizyty, wizyta.dataWizyty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lekarz, pacjent, dataWizyty);
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public void setLekarz(Lekarz lekarz) {
        this.lekarz = lekarz;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }

    public Date getDataWizyty() {
        return dataWizyty;
    }

    public void setDataWizyty(Date dataWizyty) {
        this.dataWizyty = dataWizyty;
    }
}
