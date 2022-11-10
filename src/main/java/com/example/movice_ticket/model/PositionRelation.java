package com.example.movice_ticket.model;

public class PositionRelation {
  private int   positionRelationId  ;
    private int seatId            ;
    private int viewingPositionId   ;

    private int cinemaId    ;
    private int  filmId ;
    private int  viewingTimeId;
    private int  userId ;

    private  Seats seats;
    private ViewingPosition viewingPosition;

    private  ViewingTime viewingTime;

    public PositionRelation() {
    }

    public PositionRelation(int positionRelationId, int seatId, int viewingPositionId, int cinemaId, int filmId, int viewingTimeId, int userId) {
        this.positionRelationId = positionRelationId;
        this.seatId = seatId;
        this.viewingPositionId = viewingPositionId;
        this.cinemaId = cinemaId;
        this.filmId = filmId;
        this.viewingTimeId = viewingTimeId;
        this.userId = userId;
    }

    public PositionRelation(int seatId, int viewingPositionId, int cinemaId, int filmId, int viewingTimeId, int userId) {
        this.seatId = seatId;
        this.viewingPositionId = viewingPositionId;
        this.cinemaId = cinemaId;
        this.filmId = filmId;
        this.viewingTimeId = viewingTimeId;
        this.userId = userId;
    }

    public ViewingTime getViewingTime() {
        return viewingTime;
    }

    public void setViewingTime(ViewingTime viewingTime) {
        this.viewingTime = viewingTime;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getViewingTimeId() {
        return viewingTimeId;
    }

    public void setViewingTimeId(int viewingTimeId) {
        this.viewingTimeId = viewingTimeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPositionRelationId() {
        return positionRelationId;
    }

    public void setPositionRelationId(int positionRelationId) {
        this.positionRelationId = positionRelationId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getViewingPositionId() {
        return viewingPositionId;
    }

    public void setViewingPositionId(int viewingPositionId) {
        this.viewingPositionId = viewingPositionId;
    }



    public Seats getSeats() {
        return seats;
    }

    public void setSeats(Seats seats) {
        this.seats = seats;
    }

    public ViewingPosition getViewingPosition() {
        return viewingPosition;
    }

    public void setViewingPosition(ViewingPosition viewingPosition) {
        this.viewingPosition = viewingPosition;
    }


}
