package ir.test.entity;

import java.util.Objects;

public class Person {
    private int personId;
    private String firstName;
    private String lastName;

    public Person(int id,String name , String family){
        this.personId = id;
        this.firstName = name;
        this.lastName = family;
    }
    public Person(int id){
        this.personId = id;
    }
    public Person(String family){
        this.lastName = family;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.lastName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//  ------------- after override equals & hashcode -------------
    @Override
    public boolean equals(Object o){
        if (this == o ) return true;
        if(o == null || getClass()!=o.getClass())
            return false;
        Person person = (Person) o;     // casting
        return personId == person.personId;
    }
    @Override
    public int hashCode(){
        return Objects.hash(personId);
    }
}
