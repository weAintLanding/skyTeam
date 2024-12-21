public class AltitudeTrack {
    private final int[] altitudes = {6000, 5000, 4000, 3000, 2000, 1000, 0};
    private final int[] rerollTokens = {1,0,0,0,1,0,0};
    private int currentAltitudeIndex;
    private final Game game;

    public AltitudeTrack(Game game) {
        currentAltitudeIndex = -1;
        this.game = game;
    }

    public int getCurrentAltitude() {
        return altitudes[currentAltitudeIndex];
    }

    public int getRerollTokens() {
        return rerollTokens[currentAltitudeIndex];
    }

    public void descend() {
        if (currentAltitudeIndex < altitudes.length - 1) {
            currentAltitudeIndex++;
            System.out.print("Descending and Current Altitude is: " + getCurrentAltitude());
            if (getRerollTokens() > 0) {
                game.setRerollsAvailable();
            }
            System.out.println(" | Reroll Tokens: " + game.getRerollsAvailable());
            System.out.println();
        } else {
            System.out.println("Already at the final altitude (landed).");
            System.out.println(" | Reroll Tokens: " + game.getRerollsAvailable());
            System.out.println();
        }
    }
    public void displayAltitudeStatus() {
        System.out.print("Current Altitude: " + getCurrentAltitude());
        if (getRerollTokens() > 0) {
            System.out.println(" | Reroll Tokens: " + getRerollTokens());
        } else {
            System.out.println();
        }
    }
}


