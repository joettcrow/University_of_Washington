package cp120.class3;

public class Accumulator {
    private int accum;

    public static void main(String[] args) {
        Accumulator accum1 = new Accumulator();
        Accumulator accum2 = new Accumulator();
        Accumulator accum3 = new Accumulator();
    }

    public Accumulator()
    {
        System.out.println("Accumulator Instantiated");
        accum = 0;
    }

    public void add( int val )
    {
        accum = accum + val;
    }

    public int getCurrentValue()
    {
        return accum;
    }
}
