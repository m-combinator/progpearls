
public class BitVector {

    private int [] vector;
    private static final int BUCKET_WIDTH = 32;
    private long bitCount;

    public BitVector(long bitCount) {
        this.bitCount = bitCount;
        int size = 1+(int)(bitCount/BUCKET_WIDTH);
        vector = new int[size];
    }

    private void checkValidBit(long bit) {
        if (bit < 0 || bit >= bitCount) {
            throw new IndexOutOfBoundsException("Bit index is invalid.");
        }
    }

    public boolean testBit(long bit) {
        checkValidBit(bit);
        int bucket = getBucket(bit);
        int position = getPosition(bit);
        return ((1 << position) & vector[bucket]) != 0;
    }

    public void setBit(long bit) {
        checkValidBit(bit);
        int bucket = getBucket(bit);
        int position = getPosition(bit);
        vector[bucket] |= (1 << position);
    }

    public void clearBit(long bit) {
        checkValidBit(bit);
        int bucket = getBucket(bit);
        int position = getPosition(bit);
        int mask = 0xFFFFFFFF ^ (1 << position);
        vector[bucket] &= mask;
    }

    public void flipBit(long bit) {
        checkValidBit(bit);
        int bucket = getBucket(bit);
        int position = getPosition(bit);
        vector[bucket] ^= (1 << position);
    }

    public long bitCount() {
        return bitCount;
    }

    private static int getBucket(long bit) {
        return (int)(bit/BUCKET_WIDTH);
    }

    private static int getPosition(long bit) {
        return (int)(bit%BUCKET_WIDTH);
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
        for (int i = 0; i < bv.bitCount(); i++) {
            if (bv.testBit(i)) {
                System.out.println(i);
            }
        }
    }
}
