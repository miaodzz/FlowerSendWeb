package entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "HUAGUAN_CASH_OUT")
public class HuaguanCashOut implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 5869806724177150895L;

    /** 处理序号 */
    @Id
    private Integer serialnumber;

    /** 用户id */
    private String userId;

    /** 订单生成时间 */
    private Timestamp time;

    /** 提现数量 */
    private Integer amount;

    /** 提现金额 */
    private Integer money;

    private String nickname;
    public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
     * 获取处理序号
     * 
     * @return 处理序号
     */
    public Integer getSerialnumber() {
        return this.serialnumber;
    }

    /**
     * 设置处理序号
     * 
     * @param serialnumber
     *          处理序号
     */
    public void setSerialnumber(Integer serialnumber) {
        this.serialnumber = serialnumber;
    }

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
     * 获取订单生成时间
     * 
     * @return 订单生成时间
     */
    public Timestamp getTime() {
        return this.time;
    }

    /**
     * 设置订单生成时间
     * 
     * @param time
     *          订单生成时间
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }

    /**
     * 获取提现数量
     * 
     * @return 提现数量
     */
    public Integer getAmount() {
        return this.amount;
    }

    /**
     * 设置提现数量
     * 
     * @param amount
     *          提现数量
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取提现金额
     * 
     * @return 提现金额
     */
    public Integer getMoney() {
        return this.money;
    }

    /**
     * 设置提现金额
     * 
     * @param money
     *          提现金额
     */
    public void setMoney(Integer money) {
        this.money = money;
    }
}