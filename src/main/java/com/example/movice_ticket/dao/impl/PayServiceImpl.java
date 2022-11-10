package com.example.movice_ticket.dao.impl;


import com.alipay.api.AlipayApiException;

import com.example.movice_ticket.config.AlipayConfig;
import com.example.movice_ticket.dao.PayService;
import com.example.movice_ticket.model.AlipayBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

 @Autowired
 private AlipayConfig alipay;

 @Override
 public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
  return alipay.pay(alipayBean);
 }

@Override
public String aliquery(AlipayBean alipayBean) throws AlipayApiException {
	 
	return alipay.refund(alipayBean);
}


}
