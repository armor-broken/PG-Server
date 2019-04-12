package com.example.service;

import com.example.model.HistorydataInfo;
import com.example.model.RecordInfo;
import com.example.model.TaskInfo;
import com.github.pagehelper.Page;

import java.util.List;

public interface HistorydataInfoService {
    List<HistorydataInfo> findHistoryData1();

    /**
     * 查询历史数据列表
     * @return
     */

    Page<RecordInfo> findHistoryList1(int pageNo, int pageSize);

}
