package z1;

import java.util.ArrayList;
import java.util.List;

public class Mama {
    private String idMamy;
    private String imie;
    private int wiek;
    private static List<Mama> listaMam = new ArrayList<>();
    private List<Noworodek> listaDzieci = new ArrayList<>();

    public Mama(String idMamy, String imie, int wiek) {
        this.idMamy = idMamy;
        this.imie = imie;
        this.wiek = wiek;
        listaMam.add(this);
    }

    public static Mama stworzMame(String[] tab) {
        return new Mama(tab[0], tab[1], Integer.parseInt(tab[2]));
    }

    public static Mama znajdzMame(String id) {
        for (Mama mama : listaMam) {
            if (mama.idMamy.equals(id)) {
                return mama;
            }
        }
        throw new IllegalArgumentException("brak mamy o podanym id");
    }

    public static List<String> zwrocMame25LatIDziecko4000() {
        List<String> wynik = new ArrayList<>();
        for (Mama mama : listaMam) {
            if (mama.wiek < 25) {
                for (Noworodek noworodek : mama.listaDzieci) {
                    if (noworodek.getWaga() > 4000) {
                        wynik.add(mama.imie);
                        break;
                    }
                }
            }
        }
        return wynik;
    }

    public String getIdMamy() {
        return idMamy;
    }

    public String getImie() {
        return imie;
    }

    public int getWiek() {
        return wiek;
    }

    public void setIdMamy(String idMamy) {
        this.idMamy = idMamy;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public List<Noworodek> getListaDzieci() {
        return listaDzieci;
    }

    @Override
    public String toString() {
        return "Mama{" +
                "idMamy='" + idMamy + '\'' +
                ", imie='" + imie + '\'' +
                ", wiek=" + wiek +
                '}';
    }
}