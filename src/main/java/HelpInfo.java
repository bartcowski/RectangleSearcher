/*
 * @author Bartosz Ciućkowski
 * @topic AAL punkty symetryczne
 */

public class HelpInfo {
    public static void showRunModesInfo() {
        System.err.println("Wrong running option!");
        System.out.println("Choose from 3 modes by providing following arguments (without brackets):");
        System.out.println("m1 (fileName).txt                                       =reads points from file");
        System.out.println("m2 (amountOfPoints) (coordinatesFROM) (coordinatesTO)   =generates points with coordinates restricted by FROM and TO");
        System.out.println("m3 (n) (step) (k) (repeat)                              =generates points, used for benchmarking, provides additional information in table");
        System.out.println("   where: n - amount of points in the first collection (e.g. 1000)");
        System.out.println("          step - by how much should the collection grow bigger with every 'k' (e.g. 500)");
        System.out.println("          k - how many times should the collection grow by 'step' value (e.g. 30)");
        System.out.println("          repeat - how many times should the test be repeated for the same amount of points (e.g. 10)");
    }

    public static void showM1Info() {
        System.err.println("Wrong use of MODE1 or file not found");
        System.out.println("Template: m1 (fileName).txt");
        System.out.println("Make sure to provide proper file with no typos in its name and add .txt at the end");
    }

    public static void showM2Info() {
        System.err.println("Wrong use of MODE2");
        System.out.println("Template: m2 (amountOfPoints) (coordinatesFROM) (coordinatesTO)");
        System.out.println("Make sure to provide INTEGER values for all arguments and:");
        System.out.println("amountOfPoints >= 5, coordinatesFROM < coordinatesTO");
    }

    public static void showM3Info() {
        System.err.println("Wrong use of MODE3");
        System.out.println("Template: m3 (n) (step) (k) (repeat)");
        System.out.println("   where: n - amount of points in the first collection (e.g. 1000)");
        System.out.println("          step - by how much should the collection grow bigger with every 'k' (e.g. 500)");
        System.out.println("          k - how many times should the collection grow by 'step' value (e.g. 30)");
        System.out.println("          repeat - how many times should the test be repeated for the same amount of points (e.g. 10)");
    }
}
