package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_PICTURE")
public class UserPicture implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -2169982541510070391L;

    /** 用户id */
    private String userId;

    /** 图片地址 */
    private String picture;

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
     * 获取图片地址
     * 
     * @return 图片地址
     */
    public String getPicture() {
        return this.picture;
    }

    /**
     * 设置图片地址
     * 
     * @param picture
     *          图片地址
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }
}