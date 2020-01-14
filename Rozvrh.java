import java.util.Scanner;

class Rozvrh {
    public static void main(String[] args) {
        String rozvrh[][] = { { " ", "HW", "HW", "NET", "NET", "PCV", "PCV", " ", "ANJ", "ANJ", " "},
                { " ", "MAT", "ANJ", "PRG", "PRG", "CJL", "MAT", " ", " ", " ", " " },
                { " ", "TEV", "TEV", "CJL", "FYZ", "ZMU", "MDO", "ANJ", " ", " ", " " },
                { " ", "ZMU", "ZMU", "OPS", "OPS", "WEB", "WEB", " ", " ", " ", " " },
                { " ", "POP", "POP", "OVS", "FYZ", "MAT", "MAT", " ", " ", " ", " " }, };
        String dny[] = { "pondeli", "utery", "stredu", "ctvrtek", "patek" };
        Scanner sc = new Scanner(System.in);
        String name = "";
        int nultaH = 0;
        int pocetHVDnu = 0;

        System.out.println("Vitej v programu, který ti rekne, kdy mas hodiny programovani, Napis sve krestni jmeno");
        name = sc.nextLine();
        sc.reset();
        if (name.equals("test")) {

            name = test();
            
            
        } else {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 10; j++) {
                    rozvrh[i][j] = null;
                }
            }
            user(name, dny, nultaH, rozvrh, sc, pocetHVDnu);

        }
        searchForPrg(rozvrh, dny, name);

    }

    public static String test() {
        System.out.println("test mode");
        return "Lukas";
    }


    public static void fillRozvrh(int pocetHVDnu, String rozvrh[][], int day, Scanner sc) {
        for (int hour = 1; hour <= pocetHVDnu; hour++) {
            System.out.println(hour + ". Hodinu máš jaký predmet?");
            sc.reset();
            rozvrh[day][hour] = sc.nextLine();
        }
    }

    public static void fillInRest(int pocetHVDnu, String rozvrh[][], int day) {
        if (pocetHVDnu < 10) {
            for (int i = pocetHVDnu + 1; i <= 10; i++) {
                rozvrh[day][i] = " ";
            }
        }
    }

    public static void user(String name, String dny[], int nultaH, String rozvrh[][], Scanner sc, int pocetHVDnu) {
        System.out.println("Vítej uživateli: " + name
                + ".\nZacneme Pondelim, mas nultou hodinu? (tohle uvidíš ještě 4x)\n1. Ano | 2. Ne\n----------------");
        for (int day = 0; day < dny.length; day++) {

            System.out.println("Mas v " + (String) dny[day] + " nultou hodinu? 1. Ano | 2. Ne");
            sc.reset();
            nultaH = Integer.parseInt(sc.nextLine());

            if (nultaH == 2) {

                rozvrh[day][0] = " ";

            } else {

                System.out.println("Co mas za 0. hodinu?");
                sc.reset();
                rozvrh[day][0] = sc.nextLine();

            }

            System.out.println("Kolik mas v " + (String) dny[day] + " hodin? (krome 0. hodiny)");
            sc.reset();
            pocetHVDnu = Integer.parseInt(sc.nextLine());

            fillRozvrh(pocetHVDnu, rozvrh, day, sc);
            fillInRest(pocetHVDnu, rozvrh, day);

        }
    }

    public static void searchForPrg(String rozvrh[][], String dny[], String name) {
        int hodinyPrg[] = new int[60];
        int den[] = new int[60];
        String hodina;
        int incr = 0;
        for (int i = 0; i < 5; i++) {
            for (int y = 0; y < 10; y++) {
                hodina = rozvrh[i][y];

                if (hodina.toUpperCase().equals("PRG")) {
                    den[incr] = i;
                    hodinyPrg[incr] = y;
                    incr++;
                }

            }
        }
        printFin(den, hodinyPrg, dny, name, incr);

    }

    public static void printFin(int den[], int hodinyPrg[], String dny[], String name, int incr) {
        System.out.println("Studente: " + name + "\nHodiny programovani mas v: ");
        for (int i = 0; i < incr; i++) {
            System.out.println(dny[den[i]] + ": " + hodinyPrg[i] + ". hodinu");
        }

    }

}
