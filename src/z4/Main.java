package z4;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Figura[] figury = {Figura.stworzKwadrat(10), Figura.stworzKolo(20), Figura.stworzProstokat(10, 20)};
        for (Figura f : figury) {
            System.out.println(f);
        }
        List<Figura> figuryPunktA = Arrays.asList(figury);
        System.out.println("Figura z największym obwodem: " + znajdzFigureZNajwiekszymObwodem(figury));
        System.out.println("Figura z największym polem: " + znajdzFigureZNajwiekszymPolem(figury));
        System.out.println(figuryPunktA.contains(new Kwadrat(10)));
    }


    private static Figura znajdzFigureZNajwiekszymObwodem(Figura[] figury) {
        Figura maksFigura = null;
        double maksObwod = 0;
        for (Figura f : figury) {
            if (f.obwod() > maksObwod) {
                maksObwod = f.obwod();
                maksFigura = f;
            }
        }
        return maksFigura;
    }

    private static Figura znajdzFigureZNajwiekszymPolem(Figura[] figury) {
        Figura maksFigura = null;
        double maksPole = 0;
        for (Figura f : figury) {
            if (f.pole() > maksPole) {
                maksPole = f.pole();
                maksFigura = f;
            }
        }
        return maksFigura;
    }
}
