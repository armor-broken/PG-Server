package com.example.controller;

import com.example.model.HistorydataInfo;
import com.example.model.Response;
import com.example.service.HistorydataInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuquan
 */
@RestController
@RequestMapping("/historydatas")
public class HistorydataInfoController {


    @Autowired
    private HistorydataInfoService historydataInfoService;

    //获取当前GPS信息
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public List<HistorydataInfo> collectionInit() {
        return historydataInfoService.findHistoryData();
    }


}