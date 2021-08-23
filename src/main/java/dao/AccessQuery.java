package dao;

import handler.DateTimeHelper;
import logging.Logger;

public class AccessQuery {

    private final static String TABLE_NAME = "demo_table";
    private final static String columns = "id,name,...";

    public static String selectNextWeekData() {
        String nextWeek = DateTimeHelper.getNextWeekOfAccess();
        String query = "SELECT " + columns + " FROM " + TABLE_NAME + " WHERE Semaine='" + nextWeek + "'";
        Logger.log("Execute query: " + query);
        return query;
    }

    public static String deleteNextTwoWeeksData() {
        String nextWeek = DateTimeHelper.getNextWeekOfAccess();
        String nextTwoWeeks = DateTimeHelper.getNextTwoWeeksOfAccess();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE Semaine='" + nextWeek + "' or Semaine='" + nextTwoWeeks + "'";
        Logger.log("Execute query: " + query);
        return query;
    }

    public static String insertNewTwoWeeksData(String values) {
        String query = "INSERT INTO " + TABLE_NAME + " (" + columns + ") VALUES (" + values + ")";
        // Logger.log("Execute query: " + query);
        return query;
    }
}
