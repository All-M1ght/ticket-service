package com.ticket.api;

import com.alibaba.fastjson.JSON;
import com.ticket.model.Ticket;
import com.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ticket")
public class TicketApi {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/bookTicket",method = RequestMethod.POST)
    public String bookTicket(@RequestBody String param) {
        Map<String,String> map = JSON.parseObject(param,Map.class);
//        Long userId = Long.parseLong(map.get("userId"));
        String uid = map.get("userId");
        Long tid = Long.parseLong(map.get("ticketId"));
        Map<String,String> respond = new HashMap<>();
        String res = this.restTemplate.getForObject("http://192.168.68.195:8006/user/findUser/" + uid, String.class);
        Map<String,String> map2 = JSON.parseObject(res,Map.class);
        String message = map2.get("message");
        if(!message.equals("success")){
            respond.put("message","user is not exist");
            return JSON.toJSONString(respond);
        }
        Ticket ticket = ticketService.bookTicket(tid);
        if(ticket!=null){
            Map<String,String> rfcParams = new HashMap<>();
            rfcParams.put("tid",tid+"");
            rfcParams.put("uid",uid+"");
            rfcParams.put("price",ticket.getPrice()+"");
            String rfcRes = restTemplate.postForObject(
                    "http://192.168.68.195:8008/order/creatOrder",
                    JSON.toJSONString(rfcParams),
                    String.class);
            Map<String,String> rfcResMap = JSON.parseObject(rfcRes,Map.class);
            String orderMessage = rfcResMap.get("message");
            if(orderMessage.equals("success")){
                respond.put("message","booked successfully");
                respond.put("order id",rfcResMap.get("order id"));
                respond.put("order state",rfcResMap.get("order state"));
                respond.put("order price",rfcResMap.get("order price"));

            }else {
                respond.put("message","create order error");
            }

        }else {
            respond.put("message","ticket is not available");
        }
        return JSON.toJSONString(respond);
    }

    @RequestMapping(value = "/sellTicket",method = RequestMethod.POST)
    public String sellTicket(@RequestBody String param) {
        Map<String,String> map = JSON.parseObject(param,Map.class);
        Long tid = Long.parseLong(map.get("tid"));
        Map<String,String> respond = new HashMap<>();
        if(ticketService.sellTicket(tid)){
            respond.put("message","success");
        }else {
            respond.put("message","error");
        }
        return JSON.toJSONString(respond);
    }


}
