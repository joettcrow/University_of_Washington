package classtime.CP125;

/**
 * @author jcrowley
 */

public enum EnumDemo1 {
    APPLE(5),
    ORANGE(22),
    CHERRY(7),
    BANANA(5);

    private int rating;

    private EnumDemo1( int rating ){
        this.rating = rating;
    }

    public int getRating(){
        return rating;
    }

    public void whoAreYou()
    {
        System.out.println("I am a " + this);
    }
}
