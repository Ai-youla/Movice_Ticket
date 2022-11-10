package com.example.movice_ticket.model;

public class ViewingPosition {
   private int viewingPositionId  ;
    private int positionNum        ;
    private int   positionState     ;
    private String  position          ;

    public ViewingPosition() {
    }

    public ViewingPosition(int viewingPositionId, int positionNum, int positionState, String position) {
        this.viewingPositionId = viewingPositionId;
        this.positionNum = positionNum;
        this.positionState = positionState;
        this.position = position;
    }

    public int getViewingPositionId() {
        return viewingPositionId;
    }

    public void setViewingPositionId(int viewingPositionId) {
        this.viewingPositionId = viewingPositionId;
    }

    public int getPositionNum() {
        return positionNum;
    }

    public void setPositionNum(int positionNum) {
        this.positionNum = positionNum;
    }

    public int getPositionState() {
        return positionState;
    }

    public void setPositionState(int positionState) {
        this.positionState = positionState;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
