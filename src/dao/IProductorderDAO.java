package dao;
 
import java.util.List;

import entity.*;

public interface IProductorderDAO {

public boolean create(Productorder productorder);

public int updateByID(Productorder productorder); 

public int deleteByID(Integer product_order_id) ;

public Productorder findByID(Integer product_order_id) ;

public List<Productorder> findAll() ;

public List<Productorder> findAll(int currentPage,int lineSize,String column, String keyWord) ;

public List<Productorder> findAll(String column, String kw,String key,String r);

public String findTodayStar(int man, int send);

public List<Productorder> findAll(String column, String kw);

public int findCount() ;

public int findCount(String column, String keyWord) ;

public int findLastId();

public int updateSendbackByID(Productorder p);
}