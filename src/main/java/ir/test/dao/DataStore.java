package ir.test.dao;

import ir.test.entity.Person;
import ir.test.entity.Vacation;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private static  List<Person> personList = new ArrayList<>();
    private static List<Vacation> vacationList = new ArrayList<>();


    public static void AddPerson(Person person){
        personList.add(person);
    }

    public static void deletePerson(Person person){

        for (int i = 0; i <personList.size() ; i++) {

            if (personList.get(i).getPersonId()==(person.getPersonId()))
                personList.remove(i);

        }
    }

    public static List<Person> findAllPerson(){

        return personList;

    }

    public static void AddVacation(Vacation vacation){
        vacationList.add(vacation);
    }
    public static List<Vacation> findAllVacation(){
        return vacationList;
    }
}
