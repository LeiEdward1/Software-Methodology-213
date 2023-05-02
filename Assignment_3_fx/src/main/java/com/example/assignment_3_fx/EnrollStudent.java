package com.example.assignment_3_fx;

/**
 * Used to create a newly enrolled student with the students and credits taken in.
 *
 * @author Edward Lei, Andy Zhang
 */
public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    /**
     * Constructor to get a students profile and set their credits enrolled.
     * @param student is used to find their profile
     * @param creditsEnrolled sets how many credits the student enrolled with
     */
    public EnrollStudent(Student student){
        this.profile = student.getProfile();
        this.creditsEnrolled = 0;
    }

    /**
     * Constructor to get a students profile and set their credits enrolled.
     * @param student is used to find their profile
     * @param creditsEnrolled sets how many credits the student enrolled with
     */
    public EnrollStudent(Student student, int creditsEnrolled){
        this.profile = student.getProfile();
        this.creditsEnrolled = creditsEnrolled;
    }

    /**
     * Constructor to get a students profile and set their credits enrolled.
     * @param student is used to find their profile
     * @param creditsEnrolled sets how many credits the student enrolled with
     */
    public EnrollStudent(String fname, String lname, Date dob){
        this.profile = new Profile(fname, lname, dob);
        this.creditsEnrolled = 0;
    }

    /**
     * Constructor to get a students profile and set their credits enrolled.
     * @param profile is the profile being set
     */
    public EnrollStudent(Profile profile){
        this.profile = profile;
        this.creditsEnrolled = 0;
    }

    /**
     * Getter method for creditsEnrolled
     * @return credits enrolled
     */
    public int getCreditsEnrolled(){
        return creditsEnrolled;
    }
    @Override
    public boolean equals(Object o){
        if (!(o instanceof EnrollStudent)) {
            return false;
        }
        EnrollStudent temp = (EnrollStudent) o;
        if (profile.equals(temp.profile)) {
            return true;
        }
        return false;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setCreditsEnrolled(int creditsEnrolled) {
        this.creditsEnrolled = creditsEnrolled;
    }

    @Override
    public String toString(){
        return profile + ": credits enrolled: " + creditsEnrolled;
    }
}