package com.example.service;

import com.example.model.CollectionInfo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CollectionInfoService {
    /**
     * 插入台站信息
     * @param collection
     * @return
     */
    boolean insert1(@RequestBody CollectionInfo collection);

    /**
     * 查询频率表
     * @return
     */
    List<Integer> selectFrequency1();

    List<Integer> findFrequency1(int id);

    /**
     * 插入采集信息
     * @param collection
     * @return
     */
    boolean insertCollection1(@RequestBody CollectionInfo collection);

    /**
     * 插入台站信息
     * @param collection
     * @return
     */
    boolean insert2(@RequestBody CollectionInfo collection);

    /**
     * 查询频率表
     * @return
     */
    List<Integer> selectFrequency2();

    List<Integer> findFrequency2(int id);

    /**
     * 插入采集信息
     * @param collection
     * @return
     */
    boolean insertCollection2(@RequestBody CollectionInfo collection);

}
