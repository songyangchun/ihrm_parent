package com.platform.rcmd.service;


import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.rcmd.Plan;
import com.platform.rcmd.dao.PlanDao;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PlanService {

    @Autowired
    private PlanDao planDao;



    @Autowired
    private IdWorker idWorker;


    public String uploadImage(String id, MultipartFile file) throws Exception {
        //根据id查询计划        
        Plan plan = planDao.findById(id).get();
        //对上传文件进行Base64编码      
        String s = Base64.encode(file.getBytes());
        //拼接DataURL数据头        
        String dataUrl = new String("data:image/jpg;base64,"+s);
        plan.setImg(dataUrl);
        //保存图片信息        
        planDao.save(plan);
        return dataUrl;
    }


        /**
         * 1.保存提交的组团计划
         */

    public void save(Plan plan) {


        Date date = new Date();

        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

        //设置主键的值
        String id = idWorker.nextId()+"";
        plan.setId(id);
        plan.setCreatTime(dateFormat.format(date));
//        username从哪里获取
//        plan.setUserName("笔袋");

        //调用dao保存用户
        planDao.save(plan);
    }


    /**
     * 查询计划列表
     * */
    public List<Plan> findAll(){
        return planDao.findAll();
    }


    /**
     * 4.查询全部用户列表
     *   参数：userName
     */
    public Page findAll(String userName, int page , int size) {

        //1，需要查询条件
        Specification<Plan> spec = new Specification<Plan>() {
            /*
             * 动态拼接查询条件
             * */
            public Predicate toPredicate(Root<Plan> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                //根据请求的companyId是否为空构造查询条件
                if (!StringUtils.isEmpty(userName)){
                    list.add(criteriaBuilder.equal(root.get("userName").as(String.class),userName));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        //2，分页  spec：查询条件
//
        return planDao.findAll(spec, PageRequest.of(page-1,size));
    }


    /**
     * 删除发布的计划
     * */
    public void deleteById(String id){
        planDao.deleteById(id);
    }

    /*
     * 根据id查询发布的计划
     * */
    public Plan findById(String id){
        return planDao.findById(id).get();
    }


}
