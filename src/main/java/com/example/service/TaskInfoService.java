package com.example.service;

import com.example.model.TaskInfo;
import com.example.model.FrequencyInfo;
import com.example.model.Station;
import com.example.model.CategorieInfo;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TaskInfoService {
    /**
     * 插入任务
     * @param task
     * @return
     */
    boolean insertTask1(@RequestBody TaskInfo task);

    /**
     * 删除任务
     * @param id
     * @return
     */
    boolean deleteTask1(int id);

    /**
     * 更新任务
     * @param task
     * @return
     */
    boolean updateTask1(@RequestBody TaskInfo task);

    /**
     * 查询任务类型
     * @return
     */
    List<CategorieInfo> findCategorie1();

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    Page<TaskInfo> findTask1(int pageNo, int pageSize);

    /**
     * 插入频率
     * @param fre
     * @return
     */
    boolean insertFre1(@RequestBody FrequencyInfo fre);

    /**
     * 删除频率
     * @param id
     * @return
     */
    boolean deleteFre1(int id);

    /**
     * 更新频率
     * @param fre
     * @return
     */
    boolean updateFre1(@RequestBody FrequencyInfo fre);

    /**
     * 分页查询频率
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    Page<FrequencyInfo> findFre1(int taskId, int pageNo, int pageSize);

    /**
     * 插入台站
     * @param station
     * @return
     */
    boolean insertStation1(@RequestBody Station station);

    /**
     * 删除台站
     * @param id
     * @return
     */
    boolean deleteStation1(int id);

    /**
     * 更新台站
     * @param station
     * @return
     */
    boolean updateStation1(@RequestBody Station station);

    /**
     * 分页查询台站
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    Page<Station> findStation1(int taskId, int pageNo, int pageSize);

//    数据库2
    /**
     * 插入任务
     * @param task
     * @return
     */
    boolean insertTask2(@RequestBody TaskInfo task);

    /**
     * 删除任务
     * @param id
     * @return
     */
    boolean deleteTask2(int id);

    /**
     * 更新任务
     * @param task
     * @return
     */
    boolean updateTask2(@RequestBody TaskInfo task);

    /**
     * 查询任务类型
     * @return
     */
    List<CategorieInfo> findCategorie2();

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    Page<TaskInfo> findTask2(int pageNo, int pageSize);

    /**
     * 插入频率
     * @param fre
     * @return
     */
    boolean insertFre2(@RequestBody FrequencyInfo fre);

    /**
     * 删除频率
     * @param id
     * @return
     */
    boolean deleteFre2(int id);

    /**
     * 更新频率
     * @param fre
     * @return
     */
    boolean updateFre2(@RequestBody FrequencyInfo fre);

    /**
     * 分页查询频率
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    Page<FrequencyInfo> findFre2(int taskId, int pageNo, int pageSize);

    /**
     * 插入台站
     * @param station
     * @return
     */
    boolean insertStation2(@RequestBody Station station);

    /**
     * 删除台站
     * @param id
     * @return
     */
    boolean deleteStation2(int id);

    /**
     * 更新台站
     * @param station
     * @return
     */
    boolean updateStation2(@RequestBody Station station);

    /**
     * 分页查询台站
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    Page<Station> findStation2(int taskId, int pageNo, int pageSize);
}
