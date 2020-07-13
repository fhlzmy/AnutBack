package com.fhlzmy.web.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Anut {

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    private String anutId;

    @Column
    private String userId;

    @Column
    private String isAnut;

    @Column
    private String aiai;

    @Column
    private String bodyInfo;

    @Column
    private Date aae041;

    @Column
    private Date aae042;


    public String getAnutId() {
        return anutId;
    }

    public void setAnutId(String anutId) {
        this.anutId = anutId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsAnut() {
        return isAnut;
    }

    public void setIsAnut(String isAnut) {
        this.isAnut = isAnut;
    }

    public String getAiai() {
        return aiai;
    }

    public void setAiai(String aiai) {
        this.aiai = aiai;
    }

    public String getBodyInfo() {
        return bodyInfo;
    }

    public void setBodyInfo(String bodyInfo) {
        this.bodyInfo = bodyInfo;
    }

    public Date getAae041() {
        return aae041;
    }

    public void setAae041(Date aae041) {
        this.aae041 = aae041;
    }

    public Date getAae042() {
        return aae042;
    }

    public void setAae042(Date aae042) {
        this.aae042 = aae042;
    }


    @Override
    public String toString() {
        return "Anut{" +
                "anutId='" + anutId + '\'' +
                ", userId='" + userId + '\'' +
                ", isAnut='" + isAnut + '\'' +
                ", aiai='" + aiai + '\'' +
                ", bodyInfo='" + bodyInfo + '\'' +
                '}';
    }
}
