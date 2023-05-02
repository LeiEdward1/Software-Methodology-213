package com.example.assignment_3_fx;

import org.junit.Test;

import static org.junit.Assert.*;

public class RosterTest {

    @Test
    public void add_international() {
        Roster roster = new Roster();
        International international = new International("Andy", "Zhang", "3/14/2003", Major.CS, 42, false);
        assertTrue(roster.add(international));
    }

    @Test
    public void add_duplicate_international() {
        Roster roster = new Roster();
        International international = new International("Andy", "Zhang", "3/14/2003", Major.CS, 42, false);
        International international2 = new International("Andy", "Zhang", "3/14/2003", Major.CS, 42, true);
        roster.add(international);
        assertFalse(roster.add(international2));
    }

    @Test
    public void add_tristate() {
        Roster roster = new Roster();
        TriState tristate = new TriState("Andy", "Zhang", "3/14/2003", Major.CS, 42, "NY");
        assertTrue(roster.add(tristate));
    }

    @Test
    public void add_tristate_different_state() {
        Roster roster = new Roster();
        TriState tristate = new TriState("Andy", "Zhang", "3/14/2003", Major.CS, 42, "NY");
        TriState tristate2 = new TriState("Andy", "Zhang", "3/14/2003", Major.CS, 42, "CT");
        roster.add(tristate);
        assertFalse(roster.add(tristate2));
    }
}