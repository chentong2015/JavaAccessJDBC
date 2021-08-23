package dao;

import exception.MyAccessException;
import logging.Logger;
import model.ItemPlanTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class AccessDatabase {

    private TableRowMapper mapper;
    private Connection dbConnection;
    private Statement statement;

    public AccessDatabase() throws MyAccessException {
        mapper = new TableRowMapper();
        Logger.log("Connection Access Success");
    }

    public void connect() throws MyAccessException {
        dbConnection = AccessConnection.instance.connect();
        try {
            statement = dbConnection.createStatement();
        } catch (SQLException exception) {
            throw new MyAccessException("Can not create connection statement");
        }
    }

    public HashMap<String, ItemPlanTable> searchNextWeekPlaning() throws MyAccessException {
        try {
            HashMap<String, ItemPlanTable> plans = new HashMap<>();
            ResultSet result = statement.executeQuery(AccessQuery.selectNextWeekData());
            while (result.next()) {
                ItemPlanTable itemPlanTable = mapper.mapRowToObject(result);
                plans.put(itemPlanTable.getKey(), itemPlanTable);
            }
            Logger.log("## Search next week planning successfully");
            return plans;
        } catch (MyAccessException e) {
            throw new MyAccessException(e.getMessage());
        } catch (SQLException exception) {
            throw new MyAccessException("Query Failed: search next week planning");
        }
    }

    public void deleteTowWeeksPlanning() throws MyAccessException {
        try {
            statement.execute(AccessQuery.deleteNextTwoWeeksData());
            Logger.log("## Delete two weeks data successfully");
        } catch (SQLException exception) {
            throw new MyAccessException("Query Failed: Delete next two weeks planning ");
        }
    }

    public void insertNewTwoWeeksPlanning(HashMap<String, ItemPlanTable> plannings) throws MyAccessException {
        for (ItemPlanTable plan : plannings.values()) {
            try {
                String values = mapper.mapObjectToRow(plan);
                statement.execute(AccessQuery.insertNewTwoWeeksData(values));
            } catch (SQLException exception) {
                throw new MyAccessException("Error: inert new two weeks data");
            }
        }
        Logger.log("## Insert new two weeks planning successfully");
    }

    // Statement relates to Access Connection, close them at the end
    public void closeConnection() throws MyAccessException {
        try {
            statement.close();
            dbConnection.close();
            Logger.log("Close Access Connection successfully");
        } catch (SQLException exception) {
            throw new MyAccessException("Error: close Access connection");
        }
    }
}
