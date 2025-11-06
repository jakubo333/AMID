import java.util.*;

class Student {
    private String imie, nazwisko, kierunek;
    private char plec;
    private ArrayList<Double> oceny = new ArrayList<>();
    private static ArrayList<Student> listaStudentow = new ArrayList<>();


    public Student(String imie, String nazwisko, char plec, String kierunek, double... ocenyPoczatkowe) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.plec = plec;
        this.kierunek = kierunek;
        for (double o : ocenyPoczatkowe) oceny.add(o);
        listaStudentow.add(this);
        WyswietlInformacje();
    }



    public void WyswietlInformacje() {
        System.out.println("\n---------------------------");
        System.out.println(imie + " " + nazwisko + " (" + plec + ")");
        System.out.println("Kierunek: " + kierunek);
        System.out.println("Oceny: " + oceny);
        System.out.printf("Średnia: %.2f\n", ObliczSredniaOcen());
        System.out.println("Zaliczenie: " + (SprawdzZaliczenie() ? "TAK " : "NIE "));
    }

    public void ZmienKierunek(String nowyKierunek) {
        kierunek = nowyKierunek;
        System.out.println(" Kierunek zmieniono na " + nowyKierunek);
        WyswietlInformacje();
    }

    public void DodajOcene(double ocena) {
        if (!czyPoprawnaOcena(ocena)) {
            System.out.println(" Ocena musi być 1–6 (z połówkami np. 4.5)");
            return;
        }
        oceny.add(ocena);
        System.out.println(" Dodano ocenę: " + ocena);
        WyswietlInformacje();
    }

    public void ZmienOcene(int nr, double ocena) {
        if (nr < 1 || nr > oceny.size()) {
            System.out.println(" Nieprawidłowy numer oceny.");
            return;
        }
        if (!czyPoprawnaOcena(ocena)) {
            System.out.println(" Ocena musi być 1–6 (z połówkami np. 4.5)");
            return;
        }
        oceny.set(nr - 1, ocena);
        System.out.println(" Zmieniono ocenę nr " + nr + " na " + ocena);
        WyswietlInformacje();
    }

    private boolean czyPoprawnaOcena(double o) {
        return o >= 1 && o <= 6 && (o * 2) % 1 == 0;
    }

    public double ObliczSredniaOcen() {
        if (oceny.isEmpty()) return 0;
        double suma = 0;
        for (double o : oceny) suma += o;
        return Math.round((suma / oceny.size()) * 100.0) / 100.0;
    }

    public boolean SprawdzZaliczenie() {
        return ObliczSredniaOcen() >= 2.0;
    }

    public void ZmienImie(String noweImie) {
        imie = noweImie;
        System.out.println(" Imię zmieniono na " + noweImie);
        WyswietlInformacje();
    }

    public void ZmienNazwisko(String noweNazwisko) {
        nazwisko = noweNazwisko;
        System.out.println(" Nazwisko zmieniono na " + noweNazwisko);
        WyswietlInformacje();
    }

    public void UstawPlec(char nowaPlec) {
        plec = nowaPlec;
        System.out.println(" Płeć zmieniono na " + nowaPlec);
        WyswietlInformacje();
    }

    public static void WyswietlSredniaOcenaKierunku(String kierunek) {
        double suma = 0; int licznik = 0;
        for (Student s : listaStudentow)
            if (s.kierunek.equalsIgnoreCase(kierunek)) {
                suma += s.ObliczSredniaOcen();
                licznik++;
            }
        if (licznik == 0)
            System.out.println(" Brak studentów na kierunku " + kierunek);
        else
            System.out.printf(" Średnia ocen kierunku %s: %.2f\n", kierunek, suma / licznik);
    }

    public String getNazwisko() { return nazwisko; }
    public static ArrayList<Student> getListaStudentow() { return listaStudentow; }
}


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        new Student("Jan", "Kowalski", 'M', "Informatyka", 3, 3.5, 4);
        new Student("Anna", "Nowak", 'K', "Matematyka", 4, 5, 4.5);
        new Student("Piotr", "Wiśniewski", 'M', "Informatyka", 2, 2.5, 3);

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Wyświetl studentów");
            System.out.println("2. Dodaj ocenę");
            System.out.println("3. Zmień ocenę");
            System.out.println("4. Zmień kierunek");
            System.out.println("5. Zmień imię");
            System.out.println("6. Zmień nazwisko");
            System.out.println("7. Zmień płeć");
            System.out.println("8. Średnia kierunku");
            System.out.println("0. Zakończ");
            System.out.print("Wybór: ");

            int opcja = sc.nextInt();
            sc.nextLine();
            if (opcja == 0) break;

            if (opcja == 1) {
                for (Student s : Student.getListaStudentow()) s.WyswietlInformacje();
                continue;
            }
            if (opcja == 8) {
                System.out.print("Podaj kierunek: ");
                Student.WyswietlSredniaOcenaKierunku(sc.nextLine());
                continue;
            }

            System.out.print("Podaj nazwisko studenta: ");
            String nazw = sc.nextLine();
            Student wybrany = null;
            for (Student s : Student.getListaStudentow())
                if (s.getNazwisko().equalsIgnoreCase(nazw)) wybrany = s;

            if (wybrany == null) {
                System.out.println(" Nie znaleziono studenta!");
                continue;
            }

            switch (opcja) {
                case 2 -> { System.out.print("Nowa ocena: "); wybrany.DodajOcene(sc.nextDouble()); }
                case 3 -> {
                    System.out.print("Nr oceny do zmiany: ");
                    int nr = sc.nextInt();
                    System.out.print("Nowa ocena: ");
                    wybrany.ZmienOcene(nr, sc.nextDouble());
                }
                case 4 -> { System.out.print("Nowy kierunek: "); wybrany.ZmienKierunek(sc.nextLine()); }
                case 5 -> { System.out.print("Nowe imię: "); wybrany.ZmienImie(sc.nextLine()); }
                case 6 -> { System.out.print("Nowe nazwisko: "); wybrany.ZmienNazwisko(sc.nextLine()); }
                case 7 -> { System.out.print("Nowa płeć (M/K): "); wybrany.UstawPlec(sc.next().charAt(0)); }
                default -> System.out.println(" Błędna opcja.");
            }
        }
        sc.close();
    }
}
