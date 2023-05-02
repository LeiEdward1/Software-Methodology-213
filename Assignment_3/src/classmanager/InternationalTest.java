package classmanager;

import org.junit.Test;

import static org.junit.Assert.*;

public class InternationalTest {

    @Test
    public void tuitionDueFalse() {
        International international = new International("Andy", "Zhang", "3/14/2003", Major.CS, 42, false);
        double expected = 37587;
        assertEquals(expected,international.tuitionDue(18),0);
    }

    @Test
    public void tuitionDueTrue() {
        International international = new International("Andy", "Zhang", "3/14/2003", Major.CS, 42, true);
        double expected = 5918;
        assertEquals(expected,international.tuitionDue(18),0);
    }
}