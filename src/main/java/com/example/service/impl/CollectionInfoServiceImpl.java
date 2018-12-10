package com.example.service.impl;

import com.example.dao.db1.CollectionInfo1Mapper;
import com.example.dao.db2.CollectionInfo2Mapper;
import com.example.model.CollectionInfo;
import com.example.service.CollectionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

/**
 * Created by liuquan on 2018/10/29.
 */
@Service
public class CollectionInfoServiceImpl implements CollectionInfoService {

    @Autowired
    private CollectionInfo1Mapper collectionInfo1Mapper;

    @Autowired
    private CollectionInfo2Mapper collectionInfo2Mapper;

    /**
     * 前端json数据传入台站
     * @param collection
     * @return
     */
    private CollectionInfo getCollection(CollectionInfo collection) {
        CollectionInfo collectionInfo = new CollectionInfo();
        collectionInfo.setClcFre(collection.getClcFre());
        collectionInfo.setClcRf(collection.getClcRf());
        collectionInfo.setClcMpath(collection.getClcMpath());
        collectionInfo.setClcLon(collection.getClcLon());
        collectionInfo.setClcLat(collection.getClcLat());
        collectionInfo.setClcAlt(collection.getClcAlt());
        collectionInfo.setClcSatNum(collection.getClcSatNum());
        collectionInfo.setClcDev(collection.getClcDev());
        collectionInfo.setClcUTC(collection.getClcUTC());
        collectionInfo.setClcIdentify(collection.getClcIdentify());
        return collectionInfo;
    }


    /**
     * 新增数据
     * @param collection
     * @return
     */
    @Override
    public boolean insert1(@RequestBody CollectionInfo collection){
        collectionInfo1Mapper.insert1(collection);
        return true;
    }

    /**
     * 查询频率表
     * @return
     */
    @Override
    public List<Integer> selectFrequency1() {
        return collectionInfo1Mapper.selectFrequency1();
    }

    public List<Integer> findFrequency1(int id) {
        return collectionInfo1Mapper.findFrequency1(id);
    }

    /**
     * 插入采集信息
     * @param collection
     * @return
     */
    @Override
    public boolean insertCollection1(@RequestBody CollectionInfo collection){
        collectionInfo1Mapper.insertCollection1(collection);
        return true;
    }

    /**
     * 新增数据
     * @param collection
     * @return
     */
    @Override
    public boolean insert2(@RequestBody CollectionInfo collection){
        collectionInfo2Mapper.insert2(collection);
        return true;
    }

    /**
     * 查询频率表
     * @return
     */
    @Override
    public List<Integer> selectFrequency2() {
        return collectionInfo2Mapper.selectFrequency2();
    }

    public List<Integer> findFrequency2(int id) {
        return collectionInfo2Mapper.findFrequency2(id);
    }

    /**
     * 插入采集信息
     * @param collection
     * @return
     */
    @Override
    public boolean insertCollection2(@RequestBody CollectionInfo collection){
        collectionInfo2Mapper.insertCollection2(collection);
        return true;
    }



}
