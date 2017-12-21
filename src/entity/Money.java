package entity;
/*
 * Welcome to use the TableGo Tools.
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 * 
 * Author:bianj
 * Email:edinsker@163.com
 * Version:5.0.0
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MONEY")
public class Money implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 5633808950828914552L;

    /** 用户id */
    @Id
    private String userId;

    /** 用户拥有花芽数 */
    private Integer huayaNum;

    /** 用户拥有花冠数 */
    private Integer huaguanNum;

    /**
     * 获取用户id
     * 
     * @return 用户id
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置用户id
     * 
     * @param userId
     *          用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户拥有花芽数
     * 
     * @return 用户拥有花芽数
     */
    public Integer getHuayaNum() {
        return this.huayaNum;
    }

    /**
     * 设置用户拥有花芽数
     * 
     * @param huayaNum
     *          用户拥有花芽数
     */
    public void setHuayaNum(Integer huayaNum) {
        this.huayaNum = huayaNum;
    }

    /**
     * 获取用户拥有花冠数
     * 
     * @return 用户拥有花冠数
     */
    public Integer getHuaguanNum() {
        return this.huaguanNum;
    }

    /**
     * 设置用户拥有花冠数
     * 
     * @param huaguanNum
     *          用户拥有花冠数
     */
    public void setHuaguanNum(Integer huaguanNum) {
        this.huaguanNum = huaguanNum;
    }
}