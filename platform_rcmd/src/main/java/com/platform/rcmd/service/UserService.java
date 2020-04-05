package com.platform.rcmd.service;


import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.rcmd.User;
import com.platform.rcmd.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService {


    @Autowired
    private UserDao userDao;



    @Autowired
    private IdWorker idWorker;

    /**
     * 1.保存用户
     */
    public void save(User user) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        //设置主键的值
        String id = idWorker.nextId()+"";
        user.setId(id);
        user.setCreateTime(dateFormat.format(date));
        userDao.save(user);
        //调用dao保存用户

    }


    /**
     * 2.更新用户
     */
    public User update(User user) {
        //1.根据id查询用户
        User target = userDao.findById(user.getId()).get();
        userDao.save(user);
        return target;
    }


    /**
     * 根据mobile查询用户
     */
    public User findByMobile(String mobile) {
        return userDao.findByMobile(mobile);
    }


    /**
     * 查询全部用户列表（部分信息）
     * */
    public List<Map<String, Object>> findAll(){
        return userDao.getUserList();
    }

//    /**
//     * 查询全部用户列表（部分信息）
//     * */
//    public List<User> findAll(){
//        return userDao.findAll();
//    }



    /*
     * 根据id查询用户
     * */
    public User findById(String id){
        return userDao.findById(id).get();
    }

}
