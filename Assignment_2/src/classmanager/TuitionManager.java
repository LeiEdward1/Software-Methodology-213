package classmanager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Used to scan all the text being inputted and return statements
 *
 * @author Edward Lei, Andy Zhang
 */
public class TuitionManager {
    public static final int ADD_NON_TRISTATE_MIN_ARGS = 5;
    public static final int ADD_TRISTATE_MIN_ARGS = 6;
    public static final int REMOVE_MIN_ARGS = 3;
    public static final int LIST_MIN_ARGS = 1;

    public static final int ENROLL_MIN_ARGS = 4;
    public static final int DROP_MIN_ARGS = 3;
    public static final int AWARD_MIN_ARGS = 4;

    public static final int RESIDENT_NUM = 1;
    public static final int NON_RESIDENT_NUM = 2;
    public static final int INTERNATIONAL_NUM = 3;
    public static final int TRISTATE_NUM = 4;

    public static final int MIN_CREDS = 3;
    public static final int MAX_CREDS = 24;
    public static final int MIN_INTERNATIONAL_CREDS = 12;
    public static final int MAX_ABROAD_CREDS = 12;

    private static final int MAX_SCHOLARSHIP_RES = 10000;
    private static final int MIN_SCHOLARSHIP = 0;



    Scanner scanner = new Scanner(System.in);

