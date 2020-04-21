package com.user.api;
import com.alibaba.fastjson.JSON;
import com.user.model.User;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserApi {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public String deleteUser(@RequestBody String user) {
        Map<String,String> map =JSON.parseObject(user,Map.class);
        Long userId = Long.parseLong(map.get("userId"));
        User model = new User(userId);
        Map<String,String> res = new HashMap();
        if(userService.findUserById(model) != null){
            userService.deleteUser(model);
            res.put("message","success");
        }else{
            res.put("message","This user does not exist");
        }
        return JSON.toJSONString(res);
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addUser(@RequestBody String user) {
        Map<String,String> map =JSON.parseObject(user,Map.class);
//        Long userId = Long.parseLong(map.get("userId"));
        String userName = map.get("userName");
        User model = new User(userName);
        Map<String,String> res = new HashMap();
        User newUser = userService.addNewUser(model);
        if(newUser!=null){
            res.put("message","success");
            res.put("id",newUser.getUserId()+"");
            res.put("username",newUser.getUserName());
        }
        else
            res.put("message","dababase error");
        return JSON.toJSONString(res);
    }
    @RequestMapping(value = "/findUser/{userId}",method = RequestMethod.GET)
    public String findUser(@PathVariable Long userId) {
        User model = new User(userId);
        Map<String,String> res = new HashMap();
        User newUser = userService.findUserById(model);
        if(newUser!=null){
            res.put("message","success");
        }
        else
            res.put("message","error");
        return JSON.toJSONString(res);
    }
}
