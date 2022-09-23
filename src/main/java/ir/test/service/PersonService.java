package ir.test.service;

import ir.test.dao.PersonDao;
import ir.test.entity.Person;

import java.util.List;

public class PersonService {

public void showAll(){
    PersonDao personDao = new PersonDao();
    personDao.showPersons();
}

    public void createPerson(Person person){
        PersonDao personnelDao = new PersonDao();
        if (canSavePerson(person)){
            personnelDao.create(person);
        }
    }

    public void deletePerson(Person person){
        PersonDao personDao = new PersonDao();
        personDao.delete(person);
//        System.out.println("delete person");
    }

    public void searchPersonByID(int id){

    PersonDao personDao = new PersonDao();
        personDao.searchById(id);
    }

    public void searchPersonByFamily(String family){

        PersonDao personDao = new PersonDao();
        personDao.searchByFamily(family);
    }

    private boolean canSavePerson(Person person) {
        PersonDao personnelDao = new PersonDao();
        List<Person> personList = personnelDao.findAll();

        for (Person p:personList) {
//   after override hashcode && equal //if (p.getPersonId() == (person.getPersonId()))
            if(p.equals(person))
                 {
                return false;
            }
        }

        return true;
    }
}
