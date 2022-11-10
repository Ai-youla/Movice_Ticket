package com.example.movice_ticket.dao.impl;

import com.example.movice_ticket.dao.PositionRelationDAO;
import com.example.movice_ticket.mapper.PositionRelationMapper;
import com.example.movice_ticket.model.PositionRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PositionRelationDAOImpl implements PositionRelationDAO {
    @Autowired
    private PositionRelationMapper positionRelationMapper;

    @Override
    public int createPositonRelation(PositionRelation positionRelation) {
        int positionRelationId = 0;
        if ( positionRelationMapper.createPositonRelation(positionRelation)){
            positionRelationId = positionRelation.getPositionRelationId();
        }
        return positionRelationId;
    }

    @Override
    public List<PositionRelation> queryPositionRelation(Map<String, Object> map) {
        return positionRelationMapper.queryPositionRelation(map);
    }

    @Override
    public List<PositionRelation> queryById(Map<String, Object> map) {
        return positionRelationMapper.queryById(map);
    }

    @Override
    public PositionRelation ById(int positionRelationId) {
        return positionRelationMapper.ById(positionRelationId);
    }

    @Override
    public boolean delete(int positionRelationId) {
        return positionRelationMapper.delete(positionRelationId);
    }

    @Override
    public boolean updatePosition(PositionRelation positionRelation) {
        return positionRelationMapper.updatePosition(positionRelation);
    }
}
