package com.cn.weixuan.base.module;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 缓存用户信息
 * <br/>
 *
 * @author ：YHY
 * @date ：2020/7/3 14:40
 */
@Data
@Builder
public class CacheUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String phone;

    private String idcard;

    private String name;

    private Integer state;

    private String userName;

    private String token;

    private String role;

    private String recognizeState;
}
