package com.example.assignment_3_fx;

/**
 * @author Edward Lei, Andy Zhang
 *
 * Enrolls a student into the enroll student array.
 */
public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;
    private static final int INVALID_SCHOLARSHIP = -1;
    private static final int MAX_SCHOLARSHIP_RES = 10000;
    private static final int MIN_SCHOLARSHIP = 0;

    public static final int RESIDENT_NUM = 1;
    public static final int NON_RESIDENT_NUM = 2;
    public static final int INTERNATIONAL_NUM = 3;
    public static final int TRISTATE_NUM = 4;
    private static final int INITIAL_CAPACITY = 4;

    /**
     * Constructor to set the enroll student array to inital capacity and size to 0;
     */
    public Enrollment(){
        enrollStudents = new EnrollStudent [INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Another constructor to set enrollStudents array
     * @param enrollStudents used to set instance variable
     */
    public Enrollment(EnrollStudent[]enrollStudents){
        this.enrollStudents = enrollStudents;
    }

    /**
     * Another constructor to set the size of the instance variable.
     * @param enrollStudents used to set instance
     * @param size used to set instance
     */
    public Enrollment(EnrollStudent[] enrollStudents, int size){
        this.enrollStudents = enrollStudents;
        this.size = size;
    }

    /**
     * Adds a student into the enrollStudent array.
     * Adds the student at the end and shifts everything.
     * @param enrollStudent is adding a new student into the enrollStudent array and sets credits.
     *
     */
    public void add(EnrollStudent enrollStudent){
        if(contains(enrollStudent)){
            EnrollStudent dummy = getStudent(enrollStudent.getProfile());
            dummy.setCreditsEnrolled(enrollStudent.getCreditsEnrolled());
        } else{
            size++;
            if(size>enrollStudents.length){
                grow();
            }
            enrollStudents[size-1] = enrollStudent;
        }


    }

    /**
     * Removes student
     * @param enrollStudent used to check where to remove student
     */
    public void remove(EnrollStudent enrollStudent){
        if (!contains(enrollStudent)){
            return;
        }

        int indexRemove = findStudent(enrollStudent);
        enrollStudents[indexRemove] = enrollStudents[size-1];
        enrollStudents[size-1] = null;
        size--;
    }

    /**
     * Prints enrollment.
     */
    public String printEnrollment(){
        String prints = "";
        for (int i = 0; i < size; i++){
            prints+=enrollStudents[i]+"\n";
        }
        return prints;
    }

    /**
     * Returns where student is on the enrollStudent array.
     * @param profile used to search profile.
     * @return the student that is found
     */
    public EnrollStudent getStudent(Profile profile){
        EnrollStudent dummy = new EnrollStudent(profile);
        for  (EnrollStudent student: enrollStudents){
            if (dummy.equals(student)){
                return student;
            }
        }

        return null;
    }

    /**
     * Checks if a student is in the enrollStudent array.
     * @param enrollStudent is being checked
     * @return true if the student is there, false otherwise.
     */
    public boolean contains(EnrollStudent enrollStudent){
        if(findStudent(enrollStudent)==-1){
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Finds the student and returns the location
     * @param enrollStudent used to check array
     * @return integer location, -1 otherwise
     */
    private int findStudent(EnrollStudent enrollStudent){
        for(int i = 0; i < size; i++){
            if(enrollStudents[i].equals(enrollStudent)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Grows the array by 4.
     */
    private void grow() {
        EnrollStudent[] newList = new EnrollStudent[enrollStudents.length+4];
        for (int i = 0; i < size-1; i++) {
            //System.out.println("size: " + size);
            newList[i] = enrollStudents[i];
        }
        enrollStudents = newList;
    } //increase the array capacity by 4


    /**
     * Sorts the enrollment by credits.
     * @param roster is being sorted.
     */
    public String sortCreds(Roster roster){
        String prints = "";
        for (int i = 0; i < size; i++) {
            int index = i;
            for (int j = i + 1; j < size; j++) {
                if (enrollStudents[index].getCreditsEnrolled()>enrollStudents[j].getCreditsEnrolled()) {
                    index = j;
                }
            }
            EnrollStudent sortedStudent = enrollStudents[index];
            enrollStudents[index] = enrollStudents[i];
            enrollStudents[i] = sortedStudent;
        }
        for(int i = 0;i<size;i++){
            Student dummy = roster.getStudent(enrollStudents[i].getProfile());
            String credits = Integer.toString(enrollStudents[i].getCreditsEnrolled());
            if (roster.getStudentType(dummy)==RESIDENT_NUM){
                Resident dumDum = (Resident) dummy;
                prints+=dumDum.printTuition(credits)+"\n";
              //System.out.println(dumDum.printTuition(credits));
            } else if (roster.getStudentType(dummy)==NON_RESIDENT_NUM){
                NonResident dumDum = (NonResident) dummy;
                prints+=dumDum.printTuition(credits)+"\n";
                System.out.println(dumDum.printTuition(credits));
            } else if (roster.getStudentType(dummy)==TRISTATE_NUM){
                TriState dumDum = (TriState) dummy;
                prints+=dumDum.printTuition(credits)+"\n";
                System.out.println(dumDum.printTuition(credits));
            } else if (roster.getStudentType(dummy)==INTERNATIONAL_NUM){
                International dumDum = (International) dummy;
                prints+=dumDum.printTuition(credits)+"\n";
                System.out.println(dumDum.printTuition(credits));
            }

        }
        return prints;
    }

    public boolean isEmpty(){
        if (size<=0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Prints the entire enrollStudents.
     */
    public String print() {
        String prints = "";
        for(int i = 0;i<size;i++){
            prints+=enrollStudents+ "\n";
            //System.out.print(enrollStudents+" ");
        }
        return prints;
    }
}