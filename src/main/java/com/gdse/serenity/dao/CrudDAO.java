package com.gdse.serenity.dao;

import com.gdse.serenity.entity.SuperEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity> extends SuperDAO {

    public boolean save(T dto);
    public boolean update(T dto);
    public boolean deleteById(String pk);
    public List<T> getAll() throws IOException;
    public Optional<T> findById(String Id) throws IOException;
    public Optional<T> getLastId() throws IOException;
    public String getNextId();

}
