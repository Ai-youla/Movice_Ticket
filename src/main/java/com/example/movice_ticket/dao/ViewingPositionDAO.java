package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.ViewingPosition;

public interface ViewingPositionDAO {
    /**
     * 生成观影选位
     * @param viewingPosition
     * @return
     */
    public int createViewingPosition(ViewingPosition viewingPosition);

    /**
     * 通过ID查询选位信息
     * @param viewingPositionId
     * @return
     */
    public ViewingPosition byId(int viewingPositionId);

    /**
     * 修改选位信息
     * @param viewingPosition
     * @return
     */
     public boolean updateViewingposition(ViewingPosition viewingPosition);
}
