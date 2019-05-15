package com.grom.server.controller;


import com.grom.domain.entity.User;
import com.grom.server.redis.RedisUtil;
import com.grom.server.service.UserService;
import com.grom.util.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @Auther: lfh
 * @Date: 2019/5/14
 */
@RestController
@RequestMapping(value = "/user")
public class CheckCodeController {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private UserService userService;
    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public User all() {
        Object str = redisUtil.get("user");
        if(StringUtils.isEmpty(str)){
            User user =userService.getAll();
            redisUtil.set("user",user);
            return user;
        }else {
            System.out.println("-----------------"+str);
            return userService.getAll();
        }
    }


    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<User> list(@RequestParam Map<String, Object> params) {
        try {
            return userService.list(params);
        } catch (Exception e) {
            return new TableResultResponse<>().rel(false).msg(e.getMessage());
        }
    }
}
