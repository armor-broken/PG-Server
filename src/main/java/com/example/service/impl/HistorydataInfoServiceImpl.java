package com.example.service.impl;

import com.example.dao.db2.HistorydataInfo2Mapper;
import com.example.model.HistorydataInfo;
import com.example.service.HistorydataInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuquan on 2018/10/29.
 */
@Service
public class HistorydataInfoServiceImpl implements HistorydataInfoService {

    @Autowired
    private HistorydataInfo2Mapper historydataInfoMapper;



    /**
     * 查询频率表
     * @return
     */
    @Override
    public List<HistorydataInfo> findHistoryData() {
        return historydataInfoMapper.findHistoryData();
    }




}
