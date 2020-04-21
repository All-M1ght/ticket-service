package com.order.api;

import com.alibaba.fastjson.JSON;
import com.order.model.Order;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderApi {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/creatOrder",method = RequestMethod.POST)
    public String creatOrder(@RequestBody String param) {
        Map<String,String> map = JSON.parseObject(param,Map.class);
        Long uid = Long.parseLong(map.get("uid"));
        Long tid = Long.parseLong(map.get("tid"));
        Integer price = Integer.parseInt(map.get("price"));
        Order model = new Order(uid, tid, 0,price);
        Map<String,String> respond = new HashMap<>();
        Order order = orderService.creatOrder(model);
        respond.put("message","success");
        respond.put("order id",order.getId()+"");
        respond.put("order state",order.getState()+"");
        respond.put("order price",order.getPrice()+"");
        return JSON.toJSONString(respond);
    }
    @RequestMapping(value = "/pay4Order",method = RequestMethod.POST)
    public String pay4Order(@RequestBody String param) {
        Map<String,String> map = JSON.parseObject(param,Map.class);
        Long oid = Long.parseLong(map.get("oid"));
        Integer price = Integer.parseInt(map.get("price"));
        Map<String,String> respond = new HashMap<>();
        Order order = orderService.payOrder(oid,price);
        if(order == null){
            respond.put("message","payment error");
        }else {
            Map<String,String> rfcParams = new HashMap<>();
            rfcParams.put("tid",order.getTid()+"");

            String rfcRes = restTemplate.postForObject(
                    "http://192.168.68.195:8007/ticket/sellTicket",
                    JSON.toJSONString(rfcParams),
                    String.class);
            Map<String,String> rfcResMap = JSON.parseObject(rfcRes,Map.class);
            String message = rfcResMap.get("message");
            if(message.equals("success"))
                respond.put("message","success");
            else
                respond.put("message","sell ticket error");
        }
        return JSON.toJSONString(respond);
    }

}
