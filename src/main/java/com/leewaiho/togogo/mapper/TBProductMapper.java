package com.leewaiho.togogo.mapper;

import com.leewaiho.togogo.model.TBProduct;
import com.leewaiho.togogo.model.TBProductExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBProductMapper {
    int countByExample(TBProductExample example);
    
    int deleteByExample(TBProductExample example);
    
    int deleteByPrimaryKey(String id);
    
    int insert(TBProduct record);
    
    int insertSelective(TBProduct record);
    
    List<TBProduct> selectByExample(TBProductExample example);
    
    TBProduct selectByPrimaryKey(String id);
    
    int updateByExampleSelective(@Param("record") TBProduct record, @Param("example") TBProductExample example);
    
    int updateByExample(@Param("record") TBProduct record, @Param("example") TBProductExample example);
    
    int updateByPrimaryKeySelective(TBProduct record);
    
    int updateByPrimaryKey(TBProduct record);
}