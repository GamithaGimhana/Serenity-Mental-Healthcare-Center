package com.gdse.serenity.dao.custom;

import com.gdse.serenity.dao.CrudDAO;
import com.gdse.serenity.entity.Therapist;

import java.io.IOException;
import java.util.List;

public interface TherapistDAO extends CrudDAO<Therapist> {
    public List<String> getAllIds() throws IOException;
}
