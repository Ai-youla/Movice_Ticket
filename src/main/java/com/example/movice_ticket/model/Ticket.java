package com.example.movice_ticket.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Ticket {
   private int  ticketId   ;
    private int cinemaId   ;
    private int  filmId    ;
    private int userId      ;
    private int orderState  ;         /*-- 订单状态 0：成功，1：失败 ；2：退票*/
    private  double money  ;          /*商品总金额*/
     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date TicketTime    ; //交易时间
    private String  out_trade_no     ; //商品订单号

 private  int positionRelationId ;//已经选择的位置

private Film film;
private Cinema cinema;
private User user;
private PositionRelation positionRelation;

    public Ticket() {
    }

 public Ticket( int cinemaId, int filmId, int userId, int orderState, double money, Date ticketTime, String out_trade_no, int positionRelationId) {
  this.cinemaId = cinemaId;
  this.filmId = filmId;
  this.userId = userId;
  this.orderState = orderState;
  this.money = money;
  TicketTime = ticketTime;
  this.out_trade_no = out_trade_no;
  this.positionRelationId = positionRelationId;
 }

 public int getPositionRelationId() {
  return positionRelationId;
 }

 public void setPositionRelationId(int positionRelationId) {
  this.positionRelationId = positionRelationId;
 }

 public PositionRelation getPositionRelation() {
  return positionRelation;
 }

 public void setPositionRelation(PositionRelation positionRelation) {
  this.positionRelation = positionRelation;
 }

 public Film getFilm() {
  return film;
 }

 public void setFilm(Film film) {
  this.film = film;
 }

 public Cinema getCinema() {
  return cinema;
 }

 public void setCinema(Cinema cinema) {
  this.cinema = cinema;
 }

 public User getUser() {
  return user;
 }

 public void setUser(User user) {
  this.user = user;
 }

 public int getTicketId() {
  return ticketId;
 }

 public void setTicketId(int ticketId) {
  this.ticketId = ticketId;
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

 public int getUserId() {
  return userId;
 }

 public void setUserId(int userId) {
  this.userId = userId;
 }

 public int getOrderState() {
  return orderState;
 }

 public void setOrderState(int orderState) {
  this.orderState = orderState;
 }

 public double getMoney() {
  return money;
 }

 public void setMoney(double money) {
  this.money = money;
 }

 public Date getTicketTime() {
  return TicketTime;
 }

 public void setTicketTime(Date ticketTime) {
  TicketTime = ticketTime;
 }

 public String getOut_trade_no() {
  return out_trade_no;
 }

 public void setOut_trade_no(String out_trade_no) {
  this.out_trade_no = out_trade_no;
 }
}
