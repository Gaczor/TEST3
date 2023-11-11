package z1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("src/z1/mamy.txt");
        BufferedReader br = new BufferedReader(fr);
        String linia;
        while ((linia = br.readLine()) != null) {
            String[] dane = linia.split(" ");
            Mama.stworzMame(dane);
        }

        FileReader fr2 = new FileReader("src/z1/nowordki.txt");
        BufferedReader br2 = new BufferedReader(fr2);
        while ((linia = br2.readLine()) != null) {
            String[] dane = linia.split(" ");
            Noworodek.stworzNoworodka(dane);
        }

        System.out.println(Noworodek.zwrocNajwyzszeDzieci());
        System.out.println("Najwięcej dzieci urodziło się dnia: " + Noworodek.zwrocMaxUrodzen(Noworodek.getListaDzieci()));
        System.out.println("Imiona kobiet w wieku poniżej 25 lat, które urodziły dzieci o wadze powyżej 4000 g: " + Mama.zwrocMame25LatIDziecko4000());
        System.out.println("Imiona i daty urodzenia dziewczynek, które odziedziczyły imię po matce: " + Noworodek.zwrocDziewczynkiZImieniemMatki(Noworodek.getListaDzieci()));
        System.out.println("Matki bliźniaków to: " + Noworodek.zwrocMatkiBlizniakow());
    }
}