    /**
     * Continuously scans lines until it is stopped processing.
     */
    public void run() {
        boolean running = true;
        Roster roster = new Roster();
        Enrollment enrollment = new Enrollment();
        System.out.println("Tuition Manager running...");
        while (running) {
            String currLine = scanner.nextLine();
            while (currLine.equals("")) {
                currLine = scanner.nextLine();
            }
            if (!processArgs(roster, enrollment,currLine)) {
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
    public boolean processArgs(Roster roster, Enrollment enrollment, String currLine) {
        StringTokenizer st = new StringTokenizer(currLine);
        if (st.hasMoreTokens()) {
            String command = st.nextToken();

            if (command.charAt(0)=='A') {
                if(!checkNumArgs(command, st)){
                    return true;
                }
                addStudent(roster, command, st);//command, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
            } else if (command.equals("R")) {
                if(!checkNumArgs(command, st)){
                    return true;
                }
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
            } else if (command.equals("LS")) {
                //load student roster from file
                File file = new File(st.nextToken());
                try {
                    Scanner sc = new Scanner(file);
                    while(sc.hasNextLine()){
                        StringTokenizer line = new StringTokenizer(sc.nextLine(), ",");
                        String studentType = line.nextToken();
                        addStudent(roster, "A" + studentType, line);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (command.equals("E")) {
                if(!checkNumArgs(command, st)){
                    return true;
                }
                enrollStudent(roster, enrollment, st);
            } else if (command.equals("D")) {
                if(!checkNumArgs(command, st)){
                    return true;
                }
                dropStudent(roster, enrollment, st.nextToken(), st.nextToken(), st.nextToken());
            } else if (command.equals("S")){
                if(!checkNumArgs(command, st)){
                    return true;
                }
                scholarshipAwarded(roster, enrollment, st);
            } else if (command.equals("SE")){
                System.out.println("Credit completed has been updated.");
                roster.printSemesterEnd(enrollment);

            } else if (command.equals("PT")) {
                System.out.println("** Tuition due **");
                enrollment.sortCreds(roster);
                System.out.println("* end of tuition due *");
            } else if (command.equals("PE")){
                enrollment.printEnrollment();
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
     * Checks data tokens that are being inputted and sees if there is enough data tokens
     *
     * @param command the first data token which differentiates the operation performed
     * @param st the data tokens delimtied by spaces
     * @return true if there are enough data tokens, return false otherwise
     */
    private boolean checkNumArgs(String command, StringTokenizer st){
        int numArgs = st.countTokens();

        if (command.equals("AR") ||
            command.equals("AN") ||
            command.equals("AI")){

            return (numArgs >= ADD_NON_TRISTATE_MIN_ARGS);
        } else if (command.equals("AT")){

            return (numArgs >= ADD_TRISTATE_MIN_ARGS);
        } else if (command.equals("R")){

            return (numArgs >= REMOVE_MIN_ARGS);
        } else if (command.equals("L")){

            return (numArgs >= LIST_MIN_ARGS);
        } else if (command.equals("E")){

            return (numArgs >= ENROLL_MIN_ARGS);
        } else if (command.equals("D")){

            return (numArgs >= DROP_MIN_ARGS);
        } else if (command.equals("S")){

            return (numArgs >= AWARD_MIN_ARGS);
        } else {
            return false;
        }
    }

    /**
     * Adds student to the roster
     *
     * @param roster  is adding the student
     */
    public void addStudent(Roster roster, String command, StringTokenizer st){//String command, String fname, String lname, String dobArg, String sMajor, String credits) {
        String fname = st.nextToken();
        String lname = st.nextToken();
        String dobArg = st.nextToken();
        String sMajor = st.nextToken();
        String credits = st.nextToken();

        if (checkDate(dobArg)){
            Major major = parseMajor(sMajor);
            if (major != null && creditValid(credits)) {
                int creditParsed = Integer.parseInt(credits);
                if(command.length() !=2){
                    return;
                }
                char studentType = command.charAt(1);
                if (studentType == 'R'){
                    roster.add(new Resident(fname, lname, dobArg, major, creditParsed));
                } else if (studentType == 'N'){
                    roster.add(new NonResident(fname, lname, dobArg, major, creditParsed));
                } else if (studentType == 'T'){
                    String state = st.nextToken();
                    roster.add(new TriState(fname, lname, dobArg, major, creditParsed, state));
                } else if (studentType == 'I'){
                    boolean studyAbroad = false;
                    if(st.hasMoreTokens()) {
                        String bool = st.nextToken();
                        if(checkBoolean(bool)){
                            studyAbroad = Boolean.parseBoolean(bool);
                        } else {
                            studyAbroad = false;
                        }
                    }
                    else{
                        studyAbroad = false;
                    }
                    roster.add(new International( fname,  lname, dobArg, major, creditParsed, studyAbroad));
                }
                System.out.println(fname + " " + lname + " " + dobArg + " added to the roster.");
            }
        }
    }

    /**
     * Processes enrollstudent object and adds it to the enrollment array
     *
     * @param roster   active roster object
     * @param enrollment active enrollment object
     * @param st the data tokens delimited by spaces
     * @return true if the argument is a valid input, return false otherwise
     */
    public void enrollStudent(Roster roster, Enrollment enrollment, StringTokenizer st){
        String fname = st.nextToken();
        String lname = st.nextToken();
        String dobArg = st.nextToken();
        String creditsArg = st.nextToken();

        if (checkDate(dobArg) && creditValid(creditsArg)){
            Profile profile = new Profile(lname, fname, new Date(dobArg));
            if (!roster.contains(profile)){
                System.out.println("Cannot enroll: " + profile + " is not in the roster.");
                return;
            } else {
                int credits = Integer.parseInt(creditsArg);
                Student currStudent = roster.getStudent(new Profile(lname, fname, new Date(dobArg)));
                EnrollStudent currEnroll = new EnrollStudent(currStudent,credits);
                enrollment.add(currEnroll);
                System.out.println(currStudent + " enrolled " + credits + " credits");
            }
        }
    }

    /**
     * Awards scholarship
     *
     * @param roster   active roster object
     * @param enrollment active enrollment object
     * @param st the data tokens delimited by spaces
     * @return true if the argument is a valid input, return false otherwise
     */
    public void scholarshipAwarded(Roster roster, Enrollment enrollment, StringTokenizer st){
        String fname = st.nextToken();
        String lname = st.nextToken();
        String dobArg = st.nextToken();
        String scholarship = st.nextToken();

        if(checkDate(dobArg)){
            Profile currProfile = new Profile(lname, fname, new Date(dobArg));
            if (doesStudentExist(roster, currProfile)){
                Student currStudent = roster.getStudent(currProfile);
                if (getStudentType(currStudent) != RESIDENT_NUM){
                    System.out.println(currStudent + " (" + studentTypetoString(currStudent) + ")" + " is not eligible for the scholarship.");
                } else if (isStudentEnrolled(enrollment, currProfile)){
                    EnrollStudent enrollStudent = enrollment.getStudent(currProfile);
                    if (enrollStudent.getCreditsEnrolled()<12){
                        System.out.println(currStudent + " part time student is not eligible for the scholarship.");
                    } else {
                        Resident copy = (Resident) currStudent;
                        copy.setScholarship(Integer.parseInt(scholarship));
                        System.out.println(copy.getProfile()+": scholarship amount updated");
                    }
                }
            }
        }

    }

    public boolean doesStudentExist(Roster roster, Profile profile){
        if(roster.contains(profile)){
            return true;
        } else {
            System.out.println("Student does not exist.");
            return false;
        }
    }

    public boolean isStudentEnrolled(Enrollment enrollment, Profile profile){
        if(enrollment.contains(enrollment.getStudent(profile))){
            return true;
        } else {
            System.out.println("Student is not enrolled");
            return false;
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
        Date dob = new Date (dobArg);
        if (!roster.remove(new Profile(lname, fname, dob))) {
            System.out.println("Student does not exist");
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
    public void dropStudent(Roster roster, Enrollment enrollment, String fname, String lname, String dobArg) {
        Date dob = new Date (dobArg);
        enrollment.remove(enrollment.getStudent(new Profile(lname, fname, dob)));
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
        Date dob = new Date (dobArg);
        boolean exists = roster.contains(new Profile(lname, fname, dob));
        if (!checkDate(dobArg)) System.out.println("DOB invalid: " + dobArg + " is not a valid date of birth.");
        else if (!exists) System.out.println("Student does not exist.");
        else if (major == null) System.out.println("Major code invalid: " + sMajor);
        else {
            roster.changeMajor(new Profile(lname, fname, dob), major);
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

    private boolean scholarshipValid(String scholarship) {
        Integer scholarshipCheck = tryParse(scholarship);
        if (scholarshipCheck == null) {
            System.out.println("Amount is not an integer.");
            return false;
        } else if (MIN_SCHOLARSHIP < scholarshipCheck && scholarshipCheck <= MAX_SCHOLARSHIP_RES) {
            System.out.println(scholarshipCheck + ": invalid amount.");
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

    private int parseResident(String studentType){
        studentType = studentType.toUpperCase();
        if(studentType.equals("RESIDENT")){
            return RESIDENT_NUM;
        }
        else if(studentType.equals("NON_RESIDENT")){
            return NON_RESIDENT_NUM;
        }
        else if(studentType.equals("INTERNATIONAL")){
            return INTERNATIONAL_NUM;
        } else {
            return -1;
        }
    }

    private boolean checkBoolean(String string){
        if (string.equalsIgnoreCase("true") || string.equalsIgnoreCase("false")){
            return true;
        } else {
            return false;
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

    private boolean checkEnrollCredits(Student student, int credits){
        int minCreds = MIN_CREDS;
        int maxCreds = MAX_CREDS;
        int studentNum = getStudentType(student);

        if(studentNum == -1){
            return false;
        } else if (studentNum == INTERNATIONAL_NUM){
            International curr = (International) student;
            if (!curr.isStudyAbroad()){
                minCreds = MIN_INTERNATIONAL_CREDS;
            } else {
                maxCreds = MAX_ABROAD_CREDS;
            }
        }

        if(!(credits >= minCreds && credits <= maxCreds)){
            String studentType = "";
            if(studentNum == RESIDENT_NUM){
                studentType = "Resident";
            } else if (studentNum == INTERNATIONAL_NUM){
                studentType = "International student";
            } else if (studentNum == TRISTATE_NUM){
                studentType = "Tri-state";
            } else if (studentNum == NON_RESIDENT_NUM){
                studentType = "Non-Resident";
            }
            System.out.println("(" + studentType + ") " + credits + ": invalid credit hours.");

            return false;
        } else {
            return true;
        }

    }

    private int getStudentType(Student student){
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

    private String studentTypetoString(Student student){
        if(student instanceof Resident){
            return "Resident";
        } else if(student instanceof TriState){
            return "Tri-state";
        } else if(student instanceof NonResident){
            return "Non-Resident";
        }  else if(student instanceof International){
            return "International";
        } else {
            return null;
        }
    }
    }
