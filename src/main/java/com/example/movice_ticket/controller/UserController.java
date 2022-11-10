package com.example.movice_ticket.controller;

import com.example.movice_ticket.dao.*;
import com.example.movice_ticket.model.*;
import com.example.movice_ticket.util.GeneratePasswordEncoder;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserController extends GeneratePasswordEncoder {

	@Autowired
	private UserDAO userdao;

	@Autowired
	private FilmDAO filmDAO;
	@Autowired
	private ViewingPositionDAO viewingPositionDAO;
	@Autowired
	private PositionRelationDAO positionRelationDAO;
	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private PlayRelationDAO playRelationDAO;
	@Autowired
	private FilmReviewDAO filmReviewDAO;
	@Autowired
	private ReplyDAO replyDAO;

	@Autowired
	private VipMemberDAO vipMemberDAO;
	@Autowired
	private ProduceRelationDAO produceRelationDAO;

	//跳转到登陆页面
	@RequestMapping("/to/login")
	public String toLogon() { 
		return "login";
	}
	//跳转到注册页面
	@RequestMapping("/to/regist")
	public String toregist() {
		return "regist";
	}


	//跳转到用户个人中心页面
	@RequestMapping("/user/userInfo")
	public String userInfo(Model model,@RequestParam(name = "page", required = false, defaultValue = "1") int page) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		Map<String, Object> ParamMap = new HashMap<String, Object>();
		//查询出用户的信息
		User user = userdao.login(userName);
		if(user!=null){
			model.addAttribute("user", user);
			VipMember vipMember = vipMemberDAO.getVip_member(user.getUserId());
			System.out.println(vipMember.getAmount()+"------------");
			//如果消费金额大于等于200,升级为会员
			 if(vipMember.getAmount()>=200){
				 ParamMap.put("member",1);//1：会员
				 ParamMap.put("userId", user.getUserId());
				 boolean success = vipMemberDAO.updateMember(ParamMap);
				  vipMember = vipMemberDAO.getVip_member(user.getUserId());
				 model.addAttribute("vipMember", vipMember);
			 }else{
				 model.addAttribute("vipMember", vipMember);
			 }

			//查询出兑换的商品
			List<ProduceRelation> produceRelations = produceRelationDAO.getProducebyId(vipMember.getVipId());
			model.addAttribute("produceRelations", produceRelations);
		}

		Ticket ticket = new Ticket();
		ticket.setUserId(user.getUserId());
		ParamMap.put("ticket", ticket);
		int size = 10;
		if(page<=0){
			page=1;
		}
		int begin = (page - 1) * size;
		ParamMap.put("begin", begin);
		ParamMap.put("size", size);

		FilmReview filmReview = new FilmReview();
		filmReview.setUserId( user.getUserId());//获取查询条件Id
		ParamMap.put("filmReview",filmReview);
		//根据用户id查询该用户的全部影评
		List<FilmReview> filmReviews = filmReviewDAO.queryList(ParamMap);
		model.addAttribute("filmReviews", filmReviews);
		//查询该用户的全部订单
		List<Ticket> tickets = ticketDao.queryTicketList(ParamMap);
		model.addAttribute("tickets", tickets);
		int pages = 0;//总页数
		int recordCount = ticketDao.queryCountTicket(ParamMap);//总订单数量
		if (recordCount % size == 0) {
			pages = recordCount / size;
			if (pages == 0) {
				pages = 1;
			}
		} else {
			pages = recordCount / size + 1;
		}
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		return "user/userInfo";
	}

	//跳转修改用户信息页面
	@RequestMapping("/user/userUpdate")
	public String userUpdate(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		//查询出用户的信息
		User user = userdao.login(userName);
		if(user!=null){
			model.addAttribute("user", user);
		}
		return "user/updateUser";
	}

	//注册
	@RequestMapping("/regist")
	public String regist(User user, Model model) {
		String pass = GeneratePasswordEncoder.generatePasswordEncoder(user.getPassword());
		user.setUserPassword(pass);
		String path = "";
			boolean b =  userdao.regist(user);
			 
			 if (b) {
					path= "login";
				} else { 
					path= "regist";
				}
		return path;
	}
	//判断用户是否存在
	 @RequestMapping("/to/judgeUser") 
	 @ResponseBody
	 public String tojudgeUser(String userName) {
		 User s = userdao.login(userName);
		 String user = "";
			if(s==null) {
				user = "用户可用";
			}else {
				user = "用户已注册";
			}
		 return user; 
	}
	
	//跳转到错误页面
	 @RequestMapping("/to/error") 
	 public String toError() {
		 return "error"; }

	//跳转到电影详细信息
	@RequestMapping("/user/queryfilm")
	public String queryfilm(int filmId,Model model) {
		Film film = filmDAO.getFilmById(filmId);
		List<PlayRelation> playRelations = playRelationDAO.getParticipants(filmId);
		Map<String,Object> paramMap = new HashMap<String,Object>() ;
		paramMap.put("film", film) ;

		int size = 10 ;
		int page=1;
		int begin = (page-1)*size ;

		paramMap.put("begin", begin) ;
		paramMap.put("size", size) ;
		FilmReview filmReview = new FilmReview();
		filmReview.setFilmId(filmId);
		paramMap.put("filmReview", filmReview) ;
		//根据电影id查询该电影的全部影评
		List<FilmReview> filmReviews = filmReviewDAO.queryList(paramMap);
		for (FilmReview filmReviewlist : filmReviews){
			if (filmReviewlist.getReplyNumber()==0){
				Reply reply = new Reply();
				reply.setFilmReviewId(filmReviewlist.getFilmReviewId());
				reply.setUserId(filmReviewlist.getUser().getUserId());
				//查询有多少回复
				List<Reply> replys = replyDAO.moreReply(reply);
				if (replys!=null){
					paramMap.put("num",replys.size());
					paramMap.put("filmReviewId",filmReviewlist.getFilmReviewId());
					//记录每条影评的回复数量
					boolean s = filmReviewDAO.addReplyNum(paramMap);
				}
			}
		}
		//刷新查询数据库中的数据
		filmReviews = filmReviewDAO.queryList(paramMap);


		float sum =0;//记录总评分
		for (FilmReview filmReview1 : filmReviews){
			sum += filmReview1.getScore();
		}
		if (filmReviews.size()>0){
			float avg = sum/filmReviews.size(); //平均评分
			film.setScore(avg);
			boolean s = filmDAO.updateScore(film);//保存评分进数据库
		}

		int pages = 0 ;//总页数
		//该电影的影评数量
		int recordCount = filmReviewDAO.querycountList(paramMap);
		if ( recordCount % size == 0 ) {
			pages = recordCount / size  ;
			if(pages==0) {
				pages=1;
			}
		} else {
			pages = recordCount / size + 1 ;
		}

		//查询出全部回复
		List<Reply> replies = replyDAO.replys();

		model.addAttribute("replies",replies) ;
		model.addAttribute("pages",pages) ;
		model.addAttribute("page",page) ;

		if (film!=null && playRelations!=null)
		{
			model.addAttribute("filmReviews", filmReviews);
			model.addAttribute("film", film);
			model.addAttribute("playRelations", playRelations);
			return "/user/film/queryfilm";
		}
		return "error"; }
	//创建订单
	@RequestMapping("/user/createTicket")
	public String createTicket(int filmId, int cinemaId, int viewingTimeId,
							   int positionNum, int seatId, double money ,String position, Model model) {
		System.out.println("+++"+position);
		Map<String,Object> map = new HashMap<>();
		ViewingPosition viewingPosition = null;
		String path = "";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = userdao.login(userName);
		for (int i =1;i <= position.length();i++){
			if((i % 4)==0){
				if(i>=4){
					String s = position.substring(i-4,i);
					//创建观影位置
					 viewingPosition = new ViewingPosition(0,positionNum,0,s);
					int viewingPositionId = viewingPositionDAO.createViewingPosition(viewingPosition);
					if (viewingPositionId>0){
//						System.out.println(viewingPositionId+"--------");

						PositionRelation positionRelation = new PositionRelation(seatId,viewingPositionId,cinemaId, filmId, viewingTimeId, user.getUserId());
						//将观影位置和用户绑定
						int positonRelationId = positionRelationDAO.createPositonRelation(positionRelation);
							if(positonRelationId>0){
								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

								Random ran = new Random();
								int num = ran.nextInt(999);
								//生成随机数订单号
								String outTradeNo =seatId+String.format("%d",num) +sdf.format(new Date());
							//	String outTradeNo = UUID.randomUUID().toString();
								Ticket ticket = new Ticket(cinemaId,filmId,user.getUserId(),0,money,null,outTradeNo,positonRelationId);
								//生成订单
								boolean b = ticketDao.createTicket(ticket);
								int integral = (int)money/2;//积分计算
								VipMember vipMember = new VipMember(integral,(float)money,0,user.getUserId());
								//记录积分
									boolean a = vipMemberDAO.updateIntegral(vipMember);
//								System.out.println(i+"----0000"+"----------"+position.length());
								map.put("userId", user.getUserId());
								map.put("filmId", filmId);
								map.put("cinemaId",cinemaId);
								map.put("out_trade_no",outTradeNo);

								if(i == position.length()){
//									System.out.println("11111");
									List<Ticket> tickets = ticketDao.getTickes(map);
									for (Ticket ticket1 : tickets){
										if(ticket1.getTicketTime()==null){
											//统一商品号
											boolean success = ticketDao.setOut_trade_no(map);
											if (b){
												path= "redirect:/open/alipay?subject="+user.getUserName()+"&totalAmount="+money+"&outTradeNo="+outTradeNo;
											}
										}
									}
								}else if (i == (position.length()-1)){
									List<Ticket> tickets2 = ticketDao.getTickes(map);
									for (Ticket ticket2 : tickets2){
										if(ticket2.getTicketTime()==null){
											//统一商品号
											boolean success = ticketDao.setOut_trade_no(map);
											if (b){
												path= "redirect:/open/alipay?subject="+user.getUserName()+"&totalAmount="+money+"&outTradeNo="+outTradeNo;
											}
										}
									}
								}
							}else {
								path= "error";
							}
					}
				}

			}
		}
		return path ;
	}

	// 修改个人信息
	@RequestMapping("/user/updatesUser")
	@ResponseBody
	public String updateUser(User user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//取出当前用户名
		String userName = auth.getName();
		//查询出该用户的全部信息
		User u = userdao.login(userName);
		//判断用户是否修改了密码
		if(u.getPassword().equalsIgnoreCase(user.getUserPassword())) {

		}else {
			String pass = GeneratePasswordEncoder.generatePasswordEncoder(user.getPassword());// 加密
			user.setUserPassword(pass);
		}//调用修改用户信息接口
		boolean b = userdao.updateUser(user);
		if (b) {//调用修改用户头像的接口
			b = userdao.updatePicture(user);
			return "success";
		} else {
			return "false";
		}
	}

	//获取用户订单详情
	@RequestMapping("/user/queryTicketById")
	@ResponseBody
	public PositionRelation positionRelation(int ticketId) {
		PositionRelation positionRelation = positionRelationDAO.ById(ticketId);
		if (positionRelation!=null){
			System.out.println("success");
		}
		return positionRelation;
	}
}
