package com.baizhi.dh.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Banner {
  @Id
  private String id;
  private String title;
  private String url;
  private String href;
  @JSONField(format = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private java.util.Date createDate;
  private String description;
  private String status;
}