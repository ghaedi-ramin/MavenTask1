package ir.test.dao;

import ir.test.entity.Person;
import ir.test.entity.Vacation;
import ir.test.service.PersonService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectToMySql {
    final static String url = "jdbc:mysql://localhost/task1";
    final static String userName = "root";
    final static String password = "123456789";
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    static Statement statement = null;
    static ResultSet resultSet = null;

    public static void savePersons() throws SQLException {

        PersonDao personDao = new PersonDao();

        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String savePerson = "INSERT INTO tbl_person (person_id, person_name, person_family) Values (?,?,?)";
        preparedStatement = connection.prepareStatement(savePerson);

        for (Person p : personDao.findAll()) {
            preparedStatement.setInt(1,p.getPersonId());
            preparedStatement.setString(2,p.getName());
            preparedStatement.setString(3, p.getLastName());

            preparedStatement.executeUpdate();
        }
        System.out.println("persons are saved in mysql");

        preparedStatement.close();
        connection.close();
    }


    public static void saveVacations() throws SQLException {

        VacationDao vacationDao = new VacationDao();

        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String saveVacation = "INSERT INTO tbl_vacation ( vacation_request_date,person_id,vacation_period,vacation_state) Values (?,?,?,?)";
        preparedStatement = connection.prepareStatement(saveVacation);

        for (Vacation v : vacationDao.findAll()) {

            preparedStatement.setString(1,v.getDate().toString());
            preparedStatement.setInt(2,v.getPerson().getPersonId());
            preparedStatement.setInt(3, v.getDuration());
            preparedStatement.setString(4, (String.valueOf(v.getState())));

            preparedStatement.executeUpdate();
        }
        System.out.println("vacations are saved in mysql");

        preparedStatement.close();
        connection.close();
    }
    public static  void fetchToPersonList() throws SQLException {


        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String selectPerson = "select * from tbl_person";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(selectPerson);

        while (resultSet.next()){

//            Object selectResulset = resultSet.getInt(1) + resultSet.getString(2) + resultSet.getString(3);
//          List<Object> person = new ArrayList<Object>();
//           person.add(selectResulset);
//           DataStore.AddPerson((Person) person);

            Person person = new Person(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            PersonService personService = new PersonService();
            personService.createPerson(person);

        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
