package com.gdse.serenity.bo.custom.impl;


import com.gdse.serenity.bo.custom.TherapyProgramBO;
import com.gdse.serenity.dao.DAOFactory;
import com.gdse.serenity.dao.custom.TherapyProgramDAO;
import com.gdse.serenity.dto.TherapyProgramDTO;
import com.gdse.serenity.dto.UserDTO;
import com.gdse.serenity.entity.TherapyProgram;
import com.gdse.serenity.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapyProgramBOImpl implements TherapyProgramBO {
    TherapyProgramDAO therapyProgramDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPY_PROGRAM);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return therapyProgramDAO.getNextId();
    }

    @Override
    public boolean save(TherapyProgramDTO therapyProgramDTO) throws SQLException, ClassNotFoundException {
        return therapyProgramDAO.save(new TherapyProgram(therapyProgramDTO.getProgramId(), therapyProgramDTO.getName(), therapyProgramDTO.getDurationInWeeks(), therapyProgramDTO.getCost(), therapyProgramDTO.getDescription()));
    }

    @Override
    public List<TherapyProgramDTO> getAll() throws SQLException, ClassNotFoundException {
        List<TherapyProgramDTO> therapyProgramDTOS = new ArrayList<>();
        List<TherapyProgram> therapyPrograms = therapyProgramDAO.getAll();
        for (TherapyProgram therapyProgram : therapyPrograms) {
            therapyProgramDTOS.add(new TherapyProgramDTO(therapyProgram.getProgramId(), therapyProgram.getName(), therapyProgram.getDurationInWeeks(), therapyProgram.getCost(), therapyProgram.getDescription()));
        }
        return therapyProgramDTOS;
    }

    @Override
    public boolean update(TherapyProgramDTO therapyProgramDTO) throws SQLException, ClassNotFoundException {
        return therapyProgramDAO.update(new TherapyProgram(therapyProgramDTO.getProgramId(), therapyProgramDTO.getName(), therapyProgramDTO.getDurationInWeeks(), therapyProgramDTO.getCost(), therapyProgramDTO.getDescription()));
    }

    @Override
    public boolean delete(String therapyProgramId) throws SQLException, ClassNotFoundException {
        return therapyProgramDAO.deleteById(therapyProgramId);
    }

    @Override
    public Optional<TherapyProgramDTO> findById(String selectedTherapyProgramId) throws SQLException, ClassNotFoundException {
        Optional<TherapyProgram> therapyProgramOpt = therapyProgramDAO.findById(selectedTherapyProgramId);
        return therapyProgramOpt.map(therapyProgram -> new TherapyProgramDTO(therapyProgram.getProgramId(), therapyProgram.getName(), therapyProgram.getDurationInWeeks(), therapyProgram.getCost(), therapyProgram.getDescription()));
    }
}
