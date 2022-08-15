package ir.test.service;

import ir.test.dao.PersonDao;
import ir.test.dao.VacationDao;
import ir.test.entity.Vacation;

import java.util.Iterator;
import java.util.List;

public class VacationService {

    public void createVacation(Vacation vacation ) {

        PersonDao personDao = new PersonDao();
        Iterator iterator = personDao.findAll().iterator();
        if (iterator.hasNext()) {

            if (canSaveVacation(vacation)){

                VacationDao vacationDao = new VacationDao();
                vacationDao.create(vacation);

                System.out.println("vacation added");
            }
            else {
                System.out.println("the duplicated vacation");
            }
        }
        else
            System.out.println("no person to request vacation");
    }
    public void showAllVacation(){

        VacationDao vacationDao = new VacationDao();
        for (Vacation v : vacationDao.findAll()) {
            System.out.println(v.getDate() + " " +v.getDuration() + " " +
                    v.getPerson().getPersonId() + " "+ v.getPerson().getName() + " " +
                    v.getPerson().getLastName() + " " + v.getState());
        }
    }

    public void confirmVacationState(int record){
        VacationDao vacationDao = new VacationDao();
        for (Vacation v : vacationDao.findAll()){
           v = vacationDao.findAll().get(record);

            v.setState(Vacation.VacationState.CONFIRMED);
        }
    }


    public void unConfirmVacationState(int record){
        VacationDao vacationDao = new VacationDao();
        for (Vacation v : vacationDao.findAll()){
            v = vacationDao.findAll().get(record);

            v.setState(Vacation.VacationState.UNCONFIRMED);
        }
    }
    private boolean canSaveVacation(Vacation vacation) {
        VacationDao vacationDao = new VacationDao();
        List<Vacation> vacationList = vacationDao.findAll();

        for (Vacation v:vacationList) {
//   after override hashcode() && equal()
            if(v.equals(vacation))
            {
                return false;
            }
        }

        return true;
    }
}