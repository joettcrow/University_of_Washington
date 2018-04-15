package com.scg.domain;

import com.scg.util.PersonalName;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class ConsultantTest {
    PersonalName name1 = new PersonalName(
            "Last",
            "first",
            "middle");
    PersonalName name2;

    @Test
    public void makeValidConsutantTest(){
        Consultant cons = new Consultant(name1);
        assertEquals(name1.toString(),cons.getName().toString());
    }

    @Test
    public void makeInvalidConsultantTest(){
        try {
            Consultant cons = new Consultant(name2);
        }catch (IllegalArgumentException exc){
            assertEquals("Name cannot be null",exc.getMessage());
        }
    }

    @Test
    public void consutantToStringTest(){
        Consultant cons = new Consultant(name1);
        assertEquals(name1.toString(),cons.toString());
    }
}