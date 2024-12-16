
/**
 * TestSuperAnimal
 *
 * Author: Mohan Dong
 * Date:   Dec 16, 2024
 *
 * Description
 * This program tests the SuperAnimal class, which extends the Animal class, and
 * has more attributes and methods.
 */

import java.util.ArrayList;

public class TestSuperAnimal {
    private static final int NUM_ANIMALS = 2;

    public static void main(String[] args) {
        ArrayList<SuperAnimal> animals = makeAnimals();

        for (final SuperAnimal animal : animals) {
            System.out.println(animal.fly());
            System.out.println(animal.teleport("home"));
            animal.eat();
            System.out.println(animal.die());
        }
    }

    /**
     * Creates a list of SuperAnimal objects with predefined attributes.
     *
     * @return an ArrayList of SuperAnimal objects.
     */
    private static ArrayList<SuperAnimal> makeAnimals() {
        ArrayList<SuperAnimal> animals = new ArrayList<>(NUM_ANIMALS);
        animals.add(new SuperAnimal("i have wings but cant teleport", "black", "carnivore", "tree", 1, true, false));
        animals.add(new SuperAnimal("i can teleport but cant fly", "white", "herbivore", "land", 1, false, true));

        return animals;
    }
}
