package com.platform.rcmd.dao;

import com.ihrm.domain.rcmd.User;
import com.ihrm.domain.rcmd.UserNameAndMobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    public User findByMobile(String mobile);

    @Query(value = "select mobile as 'account' ,user_name as 'nickname' from rcmd_user", nativeQuery = true)
    List<Map<String, Object>>getUserList();
}
