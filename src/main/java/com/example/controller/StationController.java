package com.example.controller;

import com.example.model.FrequencyInfo;
import com.example.model.Response;
import com.example.model.TaskInfo;
import com.example.model.Station;
import com.example.service.TaskInfoService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuquan
 */
@RestController
@RequestMapping("/stations")
public class StationController {


    @Autowired
    private TaskInfoService taskInfoService;
    /**
     * 增加台站
     * @param station
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Boolean insertStation(@RequestBody Station station) {
        taskInfoService.insertStation1(station);
        taskInfoService.insertStation2(station);
        return true;
    }

    /**
     * 删除台站
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean deleteStation(@PathVariable int id) {
        taskInfoService.deleteStation1(id);
        taskInfoService.deleteStation2(id);
        return true;
    }

    /**
     * 更新台站
     * @param station
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Boolean updateStation(@RequestBody Station station) {
        taskInfoService.updateStation1(station);
        taskInfoService.updateStation2(station);
        return true;
    }

    /**
     * 带分页的查看台站列表
     * @return
     */
    @RequestMapping(value = "/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    public Response findByPage(@PathVariable int taskId,
                               @RequestParam(defaultValue = "0") Integer pageNo,
                               @RequestParam(defaultValue = "0") Integer pageSize) {
        Page<Station> pageInfo = taskInfoService.findStation1(taskId, pageNo, pageSize);
        Response response = new Response();
        response.setList(pageInfo);
        response.setTotal(pageInfo.getTotal());
        return response;
    }

}