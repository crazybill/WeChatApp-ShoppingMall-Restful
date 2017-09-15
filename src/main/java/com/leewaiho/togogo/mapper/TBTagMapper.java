package com.leewaiho.togogo.mapper;

import com.leewaiho.togogo.model.TBTag;
import com.leewaiho.togogo.model.TBTagExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBTagMapper {
    int countByExample(TBTagExample example);
    
    int deleteByExample(TBTagExample example);
    
    int deleteByPrimaryKey(String id);
    
    int insert(TBTag record);
    
    int insertSelective(TBTag record);
    
    List<TBTag> selectByExampleWithBLOBs(TBTagExample example);
    
    List<TBTag> selectByExample(TBTagExample example);
    
    TBTag selectByPrimaryKey(String id);
    
    int updateByExampleSelective(@Param("record") TBTag record, @Param("example") TBTagExample example);
    
    int updateByExampleWithBLOBs(@Param("record") TBTag record, @Param("example") TBTagExample example);
    
    int updateByExample(@Param("record") TBTag record, @Param("example") TBTagExample example);
    
    int updateByPrimaryKeySelective(TBTag record);
    
    int updateByPrimaryKeyWithBLOBs(TBTag record);
    
    int updateByPrimaryKey(TBTag record);
}