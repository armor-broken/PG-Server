package com.example.dao.db1;

import com.example.model.CategorieInfo;
import com.example.model.FrequencyInfo;
import com.example.model.Station;
import com.example.model.TaskInfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper//声明成mybatis Dao层的Bean，也可以在配置类上使用@MapperScan("com.xiaolyuh.domain.mapper")注解声明
public interface TaskInfo1Mapper {
    /**
     * 查询任务类型
     * @return
     */
    List<CategorieInfo> findCategorie1();

    /**
     * 插入任务
     * @param task
     * @return
     */
    boolean insertTask1(TaskInfo task);

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
    boolean updateTask1(TaskInfo task);

    /**
     * 分页查询任务
     *
     * @return
     */
    Page<TaskInfo> findTask1();

    /**
     * 插入频率
     * @param fre
     * @return
     */
    boolean insertFre1(FrequencyInfo fre);

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
    boolean updateFre1(FrequencyInfo fre);

    /**
     * 分页查询数据
     *
     * @return
     */
    Page<FrequencyInfo> findFre1(int taskId);

    /**
     * 插入台站
     * @param station
     * @return
     */
    boolean insertStation1(Station station);

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
    boolean updateStation1(Station station);

    /**
     * 分页查询数据
     *
     * @return
     */
    Page<Station> findStation1(int taskId);
}