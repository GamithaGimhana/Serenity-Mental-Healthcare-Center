package com.gdse.serenity.dao;

import com.gdse.serenity.entity.SuperEntity;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity,ID> extends SuperDAO {

    public boolean save(T dto);
    public boolean update(T dto);
    public boolean deleteByPK(ID pk);
    public List<T> getAll();
    public Optional<T> findByPK(ID pk);
    public Optional<T> getLastPK();
    public String getNextId();

}
