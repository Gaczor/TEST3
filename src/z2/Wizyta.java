package z2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Wizyta {
    private int idLekarza;
    private int idPacjenta;
    private Date dataWizyty;

    public Wizyta(int idLekarza, int idPacjenta, String dataWizyty) throws ParseException {
        this.idLekarza = idLekarza;
        this.idPacjenta = idPacjenta;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.dataWizyty = sdf.parse(dataWizyty);
    }

    public int getIdLekarza() {
        return idLekarza;
    }

    public void setIdLekarza(int idLekarza) {
        this.idLekarza = idLekarza;
    }

    public int getIdPacjenta() {
        return idPacjenta;
    }

    public void setIdPacjenta(int idPacjenta) {
        this.idPacjenta = idPacjenta;
    }

    public Date getDataWizyty() {
        return dataWizyty;
    }

    public void setDataWizyty(Date dataWizyty) {
        this.dataWizyty = dataWizyty;
    }
}
