package com.bazn.vip.controller;


import com.bazn.vip.dao.TabAllDao;
import com.bazn.vip.multi.DataSource;
import com.bazn.vip.multi.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 *
 *
 */
@RestController
@RequestMapping("/tab")
public class TableController {

    // 跳过Service层.
    @Autowired
    private TabAllDao findAll;

    @GetMapping(value = "/all")
    @DataSource(DataSourceType.DB1)
    public List<Map<String, Object>> findAll(){
        return findAll.findTableValues("users");
    }

    @GetMapping(value = "/all2")
    @DataSource(DataSourceType.DB2)
    public List findAll2(){
        return findAll.findTableValues("SO_SOMain");
    }
}
