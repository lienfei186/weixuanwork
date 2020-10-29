package com.cn.weixuan.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author YHY
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_project")
public class Project {
    @TableId
    private int id;
    private Date createTime;
    private String name;
    private String logo;
    private String status;
    private String jsyq;
    private String type;
    private String price;
    private int bmrs;
    private String alltype;
    private String allprog;
    private Integer userId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJsyq() {
        return jsyq;
    }

    public void setJsyq(String jsyq) {
        this.jsyq = jsyq;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getBmrs() {
        return bmrs;
    }

    public void setBmrs(int bmrs) {
        this.bmrs = bmrs;
    }

    public String getAlltype() {
        return alltype;
    }

    public void setAlltype(String alltype) {
        this.alltype = alltype;
    }

    public String getAllprog() {
        return allprog;
    }

    public void setAllprog(String allprog) {
        this.allprog = allprog;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", create_time='" + createTime + '\'' +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", status='" + status + '\'' +
                ", jsyq='" + jsyq + '\'' +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", bmrs=" + bmrs +
                ", alltype='" + alltype + '\'' +
                ", allprog='" + allprog + '\'' +
                '}';
    }
}
