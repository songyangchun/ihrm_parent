package com.ihrm.company.service;


import com.ihrm.common.utils.IdWorker;
import com.ihrm.company.dao.CompanyDao;
import com.ihrm.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    //在service中注入idworkr
    @Autowired
    private IdWorker idWorker;

    /**
     * 添加企业
     * 1，配置idworkr到工程
     * 2，在service中注入idworkr
     * 3，通过idworkr生成id
     * 4，添加企业
     * */
    public void add(Company company){
        //基本属性设置，id生成器
        //通过idworkr生成id(雪花算法)
        String id =  idWorker.nextId()+"";
        company.setId(id);
       //默认状态
        company.setAuditState("0"); //0 未审核 1 已审核
        company.setState(1); //0 未激活 1 已激活
        companyDao.save(company);
    }


    /**
     * 更新企业
     * 1,参数：封装到Company
     * 2,根据id查询企业对象
     * 3，设置修改的属性
     * 4，调用dao完成更新
     * */
    public void update(Company company){
        Company temp = companyDao.findById(company.getId()).get();
        temp.setName(company.getName());
        companyDao.save(temp);

    }


    /**
     * 删除企业
     * */
    public void deleteById(String id){
      companyDao.deleteById(id);
    }

    /**
     * 根据id查询企业
     * */
    public Company findById(String id){
        return companyDao.findById(id).get();
    }


    /**
     * 查询企业列表
     * */
    public List<Company> findAll(){
        return companyDao.findAll();
    }
}
