import java.util.Random;

public class Mastermind {
    final static int NUM_CODES = 6;
    final static int CODE_LENGTH = 4;
    final static int MAX_GUESSES = 10;

    public static void main(String[] args) {
    }

    private static Code[] generateSecretCode() {
        Code[] secretCode = new Code[CODE_LENGTH];
        Random random = new Random();

        for (int i = 0; i < CODE_LENGTH; ++i) {
            secretCode[i] = Code.values()[random.nextInt(NUM_CODES)];
        }

        return secretCode;
    }
}
