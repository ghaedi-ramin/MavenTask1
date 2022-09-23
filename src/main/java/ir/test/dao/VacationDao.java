package ir.test.dao;

import ir.test.entity.Vacation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import static ir.test.dao.ConnectToMySql.*;
import static ir.test.dao.ConnectToMySql.resultSet;

public class VacationDao {

    int count = 0;
    public void create(Vacation vacation){

        try {
            Connection connection =ConnectToMySql.createConnection();

            

            String saveVacation = "INSERT  INTO tbl_vacation ( vacation_request_date,person_id,vacation_period,vacation_state) Values (?,?,?,?)";

            preparedStatement = connection.prepareStatement(saveVacation);



                preparedStatement.setString(1,vacation.getDate().toString());
                preparedStatement.setInt(2,vacation.getPerson().getPersonId());
                preparedStatement.setInt(3, vacation.getDuration());
                preparedStatement.setString(4, (String.valueOf(vacation.getState())));

                preparedStatement.executeUpdate();

            System.out.println("vacations added");

            preparedStatement.close();
            connection.close();

        }catch (Exception e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {

                System.out.println(" no this id in person");

            } else {
//
                DataStore.AddVacation(vacation);
            }

        }


    }

    public void showVacations() {
        try {

            Connection connection = ConnectToMySql.createConnection();
            String selectVacations = "  SELECT line,vacation_request_date,tbl_vacation.person_id,person_name,person_family," +
                    "vacation_period,vacation_state FROM tbl_vacation,tbl_person WHERE tbl_vacation.person_id = tbl_person.person_id";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectVacations);

            while (resultSet.next()) {

                int line = resultSet.getInt(1);
                String date = resultSet.getString(2);
                int person_id = resultSet.getInt(3);
                String name = resultSet.getString(4);
                String family = resultSet.getString(5);
                int period = resultSet.getInt(6);
                String state = resultSet.getString(7);

                System.out.printf("line: %-5d date: %-16s person_id: %-8d person_name: %-8s person_family: %-16s vacation_period: %-8d "+
                        "vacation_state: %-8S\n", line , date , person_id , name , family , period , state);

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

    public  void changeState(int row , int state){
        try {
            Connection connection = ConnectToMySql.createConnection();
            String updateToConfirm = null;

            if (state == 1)
                updateToConfirm = "update tbl_vacation set vacation_state = 'CONFIRMED' where  line = '" + row + "' ";

            else if(state == 2)
                updateToConfirm = "update tbl_vacation set vacation_state = 'UNCONFIRMED' where  line = '" + row + "' ";

            statement = connection.createStatement();

            statement.executeUpdate(updateToConfirm);


            statement.close();
            connection.close();
            System.out.println("update vacation to confirm ...");
        } catch (Exception e) {
//            DataStore.deletePerson(person);
            e.printStackTrace();
        }
    }
    public List<Vacation> findAll() {
        return DataStore.findAllVacation();
    }


}
