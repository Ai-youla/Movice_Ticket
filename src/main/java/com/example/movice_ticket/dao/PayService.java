package com.example.movice_ticket.dao;


import com.alipay.api.AlipayApiException;
import com.example.movice_ticket.model.AlipayBean;

/**
 * 支付服务
 */

public interface PayService {

 /**
  * 支付宝支付接口
  * @param alipayBean
  * @return
  * @throws AlipayApiException
  */
 String aliPay(AlipayBean alipayBean) throws AlipayApiException;
 
 /**
  * 支付宝退款接口
  * @param alipayBean
  * @return
  * @throws AlipayApiException
  */
 String aliquery(AlipayBean alipayBean) throws AlipayApiException;


}
