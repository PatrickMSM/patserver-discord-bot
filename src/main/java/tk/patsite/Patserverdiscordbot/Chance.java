package tk.patsite.Patserverdiscordbot;

public class Chance {
    public static boolean rand(int oneIn) {
        if (oneIn < 1) {
            throw new IllegalArgumentException("oneIn can not be negative or 0");
        }
        return Settings.NonSettings.RANDOM.nextInt(Math.abs(oneIn)) == 0;
    }
}
