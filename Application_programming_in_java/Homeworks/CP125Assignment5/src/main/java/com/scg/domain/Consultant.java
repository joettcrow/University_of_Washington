package com.scg.domain;

import com.scg.util.PersonalName;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This immutable class encapsulates the name of a consultant
 * @author jcrowley
 */
public class Consultant implements Comparable<Consultant>, Serializable {
    private final PersonalName name;

    private static final Logger LOGGER =
            LoggerFactory.getLogger( Consultant.class );

    /**
     * Getter method for the consultant name
     * @return the name as a PersonalName
     */
    public PersonalName getName() {
        return name;
    }

    /**
     * Constructor for the consultant
     * @param name the name for the consultant
     * @throws IllegalArgumentException if the name is null
     */
    public Consultant(PersonalName name ) throws IllegalArgumentException{
        if (name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }
        else {
            this.name = name;
        }
    }

    /**
     * Overrides the toString value to return the name
     * @return the name of the consultant as a String.
     */
    @Override
    public String toString() {
        String str = name.toString();
        return str;
    }

    /**
     * Overrides Object.equals( Object ).
     * Determines whether this Consultant object is equal to a given object.
     * The two objects are equal if they are both concrete Consultant objects,
     * and the corresponding names are equal.
     * @param o The given object.
     * @return True if the this object is equal to the given object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        boolean eq = false;
        if (this == o) eq = true;
        if (o == null || getClass() != o.getClass()) eq = false;
        Consultant that = (Consultant) o;
        eq = Objects.equals(name, that.name);
        return eq;
    }

    /**
     * Hashcode method for a consultant
     * @return the hash code of the consultant
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param consultant the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Consultant consultant) {
        int rcode = Comparator.comparing(Consultant::getName)
                .compare(this,consultant);
        LOGGER.info("did a thing");

        return rcode;
    }

    public void doAThing(){
        LOGGER.info("did a thing");
    }
}
