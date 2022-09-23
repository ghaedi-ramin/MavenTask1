package ir.test.dao;

import ir.test.entity.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import static ir.test.dao.ConnectToMySql.*;

public class PersonDao {

    int count = 0;
    public void create(Person person) {

        try {
            Connection connection = ConnectToMySql.createConnection();

            String savePerson = "INSERT INTO tbl_person (person_id, person_name, person_family) Values (?,?,?) " ;
            preparedStatement = connection.prepareStatement(savePerson);

            preparedStatement.setInt(1, person.getPersonId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getLastName());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            System.out.println("person added");
        } catch (Exception e) {

            if (e instanceof SQLIntegrityConstraintViolationException){

                System.out.println("duplicated id");

            }
            else{

                e.printStackTrace();
                DataStore.AddPerson(person);
        }



        }

    }

    public void delete(Person person)  {

        try {
            Connection connection = ConnectToMySql.createConnection();

            String deletePerson = "delete from tbl_person where  person_id ='" + person.getPersonId() + "'";
            statement = connection.createStatement();
            statement.executeUpdate(deletePerson);



                statement.close();
            connection.close();

            System.out.println("delete person...");

        }

        catch (Exception e) {

            if (e instanceof SQLIntegrityConstraintViolationException) {

                System.out.println("this person has records in vacation");

            } else {
                e.printStackTrace();
                DataStore.deletePerson(person);
            }
        }
    }

    public void showPersons() {
        try {

            Connection connection = ConnectToMySql.createConnection();
            String selectPersons = " select * from tbl_person";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectPersons);

            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String family = resultSet.getString(3);

                System.out.printf("id:%-8d name:%-8s family:%-8s \n", id, name, family);

                count = resultSet.getRow();
            }

            if (count == 0){
                System.out.println("no record in table");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            DataStore.findAllPerson();
        }

    }

    public void searchById(int personId) {

        try {

            Connection connection = ConnectToMySql.createConnection();
            String selectPersons = " select * from tbl_person where person_id = '" + personId + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectPersons);


            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String family = resultSet.getString(3);

                System.out.printf("id:%-8d name:%-8s family:%-8s \n", id, name, family);


                count = resultSet.getRow();
            }

            if (count == 0){
                System.out.println("no record for yor search");
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            DataStore.findAllPerson();
        }

    }

    public void searchByFamily(String personFamily) {

        try {

            Connection connection = ConnectToMySql.createConnection();
            String selectPersons = " select *from tbl_person where person_family = '" + personFamily + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectPersons);


            while (resultSet.next()) {
                System.out.println(resultSet.getRow());
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String family = resultSet.getString(3);

                System.out.printf("id:%-8d name:%-8s family:%-8s \n", id, name, family);

                count = resultSet.getRow();

            }

            if (count == 0){
                System.out.println("no record for yor search");
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            DataStore.findAllPerson();
        }
    }

        public List<Person> findAll () {
            return DataStore.findAllPerson();
        }

}
