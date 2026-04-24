public class Effects {

    public static void fadeYouDied() {
        String msg = "YOU DIED...";
        try {
            System.out.println();
            for (char c : msg.toCharArray()) {
                System.out.print(c);
                Thread.sleep(150); // fade speed
            }
            System.out.println("\n");
            Thread.sleep(600);
        } catch (Exception ignored) {}
    }
}
