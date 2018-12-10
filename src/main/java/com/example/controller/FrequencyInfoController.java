package com.example.controller;

import com.example.model.Response;
import com.example.model.FrequencyInfo;
import com.example.service.TaskInfoService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuquan
 */
@RestController
@RequestMapping("/frequencys")
public class FrequencyInfoController {


    @Autowired
    private TaskInfoService taskInfoService;
    /**
     * 增加频率
     * @param fre
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Boolean insertFre(@RequestBody FrequencyInfo fre) {
        taskInfoService.insertFre1(fre);
        taskInfoService.insertFre2(fre);
        return true;
    }

    /**
     * 删除频率
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean deleteFre(@PathVariable int id) {
        taskInfoService.deleteFre1(id);
        taskInfoService.deleteFre2(id);
        return true;
    }

    /**
     * 更新频率
     * @param fre
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Boolean updateFre(@RequestBody FrequencyInfo fre) {
        taskInfoService.updateFre1(fre);
        taskInfoService.updateFre2(fre);
        return true;
    }

    /**
     * 带分页的查看频率列表
     * @return
     */
    @RequestMapping(value = "/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    public Response findByPage(@PathVariable int taskId,
                               @RequestParam(defaultValue = "0") Integer pageNo,
                               @RequestParam(defaultValue = "0") Integer pageSize) {
        Page<FrequencyInfo> pageInfo = taskInfoService.findFre1(taskId, pageNo, pageSize);
        Response response = new Response();
        response.setList(pageInfo);
        response.setTotal(pageInfo.getTotal());
        return response;
    }

}