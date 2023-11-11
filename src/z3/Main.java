package z3;

public class Main {
    public static void main(String[] args) {
        Osoba[] osoby = {
                new Student("Anna", "Kowalska", "99010212345", "Warszawa", "II rok", 1500.0),
                new Pracownik("Jan", "Nowak", "88020112255", "Kraków", "Inżynier", 4500.0),
                new Student("Paweł", "Kowalska", "99010212435", "Poznań", "III rok", 2300.0),
                new Pracownik("Maria", "Nowak", "88020112345", "Szczecin", "Informatyk", 6000.0),
                new Student("Michalina", "Kowalska", "99010212345", "Gdansk", "I rok", 900.0),
                new Pracownik("Adrian", "Nowak", "88020112655", "Sopot", "Prezes", 8900.0)
        };

        System.out.println("Osoba z największym dochodem: " + znajdzOsobeZNajwiekszymDochodem(osoby));
        System.out.println("Liczba kobiet: " + policzKobiety(osoby));


    }

    private static Osoba znajdzOsobeZNajwiekszymDochodem(Osoba[] osoby) {
        Osoba osobaZNajwiekszymDochodem = null;
        double maxDochod = 0;

        for (Osoba osoba : osoby) {
            if (osoba.getDochod() > maxDochod) {
                maxDochod = osoba.getDochod();
                osobaZNajwiekszymDochodem = osoba;
            }
        }

        return osobaZNajwiekszymDochodem;
    }

    private static int policzKobiety(Osoba[] osoby) {
        int liczbaKobiet = 0;
        for (Osoba osoba : osoby) {
            if (osoba.getPlec() == 'K') {
                liczbaKobiet++;
            }
        }
        return liczbaKobiet;
    }
}
