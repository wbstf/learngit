package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public interface SeckillDao {
    /**减少库存
     *@param seckillId
     *@param killTime
     * @return  如果影响行数>1，表示更新语句记录的行数
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /*
     *根据id查找秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     *根据偏移量查看秒杀任务列表
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset , @Param("limit") int limit);

    /**
     * 使用存储过程执行秒杀
     * @param paramMap
     */
    void killByProcedure(Map<String, Object> paramMap);

}
