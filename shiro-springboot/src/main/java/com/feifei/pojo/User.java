package com.feifei.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private int userId;
    private String userCode;
    private String userPassWord;
    private String perms;


}
