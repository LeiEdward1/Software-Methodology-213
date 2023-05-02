package com.example.assignment_3_fx;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Creates a date object that stores the student's birthdate.
 * It checks if the date is a valid date, and if the student is above the age of 16.
 * This date object is also used to get the current date of when the program is being run.
 *
 * @author Andy Zhang, Edward Lei
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    //Integer representation of every 4 years, every 100 years, and every 400 years.
    // This is used for the leapYear method.
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    //Integer representation of every month
    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;
    public static final int MAX_MONTH_DAYS_31 = 31;
    public static final int MAX_MONTH_DAYS_30 = 30;
    public static final int NON_LEAP_MONTH = 28;
    public static final int LEAP_MONTH = 29;
    public static final int MIN_MONTH_DAYS = 1;
    public static final int MONTH = 0;
    public static final int DAY = 1;
    public static final int YEAR = 2;

    public static final int SIXTEEN_YEARS = 16;

    public static final int NUM_DOB_PARAMS = 3;

    /**
     * A constructor to create a new date object for the users birthdate.
     * Breaks down the date into 3 integers: day, month, and year.
     */
    public Date() {
        Calendar cr = Calendar.getInstance();
        this.year = cr.get(Calendar.YEAR);
        this.month = cr.get(Calendar.MONTH);
        this.day = cr.get(Calendar.DAY_OF_MONTH) + 1;
    }

    /**
     * Constructor for Date class. Takes in month, day, and year ints.
     * @param month
     * @param day
     * @param year
     */
    public Date(int month, int day, int year) {
        this.year = year;
        this.month = month;
        this.day = day;

    }

    /**
     * Constructor for Date class. Breaks date into 3 integers: day, month, year.
     *
     * @param date that is being set to
     */
    public Date(String date) {
        int[] dateNum = parseDate(date);

        this.month = dateNum[MONTH];
        this.day = dateNum[DAY];
        this.year = dateNum[YEAR];
    }

    /**
     * Check if a given date is a valid calendar date.
     *
     * @return true if the date is a valid calendar date, false otherwise
     */
    public boolean isValid() {
        if (!checkMonth(month)) {
            return false;
        } else {
            if (month == JANUARY | month == MARCH || month == MAY || month == JULY || month == AUGUST || month == OCTOBER || month == DECEMBER) {
                if (day > MAX_MONTH_DAYS_31 || day < MIN_MONTH_DAYS) {
                    return false;
                }
            } else if (month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) {
                if (day > MAX_MONTH_DAYS_30 || day < MIN_MONTH_DAYS) {
                    return false;
                }
            } else {
                if (checkLeapYear(year)) {
                    if (day > LEAP_MONTH || day < MIN_MONTH_DAYS) {
                        return false;
                    }
                } else {
                    if (day > NON_LEAP_MONTH || day < MIN_MONTH_DAYS) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Checks if the student is 16 years old or older.
     *
     * @return true if the student is 16 or older, false otherwise
     */
    private boolean checkMonth(int month) {
        if (month > DECEMBER || month < JANUARY) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if the student is 16 years old or older.
     *
     * @return true if the student is 16 or older, false otherwise
     */
    public boolean checkYear() {
        Date dummy = new Date();
        int tempYear = dummy.year - SIXTEEN_YEARS;
        Date tempDate = new Date(dummy.month, dummy.day, tempYear);
        if (this.compareTo(tempDate) > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check if the year that is inputted is a leap year.
     *
     * @param year that is being checked if it is a leap year
     * @return true if it is a leap year, false otherwise
     */
    private boolean checkLeapYear(int year) {
        boolean leapYear;
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                if (year % QUATERCENTENNIAL == 0) {
                    leapYear = true;
                } else {
                    leapYear = false;
                }
            } else {
                leapYear = true;
            }
        } else {
            leapYear = false;
        }
        return leapYear;
    }

    @Override
    /**
     * Returns string of the date in MM/DD/YYYY format
     */
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    @Override
    /**
     * Check if the date objects are equal to each other
     * @param temp is being compared to the other date object
     * @return true if they are equal, return false otherwise
     */
    public boolean equals(Object temp) {
        if (!(temp instanceof Date)) {
            return false;
        }
        if (this.compareTo((Date) temp) == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    /**
     * Comparing two dates and returning which one is newer.
     * Converts both dates into milliseconds to compare.
     * @param temp comparison date variable
     * @return 1 if the first date is older, return -1 if the first date is younger, return 0 otherwise
     */
    public int compareTo(Date temp) {
        Calendar cr = Calendar.getInstance();
        cr.set(year, month, day);
        long time1 = cr.getTimeInMillis();

        cr.set(temp.year, temp.month, temp.day);
        long time2 = cr.getTimeInMillis();

        long difference = time1 - time2;

        if (difference > 0) {
            return 1;
        } else if (difference < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Takes a string date and returns an int array containing
     * the month, day, and year of the date
     *
     * @param date String date in MM/DD/YYYY format
     * @return dateNum array of size 3, index 0 contains month,
     * index 2 contains day, and index 3 contains the year
     */
    public int[] parseDate(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        int[] dateNum = new int[NUM_DOB_PARAMS];
        for (int i = 0; i < NUM_DOB_PARAMS; i++) {
            dateNum[i] = Integer.parseInt(st.nextToken());
        }
        return dateNum;
    }

    /**
     * Test bed method for isValid method
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Date testTrue1 = new Date("1/31/1999");
        Date testTrue2 = new Date("1/31/1999");
        Date testFalse1 = new Date("2/29/2003");
        Date testFalse2 = new Date("1/35/2000");
        Date testFalse3 = new Date("13/5/2003");
        Date testFalse4 = new Date("2/32/2001");
        Date testFalse5 = new Date("4/31/2002");

        System.out.println(testTrue1 + " " + testTrue1.isValid());
        System.out.println(testTrue2 + " " + testTrue2.isValid());
        System.out.println(testFalse1 + " " + testFalse1.isValid());
        System.out.println(testFalse2 + " " + testFalse2.isValid());
        System.out.println(testFalse3 + " " + testFalse3.isValid());
        System.out.println(testFalse4 + " " + testFalse4.isValid());
        System.out.println(testFalse5 + " " + testFalse5.isValid());

    }
}