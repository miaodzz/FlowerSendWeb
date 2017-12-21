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

public class UserPictureDAOImpl implements IUserPictureDAO {
	private Connection conn;

	public UserPictureDAOImpl() {
		this.conn = DButil.getConnection();
	}

	public boolean create(UserPicture userPicture) {
		String sql = "INSERT INTO USER_PICTURE(user_id,picture) values (?,?) ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, userPicture.getUserId());
			pstmt.setString(2, userPicture.getPicture());
			if (pstmt.executeUpdate() == 1) {
				pstmt.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int deleteByID(String user_id, String userPic) {
		String sql = "DELETE FROM USER_PICTURE WHERE user_id= ? and picture =? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, userPic);
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateByID(UserPicture userPicture) {
		String sql = "UPDATE USER_PICTURE SET picture=? WHERE user_id = ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, userPicture.getPicture());
			pstmt.setString(2, userPicture.getUserId());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public UserPicture findByID(String user_id) {
		String sql = " SELECT * FROM USER_PICTURE WHERE user_id = ? ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			UserPicture userPicture = null;
			while (rs.next()) {
				userPicture = new UserPicture();
				userPicture.setUserId(rs.getString("user_id"));
				userPicture.setPicture(rs.getString("picture"));
			}
			pstmt.close();
			rs.close();
			return userPicture;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<UserPicture> findAll() {
		List<UserPicture> all = new ArrayList<UserPicture>();
		String sql = " SELECT * FROM USER_PICTURE";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserPicture userPicture = new UserPicture();
				userPicture.setUserId(rs.getString("user_id"));
				userPicture.setPicture(rs.getString("picture"));
				all.add(userPicture);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<UserPicture> findAll(String column, String kw) {
		List<UserPicture> all = new ArrayList<UserPicture>();
		String sql = " SELECT * FROM USER_PICTURE WHERE " + column + " LIKE ?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserPicture userPicture = new UserPicture();
				userPicture.setUserId(rs.getString("user_id"));
				userPicture.setPicture(rs.getString("picture"));
				all.add(userPicture);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<UserPicture> findAll(int cp, int ls, String column, String kw) {
		List<UserPicture> all = new ArrayList<UserPicture>();
		String sql = " SELECT * FROM USER_PICTURE WHERE " + column + " LIKE ? limit ?,?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			pstmt.setInt(2, (cp - 1) * ls);
			pstmt.setInt(3, ls);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserPicture userPicture = new UserPicture();
				userPicture.setUserId(rs.getString("user_id"));
				userPicture.setPicture(rs.getString("picture"));
				all.add(userPicture);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public int findCount() {
		String sql = " SELECT count(*) FROM USER_PICTURE";
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
		String sql = " SELECT count(*) FROM USER_PICTURE WHERE " + column + " LIKE ? ";
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
