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
     * @param student to be found
     * @return int i if student is found, return NOT_FOUND otherwise
     */
    private int find(Student student) {
        for (int i = 0; i < size; i++) {
            if (roster[i].equals(student)) {
                return i;
            }
        }
        return NOT_FOUND;
    } //search the given student in roster

    /**
     * Grows the roster array by 4.
     */
    private void grow() {
        Student[] newRoster = new Student[roster.length + 4];
        for (int i = 0; i < size; i++) {
            System.out.println("size: " + size);
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
        if (!this.contains(student) && student.returnYear() && student.yearOrder() != -1) {

            if (size + 1 > roster.length) {
                grow();
            }
            roster[size] = student;
            size++;
            return true;
        } else {
            return false;
        }
    } //add student to end of array

    /**
     * Checks if the student is in the roster array and removes them if they exist.
     *
     * @param student to be removed
     * @return true if the student is removed, false otherwise
     */
    public boolean remove(Student student) {
        int found = this.find(student);
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
     * @param student to be checked
     * @return true if the student is found, false otherwise
     */
    public boolean contains(Student student) {
        for (int i = 0; i < size; i++) {
            if (roster[i].equals(student)) {

                return true;
            }
        }
        return false;
    } //if the student is in roster

    /**
     * Finds the student and changes the major of the student.
     *
     * @param s     is the student being found
     * @param major that the student is changing into
     */
    public void changeMajor(Student s, Major major) {
        int i = find(s);
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
}