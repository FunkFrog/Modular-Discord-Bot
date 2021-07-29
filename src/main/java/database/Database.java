package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Database {
    static final Connection database;

    static {
        try {
            database = DriverManager.getConnection("jdbc:h2:" + System.getProperty("user.dir") + "/database;AUTO_SERVER=TRUE", "sa", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void close() throws SQLException {
        database.close();
    }


    public static void main(String[] args) throws SQLException {
        System.out.print("RESET DATABASE; ARE YOU SURE? (y/n): ");
        Scanner in = new Scanner(System.in);
        if (in.next().equalsIgnoreCase("y")) {
            resetTables();
            close();
            System.out.println("Reset!");
        } else {
            close();
            System.out.println("Canceled!");
        }
    }

    public static void resetTables() {
        try {
            runSchema("/ServerDataSchema.sql");
            runSchema("/UserDataSchema.sql");
            runSchema("/PerServerDataSchema.sql");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    static void runSchema(String location) throws SQLException {
        database.prepareStatement(
                new Scanner(Database.class.getResourceAsStream(location)).useDelimiter("//A").next()
        ).execute();
    }
}