package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit依赖，junit启动时，加载springIoc容器;
 * spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {
//    注入Dao依赖
    @Autowired
    private SeckillDao seckillDao;


    @Test
    public void reduceNumber() throws Exception {
       Date killTime= new Date();
       int updateCount = seckillDao.reduceNumber(1000L,killTime);
        System.out.println("updateCount"+updateCount);
    }

    @Test
    public void queryById() throws Exception {

        long id = 1000;
        Seckill seckill=  seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
        /**
         * 1000元秒杀iPhone6s
         Seckill{seckillId=1000,
         name='1000元秒杀iPhone6s',
         number=100,
         startTime=Sun Nov 01 00:00:00 CST 2015,
         endTime=Mon Nov 02 00:00:00 CST 2015,
         createTime=Mon Oct 02 08:30:23 CST 2017}
         */
    }

    @Test
    public void queryAll() throws Exception {
        /**
         * List<Seckill> queryAll(int offset ,int limit)
         * java没有保存形参的记录：queryAll(int offset ,int limit)-->queryAll(arg0,arg1)
         * 解决：修改dao的接口，加入mybatis的注解@param
         */
        List<Seckill> seckills = seckillDao.queryAll(0,4);
        for(Seckill seckill:seckills){
            System.out.println(seckill);
        }
    }

}