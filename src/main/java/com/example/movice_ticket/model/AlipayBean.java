package com.example.movice_ticket.model;

public class AlipayBean {

		 /**商户订单号*/
		 private String out_trade_no;
		 /** * 订单名称*/
		 private String subject;
		 /*** 付款金额 */
		 private String total_amount;
		 /** * 商品描述 */
		 private String body;
		 /*** 超时时间参数 */
		 private String timeout_express= "10m";
		 /** * 产品编号 */
			private String product_code = "FAST_INSTANT_TRADE_PAY";
	/** * 退款金额 */
	private String refund_amount;
	/** * 支付宝交易号 */
	private  String trade_no;

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getRefund_amount() {
		return refund_amount;
	}

	public void setRefund_amount(String refund_amount) {
		this.refund_amount = refund_amount;
	}

	public String getOut_trade_no() {
			return out_trade_no;
		}
		public void setOut_trade_no(String out_trade_no) {
			this.out_trade_no = out_trade_no;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getTotal_amount() {
			return total_amount;
		}
		public void setTotal_amount(String total_amount) {
			this.total_amount = total_amount;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
		public String getTimeout_express() {
			return timeout_express;
		}
		public void setTimeout_express(String timeout_express) {
			this.timeout_express = timeout_express;
		}
		public String getProduct_code() {
			return product_code;
		}
		public void setProduct_code(String product_code) {
			this.product_code = product_code;
		}
			
		  
}
