package org.seckill.entity;


import java.util.Date;
//    秒杀成功明细实体
public class SuccessKilled {
//    秒杀商品id
    private long seckillId;
//    用户手机号
    private long userPhone;
//      秒杀的状态
    private short state;
//      创建时间
    private Date createTime;
//    实体的多对一关系，级联   复合属性

    private Seckill Seckill;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public org.seckill.entity.Seckill getSeckill() {
        return Seckill;
    }

    public void setSeckill(Seckill seckill) {
        Seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
