package dao;

import java.util.List;

import entity.*;

public interface IFriendsDAO {

	public boolean create(Friends friends);

	public int updateByID(Friends friends);

	public int deleteByID(Integer id);

	public List<Friends> findAll();

	public List<Friends> findAll(String column, String keyWord);

	public Friends findByUF(String user_id,String friend_id) ;
	
	public List<Friends> findAll(int currentPage, int lineSize, String column, String keyWord);

	public int findCount();

	public int findCount(String column, String keyWord);

}