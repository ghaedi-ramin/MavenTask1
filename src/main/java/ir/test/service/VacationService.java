package ir.test.service;

import ir.test.dao.PersonDao;
import ir.test.dao.VacationDao;
import ir.test.entity.Vacation;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

public class VacationService {

    public void createVacation(Vacation vacation ) {

            if (canSaveVacation(vacation)){

                VacationDao vacationDao = new VacationDao();
                vacationDao.create(vacation);

//                System.out.println("vacation added");
            }
            else {
                System.out.println("the duplicated vacation");
            }
//        }
//        else
//            System.out.println("no person to request vacation");
    }
    public void showAllVacation(){

        VacationDao vacationDao = new VacationDao();
         vacationDao.showVacations();
    }

    public void changeVacationState(int line,int state){
        VacationDao vacationDao = new VacationDao();
        int code = line;
        int change = state;
        vacationDao.changeState(code,change);
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