package com.kob.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: chen
 * Time: 2024/1/28 22:54
 */
@Data       //生成getter toString之类的
@NoArgsConstructor      //无参构造函数
@AllArgsConstructor     //有参构造函数
public class User {
    private Integer id;
    private String username;
    private String password;
}
