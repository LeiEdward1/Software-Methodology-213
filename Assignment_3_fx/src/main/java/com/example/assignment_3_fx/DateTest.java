package com.example.assignment_3_fx;
import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void test_isValid_daysInFeb_nonLeap(){
        Date testFalse1 = new Date("2/29/2003");
        assertFalse(testFalse1.isValid());
    }

    @Test
    public void test_isValid_normalDay(){
        Date testTrue1 = new Date("1/31/1999");
        assertTrue(testTrue1.isValid());
    }

    @Test
    public void test_isValid_normalDay_Accidental_Duplicate(){
        Date testTrue2 = new Date("1/31/1999");
        assertTrue(testTrue2.isValid());
    }

    @Test
    public void test_isValid_tooManyDays_inAMonth(){
        Date testFalse2 = new Date("1/35/2000");
        assertFalse(testFalse2.isValid());
    }

    @Test
    public void test_isValid_invalid_month(){
        Date testFalse3 = new Date("13/5/2003");
        assertFalse(testFalse3.isValid());
    }

    @Test
    public void test_isValid_invalid_days_feb(){
        Date testFalse4 = new Date("2/32/2001");
        assertFalse(testFalse4.isValid());
    }

    @Test
    public void test_isValid_invalid_days_april(){
        Date testFalse5 = new Date("4/31/2002");
        assertFalse(testFalse5.isValid());
    }
}