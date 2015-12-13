import java.io.*;

public class BitSort {
    
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("randoms.txt"));
        BitVector bv = new BitVector(10000001);
        String str;
        while ((str=br.readLine()) != null) {
            int x = Integer.parseInt(str);
            bv.setBit(x);
        }

        PrintWriter pw = new PrintWriter("sorted.txt");
        for (int i = 0; i < bv.bitCount(); i++) {
            if (bv.testBit(i)) {
                pw.println(i);
            }
        }
        pw.flush();
        pw.close();
    }
}
