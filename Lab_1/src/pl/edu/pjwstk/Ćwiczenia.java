package pl.edu.pjwstk;

import jdk.dynalink.linker.ConversionComparator;
import java.nio.channels.SelectionKey;
import java.util.Scanner;

public class Ćwiczenia {
    public static void main(String[] args) {
//        Zadanie 1:
//        Napisz program który wyświetli wynik następującego działania:
        /*
        double wynik = ((9.5*4.5)-(2.5*3))/(45.5-3.5);
        System.out.println(wynik);
        */
//        Zadanie 2:
//        Napisz program, który przekonwertuje podane przez użytkownika stopnie Celsjusza na
//        Fahrenheity według wzoru:
//        fahrenheit = (9 / 5) * celsius + 32
//        43 stopnie Celsjusza to 109,4 F.
        /*
        Scanner skaner = new Scanner(System.in);
        System.out.println("Podaj temperature w celcjuszach ");
            double celcjusz = skaner.nextDouble();
            double fahrenheit = ((9.0/5)*celcjusz+32);
        System.out.println(String.format("Twoja temperatura w fahrenheit'ach %s F",fahrenheit));
        */
//        Zadanie 3:
//        Napisz program, który za pomocą pętli wydrukuje tabelkę (od 1 do 10):
//        Miles Kilometers
//        1 1.609
//        2 3.218
//...
//        9 14.481
//        10 16.090
        /*
        System.out.println("Miles\t\tKilometers");
        for(int i=1;i<=10;i++){
            System.out.println(String.format("%s\t\t\t%s",i,i*1.609));
        }
         */
//        Zadanie 4:
//        Napisz program, w którym użytkownik poda liczbę studentów. Następnie po kolei
//        użytkownik podaje imię każdego studenta i liczbę punktów. Na koniec wyświetl imię i punkty
//        najlepszego studenta. (uwaga: nie musisz przechowywać informacji o każdym studencie z
//        osobna!)
        /*
        int i=1;
        Scanner skaner = new Scanner(System.in);
        System.out.print("Podaj liczbe studenciakow --> ");
        int iloscStudentow = skaner.nextInt();
        System.out.print(String.format("%s.  Podaj imie studenciaka ",i));
        String imieStudenta = skaner.next();
        System.out.print("\tTeraz podaj liczbę punktów ");                              //NIEDOKOŃCZONE !!!!
        int iloscPunktow = skaner.nextInt();
        for( i=2;i<=iloscStudentow;i++){
            System.out.print(String.format("%s.  Podaj imie studenciaka ",i));
            String imieStudentaNEW = skaner.next();
            System.out.print("\tTeraz podaj liczbę punktów ");
            int iloscPunktowNEW = skaner.nextInt();
         }
        */
//        Zadanie 5:
//        Napisz program, który wczyta od użytkownika trzy boki trójkąta i sprawdzi, czy trójkąt jest
//        prawidłowy. Wydrukuj „tak” lub „nie”.
        /*
        Scanner skaner = new Scanner(System.in);
        int a = skaner.nextInt();
        int b = skaner.nextInt();
        int c = skaner.nextInt();
        if ((a+b>c)&&(a+c>b)&&(b+c>a)){
            System.out.println("tak");
        }
        else {
            System.out.println("nie");
        }
        */
//        Zadanie 6:
//        Napisz program, który pobierze od użytkownika liczbę od 1 do 7 i wypisze, który to dzień
//        tygodnia. Poniedziałek to 1, niedziela to 7.
        /*
        Scanner skaner = new Scanner(System.in);
        System.out.print("Podaj liczbę od 1 do 7 --> ");
        int liczba = skaner.nextInt();
        switch (liczba){
            case 1:{
                System.out.println("Poniedziałek");
                break;
            }
            case 2:{
                System.out.println("Wtorek");
                break;
            }
            case 3:{
                System.out.println("Środa");
                break;
            }
            case 4:{
                System.out.println("Czwartek");
                break;
            }
            case 5:{
                System.out.println("Piątek");
                break;
            }
            case 6:{
                System.out.println("Sobota");
                break;
            }
            case 7:{
                System.out.println("Niedziela");
                break;
            }
            default:{
                System.out.println("Cos poszło nie tak :(");
                break;
            }
        }
             */
//        Zadanie 7:
//        Napisz program, który pobierze od użytkownika po kolei dwie litery, a następnie wypisze
//        która z nich jest później w alfabecie. Weź pod uwagę sytuację dwóch tych samych liter.
        /*
        Scanner skaner = new Scanner(System.in);
        System.out.println("Podaj literkę");
        char pierwsza = skaner.next().charAt(0);
        System.out.println("Podaj literkę");
        char druga = skaner.next().charAt(0);

        if ((int)pierwsza>(int)druga){
            System.out.println("Ta literka jest pozniej w alfabecie");
            System.out.println(pierwsza);
            System.out.println((int) pierwsza);
        }
        else if ((int)pierwsza<(int)druga){
            System.out.println("Ta literka jest pozniej w alfabecie");
            System.out.println(druga);
            System.out.println((int) druga);
        }
        else if ((int)pierwsza==(int)druga){
            System.out.println("Te literki są takie same");
        }
        else {
            System.out.println("Coś poszło nie tak :(");
        }
//jeśli damy jedna literke duża a drugą małą to będą krzaczki co mi się nie podoba :c
         */
//        Zadanie 8:
//        Pobierz od użytkownika 3 liczby i wypisz je w kolejności malejącej.
        /*
        Scanner skaner = new Scanner(System.in);
        int a = skaner.nextInt();
        int b = skaner.nextInt();
        int c = skaner.nextInt();
        if ((a > b) && (a > c) && (b > c)) {
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
        }
        else if ((b > a) && (b > c) && (a > c)) {
            System.out.println(b);
            System.out.println(a);
            System.out.println(c);
        }
        else if ((c > b) && (c > a) && (b > a)) {
            System.out.println(c);
            System.out.println(b);
            System.out.println(a);
        }
        else if ((c > b) && (c > a) && (a > b)) {
            System.out.println(c);
            System.out.println(a);
            System.out.println(b);
        }
// program sie krzaczy jak dostanie 0, albo -(liczba)
         */
//        Zadanie 9:
//        Napisz program, który pobierze od użytkownika dwa punkty współrzędnych (x1,y1 i x2, y2), a
//        następnie wypisze odległość między nimi. Użyj Math.pow(liczba, potęga)
        /*
        Scanner skaner = new Scanner(System.in);
        System.out.println("Podaj kolejno --> x1,y1");
        int x_1 = skaner.nextInt();
        int y_1 = skaner.nextInt();
        System.out.println("Podaj kolejno --> x2,y2");
        int x_2 = skaner.nextInt();
        int y_2 = skaner.nextInt();
        double z = Math.sqrt(Math.pow(x_2-x_1,2)+Math.pow(y_2-y_1,2));
        System.out.println(String.format("Odległość między twoimi punktami wynosi %s",z));
        */
//        Zadanie 10:
//        Kamień papier nożyce. Niech program generuje losową liczbę 0, 1 lub 2 reprezentujące
//        kamień, papier i nożyce. Następnie użytkownik wybiera 0, 1 lub 2 i program określa czy
//        wygrał, przegrał, lub osiągnął remis.
        /*
        int losowanie = (int)(Math.random()*3);
        Scanner skaner = new Scanner(System.in);
        System.out.print("Wpisz 0 (papier), 1 (kamień) lub 2 (nożyce) --> ");
        int wybór = skaner.nextInt();
        if (losowanie==2&&wybór==0){
            System.out.println(String.format("Przegrałeś (%s wybral komp) (%s wybrales ty)",losowanie,wybór));
        }
        else if (losowanie==0&&wybór==2){
            System.out.println(String.format("Wygrałeś (%s wybral komp) (%s wybrales ty)",losowanie,wybór));
        }
        else if (wybór>losowanie){ //kamien>papier,nozyce>kamień
            System.out.println(String.format("Przegrałeś (%s wybral komp) (%s wybrales ty)",losowanie,wybór));
        }
        else if (losowanie>wybór){
            System.out.println(String.format("Wygrałeś (%s wybral komp) (%s wybrales ty)",losowanie,wybór));
        }
        else if (losowanie==wybór){
            System.out.println(String.format("REMIS (%s wybral komp) (%s wybrales ty)",losowanie,wybór));
        }
        else {
            System.out.println("Coś poszlo nie tak");
        }
         */
    }
}