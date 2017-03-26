import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class PointsAndSegments {

    private static Random random = new Random();

    public static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        Map<Integer, Integer> pointIndex = new HashMap<>();
        for (int i=0; i < points.length; i++) {
            pointIndex.put(points[i], i);
        }

        int elementsLength = starts.length + ends.length + points.length;
        int[] elements = new int[elementsLength];
        byte[] types = new byte[elementsLength];
        for (int i = 0; i < elementsLength; i ++) {
            if (i < starts.length) {
                elements[i] = starts[i];
                types[i] = 0;
            } else if (i < starts.length + ends.length) {
                elements[i] = ends[i - starts.length];
                types[i] = 1;
            } else {
                elements[i] = points[i - starts.length - ends.length];
                types[i] = 2;
            }
        }

        randomizedQuickSort(elements, types, 0, elementsLength - 1);

        int numSegments = 0;
        int numPoints = 0;
        for(int i = 0; i < elementsLength && numPoints < points.length; i++) {
            switch (types[i]) {
                case 0:
                    numSegments++;
                    break;
                case 1:
                    numSegments--;
                    break;
                case 2:
                    cnt[pointIndex.get(elements[i])] = numSegments;
                    numPoints++;
                    break;
            }
        }

        return cnt;
    }

    private static void randomizedQuickSort(int[] a, byte[] types, int l, int r) {
        if (l >= r) {
            return;
        }

        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        byte t2 = types[l];
        types[l] = types[k];
        types[k] = t2;

        int m = partition2(a, types, l, r);
        if (m > 0)
            randomizedQuickSort(a, types, l, m - 1);
        randomizedQuickSort(a, types,m + 1, r);
    }

    private static int partition2(int[] a, byte[] types, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
                byte t2 = types[i];
                types[i] = types[j];
                types[j] = t2;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        byte t2 = types[l];
        types[l] = types[j];
        types[j] = t2;
        return j;
    }

    public static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = naiveCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

