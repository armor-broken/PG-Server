package com.example.dao.db2;

import com.example.model.TaskInfo;
import com.example.model.Station;
import com.example.model.CategorieInfo;
import com.example.model.FrequencyInfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper//声明成mybatis Dao层的Bean，也可以在配置类上使用@MapperScan("com.xiaolyuh.domain.mapper")注解声明
public interface TaskInfo2Mapper {
    /**
     * 查询任务类型
     * @return
     */
    List<CategorieInfo> findCategorie2();

    /**
     * 插入任务
     * @param task
     * @return
     */
    boolean insertTask2(TaskInfo task);

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
    boolean updateTask2(TaskInfo task);

    /**
     * 分页查询任务
     *
     * @return
     */
    Page<TaskInfo> findTask2();

    /**
     * 插入频率
     * @param fre
     * @return
     */
    boolean insertFre2(FrequencyInfo fre);

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
    boolean updateFre2(FrequencyInfo fre);

    /**
     * 分页查询数据
     *
     * @return
     */
    Page<FrequencyInfo> findFre2(int taskId);

    /**
     * 插入台站
     * @param station
     * @return
     */
    boolean insertStation2(Station station);

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
    boolean updateStation2(Station station);

    /**
     * 分页查询数据
     *
     * @return
     */
    Page<Station> findStation2(int taskId);


}