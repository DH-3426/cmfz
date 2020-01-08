package com.baizhi.dh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Chapter {
  @Id
  private String id;
  private String title;
  private String url;
  private double size;
  private String time;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private java.util.Date createTime;
  private String albumId;
}
