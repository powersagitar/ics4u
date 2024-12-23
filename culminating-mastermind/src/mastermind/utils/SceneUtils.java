package src.mastermind.utils;

import src.mastermind.Mastermind;
import src.mastermind.core.Code;

import java.awt.*;
import java.util.HashMap;

public class SceneUtils {
    public static final HashMap<Code.Color, Color> codeColorAwtColorMap = new HashMap<>(Mastermind.TOTAL_COLORS);

    static {
        codeColorAwtColorMap.put(Code.Color.Blue, Color.blue);
        codeColorAwtColorMap.put(Code.Color.Green, Color.green);
        codeColorAwtColorMap.put(Code.Color.Orange, Color.orange);
        codeColorAwtColorMap.put(Code.Color.Purple, new Color(139, 0, 255));
        codeColorAwtColorMap.put(Code.Color.Red, Color.red);
        codeColorAwtColorMap.put(Code.Color.Yellow, Color.yellow);
    }
}