package com.leewaiho.togogo.mapper;

import com.leewaiho.togogo.model.TBOrder;
import com.leewaiho.togogo.model.TBOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBOrderMapper {
    int countByExample(TBOrderExample example);
    
    int deleteByExample(TBOrderExample example);
    
    int deleteByPrimaryKey(String id);
    
    int insert(TBOrder record);
    
    int insertSelective(TBOrder record);
    
    List<TBOrder> selectByExample(TBOrderExample example);
    
    TBOrder selectByPrimaryKey(String id);
    
    int updateByExampleSelective(@Param("record") TBOrder record, @Param("example") TBOrderExample example);
    
    int updateByExample(@Param("record") TBOrder record, @Param("example") TBOrderExample example);
    
    int updateByPrimaryKeySelective(TBOrder record);
    
    int updateByPrimaryKey(TBOrder record);
}