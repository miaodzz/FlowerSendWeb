package entity;
public class Friends implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -7594503899308432278L;

    /**  */
    private String userId;

    /**  */
    private String friendId;

    /**  */
    private int id;

    public void exchange() {
    	String temp=userId;
    	userId=friendId;
    	friendId=temp;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置
     * 
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取
     * 
     * @return 
     */
    public String getFriendId() {
        return this.friendId;
    }

    /**
     * 设置
     * 
     * @param friendId
     */
    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    /**
     * 获取
     * 
     * @return 
     */
    public int getId() {
        return this.id;
    }

    /**
     * 设置
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
}