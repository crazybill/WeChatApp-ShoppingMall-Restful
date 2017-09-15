package com.leewaiho.togogo.mapper;

import com.leewaiho.togogo.model.TBOrderItem;
import com.leewaiho.togogo.model.TBOrderItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBOrderItemMapper {
    int countByExample(TBOrderItemExample example);
    
    int deleteByExample(TBOrderItemExample example);
    
    int deleteByPrimaryKey(String id);
    
    int insert(TBOrderItem record);
    
    int insertSelective(TBOrderItem record);
    
    List<TBOrderItem> selectByExampleWithBLOBs(TBOrderItemExample example);
    
    List<TBOrderItem> selectByExample(TBOrderItemExample example);
    
    TBOrderItem selectByPrimaryKey(String id);
    
    int updateByExampleSelective(@Param("record") TBOrderItem record, @Param("example") TBOrderItemExample example);
    
    int updateByExampleWithBLOBs(@Param("record") TBOrderItem record, @Param("example") TBOrderItemExample example);
    
    int updateByExample(@Param("record") TBOrderItem record, @Param("example") TBOrderItemExample example);
    
    int updateByPrimaryKeySelective(TBOrderItem record);
    
    int updateByPrimaryKeyWithBLOBs(TBOrderItem record);
    
    int updateByPrimaryKey(TBOrderItem record);
}