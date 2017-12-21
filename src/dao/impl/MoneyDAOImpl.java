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

public class MoneyDAOImpl implements IMoneyDAO {
	private Connection conn;

	public MoneyDAOImpl() {
		this.conn = DButil.getConnection();
	}


	public int updateByID(Money money) {
		String sql = "UPDATE MONEY SET huaya_num=?,huaguan_num=? WHERE user_id = ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, money.getHuayaNum());
			pstmt.setInt(2, money.getHuaguanNum());
			pstmt.setString(3, money.getUserId());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Money findByID(String user_id) {
		String sql = " SELECT * FROM MONEY WHERE user_id = ? ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			Money money = null;
			while (rs.next()) {
				money = new Money();
				money.setUserId(rs.getString("user_id"));
				money.setHuayaNum(rs.getInt("huaya_num"));
				money.setHuaguanNum(rs.getInt("huaguan_num"));
			}
			pstmt.close();
			rs.close();
			return money;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Money> findAll() {
		List<Money> all = new ArrayList<Money>();
		String sql = " SELECT * FROM MONEY";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Money money = new Money();
				money.setUserId(rs.getString("user_id"));
				money.setHuayaNum(rs.getInt("huaya_num"));
				money.setHuaguanNum(rs.getInt("huaguan_num"));
				all.add(money);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<Money> findAll(String column, String kw) {
		List<Money> all = new ArrayList<Money>();
		String sql = " SELECT * FROM MONEY WHERE " + column + " LIKE ?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Money money = new Money();
				money.setUserId(rs.getString("user_id"));
				money.setHuayaNum(rs.getInt("huaya_num"));
				money.setHuaguanNum(rs.getInt("huaguan_num"));
				all.add(money);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<Money> findAll(int cp, int ls, String column, String kw) {
		List<Money> all = new ArrayList<Money>();
		String sql = " SELECT * FROM MONEY WHERE " + column + " LIKE ? limit ?,?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			pstmt.setInt(2, (cp - 1) * ls);
			pstmt.setInt(3, ls);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Money money = new Money();
				money.setUserId(rs.getString("user_id"));
				money.setHuayaNum(rs.getInt("huaya_num"));
				money.setHuaguanNum(rs.getInt("huaguan_num"));
				all.add(money);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public int findCount() {
		String sql = " SELECT count(*) FROM MONEY";
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
		String sql = " SELECT count(*) FROM MONEY WHERE " + column + " LIKE ? ";
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
