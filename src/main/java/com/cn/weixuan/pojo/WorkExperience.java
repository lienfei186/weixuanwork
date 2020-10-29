package com.cn.weixuan.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
//工作经历
@Data
public class WorkExperience implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer workId;
    private String companyName;
    @JsonFormat(pattern = "yyyy.MM")
    @DateTimeFormat(pattern = "yyyy.MM")
    private Date startTime;
    @JsonFormat(pattern = "yyyy.MM")
    @DateTimeFormat(pattern = "yyyy.MM")
    private Date endTime;
    private String jobtitle;
    private String jubduties;
    private Integer userId;
}
