package dao;
 
import java.util.List;

import entity.*;

public interface IUserPictureDAO {

public boolean create(UserPicture userPicture);

public int updateByID(UserPicture userPicture); 

public int deleteByID(String user_id,String userpic) ;

public List<UserPicture> findAll() ;

public List<UserPicture> findAll(String column, String keyWord) ;

public List<UserPicture> findAll(int currentPage,int lineSize,String column, String keyWord) ;

public int findCount() ;

public int findCount(String column, String keyWord) ;

}