package com.leewaiho.togogo.mapper;

import com.leewaiho.togogo.model.TBProductImg;
import com.leewaiho.togogo.model.TBProductImgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBProductImgMapper {
    int countByExample(TBProductImgExample example);
    
    int deleteByExample(TBProductImgExample example);
    
    int deleteByPrimaryKey(String id);
    
    int insert(TBProductImg record);
    
    int insertSelective(TBProductImg record);
    
    List<TBProductImg> selectByExample(TBProductImgExample example);
    
    TBProductImg selectByPrimaryKey(String id);
    
    int updateByExampleSelective(@Param("record") TBProductImg record, @Param("example") TBProductImgExample example);
    
    int updateByExample(@Param("record") TBProductImg record, @Param("example") TBProductImgExample example);
    
    int updateByPrimaryKeySelective(TBProductImg record);
    
    int updateByPrimaryKey(TBProductImg record);
}