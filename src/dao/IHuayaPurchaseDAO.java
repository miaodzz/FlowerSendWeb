package dao;
 
import java.util.List;

import entity.*;

public interface IHuayaPurchaseDAO {

public boolean create(HuayaPurchase huayaPurchase);

public int updateByID(HuayaPurchase huayaPurchase); 

public int deleteByID(Integer serialnumber) ;

public List<HuayaPurchase> findAll() ;

public List<HuayaPurchase> findAll(String column, String keyWord) ;

public List<HuayaPurchase> findAll(int currentPage,int lineSize,String column, String keyWord) ;

public int findCount() ;

public int findCount(String column, String keyWord) ;

public HuayaPurchase findByID(Integer serialnumber);

public int findCountUser(String attribute);

public List<HuayaPurchase> findByUser(String attribute);

}