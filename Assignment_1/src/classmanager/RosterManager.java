package classmanager;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Used to scan all the text being inputted and return statements
 *
 * @author Edward Lei, Andy Zhang
 */
public class RosterManager {
    Scanner scanner = new Scanner(System.in);

    /**
     * Continuously scans lines until it is stopped processing.
     */
    public void run() {
        boolean running = true;
        Roster roster = new Roster();
        while (running) {
            String currLine = scanner.nextLine();
            while (currLine.equals("")) {
                currLine = scanner.nextLine();
            }
            if (!processArgs(roster, currLine)) {
                running = false;
            }
        }
    }

    /**
     * Checks arguments that are being inputted and sees if they are valid
     *
     * @param roster   used to call add student
     * @param currLine used to parse through what is being typed in
     * @return true if the argument is a valid input, return false otherwise
     */
    public boolean processArgs(Roster roster, String currLine) {
        StringTokenizer st = new StringTokenizer(currLine);
        if (st.hasMoreTokens()) {
            String command = st.nextToken();
            if (command.equals("A")) {
                addStudent(roster, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
            } else if (command.equals("R")) {
                removeStudent(roster, st.nextToken(), st.nextToken(), st.nextToken());
            } else if (command.equals("P")) {
                print(roster);
            } else if (command.equals("PS")) {
                printByStanding(roster);
            } else if (command.equals("PC")) {
                printBySchoolMajor(roster);
            } else if (command.equals("L")) {
                listSchool(roster, st.nextToken());
            } else if (command.equals("C")) {
                changeMajor(roster, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken().toUpperCase());
            } else if (command.equals("Q")) {
                System.out.println("Roster Manager terminated.");
                return false;
            } else {
                System.out.println(command + " is an invalid command!");
            }
        }
        return true;
    }

    /**
     * Adds student to the roster
     *
     * @param roster  is adding the student
     * @param fname   first name being added to student object
     * @param lname   last name being added to student object
     * @param dobArg  date of birth being added to student object
     * @param sMajor  major that is being added to student object
     * @param credits credits being added to student object
     */
    public void addStudent(Roster roster, String fname, String lname, String dobArg, String sMajor, String credits) {
        if (checkDate(dobArg)) {
            Major major = parseMajor(sMajor);
            if (major != null && creditValid(credits)) {
                int creditParsed = Integer.parseInt(credits);
                roster.add(new Student(fname, lname, dobArg, major, creditParsed));
                System.out.println(fname + " " + lname + " " + dobArg + " added to the roster.");
            }
        }
    }

    /**
     * Removes student from the roster, if it exists, prints text statement otherwise.
     *
     * @param roster checking if student is in roster
     * @param fname  first name in student object
     * @param lname  last name in student object
     * @param dobArg date of birth in student object
     */
    public void removeStudent(Roster roster, String fname, String lname, String dobArg) {
        if (!roster.remove(new Student(fname, lname, dobArg))) {
            System.out.println("Student does not exist");
        }
    }

    /**
     * Goes through roster to find the student and change their major
     *
     * @param roster parses for student
     * @param fname  first name of the searched student
     * @param lname  last name of the searched student
     * @param dobArg date of birth of the searched student
     * @param sMajor major that is being changed
     */
    public void changeMajor(Roster roster, String fname, String lname, String dobArg, String sMajor) {
        Major major = parseMajor(sMajor);
        boolean exists = roster.contains(new Student(fname, lname, dobArg));
        if (!checkDate(dobArg)) System.out.println("DOB invalid: " + dobArg + " is not a valid date of birth.");
        else if (!exists) System.out.println("Student does not exist.");
        else if (major == null) System.out.println("Major code invalid: " + sMajor);
        else {
            roster.changeMajor(new Student(fname, lname, dobArg), major);
            System.out.println(fname + " " + lname + " major changed to " + sMajor + ".");
        }
    }

    /**
     * Check if the roster is empty and prints a text statement if it is.
     *
     * @param roster used to check size of roster
     * @return true if it is empty, false otherwise
     */
    public boolean isRosterEmpty(Roster roster) {
        if (roster.isEmpty()) {
            System.out.println("Student roster is empty!");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a school in a roster does nto have any students.
     *
     * @param roster is being checked
     * @param school checking if it has anyone
     * @return true if the school is empty, false otherwise
     */
    public boolean isSchoolEmpty(Roster roster, String school) {
        if (roster.isSchoolEmpty(school)) {
            System.out.println("School roster is empty!");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the school that is inputted is a valid school at Rutgers
     *
     * @param school the school string is being checked for validity
     * @return true if the school is valid, false otherwise
     */
    public boolean isSchoolValid(String school) {
        school = school.toUpperCase();
        if (school.equals("RBS") || school.equals("SAS") || school.equals("SC&I") || school.equals("SOE")) {
            return true;
        } else {
            System.out.println("School doesn't exist: " + school);
            return false;
        }
    }

    /**
     * Prints the roster if the roster is not empty.
     * Prints alphabetically from last name, first name, and then ordered by date of birth.
     *
     * @param roster is being printed
     */
    public void print(Roster roster) {
        if (!isRosterEmpty(roster)) {
            roster.print();
        }
    }

    /**
     * Prints the roster by standing, listed by Freshman, Junior, Senior, Sophomore.
     *
     * @param roster is being sorted in the printByStanding method
     */
    public void printByStanding(Roster roster) {
        if (!isRosterEmpty(roster)) {
            roster.printByStanding();
        }
    }

    /**
     * Calls the printBySchoolMajor to sort the roster array.
     * Prints the roster in order of school and then major.
     *
     * @param roster is being printed
     */
    public void printBySchoolMajor(Roster roster) {
        if (!isRosterEmpty(roster)) {
            roster.printBySchoolMajor();
        }
    }

    /**
     * List every student in a school and print it.
     *
     * @param roster being parsed through to find students in school
     * @param school use school to find every student in school
     */
    public void listSchool(Roster roster, String school) {
        if (isSchoolValid(school) && !isSchoolEmpty(roster, school)) {
            roster.printList(school);
        }
    }

    /**
     * Checks if the credits inputted are valid.
     *
     * @param credits is the string being checked
     * @return true if credits is an integer, and is non-negative, return false otherwise
     */
    private boolean creditValid(String credits) {
        Integer creditCheck = tryParse(credits);
        if (creditCheck == null) {
            System.out.println("Credits completed invalid: not an integer!");
            return false;
        } else if (creditCheck < 0) {
            System.out.println("Credits completed invalid: cannot be negative!");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Used to parse the credits from the creditValid method and return an integer if it is an integer
     *
     * @param text is being parsed for an integer
     * @return an integer if text is an integer, return null otherwise
     */
    private Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Checks if the major is a real major that is in the enum.
     *
     * @param sMajor is being checked for its validity
     * @return the major, return null otherwise
     */
    private Major parseMajor(String sMajor) {
        sMajor = sMajor.toUpperCase();

        if (sMajor.equals(("CS"))) {
            return Major.CS;
        } else if (sMajor.equals("BAIT")) {
            return Major.BAIT;
        } else if (sMajor.equals("MATH")) {
            return Major.MATH;
        } else if (sMajor.equals("ITI")) {
            return Major.ITI;
        } else if (sMajor.equals("EE")) {
            return Major.EE;
        } else {
            return null;
        }
    }

    /**
     * Checks the date string if is a valid date that can be used for a student.
     * If the student is under 16 or the date of birth is invalid.
     *
     * @param date the student is being checked for validity
     * @return true if the date of birth is valid, false otherwise
     */
    private boolean checkDate(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        int[] dateNum = new int[3];
        if (st.countTokens() != 3) {
            System.out.println("DOB invalid: " + date + " is not a valid calendar date.");
            return false;
        }
        Date inputDate = new Date(date);
        if (!inputDate.checkYear()) {
            System.out.println("DOB invalid: " + date + " is younger than 16 years old."); //Change so it doesnt print invalid date and 16 year old
            return false;
        } else if (!inputDate.isValid()) {
            System.out.println("DOB invalid: " + date + " is not a valid calendar date.");
            return false;
        }
        return true;
    }
}
