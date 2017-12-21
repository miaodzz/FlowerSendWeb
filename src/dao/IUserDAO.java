package dao;

import java.util.List;

import entity.*;

public interface IUserDAO {

	public boolean create(User user);

	public boolean createUnregistered(User user);
	
	public int updatePasswordByTelephone(User user);

	public int updatePasswdByID(User user);
	
	public int updateNormalByID(User user);

	public int updateAvatarByID(User user);
	
	public int deleteByID(String user_id);

	public int updateRole(String user_id,String role);
	
	public User findByTelPwd(User u);
	
	public User findByTel(String tel) ;
	
	public User findByID(String user_id);
	
	public List<User> findAll(int cp, int ls, String kw);
	
	public List<User> findAll(int cp, int ls);
	
	public List<User> findAll();

	public List<User> findAll(String column, String keyWord);
	
	public List<User> findRole(String role,int cp, int ls, String kw);
	
	public List<User> findRole(String role,int cp, int ls);

	public List<User> findAll(int currentPage, int lineSize, String column, String keyWord);

	public int findCount();
	
	public int findCount(String kw);
	
	public int findCountRole(String role,String kw);

	public int findCount(String column, String keyWord);

}