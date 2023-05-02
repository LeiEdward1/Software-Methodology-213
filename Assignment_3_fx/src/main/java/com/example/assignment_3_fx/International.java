package com.example.assignment_3_fx;

/**
 * @author Edward Lei, Andy Zhang
 *
 * Extends NonResident and creates an international student object.
 */
public class International extends NonResident {
    private boolean isStudyAbroad;
    public static final int NON_RESIDENT_TUITION = 29737;
    public static final int HEALTH_INSURANCE = 2650;
    public static final int UNI_FEE = 3268;
    public static final int PART_NON_RESIDENT_TUITION = 966;
    public static final double PART_UNI_FEE = 2614.4;
    public static final int TUITION_DISCOUNT_NY = 4000;
    public static final int TUITION_DISCOUNT_CT = 5000;
    public static final int EXCEED_CREDIT = 16;
    public static final int MIN_CREDIT = 12;
    public International(){
        isStudyAbroad = false;
    }

    public International(String fname, String lname, String dob,
                         Major major, int creditCompleted, boolean isStudyAbroad){
        super(fname,lname,dob,major,creditCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }
    public double tuitionDue(int creditEnrolled){
        double tuition = 0;
        if(isStudyAbroad){
                tuition = HEALTH_INSURANCE + UNI_FEE;
        }
        else{
            if(creditEnrolled>EXCEED_CREDIT){
                tuition = NON_RESIDENT_TUITION + UNI_FEE + HEALTH_INSURANCE +
                        (creditEnrolled - EXCEED_CREDIT ) * PART_NON_RESIDENT_TUITION;
            }
            else if(getCredits()<MIN_CREDIT){
                tuition = NON_RESIDENT_TUITION + UNI_FEE + HEALTH_INSURANCE;
            }
            else {
                tuition = creditEnrolled * PART_NON_RESIDENT_TUITION + PART_UNI_FEE;
            }
        }
        return tuition;
    }
    public String printTuition(String creditsEnrolled){
        int credits = Integer.parseInt(creditsEnrolled);
        return this.getProfile() + " " + "(International Student" +printStudyAbroad() + "enrolled "
                + creditsEnrolled + " credits" + ":tuition due:" + "$" + tuitionDue(credits);
    }
    public boolean isStudyAbroad(){
        return isStudyAbroad;
    }
    public String printStudyAbroad(){
        if(isStudyAbroad){
            return ":study abroad)";
        }
        else{
            return ")";
        }
    }
    @Override
    public String toString(){
        return this.getProfile() + " " + this.getMajor().getMajor() + " " + this.getCredits() + " (" + creditYear() +
                ")"+ " "+ "(International" + printStudyAbroad();
    }
}
