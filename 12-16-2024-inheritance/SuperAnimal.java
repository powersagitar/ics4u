/**
 * SuperAnimal
 *
 * Author: Mohan Dong
 * Date: Dec 16, 2024
 *
 * Description
 * This program defines the SuperAnimal class, which extends the Animal class,
 * and has more attributes and methods.
 */

public class SuperAnimal extends Animal {
    private boolean wings;
    private boolean canTeleport;

    /**
     * Constructs a new SuperAnimal with the specified attributes.
     *
     * @param name                  the name of the animal
     * @param color                 the color of the animal
     * @param dietaryClassification the dietary classification of the animal (e.g.,
     *                              herbivore, carnivore)
     * @param habitat               the habitat where the animal lives
     * @param intelligence          the intelligence level of the animal
     * @param wings                 whether the animal has wings
     * @param canTeleport           whether the animal can teleport
     */
    SuperAnimal(String name, String color, String dietaryClassification, String habitat, int intelligence,
            boolean wings, boolean canTeleport) {
        super(name, color, dietaryClassification, habitat, intelligence);

        this.wings = wings;
        this.canTeleport = canTeleport;
    }

    /**
     * Determines if the animal can fly based on the presence of wings.
     *
     * @return A string indicating whether the animal is flying or cannot fly.
     */
    public String fly() {
        if (this.wings) {
            return this.getName() + " is flying.";
        } else {
            return this.getName() + " cannot fly.";
        }
    }

    /**
     * Teleports the animal to the specified place if it has the ability to
     * teleport.
     *
     * @param toPlace the destination to which the animal will teleport
     * @return a message indicating whether the animal is teleporting to the
     *         specified place or cannot teleport
     */
    public String teleport(String toPlace) {
        if (this.canTeleport) {
            return this.getName() + " is teleporting to " + toPlace;
        } else {
            return this.getName() + " cannot teleport.";
        }
    }

    /**
     * This method returns a string indicating that the animal is dead.
     *
     * @return A string in the format "<animal name> is dead."
     */
    public String die() {
        return this.getName() + " is dead.";
    }
}
