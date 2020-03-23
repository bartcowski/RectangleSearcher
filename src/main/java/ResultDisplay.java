/*
 * @author Bartosz Ciućkowski
 * @topic AAL punkty symetryczne
 */

public class ResultDisplay {
    public static void showResults(FoundRectangle bestRectangle, FoundRectangle originalRectangle, int reflections) {
        System.out.println("Smallest perimeter rectangle found...");
        System.out.println("Boundary points:");
        System.out.println("TOP: (" + bestRectangle.getMaxY().getX() + ", " + bestRectangle.getMaxY().getY() + ")");
        System.out.println("RIGHT: (" + bestRectangle.getMaxX().getX() + ", " + bestRectangle.getMaxX().getY() + ")");
        System.out.println("LEFT: (" + bestRectangle.getMinX().getX() + ", " + bestRectangle.getMinX().getY() + ")");
        System.out.println("BOT: (" + bestRectangle.getMinY().getX() + ", " + bestRectangle.getMinY().getY() + ")");
        System.out.println("Perimeter: " + bestRectangle.calculatePerimeter());

        if (bestRectangle.calculatePerimeter() < originalRectangle.calculatePerimeter()) {
            System.out.println("Original rectangle perimeter (before any reflections): " + originalRectangle.calculatePerimeter());
            System.out.println("Improved by: " + (originalRectangle.calculatePerimeter() - bestRectangle.calculatePerimeter())
                                + " with use of: " + reflections + " reflections");
        } else {
            System.out.println("Original rectangle perimeter (before any reflections): " + originalRectangle.calculatePerimeter());
            System.out.println("No improvements could've been made");
        }
    }

    public static void showBenchmarkResults(int n, int step, int k, long[] execTimes, double[] qFactors) {
        System.out.println("Benchmark completed");
        System.out.printf("%15s | %15s | %10s\n", "n", "t(n) [ms]", "q(n)");
        System.out.println();
        for (int i = 0; i < k; ++i, n += step) {
            System.out.printf("%15s | %15s | %10.2f\n", String.valueOf(n), String.valueOf(execTimes[i]), qFactors[i]);
        }
    }

    public static void showBenchmarkProgress(int currentIter, int totalIter) {
        System.out.println("Benchmark status: " + (currentIter + 1) + "/" + totalIter);
    }
}
