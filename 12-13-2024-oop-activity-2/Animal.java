/**
 * Animal.java
 *
 * Author: Mohan Dong
 * Date: Dec 12, 2024
 *
 * Description:
 * Represents an Animal with various attributes and behaviors.
 */

public class Animal {
    // The name of the animal
    private String name;
    // The color of the animal
    private String color;
    // The dietary classification of the animal (e.g., herbivore, carnivore)
    private String dietaryClassification;
    // The habitat where the animal lives
    private String habitat;
    // The intelligence level of the animal
    private int intelligence;

    /**
     * Constructs an Animal with the specified attributes.
     *
     * @param name                  the name of the animal
     * @param color                 the color of the animal
     * @param dietaryClassification the dietary classification of the animal
     * @param habitat               the habitat of the animal
     * @param intelligence          the intelligence level of the animal
     */
    Animal(String name, String color, String dietaryClassification, String habitat, int intelligence) {
        this.name = name;
        this.color = color;
        this.dietaryClassification = dietaryClassification;
        this.habitat = habitat;
        this.intelligence = intelligence;
    }

    /**
     * Prints the details of the animal for debugging purposes.
     */
    public void debug() {
        System.out.println("Animal:");
        System.out.println("Name: " + name);
        System.out.println("Color: " + color);
        System.out.println("Dietary Classification: " + dietaryClassification);
        System.out.println("Habitat: " + habitat);
        System.out.println("Intelligence: " + intelligence);
    }

    /**
     * Simulates the animal sleeping.
     */
    public void sleep() {
        System.out.println("Sleeping...");
    }

    /**
     * Simulates the animal eating.
     */
    public void eat() {
        System.out.println("Eating...");
    }

    /**
     * Simulates the animal drinking.
     */
    public void drink() {
        System.out.println("Drinking...");
    }

    /**
     * Simulates the animal moving.
     */
    public void move() {
        System.out.println("Moving...");
    }

    /**
     * Simulates the animal making a sound.
     */
    public void makeSound() {
        System.out.println("Making sound...");
    }

    /**
     * Sets the name of the animal.
     *
     * @param name the new name of the animal
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the animal.
     *
     * @return the name of the animal
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the color of the animal.
     *
     * @param color the new color of the animal
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Returns the color of the animal.
     *
     * @return the color of the animal
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the dietary classification of the animal.
     *
     * @param dietaryClassification the new dietary classification of the animal
     */
    public void setDietaryClassification(String dietaryClassification) {
        this.dietaryClassification = dietaryClassification;
    }

    /**
     * Returns the dietary classification of the animal.
     *
     * @return the dietary classification of the animal
     */
    public String getDietaryClassification() {
        return dietaryClassification;
    }

    /**
     * Sets the habitat of the animal.
     *
     * @param habitat the new habitat of the animal
     */
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    /**
     * Returns the habitat of the animal.
     *
     * @return the habitat of the animal
     */
    public String getHabitat() {
        return habitat;
    }

    /**
     * Sets the intelligence level of the animal.
     *
     * @param intelligence the new intelligence level of the animal
     */
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * Returns the intelligence level of the animal.
     *
     * @return the intelligence level of the animal
     */
    public int getIntelligence() {
        return intelligence;
    }
}
