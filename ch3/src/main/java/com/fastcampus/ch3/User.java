package com.fastcampus.ch3;

import java.util.Date;
import java.util.Objects;

@SuppressWarnings("unused")
public class User {
    private String id;
    private String pwd;
    private String nm;
    private String email;
    private Date birth;
    private String sns;
    private Date regDate;

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
    public String getNm() {
        return nm;
    }
    public void setNm(String nm) {
        this.nm = nm;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public String getSns() {
        return sns;
    }
    public void setSns(String sns) {
        this.sns = sns;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public User(String id, String pwd, String nm, String email, Date birth, String sns, Date regDate) {
        this.id = id;
        this.pwd = pwd;
        this.nm = nm;
        this.email = email;
        this.birth = birth;
        this.sns = sns;
        this.regDate = regDate;
    }
    public User(String id, String pwd, String nm, String email, Date birth, String sns) {
        this.id = id;
        this.pwd = pwd;
        this.nm = nm;
        this.email = email;
        this.birth = birth;
        this.sns = sns;
    }
        public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nm='" + nm + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", sns='" + sns + '\'' +
                ", regDate=" + regDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(pwd, user.pwd) && Objects.equals(nm, user.nm) && Objects.equals(email, user.email) && Objects.equals(birth, user.birth) && Objects.equals(sns, user.sns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pwd, nm, email, birth, sns);
    }
}
