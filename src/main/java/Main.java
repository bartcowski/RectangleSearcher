/*
 * @author Bartosz Ciućkowski
 * @topic AAL punkty symetryczne
 */

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PointsCollection experimentSet = new PointsCollection();

        if (args.length < 1) {
            HelpInfo.showRunModesInfo();
            System.exit(1);
        }

        switch (args[0]) {
            case "m1": {
                try {
                    experimentSet.createFromFile(args[1]);
                } catch (IOException e) {
                    HelpInfo.showM1Info();
                    System.exit(1);
                }
                RectangleSearcher rectangleSearcher = new RectangleSearcher(experimentSet);
                rectangleSearcher.searchForRectangles();
                break;
            }
            case "m2": {
                if (args.length != 4 || Integer.parseInt(args[1]) < 5 || Integer.parseInt(args[2]) >= Integer.parseInt(args[3])) {
                    HelpInfo.showM2Info();
                } else {
                    experimentSet.createRandomly(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                    RectangleSearcher rectangleSearcher = new RectangleSearcher(experimentSet);
                    rectangleSearcher.searchForRectangles();
                }
                break;
            }
            case "m3": {
                if (args.length != 5 || Integer.parseInt(args[1]) < 5 || Integer.parseInt(args[2]) < 1
                        || Integer.parseInt(args[3]) < 1 || Integer.parseInt(args[4]) < 1) {
                    HelpInfo.showM3Info();
                } else {
                    MyBenchmark benchmark = new MyBenchmark(Integer.parseInt(args[3]));
                    benchmark.testAlgorithm(Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                                            Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                }
                break;
            }
            default:
                HelpInfo.showRunModesInfo();
                break;
        }
    }
}
