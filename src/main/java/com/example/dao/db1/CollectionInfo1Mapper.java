package com.example.dao.db1;

import com.example.model.CollectionInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper//声明成mybatis Dao层的Bean，也可以在配置类上使用@MapperScan("com.xiaolyuh.domain.mapper")注解声明
public interface CollectionInfo1Mapper {

    /**
     * 插入采集数据
     * @param collection
     * @return
     */
    boolean insert1(CollectionInfo collection);

    /**
     * 频率表
     * @return
     */
    List<Integer> selectFrequency1();

    List<Integer> findFrequency1(int id);

    /**
     * 插入采集信息
     * @param collection
     * @return
     */
    boolean insertCollection1(CollectionInfo collection);
}