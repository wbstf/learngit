package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void getById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void exportSeckillLoginc() throws Exception {
        //        exposed=false, md5='null', seckillId=1000, now=1507973127393, start=1446307200000, end=1446393600000
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            String md5 = exposer.getMd5();
            long phone = 2147483647;
            try {
                SeckillExcution excution = seckillService.excuteSeckill(id, phone, md5);
                logger.info("result={}", excution);
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage(), e);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage(), e);
            }
        }else {
//            秒杀未开启
            logger.warn("秒杀未开启exposer={}", exposer);
        }

    }

    @Test
    public void executeSeckillProcedure(){
        long seckillId = 1000;
        long phone = 2147483647;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()){
            String md5 = exposer.getMd5();
            SeckillExcution excution = seckillService.executeSeckillProcedure(seckillId,phone,md5);
            logger.info(excution.getStateInfo());
        }
    }
}


