package entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "USER")
public class User implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 7421339621153445764L;

    /** 普通用户id */
    private String userId;

    /** 电话号码 */
    private String telephone;

    /** 昵称 */
    private String nickname;

    /** 密码 */
    private String userPasswd;

    /** 真实姓名 */
    private String truename;

    /** 生日 */
    private Date birthday;

    /** 头像 */
    private String avatar;

    /** 个性签名 */
    private String signature;

    /** 收货地址 */
    private String address;

    
    private String sex;
    /**
     * 获取普通用户id
     * 
     * @return 普通用户id
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置普通用户id
     * 
     * @param userId
     *          普通用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取电话号码
     * 
     * @return 电话号码
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * 设置电话号码
     * 
     * @param telephone
     *          电话号码
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取昵称
     * 
     * @return 昵称
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * 设置昵称
     * 
     * @param nickname
     *          昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取密码
     * 
     * @return 密码
     */
    public String getUserPasswd() {
        return this.userPasswd;
    }

    /**
     * 设置密码
     * 
     * @param userPasswd
     *          密码
     */
    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    /**
     * 获取真实姓名
     * 
     * @return 真实姓名
     */
    public String getTruename() {
        return this.truename;
    }

    /**
     * 设置真实姓名
     * 
     * @param truename
     *          真实姓名
     */
    public void setTruename(String truename) {
        this.truename = truename;
    }

    /**
     * 获取生日
     * 
     * @return 生日
     */
    public Date getBirthday() {
        return this.birthday;
    }

    /**
     * 设置生日
     * 
     * @param birthday
     *          生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取头像
     * 
     * @return 头像
     */
    public String getAvatar() {
        return this.avatar;
    }

    /**
     * 设置头像
     * 
     * @param avatar
     *          头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取个性签名
     * 
     * @return 个性签名
     */
    public String getSignature() {
        return this.signature;
    }

    /**
     * 设置个性签名
     * 
     * @param signature
     *          个性签名
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * 获取收货地址
     * 
     * @return 收货地址
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 设置收货地址
     * 
     * @param address
     *          收货地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    private String role;
    
	public void setRole(String string) {
		// TODO Auto-generated method stub
		this.role=string;
	}
	public String getRole()
	{
		return this.role;
	}

	public String getSex() {
		return sex;
	}

	public String getSexOp() {
		if(sex==null||sex.equals("男"))
		{
			return("女");
		}
		else return("男");
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}