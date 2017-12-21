package dao;

import java.util.List;

import entity.*;

public interface IMoneyDAO {


	public int updateByID(Money money);

	public List<Money> findAll();

	public Money findByID(String user_id);

	public List<Money> findAll(String column, String keyWord);

	public List<Money> findAll(int currentPage, int lineSize, String column, String keyWord);

	public int findCount();

	public int findCount(String column, String keyWord);

}