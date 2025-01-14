package mastermind.core;

import mastermind.Mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CodeFactory {
    private CodeFactory() {
        throw new IllegalStateException("Factory class should not be instantiated.");
    }

    public static Code getRandom() {
        final Random random = new Random();
        final ArrayList<Integer> codeBuilder = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            final int randColor = random.nextInt(Mastermind.TOTAL_COLORS);
            codeBuilder.add(randColor);
        }

        return fromColorIndices(codeBuilder);
    }

    public static Code fromColorIndices(final List<Integer> indices) {
        final List<Code.Color> code = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (final int idx : indices) {
            code.add(Code.Color.fromIndex(idx));
        }

        return new Code(code);
    }
}
