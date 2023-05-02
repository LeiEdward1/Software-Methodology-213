package classmanager;

/**
 * @author Edward Lei, Andy Zhang
 *
 * Extends student and creates a non-resident student object
 */
public class NonResident extends Student{
    public static final int RESIDENT_TUITION = 12536;
    public static final int NON_RESIDENT_TUITION = 29737;
    public static final int HEALTH_INSURANCE = 2650;
    public static final int UNI_FEE = 3268;
    public static final int PART_RESIDENT_TUITION = 404;
    public static final int PART_NON_RESIDENT_TUITION = 966;
    public static final double PART_UNI_FEE = 2614.4;
    public static final int TUITION_DISCOUNT_NY = 4000;
    public static final int TUITION_DISCOUNT_CT = 5000;
    public static final int EXCEED_CREDIT = 16;
    public static final int MIN_CREDIT = 12;

    public NonResident(){
        super();
    }
    public NonResident(String fname, String lname, String dob, Major major, int creditCompleted){
        super(fname,lname,dob,major,creditCompleted);
    }

    public double tuitionDue(int creditsEnrolled) {
        double tuition = 0;
        if(creditsEnrolled>EXCEED_CREDIT){
            tuition = NON_RESIDENT_TUITION + UNI_FEE  +
                    (creditsEnrolled - EXCEED_CREDIT ) * PART_NON_RESIDENT_TUITION;
        }
        else if(creditsEnrolled<MIN_CREDIT){
            tuition = NON_RESIDENT_TUITION + UNI_FEE + HEALTH_INSURANCE;
        }
        else {
            tuition = creditsEnrolled * PART_NON_RESIDENT_TUITION + PART_UNI_FEE;
        }
        return tuition;
    }
    //polymorphism

    public boolean isResident(){
        return false;
    } //polymorphism
    public String printTuition(String creditsEnrolled){
        int credits = Integer.parseInt(creditsEnrolled);
        return this.getProfile() + " " + "(Non-Resident)" + "enrolled "
                + creditsEnrolled + " credits" + ":tuition due:" + tuitionDue(credits);
    }
    @Override
    public String toString(){
        return this.getProfile() + " " + this.getMajor() + " " + this.getCredits() + " (" + creditYear() +
                ")"+ " "+ "Resident)";
    }
}
