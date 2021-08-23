package handler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

public class DateTimeHelper {

    // Process only the next two weeks data from now on
    private final static String nextOneWeek;
    private final static String nextTwoWeeks;

    static {
        // Gets the last two digits of a year number
        String yearNumber = String.valueOf(getYearNumber() % 100);
        int weekNumber = getCurrentWeekNumber();
        nextOneWeek = "W" + yearNumber + (weekNumber + 1);
        nextTwoWeeks = "W" + yearNumber + (weekNumber + 2);
    }

    public static int getYearNumber() {
        return LocalDateTime.now().getYear();
    }

    public static int getCurrentWeekNumber() {
        LocalDate date = LocalDate.now();
        return date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);

        // Get week number based on year
        // LocalDate date = LocalDate.now(ZoneId.of("Europe/Paris"));
        // TemporalField field = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        // int weekNumber = date.get(field);
    }

    public static String getNextWeek() {
        return nextOneWeek;
    }

    public static String getNextWeekOfAccess() {
        return nextOneWeek.replace("W", "S");
    }

    public static String getNextTwoWeeks() {
        return nextTwoWeeks;
    }

    public static String getNextTwoWeeksOfAccess() {
        return nextTwoWeeks.replace("W", "S");
    }

    public static boolean IsValidWeekNumber(String week) {
        return week.equals(nextOneWeek) || week.equals(nextTwoWeeks);
    }

    public static String getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        return LocalDateTime.now().format(formatter);
    }

    public static LocalDate convertToLocalDate(Date date) {
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDate parseToLocalDate(String date) {
        return LocalDate.parse(date);
    }

    public static LocalDate formatToLocalDate(String dateInString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateInString, formatter);
    }
}
