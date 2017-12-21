package dao;
 
import java.util.List;

import entity.*;

public interface IHuaguanCashOutDAO {

public boolean create(HuaguanCashOut huaguanCashOut);

public int updateByID(HuaguanCashOut huaguanCashOut); 

public int deleteByID(Integer serialnumber) ;
 
public List<HuaguanCashOut> findAll() ;

public List<HuaguanCashOut> findAll(String column, String keyWord) ;

public List<HuaguanCashOut> findAll(int currentPage,int lineSize,String column, String keyWord) ;

public int findCount() ;

public int findCount(String column, String keyWord) ;

public HuaguanCashOut findByID(Integer serialnumber);

public List<HuaguanCashOut> findByUser(String attribute);

public int findCountUser(String attribute);

}