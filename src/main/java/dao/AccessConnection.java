package dao;

import exception.MyAccessException;
import handler.PropertiesHelper;
import logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Access Connection is Singleton
public class AccessConnection {

    public static AccessConnection instance = new AccessConnection();

    private AccessConnection() {
    }

    public Connection connect() throws MyAccessException {
        try {
            String connectionURL = PropertiesHelper.getDbConnectionURL();
            String databaseURL = "jdbc:ucanaccess://" + connectionURL; // set openExclusive=false;
            Logger.log("Connection URL:" + databaseURL);
            return DriverManager.getConnection(databaseURL);
        } catch (MyAccessException exception) {
            throw new MyAccessException(exception.getMessage());
        } catch (SQLException e) {
            throw new MyAccessException("Can not connect Access, database is busy or waiting for lock from other user !");
        }
    }
}


