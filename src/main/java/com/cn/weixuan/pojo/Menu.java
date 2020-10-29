package com.cn.weixuan.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
@Data
public class Menu implements Serializable {
   private static final long serialVersionUID = 1L;

    private Integer menuId;

    private String menuName;

    private String permission;

    private String resourceType;

    private Integer parentId;

    private String parentName;

    private String url;
}
