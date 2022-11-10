package com.example.movice_ticket.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.example.movice_ticket.config.AlipayConfig;
import com.example.movice_ticket.dao.PayService;
import com.example.movice_ticket.dao.PositionRelationDAO;
import com.example.movice_ticket.dao.TicketDao;
import com.example.movice_ticket.model.AlipayBean;
import com.example.movice_ticket.model.PositionRelation;
import com.example.movice_ticket.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class AlipayController {

	/**
	 * 订单接口
	 */
	@Autowired
	private PayService payService;

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private PositionRelationDAO positionRelationDAO;

	@RequestMapping(value = "/open/alipay")
	@ResponseBody
	public String alipay(String subject, String totalAmount, String outTradeNo) throws AlipayApiException {
//		 System.out.println("pay------------");
		AlipayBean alipayBean = new AlipayBean();
//	  String outTradeNo = UUID.randomUUID().toString();
		alipayBean.setOut_trade_no(outTradeNo);
		alipayBean.setSubject(subject);
		alipayBean.setTotal_amount(totalAmount);
//	  alipayBean.setBody(body);
		return payService.aliPay(alipayBean);
	}


	//支付成功支付宝调用方法：
	@RequestMapping(value = "/paySuccess")

	public String topaySuccess(HttpServletRequest request, Model model) throws AlipayApiException, UnsupportedEncodingException {
		//获取支付宝GET过来反馈信息
//		 System.out.println("paysuccess+============");
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		//计算得出通知验证结果
		//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
		boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipayPublicKey, AlipayConfig.charset, "RSA2");

		if (verify_result) {//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			//该页面可做页面美工编辑
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			Map<String, Object> map = new HashMap<>();
			map.put("out_trade_no", out_trade_no);
			Ticket ticket = null;
			List<PositionRelation> positionRelationList = new ArrayList<>();
			List<Ticket> tickets = ticketDao.getTickelist(out_trade_no);//查询出这个商品号的全部订单
			int ticketId = 0;
			for (Ticket ticket2 : tickets) {
//					ticketId=ticket2.getTicketId();
				ticket = ticket2;
				map.put("ticketId", ticket2.getTicketId());
				if (ticket2.getTicketTime() == null) {
					boolean b = ticketDao.updateOut_trade_no(map);
				}
				//查询已选的信息
				if (ticket2.getPositionRelationId() > 0) {
					PositionRelation positionRelation = positionRelationDAO.ById(ticket2.getPositionRelationId());
					model.addAttribute("positionRelation", positionRelation);
					positionRelationList.add(positionRelation);
				}

			}
//				Ticket ticket = ticketDao.getTicket(ticketId);
//				List<PositionRelation> positionRelations = positionRelationDAO.queryById(map);
//				//支付宝交易号
//				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			if (positionRelationList != null) {
				model.addAttribute("positionRelations", positionRelationList);
				model.addAttribute("ticket", ticket);
				return "paySuccess";
			} else {
				return "error";
			}

		} else {
			return "error";
		}

	}

	/**
	 * 订单退款接口
	 */
	@RequestMapping(value = "/refund")
	@ResponseBody
	public String aliquery(String out_trade_no, String totalAmount) throws AlipayApiException {
		AlipayBean alipayBean = new AlipayBean();

		alipayBean.setOut_trade_no(out_trade_no);
		alipayBean.setTrade_no(null);
		alipayBean.setRefund_amount(totalAmount);
		Map<String, Object> map = new HashMap<>();
		map.put("out_trade_no", out_trade_no);
		map.put("orderState",2);
		boolean b = ticketDao.setorderState(map);
		return payService.aliquery(alipayBean);
	}



}
