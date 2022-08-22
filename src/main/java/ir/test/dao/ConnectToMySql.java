package ir.test.dao;

import ir.test.entity.Person;
import ir.test.entity.Vacation;
import ir.test.service.PersonService;
import ir.test.service.VacationService;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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

        String savePerson = "INSERT IGNORE INTO tbl_person (person_id, person_name, person_family) Values (?,?,?)";
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
        String saveVacation = "INSERT IGNORE INTO tbl_vacation ( vacation_request_date,person_id,vacation_period,vacation_state) Values (?,?,?,?)";

        preparedStatement = connection.prepareStatement(saveVacation);

        for (Vacation v : vacationDao.findAll())
        {

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
    public void fetchToPersonList() throws SQLException {


        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String selectPerson = "select * from tbl_person";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(selectPerson);

        while (resultSet.next()){

            Person person = new Person(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            PersonService personService = new PersonService();
            personService.createPerson(person);

        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    public void fetchToVacationList() throws SQLException {


        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String selectVacation = "select * from tbl_Vacation";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(selectVacation);


        while (resultSet.next()){

           String r1 = resultSet.getString(1);
            int r2 = resultSet.getInt(2);
            int r3 = resultSet.getInt(3);
            String r4 = resultSet.getString(4);

            PersonService personService1 = new PersonService();
            personService1.getNameFamily(r2);

            Person person = personService1.getNameFamily(r2);

            String name = null;
            String family = null;
            name = person.getName();
            family = person.getLastName();

            Person person1 = new Person(r2, name, family);

          Vacation vacation = new Vacation(LocalDate.parse(r1),r3,person1, Vacation.VacationState.valueOf(r4));
            VacationService vacationService = new VacationService();
            vacationService.createVacation(vacation);

        }
        resultSet.close();
        statement.close();
        connection.close();
    }

}
