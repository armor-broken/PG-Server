package com.example.service.impl;

//import com.example.annotation.DataSource;
//import com.example.common.ContextConst;
import com.example.dao.db1.TaskInfo1Mapper;
import com.example.dao.db2.TaskInfo2Mapper;
import com.example.model.TaskInfo;
import com.example.model.Station;
import com.example.model.CategorieInfo;
import com.example.model.FrequencyInfo;
import com.example.service.TaskInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by yuhao.wang on 2017/6/19.
 */
@Service
public class TaskInfoServiceImpl implements TaskInfoService {

    @Autowired
    private TaskInfo1Mapper taskInfo1Mapper;

    @Autowired
    private TaskInfo2Mapper taskInfo2Mapper;

    /**
     * 前端json数据传入台站实体函数
     * @param task
     * @return
     */
    private TaskInfo getTask(TaskInfo task) {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setId(task.getId());
        taskInfo.setCatId(task.getCatId());
        taskInfo.setName(task.getName());
        taskInfo.setStationId(task.getStationId());
        taskInfo.setStationName(task.getStationName());
        taskInfo.setStationConId(task.getStationConId());
        taskInfo.setFreId(task.getFreId());
        taskInfo.settaskFre(task.gettaskFre());
        taskInfo.settaskProgram(task.gettaskProgram());
        taskInfo.setStationLon(task.getStationLon());
        taskInfo.setStationLat(task.getStationLat());
        taskInfo.setStationAlt(task.getStationAlt());
        taskInfo.setStationAntHei(task.getStationAntHei());
        taskInfo.setStationAntForm(task.getStationAntForm());
        taskInfo.setStationAntOri(task.getStationAntOri());
        taskInfo.setStationPower(task.getStationPower());
        taskInfo.setStationSyn(task.getStationSyn());
        return taskInfo;
    }

    /**
     * 前端json数据传入频率
     * @param fre
     * @return
     */
    private FrequencyInfo getFre(FrequencyInfo fre) {
        FrequencyInfo frequencyInfo = new FrequencyInfo();
        frequencyInfo.setId(fre.getId());
        frequencyInfo.setTaskId(fre.getTaskId());
        frequencyInfo.setTaskFre(fre.getTaskFre());
        frequencyInfo.setTaskProgram(fre.getTaskProgram());
        return frequencyInfo;
    }

    /**
     * 前端json数据传入台站
     * @param station
     * @return
     */
    private Station getStation(Station station) {
        Station stationInfo = new Station();
        stationInfo.setId(station.getId());
        stationInfo.setTaskId(station.getTaskId());
        stationInfo.setStationName(station.getStationName());
        stationInfo.setStationLon(station.getStationLon());
        stationInfo.setStationLat(station.getStationLat());
        stationInfo.setStationAlt(station.getStationAlt());
        stationInfo.setStationAntHei(station.getStationAntHei());
        stationInfo.setStationAntForm(station.getStationAntForm());
        stationInfo.setStationAntOri(station.getStationAntOri());
        stationInfo.setStationPower(station.getStationPower());
        stationInfo.setSyn(station.getSyn());
        return stationInfo;
    }

    /**
     * 查找任务类型
     * @return
     */
    @Override
    public List<CategorieInfo> findCategorie1() {
        return taskInfo1Mapper.findCategorie1();
    }

    /**
     * 新增任务
     * @param task
     * @return
     */
    @Override
    public boolean insertTask1(@RequestBody TaskInfo task){
        taskInfo1Mapper.insertTask1(getTask(task));
        return true;
    }

    /**
     * 删除任务
     * @param id
     * @return
     */
    @Override
    public boolean deleteTask1(int id){
        taskInfo1Mapper.deleteTask1(id);
        return true;
    }

    /**
     * 编辑任务
     * @param task
     * @return
     */
    @Override
    public boolean updateTask1(@RequestBody TaskInfo task){
        taskInfo1Mapper.updateTask1(getTask(task));
        return true;
    }

    /**
     * 分页数据
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    @Override
    public Page<TaskInfo> findTask1(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return taskInfo1Mapper.findTask1();
    }

    /**
     * 新增频率
     * @param fre
     * @return
     */
    @Override
    public boolean insertFre1(@RequestBody FrequencyInfo fre){
        taskInfo1Mapper.insertFre1(getFre(fre));
        return true;
    }

