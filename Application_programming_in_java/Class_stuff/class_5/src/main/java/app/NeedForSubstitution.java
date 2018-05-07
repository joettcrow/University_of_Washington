package app;

import java.math.BigDecimal;

/**
 * @author jcrowley
 */

public class NeedForSubstitution {
    private BigDecimal biggie1;
    private BigDecimal biggie2;
    private BigDecimal biggie3;

    public NeedForSubstitution(BigDecimal biggie3){
        this.biggie3 = biggie3;
        biggie1 = BigDecimal.TEN;
        biggie2 = BigDecimal.TEN;
    }

    public void method( BigDecimal biggie ){
        if (biggie == BigDecimal.TEN){

        }
    }
}
