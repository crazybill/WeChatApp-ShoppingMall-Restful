package com.leewaiho.togogo.mapper;

import com.leewaiho.togogo.model.TSUser;
import com.leewaiho.togogo.model.TSUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSUserMapper {
    int countByExample(TSUserExample example);
    
    int deleteByExample(TSUserExample example);
    
    int deleteByPrimaryKey(String id);
    
    int insert(TSUser record);
    
    int insertSelective(TSUser record);
    
    List<TSUser> selectByExample(TSUserExample example);
    
    TSUser selectByPrimaryKey(String id);
    
    int updateByExampleSelective(@Param("record") TSUser record, @Param("example") TSUserExample example);
    
    int updateByExample(@Param("record") TSUser record, @Param("example") TSUserExample example);
    
    int updateByPrimaryKeySelective(TSUser record);
    
    int updateByPrimaryKey(TSUser record);
}