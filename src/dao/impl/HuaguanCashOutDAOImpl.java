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

public class HuaguanCashOutDAOImpl implements IHuaguanCashOutDAO {
	private Connection conn;

	public HuaguanCashOutDAOImpl() {
		this.conn = DButil.getConnection();
	}

	public boolean create(HuaguanCashOut huaguanCashOut) {
		String sql = "INSERT INTO HUAGUAN_CASH_OUT(user_id,time,amount,money) values (?,?,?,?) ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, huaguanCashOut.getUserId());
			pstmt.setTimestamp(2, huaguanCashOut.getTime());
			pstmt.setInt(3, huaguanCashOut.getAmount());
			pstmt.setInt(4, huaguanCashOut.getMoney());
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
		String sql = "DELETE FROM HUAGUAN_CASH_OUT WHERE serialnumber= ? ";
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

	public int updateByID(HuaguanCashOut huaguanCashOut) {
		String sql = "UPDATE HUAGUAN_CASH_OUT SET user_id=?,amount=?,money=? WHERE serialnumber = ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, huaguanCashOut.getUserId());
			//pstmt.setTimestamp(2, huaguanCashOut.getTime());
			pstmt.setInt(2, huaguanCashOut.getAmount());
			pstmt.setInt(3, huaguanCashOut.getMoney());
			pstmt.setInt(4, huaguanCashOut.getSerialnumber());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public HuaguanCashOut findByID(Integer serialnumber) {
		String sql = " SELECT serialnumber,user.user_id,user.nickname,HUAGUAN_CASH_OUT.time,amount,money FROM HUAGUAN_CASH_OUT,USER WHERE HUAGUAN_CASH_OUT.user_id=USER.user_id and serialnumber = ? ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, serialnumber);
			ResultSet rs = pstmt.executeQuery();
			HuaguanCashOut huaguanCashOut = null;
			while (rs.next()) {
				huaguanCashOut = new HuaguanCashOut();
				huaguanCashOut.setSerialnumber(rs.getInt("serialnumber"));
				huaguanCashOut.setUserId(rs.getString("user.user_id"));
				huaguanCashOut.setTime(rs.getTimestamp("time"));
				huaguanCashOut.setAmount(rs.getInt("amount"));
				huaguanCashOut.setMoney(rs.getInt("money"));
				huaguanCashOut.setNickname(rs.getString("user.nickname"));
			}
			pstmt.close();
			rs.close();
			return huaguanCashOut;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<HuaguanCashOut> findAll() {
		List<HuaguanCashOut> all = new ArrayList<HuaguanCashOut>();
		String sql = " SELECT serialnumber,user.user_id,user.nickname,HUAGUAN_CASH_OUT.time,amount,money FROM HUAGUAN_CASH_OUT,USER WHERE HUAGUAN_CASH_OUT.user_id=USER.user_id ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				HuaguanCashOut huaguanCashOut = new HuaguanCashOut();
				huaguanCashOut = new HuaguanCashOut();
				huaguanCashOut.setSerialnumber(rs.getInt("serialnumber"));
				huaguanCashOut.setUserId(rs.getString("user.user_id"));
				huaguanCashOut.setTime(rs.getTimestamp("time"));
				huaguanCashOut.setAmount(rs.getInt("amount"));
				huaguanCashOut.setMoney(rs.getInt("money"));
				huaguanCashOut.setNickname(rs.getString("user.nickname"));
				all.add(huaguanCashOut);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<HuaguanCashOut> findAll(String column, String kw) {
		List<HuaguanCashOut> all = new ArrayList<HuaguanCashOut>();
		String sql = " SELECT * FROM HUAGUAN_CASH_OUT WHERE " + column + " LIKE ?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				HuaguanCashOut huaguanCashOut = new HuaguanCashOut();
				huaguanCashOut.setSerialnumber(rs.getInt("serialnumber"));
				huaguanCashOut.setUserId(rs.getString("user_id"));
				huaguanCashOut.setTime(rs.getTimestamp("time"));
				huaguanCashOut.setAmount(rs.getInt("amount"));
				huaguanCashOut.setMoney(rs.getInt("money"));
				all.add(huaguanCashOut);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<HuaguanCashOut> findAll(int cp, int ls, String column, String kw) {
		List<HuaguanCashOut> all = new ArrayList<HuaguanCashOut>();
		String sql = " SELECT * FROM HUAGUAN_CASH_OUT WHERE " + column + " LIKE ? limit ?,?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			pstmt.setInt(2, (cp - 1) * ls);
			pstmt.setInt(3, ls);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				HuaguanCashOut huaguanCashOut = new HuaguanCashOut();
				huaguanCashOut.setSerialnumber(rs.getInt("serialnumber"));
				huaguanCashOut.setUserId(rs.getString("user_id"));
				huaguanCashOut.setTime(rs.getTimestamp("time"));
				huaguanCashOut.setAmount(rs.getInt("amount"));
				huaguanCashOut.setMoney(rs.getInt("money"));
				all.add(huaguanCashOut);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public int findCount() {
		String sql = " SELECT count(*) FROM HUAGUAN_CASH_OUT";
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
		String sql = " SELECT count(*) FROM HUAGUAN_CASH_OUT WHERE " + column + " LIKE ? ";
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
	public List<HuaguanCashOut> findByUser(String attribute) {
		List<HuaguanCashOut> all = new ArrayList<HuaguanCashOut>();
		String sql = " SELECT * FROM HUAGUAN_CASH_OUT WHERE user_id like ?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, attribute);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				HuaguanCashOut huaguanCashOut = new HuaguanCashOut();
				huaguanCashOut.setSerialnumber(rs.getInt("serialnumber"));
				huaguanCashOut.setUserId(rs.getString("user_id"));
				huaguanCashOut.setTime(rs.getTimestamp("time"));
				huaguanCashOut.setAmount(rs.getInt("amount"));
				huaguanCashOut.setMoney(rs.getInt("money"));
				all.add(huaguanCashOut);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	@Override
	public int findCountUser(String attribute) {
		String sql = " SELECT count(*) FROM HUAGUAN_CASH_OUT WHERE user_id like ? ";
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

}
