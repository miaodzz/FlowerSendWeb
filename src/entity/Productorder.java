package entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTORDER")
public class Productorder implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -7883281353711844174L;

    /** 订单号 */
    @Id
    private Integer productOrderId;

    /** 赠送者id */
    private String orderBy;
    
    /** 赠送者昵称 */
    private String byNickname;

    private String toNickname;
    
    /** 被赠送者id */
    private String orderTo;

    /** 商品id */
    private Integer productId;
    
    private String productName;

    /** 配送天数 */
    private Integer sendDays;

    /** 每天配送数量 */
    private Integer everydayNumber;

    /** 总价 */
    private Integer countPrice;

    /** 回赠情况 */
    private Integer sendback;

    /** 下单时间 */
    private Timestamp purchaseTime;

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
     * 获取赠送者id
     * 
     * @return 赠送者id
     */
    public String getOrderBy() {
        return this.orderBy;
    }

    /**
     * 设置赠送者id
     * 
     * @param orderBy
     *          赠送者id
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 获取被赠送者id
     * 
     * @return 被赠送者id
     */
    public String getOrderTo() {
        return this.orderTo;
    }

    /**
     * 设置被赠送者id
     * 
     * @param orderTo
     *          被赠送者id
     */
    public void setOrderTo(String orderTo) {
        this.orderTo = orderTo;
    }

    /**
     * 获取商品id
     * 
     * @return 商品id
     */
    public Integer getProductId() {
        return this.productId;
    }

    /**
     * 设置商品id
     * 
     * @param productId
     *          商品id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取配送天数
     * 
     * @return 配送天数
     */
    public Integer getSendDays() {
        return this.sendDays;
    }

    /**
     * 设置配送天数
     * 
     * @param sendDays
     *          配送天数
     */
    public void setSendDays(Integer sendDays) {
        this.sendDays = sendDays;
    }

    /**
     * 获取每天配送数量
     * 
     * @return 每天配送数量
     */
    public Integer getEverydayNumber() {
        return this.everydayNumber;
    }

    /**
     * 设置每天配送数量
     * 
     * @param everydayNumber
     *          每天配送数量
     */
    public void setEverydayNumber(Integer everydayNumber) {
        this.everydayNumber = everydayNumber;
    }

    /**
     * 获取总价
     * 
     * @return 总价
     */
    public Integer getCountPrice() {
        return this.countPrice;
    }

    /**
     * 设置总价
     * 
     * @param countPrice
     *          总价
     */
    public void setCountPrice(Integer countPrice) {
        this.countPrice = countPrice;
    }

    /**
     * 获取回赠情况
     * 
     * @return 回赠情况
     */
    public Integer getSendback() {
        return this.sendback;
    }

    /**
     * 设置回赠情况
     * 
     * @param sendback
     *          回赠情况
     */
    public void setSendback(Integer sendback) {
        this.sendback = sendback;
    }

    /**
     * 获取下单时间
     * 
     * @return 下单时间
     */
    public Timestamp getPurchaseTime() {
        return this.purchaseTime;
    }

    /**
     * 设置下单时间
     * 
     * @param purchaseTime
     *          下单时间
     */
    public void setPurchaseTime(Timestamp purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

	public String getByNickname() {
		return byNickname;
	}

	public void setByNickname(String bynickname) {
		this.byNickname = bynickname;
	}

	public String getToNickname() {
		return toNickname;
	}

	public void setToNickname(String tonickname) {
		this.toNickname = tonickname;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	private String message;

	private String productPic;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setProductPic(String productPicture) {
		this.productPic=productPicture;
		
	}
	public String getProductPic() {
		return this.productPic;
		
	}
}