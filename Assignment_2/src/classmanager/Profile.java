package classmanager;
/**
 * Creates a profile for the student that holds
 * the person's date of birth, last name, and first name.
 *
 * @author Edward Lei, Andy Zhang
 */
public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob; //use the Date class described in (f)

    /**
     * Constructor used to initalize private variables.
     */
    public Profile() {
        lname = "";
        fname = "";
        Date dob = new Date();
    }

    /**
     * Constructor used to set the variables lname, fname, and dob.
     *
     * @param lname is the last name being set
     * @param fname is the first name being set
     * @param dob   is the date of birth being set
     */
    public Profile(String lname, String fname, Date dob) {
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    /**
     * Used to call the isValid method and check if the student's birth year is valid.
     * Student's birth year is valid if they are 16 years old or older.
     *
     * @return true if the student's birth year is valid, false otherwise
     */
    public boolean returnYear() {
        return dob.isValid();
    }
    /**
     * Used to give each year a numerical value.
     *
     * @return integer that is used to order the different years, return -1 if it is not a valid year
     */

    @Override
    /**
     * Returns first name, last name, and dob.
     * @returns the first name, last name, and dob as a formatted string
     */
    public String toString() {
        return fname + " " + lname + " " + dob;
    }

    @Override
    /**
     * Comparison between two different profile objects. This method is not case-sensitive.
     * @param profile is the profile being compared
     * @return true if the two profiles are the same, false otherwise
     */
    public boolean equals(Object profile) {
        if (!(profile instanceof Profile)) {
            return false;
        }
        Profile temp = (Profile) profile;
        if (fname.toUpperCase().equals(temp.fname.toUpperCase()) && lname.toUpperCase().equals
                (temp.lname.toUpperCase()) && dob.equals(temp.dob)) {
            return true;
        }
        return false;
    }

    public String getFname(){
        return fname;
    }

    public String getLname(){
        return lname;
    }

    public Date getDob(){
        return dob;
    }

    @Override
    /**
     * Compares the two profiles by last name, first name, and then birthday. This method is not case-sensitive.
     * @param profile
     * @return 1 if the current profile is alphabetically ahead by last name,
     * then first name, and then by oldest birthday. -1 for the opposite, and 0 if last name,
     * first name and date of birth are all equivalent
     */

    public int compareTo(Profile profile) {
        int lCompare = this.lname.toUpperCase().compareTo(profile.lname.toUpperCase());
        if (lCompare > 0) {
            return 1;
        } else if (lCompare < 0) {
            return -1;
        } else {
            int fCompare = this.fname.toUpperCase().compareTo(profile.fname.toUpperCase());
            if (fCompare > 0) {
                return 1;
            } else if (fCompare < 0) {
                return -1;
            } else {
                int dCompare = this.dob.compareTo(profile.dob);
                if (dCompare > 0) {
                    return 1;
                } else if (dCompare < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}
