package com.gdse.serenity.dao;

import com.gdse.serenity.entity.SuperEntity;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity> extends SuperDAO {

    public boolean save(T dto);
    public boolean update(T dto);
    public boolean deleteById(String pk);
    public List<T> getAll();
    public Optional<T> findById(String Id);
    public Optional<T> getLastId();
    public String getNextId();

}
