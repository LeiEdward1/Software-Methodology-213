package classmanager;
/**
 * Enum that holds the different majors.
 *
 * @author Edward Lei, Andy Zhang
 */
public enum Major {
    BAIT, CS, MATH, ITI, EE;

    /**
     * Used to sort major by integer values.
     *
     * @return integer that is used to identify major, returns -1 if the major doesn't exist
     */
    public int getOrder() {
        switch (this) {
            case BAIT:
                return 0;

            case CS:
                return 1;

            case MATH:
                return 2;

            case ITI:
                return 3;

            case EE:
                return 4;

            default:
                return -1;
        }
    }

    /**
     * Used in RosterManager to print the major the student is in
     *
     * @return string to print out what major the student is declared for, returns null otherwise
     */
    public String getMajor() {
        switch (this) {
            case BAIT:
                return "(33:136 BAIT RBS)";

            case CS:
                return "(01:198 CS SAS)";

            case MATH:
                return "(01:640 MATH SAS)";

            case ITI:
                return "(04:547 ITI SC&I)";

            case EE:
                return "(14:332 EE SOE)";

            default:
                return null;
        }
    }

    /**
     * Identifies the school each major is in
     *
     * @return string which identifies the school, return null otherwise
     */
    public String getSchool() {
        switch (this) {
            case BAIT:
                return "RBS";

            case CS:
            case MATH:
                return "SAS";

            case ITI:
                return "SC&I";

            case EE:
                return "SOE";

            default:
                return null;
        }
    }
}
