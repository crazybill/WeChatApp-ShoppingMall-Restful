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
    
    private int state = State.UNPAYED.getValue();
    
    public enum State {
        CLOSED("已关闭", 01), UNPAYED("待支付", 10), UNCHECKED("待确认", 20), PROCESSING("正在处理", 30), FINISHED("已完成", 40);
        
        private String title;
        private int value;
        
        State(String title, int value) {
            this.title = title;
            this.value = value;
        }
        
        public String getTitle() {
            return title;
        }
        
        public int getValue() {
            return value;
        }
    
        @Override
        public String toString() {
            return "State{" +
                           "title='" + title + '\'' +
                           ", value=" + value +
                           "} " + super.toString();
        }
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
