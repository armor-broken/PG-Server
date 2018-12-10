package com.example.dao.db2;

import com.example.model.CollectionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper//声明成mybatis Dao层的Bean，也可以在配置类上使用@MapperScan("com.xiaolyuh.domain.mapper")注解声明
public interface CollectionInfo2Mapper {

    /**
     * 插入采集数据
     * @param collection
     * @return
     */
    boolean insert2(CollectionInfo collection);

    /**
     * 频率表
     * @return
     */
    List<Integer> selectFrequency2();

    List<Integer> findFrequency2(int id);

    /**
     * 插入采集信息
     * @param collection
     * @return
     */
    boolean insertCollection2(CollectionInfo collection);
}