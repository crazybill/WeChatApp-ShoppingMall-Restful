package com.leewaiho.togogo.module.sys.model.product;

import com.leewaiho.togogo.common.base.model.BaseModel;
import com.leewaiho.togogo.module.sys.model.image.TSImage;

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
@Table(name = "T_B_PRODUCT_IMAGE")
public class TBProductImg extends BaseModel implements Serializable{
    
    @OneToOne
    @JoinColumn(name = "IMAGE_ID", referencedColumnName = "id")
    private TSImage image;
    
    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "id")
    private TBProduct product;
    
    private String description;
    
    private boolean isCover;
    
    private int sort;
    
    public TBProductImg() {
    }
    
    public TSImage getImage() {
        return image;
    }
    
    public void setImage(TSImage image) {
        this.image = image;
    }
    
    public TBProduct getProduct() {
        return product;
    }
    
    public void setProduct(TBProduct product) {
        this.product = product;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isCover() {
        return isCover;
    }
    
    public void setCover(boolean cover) {
        isCover = cover;
    }
    
    public int getSort() {
        return sort;
    }
    
    public void setSort(int sort) {
        this.sort = sort;
    }
    
    @Override
    public String toString() {
        return "TBProductImg{" +
                       "image=" + image +
                       ", product=" + product +
                       ", description='" + description + '\'' +
                       ", isCover=" + isCover +
                       ", sort=" + sort +
                       "} " + super.toString();
    }
}
