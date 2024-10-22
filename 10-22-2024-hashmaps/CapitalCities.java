
/**
 * Capital Cities
 * Exploration on how how hash maps work in Java.
 *
 * Author: Mohan Dong
 * Date: 10/22/2024
 */

import java.util.HashMap;

public class CapitalCities {
    public static void main(String[] args) {
        var capitalCities = new HashMap<String, String>();

        capitalCities.put("England", "London");
        capitalCities.put("Germany", "Berlin");
        capitalCities.put("Norway", "Oslo");
        capitalCities.put("USA", "Washington DC");
        System.out.println(capitalCities);

        // print keys
        for (var i : capitalCities.keySet()) {
            System.out.println(i);
        }

        // print values
        for (var i : capitalCities.values()) {
            System.out.println(i);
        }

        // print keys and values
        for (var i : capitalCities.keySet()) {
            System.out.println(i + "=" + capitalCities.get(i));
        }
    }
}