    /**
     * 删除频率
     * @param id
     * @return
     */
    @Override
    public boolean deleteFre1(int id){
        taskInfo1Mapper.deleteFre1(id);
        return true;
    }

    /**
     * 编辑频率
     * @param fre
     * @return
     */
    @Override
    public boolean updateFre1(@RequestBody FrequencyInfo fre){
        taskInfo1Mapper.updateFre1(getFre(fre));
        return true;
    }

    /**
     * 分页频率
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    @Override
    public Page<FrequencyInfo> findFre1(int taskId, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return taskInfo1Mapper.findFre1(taskId);
    }

    /**
     * 新增台站
     * @param station
     * @return
     */
    @Override
    public boolean insertStation1(@RequestBody Station station){
        taskInfo1Mapper.insertStation1(getStation(station));
        return true;
    }

    /**
     * 删除台站
     * @param id
     * @return
     */
    @Override
    public boolean deleteStation1(int id){
        taskInfo1Mapper.deleteStation1(id);
        return true;
    }

    /**
     * 编辑台站
     * @param station
     * @return
     */
    @Override
    public boolean updateStation1(@RequestBody Station station){
        taskInfo1Mapper.updateStation1(getStation(station));
        return true;
    }

    /**
     * 分页台站
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    @Override
    public Page<Station> findStation1(int taskId, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return taskInfo1Mapper.findStation1(taskId);
    }

//    数据库2

    /**
     * 查找任务类型
     * @return
     */
    @Override
    public List<CategorieInfo> findCategorie2() {
        return taskInfo2Mapper.findCategorie2();
    }

    /**
     * 新增任务
     * @param task
     * @return
     */
    @Override
    public boolean insertTask2(@RequestBody TaskInfo task){
        taskInfo2Mapper.insertTask2(getTask(task));
        return true;
    }

    /**
     * 删除任务
     * @param id
     * @return
     */
    @Override
    public boolean deleteTask2(int id){
        taskInfo2Mapper.deleteTask2(id);
        return true;
    }

    /**
     * 编辑任务
     * @param task
     * @return
     */
    @Override
    public boolean updateTask2(@RequestBody TaskInfo task){
        taskInfo2Mapper.updateTask2(getTask(task));
        return true;
    }

    /**
     * 分页数据
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    @Override
    public Page<TaskInfo> findTask2(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return taskInfo2Mapper.findTask2();
    }

    /**
     * 新增频率
     * @param fre
     * @return
     */
    @Override
    public boolean insertFre2(@RequestBody FrequencyInfo fre){
        taskInfo2Mapper.insertFre2(getFre(fre));
        return true;
    }

    /**
     * 删除频率
     * @param id
     * @return
     */
    @Override
    public boolean deleteFre2(int id){
        taskInfo2Mapper.deleteFre2(id);
        return true;
    }

    /**
     * 编辑频率
     * @param fre
     * @return
     */
    @Override
    public boolean updateFre2(@RequestBody FrequencyInfo fre){
        taskInfo2Mapper.updateFre2(getFre(fre));
        return true;
    }

    /**
     * 分页频率
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    @Override
    public Page<FrequencyInfo> findFre2(int taskId, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return taskInfo2Mapper.findFre2(taskId);
    }

    /**
     * 新增台站
     * @param station
     * @return
     */
    @Override
    public boolean insertStation2(@RequestBody Station station){
        taskInfo2Mapper.insertStation2(getStation(station));
        return true;
    }

    /**
     * 删除台站
     * @param id
     * @return
     */
    @Override
    public boolean deleteStation2(int id){
        taskInfo2Mapper.deleteStation2(id);
        return true;
    }

    /**
     * 编辑台站
     * @param station
     * @return
     */
    @Override
    public boolean updateStation2(@RequestBody Station station){
        taskInfo2Mapper.updateStation2(getStation(station));
        return true;
    }

    /**
     * 分页台站
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    @Override
    public Page<Station> findStation2(int taskId, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return taskInfo2Mapper.findStation2(taskId);
    }




}
