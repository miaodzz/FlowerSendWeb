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

public class FriendsDAOImpl implements IFriendsDAO {
	private Connection conn;

	public FriendsDAOImpl() {
		this.conn = DButil.getConnection();
	}

	public boolean create(Friends friends) {
		String sql = "INSERT INTO FRIENDS(user_id,friend_id) values (?,?) ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, friends.getUserId());
			pstmt.setString(2, friends.getFriendId());
			if (pstmt.executeUpdate() == 1) {
				pstmt.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int deleteByID(Integer id) {
		String sql = "DELETE FROM FRIENDS WHERE id= ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateByID(Friends friends) {
		String sql = "UPDATE FRIENDS SET user_id=?,friend_id=? WHERE id = ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(0, friends.getUserId());
			pstmt.setString(1, friends.getFriendId());
			pstmt.setInt(3, friends.getId());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Friends findByID(Integer id) {
		String sql = " SELECT * FROM FRIENDS WHERE id = ? ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Friends friends = null;
			
			while (rs.next()) {
				friends = new Friends();
				friends.setUserId(rs.getString("user_id"));
				friends.setFriendId(rs.getString("friend_id"));
				friends.setId(rs.getInt("id"));
			}
			pstmt.close();
			rs.close();
			return friends;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Friends> findAll() {
		List<Friends> all = new ArrayList<Friends>();
		String sql = " SELECT * FROM FRIENDS";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Friends friends = new Friends();
				friends.setUserId(rs.getString("user_id"));
				friends.setFriendId(rs.getString("friend_id"));
				friends.setId(rs.getInt("id"));
				all.add(friends);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public Friends findByUF(String user_id,String friend_id) {
			String sql = " SELECT * FROM FRIENDS WHERE user_id like ? and friend_id like ?";
			try {
				PreparedStatement pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, user_id);
				pstmt.setString(2, friend_id);
				ResultSet rs = pstmt.executeQuery();
				Friends friends = null;
				while (rs.next()) {
					friends = new Friends();
					friends.setUserId(rs.getString("user_id"));
					friends.setFriendId(rs.getString("friend_id"));
					friends.setId(rs.getInt("id"));
				}
				pstmt.close();
				rs.close();
				return friends;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
	}
	
	public List<Friends> findAll(String column, String kw) {
		List<Friends> all = new ArrayList<Friends>();
		String sql = " SELECT * FROM FRIENDS WHERE " + column + " LIKE ?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Friends friends = new Friends();
				friends.setUserId(rs.getString("user_id"));
				friends.setFriendId(rs.getString("friend_id"));
				friends.setId(rs.getInt("id"));
				all.add(friends);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<Friends> findAll(int cp, int ls, String column, String kw) {
		List<Friends> all = new ArrayList<Friends>();
		String sql = " SELECT * FROM FRIENDS WHERE " + column + " LIKE ? limit ?,?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			pstmt.setInt(2, (cp - 1) * ls);
			pstmt.setInt(3, ls);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Friends friends = new Friends();
				friends.setUserId(rs.getString("user_id"));
				friends.setFriendId(rs.getString("friend_id"));
				friends.setId(rs.getInt("id"));
				all.add(friends);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public int findCount() {
		String sql = " SELECT count(*) FROM FRIENDS";
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
		String sql = " SELECT count(*) FROM FRIENDS WHERE " + column + " LIKE ? ";
		int count = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
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
