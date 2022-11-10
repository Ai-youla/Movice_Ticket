/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/1/4 17:23:23                            */
/*==============================================================*/
create database mt;
use mt;
drop table if exists Cinema;

drop table if exists CinemaAddress;

drop table if exists CinemaType;

drop table if exists Film;

drop table if exists FilmPrice;

drop table if exists FilmReview;

drop table if exists FilmType;

drop table if exists HistoricalRecords;

drop table if exists MovieNews;

drop table if exists Participants;

drop table if exists PlayRelation;

drop table if exists PositionRelation;

drop table if exists Produce;

drop table if exists ProduceRelation;

drop table if exists Reply;

drop table if exists SeatRelation;

drop table if exists Seats;

drop table if exists Ticket;

drop table if exists User;

drop table if exists ViewingPosition;

drop table if exists ViewingRelation;

drop table if exists ViewingTime;

drop table if exists VipMember;

/*==============================================================*/
/* Table: Cinema          影院                                      */
/*==============================================================*/
create table Cinema
(
   cinemaId             int not null auto_increment,
 --   filmId               int,
   cinemaAddressId      int,
   cinemaTypeId         int,
   cinemaName           varchar(100),
   service              varchar(20), -- 服务：可退票，可改签
   primary key (cinemaId)
);

/*==============================================================*/
/* Table: filmAndCinema          电影和影院的关系                               */
/*==============================================================*/
create table filmAndCinema
(
 f_c_relationId          int not null auto_increment,
   cinemaId             int,
   filmId               int,
    primary key (f_c_relationId)
);
/*==============================================================*/
/* Table: CinemaAddress           影院地址                              */
/*==============================================================*/
create table CinemaAddress
(
   cinemaAddressId      int not null auto_increment,
   province             varchar(100), -- 省
   city                 varchar(100), -- 市
   county               varchar(100), -- 区/镇
   detailed             varchar(200), -- 详细地址
   primary key (cinemaAddressId)
);

/*==============================================================*/
/* Table: CinemaType            影院类型                                */
/*==============================================================*/
create table CinemaType
(
   cinemaTypeId         int not null auto_increment,
   type                 varchar(100),
   primary key (cinemaTypeId)
);
insert into cinematype(type)values('IMAX厅'),('巨幕厅'),('CINTTY厅'),('杜比影院'),('120帧'),('MX4D厅');
/*==============================================================*/
/* Table: Film                  电影                                */
/*==============================================================*/
create table Film
(
   filmId               int not null auto_increment,
   filmName             varchar(100),
   filmDescribe         text, -- 简介
   filmPicture          varchar(100), -- 海报
   startDate            date, -- 上映日期
   movieDuration        int, -- 电影时长
   score                float(8), -- 评分
   state                varchar(100),-- 状态：即将上映，热播
   filmTypeId           int,
   filmPriceId          int,
   primary key (filmId)
);
alter table film add video  varchar(100);
/*==============================================================*/
/* Table: FilmPrice           电影票价                                  */
/*==============================================================*/
create table FilmPrice
(
   filmPriceId          int not null auto_increment,
   price                float(8), -- 价格
   filmNumber           int, -- 电影票数
   allNumber            int, -- 记录数
   primary key (filmPriceId)
);

/*==============================================================*/
/* Table: FilmReview      影评                                      */
/*==============================================================*/
create table FilmReview
(
   filmReviewId         int not null auto_increment,
   filmReview           text, -- 评论
   likeNumber           int, -- 点赞数
   replyNumber          int, -- 回复数
   time                 datetime, -- 发布时间
   score                float(8), -- 评分
   userId               int,
   filmId               int,
   primary key (filmReviewId)
);

/*==============================================================*/
/* Table: FilmType                 电影类型                             */
/*==============================================================*/
create table FilmType
(
   filmTypeId           int not null auto_increment,
   type                 varchar(20),
   primary key (filmTypeId)
);
insert into filmType(type)values('动作'),('喜剧'),('爱情'),('犯罪'),('奇幻'),('冒险'),('科幻'),('动画'),('悬疑'),('惊悚'),('剧情');

-- *==============================================================*/
-- /* Table: HistoricalRecords         观影记录                            */
-- /*==============================================================*/
-- create table HistoricalRecords
-- (
--    HistoricalRecordsId  int not null auto_increment,
--    date                 date, -- 日期
--    userId               int,
--    filmId               int,
--    primary key (HistoricalRecordsId)
-- );
 
 
/*==============================================================*/
/* Table: MovieNews          影讯                                   */
/*==============================================================*/
create table MovieNews
(
   movieNewsId          int not null auto_increment,
   filmId               int,
   title                varchar(100), -- 标题
   news                 text, -- 影讯内容
   newsPicture          varchar(100), -- 海报
   primary key (movieNewsId)
);
alter table MovieNews add video  varchar(100);
/*==============================================================*/
/* Table: Participants          参演人员                                */
/*==============================================================*/
create table Participants
(
   ParticipantsId       int not null auto_increment,
   type                 varchar(10), -- 类型
   priture              varchar(100), -- 头像
   TureName             varchar(100), -- 真名
   playName             varchar(100), -- 饰演名
   primary key (ParticipantsId)
);

