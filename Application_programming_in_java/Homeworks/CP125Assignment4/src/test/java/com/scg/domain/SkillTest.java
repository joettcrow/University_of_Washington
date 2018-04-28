package com.scg.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class SkillTest {

    @Test
    public void getDescriptionTest() {
        String str = "Project Manager";
        assertEquals(str,Skill.PROJECT_MANAGER.getDescription());
    }

    @Test
    public void getRateTest() {
        int rate = 250;
        assertEquals(rate,Skill.PROJECT_MANAGER.getRate());
    }

    @Test
    public void toStringTest() {
        String str = "Project Manager";
        assertEquals(str,Skill.PROJECT_MANAGER.toString());
    }

    @Test
    public void skillEnumTest(){
        Skill [] expValues = {
                Skill.PROJECT_MANAGER,
                Skill.SYSTEM_ARCHITECT,
                Skill.SOFTWARE_ENGINEER,
                Skill.SOFTWARE_TESTER,
                Skill.UNKNOWN_SKILL
        };
        Skill [] actValues = Skill.values();
        assertEquals(expValues.length, actValues.length);
        assertArrayEquals(expValues,actValues);
    }
}