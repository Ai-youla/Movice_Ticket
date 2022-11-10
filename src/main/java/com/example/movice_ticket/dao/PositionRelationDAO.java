package com.example.movice_ticket.dao;

import com.example.movice_ticket.model.PositionRelation;

import java.util.List;
import java.util.Map;

public interface PositionRelationDAO {
    /**
     * 确定用户选的位置
     * @param positionRelation
     * @return
     */
    public int createPositonRelation(PositionRelation positionRelation);

    /**
     * 通过位置ID，影院ID，电影ID查询已经选好的位置
     * @param map
     * @return
     */
    public List<PositionRelation> queryPositionRelation(Map<String, Object> map);
    /**
     * 通过用户ID，影院ID，电影ID查询已经选好的位置
     * @param map
     * @return
     */
    public List<PositionRelation> queryById(Map<String, Object> map);

    /**
     *查询详细的已选位置
     * @param positionRelationId
     * @return
     */
    public PositionRelation ById(int positionRelationId);

    /**
     * 删除已选位置
     * @param positionRelationId
     * @return
     */
    public boolean delete(int positionRelationId);

    /**
     * 修改已选位置
     * @param positionRelation
     * @return
     */
    public boolean updatePosition(PositionRelation positionRelation);
}
