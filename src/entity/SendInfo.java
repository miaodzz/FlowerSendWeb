package entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class SendInfo implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -3753028285853892111L;

    /** 配送号id */
    @Id
    private Integer sendId;
    
    /** 订单号 */
    private Integer productOrderId;

    /** 配送状态 */
    private String sendState;

    /** 配送时间 */
    private Timestamp sendTime;

    /** 配送者 */
    private String sendman;

    /**
     * 获取配送号id
     * 
     * @return 配送号id
     */
    public Integer getSendId() {
        return this.sendId;
    }

    /**
     * 设置配送号id
     * 
     * @param sendId
     *          配送号id
     */
    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    /**
     * 获取订单号
     * 
     * @return 订单号
     */
    public Integer getProductOrderId() {
        return this.productOrderId;
    }

    /**
     * 设置订单号
     * 
     * @param productOrderId
     *          订单号
     */
    public void setProductOrderId(Integer productOrderId) {
        this.productOrderId = productOrderId;
    }

    /**
     * 获取配送状态
     * 
     * @return 配送状态
     */
    public String getSendState() {
        return this.sendState;
    }

    /**
     * 设置配送状态
     * 
     * @param sendState
     *          配送状态
     */
    public void setSendState(String sendState) {
        this.sendState = sendState;
    }

    /**
     * 获取配送时间
     * 
     * @return 配送时间
     */
    public Timestamp getSendTime() {
        return this.sendTime;
    }

    /**
     * 设置配送时间
     * 
     * @param sendTime
     *          配送时间
     */
    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取配送者
     * 
     * @return 配送者
     */
    public String getSendman() {
        return this.sendman;
    }

    /**
     * 设置配送者
     * 
     * @param sendman
     *          配送者
     */

	public void setSendman(String sendman) {
		this.sendman = sendman;
		
	}

	private String message;
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}