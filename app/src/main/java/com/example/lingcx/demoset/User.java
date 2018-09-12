package com.example.lingcx.demoset;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcx on 2018/9/5.
 */
public class User {
    private String username;
    private String password;
    private int age;
    private List<String> phone;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getPhone() {
        if (phone == null) {
            return new ArrayList<>();
        }
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username == null ? "" : username;
    }
}
