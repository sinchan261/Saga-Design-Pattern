package com.example.paymentservice.payment.Service;


import com.example.CoreService.CoreApplication.Dao.CreditCardProcessRequest;
import com.example.CoreService.CoreApplication.Exceptions.CreditCardProcessorUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class CreditCardProcessorRemoteServiceImple {
    @Value("${remote.ccp.url}")
    private  String ccpRemoteServiceUrl;


    private RestClient restClient;



    public void process(BigInteger cardNumber, BigDecimal paymentAmount){
        try{
            var request = new CreditCardProcessRequest(cardNumber,paymentAmount);
           restClient.post().uri(ccpRemoteServiceUrl + "/ccp/process").body(request).retrieve().toBodilessEntity();
//
      }catch (ResourceAccessException e){
            throw new CreditCardProcessorUnavailableException(e);

        }
    }
}
