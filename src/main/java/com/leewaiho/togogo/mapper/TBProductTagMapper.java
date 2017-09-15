package com.leewaiho.togogo.mapper;

import com.leewaiho.togogo.model.TBProductTag;
import com.leewaiho.togogo.model.TBProductTagExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBProductTagMapper {
    int countByExample(TBProductTagExample example);
    
    int deleteByExample(TBProductTagExample example);
    
    int deleteByPrimaryKey(String id);
    
    int insert(TBProductTag record);
    
    int insertSelective(TBProductTag record);
    
    List<TBProductTag> selectByExampleWithBLOBs(TBProductTagExample example);
    
    List<TBProductTag> selectByExample(TBProductTagExample example);
    
    TBProductTag selectByPrimaryKey(String id);
    
    int updateByExampleSelective(@Param("record") TBProductTag record, @Param("example") TBProductTagExample example);
    
    int updateByExampleWithBLOBs(@Param("record") TBProductTag record, @Param("example") TBProductTagExample example);
    
    int updateByExample(@Param("record") TBProductTag record, @Param("example") TBProductTagExample example);
    
    int updateByPrimaryKeySelective(TBProductTag record);
    
    int updateByPrimaryKeyWithBLOBs(TBProductTag record);
    
    int updateByPrimaryKey(TBProductTag record);
}