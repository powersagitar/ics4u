import java.util.Arrays;
import java.util.HashSet;

public class Debug {
    public static <T> void print(T[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void print(HashSet<Code.Color[]> set) {
        for (Code.Color[] arr : set) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
