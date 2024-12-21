import java.util.ArrayList;
import java.util.Arrays;

public class ApproachTrack {
    private final int length = 7; // Total length of the approach track
    private final ArrayList<Integer> track; // Number of airplanes at each Index

    // Constructor: Initialize the track with predefined airplane counts
    public ApproachTrack() {
        this.track = new ArrayList<>(Arrays.asList(0,0,1,2,1,3,2));
        // Populate the track: (0, 0), (1, 0), (2, 1), (3, 2), (4, 1), (5, 3), (6, 2)
    }

    public int getLength() {
        return length;
    }

    // Getter for the airplane count at a specific position
    public int getAirplanesAt(int index) {
        if (index >= 0 && index < length) {
            return track.get(index);
        }
        //System.out.println("Position out of bounds: " + index);
        return -1;
    }

    // Remove an airplane from a specific position
    public boolean removeAirplane(int position) {
        if (position >= 0 && position < length && track.get(position) > 0) {
            track.set(position, track.get(position) - 1); // Decrease airplane count
            return true; // Successfully removed
        }
        System.out.println("No airplanes to remove at position " + position);
        return false; // Nothing to remove
    }

    // Check if a position contains airplanes
    public boolean hasAirplanesAt(int position) {
        if (position >= 0 && position < length) {
            return track.get(position) > 0;
        }
        return false;
    }

    public boolean removeAirplaneWithRadio(int currentPosition, int diceValue) {
        int targetPosition = currentPosition + diceValue - 1; // Adjust for 1-based counting
        if (targetPosition >= 0 && targetPosition < length) {
            return removeAirplane(targetPosition);
        } else {
            System.out.println("Target position out of bounds: " + targetPosition);
            return false;
        }
    }

}
