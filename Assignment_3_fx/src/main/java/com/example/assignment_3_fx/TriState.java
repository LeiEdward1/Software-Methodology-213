package com.example.assignment_3_fx;

/**
 * @author Edward Lei, Andy Zhang
 *
 * Extends a NonResident class and creates an object for a Tri-state student
 */
public class TriState extends NonResident{
    private String state;
    public static final int NON_RESIDENT_TUITION = 29737;
    public static final int HEALTH_INSURANCE = 2650;
    public static final int UNI_FEE = 3268;
    public static final int PART_NON_RESIDENT_TUITION = 966;
    public static final double PART_UNI_FEE = 2614.4;
    public static final int TUITION_DISCOUNT_NY = 4000;
    public static final int TUITION_DISCOUNT_CT = 5000;
    public static final int EXCEED_CREDIT = 16;
    public static final int MIN_CREDIT = 12;

    public TriState(String fname, String lname, String dob, Major major, int creditCompleted, String state){
        super(fname,lname,dob,major,creditCompleted);
        this.state = state;
    }

    public double tuitionDue(int creditsEnrolled) {
        double tuition = 0;
        if (state.equalsIgnoreCase("CT")) {
            if (creditsEnrolled < MIN_CREDIT) {
                tuition = NON_RESIDENT_TUITION + (creditsEnrolled - EXCEED_CREDIT) * PART_NON_RESIDENT_TUITION +
                        UNI_FEE - TUITION_DISCOUNT_CT;
            } else {
                tuition = NON_RESIDENT_TUITION + UNI_FEE - TUITION_DISCOUNT_CT;
            }
        }
        else{
            if (creditsEnrolled < MIN_CREDIT) {
                tuition = NON_RESIDENT_TUITION + (creditsEnrolled - EXCEED_CREDIT) * PART_NON_RESIDENT_TUITION +
                        UNI_FEE - TUITION_DISCOUNT_NY;
            } else {
                tuition = NON_RESIDENT_TUITION + UNI_FEE - TUITION_DISCOUNT_NY;
            }
        }
        return tuition;
    } //polymorphism

    public boolean isResident(){
        return false;
    } //polymorphism

    public String getState() { return state; }

    @Override
    public String toString() {
        state = state.toUpperCase();
        return this.getProfile() + " " + this.getMajor().getMajor() + " " + this.getCredits() + " (" + creditYear() +
                ")"+ " " + "(Tri-state" + " "+state + ")";
    }
    public String printTuition(String creditsEnrolled){
        int credits = Integer.parseInt(creditsEnrolled);
        state = state.toUpperCase();
        return this.getProfile() + " " + "(Tri-State"  + " "+state + ")"+ "enrolled "
                + creditsEnrolled + " credits" + ":tuition due:" + "$"+tuitionDue(credits);
    }
}
