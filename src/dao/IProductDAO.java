package dao;
 
import java.util.List;

import entity.*;

public interface IProductDAO {

public boolean create(Product product);

public int updateByID(Product product); 

public int deleteByID(Integer product_id) ;

public Product findByID(Integer product_id) ;

public List<Product> findAll() ;

public List<Product> findAll(String column, String keyWord) ;

public List<Product> findAll(int currentPage,int lineSize,String column, String keyWord) ;

public int findCount() ;

public int findCount(String column, String keyWord) ;

public int updatePictureByID(Product product);

}