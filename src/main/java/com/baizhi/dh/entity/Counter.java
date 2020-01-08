package com.baizhi.dh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Counter {
  @Id
  private String id;
  private String title;
  private long count;
  private java.util.Date createDate;
  private String userId;
  private String courseId;
}
