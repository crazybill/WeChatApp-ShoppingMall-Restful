package com.leewaiho.togogo.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/10/9
 * Project togogo-shixun
 */
@Data
public class WebUser implements Serializable {
    private String username;
    private String password;
}
