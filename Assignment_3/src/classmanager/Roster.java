package classmanager;
/**
 * Used to create a student array object that holds all of the students
 *
 * @author Edward Lei, Andy Zhang
 */
public class Roster {
    private Student[] roster;
    private int size;
    private static final int NOT_FOUND = -1;
    private static final int INITIAL_CAPACITY = 4;
    private static final int GRADUATION_CRED = 120;

    public static final int RESIDENT_NUM = 1;
    public static final int NON_RESIDENT_NUM = 2;
    public static final int INTERNATIONAL_NUM = 3;
    public static final int TRISTATE_NUM = 4;

    /**
     * Constructor to set the roster to a size of 4 to add students in.
     * Set the integer size variable to 0.
     */
    public Roster() {
        roster = new Student[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Set the roster and size global variables.
     *
     * @param roster
     * @param size
     */
    public Roster(Student[] roster, int size) {
        this.roster = roster;
        this.size = size;
    }

    /**
     * Finds the student in the array and returns its index.
     *
     * @param profile to be found
     * @return int i if student is found, return NOT_FOUND otherwise
     */
    private int find(Profile profile) {
        for (int i = 0; i < size; i++) {
            if (roster[i].getProfile().equals(profile)) {
                return i;
            }
        }
        return NOT_FOUND;
    } //search the given student in roster

    /**
     * Takes the profile of a student and finds it in the roster array.
     * @param profile is being checked in the roster array
     * @return the student, if it is found, null otherwise
     */
    public Student getStudent(Profile profile){
        if(!contains(profile)){
            return null;
        } else {
            return roster[find(profile)];
        }
    }


    /**
     * Grows the roster array by 4.
     */
    private void grow() {
        Student[] newRoster = new Student[roster.length + 4];
        for (int i = 0; i < size; i++) {
            newRoster[i] = roster[i];
        }
        roster = newRoster;
    } //increase the array capacity by 4

    /**
     * Adds a student to the back of the array.
     * Checks if the student exists before adding it to the array.
     * If there is not enough space, the array calls the grow function
     *
     * @param student to be added
     * @return true if the student is added, return false otherwise
     */
    public boolean add(Student student) {
        if (!this.contains(student.getProfile()) && student.returnYear() && student.yearOrder() != -1) {

            if (size + 1 > roster.length) {
                grow();
            }

            if( getStudentType(student)==RESIDENT_NUM){
                Resident students = (Resident)student;
                roster[size] = students;
            }
            else  if( getStudentType(student)==NON_RESIDENT_NUM){
                NonResident students = (NonResident) student;
                roster[size] = students;
            }
            else if(getStudentType(student)==TRISTATE_NUM){
                TriState students = (TriState) student;
                roster[size] = students;
            }
            else{
                International students = (International)student;
                roster[size]=students;
            }
            size++;
            return true;
        } else {
            return false;
        }
    } //add student to end of array

    /**
     * Checks if the student is in the roster array and removes them if they exist.
     *
     * @param profile to be removed
     * @return true if the student is removed, false otherwise
     */
    public boolean remove(Profile profile) {
        int found = this.find(profile);
        if (found != NOT_FOUND) {
            if (size == found + 1) {
                roster[found] = null;
            } else {
                for (int i = found + 1; i < size; i++) {
                    roster[i - 1] = roster[i];
                }
            }
            size--;
            return true;
        } else return false;
    }
    //maintain the order after remove

    /**
     * Checks if the roster array contains the student that is being inputted.
     *
     * @param profile of student to be checked
     * @return true if the student is found, false otherwise
     */
    public boolean contains(Profile profile) {
        for (int i = 0; i < size; i++) {
            if (roster[i].getProfile().equals(profile)) {
                return true;
            }
        }
        return false;
    } //if the student is in roster

    /**
     * Finds the student and changes the major of the student.
     *
     * @param profile is the profile of the student being found
     * @param major that the student is changing into
     */
    public void changeMajor(Profile profile, Major major) {
        int i = find(profile);
        roster[i].changeMajor(major);
    }

    /**
     * Prints roster by alphabetical order, sorted by last name, first name, and then sorted by DOB.
     */
    public void print() {
        System.out.println("*Student roster sorted by last name, first name, DOB **");
        for (int i = 0; i < size; i++) {
            int index = i;
            for (int j = i + 1; j < size; j++) {
                if (roster[index].compareTo(roster[j]) > 0) {
                    index = j;
                }
            }
            Student sortedStudent = roster[index];
            roster[index] = roster[i];
            roster[i] = sortedStudent;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(roster[i].toString());
        }
        System.out.println("* end of list **");
    }

    /**
     * Prints roster sorted by school and then major.
     */
    public void printBySchoolMajor() { //print roster sorted by school & major
        System.out.println("*Student roster sorted by school, major **");
        for (int i = 1; i < size; i++) {
            Student curr = roster[i];
            int currOrder = roster[i].getMajor().getOrder();
            int j = i - 1;
            int jOrder = roster[j].getMajor().getOrder();
            while (j >= 0 && currOrder < jOrder) {
                roster[j + 1] = roster[j];
                j--;
                if (j >= 0) {
                    jOrder = roster[j].getMajor().getOrder();
                }
            }
            roster[j + 1] = curr;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(roster[i].toString());
        }
        System.out.println("* end of list **");
    }

    /**
     * Prints roster by standing using insertion sort for the students.
     * Displays an "end of list" message after it is sorted and printed.
     */
    public void printByStanding() {
        System.out.println("*Student roster sorted by standing **");
        for (int i = 0; i < size; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (roster[minIndex].compareToYear(roster[j]) > 0) {
                    minIndex = j;
                }
            }
            Student sortedStudent = roster[minIndex];
            roster[minIndex] = roster[i];
            roster[i] = sortedStudent;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(roster[i].toString());
        }
        System.out.println("* end of list **");
    }

    /**
     * Prints the students based on the school that is inputted in.
     *
     * @param school the school that is being sorted for
     */
    public void printList(String school) {
        System.out.println("* Students in " + school + " *");
        school = school.toUpperCase();
        for (int i = 0; i < size; i++) {
            int index = i;
            for (int j = i + 1; j < size; j++) {
                if (roster[index].compareTo(roster[j]) > 0) {
                    index = j;
                }
            }
            Student sortedStudent = roster[index];
            roster[index] = roster[i];
            roster[i] = sortedStudent;
        }
        for (int i = 0; i < size; i++) {
            if (roster[i].getSchool().equals(school)) System.out.println(roster[i].toString());
        }
        System.out.println("* end of list **");
    }

    /**
     * Checks if the school has no students in it.
     *
     * @param school is being checked in roster
     * @return true if the school is empty, false otherwisee
     */
    public boolean isSchoolEmpty(String school) {
        school = school.toUpperCase();
        for (int i = 0; i < size; i++) {
            if (roster[i].getSchool().equals(school)) return false;
        }
        return true;
    }

    /**
     * Checks if the roster size is 0.
     *
     * @return true if the size of the roster is 0, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    public void replace(Student student){
        for(int i = 0; i < size; i++){
            if(roster[i].equals(student)){
                roster[i] = student;
            }
        }
    }

    public int getSize(){
        return size;
    }
    public  boolean gradReq(Student student, int creditsEnrolled){
        if(student.getCredits()+creditsEnrolled>=GRADUATION_CRED){
            return true;
        }
        else{
            return false;
        }
    }

    public int getStudentType(Student student){
        if(student instanceof Resident){
            return RESIDENT_NUM;
        } else if(student instanceof TriState){
            return TRISTATE_NUM;
        }
        else if(student instanceof International){
            return INTERNATIONAL_NUM;
        } else if(student instanceof NonResident){
            return NON_RESIDENT_NUM;
        }
        else {
            return -1;
        }
    }

    public void printSemesterEnd(Enrollment enrollment){
        for(int i = 0; i < size; i++){
             EnrollStudent enrollStudent = enrollment.getStudent(roster[i].getProfile());
             if (enrollStudent != null){
                 int creditsEnrolled = enrollStudent.getCreditsEnrolled();
                 semesterEnd(roster[i], creditsEnrolled);
             }
        }
    }
    public void semesterEnd(Student student, int creditsEnrolled){

            if(gradReq(student, creditsEnrolled)){
                Profile newProf = student.getProfile();
                int currCred = student.getCredits();
                Major currMajor = student.getMajor();
                int studentType = getStudentType(student);
                String date = newProf.getDob().toString();
                if(studentType == RESIDENT_NUM) {
                    Resident newStudent = new Resident(newProf.getFname(), newProf.getLname(),
                            date, currMajor, currCred + creditsEnrolled);
                    System.out.println(newStudent);
                }
                else if(studentType == NON_RESIDENT_NUM){
                    NonResident newStudent = new NonResident(newProf.getFname(), newProf.getLname(),
                            date, currMajor, currCred + creditsEnrolled);
                    System.out.println(newStudent);
                }
                else if(studentType == INTERNATIONAL_NUM){
                    International dummy = (International) student;
                    International newStudent = new International(newProf.getFname(), newProf.getLname(),
                            date, student.getMajor(), currCred + creditsEnrolled, dummy.isStudyAbroad());
                    System.out.println(newStudent);
                }
                else{
                    TriState dummy = (TriState)student;
                    TriState newStudent = new TriState(newProf.getFname(), newProf.getLname(),
                            date, student.getMajor(), currCred + creditsEnrolled, dummy.getState());
                    System.out.println(newStudent);
                }
            }


    }
}