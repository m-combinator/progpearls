import java.util.Random;
import java.io.PrintWriter;
import java.io.IOException;

public class DataGenerator {

    private static final Random random = new Random();
    public int[] generate(int count, int maxNum) {
        
        int [] numbers = new int[count];
        for (int i = 1; i <= count; i++) {
            numbers[i-1] = i;
        }

        for (int i = count+1; i <= maxNum; i++) {
            double rand = random.nextInt(i);
            if (rand < count) {
                int index = random.nextInt(count);
                numbers[index] = i;
            }
        }
        return numbers;
    }

    public static void main(String[]args) throws IOException {
        int [] res = new DataGenerator().generate(1000000, 10000000);
        PrintWriter pw = new PrintWriter("randoms.txt");
        for (int x : res) {
            pw.println(x);
        }
        pw.flush();
        pw.close();
    }
}
