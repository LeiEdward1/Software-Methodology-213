package classmanager;

/**
 * Extending from the student abstract. Creates a resident student.
 *
 * @author Edward Lei, Andy Zhang
 */
public class Resident extends Student {
    private int scholarship;
    public static final int RESIDENT_TUITION = 12536;
    public static final int UNI_FEE = 3268;
    public static final int PART_RESIDENT_TUITION = 404;
    public static final double PART_UNI_FEE = 2614.4;
    public static final int EXCEED_CREDIT = 16;
    public static final int MIN_CREDIT = 12;

    /**
     * Constructor that sets scholarship given to 0
     */
    public Resident(){
        scholarship = 0;
    }

    /**
     * Constructor that creates a new student object.
     * @param fname first name that is taken in
     * @param lname last name that is taken in
     * @param dob date of birth of the student
     * @param major major of the student
     * @param creditCompleted credits completed by the student
     */
    public Resident(String fname, String lname, String dob, Major major,
                    int creditCompleted){
        super(fname, lname,  dob, major, creditCompleted);
        this.scholarship = 0;
        Resident newStudent = new Resident();
        newStudent.getCredits();
    }

    /**
     * Gets the scholarship
     * @return scholarship of student
     */
    public int getScholarship(){
        return scholarship;
    }

    public void setScholarship(int scholarship){
        this.scholarship = scholarship;
    }

    public double tuitionDue(int creditsEnrolled) {
        double tuition = 0;
        if (creditsEnrolled<MIN_CREDIT) {
            tuition = PART_UNI_FEE + creditsEnrolled * PART_RESIDENT_TUITION;
        }
        else{
            if (creditsEnrolled > EXCEED_CREDIT) {
                tuition = RESIDENT_TUITION + (creditsEnrolled - EXCEED_CREDIT) * PART_RESIDENT_TUITION +
                        UNI_FEE - scholarship;
            }
            else {
                tuition = RESIDENT_TUITION + UNI_FEE - scholarship;
            }
        }
        return tuition;
    } //polymorphism
    public String printTuition(String creditsEnrolled){
        int credits = Integer.parseInt(creditsEnrolled);
        return this.getProfile() + " " + "(Resident)" + "enrolled "
                + creditsEnrolled + " credits" + ":tuition due:" + tuitionDue(credits);
    }
    public boolean isResident(){
        return true;
    } //polymorphism
    public void setScholarship(){

    }
    @Override
    public String toString() {
        return this.getProfile() + " " + this.getMajor() + " " + this.getCredits() + " (" + creditYear() + ")" + "(Resident)";
    }
}
