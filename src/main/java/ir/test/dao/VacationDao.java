package ir.test.dao;

import ir.test.entity.Vacation;

import java.util.List;

public class VacationDao {

    public void create(Vacation vacation){
        DataStore.AddVacation(vacation);
    }
    public List<Vacation> findAll() {
        return DataStore.findAllVacation();
    }


}
