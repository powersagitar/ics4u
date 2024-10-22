
/**
 * People
 * Exploration on how how hash maps work in Java.
 *
 * Author: Mohan Dong
 * Date: 10/22/2024
 */

import java.util.HashMap;

public class People {
    public static void main(String[] args) {
        var people = new HashMap<String, Integer>();

        people.put("John", 32);
        people.put("Steve", 30);
        people.put("Angie", 33);

        for (var i : people.keySet()) {
            System.out.println("Name: " + i + " Age: " + people.get(i));
        }
    }
}
