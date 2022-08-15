package ir.test.service;

import ir.test.dao.PersonDao;
import ir.test.entity.Person;

import java.util.List;

public class PersonService {

public void showAll(){
    PersonDao personDao = new PersonDao();
    for (Person p : personDao.findAll()) {
                    System.out.println(p.getPersonId() + " " +p.getName() + " " + p.getLastName());
                }
}

    public void createPerson(Person person){
        PersonDao personnelDao = new PersonDao();
        if (canSavePerson(person)){
            personnelDao.create(person);
            System.out.println("person added");
        }else {
            System.out.println("error");
        }
    }

    public void deletePerson(Person person){
        PersonDao personDao = new PersonDao();
        personDao.delete(person);
        System.out.println("delete person");
    }

    public Person getNameFamily(int id) {

        PersonDao personDao = new PersonDao();
        for (Person p : personDao.findAll())
            if (id == p.getPersonId()) {

                Person person = new Person(id, p.getName(), p.getLastName());
                return person;
            }

        return null;
    }
    public void searchPersonByID(int id){

    PersonDao personDao = new PersonDao();
        for (Person p : personDao.findAll()) {
                            if (id == p.getPersonId())
                                System.out.println(p.getPersonId() + " " + p.getName() + " " + p.getLastName());
                        }
    }

    public void searchPersonByFamily(String family){

        PersonDao personDao = new PersonDao();
        for (Person p : personDao.findAll()) {
                            if (p.getLastName() .equals( family))
                                System.out.println(p.getPersonId() + " " + p.getName() + " " + p.getLastName());
                        }
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
