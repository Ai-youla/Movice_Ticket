package com.example.movice_ticket.dao.impl;

import com.example.movice_ticket.dao.ViewingPositionDAO;
import com.example.movice_ticket.mapper.ViewingPositionMapper;
import com.example.movice_ticket.model.ViewingPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ViewingPositionDAOImpl implements ViewingPositionDAO  {
    @Autowired
    private ViewingPositionMapper viewingPositionMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int createViewingPosition(ViewingPosition viewingPosition) {
        int v  = 0;
       boolean b  = viewingPositionMapper.createViewingPosition(viewingPosition);
        if(b){
            v = viewingPosition.getViewingPositionId();
        }
        return v;
    }

    @Override
    public ViewingPosition byId(int viewingPositionId) {
        return viewingPositionMapper.byId(viewingPositionId);
    }

    @Override
    public boolean updateViewingposition(ViewingPosition viewingPosition) {
        return viewingPositionMapper.updateViewingposition(viewingPosition);
    }
}
