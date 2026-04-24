import java.util.Scanner;

public class MainMenu {

    private static final Scanner sc = new Scanner(System.in);

    // Main Menu Screen
    public static int open() {
        while (true) {
            clearScreen();

            System.out.println("===============================================================");
            System.out.println("                     H O L L O W   F R O N T I E R");
            System.out.println("===============================================================\n");

            System.out.println("         Where the desert breathes...");
            System.out.println("         Where the stars burn beneath the earth...");
            System.out.println("         Where fate is carved in dust and fire.\n");

            System.out.println("---------------------------------------------------------------");
            System.out.println("                       [1] PLAY");
            System.out.println("                       [2] EXIT");
            System.out.println("---------------------------------------------------------------");
            System.out.print("\nChoose: ");

            String choice = sc.nextLine().trim();

            if (choice.equals("1")) return 1;
            if (choice.equals("2")) return 2;

            System.out.println("\nInvalid option. Press ENTER to try again.");
            sc.nextLine();
        }
    }

    // Local clear screen (does not depend on RPGGame)
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls")
                        .inheritIO()
                        .start()
                        .waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Could not clear screen.");
        }
    }
}
