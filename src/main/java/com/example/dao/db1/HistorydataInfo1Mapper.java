package com.example.dao.db1;

import com.example.model.HistorydataInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper//声明成mybatis Dao层的Bean，也可以在配置类上使用@MapperScan("com.xiaolyuh.domain.mapper")注解声明
public interface HistorydataInfo1Mapper {
    List<HistorydataInfo> findHistoryData1();
}