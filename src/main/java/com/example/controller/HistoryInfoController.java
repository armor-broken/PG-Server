package com.example.controller;

import com.example.model.HistorydataInfo;
import com.example.model.RecordInfo;
import com.example.model.Response;
import com.example.model.TaskInfo;
import com.example.service.HistorydataInfoService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuquan
 */
@RestController
@RequestMapping("/historydatas")
public class HistoryInfoController {


    @Autowired
    private HistorydataInfoService historydataInfoService;

    //获取当前GPS信息
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public List<HistorydataInfo> collectionInit() {
        return historydataInfoService.findHistoryData1();
    }    //获取当前GPS信息

    /**
     * 带分页的查看历史数据列表
     * @return
     */
    @RequestMapping(value = "/historylist",method = RequestMethod.GET)
    @ResponseBody
    public Response findByPage(@RequestParam(defaultValue = "0") Integer pageNo,
                               @RequestParam(defaultValue = "0") Integer pageSize) {
        Page<RecordInfo> pageInfo = historydataInfoService.findHistoryList1(pageNo, pageSize);
        Response response = new Response();
        response.setList(pageInfo);
        response.setTotal(pageInfo.getTotal());
        return response;
    }



}