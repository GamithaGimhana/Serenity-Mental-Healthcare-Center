package com.gdse.serenity.bo.custom.impl;

import com.gdse.serenity.bo.custom.TherapistBO;
import com.gdse.serenity.dao.DAOFactory;
import com.gdse.serenity.dao.custom.TherapistDAO;
import com.gdse.serenity.dao.custom.TherapyProgramDAO;
import com.gdse.serenity.dto.TherapistDTO;
import com.gdse.serenity.entity.Therapist;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapistBOImpl implements TherapistBO {
    TherapistDAO therapistDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIST);
    TherapyProgramDAO therapyProgramDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPY_PROGRAM);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return therapistDAO.getNextId();
    }

    @Override
    public boolean save(TherapistDTO therapistDTO) throws SQLException, ClassNotFoundException {
        return therapistDAO.save(new Therapist(therapistDTO.getTId(), therapistDTO.getName(), therapistDTO.getEmail(), therapistDTO.getPhone(), therapistDTO.getSpecialization(), therapistDTO.getStatus()));
    }

    @Override
    public List<TherapistDTO> getAll() throws SQLException, ClassNotFoundException, IOException {
        List<TherapistDTO> therapistDTOS = new ArrayList<>();
        List<Therapist> therapists = therapistDAO.getAll();
        for (Therapist therapist : therapists) {
            therapistDTOS.add(new TherapistDTO(therapist.getTId(), therapist.getName(), therapist.getEmail(), therapist.getPhone(), therapist.getSpecialization(), therapist.getStatus()));
        }
        return therapistDTOS;
    }

    @Override
    public boolean update(TherapistDTO therapistDTO) throws SQLException, ClassNotFoundException {
        return therapistDAO.update(new Therapist(therapistDTO.getTId(), therapistDTO.getName(), therapistDTO.getEmail(), therapistDTO.getPhone(), therapistDTO.getSpecialization(), therapistDTO.getStatus()));
    }

    @Override
    public boolean delete(String therapistId) throws SQLException, ClassNotFoundException {
        return therapistDAO.deleteById(therapistId);
    }

    @Override
    public Optional<TherapistDTO> findById(String selectedTherapistId) throws SQLException, ClassNotFoundException, IOException {
        Optional<Therapist> therapistOpt = therapistDAO.findById(selectedTherapistId);
        return therapistOpt.map(therapist -> new TherapistDTO(therapist.getTId(), therapist.getName(), therapist.getEmail(), therapist.getPhone(), therapist.getSpecialization(), therapist.getStatus()));
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException, IOException {
        return therapistDAO.getAllIds();
    }

    @Override
    public List<String> getAssignedTherapists(String programId) throws SQLException, ClassNotFoundException, IOException {
        return therapyProgramDAO.getAssignedTherapists(programId);
    }
}
