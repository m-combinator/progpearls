
public class BitVector {

    private int [] vector;
    private static final int BUCKET_WIDTH = 32;
    private static final int SHIFT = 5;
    private static final int MASK = 0x1f;
    private int bitCount;

    public BitVector(int bitCount) {
        this.bitCount = bitCount;
        int size = 1+getBucket(bitCount);
        vector = new int[size];
    }

    private void checkValidBit(int bit) {
        if (bit < 0 || bit >= bitCount) {
            throw new IndexOutOfBoundsException("Bit index is invalid.");
        }
    }

    public boolean testBit(int bit) {
        checkValidBit(bit);
        int bucket = getBucket(bit);
        int position = getPosition(bit);
        return ((1 << position) & vector[bucket]) != 0;
    }

    public void setBit(int bit) {
        checkValidBit(bit);
        int bucket = getBucket(bit);
        int position = getPosition(bit);
        vector[bucket] |= (1 << position);
    }

    public void clearBit(int bit) {
        checkValidBit(bit);
        int bucket = getBucket(bit);
        int position = getPosition(bit);
        vector[bucket] &= ~(1 << position);
    }

    public void flipBit(int bit) {
        checkValidBit(bit);
        int bucket = getBucket(bit);
        int position = getPosition(bit);
        vector[bucket] ^= (1 << position);
    }

    public int bitCount() {
        return bitCount;
    }

    private static int getBucket(int bit) {
        return (bit >> SHIFT);
    }

    private static int getPosition(int bit) {
        return (bit & MASK);
    }

    public static void main(String[]args) {
        BitVector bv = new BitVector(64);
        bv.setBit(63);
        bv.setBit(5);
        bv.setBit(1);
        bv.setBit(22);
        bv.setBit(15);
        bv.clearBit(22);
        bv.flipBit(8);
        bv.setBit(33);
        bv.flipBit(22);
        for (int i = 0; i < bv.bitCount(); i++) {
            if (bv.testBit(i)) {
                System.out.println(i);
            }
        }
    }
}
