package app;

/**
 * @author jcrowley
 */

public class Dummy {

    private int count;

    public Dummy(int count){
        this.count = count;
    }

    public boolean equals(Object obj){
        boolean rval = false;
        if (obj == null){
            rval = false;
        }
        else if (this == obj){
            rval = true;
        }
        else if (!(obj instanceof Dummy)){
            rval = false;
        }
        else {
            Dummy that = (Dummy)obj;
            rval = this.count == that.count;
        }
        return rval;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
