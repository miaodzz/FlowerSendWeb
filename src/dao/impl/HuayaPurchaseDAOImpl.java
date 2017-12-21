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

public class HuayaPurchaseDAOImpl implements IHuayaPurchaseDAO {
	private Connection conn;

	public HuayaPurchaseDAOImpl() {
		this.conn = DButil.getConnection();
	}

	public boolean create(HuayaPurchase huayaPurchase) {
		String sql = "INSERT INTO HUAYA_PURCHASE(user_id,time,amount,money) values (?,?,?,?) ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, huayaPurchase.getUserId());
			pstmt.setTimestamp(2, huayaPurchase.getTime());
			pstmt.setInt(3, huayaPurchase.getAmount());
			pstmt.setInt(4, huayaPurchase.getMoney());
			if (pstmt.executeUpdate() == 1) {
				pstmt.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int deleteByID(Integer serialnumber) {
		String sql = "DELETE FROM HUAYA_PURCHASE WHERE serialnumber= ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, serialnumber);
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateByID(HuayaPurchase huayaPurchase) {
		String sql = "UPDATE HUAYA_PURCHASE SET user_id=?,amount=?,money=? WHERE serialnumber = ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, huayaPurchase.getUserId());
			//pstmt.setTimestamp(2, huayaPurchase.getTime());
			pstmt.setInt(2, huayaPurchase.getAmount());
			pstmt.setInt(3, huayaPurchase.getMoney());
			pstmt.setInt(4, huayaPurchase.getSerialnumber());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public HuayaPurchase findByID(Integer serialnumber) {
		String sql = " SELECT serialnumber,user.user_id,user.nickname,HUAYA_PURCHASE.time,amount,money FROM HUAYA_PURCHASE,USER WHERE serialnumber = ? AND HUAYA_PURCHASE.user_id=USER.user_id ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, serialnumber);
			ResultSet rs = pstmt.executeQuery();
			HuayaPurchase huayaPurchase = null;
			while (rs.next()) {
				huayaPurchase = new HuayaPurchase();
				huayaPurchase.setSerialnumber(rs.getInt("serialnumber"));
				huayaPurchase.setUserId(rs.getString("user.user_id"));
				huayaPurchase.setTime(rs.getTimestamp("HUAYA_PURCHASE.time"));
				huayaPurchase.setAmount(rs.getInt("amount"));
				huayaPurchase.setMoney(rs.getInt("money"));
				huayaPurchase.setNickname(rs.getString("user.nickname"));
			}
			pstmt.close();
			rs.close();
			return huayaPurchase;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<HuayaPurchase> findAll() {
		List<HuayaPurchase> all = new ArrayList<HuayaPurchase>();
		String sql = " SELECT serialnumber,user.user_id,user.nickname,HUAYA_PURCHASE.time,amount,money FROM HUAYA_PURCHASE,USER WHERE HUAYA_PURCHASE.user_id=USER.user_id";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				HuayaPurchase huayaPurchase = new HuayaPurchase();
				huayaPurchase.setSerialnumber(rs.getInt("serialnumber"));
				huayaPurchase.setUserId(rs.getString("user.user_id"));
				huayaPurchase.setTime(rs.getTimestamp("HUAYA_PURCHASE.time"));
				huayaPurchase.setAmount(rs.getInt("amount"));
				huayaPurchase.setMoney(rs.getInt("money"));
				huayaPurchase.setNickname(rs.getString("user.nickname"));
				all.add(huayaPurchase);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<HuayaPurchase> findAll(String column, String kw) {
		List<HuayaPurchase> all = new ArrayList<HuayaPurchase>();
		String sql = " SELECT * FROM HUAYA_PURCHASE WHERE " + column + " LIKE ?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				HuayaPurchase huayaPurchase = new HuayaPurchase();
				huayaPurchase.setSerialnumber(rs.getInt("serialnumber"));
				huayaPurchase.setUserId(rs.getString("user_id"));
				huayaPurchase.setTime(rs.getTimestamp("time"));
				huayaPurchase.setAmount(rs.getInt("amount"));
				huayaPurchase.setMoney(rs.getInt("money"));
				all.add(huayaPurchase);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<HuayaPurchase> findAll(int cp, int ls, String column, String kw) {
		List<HuayaPurchase> all = new ArrayList<HuayaPurchase>();
		String sql = " SELECT * FROM HUAYA_PURCHASE WHERE " + column + " LIKE ? limit ?,?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			pstmt.setInt(2, (cp - 1) * ls);
			pstmt.setInt(3, ls);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				HuayaPurchase huayaPurchase = new HuayaPurchase();
				huayaPurchase.setSerialnumber(rs.getInt("serialnumber"));
				huayaPurchase.setUserId(rs.getString("user_id"));
				huayaPurchase.setTime(rs.getTimestamp("time"));
				huayaPurchase.setAmount(rs.getInt("amount"));
				huayaPurchase.setMoney(rs.getInt("money"));
				all.add(huayaPurchase);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public int findCount() {
		String sql = " SELECT count(*) FROM HUAYA_PURCHASE";
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
		String sql = " SELECT count(*) FROM HUAYA_PURCHASE WHERE " + column + " LIKE ? ";
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

	@Override
	public int findCountUser(String attribute) {
		String sql = " SELECT count(*) FROM HUAYA_PURCHASE WHERE user_id like ? ";
		int count = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, attribute);
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

	@Override
	public List<HuayaPurchase> findByUser(String attribute) {
		List<HuayaPurchase> all = new ArrayList<HuayaPurchase>();
		String sql = " SELECT * FROM HUAYA_PURCHASE WHERE user_id LIKE ?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, attribute);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				HuayaPurchase huayaPurchase = new HuayaPurchase();
				huayaPurchase.setSerialnumber(rs.getInt("serialnumber"));
				huayaPurchase.setUserId(rs.getString("user_id"));
				huayaPurchase.setTime(rs.getTimestamp("time"));
				huayaPurchase.setAmount(rs.getInt("amount"));
				huayaPurchase.setMoney(rs.getInt("money"));
				all.add(huayaPurchase);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

}
