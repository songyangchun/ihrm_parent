package com.platform.rcmd.controller;


import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;

import com.ihrm.common.exception.CommonException;
import com.ihrm.common.utils.JwtUtil;
import com.ihrm.domain.rcmd.User;
import com.ihrm.domain.rcmd.UserNameAndMobile;
import com.platform.rcmd.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

//解决跨域问题
@CrossOrigin
@RestController
@RequestMapping(value = "/plan")
public class UserController  extends BaseController {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtils;

    //保存注册用户信息 POST /user/reg/
    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public Result add(@RequestBody User plan) throws Exception{
        userService.save(plan);
        return Result.SUCCESS();
    }



    /**
     * 用户登录
     *  1.通过service根据mobile查询用户
     *  2.比较password
     *  3.生成jwt信息
     *
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Result login(@RequestBody Map<String,String> loginMap) {
        String mobile = loginMap.get("mobile");
        String password = loginMap.get("password");
        User user = userService.findByMobile(mobile);
        //登录失败
        if(user == null || !user.getPassword().equals(password)) {
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
        else {
            //登录成功
            String token = jwtUtils.createJwt(user.getId(), user.getUserName());
            return new Result(ResultCode.SUCCESS,token);

        }
    }


    /**
     * 用户登录成功之后，获取用户信息
     *      1.获取用户id
     *      2.根据用户id查询用户
     *      3.构建返回值对象
     *      4.响应
     */
    @RequestMapping(value="/profile",method = RequestMethod.POST)
    public Result profile(HttpServletRequest request) throws Exception {
//
//        /**
//         * 从请求头信息中获取token数据
//         *   1.获取请求头信息：名称=Authorization
//         *   2.替换Bearer+空格
//         *   3.解析token
//         *   4.获取clamis
//         */
        //1.获取请求头信息：名称=Authorization
        String authorization = request.getHeader("Authorization");
        if(StringUtils.isEmpty(authorization)) {
            throw new CommonException(ResultCode.UNAUTHENTICATED);
        }
        //2.替换Bearer+空格
        String token = authorization.replace("Bearer ","");
        //3.解析token
        Claims claims = jwtUtils.parseJwt(token);
        String userid = claims.getId();
        User user = userService.findById(userid);
        return new Result(ResultCode.SUCCESS,user);
//        ProfileResult result = new ProfileResult(user);
//        return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 根据id查询user
     */
    @RequestMapping(value="/user/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable(value="id") String id) {
        User user = userService.findById(id);
        return new Result(ResultCode.SUCCESS,user);
    }

    /**
     * 修改user
     */
    @RequestMapping(value="/user/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable(value="id") String id,@RequestBody User user) {
        //1.设置修改的部门id
        user.setId(id);
        //2.调用service更新
        User user1 = userService.update(user);
        return new Result(ResultCode.SUCCESS,user1);
    }

    //查询全部用户列表（部分信息）
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Result findAll()throws Exception{
        List<Map<String, Object>> userList = userService.findAll();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(userList);
        return  result;

    }



}
