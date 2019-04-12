package com.example.controller;

import com.example.model.TaskInfo;
import com.example.model.Response;
import com.example.service.TaskInfoService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuquan
 * @description: 测试任务
 */
@RestController
@RequestMapping("/tasks")
public class TaskInfoController {


    @Autowired
    private TaskInfoService taskInfoService;

    /**
     * 增加任务信息
     * @param task
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Boolean insertTask(@RequestBody TaskInfo task) {
        taskInfoService.insertTask1(task);
        taskInfoService.insertTask2(task);
        return true;
    }

    /**
     * 删除任务信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean deleteTask(@PathVariable int id) {
        taskInfoService.deleteTask1(id);
        taskInfoService.deleteTask2(id);
        return true;
    }

    /**
     * 更新任务信息
     * @param task
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Boolean updateTask(@RequestBody TaskInfo task) {
        taskInfoService.updateTask1(task);
        taskInfoService.updateTask2(task);
        return true;
    }

    /**
     * 带分页的查看台站信息列表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Response findByPage(@RequestParam(defaultValue = "0") Integer pageNo,
                               @RequestParam(defaultValue = "0") Integer pageSize) {
        Page<TaskInfo> pageInfo = taskInfoService.findTask1(pageNo, pageSize);
        Response response = new Response();
        response.setList(pageInfo);
        response.setTotal(pageInfo.getTotal());
        return response;
    }


}