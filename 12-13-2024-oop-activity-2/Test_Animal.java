
/**
 * Test_Animal.java
 *
 * Author: Mohan Dong
 * Date:   Dec 12, 2024
 *
 * Description:
 *
 * This program demonstrates the creation and manipulation of Animal objects.
 * It creates a list of Animal objects, modifies one of them, and prints their details.
 * Additionally, it makes one of the animals produce a sound.
 *
 * The program includes the following functionalities:
 * - Creating a list of Animal objects with specified attributes.
 * - Modifying the attributes of an Animal object.
 * - Printing the details of Animal objects before and after modification.
 * - Making an Animal object produce a sound.
 *
 * This program serves as an example of basic object-oriented programming concepts in Java.
 */

import java.util.ArrayList;

/**
 * The Test_Animal class demonstrates the creation and manipulation of Animal
 * objects.
 */
public class Test_Animal {

    /**
     * The main method is the entry point of the program.
     * It creates a list of Animal objects, modifies one of them, and prints their
     * details.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Create a list of Animal objects with a specified number of animals.
        ArrayList<Animal> animals = spawnAnimals(2);

        // Print the details of the animals before any changes.
        System.out.println("Animals before changes:");
        debugAnimals(animals);

        // Modify the name of the first animal in the list.
        animals.get(0).setName("new name");

        // Uncomment the following lines to modify other attributes of the first animal.
        // animals.get(0).setHabitat("new habitat");
        // animals.get(0).setColor("new color");
        // animals.get(0).setDietaryClassification("new dietary classification");
        // animals.get(0).setIntelligence(2);

        // Print a blank line for separation.
        System.out.println();
        // Print the details of the animals after the changes.
        System.out.println("Animals after changes:");
        debugAnimals(animals);

        // Make the second animal in the list produce a sound.
        animals.get(1).makeSound();
    }

    /**
     * Creates a list of Animal objects with the specified number of animals.
     *
     * @param num The number of Animal objects to create.
     * @return An ArrayList containing the created Animal objects.
     */
    private static ArrayList<Animal> spawnAnimals(final int num) {
        // Initialize an ArrayList to hold the Animal objects.
        ArrayList<Animal> animals = new ArrayList<Animal>(num);

        // Create the specified number of Animal objects and add them to the list.
        for (int i = 0; i < num; i++) {
            animals.add(new Animal("animal " + i, "black", "carnivore", "land", 1));
        }

        // Return the list of created Animal objects.
        return animals;
    }

    /**
     * Prints the details of each Animal object in the provided list.
     *
     * @param animals The list of Animal objects to debug.
     */
    private static void debugAnimals(final ArrayList<Animal> animals) {
        // Iterate through each Animal object in the list and print its details.
        for (Animal animal : animals) {
            animal.debug();
            // Print a blank line for separation.
            System.out.println();
        }
    }
}
