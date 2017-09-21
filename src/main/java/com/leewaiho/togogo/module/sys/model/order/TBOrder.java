package com.leewaiho.togogo.module.sys.model.order;

import com.leewaiho.togogo.common.base.model.BaseModel;
import com.leewaiho.togogo.module.sys.model.user.TSUser;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/21
 * Project togogo-shixun
 */
@Entity
@Table(name = "T_B_ORDER")
public class TBOrder extends BaseModel implements Serializable {
    
    @OneToMany(mappedBy = "order")
    private Set<TBOrderItem> orderItems;
    
    private BigDecimal price;
    
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "ID")
    private TSUser owner;
    
    @ManyToOne
    @JoinColumn(name = "PROCESSOR_ID", referencedColumnName = "ID")
    private TSUser processor;
    
    private Date finishedTime;
    
    private int state = State.UNPAYED;
    
    public class State {
        public static final int CLOSED = 01; // 已关闭
        public static final int UNPAYED = 10; // 待支付
        public static final int UNCHECKED = 20; // 待确认
        public static final int PROCESSING = 30; // 正在处理
        public static final int FINISHED = 40; // 已完成
    }
    
    public TBOrder() {
    }
    
    public Set<TBOrderItem> getOrderItems() {
        return orderItems;
    }
    
    public void setOrderItems(Set<TBOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public TSUser getOwner() {
        return owner;
    }
    
    public void setOwner(TSUser owner) {
        this.owner = owner;
    }
    
    public TSUser getProcessor() {
        return processor;
    }
    
    public void setProcessor(TSUser processor) {
        this.processor = processor;
    }
    
    public Date getFinishedTime() {
        return finishedTime;
    }
    
    public void setFinishedTime(Date finishedTime) {
        this.finishedTime = finishedTime;
    }
    
    public int getState() {
        return state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    @Override
    public String toString() {
        return "TBOrder{" +
                       "orderItems=" + orderItems +
                       ", price=" + price +
                       ", description='" + description + '\'' +
                       ", owner=" + owner +
                       ", processor=" + processor +
                       ", finishedTime=" + finishedTime +
                       ", state=" + state +
                       "} " + super.toString();
    }
}
