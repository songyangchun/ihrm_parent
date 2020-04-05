package com.ihrm.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



/**
 * @author song
 * @date 2019/11/26
 * 分页数据响应对象
 * {
 *     success：是否成功
 *     code：返回码
 *     message:返回信息
 *     data :返回数据
 *          {
 *              total,//总条数
 *              rows //数据列表
 *          }
 * }
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    private Long total;
    private List<T> rows;
}
