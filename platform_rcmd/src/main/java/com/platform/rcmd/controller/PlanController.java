package com.platform.rcmd.controller;


import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.rcmd.Plan;
import com.ihrm.domain.system.User;

import com.platform.rcmd.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

//解决跨域问题
@CrossOrigin
@RestController
@RequestMapping(value = "/plan")
public class PlanController extends BaseController {

    @Autowired
    private PlanService planService;

    //添加计划 POST /plan
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result add(@RequestBody Plan plan) throws Exception {
        planService.save(plan);
        return Result.SUCCESS();
    }


//    //查询全部计划列表 GET /plan
//    @RequestMapping(value = "",method = RequestMethod.GET)
//    public Result findAll()throws Exception{
//        List<Plan> planList = planService.findAll();
//        Result result = new Result(ResultCode.SUCCESS);
//        result.setData(planList);
//        return  result;
//
//    }


    /**
     * 查询全部计划列表 GET /plan
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result findAll(int page, int size, String userName) {

        //获取当前企业id
        //完成查询
        Page<Plan> pageUser = planService.findAll(userName, page, size);
        //构造返回结果
        PageResult<Plan> pageResult = new PageResult(pageUser.getTotalElements(), pageUser.getContent());
        return new Result(ResultCode.SUCCESS, pageResult);
    }


    //根据id删除计划 DELETE /plan/{planId}
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id) throws Exception {
        planService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据ID查询plan
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id") String id) {
        Plan plan = planService.findById(id);
        return new Result(ResultCode.SUCCESS, plan);
    }

    @RequestMapping(value = "/upload/{id}")
    public Result upload(@PathVariable String id, @RequestParam(name = "file") MultipartFile file) throws Exception {
        String image = planService.uploadImage(id, file);
        return new Result(ResultCode.SUCCESS, image);
    }
}
