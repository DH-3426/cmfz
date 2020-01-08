package com.baizhi.dh.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {
  @Id
  private String id;
  private String username;
  private String password;

}
