package com.example.controller;

import com.example.model.CategorieInfo;
import com.example.service.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuquan
 * @description: 测试任务类型
 */
@RestController
@RequestMapping("/task")
public class TaskCateController {
    @Autowired
    private TaskInfoService taskInfoService;

    /**
     * 带分页的查看台站信息列表
     * @return
     */
    @RequestMapping(value = "/categories",method = RequestMethod.GET)
    @ResponseBody
    public List<CategorieInfo> findCategorie() {
        return taskInfoService.findCategorie1();
    }
}