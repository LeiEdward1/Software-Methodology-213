package classmanager;
/**
 * Creates a student object that holds a student's profile, major, and credits completed.
 *
 * @author Edward Lei, Andy Zhang
 */
public class Student implements Comparable<Student> {
    private Profile profile;
    private Major major; //Major is an enum type
    private int creditCompleted;

    private static final int FRESHMAN_MIN = 0;
    private static final int SOPHOMORE_MIN = 30;
    private static final int JUNIOR_MIN = 60;
    private static final int SENIOR_MIN = 90;

    /**
     * Constructor to create new profile and set the credits completed to 0.
     */
    public Student() {
        profile = new Profile();
        this.creditCompleted = 0;
    }

    /**
     * Constructor that adds the last name, first name date of birth to the profile object.
     *
     * @param fname added to profile object
     * @param lname added to profile object
     * @param dob   added to profile object
     */
    public Student(String fname, String lname, String dob) {
        this.profile = new Profile(lname, fname, new Date(dob));
        this.major = null;
        this.creditCompleted = 0;
    }

    /**
     * Constructor to set profile, major, and creditCompleted
     *
     * @param fname           added to profile object
     * @param lname           added to profile object
     * @param dob             added to profile object
     * @param major           set major
     * @param creditCompleted set creditCompleted
     */
    public Student(String fname, String lname, String dob, Major major, int creditCompleted) {
        this.profile = new Profile(lname, fname, new Date(dob));
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    @Override
    /**
     * Returns a string containing first name, last name, date of birth,
     * major, number of credits completed, and their standing (freshman/
     * sophomore/junior/senior)
     */
    public String toString() {
        return profile + " " + major.getMajor() + " " + creditCompleted + " (" + creditYear() + ")";
    }

    @Override
    /** Checks if two student objects are equivalent based on their profile using compareTo().
     * @param Any object, the method checks if it's a student object or not before proceeding
     * @return true if the students are equal and false otherwise
     */
    public boolean equals(Object o) {
        if (!(o instanceof Student)) {
            return false;
        }
        Student x = (Student) o;

        return this.profile.equals(x.profile);
    }

    @Override
    /**
     * Compares two students based on their profile. This is based on last name, first name,
     * and then oldest date of birth. This method is not case-sensitive.
     * @param Student object being compared to.
     */
    public int compareTo(Student s) {
        return this.profile.compareTo(s.profile);
    }

    /**
     * Used to order the years. The order is freshman, junior, senior, sophomore.
     *
     * @param s compared to the other student
     * @return 1 when the current student has a higher order than the inputted student,
     * return 0 if the years are the same, and -1 otherwise
     */
    public int compareToYear(Student s) { //Ordering is freshman, junior, senior, sophomore
        if (this.yearOrder() > s.yearOrder()) {
            return 1;
        } else if (this.yearOrder() == s.yearOrder()) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * Checks the credits completed and determines if the student is a freshman, sophomore, junior, or senior.
     *
     * @return A string containing "Freshman"/"Sophomore"/"Junior"/"Senior", or null if the credits are negative
     */
    public String creditYear() {
        if (creditCompleted < SOPHOMORE_MIN && creditCompleted >= FRESHMAN_MIN) {
            return "Freshman";
        } else if (creditCompleted >= SOPHOMORE_MIN && creditCompleted < JUNIOR_MIN) {
            return "Sophomore";
        } else if (creditCompleted >= JUNIOR_MIN && creditCompleted < SENIOR_MIN) {
            return "Junior";
        } else if (creditCompleted >= SENIOR_MIN) {
            return "Senior";
        } else {
            return "cannot be negative!";
        }
    }

    /**
     * Used to give each year a numerical value.
     *
     * @return integer that is used to order the different years, return -1 if it is not a valid year
     */
    public int yearOrder() {
        String year = this.creditYear();

        if (year.equals("Freshman")) {
            return 0;
        } else if (year.equals("Junior")) {
            return 1;
        } else if (year.equals("Senior")) {
            return 2;
        } else if (year.equals("Sophomore")) {
            return 3;
        } else {
            return -1;
        }
    }

    /**
     * Returns the profile of the student
     *
     * @return profile of student
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Returns the major of the student
     *
     * @return major of student
     */
    public Major getMajor() {
        return this.major;
    }

    /**
     * Changes the major of the student.
     *
     * @param m is the new major
     */
    public void changeMajor(Major m) {
        this.major = m;
    }

    /**
     * Returns credits completed by student.
     *
     * @return integer of credits completed
     */
    public int getCredits() {
        return this.creditCompleted;
    }

    /**
     * Returns true if the date of birth is valid.
     * Returns false if the date of birth is invalid.
     *
     * @return true if the date of birth is valid, false otherwise.
     */
    public boolean returnYear() {
        return this.profile.returnYear();
    }

    /**
     * Returns what school the student is in, either RBS, SAS, SOE, or SC&I
     *
     * @return string of which school the student is in.
     */
    public String getSchool() {
        return this.major.getSchool();
    }

    /**
     * Test bed method for compareTo method
     *
     * @param args
     */
    public static void main(String[] args) {
        Student differentMajorCredsA = new Student("John", "Doe", "1/1/2002", Major.CS, 25);
        Student differentMajorCredsB = new Student("John", "Doe", "1/1/2002", Major.EE, 100);

        Student differentLastNameA = new Student("Mike", "Hawk", "2/2/2001", Major.CS, 100);
        Student differentLastNameB = new Student("Mike", "Haw", "2/2/2001", Major.CS, 100);

        Student differentEverythingA = new Student("Tayley", "Ham", "1/10/2000", Major.EE, 100);
        Student differentEverythingB = new Student("Haley", "Tam", "2/25/1999", Major.BAIT, 40);

        Student differentDobA = new Student("Jane", "Doe", "1/2/2001", Major.CS, 100);
        Student differentDobB = new Student("Jane", "Doe", "1/2/2001", Major.CS, 100);

        Student differentFirstNameA = new Student("Sam", "Doe", "1/1/2000", Major.EE, 100);
        Student differentFirstNameB = new Student("Jane", "Doe", "1/1/2000", Major.EE, 100);

        Student differentFirstNameC = new Student("Hello", "World", "1/2/1999", Major.BAIT, 50);
        Student differentFirstNameD = new Student("World", "World", "1/2/1999", Major.BAIT, 50);

        System.out.println("Test Case 1 -- Different Major and Creds" +
                           "\nStudent 1: " + differentMajorCredsA +
                           "\nStudent 2: " + differentMajorCredsB +
                           "\nCompareTo: " +differentMajorCredsA.compareTo(differentMajorCredsB));
        System.out.println();

        System.out.println("Test Case 2 -- Different Last names" +
                          "\nStudent 1: " + differentLastNameA +
                          "\nStudent 2: " + differentLastNameB +
                          "\nCompareTo: " +differentLastNameA.compareTo(differentLastNameB));
        System.out.println();

        System.out.println("Test Case 3 -- Different Everything" +
                           "\nStudent 1: " + differentEverythingA +
                           "\nStudent 2: " + differentEverythingB +
                           "\nCompareTo: " +differentEverythingA.compareTo(differentEverythingB));
        System.out.println();

        System.out.println("Test Case 4 -- Different Date of Birth" +
                           "\nStudent 1: " + differentDobA +
                           "\nStudent 2: " + differentDobB +
                           "\nCompareTo: " +differentDobA.compareTo(differentDobB));
        System.out.println();

        System.out.println("Test Case 5 -- Different First Name" +
                           "\nStudent 1: " + differentFirstNameA +
                           "\nStudent 2: " + differentFirstNameB +
                           "\nCompareTo: " +differentFirstNameA.compareTo(differentFirstNameB));
        System.out.println();

        System.out.println("Test Case 6 -- Different First Name" +
                           "\nStudent 1: " + differentFirstNameC +
                           "\nStudent 2: " + differentFirstNameD +
                           "\nCompareTo: " +differentFirstNameC.compareTo(differentFirstNameD));
        System.out.println();

    }
}