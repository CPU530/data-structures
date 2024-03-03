import java.util.Arrays;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 *
 * @author Robert McVey
 * this program compares two sort methods against each other and prints out the information into a CSV
 */
public class hw6 {

    public static void main(String[] args) throws IOException {

        String[][] data = new String[4][7];
        String csvFileName = "mydata.csv";
        int[] sizes = { 1000,
                2000, 3000,
                4000, 5000,
                6000, 7000, 8000, 9000, 10000, 11000, 12000, 13000, 14000, 15000, 16000, 17000, 18000, 19000, 20000,
                30000, 40000, 60000, 120000, 240000 }; // Array sizes

        File csvFile = new File(csvFileName);
        try {
            FileWriter writer = new FileWriter(csvFile);
            // Write column names
            writer.append("Bubble Sort Start,");
            writer.append("Bubble Sort End,");
            writer.append("Merge Sort Start,");
            writer.append("Merge Sort End,");
            writer.append("Array size\n");
            for (int size : sizes) {

                int[] randomArray = generateRandomArray(size);

                long bubbleSortStartTime = System.currentTimeMillis();
                writer.append(Long.toString(bubbleSortStartTime));
                writer.append(",");

                bubbleSort(randomArray.clone());

                long bubbleSortEndTime = System.currentTimeMillis();
                writer.append(Long.toString(bubbleSortEndTime));
                writer.append(",");

                long mergeSortStartTime = System.currentTimeMillis();
                writer.append(Long.toString(mergeSortStartTime));
                writer.append(",");

                mergeSort(randomArray.clone());

                long mergeSortEndTime = System.currentTimeMillis();
                writer.append(Long.toString(mergeSortEndTime));
                writer.append(",");
                writer.append(String.valueOf(size));
                writer.append("\n");
            }

            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static int[] generateRandomArray(int size) {

        int[] arr = new int[size];

        Random random = new Random();

        for (int i = 0; i < size; i++) {

            arr[i] = random.nextInt(100000);
            // Generate random integers

        }

        return arr;

    }

    private static void bubbleSort(int[] arr) {

        // Bubble sort implementation

        int n = arr.length;

        int temp = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 1; j < (n - i); j++) {

                if (arr[j - 1] > arr[j]) {

                    temp = arr[j - 1];

                    arr[j - 1] = arr[j];

                    arr[j] = temp;

                }

            }

        }

    }

    public static int[] mergeSort(int[] array) {
        int length = array.length;
        if (length <= 1) {

            return array; // Return the sorted array
        }
        if (length > 1) { // Only split if there are more than 1 elements
            int mid = length / 2;
            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, length);

            mergeSort(left); // Sort the left half
            mergeSort(right); // Sort the right half
            merge(array, left, right); // Merge the sorted halves
        }
        return array; // Return the sorted array
    }

    public static void merge(int[] array, int[] leftArray, int[] rightArray) {
        int n1 = leftArray.length;
        int n2 = rightArray.length;

        int i = 0, j = 0, k = 0;

        // Merge the two arrays
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        // Copy remaining elements from leftArray (if any)
        while (i < n1) {
            array[k++] = leftArray[i++];
        }

        // Copy remaining elements from rightArray (if any)
        while (j < n2) {
            array[k++] = rightArray[j++];
        }
    }
}