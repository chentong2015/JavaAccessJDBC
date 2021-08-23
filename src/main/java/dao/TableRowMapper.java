package dao;

import exception.MyAccessException;
import handler.DateTimeHelper;
import model.ItemPlanTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableRowMapper {

    public ItemPlanTable mapRowToObject(ResultSet resultSet) throws MyAccessException {
        ItemPlanTable item = new ItemPlanTable();
        try {
            item.setSemaine(resultSet.getString("Semaine"));
            String date = resultSet.getDate("Date_Op").toString();
            // ...
        } catch (SQLException exception) {
            throw new MyAccessException("Query Failed: parse next week planning");
        }
        return item;
    }

    public String mapObjectToRow(ItemPlanTable item) {
        String year = String.valueOf(DateTimeHelper.getYearNumber());
        return year + ",'" + item.getSemaine() + "...";
    }
}
