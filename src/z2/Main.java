package z2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        List<Lekarz> lekarze = wczytajLekarzy("src/z2/lekarze.txt");
        List<Pacjent> pacjenci = wczytajPacjentow("src/z2/pacjenci.txt");
        List<Wizyta> wizyty = wczytajWizyty("src/z2/wizyty.txt", lekarze, pacjenci);

        Map<Integer, Integer> liczbaWizytLekarzy = new HashMap<>();
        Map<Integer, Integer> liczbaWizytPacjentow = new HashMap<>();

        for (Wizyta wizyta : wizyty) {
            int idLekarza = wizyta.getIdLekarza();
            liczbaWizytLekarzy.put(idLekarza, liczbaWizytLekarzy.getOrDefault(idLekarza, 0) + 1);
        }

        for (Wizyta wizyta : wizyty) {
            int idPacjenta = wizyta.getIdPacjenta();
            liczbaWizytPacjentow.put(idPacjenta, liczbaWizytPacjentow.getOrDefault(idPacjenta, 0) + 1);
        }

        System.out.println("Najczęściej odwiedzający lekarz: " + znajdzLekarza(lekarze, znajdzNajczestszego(liczbaWizytLekarzy)));
        System.out.println("Najczęściej odwiedzający pacjent: " + znajdzPacjenta(pacjenci, znajdzNajczestszego(liczbaWizytPacjentow)));
        System.out.println("Najpopularniejsza specjalizacja: " + znajdzNajpopularniejszaSpecjalizacje(lekarze, wizyty));
        znajdzNajwiecejWizytWRoku(wizyty);
        wypiszTop5NajstarszychLekarzy(lekarze);
        wypiszTop5LekarzyZaNajwiecejWizyt(lekarze, wizyty);
        znajdzPacjentowZCoNajmniej5Lekarzami(wizyty, pacjenci); //nikt nie odwiedził 5 lekarzy, najwieksza ilosc odwiedzonych lekarzy to 3 przez jedną osobę
        znajdzLekarzyZJednymPacjentem(wizyty, lekarze);
    }


    private static List<Lekarz> wczytajLekarzy(String fileName) {
        List<Lekarz> lekarze = new ArrayList<>();
        Set<String> unikalniLekarze = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Id_lekarza")) {
                    continue;
                }

                String[] parts = line.split("[\\t\\s]+");
                int idLekarza = Integer.parseInt(parts[0]);
                String nazwisko = parts[1];
                String imie = parts[2];
                String specjalnosc = parts[3];
                String dataUrodzenia = parts[4];
                String nip = parts[5];
                String pesel = parts[6];

                if (unikalniLekarze.add(nip)) {
                    lekarze.add(new Lekarz(idLekarza, nazwisko, imie, specjalnosc, dataUrodzenia, nip, pesel));
                }
            }
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
        return lekarze;
    }

    private static List<Pacjent> wczytajPacjentow(String fileName) {
        List<Pacjent> pacjenci = new ArrayList<>();
        Set<String> unikalniPacjenci = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Id_pacjenta")) {
                    continue;
                }
                String[] parts = line.split("[\\t\\s]+");
                int idPacjenta = Integer.parseInt(parts[0]);
                String nazwisko = parts[1];
                String imie = parts[2];
                String pesel = parts[3];
                String dataUrodzenia = parts[4];

                if (unikalniPacjenci.add(pesel)) {
                    pacjenci.add(new Pacjent(idPacjenta, nazwisko, imie, pesel, dataUrodzenia));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return pacjenci;
    }

    private static List<Wizyta> wczytajWizyty(String fileName, List<Lekarz> lekarze, List<Pacjent> pacjenci) {
        List<Wizyta> wizyty = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Id_lekarza")) {
                    continue;
                }

                String[] parts = line.split("[\\t\\s]+");
                int idLekarza = Integer.parseInt(parts[0]);
                int idPacjenta = Integer.parseInt(parts[1]);
                String dataWizyty = parts[2];

                if (czyIdentyfikatoryIstnieja(idLekarza, idPacjenta, lekarze, pacjenci)) {
                    wizyty.add(new Wizyta(idLekarza, idPacjenta, dataWizyty));
                } else {
                    throw new IllegalArgumentException("Błąd w pliku wizyty.txt: Nieprawidłowe id lekarza lub pacjenta.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return wizyty;
    }

    private static int znajdzNajczestszego(Map<Integer, Integer> mapa) {
        int idNajczestszy = -1;
        int maxLiczbaWizyt = -1;

        for (Map.Entry<Integer, Integer> entry : mapa.entrySet()) {
            if (entry.getValue() > maxLiczbaWizyt) {
                maxLiczbaWizyt = entry.getValue();
                idNajczestszy = entry.getKey();
            }
        }

        return idNajczestszy;
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

    private static boolean czyIdentyfikatoryIstnieja(int idLekarza, int idPacjenta, List<Lekarz> lekarze, List<Pacjent> pacjenci) {
        for (Lekarz lekarz : lekarze) {
            if (lekarz.getIdLekarza() == idLekarza) {
                for (Pacjent pacjent : pacjenci) {
                    if (pacjent.getIdPacjenta() == idPacjenta) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String znajdzNajpopularniejszaSpecjalizacje(List<Lekarz> lekarze, List<Wizyta> wizyty) {
        Map<String, Integer> licznikSpecjalizacji = new HashMap<>();

        for (Lekarz lekarz : lekarze) {
            licznikSpecjalizacji.put(lekarz.getSpecjalnosc(), 0);
        }

        for (Wizyta wizyta : wizyty) {
            int idLekarza = wizyta.getIdLekarza();
            for (Lekarz lekarz : lekarze) {
                if (lekarz.getIdLekarza() == idLekarza) {
                    String specjalizacja = lekarz.getSpecjalnosc();
                    licznikSpecjalizacji.put(specjalizacja, licznikSpecjalizacji.get(specjalizacja) + 1);
                    break;
                }
            }
        }

        String najpopularniejszaSpecjalizacja = null;
        int maxLiczbaWizyt = 0;

        for (Map.Entry<String, Integer> entry : licznikSpecjalizacji.entrySet()) {
            if (entry.getValue() > maxLiczbaWizyt) {
                najpopularniejszaSpecjalizacja = entry.getKey();
                maxLiczbaWizyt = entry.getValue();
            }
        }
        return najpopularniejszaSpecjalizacja + " - liczba wizyt: " + maxLiczbaWizyt;
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

    public static void wypiszTop5LekarzyZaNajwiecejWizyt(List<Lekarz> lekarze, List<Wizyta> wizyty) {
        Map<Integer, Integer> licznikWizytLekarzy = new HashMap<>();

        for (Wizyta wizyta : wizyty) {
            int idLekarza = wizyta.getIdLekarza();
            licznikWizytLekarzy.put(idLekarza, licznikWizytLekarzy.getOrDefault(idLekarza, 0) + 1);
        }

        List<Lekarz> top5NajwiecejWizyt = new ArrayList<>(5);

        for (int i = 0; i < Math.min(5, lekarze.size()); i++) {
            Lekarz najwiecejWizyt = null;
            for (Lekarz lekarz : lekarze) {
                if (!top5NajwiecejWizyt.contains(lekarz) && (najwiecejWizyt == null || licznikWizytLekarzy.getOrDefault(lekarz.getIdLekarza(), 0) > licznikWizytLekarzy.getOrDefault(najwiecejWizyt.getIdLekarza(), 0))) {
                    najwiecejWizyt = lekarz;
                }
            }
            top5NajwiecejWizyt.add(najwiecejWizyt);
        }

        System.out.println("\nTop 5 lekarzy z największą liczbą wizyt:");
        for (Lekarz lekarz : top5NajwiecejWizyt) {
            System.out.println(lekarz + " - liczba wizyt: " + licznikWizytLekarzy.getOrDefault(lekarz.getIdLekarza(), 0));
        }
    }

    public static void znajdzPacjentowZCoNajmniej5Lekarzami(List<Wizyta> wizyty, List<Pacjent> pacjenci) {
        Map<Integer, Set<Integer>> pacjenciDoLekarzy = new HashMap<>();
        for (Wizyta wizyta : wizyty) {
            pacjenciDoLekarzy.computeIfAbsent(wizyta.getIdPacjenta(), k -> new HashSet<>()).add(wizyta.getIdLekarza());
        }

        Map<Integer, String> idDoNazwiskaPacjenta = new HashMap<>();
        for (Pacjent pacjent : pacjenci) {
            idDoNazwiskaPacjenta.put(pacjent.getIdPacjenta(), pacjent.getImie() + " " + pacjent.getNazwisko());
        }
        boolean znaleziono = false;
        for (Map.Entry<Integer, Set<Integer>> entry : pacjenciDoLekarzy.entrySet()) {
            if (entry.getValue().size() >= 5) {
                System.out.println("\nPacjent " + idDoNazwiskaPacjenta.get(entry.getKey()) + " odwiedził co najmniej 5 różnych lekarzy");
                znaleziono = true;
            }
        }
        if (!znaleziono) {
            System.out.println("\nNie znaleziono pacjenta, który odwiedził co najmniej 5 różnych lekarzy");
        }
    }

    public static void znajdzLekarzyZJednymPacjentem(List<Wizyta> wizyty, List<Lekarz> lekarze) {
        Map<Integer, Set<Integer>> lekarzeDoPacjentow = new HashMap<>();
        for (Wizyta wizyta : wizyty) {
            lekarzeDoPacjentow.computeIfAbsent(wizyta.getIdLekarza(), k -> new HashSet<>()).add(wizyta.getIdPacjenta());
        }

        Map<Integer, String> idDoNazwiskaLekarza = new HashMap<>();
        for (Lekarz lekarz : lekarze) {
            idDoNazwiskaLekarza.put(lekarz.getIdLekarza(), lekarz.getImie() + " " + lekarz.getNazwisko());
        }
        boolean znaleziono = false;
        for (Map.Entry<Integer, Set<Integer>> entry : lekarzeDoPacjentow.entrySet()) {
            if (entry.getValue().size() == 1) {
                System.out.println("\nLekarz " + idDoNazwiskaLekarza.get(entry.getKey()) + " przyjął tylko jednego pacjenta");
                znaleziono = true;
            }
        }
        if (!znaleziono) {
            System.out.println("\nNie znaleziono lekarza, który przyjął tylko 1 pacjenta");
        }
    }
}


