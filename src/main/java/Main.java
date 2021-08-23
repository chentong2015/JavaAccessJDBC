import dao.AccessDatabase;
import exception.MyAccessException;
import logging.Logger;
import view.GcrWindows;

public class Main {

    // TODO: Abstract class + unit test
    public static void main(String[] args) throws MyAccessException {
        new GcrWindows("Please wait a second ...");
        Logger.log("Start application");
        AccessDatabase database = new AccessDatabase();
        database.connect();
        // TODO: service part
        database.closeConnection();
        Logger.log("Successfully done !");
        System.exit(0);
    }
}
