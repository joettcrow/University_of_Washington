package CP125;

/**
 * @author jcrowley
 */

public abstract class VarArgsStuff {

    /**
     * Hello world!
     *
     */

    abstract void fun( Object obj);
    abstract void fun( Object obj1, Object obj2);
    abstract void fun( Object obj1, Object obj2, Object obj3);
    abstract void fun( Object obj1, Object obj2, Object obj3, Object ... objects);

    public static void main( String[] args ) {
        System.out.println("Hello World!");
    }

}
