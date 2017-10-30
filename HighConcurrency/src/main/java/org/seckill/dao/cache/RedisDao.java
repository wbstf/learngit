package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JedisPool jedisPool;
    public RedisDao(String  ip,int port){
        jedisPool = new JedisPool(ip, port);
    }
//    传入Seckill的字节码
    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
    public Seckill getSeckill(long seckillId){
//        redis 操作
        try {
            Jedis jedis = jedisPool.getResource();

            try {
                String key = "seckill:" + seckillId;
//            并没有内部实例化操作
//            get->byte[] ->反序列化->Object(Seckill)
//                采用自定义的序列化
//                protostuff:pojo
                byte[] bytes = jedis.get(key.getBytes());
//                缓存重新获取到
                if(bytes!= null){
//                    空对象
                    Seckill seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);
//                    seckill被反序列化
                    return seckill;
                }

            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
    public String putSeckill(Seckill seckill){
        //            set->Object(Seckill) ->反序列化->byte[]
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:"+seckill.getSeckillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill,schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
//                超时缓存
                int timeout = 60*60;
                String result = jedis.setex(key.getBytes(),timeout, bytes);

                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
