package ir.test.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Vacation {

    public enum VacationState{

        UNCHECK,CONFIRMED,UNCONFIRMED;

    }

    private LocalDate date;
    private int duration;
    private Person person;
    private VacationState state;

    public Vacation(LocalDate date, int duration, Person person , VacationState vacationState) {
        this.date = date;
        this.duration = duration;
        this.person = person;
        this.state = vacationState;

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public VacationState getState() {
        return state;
    }

    public void setState(VacationState state) {
        this.state = state;
    }


    //  ------------- after override equals & hashcode -------------
    //  ------------- no duplicated in date & person   -------------
    @Override
    public boolean equals(Object o){
        if (this == o ) return true;
        if(o == null || getClass()!=o.getClass())
            return false;
        Vacation vacation = (Vacation) o;    // casting
        return Objects.equals(date,vacation.date)
                && Objects.equals(person,vacation.person);
    }
    @Override
    public int hashCode(){
        return Objects.hash(date,person);
    }
}
