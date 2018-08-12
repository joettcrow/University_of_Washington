package edu.uw.jtc.broker;

import edu.uw.ext.framework.order.StopBuyOrder;
import java.util.function.BiPredicate;

/**
 * Took this from Russ as a reference
 * @author jcrowley
 */
public class StopBuyOrderDispatchFilter implements BiPredicate<Integer, StopBuyOrder> {

    /**
     * This is not actually used, but wanted it as a reference
     * @param t the threshold
     * @param o the order to compare
     * @return if the order prices is above the threshold
     */
    public boolean test(Integer t, StopBuyOrder o) {
        return o.getPrice() <= t;
    }

}
