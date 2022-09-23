package ir.test.service;

import ir.test.dao.ConnectToMySql;

public class StartUP {
    public void startupSystem(){
        ConnectToMySql connectToMySql = new ConnectToMySql();
        connectToMySql.createTable();
    }
}
