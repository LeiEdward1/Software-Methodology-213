package com.example.assignment_3_fx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TuitionManagerController {
    public static final int NUM_DOB_PARAMS = 3;
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

    public static final String errorFName = "First Name is empty.";

    private static final int MAX_SCHOLARSHIP_RES = 10000;
    private static final int MIN_SCHOLARSHIP = 0;

    private Roster roster = new Roster();
    private Enrollment enrollment = new Enrollment();
    @FXML
    private Label labelFName, labelLName;

    @FXML
    private TextField textFName, textLName, textCreds, enrollFName, enrollLName, enrollCreds, scholarFName, scholarLName, scholarAMT;

    @FXML
    private DatePicker textDOB, enrollDOB, scholarDOB;

    @FXML
    private RadioButton radioBAIT, radioCS, radioECE, radioITI, radioMATH, radioRes, radioNonRes, radioTri, radioInt, radioNY, radioCT;

    @FXML
    private CheckBox checkStudy;

    @FXML
    private TextArea console;

    private boolean semesterEndFlag = false;


    @FXML
    protected void onHelloButtonClick() {
        labelFName.setText("Welcome to JavaFX Application!");
        labelLName.setText("Welcome to JavaFX Application!");
        textFName.setText("Welcome!");
        textLName.setText("Welcome!");
    }

    @FXML
    protected void onChangeMajorClick(){
        console.clear();
        if(checkProfile(textFName, textLName, textDOB)){
            changeMajor();
        }
        else{
            console.appendText("\nStudent does not exist!");
        }
    }

    /**
     * Goes through roster to find the student and change their major
     *
     */
    public void changeMajor() {
        Major major = getSelectedMajor();
        Date dob = new Date (convertDOB(textDOB.getValue().toString()));
        boolean exists = roster.contains(new Profile(textLName.getText(), textFName.getText(), dob));
        if (!checkDate(textDOB)) console.appendText("DOB invalid: " + convertDOB(textDOB.getValue().toString()) + " is not a valid date of birth.\n");
        else if (!exists) console.appendText("Student does not exist.\n");
        else if (major == null) console.appendText("Major code invalid: " + major + "\n");
        else {
            roster.changeMajor(new Profile(textLName.getText(), textFName.getText(), dob), major);
            console.appendText(textFName.getText() + " " + textLName.getText() + " major changed to " + major + ".\n");
        }
    }
    @FXML
    protected void onLoadFromFileClick(){
        console.clear();
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");

        File file = fileChooser.showOpenDialog(stage);
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                StringTokenizer line = new StringTokenizer(sc.nextLine(), ",");
                String studentType = line.nextToken();
                addStudentString( "A" + studentType, line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            console.appendText("Error with locating file.\n");
        }
    }

    @FXML
    protected void onPrintByProfileClick(){
        console.clear();
        if(!isRosterEmpty()){
            console.appendText(roster.print());
        }

    }
    @FXML
    protected void onPrintBySchoolAndMajor(){
        console.clear();
        if(!isRosterEmpty()) {
            console.appendText(roster.printBySchoolMajor());
        }
    }
    @FXML
    protected void onPrintByStanding(){
        console.clear();
        if(!isRosterEmpty()) {
            console.appendText(roster.printByStanding());
        }
    }
    @FXML
    protected void onPrintRBSClick(){
        console.clear();
        if(!isSchoolEmpty("RBS")){
            console.appendText(roster.printList("RBS"));
        }

    }
    @FXML
    protected void onPrintSASClick(){
        console.clear();
        if(!isSchoolEmpty("SAS")) {
            console.appendText(roster.printList("SAS"));
        }
    }
    @FXML
    protected void onPrintSCIClick(){
        console.clear();
        if(!isSchoolEmpty("SCI")) {
            console.appendText(roster.printList("SCI"));
        }
    }
    @FXML
    protected void onPrintSOEClick(){
        console.clear();
        if(!isSchoolEmpty("SOE")) {
            console.appendText(roster.printList("SOE"));
        }
    }
    @FXML
    protected void onPrintEnrollClick(){
        console.clear();
        if(!isEnrollmentEmpty()){
            console.appendText(enrollment.printEnrollment());
        }

    }
    @FXML
    protected void onPrintTuitionClick(){
        console.clear();
        if(!isEnrollmentEmpty()) {
            console.appendText(enrollment.sortCreds(roster));
        }
    }
    @FXML
    protected void onPrintSemesterEndClick(){
        console.clear();
        if(!isEnrollmentEmpty()){
            if(!semesterEndFlag){
            console.appendText(roster.printSemesterEnd(enrollment));
            semesterEndFlag = true;
            }
        }

    }

    @FXML
    protected void onScholarshipButtonClick(){
        boolean errFlag = false;
        if(!checkProfile(scholarFName, scholarLName, scholarDOB)){
            errFlag = true;
        }

        if(!amountValid(scholarAMT.getText())){
            errFlag = true;
        }

        if(!errFlag){
            scholarshipAwarded();
        }
    }

    @FXML
    protected void onEnrollButtonClick() {
        console.clear();
        boolean errFlag = false;
        if(!checkProfile(enrollFName, enrollLName, enrollDOB)){
            errFlag = true;
        }

        if(!creditValid(enrollCreds.getText())){
            errFlag = true;
        } else {
            Profile profile = new Profile(enrollLName.getText(), enrollFName.getText(), new Date(convertDOB(enrollDOB.getValue().toString())));

            if(!checkEnrollCredits(roster.getStudent(profile), Integer.parseInt(enrollCreds.getText()))){
                errFlag = true;
            }
        }

        if(!errFlag){
            enrollStudent();
        }

    }


    @FXML
    protected void onDropButtonClick() {
        console.clear();
        boolean errFlag = false;
        if(!checkProfile(enrollFName, enrollLName, enrollDOB)){
            errFlag = true;
        }


        if(!creditValid(enrollCreds.getText())){
            errFlag = true;
        }

        Profile profile = new Profile(enrollLName.getText(), enrollFName.getText(), new Date(enrollDOB.getValue().toString()));
        if(isStudentEnrolled(profile)){
            errFlag = true;
            console.appendText("Student is not enrolled");
            //print something not enrolled
        }


        if(!errFlag){
            dropStudent();
        }

    }

    private void enrollStudent(){
        String fname = enrollFName.getText();
        String lname = enrollLName.getText();
        String dobArg = convertDOB(enrollDOB.getValue().toString());
        String creditsArg = enrollCreds.getText();

        Profile profile = new Profile(lname, fname, new Date(dobArg));
        if (!roster.contains(profile)){
                console.appendText("Cannot enroll:"+profile+" is not in roster");
                return;
        } else {
            int credits = Integer.parseInt(creditsArg);
            Student currStudent = roster.getStudent(new Profile(lname, fname, new Date(dobArg)));
            EnrollStudent currEnroll = new EnrollStudent(currStudent,credits);
            enrollment.add(currEnroll);
            console.appendText(currStudent+" enrolled "+ credits+ " credits");
        }

    }
    public void dropStudent() {
        Date dob = new Date (enrollDOB.getValue().toString());
        enrollment.remove(enrollment.getStudent(new Profile(enrollLName.getText(), enrollFName.getText(), dob)));
    }


    @FXML
    protected void onAddButtonClick() {
        console.clear();
        boolean errFlag = false;
        if(!checkNotDupProfile(textFName, textLName, textDOB)){
            errFlag = true;

        }

        if(!creditValid(textCreds.getText())){
            console.appendText("\nCredits are not valid!");
            errFlag = true;
        }

        if(!checkTristateValid()){
            errFlag = true;
            console.appendText("\nChoose a state!");
        }

        if(!errFlag){
            addStudent();
        }

    }

    @FXML
    protected void onRemoveButtonClick(){
        console.clear();
        if(checkProfile(textFName, textLName, textDOB)){
            removeStudent();
        }
        else{
            console.appendText("\nStudent does not exist!");
        }
    }

    @FXML
    protected void onNonResidentClick() {
       checkNonResident();
    }

    @FXML
    protected void onResidentClick() {
        checkResident();
    }

    @FXML
    protected void onTristateClick() {
        checkTristate();
    }
    @FXML
    protected void onInternationalClick() {
        checkInternational();
    }

    private void checkTristate(){
        if(radioTri.isSelected()){
            radioNY.setDisable(false);
            radioCT.setDisable(false);
            checkStudy.setDisable(true);

            checkStudy.setSelected(false);
        }
    }

    private void checkInternational(){
        if(radioInt.isSelected()){
            radioNY.setDisable(true);
            radioCT.setDisable(true);
            checkStudy.setDisable(false);

            radioNY.setSelected(false);
            radioCT.setSelected(false);
        }
    }

    private void checkResident(){
        if(radioRes.isSelected()){
            radioTri.setDisable(true);
            radioInt.setDisable(true);
            radioNY.setDisable(true);
            radioCT.setDisable(true);
            checkStudy.setDisable(true);

            radioTri.setSelected(false);
            radioInt.setSelected(false);
            radioNY.setSelected(false);
            radioCT.setSelected(false);
            checkStudy.setSelected(false);

        }
    }

    private void checkNonResident(){
        if(radioNonRes.isSelected()){
            radioTri.setDisable(false);
            radioInt.setDisable(false);

        }
        checkTristate();
        checkInternational();
    }

    public void addStudent(){//String command, String fname, String lname, String dobArg, String sMajor, String credits) {
        String fname = textFName.getText();
        String lname = textLName.getText();
        String dobArg = convertDOB(textDOB.getValue().toString());
        String credits = textCreds.getText();

            Major major = getSelectedMajor();

                int creditParsed = Integer.parseInt(credits);
                if (radioRes.isSelected()){
                    roster.add(new Resident(fname, lname, dobArg, major, creditParsed));
                } else {
                    if (radioTri.isSelected()){
                        String state = getState();

                        roster.add(new TriState(fname, lname, dobArg, major, creditParsed, state));

                    } else if (radioInt.isSelected()){
                        boolean studyAbroad = checkStudy.isSelected();
                        roster.add(new International( fname,  lname, dobArg, major, creditParsed, studyAbroad));
                    } else{
                        roster.add(new NonResident(fname, lname, dobArg, major, creditParsed));
                    }
                }
                console.clear();
                console.appendText(fname+ " " + lname + " " + dobArg + " added to roster.");
    }

    public void addStudentString(String command, StringTokenizer st){//String command, String fname, String lname, String dobArg, String sMajor, String credits) {
        String fname = st.nextToken();
        String lname = st.nextToken();
        String dobArg = st.nextToken();
        String sMajor = st.nextToken();
        String credits = st.nextToken();

        if (checkDateString(dobArg)){
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
                console.appendText(fname + " " + lname + " " + dobArg + " added to the roster.\n");
            }
        }
    }

    /**
     * Removes student from the roster, if it exists, prints text statement otherwise.
     *
     */
    public void removeStudent() {
        console.clear();
        Date dob = new Date (convertDOB(textDOB.getValue().toString()));
        Profile student = new Profile(textLName.getText(), textFName.getText(), dob);
        if (!roster.remove(student)) {
            console.appendText("Student does not exist");
        } else {
            console.appendText(student+ " removed from roster.");
            EnrollStudent enrollCheck = enrollment.getStudent(student);
            if(enrollCheck!=null){
                enrollment.remove(enrollCheck);
            }
        }
    }

    private String getState(){
        if (radioNY.isSelected()){
            return "NY";
        } else if (radioCT.isSelected()){
            return "CT";
        } else {
            return null;
        }
    }

    private Major getSelectedMajor(){
        if (radioBAIT.isSelected()){
            return Major.BAIT;
        } else if (radioCS.isSelected()){
            return Major.CS;
        } else if (radioECE.isSelected()){
            return Major.EE;
        } else if (radioITI.isSelected()){
            return Major.ITI;
        } else if (radioMATH.isSelected()){
            return Major.MATH;
        } else {
            return Major.BAIT;
        }
    }

    private String convertDOB(String dob){
        StringTokenizer st = new StringTokenizer(dob, "-");
        if (st.countTokens()!=3){
            return null;
        }

        String[] dateString = new String[3];
        for (int i = 0; i < NUM_DOB_PARAMS; i++) {
            dateString[i] = st.nextToken();
        }
        return dateString[1] + "/" + dateString[2] + "/" + dateString[0];
    }

    private boolean checkTristateValid(){
        if(radioTri.isSelected() && !radioNY.isSelected() && !radioCT.isSelected()){
            return false;
        } else {
            return true;
        }
    }

    private boolean checkNotDupProfile(TextField fname, TextField lname, DatePicker dob){
        if (!checkProfile(fname,lname,dob)){
            return false;
        }
        Profile test = new Profile(lname.getText(), fname.getText(), new Date(convertDOB(dob.getValue().toString())));
        if (roster.contains(test)){
            console.appendText(test + " is already in the roster!\n");
            return false;
        } else {
            return true;
        }
    }



    private boolean checkProfile(TextField fname, TextField lname, DatePicker dob){
        boolean errFlag = false;
        if(fname.getText().equals("")){

            console.appendText("First Name is empty");
            errFlag = true;
        }
        if(lname.getText().equals("")){
            console.appendText("\nLast Name is empty.");
            errFlag = true;
        }

        if (!checkDate(dob)){

            errFlag = true;
        }

        return !errFlag;
    }

    /**
     * Checks the date string if is a valid date that can be used for a student.
     * If the student is under 16 or the date of birth is invalid.
     *
     * @return true if the date of birth is valid, false otherwise
     */
    private boolean checkDate(DatePicker dob) {
        if(dob.getValue()==null){
            console.appendText("DOB empty: Please enter a date of birth.\n");
            return false;
        }

        String date = dob.getValue().toString();
        return checkDateString(convertDOB(date));
    }

    /**
     * Checks the date string if is a valid date that can be used for a student.
     * If the student is under 16 or the date of birth is invalid.
     *
     * @return true if the date of birth is valid, false otherwise
     */
    private boolean checkDateString(String date) {
        if(date==null){
            console.appendText("DOB empty: Please enter a date of birth.\n");
            return false;
        }
        StringTokenizer st = new StringTokenizer(date, "/");
        int[] dateNum = new int[3];
        if (st.countTokens() != 3) {
            console.appendText("DOB invalid: " + date + " is not a valid calendar date.");
            return false;
        }
        Date inputDate = new Date(date);
        if (!inputDate.checkYear()) {
            console.appendText("DOB invalid: " + date + " is younger than 16 years old."); //Change so it doesnt print invalid date and 16 year old
            return false;
        } else if (!inputDate.isValid()) {
            console.appendText("DOB invalid: " + date + " is not a valid calendar date.");
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
            console.appendText("(" + studentType + ") " + credits + ": invalid credit hours.");

            return false;
        } else {
            return true;
        }

    }

    /**
     * Checks if the credits inputted are valid.
     *
     * @return true if credits is an integer, and is non-negative, return false otherwise
     */
    private boolean creditValid(String creds) {
        if (creds.equals("")){
            console.appendText("\nCredits completed empty: please enter an integer!");

            return false;
        }
        Integer creditCheck = tryParse(creds);
        if (creditCheck == null) {
            console.appendText("\nCredits completed invalid: not an integer!");
            return false;
        } else if (creditCheck < 0) {
            console.appendText("\nCredits completed invalid: cannot be negative!");
            return false;
        } else {
            return true;
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

    private boolean amountValid(String creds) {
        if (creds.equals("")){
            console.appendText("\nScholarship amount completed empty: please enter an integer!");
            return false;
        }
        Integer creditCheck = tryParse(creds);
        if (creditCheck == null) {
            console.appendText("\nScholarship amount completed invalid: not an integer!");

            return false;
        } else if (creditCheck < 0) {
            console.appendText("\nScholarship amount completed invalid: cannot be negative!");

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
    public boolean isStudentEnrolled(Profile profile){
        if(enrollment.contains(enrollment.getStudent(profile))){
            return true;
        } else {

            return false;
        }
    }

    public boolean doesStudentExist(Profile profile){
        if(roster.contains(profile)){
            return true;
        } else {
            console.appendText("Student does not exist.");
            return false;
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

    /**
     * Check if the roster is empty and prints a text statement if it is.
     *
     * @return true if it is empty, false otherwise
     */
    public boolean isRosterEmpty() {
        if (roster.isEmpty()) {
            console.appendText("Student roster is empty!\n");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a school in a roster does nto have any students.
     *
     * @param school checking if it has anyone
     * @return true if the school is empty, false otherwise
     */
    public boolean isSchoolEmpty(String school) {
        if (roster.isSchoolEmpty(school)) {
            console.appendText("School roster is empty!\n");
            return true;
        } else {
            return false;
        }
    }

    public boolean isEnrollmentEmpty(){
        if(enrollment.isEmpty()){
            console.appendText("Enrollment is empty!\n");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Awards scholarship
     *
     * @return true if the argument is a valid input, return false otherwise
     */
    public void scholarshipAwarded(){
        String fname = scholarFName.getText();
        String lname = scholarLName.getText();
        String dobArg = convertDOB(scholarDOB.getValue().toString());
        String scholarship = scholarAMT.getText();

            Profile currProfile = new Profile(lname, fname, new Date(dobArg));
            if (doesStudentExist(currProfile)){
                Student currStudent = roster.getStudent(currProfile);
                if (getStudentType(currStudent) != RESIDENT_NUM){
                    console.appendText(currStudent + " (" + studentTypetoString(currStudent) + ")" + " is not eligible for the scholarship.\n");
                } else if (isStudentEnrolled(currProfile)){
                    EnrollStudent enrollStudent = enrollment.getStudent(currProfile);
                    if (enrollStudent.getCreditsEnrolled()<MIN_INTERNATIONAL_CREDS){
                        console.appendText(currStudent + " part time student is not eligible for the scholarship.\n");
                    } else {
                        Resident copy = (Resident) currStudent;
                        copy.setScholarship(Integer.parseInt(scholarship));
                        console.appendText(copy.getProfile()+": scholarship amount updated\n");
                    }
                } else {
                    console.appendText(currStudent + " is not enrolled and is not eligible for the scholarship.\n");
                }
            }


    }
}