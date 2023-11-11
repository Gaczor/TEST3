package z3;

public class Student extends Osoba {
    private String grupa;
    private double stypendium;

    public Student(String imie, String nazwisko, String pesel, String miasto, String grupa, double stypendium) {
        super(imie, nazwisko, pesel, miasto);
        this.grupa = grupa;
        this.stypendium = stypendium;
    }

    @Override
    public double getDochod() {
        return stypendium;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public double getStypendium() {
        return stypendium;
    }

    public void setStypendium(double stypendium) {
        this.stypendium = stypendium;
    }
}
