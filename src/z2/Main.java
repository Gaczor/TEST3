package z2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            List<Lekarz> lekarze = wczytajLekarzy("src/z2/lekarze.txt");
            List<Pacjent> pacjenci = wczytajPacjentow("src/z2/pacjenci.txt");
            List<Wizyta> wizyty = wczytajWizyty("src/z2/wizyty.txt", lekarze, pacjenci);

            if (znajdzLekarzaZNajwiekszaLiczbaWizyt(lekarze) != null) {
                System.out.println("Lekarz z największą liczbą wizyt: " + znajdzLekarzaZNajwiekszaLiczbaWizyt(lekarze).toString());
            }
            if (znajdzPacjentaZNajwiekszaLiczbaWizyt(pacjenci) != null) {
                System.out.println("Pacjent z największą liczbą wizyt: " + znajdzPacjentaZNajwiekszaLiczbaWizyt(pacjenci).toString());
            }

            System.out.println("Najpopularniejsza specjalizacje: " + znajdzNajpopularniejszaSpecjalizacje(lekarze));
            znajdzNajwiecejWizytWRoku(wizyty);
            wypiszTop5NajstarszychLekarzy(lekarze);
            wypiszTop5LekarzyZNajwiecejWizyt(lekarze);
            znajdzPacjentowZCoNajmniej5Lekarzami(pacjenci);
            znajdzLekarzyZJednymPacjentem(lekarze);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static List<Lekarz> wczytajLekarzy(String nazwaPliku) throws IOException, ParseException {
        List<Lekarz> lekarze = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nazwaPliku))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                if (linia.startsWith("Id_lekarza")) {
                    continue;
                }
                String[] elementy = linia.split("\t");

                if (elementy.length == 7) {
                    Lekarz lekarz = new Lekarz(
                            Integer.parseInt(elementy[0]), // idLekarza
                            elementy[1], // nazwisko
                            elementy[2], // imie
                            elementy[3], // specjalnosc
                            new SimpleDateFormat("yyyy-MM-dd").parse(elementy[4]), // dataUrodzenia
                            elementy[5], // nip
                            elementy[6], // pesel
                            new ArrayList<>() // listaWizyt
                    );
                    lekarze.add(lekarz);
                } else {
                    System.out.println("Błąd: Nieprawidłowa liczba elementów w linii: " + linia);
                }
            }
        }
        return lekarze;
    }


    public static List<Pacjent> wczytajPacjentow(String sciezkaPliku) throws IOException, ParseException {
        List<Pacjent> pacjenci = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(sciezkaPliku))) {
            String linia;

            while ((linia = reader.readLine()) != null) {
                if (linia.startsWith("Id_pacjenta")) {
                    continue;
                }
                String[] dane = linia.split("\t");

                if (dane.length == 5) {
                    Pacjent pacjent = new Pacjent(
                            Integer.parseInt(dane[0]), // id
                            dane[1], // nazwisko
                            dane[2], // imie
                            dane[3], // adres
                            new SimpleDateFormat("yyyy-MM-dd").parse(dane[4]) // data urodzenia
                    );
                    pacjenci.add(pacjent);
                } else {
                    System.out.println("Błąd: Nieprawidłowa liczba elementów w linii: " + linia);
                }
            }
            return pacjenci;
        }
    }


    public static List<Wizyta> wczytajWizyty(String sciezkaPliku, List<Lekarz> lekarze, List<Pacjent> pacjenci) throws IOException, ParseException {
        List<Wizyta> wizyty = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(sciezkaPliku))) {
            String linia;

            while ((linia = reader.readLine()) != null) {
                if (linia.startsWith("Id_lekarza")) {
                    continue;
                }
                String[] dane = linia.split("\t");

                if (dane.length == 3) {
                    int idLekarza = Integer.parseInt(dane[0]);
                    int idPacjenta = Integer.parseInt(dane[1]);
                    Date dataWizyty = new SimpleDateFormat("yyyy-MM-dd").parse(dane[2]);

                    Lekarz lekarz = znajdzLekarza(lekarze, idLekarza);
                    Pacjent pacjent = znajdzPacjenta(pacjenci, idPacjenta);
                    if (lekarz != null && pacjent != null) {
                        Wizyta wizyta = new Wizyta(lekarz, pacjent, dataWizyty);
                        wizyty.add(wizyta);
                        lekarz.dodajWizyte(wizyta);
                        pacjent.dodajWizytePacjenta(wizyta);
                    }
                } else {
                    System.err.println("Błąd: Nieprawidłowa liczba elementów w linii: " + linia);
                }
            }
        }
        return wizyty;
    }


    private static Lekarz znajdzLekarza(List<Lekarz> lekarze, int idLekarza) {
        for (Lekarz lekarz : lekarze) {
            if (lekarz.getIdLekarza() == idLekarza) {
                return lekarz;
            }
        }
        return null;
    }

    private static Pacjent znajdzPacjenta(List<Pacjent> pacjenci, int idPacjenta) {
        for (Pacjent pacjent : pacjenci) {
            if (pacjent.getIdPacjenta() == idPacjenta) {
                return pacjent;
            }
        }
        return null;
    }

    public static Lekarz znajdzLekarzaZNajwiekszaLiczbaWizyt(List<Lekarz> lekarze) {
        Lekarz lekarzZNajwiekszaLiczbaWizyt = null;
        int maxLiczbaWizyt = 0;

        for (Lekarz lekarz : lekarze) {
            int liczbaWizyt = lekarz.getListaWizyt().size();
            if (liczbaWizyt > maxLiczbaWizyt) {
                maxLiczbaWizyt = liczbaWizyt;
                lekarzZNajwiekszaLiczbaWizyt = lekarz;
            }
        }

        return lekarzZNajwiekszaLiczbaWizyt;
    }

    public static Pacjent znajdzPacjentaZNajwiekszaLiczbaWizyt(List<Pacjent> pacjenci) {
        Pacjent pacjentZNajwiekszaLiczbaWizyt = null;
        int maxLiczbaWizyt = 0;

        for (Pacjent pacjent : pacjenci) {
            int liczbaWizyt = pacjent.getListaWizytPacjenta().size();
            if (liczbaWizyt > maxLiczbaWizyt) {
                maxLiczbaWizyt = liczbaWizyt;
                pacjentZNajwiekszaLiczbaWizyt = pacjent;
            }
        }

        return pacjentZNajwiekszaLiczbaWizyt;
    }

    public static List<String> znajdzNajpopularniejszaSpecjalizacje(List<Lekarz> lekarze) {
        Map<String, Integer> specjalizacjeLicznik = new HashMap<>();

        for (Lekarz lekarz : lekarze) {
            String specjalizacja = lekarz.getSpecjalnosc();
            specjalizacjeLicznik.put(specjalizacja, specjalizacjeLicznik.getOrDefault(specjalizacja, 0) + 1);
        }

        int maxLiczbaWizyt = 0;
        List<String> najlepszeSpecjalizacje = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : specjalizacjeLicznik.entrySet()) {
            int liczbaWizyt = entry.getValue();
            if (liczbaWizyt > maxLiczbaWizyt) {
                maxLiczbaWizyt = liczbaWizyt;
                najlepszeSpecjalizacje.clear();
                najlepszeSpecjalizacje.add(entry.getKey());
            } else if (liczbaWizyt == maxLiczbaWizyt) {
                najlepszeSpecjalizacje.add(entry.getKey());
            }
        }

        return najlepszeSpecjalizacje;
    }

    private static void znajdzNajwiecejWizytWRoku(List<Wizyta> wizyty) {
        Map<Integer, Integer> wizytyPerRok = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

        for (Wizyta wizyta : wizyty) {
            int rok = Integer.parseInt(sdf.format(wizyta.getDataWizyty()));
            if (!wizytyPerRok.containsKey(rok)) {
                wizytyPerRok.put(rok, 1);
            } else {
                wizytyPerRok.put(rok, wizytyPerRok.get(rok) + 1);
            }
        }
        int rokZNajwiecejWizytami = 0;
        int maxWizyt = 0;
        for (Map.Entry<Integer, Integer> entry : wizytyPerRok.entrySet()) {
            if (entry.getValue() > maxWizyt) {
                maxWizyt = entry.getValue();
                rokZNajwiecejWizytami = entry.getKey();
            }
        }

        if (rokZNajwiecejWizytami > 0) {
            System.out.println("Rok z największą liczbą wizyt: " + rokZNajwiecejWizytami + ", liczba wizyt: " + maxWizyt);
        } else {
            System.out.println("Brak danych o wizytach.");
        }
    }

    public static void wypiszTop5NajstarszychLekarzy(List<Lekarz> lekarze) {
        List<Lekarz> top5Najstarszych = new ArrayList<>(5);
        for (int i = 0; i < Math.min(5, lekarze.size()); i++) {
            Lekarz najstarszy = null;
            for (Lekarz lekarz : lekarze) {
                if (!top5Najstarszych.contains(lekarz) && (najstarszy == null || lekarz.getDataUrodzenia().compareTo(najstarszy.getDataUrodzenia()) < 0)) {
                    najstarszy = lekarz;
                }
            }
            top5Najstarszych.add(najstarszy);
        }
        System.out.println("\nTop 5 najstarszych lekarzy:");
        for (Lekarz lekarz : top5Najstarszych) {
            System.out.println(lekarz);
        }
    }


    public static void wypiszTop5LekarzyZNajwiecejWizyt(List<Lekarz> lekarze) {
        Map<Lekarz, Integer> licznikWizytLekarzy = new HashMap<>();
        for (Lekarz lekarz : lekarze) {
            licznikWizytLekarzy.put(lekarz, lekarz.getListaWizyt().size());
        }
        List<Lekarz> top5NajwiecejWizyt = new ArrayList<>();
        for (int i = 0; i < Math.min(5, lekarze.size()); i++) {
            Lekarz lekarzZNajwiecejWizytami = null;
            for (Lekarz lekarz : lekarze) {
                if (!top5NajwiecejWizyt.contains(lekarz)) {
                    if (lekarzZNajwiecejWizytami == null || licznikWizytLekarzy.get(lekarz) > licznikWizytLekarzy.get(lekarzZNajwiecejWizytami)) {
                        lekarzZNajwiecejWizytami = lekarz;
                    }
                }
            }
            if (lekarzZNajwiecejWizytami != null) {
                top5NajwiecejWizyt.add(lekarzZNajwiecejWizytami);
            }
        }
        System.out.println("\nTop 5 lekarzy z największą liczbą wizyt:");
        for (Lekarz lekarz : top5NajwiecejWizyt) {
            System.out.println(lekarz.getNazwisko() + " " + lekarz.getImie() + " - liczba wizyt: " + licznikWizytLekarzy.get(lekarz));
        }
    }

    public static void znajdzPacjentowZCoNajmniej5Lekarzami(List<Pacjent> pacjenci) {
        Map<Pacjent, Set<Integer>> pacjenciDoLekarzy = new HashMap<>();

        for (Pacjent pacjent : pacjenci) {
            Set<Integer> idLekarzy = new HashSet<>();
            for (Wizyta wizyta : pacjent.getListaWizytPacjenta()) {
                idLekarzy.add(wizyta.getLekarz().getIdLekarza());
            }
            pacjenciDoLekarzy.put(pacjent, idLekarzy);
        }

        boolean znaleziono = false;
        for (Map.Entry<Pacjent, Set<Integer>> entry : pacjenciDoLekarzy.entrySet()) {
            if (entry.getValue().size() >= 5) {
                Pacjent pacjent = entry.getKey();
                System.out.println("\nPacjent " + pacjent.getImie() + " " + pacjent.getNazwisko() + " odwiedził co najmniej 5 różnych lekarzy");
                znaleziono = true;
            }
        }

        if (!znaleziono) {
            System.out.println("\nNie znaleziono pacjenta, który odwiedził co najmniej 5 różnych lekarzy");
        }
    }

    public static void znajdzLekarzyZJednymPacjentem(List<Lekarz> lekarze) {
        Map<Lekarz, Set<Integer>> lekarzeDoPacjentow = new HashMap<>();

        for (Lekarz lekarz : lekarze) {
            Set<Integer> idPacjentow = new HashSet<>();
            for (Wizyta wizyta : lekarz.getListaWizyt()) {
                idPacjentow.add(wizyta.getPacjent().getIdPacjenta());
            }
            lekarzeDoPacjentow.put(lekarz, idPacjentow);
        }

        boolean znaleziono = false;
        for (Map.Entry<Lekarz, Set<Integer>> entry : lekarzeDoPacjentow.entrySet()) {
            if (entry.getValue().size() == 1) {
                Lekarz lekarz = entry.getKey();
                System.out.println("\nLekarz " + lekarz.getImie() + " " + lekarz.getNazwisko() + " przyjął tylko jednego pacjenta");
                znaleziono = true;
            }
        }

        if (!znaleziono) {
            System.out.println("\nNie znaleziono lekarza, który przyjął tylko 1 pacjenta");
        }
    }


}


