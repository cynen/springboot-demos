package com.bazn.vip.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 查询所有的表
 */
@Mapper
public interface TabAllDao {

    // 查询单表
    @Select({
            "<script>",
            "Select * FROM ${tabs}",
            "</script>"
    })
    List<Map<String,Object>> findTableValues(@Param("tabs") String tabs); // 采购订单明细总数

}