/*==============================================================*/
/* Table: PlayRelation            人员和电影关系                              */
/*==============================================================*/
create table PlayRelation
(
   playRelationId       int not null auto_increment,
   ParticipantsId       int,
   filmId               int,
   primary key (playRelationId)
);

/*==============================================================*/
/* Table: PositionRelation       用户位置和影厅座位的关系                               */
/*==============================================================*/
create table PositionRelation
(
   positionRelationId   int not null auto_increment,
   seatId               int,
   viewingPositionId    int,
   cinemaId    int,
   filmId int,
   viewingTimeId int,
   userId int,
   primary key (positionRelationId)
);
 
/*==============================================================*/
/* Table: Produce        积分商品                                       */
/*==============================================================*/
create table Produce
(
   produceId            int not null auto_increment,
   produceName          varchar(100), -- 商品名
   picture              varchar(100), -- 海报
   integral             int, -- 需要积分
   produceNum           int,  -- 商品数量
   state                int, -- 状态
   beginDate            date, -- 开始日期
   overDate             date, -- 结束日期
   primary key (produceId)
);

/*==============================================================*/
/* Table: ProduceRelation        VIP和商品的关系                               */
/*==============================================================*/
create table ProduceRelation
(
   produceRelationId     int not null auto_increment,
   vipId                int not null,
   produceId            int not null,
   primary key (produceRelationId)
);
alter table ProduceRelation add number int ; -- 记录兑换的商品
/*==============================================================*/
/* Table: Reply               回复影评                                  */
/*==============================================================*/
create table Reply
(
   replyId              int not null auto_increment,
   filmReviewId         int, 
   userId               int,
   replyText            text, -- 回复内容
   replytime            datetime, -- 回复时间
   primary key (replyId)
);
/*==============================================================*/
/* Table: SeatRelation    影厅座位和影院的关系                                      */
/*==============================================================*/
create table SeatRelation
(
   seatRelationId       int not null auto_increment,
   cinemaId             int,
   seatId               int,
   primary key (seatRelationId)
);

/*==============================================================*/
/* Table: Seats        影厅座位                                         */
/*==============================================================*/
create table Seats
(
   seatId               int not null auto_increment,
   seats                varchar(400),  -- 座位排列
   cinemaNum 			varchar(100),  -- 几号影厅
   primary key (seatId)
);
/*==============================================================*/
/* Table: Ticket                 购票                               */
/*==============================================================*/
create table Ticket
(
   ticketId             int not null auto_increment,
   cinemaId             int,
   filmId               int,
   userId               int,
   orderState           int, -- 订单状态 0：成功，1：失败 ；2：退票
   money                double, -- 商品总金额
   TicketTime           datetime,
   out_trade_no         varchar(200), -- 商品订单号
   primary key (ticketId)
);
alter table ticket add positionRelationId int; 
/*==============================================================*/
/* Table: User               用户                                   */
/*==============================================================*/

create table User
(
   userId               int not null auto_increment,
   userName             varchar(100),
   userPassword         varchar(100),
   picture              varchar(100), -- 头像
   address              varchar(200), -- 地址
   email                varchar(100),
   phone                varchar(20),
   userRole             varchar(20), -- 权限
   primary key (userId)
);
insert into user (userName,userPassword,picture,address,email,phone,userRole)
value('admin','$2a$10$yxWE1cpFNfXHUguhrlyxqe0t.ejDYGHQ6kbg16aNIIRXIPcnmULAG',null,null,null,null,'ROLE_ADMIN');

/*==============================================================*/
/* Table: VipMember            VIP会员                                */
/*==============================================================*/
create table vipMember
(
   vipId                int not null auto_increment,
   integral             int, -- 积分
   amount               float(8), -- 消费金额
   member               int, -- 0不是会员，1是会员
   userId               int,
   primary key (vipId)
);

/*==============================================================*/
/* Table: ViewingPosition         观影位置                              */
/*==============================================================*/
create table ViewingPosition
(
   viewingPositionId    int not null auto_increment,
   positionNum          int, -- 位置数量
   positionState        int, -- 位置的状态 0:已选，1，不可选
   position             varchar(20), -- 具体位置
   primary key (viewingPositionId)
);

/*==============================================================*/
/* Table: ViewingRelation          观影时间和影院的关系                             */
/*==============================================================*/
create table ViewingRelation
(
   viewingRelationId    int not null auto_increment,
   viewingTimeId        int,
   cinemaId             int,
   filmId               int,
   primary key (viewingRelationId)
);
alter table ViewingRelation add seatId int;
/*==============================================================*/
/* Table: ViewingTime          观影时间                                 */
/*==============================================================*/

create table ViewingTime
(
   viewingTimeId        int not null auto_increment,
   sessions             int, -- 英语2D、英语3D
   beginTime            time,
   overTime             time,
   viemingDate          date, -- 日期
   primary key (viewingTimeId)
);

 
