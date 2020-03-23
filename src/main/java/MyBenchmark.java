/*
 * @author Bartosz Ciućkowski
 * @topic AAL punkty symetryczne
 */

import java.util.Arrays;

public class MyBenchmark {
    private final int WU_N = 2000;
    private final int WU_STEP = 4000;
    private final int WU_ITERATIONS = 20;
    private final int WU_REPEAT = 4;
    private long[] executionTimes;   //stores time values for every collection size tested in the same benchmark
    private long[] theoreticalTimes; //stores theoretical times acquired by EXPECTED complexity
    private double[] qFactors;         //stores calculated (out of theoretical and execution times) factors that show if theoretical complexity is correct

    public MyBenchmark(int execTimesSize) {
        executionTimes = new long[execTimesSize];
        theoreticalTimes = new long[execTimesSize];
        qFactors = new double[execTimesSize];
    }

    public long[] getExecutionTimes() {
        return executionTimes;
    }

    public void testAlgorithm(int n, int step, int k, int repeat) {
        int origN = n;
        long timesSum = 0; //sum of times for single collection size, further used to calculate average time (timesSum/repeat)
        long startTime;
        long endTime;

        //warm up for JVM
        warmUpJVM(WU_N, WU_STEP, WU_ITERATIONS, WU_REPEAT);

        //actual benchmark
        for (int i = 0; i < k; ++i, n+=step) {
            for(int j = 0; j < repeat; ++j) {
                PointsCollection pointsCollection = new PointsCollection();
                pointsCollection.createRandomly(n);
                RectangleSearcher rectangleSearcher = new RectangleSearcher(pointsCollection);

                startTime = System.nanoTime();
                rectangleSearcher.benchmarkSearchForRectangles();
                endTime = System.nanoTime();

                timesSum += endTime - startTime;
            }
            executionTimes[i] = (timesSum / repeat / 1000000);    //average time in milliseconds
            theoreticalTimes[i] = calculateTheoreticalTime(n);
            ResultDisplay.showBenchmarkProgress(i, k);
        }
        calculateFactors();
        ResultDisplay.showBenchmarkResults(origN, step, k, executionTimes, qFactors);
    }

    private long calculateMedianOfTimes(long[] times) {
        long[] timesCopy = Arrays.copyOf(times, times.length);
        Arrays.sort(timesCopy);
        if (timesCopy.length % 2 == 0) {
            return (timesCopy[timesCopy.length / 2] + timesCopy[(timesCopy.length / 2 - 1)]) / 2;
        } else {
            return timesCopy[timesCopy.length / 2];
        }
    }

    private long calculateTheoreticalTime(long n) {
        //for O(n log(n)) complexity
        return (int) ((Math.log(n) / Math.log(2) + 1e-10) * n);
    }

    private void calculateFactors() {
        for (int i = 0; i < qFactors.length; ++i) {
            qFactors[i] = ((double) (executionTimes[i] * calculateMedianOfTimes(theoreticalTimes)) /
                           (double) (theoreticalTimes[i] * calculateMedianOfTimes(executionTimes)));
        }
    }

    private void warmUpJVM(int n, int step, int k, int repeat) {
        for (int i = 0; i < k; ++i, n+=step) {
            System.out.println("Warm up...");
            for(int j = 0; j < repeat; ++j) {
                PointsCollection pointsCollection = new PointsCollection();
                pointsCollection.createRandomly(n);
                RectangleSearcher rectangleSearcher = new RectangleSearcher(pointsCollection);
                rectangleSearcher.benchmarkSearchForRectangles();
            }
        }
    }
}
