package z1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Noworodek {
    private String idDziecka;
    private String plec;
    private String imie;
    private LocalDate dataUrodzenia;
    private int waga;
    private int wzrost;
    private Mama mama;
    private static List<Noworodek> listaDzieci = new ArrayList<>();

    public Noworodek(String idDziecka, String plec, String imie, LocalDate dataUrodzenia, int waga, int wzrost, String idMamy) {
        this.idDziecka = idDziecka;
        this.plec = plec;
        this.imie = imie;
        this.dataUrodzenia = dataUrodzenia;
        this.waga = waga;
        this.wzrost = wzrost;
        this.mama = przypiszMame(idMamy);
        listaDzieci.add(this);
    }
    public static Noworodek stworzNoworodka (String[] tab){
        return new Noworodek(tab[0],tab[1], tab[2],LocalDate.parse(tab[3]), Integer.parseInt(tab[4]), Integer.parseInt(tab[5]), tab[6]);
    }
    private Mama przypiszMame(String idMamy) {
        Mama mama = Mama.znajdzMame(idMamy);
        mama.getListaDzieci().add(this);
        return mama;
    }
    public static String zwrocNajwyzszeDzieci() {
        Noworodek najwyzszy = null;
        Noworodek najwyzsza = null;
        for (Noworodek noworodek : listaDzieci) {
            if (noworodek.plec.equals("s")) {
                if (najwyzszy == null || noworodek.wzrost > najwyzszy.wzrost) {
                    najwyzszy = noworodek;
                }
            } else if (noworodek.plec.equals("c")) {
                if (najwyzsza == null || noworodek.wzrost > najwyzsza.wzrost) {
                    najwyzsza = noworodek;
                }
            }
        }
        String wynik = "";
        if (najwyzszy != null ) {
            wynik += "Najwyzszy chlopiec to: " + najwyzszy.imie + ", Wzrost: " + najwyzszy.wzrost + "\n";
        } else {
            wynik += "Nie znaleziono chlopca\n";
        }
        if (najwyzsza != null) {
            wynik += "Najwyzsza dziewczynka to: " + najwyzsza.imie + ", Wzrost: " + najwyzsza.wzrost;
        } else {
            wynik += "Nie znaleziono dziewczynki";
        } return wynik;
    }
    public static Map.Entry<String, Integer> zwrocMaxUrodzen(List<Noworodek> noworodki) {
        Map<String, List<Noworodek>> urodzieniDnia = new HashMap<>();
        Map<String, Integer> urodzieniDniaLicznik = new HashMap<>();

        for (Noworodek noworodek : noworodki) {
            String data = noworodek.dataUrodzenia.toString();
            urodzieniDnia.putIfAbsent(data, new ArrayList<>());
            urodzieniDnia.get(data).add(noworodek);
            urodzieniDniaLicznik.put(data, urodzieniDnia.get(data).size());
        }
        Map.Entry<String, Integer> MaxUrodzonych = null;
        for (Map.Entry<String, Integer> entry : urodzieniDniaLicznik.entrySet()) {
            if (MaxUrodzonych == null || entry.getValue() > MaxUrodzonych.getValue()) {
                MaxUrodzonych = entry;
            }
        }
        return MaxUrodzonych;
    }

    public static List<String> zwrocDziewczynkiZImieniemMatki(List<Noworodek> listaDzieci) {
        List<String> dziewczynkiZImieniemMatki = new ArrayList<>();

        for (Noworodek noworodek : listaDzieci) {
            if (noworodek.plec.equals("c") && noworodek.imie.equals(noworodek.mama.getImie())) {
                dziewczynkiZImieniemMatki.add(noworodek.imie + " - " + noworodek.dataUrodzenia);
            }
        }
        return dziewczynkiZImieniemMatki;
    }

    public static List<String> zwrocMatkiBlizniakow() {
        Map<String, List<Noworodek>> datyPrzypuszczalnychBlizniakow = new HashMap<>();
        List<String> matkiBlizniakow = new ArrayList<>();

        for (Noworodek noworodek : listaDzieci) {
            String klucz = noworodek.mama.getIdMamy() + noworodek.dataUrodzenia.toString();
            datyPrzypuszczalnychBlizniakow.putIfAbsent(klucz, new ArrayList<>());
            datyPrzypuszczalnychBlizniakow.get(klucz).add(noworodek);
        }
        for (Map.Entry<String, List<Noworodek>> entry : datyPrzypuszczalnychBlizniakow.entrySet()) {
            if (entry.getValue().size() > 1) {
                Noworodek pierwszyBlizniak = entry.getValue().get(0);
                Mama mama = pierwszyBlizniak.getMama();
                matkiBlizniakow.add(mama.getImie() + " - wiek: " + mama.getWiek());
            }
        }
        return matkiBlizniakow;
    }

    public String getIdDziecka() {
        return idDziecka;
    }

    public void setIdDziecka(String idDziecka) {
        this.idDziecka = idDziecka;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public int getWaga() {
        return waga;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }

    public int getWzrost() {
        return wzrost;
    }

    public void setWzrost(int wzrost) {
        this.wzrost = wzrost;
    }

    public Mama getMama() {
        return mama;
    }

    public void setMama(Mama mama) {
        this.mama = mama;
    }

    public static List<Noworodek> getListaDzieci() {
        return listaDzieci;
    }

    public static void setListaDzieci(List<Noworodek> listaDzieci) {
        Noworodek.listaDzieci = listaDzieci;
    }

    @Override
    public String toString() {
        return "Noworodek{" +
                "idDziecka='" + idDziecka + '\'' +
                ", plec='" + plec + '\'' +
                ", imie='" + imie + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", waga=" + waga +
                ", wzrost=" + wzrost +
                ", mama=" + mama +
                '}';
    }
}
