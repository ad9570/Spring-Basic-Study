package com.fastcampus.ch4.domain;

import java.util.Objects;

@SuppressWarnings("unused")
public class User {
    private String id;
    private String pwd;

    public User(){}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(pwd, user.pwd);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, pwd);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}