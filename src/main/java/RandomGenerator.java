/*
 * @author Bartosz Ciućkowski
 * @topic AAL punkty symetryczne
 */

import java.util.Random;

public class RandomGenerator {
    private static final int RANGE = 10000; //limits integers generated without given restrictions to (-RANGE, +RANGE) for more reasonable results
    private Random rand = new Random();

    public int getRandomInt(int from, int to) {
        return (rand.nextInt(to - from + 1) + from) - (rand.nextInt(to - from + 1) + from);
    }

    public int getRandomInt() {
        return (rand.nextInt(2*RANGE+1) - RANGE) - (rand.nextInt(2*RANGE+1) - RANGE);
    }
}
