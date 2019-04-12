package com.example.service.impl;

import com.example.dao.db1.HistorydataInfo1Mapper;
import com.example.dao.db2.HistorydataInfo2Mapper;
import com.example.model.HistorydataInfo;
import com.example.model.RecordInfo;
import com.example.model.TaskInfo;
import com.example.service.HistorydataInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuquan on 2018/10/29.
 */
@Service
public class HistorydataInfoServiceImpl implements HistorydataInfoService {

    @Autowired
    private HistorydataInfo1Mapper historydataInfo1Mapper;



    /**
     * 查询频率表
     * @return
     */
    @Override
    public List<HistorydataInfo> findHistoryData1() {
        return historydataInfo1Mapper.findHistoryData1();
    }


    /**
     * 带分页的历史数据查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<RecordInfo> findHistoryList1(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return historydataInfo1Mapper.findHistoryList1();
    }




}
