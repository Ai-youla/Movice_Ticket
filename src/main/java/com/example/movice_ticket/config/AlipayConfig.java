package com.example.movice_ticket.config;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.example.movice_ticket.model.AlipayBean;
import org.springframework.stereotype.Component;

/**
 * 支付宝支付接口
 */
@Component
public class AlipayConfig { 
	public static String  serverUrl = "https://openapi.alipaydev.com/gateway.do";//支付宝网关
	//支付宝沙箱的商家Id
	public static  String appId =  "2021000119633271";
	//商家私钥
	public static  String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDl7puz/nF5rGTKnEjLWdutV6AWFzLXOudWuhg26rAa+/SPQcl3e9e3dy6rqn+42NA6YYYPQ0kVYc8VrZvD6ZWUuLqX89nEofyomk37PtHBHUW5xqBomHTS24HT5bben9H6/HoeaSitofklHHUZDaKkRBTELbSnhkTNht7hFhMcymr11KtvqfOfh36VJdb3Tc7g8Bb4B4acyDH/ptVA65JUW2erlfgWSkJUXpBeq91zswbigmU6bhlC8sMSHspCMXdmZI5FB2RTcbPdr3Fsr1q3BkKZ00S8rvXUfFD50GqjZBPtnfWPRv2xgkaGfXEEex6uYDcWJvTgvPGT3e9IEh8pAgMBAAECggEBAKPY5XFdFqW6uFQ9DA6EaOtcLsKvI1aGLgX6NHklwfpoorxXB+ayBGVDc6l2pO8m6TNA/ZPwwaqP+U6cWQxBbor9HFJdvUfGmtHWq00nVbdEUcRxiysdwNnk+93wquB3iN1OfwMespMQggb1AWip88UUkqu9WieqoWrH+hjxbwsqxUhqhT/+zVlPu96HdqkYhJa9qOhXxsY+2Se8OSjoCKSO/kmF6PTYIX2FLqDXgL2SlNRUX2mcwWe/IEvYG9uat5WrlvNp7ZEndG34eIcLSfwrj5Ux+RiDz8ifE+MQOqyHVEbBsUL2kcXREk8kihb4wgZf3X9DMVcxi/z+rM1Zp3UCgYEA8yz30ychy2/c7Z4cHbHOVUdd3YDxhdyw0xQ9scmZe4e39EPumSzxaF70G7jXDvMPIIqCenEEMG/jRaHoNbaAUJ1v9q7cilZSRpWppz3ItBXG0VJcpg8AOM4PGp71h6TRl8uBowgnNLSYHYlLCqrX4yCQB6oHeHwt10N1idSJJEMCgYEA8g7XyBfVEvR8T5P7b/pz56d4q7mvQ73qyTvMJ5wKmA3d88AKEj7+DQ4pdWM+nGIRL5RaFMxn4ZMt98i9eaBv3+Y0aXVOLPK1G0GxGyUSxdWmUvSxQXtRQIzZYpHCR+cM4B5fTvSmKhw6FntR+n7JrmDFVnODlWXrSCCEJUsnjiMCgYEAn+06C19YBXBLLubl/M18Mm+eFtf/f5b1n8xXCr7YNvq6C2ZF2+1H0ulnx3eX0Fgf4bnWN+pt4+Eh6iCiE/kziFESWn0Dw+MjdxJkof2rKmyL9gwhrIB/F9zE6DAUsCHASgFn/62UA0CmLf6mIJzdIJ3Au5miEiYxC7ps6bmie/sCgYBedrxvMh3NT+xTggTWJn+lIA5Pth9X6H4AGn4J1zOrM34y6fcEN+EdZzF2xkxg60SZtOOSylKgDbgawxRuqtX+IvDBVy4zDGTxRim/1jAFVOOb7DJgw6dp8Y3Eke2n8dtse23mekN+m5VvwKC1iVVb4BgjLOFlJGk1tLrdcv700QKBgCSCQW3AjXGTgUjLNb98UfuyyZZGxtUe9YY8E2jeWvFRrD79IxYAkAiTmEHredbPEZ/lz6TJFZ9CpnaNckV3NA7FfOiZ/OLPhfm4Tp7gJLNjNRXg0XpHmX57zGHp/RwbyX5TjmU2FkRMiWwpDqJuMMEngJyTx08TXxWadATZpvmb";
	public static  String format = "json";
	public static  String charset = "UTF-8";//字符编码格式
	//支付宝公钥
	public static  String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlo3CMrtwjm0fi0gEmO2zNYQYR9bDtUNme3MPAWE6fjkkSPv0qN+jvOI6XIguyTZm54ZkFDHf5rMFhZD0xvkXQJMY3MFKxz/LHCWw66WzYE9cGiNXICo5e0206Ai7KtT5yyWd3jBV8TysCnNgvf4rAqNq+TN3rMIiGFbR2L246u4btZe5h6B571nXsYqmKn2e6gZDtkC0GrYlfqnK7PA511UCjJqIySDWVDFVlFAevl9cz30CuKwsV7ukwT8oRA9XItQsTA5IlIaw/yKCh3q2NLQ7dVD+OSh+oBti8cAoM3mzxDdwKpqv5e4zGPrI8eGLwtqI4ijvX54n4L0WzDdFYwIDAQAB";
	//签名方式
	public static  String signType = "RSA2";
	
	/**
	  * 支付接口
	  * @param alipayBean
	  * @return
	  * @throws AlipayApiException
	  */
	 public String pay(AlipayBean alipayBean) throws AlipayApiException {

		String returnUrl = "http://localhost:8090/paySuccess";
		String notifyUrl = "http://localhost:8090/paySuccess";
		 // 1、获得初始化的AlipayClient
	  AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
	  // 2、设置请求参数
	  AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
	  // 页面跳转同步通知页面路径
	  alipayRequest.setReturnUrl(returnUrl);
	  // 服务器异步通知页面路径
	  alipayRequest.setNotifyUrl(notifyUrl);
	  // 封装参数
	  alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
	  // 3、请求支付宝进行付款，并获取支付结果
	  String result = alipayClient.pageExecute(alipayRequest).getBody();
	  // 返回付款信息
	  return result;
	 }
	 /**
	  * 支付退款接口
	  * @param alipayBean
	  * @return
	  * @throws AlipayApiException
	  */
	 public String refund(AlipayBean alipayBean) throws AlipayApiException {

		String returnUrl = "http://localhost:8090/refundSuccess";//http://localhost:8090/refundSuccess
		 String notifyUrl = "http://localhost:8090/refundSuccess2";//http://localhost:8090/paySuccess
		 // 1、获得初始化的AlipayClient
	  AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
	  // 2、设置请求参数
		 AlipayTradeRefundRequest alipay_request = new AlipayTradeRefundRequest();

	  // 页面跳转同步通知页面路径
	  alipay_request.setReturnUrl(returnUrl);
	  // 服务器异步通知页面路径
	  alipay_request.setNotifyUrl(notifyUrl);
	  // 封装参数
	  alipay_request.setBizContent(JSON.toJSONString(alipayBean));
	  // 3、请求支付宝进行退款
		 AlipayTradeRefundResponse response = null;
		 response = alipayClient.execute(alipay_request);
//		 String result   =alipayClient.execute(alipay_request).getBody();
		 String s = "";
		 if(response.isSuccess()){
			 s = "success";
		 }else {
			 s = "false";
		 }
	  // 返回退款订单信息
	  return s;
	 }
}
