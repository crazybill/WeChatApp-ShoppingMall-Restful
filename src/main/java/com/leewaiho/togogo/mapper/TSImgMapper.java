package com.leewaiho.togogo.mapper;

import com.leewaiho.togogo.model.TSImg;
import com.leewaiho.togogo.model.TSImgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSImgMapper {
    int countByExample(TSImgExample example);
    
    int deleteByExample(TSImgExample example);
    
    int deleteByPrimaryKey(String id);
    
    int insert(TSImg record);
    
    int insertSelective(TSImg record);
    
    List<TSImg> selectByExample(TSImgExample example);
    
    TSImg selectByPrimaryKey(String id);
    
    int updateByExampleSelective(@Param("record") TSImg record, @Param("example") TSImgExample example);
    
    int updateByExample(@Param("record") TSImg record, @Param("example") TSImgExample example);
    
    int updateByPrimaryKeySelective(TSImg record);
    
    int updateByPrimaryKey(TSImg record);
}