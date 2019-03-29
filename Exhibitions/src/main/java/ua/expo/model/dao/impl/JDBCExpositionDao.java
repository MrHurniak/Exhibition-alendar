package ua.expo.model.dao.impl;

import ua.expo.model.dao.GenericDAO;
import ua.expo.model.entity.Exposition;

import java.sql.Connection;
import java.util.ArrayList;

public class JDBCExposition implements GenericDAO<Exposition> {
    private Connection connection;

    private JDBCExposition(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Exposition obj) {

    }

    @Override
    public Exposition getById(int id) {
        return null;
    }

    @Override
    public void update(Exposition obj) {

    }

    @Override
    public void delete(Exposition obj) {

    }

    @Override
    public ArrayList<Exposition> getAll() {
        return null;
    }
}
