package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerData {
    public static ResultSet getDBRow(Long id) throws SQLException {
        PreparedStatement statement = Database.database.prepareStatement("SELECT * FROM SERVERDATA WHERE ID = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet;
    }

    private static boolean getDBColumnAsBool(long id, String colLabel) throws SQLException {
        PreparedStatement statement = Database.database.prepareStatement("SELECT * FROM SERVERDATA WHERE ID = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getBoolean(colLabel);
    }

    private static void setDBColumnAsBool(long id, String colLabel, boolean value) throws SQLException {
        PreparedStatement statement = Database.database.prepareStatement("UPDATE SERVERDATA SET " + colLabel + " = ? WHERE ID = ?");
        statement.setBoolean(1, value);
        statement.setLong(2, id);
        statement.execute();
    }

}
