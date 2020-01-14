import java.util.Scanner;

class Rozvrh {
    public static void main(String[] args) {
        //variables
        String rozvrh[][] = { { " ", "HW", "HW", "NET", "NET", "PCV", "PCV", " ", "ANJ", "ANJ", " "},
                              { " ", "MAT", "ANJ", "PRG", "PRG", "CJL", "MAT", " ", " ", " ", " " },
                              { " ", "TEV", "TEV", "CJL", "FYZ", "ZMU", "MDO", "ANJ", " ", " ", " " },
                              { " ", "ZMU", "ZMU", "OPS", "OPS", "WEB", "WEB", " ", " ", " ", " " },
                              {" ", "POP", "POP", "OVS", "FYZ", "MAT", "MAT", " ", " ", " ", " " },
                            };
        String dny[] = { "pondeli", "utery", "stredu", "ctvrtek", "patek" };
        Scanner sc = new Scanner(System.in);
        String name = "";
        int nultaH = 0;
        int pocetHVDnu = 0;

        //Hello & input name
        System.out.println("Vitej v programu, který ti rekne, kdy mas hodiny programovani, Napis sve krestni jmeno");
        name = sc.nextLine();
        sc.reset();
        //if name is test, run test method, else clear array and do the normal procedure
        if (name.equals("test")) {

            name = test(); //sets the name to "Lukas" and prints out "test mode". did a lot more until i deleted most of it.
            
            
        } else {
            //clears the array
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 10; j++) {
                    rozvrh[i][j] = null;
                }
            }
            user(name, dny, nultaH, rozvrh, sc, pocetHVDnu); //"main" method that loops through every day, handles 0. hours and calls other methods  to fill in the classes

        }
        searchForPrg(rozvrh, dny, name); //starts searching for the PRG classes and then calls another method to print them out

    }

    //mostly deleted test method for ease of debugging and stuff
    public static String test() {
        System.out.println("test mode");
        return "Lukas";
    }

    //fills the array with user input
    public static void fillRozvrh(int pocetHVDnu, String rozvrh[][], int day, Scanner sc) {
        //loops through hours. called every "day" from previous loop.
        for (int hour = 1; hour <= pocetHVDnu; hour++) {
            System.out.println(hour + ". Hodinu máš jaký predmet?");
            sc.reset();
            rozvrh[day][hour] = sc.nextLine(); //user inputs what hour he has at the time it is shown
        }
    }
    //fills the array to the brim with empty strings so every row is the same length
    public static void fillInRest(int pocetHVDnu, String rozvrh[][], int day) {
        if (pocetHVDnu < 10) {
            for (int i = pocetHVDnu + 1; i <= 10; i++) {
                rozvrh[day][i] = " ";
            }
        }
    }
    //main method, handles most of everything
    public static void user(String name, String dny[], int nultaH, String rozvrh[][], Scanner sc, int pocetHVDnu) {
        System.out.println("Vítej uživateli: " + name
                + ".\nZacneme Pondelim, mas nultou hodinu? (tohle uvidíš ještě 4x)\n1. Ano | 2. Ne\n----------------");

        //loops through the school days
        for (int day = 0; day < dny.length; day++) {

            //asks if user has a 0. hour
            System.out.println("Mas v " + (String) dny[day] + " nultou hodinu? 1. Ano | 2. Ne");
            sc.reset();
            nultaH = Integer.parseInt(sc.nextLine()); //answer

            if (nultaH == 2) { //no

                rozvrh[day][0] = " "; //sets the 0. class to blank

            } else { //yes

                System.out.println("Co mas za 0. hodinu?");
                sc.reset();
                rozvrh[day][0] = sc.nextLine(); //sets the 0. class to user input

            }

            System.out.println("Kolik mas v " + (String) dny[day] + " hodin? (krome 0. hodiny)");
            sc.reset();
            pocetHVDnu = Integer.parseInt(sc.nextLine()); //how many hours do you have in aday

            fillRozvrh(pocetHVDnu, rozvrh, day, sc); //fills the array with user input
            fillInRest(pocetHVDnu, rozvrh, day);     //makes every row have the same # of elements

        }
    }
    //searches for the PRG classes
    public static void searchForPrg(String rozvrh[][], String dny[], String name) {
        //variables
        int hodinyPrg[] = new int[60];
        int den[] = new int[60];
        String hodina;
        int incr = 0;
        //loop through array
        for (int i = 0; i < 5; i++) {
            for (int y = 0; y < 10; y++) {
                hodina = rozvrh[i][y]; //set variable to the current element

                if (hodina.toUpperCase().equals("PRG")) { //if var is PRG, save it
                    den[incr] = i;       //var den saves the day the class is on
                    hodinyPrg[incr] = y; //var hodinyPRG saves the time it is on
                    incr++;        
                }

            }
        }
        printFin(den, hodinyPrg, dny, name, incr); //print out

    }
    //prints out times of PRG classes
    public static void printFin(int den[], int hodinyPrg[], String dny[], String name, int incr) {
        System.out.println("Studente: " + name + "\nHodiny programovani mas v: ");
        //loop that prints every element from both "saving" arrays at the same position
        for (int i = 0; i < incr; i++) {
            System.out.println(dny[den[i]] + ": " + hodinyPrg[i] + ". hodinu"); //eq.: at Friday: 3. class you have PRG
        }

    }

}
