package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.FilmReview;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
@Mapper
public interface FilmReviewMapper {

	@Insert("insert into filmReview (filmReview,likeNumber,replyNumber,time, score,userId, filmId)value(#{filmReview},0,0,now(), #{score},#{userId},#{filmId})")
	public boolean createFilmReview(FilmReview filmReview);
	
	@Update("update filmReview set filmReview=#{filmReview} , time = #{time} where filmReviewId = #{filmReviewId} ")
	public boolean updateFilmReview(Map<String, Object>param);
	
	@Update("update filmReview set likeNumber=likeNumber + #{num} where filmReviewId = #{filmReviewId}")
	public boolean addlikeNum(Map<String, Object>param);
	
	@Update("update filmReview set likeNumber=likeNumber - #{num} where filmReviewId = #{filmReviewId}")
	public boolean reducelikeNum(Map<String, Object>param);
	
	@Update("update filmReview set  replyNumber=replyNumber + #{num} where filmReviewId = #{filmReviewId}")
	public boolean addReplyNum(Map<String, Object>param);
	
	@Update("update filmReview set  replyNumber=replyNumber - #{num} where filmReviewId = #{filmReviewId}")
	public boolean reduceReplyNum(Map<String, Object>param);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.FilmReviewBuilder.class,method="queryFilmReview")
	@Result(property="user",column="userId",
	one=@One(select="com.example.movice_ticket.mapper.UserMapper.query"))
	@Result(property="film",column="filmId",
	one=@One(select="com.example.movice_ticket.mapper.FilmMapper.getFilmById"))
	public List<FilmReview> queryList(Map<String, Object>param);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.FilmReviewBuilder.class,method="queryConutFilmReview")
	public int querycountList(Map<String, Object>param);
	
	@Select("select * from filmReview where filmReviewId = #{filmReviewId}")
	public FilmReview byfilmReviewId(int filmReviewId);
	
	@Delete("delete from filmReview where filmReviewId = #{filmReviewId}")
	public boolean delete(int filmReviewId);
	
}
