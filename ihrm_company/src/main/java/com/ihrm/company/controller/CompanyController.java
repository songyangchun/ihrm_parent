package com.ihrm.company.controller;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.company.service.CompanyService;
import com.ihrm.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//解决跨域问题
@CrossOrigin
@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    //添加企业 POST /company
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result add(@RequestBody Company company) throws Exception{
        companyService.add(company);
        return Result.SUCCESS();
    }

    //根据id更新企业
    /**
     * 1.方法
     * 2.请求参数
     * 3.响应
     *  PUT /company/{companyId}
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable(value="id") String id, @RequestBody Company company ) {
        //业务操作
        company.setId(id);
        companyService.update(company);
        return new Result(ResultCode.SUCCESS);
    }


    //根据id删除企业 DELETE /company/{companyId}
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id")String id) throws Exception{
        companyService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }
    //根据id查询企业 GET /company/{companyId}
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result find(@PathVariable(name = "id") String id)throws Exception{
       Company company =  companyService.findById(id);
       Result result = new Result(ResultCode.SUCCESS);
       result.setData(company);
       return result;

    }
    //查询全部企业列表 GET /company
    @RequestMapping(value = "",method = RequestMethod.GET)
    public Result findAll()throws Exception{
        List<Company> companyList = companyService.findAll();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(companyList);
        return  result;

    }
}
