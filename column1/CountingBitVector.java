public class CountingBitVector {

    private int [] vector;
    private static final int BUCKET_WIDTH = 32;
    private static final int SHIFT = 3;
    private static final int MASK = 0x7;
    private int bitCount;

    public CountingBitVector(int bits) {
        bitCount = bits;
        vector = new int[1+(bits >> SHIFT)];
    }

    public void increment(int bit) {
        int position = getPosition(bit);
        int nibble = getNibble(bit);
        clearNibble(bit);
        nibble >>= position*4;
        nibble++;
        vector[getBucket(bit)] |= (nibble << position*4);
    }

    public int getCount(int bit) {
        return getNibble(bit) >> getPosition(bit)*4;
    }

    public int getNibble(int bit) {
        return vector[getBucket(bit)] & (0xf << getPosition(bit)*4);
    }

    public void clearNibble(int bit) {
        vector[getBucket(bit)] ^= getNibble(bit);
    }

    public int getBucket(int bit) {
        return bit >> SHIFT;
    }

    public int getPosition(int bit) {
        return bit & MASK;
    }

    public int bitCount() {
        return bitCount;
    }

    public static void main(String[]args) {
        CountingBitVector cbv = new CountingBitVector(15);
        int [] numbers = {1, 2, 3, 1, 1, 1, 14, 5, 3, 6, 2, 2, 7, 6, 6, 6, 6, 6, 14};
        for (int x : numbers) {
            cbv.increment(x);
        }

        for (int i = 0; i < cbv.bitCount(); i++) {
            System.out.println(i + " " + cbv.getCount(i));
        }
    }
}
