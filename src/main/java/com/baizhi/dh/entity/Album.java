package com.baizhi.dh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Album {
  @Id
  private String id;
  private String title;
  private String score;
  private String author;
  private String broadcast;
  private String cover;
  private long count;
  private String description;
  private String status;
  private java.util.Date createDate;



}
