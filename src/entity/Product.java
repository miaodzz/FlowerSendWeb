package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 252436193609920877L;

    /** 商品id */
    @Id
    private Integer productId;

    /** 商品名 */
    private String productName;

    /** 商品图片 */
    private String productPicture;

    /** 商品含义（花语） */
    private String productMean;

    /** 商品品种 */
    private String productType;

    private int productPrice;

    
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
     * 获取商品名
     * 
     * @return 商品名
     */
    public String getProductName() {
        return this.productName;
    }

    /**
     * 设置商品名
     * 
     * @param productName
     *          商品名
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取商品图片
     * 
     * @return 商品图片
     */
    public String getProductPicture() {
        return this.productPicture;
    }

    /**
     * 设置商品图片
     * 
     * @param productPicture
     *          商品图片
     */
    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    /**
     * 获取商品含义（花语）
     * 
     * @return 商品含义（花语）
     */
    public String getProductMean() {
        return this.productMean;
    }

    /**
     * 设置商品含义（花语）
     * 
     * @param productMean
     *          商品含义（花语）
     */
    public void setProductMean(String productMean) {
        this.productMean = productMean;
    }

    /**
     * 获取商品品种
     * 
     * @return 商品品种
     */
    public String getProductType() {
        return this.productType;
    }

    public String getProductTypeOp() {
    	if(this.productType==null)
    		return "actual";
        if(this.productType.equals("virtual"))
        	return "actual";
        if(this.productType.equals("actual"))
        	return "virtual";
        return "actual";
    }
    
    /**
     * 设置商品品种
     * 
     * @param productType
     *          商品品种
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
}