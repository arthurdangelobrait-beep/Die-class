package a4;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * This class represents a die with a user-defined number of faces.
 * Each face has a string label and one face is the current value.
 *
 * Fields:
 * faceLabels - stores the labels of each face
 * currentFace - index of the current face
 * rng - random number generator for rolling
 */
public class Die implements Comparable<Die> {

    private final String[] faceLabels;
    private int currentFace;
    private final Random rng;

    /**
     * Constructor that creates a die with given labels.
     *
     * @param labels the face labels
     */
    public Die(String... labels) {
        Objects.requireNonNull(labels);

        if (labels.length == 0) {
            throw new IllegalArgumentException("Die must have at least one face");
        }

        this.faceLabels = new String[labels.length];
        for (int i = 0; i < labels.length; i++) {
            this.faceLabels[i] = Objects.requireNonNull(labels[i]);
        }

        this.currentFace = 0;
        this.rng = new Random();
    }

    /**
     * Copy constructor.
     *
     * @param other the die to copy
     */
    public Die(Die other) {
        Objects.requireNonNull(other);

        this.faceLabels = Arrays.copyOf(other.faceLabels, other.faceLabels.length);
        this.currentFace = other.currentFace;
        this.rng = new Random(); // independent
    }

    /**
     * Creates a standard 6-sided die.
     *
     * @return a die with labels "1" to "6"
     */
    public static Die standardDie() {
        return new Die("1", "2", "3", "4", "5", "6");
    }

    /**
     * Gets current value.
     *
     * @return current face label
     */
    public String getValue() {
        return faceLabels[currentFace];
    }

    /**
     * Rolls the die randomly.
     *
     * @return new value
     */
    public String roll() {
        currentFace = rng.nextInt(faceLabels.length);
        return getValue();
    }

    /**
     * Returns a copy of face labels.
     *
     * @return array of labels
     */
    public String[] getFaceLabels() {
        return Arrays.copyOf(faceLabels, faceLabels.length);
    }

    /**
     * Compares dice using dictionary order of values.
     */
    @Override
    public int compareTo(Die other) {
        Objects.requireNonNull(other);
        return this.getValue().compareTo(other.getValue());
    }

    /**
     * two dice are equal if their current values match.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Die)) return false;

        Die other = (Die) obj;
        return this.getValue().equals(other.getValue());
    }

    /**
     * hash code based on current value.
     */
    @Override
    public int hashCode() {
        return getValue().hashCode();
    }

    /**
     * returns string showing faces and current value.
     */
    @Override
    public String toString() {
        return "Die faces=" + Arrays.toString(faceLabels)
                + " current=" + getValue();
    }

    /**
     * main method demonstrating functionality.
     */
    public static void main(String[] args) {

        Die d1 = new Die("A", "B", "C", "A", "B", "C");
        Die d2 = new Die("01", "02", "03", "04", "05", "06", "A", "B", "C", "D", "E", "F");

        System.out.println("Creating a 6 sided die d1 with faces A, B, C, A, B, C");
        System.out.println(d1.getValue() + " : " + Arrays.toString(d1.getFaceLabels()));
        System.out.println();

        System.out.println("Creating a 12 sided die d2 with faces 01, 02, ..., F");
        System.out.println(d2.getValue() + " : " + Arrays.toString(d2.getFaceLabels()));
        System.out.println();

        System.out.println("d1 current value: " + d1.getValue());
        System.out.println("d2 current value: " + d2.getValue());
        System.out.println();

        System.out.println("Rolling the dice 5 times:");
        for (int i = 0; i < 5; i++) {

            System.out.println("Rolling...");

            d1.roll();
            d2.roll();

            System.out.println("d1 current value: " + d1.getValue());
            System.out.println("d2 current value: " + d2.getValue());

            int cmp = d1.compareTo(d2);

            if (cmp < 0) {
                System.out.println("d1 less than d2");
            } else if (cmp > 0) {
                System.out.println("d1 greater than d2");
            } else {
                System.out.println("d1 equals d2");
            }

            System.out.println();
        }

        //  contructor test
        Die d3 = new Die(d1);
        System.out.println("Copy of d1: " + d3);

        // die test
        Die d4 = Die.standardDie();
        System.out.println("Standard die: " + d4);
    }
}