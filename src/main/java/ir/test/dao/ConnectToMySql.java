package ir.test.dao;


import java.sql.*;

public class ConnectToMySql {
    static String url = "jdbc:mysql://localhost/task1";
    static String userName = "root";
    static String password = "123456789";
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    static Statement statement = null;
    static ResultSet resultSet = null;

    public static Connection createConnection() throws SQLException {

        try {
            connection = DriverManager.getConnection(url, userName, password);
            return connection;
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void createTable() {

        try {
            Connection connection = ConnectToMySql.createConnection();
            statement = connection.createStatement();


            String sql = "CREATE TABLE tbl_vacation " +
                    "(vacation_request_date VARCHAR(45) not NULL, " +
                    " person_id INTEGER not null , " +
                    " vacation_period INTEGER , " +
                    " vacation_state VARCHAR(30), " +
                    " PRIMARY KEY ( vacation_request_date ))";

            statement.executeUpdate(sql);

            System.out.println("Created table_vacation ...");

            sql = "CREATE TABLE  tbl_person " +
                    "(person_id INTEGER not NULL, " +
                    " person_name VARCHAR (45), " +
                    " person_family VARCHAR(45), " +
                    " PRIMARY KEY (person_id ))";
            statement.executeUpdate(sql);
            System.out.println("Created table_person ...");

        } catch (Exception e) {
            System.out.println("have the tables");

        }
    }
}

