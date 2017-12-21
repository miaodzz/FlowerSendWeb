package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import dao.*;

import entity.*;
import util.DButil;

public class UserDAOImpl implements IUserDAO {
	private Connection conn;

	public UserDAOImpl() {
		this.conn = DButil.getConnection();
	}

	public boolean createUnregistered(User user){
		String sql = "INSERT INTO USER(user_id,telephone,nickname,user_passwd,address) values (?,?,?,?,?) ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId()); 
			pstmt.setString(2, user.getTelephone());
			pstmt.setString(3, user.getNickname());
			pstmt.setString(4, user.getUserPasswd());
			pstmt.setString(5, user.getAddress());
			if (pstmt.executeUpdate() == 1) {
				pstmt.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean create(User user) {
		String sql = "INSERT INTO USER(user_id,telephone,nickname,user_passwd,truename,birthday,avatar,signature,sex,address) values (?,?,?,?,?,?,?,?,?,?) ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId()); 
			pstmt.setString(2, user.getTelephone());
			pstmt.setString(3, user.getNickname());
			pstmt.setString(4, user.getUserPasswd());
			pstmt.setString(5, user.getTruename());
			pstmt.setDate(6, user.getBirthday());
			pstmt.setString(7, user.getAvatar());
			pstmt.setString(8, user.getSignature());
			pstmt.setString(9, user.getSex());
			pstmt.setString(10, user.getAddress());
			if (pstmt.executeUpdate() == 1) {
				pstmt.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int deleteByID(String user_id) {
		String sql = "DELETE FROM USER WHERE user_id like ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updatePasswdByID(User user)
	{
		String sql = "UPDATE USER SET user_passwd=? WHERE user_id like ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserPasswd());
			pstmt.setString(2, user.getUserId());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updatePasswordByTelephone(User user) {
		String sql = "UPDATE USER SET user_passwd=? WHERE telephone like ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserPasswd());
			pstmt.setString(2, user.getTelephone());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public int updateNormalByID(User user) {
		String sql = "UPDATE USER SET telephone=?,nickname=?,truename=?,signature=?,address=?,sex=?,birthday=? WHERE user_id like ? ";
		//System.out.println(sql);
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user.getTelephone());
			pstmt.setString(2, user.getNickname());
			pstmt.setString(3, user.getTruename());
			
			//pstmt.setString(5, user.getAvatar());
			pstmt.setString(4, user.getSignature());
			pstmt.setString(5, user.getAddress());
			pstmt.setString(6,user.getSex());
			pstmt.setDate(7, user.getBirthday());
			pstmt.setString(8, user.getUserId());
			result = pstmt.executeUpdate(); 
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateAvatarByID(User user) {
		String sql = "UPDATE USER SET avatar = ? WHERE user_id like ? ";
		//System.out.println(sql);
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user.getAvatar());
			pstmt.setString(2, user.getUserId());
			result = pstmt.executeUpdate(); 
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public User findByTelPwd(User u){
		String sql = " SELECT * FROM USER WHERE telephone like ? and user_passwd like ? ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, u.getTelephone());
			pstmt.setString(2, u.getUserPasswd());
			ResultSet rs = pstmt.executeQuery();
			User user = null;
			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setTelephone(rs.getString("telephone"));
				user.setNickname(rs.getString("nickname"));
				user.setUserPasswd(rs.getString("user_passwd"));
				user.setTruename(rs.getString("truename"));
				user.setBirthday(rs.getDate("birthday"));
				user.setAvatar(rs.getString("avatar"));
				user.setSignature(rs.getString("signature"));
				user.setAddress(rs.getString("address")); 
				user.setRole(rs.getString("role"));
				user.setSex(rs.getString("sex"));
			}
			pstmt.close();
			rs.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int updateRole(String user_id,String role) {
		String sql = "UPDATE USER SET role = ? WHERE user_id like ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, role);
			pstmt.setString(2, user_id);
	
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public User findByID(String user_id) {
		String sql = " SELECT * FROM USER WHERE user_id like ? ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			User user = null;
			//System.out.println("findByID"+user_id);
			while (rs.next()) {
				//System.out.println("找到了");
				user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setTelephone(rs.getString("telephone"));
				user.setNickname(rs.getString("nickname"));
				user.setSex(rs.getString("sex"));
				user.setUserPasswd(rs.getString("user_passwd"));
				user.setTruename(rs.getString("truename"));
				user.setBirthday(rs.getDate("birthday"));
				user.setAvatar(rs.getString("avatar"));
				user.setSignature(rs.getString("signature"));
				user.setAddress(rs.getString("address"));
				user.setRole(rs.getString("role"));
			}
			pstmt.close();
			rs.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User findByTel(String tel) {
		String sql = " SELECT * FROM USER WHERE telephone like ? ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			ResultSet rs = pstmt.executeQuery();
			User user = null;
			//System.out.println("findByID"+user_id);
			while (rs.next()) {
				//System.out.println("找到了");
				user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setTelephone(rs.getString("telephone"));
				user.setNickname(rs.getString("nickname"));
				user.setSex(rs.getString("sex"));
				user.setUserPasswd(rs.getString("user_passwd"));
				user.setTruename(rs.getString("truename"));
				user.setBirthday(rs.getDate("birthday"));
				user.setAvatar(rs.getString("avatar"));
				user.setSignature(rs.getString("signature"));
				user.setAddress(rs.getString("address"));
				user.setRole(rs.getString("role"));
			}
			pstmt.close();
			rs.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> findAll() {
		List<User> all = new ArrayList<User>();
		String sql = " SELECT * FROM USER";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setTelephone(rs.getString("telephone"));
				user.setNickname(rs.getString("nickname"));
				user.setUserPasswd(rs.getString("user_passwd"));
				user.setTruename(rs.getString("truename"));
				user.setBirthday(rs.getDate("birthday"));
				user.setAvatar(rs.getString("avatar"));
				user.setSignature(rs.getString("signature"));
				user.setAddress(rs.getString("address"));
				user.setRole(rs.getString("role"));
				user.setSex(rs.getString("sex"));
				all.add(user);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<User> findAll(String column, String kw) {
		List<User> all = new ArrayList<User>();
		String sql = " SELECT * FROM USER WHERE binary " + column + " LIKE ?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setTelephone(rs.getString("telephone"));
				user.setNickname(rs.getString("nickname"));
				user.setUserPasswd(rs.getString("user_passwd"));
				user.setTruename(rs.getString("truename"));
				user.setBirthday(rs.getDate("birthday"));
				user.setAvatar(rs.getString("avatar"));
				user.setSignature(rs.getString("signature"));
				user.setAddress(rs.getString("address"));
				user.setRole(rs.getString("role"));
				user.setSex(rs.getString("sex"));
				all.add(user);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}


	
	public List<User> findAll(int cp, int ls, String kw) {
		List<User> all = new ArrayList<User>();
		String sql = " SELECT * FROM ( SELECT * FROM USER WHERE binary user_id LIKE ? or binary telephone LIKE ? or binary nickname LIKE ? or binary truename LIKE ? or binary  address like ? ) as a limit ?,?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			pstmt.setString(2, "%" + kw + "%");
			pstmt.setString(3, "%" + kw + "%");
			pstmt.setString(4, "%" + kw + "%");
			pstmt.setString(5, "%" + kw + "%");
			
			pstmt.setInt(6, (cp - 1) * ls);
			pstmt.setInt(7, cp*ls-1);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setTelephone(rs.getString("telephone"));
				user.setNickname(rs.getString("nickname"));
				user.setUserPasswd(rs.getString("user_passwd"));
				user.setTruename(rs.getString("truename"));
				user.setBirthday(rs.getDate("birthday"));
				user.setAvatar(rs.getString("avatar"));
				user.setSignature(rs.getString("signature"));
				user.setAddress(rs.getString("address"));
				user.setRole(rs.getString("role"));
				user.setSex(rs.getString("sex"));
				all.add(user);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}
	
	public List<User> findRole(String role,int cp, int ls, String kw) {
		
		if(role==null||role.equals("")||role.equals("all"))return findAll(cp,ls,kw); 
		List<User> all = new ArrayList<User>();
		String sql = " SELECT * FROM (SELECT * FROM USER WHERE ( user_id = ? or binary  telephone LIKE ? or binary  nickname LIKE ? or binary  truename LIKE ? or binary  address like ? )and   role like ? ) as a limit ?,?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1,  "%" + kw + "%");
			pstmt.setString(2,  "%" + kw + "%");
			pstmt.setString(3,  "%" + kw + "%");
			pstmt.setString(4,  "%" + kw + "%");
			pstmt.setString(5,  "%" + kw + "%");
			pstmt.setString(6,  "%" + role + "%");
			pstmt.setInt(7, (cp - 1) * ls);
			pstmt.setInt(8, cp*ls-1);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setTelephone(rs.getString("telephone"));
				user.setNickname(rs.getString("nickname"));
				user.setUserPasswd(rs.getString("user_passwd"));
				user.setTruename(rs.getString("truename"));
				user.setBirthday(rs.getDate("birthday"));
				user.setAvatar(rs.getString("avatar"));
				user.setSignature(rs.getString("signature"));
				user.setSex(rs.getString("sex"));
				user.setAddress(rs.getString("address"));
				user.setRole(rs.getString("role"));
				all.add(user);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}
	
public List<User> findRole(String role,int cp, int ls) {
		
		if(role==null||role.equals("all")||role.equals(""))return findAll(cp,ls); 
		List<User> all = new ArrayList<User>();
		String sql = "SELECT * FROM (SELECT * FROM USER WHERE binary role like ? ) as a limit ?,?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + role + "%");
			pstmt.setInt(2, (cp - 1) * ls);
			pstmt.setInt(3, cp*ls-1);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setTelephone(rs.getString("telephone"));
				user.setNickname(rs.getString("nickname"));
				user.setUserPasswd(rs.getString("user_passwd"));
				user.setTruename(rs.getString("truename"));
				user.setBirthday(rs.getDate("birthday"));
				user.setAvatar(rs.getString("avatar"));
				user.setSignature(rs.getString("signature"));
				user.setSex(rs.getString("sex"));
				user.setAddress(rs.getString("address"));
				user.setRole(rs.getString("role"));
				all.add(user);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}
	
	public List<User> findAll(int cp, int ls, String column, String kw) {
		List<User> all = new ArrayList<User>();
		String sql = " SELECT * FROM (SELECT * FROM USER WHERE  binary " + column + " LIKE ? ) as a limit ?,?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			pstmt.setInt(2, (cp - 1) * ls);
			pstmt.setInt(3, cp * ls -1);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setTelephone(rs.getString("telephone"));
				user.setNickname(rs.getString("nickname"));
				user.setUserPasswd(rs.getString("user_passwd"));
				user.setTruename(rs.getString("truename"));
				user.setBirthday(rs.getDate("birthday"));
				user.setAvatar(rs.getString("avatar"));
				user.setSignature(rs.getString("signature"));
				user.setAddress(rs.getString("address"));
				user.setRole(rs.getString("role"));
				user.setSex(rs.getString("sex"));
				all.add(user);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<User> findAll(int cp, int ls) {
		List<User> all = new ArrayList<User>();
		String sql = " SELECT * FROM USER limit ?,?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			
			pstmt.setInt(1, (cp - 1) * ls);
			pstmt.setInt(2, ls);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setTelephone(rs.getString("telephone"));
				user.setNickname(rs.getString("nickname"));
				user.setUserPasswd(rs.getString("user_passwd"));
				user.setTruename(rs.getString("truename"));
				user.setBirthday(rs.getDate("birthday"));
				user.setAvatar(rs.getString("avatar"));
				//System.out.print(user.getAvatar());
				user.setSignature(rs.getString("signature"));
				user.setSex(rs.getString("sex"));
				user.setAddress(rs.getString("address"));
				user.setRole(rs.getString("role"));
				all.add(user);
			}
			pstmt.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	
	public int findCount() {
		String sql = " SELECT count(*) FROM USER";
		int count = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int findCount(String column, String kw) {
		if(column.equals("role")&&kw.equals("all")){return findCount();}//没角色
		String sql = " SELECT count(*) FROM USER WHERE binary " + column + " LIKE ? ";
		int count = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1,  "%" + kw + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int findCount(String kw) {
		//关键字
		int count=0;
		String sql = " SELECT count(*) FROM USER WHERE user_id = ? or binary telephone LIKE ? or binary  nickname LIKE ? or binary  truename LIKE ?  or binary  address like ?   ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			//System.out.println("kw来自findCountString kw"+kw);
			pstmt.setString(1, kw);
			pstmt.setString(2, "%" + kw + "%");
			pstmt.setString(3, "%" + kw + "%");
			pstmt.setString(4, "%" + kw + "%");
			pstmt.setString(5, "%" + kw + "%");
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	
	public int findCountRole(String role,String kw) {
		
		if(kw==null||kw.equals("")) {return findCount("role",role);}//如果没关键字
		if(role==null||role.equals("")||role.equals("all")) {return findCount(kw);}//有关键字没角色
		//有关键字有角色
		int count=0;
		String sql = " SELECT count(*) FROM USER WHERE (user_id = ? or binary  telephone LIKE ? or binary  nickname LIKE ? or binary  truename LIKE ? or binary  address like ? )and role like ?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, kw );
			pstmt.setString(2, "%" + kw + "%");
			pstmt.setString(3, "%" + kw + "%");
			pstmt.setString(4, "%" + kw + "%");
			pstmt.setString(5, "%" + kw + "%");
			pstmt.setString(6,  "%" + role + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
