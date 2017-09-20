package com.leewaiho.togogo.module.sys.model.product;

import com.leewaiho.togogo.common.base.model.BaseModel;
import com.leewaiho.togogo.module.sys.model.tag.TBTag;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/20
 * Project togogo-shixun
 */
@Entity
@Table(name = "T_B_PRODUCT_TAG")
public class TBProductTag extends BaseModel implements Serializable {
    
    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "id")
    private TBProduct product;
    
    @OneToOne
    @JoinColumn(name = "TAG_ID", referencedColumnName = "id")
    private TBTag tag;
    
    private int sort;
    
    public TBProductTag() {
    }
    
    public TBProduct getProduct() {
        return product;
    }
    
    public void setProduct(TBProduct product) {
        this.product = product;
    }
    
    public TBTag getTag() {
        return tag;
    }
    
    public void setTag(TBTag tag) {
        this.tag = tag;
    }
    
    public int getSort() {
        return sort;
    }
    
    public void setSort(int sort) {
        this.sort = sort;
    }
    
    @Override
    public String toString() {
        return "TBProductTag{" +
                       "product=" + product +
                       ", tag=" + tag +
                       ", sort=" + sort +
                       "} " + super.toString();
    }
}
