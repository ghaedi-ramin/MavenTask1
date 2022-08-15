package ir.test.dao;

import ir.test.entity.Person;

import java.util.List;

public class PersonDao {


    public void create(Person person) {
        DataStore.AddPerson(person);
    }


    public void delete(Person person) {

        DataStore.deletePerson(person);
    }

    public List<Person> findAll() {
        return DataStore.findAllPerson();
    }

}
