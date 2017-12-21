package dao;
 
import java.util.List;

import entity.*;

public interface ISendInfoDAO {

public boolean create(SendInfo sendInfo);

public int updateByID(SendInfo sendInfo); 

public int deleteByID(Integer send_id) ;

public List<SendInfo> findAll() ;

public List<SendInfo> findAll(String column, String keyWord) ;

public List<SendInfo> findAll(int currentPage,int lineSize,String column, String keyWord) ;

public List<SendInfo> findPOID(Integer kw);


public int countPOID(Integer kw);

public int findCount() ;

public int findCount(String column, String keyWord) ;

public SendInfo findByID(Integer send_id);

public int updateNormalByID(SendInfo sendinfo);

}