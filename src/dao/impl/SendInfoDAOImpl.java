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

public class SendInfoDAOImpl implements ISendInfoDAO {
	private Connection conn;

	public SendInfoDAOImpl() {
		this.conn = DButil.getConnection();
	}

	public boolean create(SendInfo sendInfo) {
		String sql = "INSERT INTO SEND_INFO(product_order_id, send_state, send_time, sendman,message) values (?,?,?,?,?) ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, sendInfo.getProductOrderId());
			pstmt.setString(2, sendInfo.getSendState());
			pstmt.setTimestamp(3, sendInfo.getSendTime());
			pstmt.setString(4, sendInfo.getSendman());
			pstmt.setString(5, sendInfo.getMessage());
			if (pstmt.executeUpdate() == 1) {
				pstmt.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int deleteByID(Integer send_id) {
		String sql = "DELETE FROM SEND_INFO WHERE send_id= ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, send_id);
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateByID(SendInfo sendInfo) {//主要是管理员用
		String sql = "UPDATE SEND_INFO SET product_order_id=?,send_state=?,sendman=? WHERE send_id = ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, sendInfo.getProductOrderId());
			pstmt.setString(2, sendInfo.getSendState());
			//pstmt.setTimestamp(3, sendInfo.getSendTime());
			pstmt.setString(3, sendInfo.getSendman());
			pstmt.setInt(4, sendInfo.getSendId());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public SendInfo findByID(Integer send_id) {
		String sql = " SELECT * FROM SEND_INFO WHERE send_id = ? ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, send_id);
			ResultSet rs = pstmt.executeQuery();
			SendInfo sendInfo = null;
			while (rs.next()) {
				sendInfo = new SendInfo();
				sendInfo.setSendId(rs.getInt("send_id"));
				sendInfo.setProductOrderId(rs.getInt("product_order_id"));
				sendInfo.setSendState(rs.getString("send_state"));
				sendInfo.setSendTime(rs.getTimestamp("send_time"));
				sendInfo.setSendman(rs.getString("sendman"));
				sendInfo.setMessage(rs.getString("message"));
			}
			pstmt.close();
			rs.close();
			return sendInfo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<SendInfo> findAll() {
		List<SendInfo> all = new ArrayList<SendInfo>();
		String sql = " SELECT * FROM SEND_INFO";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SendInfo sendInfo = new SendInfo();
				sendInfo.setSendId(rs.getInt("send_id"));
				sendInfo.setProductOrderId(rs.getInt("product_order_id"));
				sendInfo.setSendState(rs.getString("send_state"));
				sendInfo.setSendTime(rs.getTimestamp("send_time"));
				sendInfo.setSendman(rs.getString("sendman"));
				sendInfo.setMessage(rs.getString("message"));
				all.add(sendInfo);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<SendInfo> findAll(String column, String kw) {
		List<SendInfo> all = new ArrayList<SendInfo>();
		String sql = " SELECT * FROM SEND_INFO WHERE " + column + " LIKE ?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SendInfo sendInfo = new SendInfo();
				sendInfo.setSendId(rs.getInt("send_id"));
				sendInfo.setProductOrderId(rs.getInt("product_order_id"));
				sendInfo.setSendState(rs.getString("send_state"));
				sendInfo.setSendTime(rs.getTimestamp("send_time"));
				sendInfo.setSendman(rs.getString("sendman"));
				sendInfo.setMessage(rs.getString("message"));
				all.add(sendInfo);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<SendInfo> findPOID(Integer kw) {
		List<SendInfo> all = new ArrayList<SendInfo>();
		String sql = " SELECT * FROM SEND_INFO WHERE product_order_id= ? ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, kw);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SendInfo sendInfo = new SendInfo();
				sendInfo.setSendId(rs.getInt("send_id"));
				sendInfo.setProductOrderId(rs.getInt("product_order_id"));
				sendInfo.setSendState(rs.getString("send_state"));
				sendInfo.setSendTime(rs.getTimestamp("send_time"));
				sendInfo.setSendman(rs.getString("sendman"));

				sendInfo.setMessage(rs.getString("message"));
				all.add(sendInfo);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}
	
	public List<SendInfo> findAll(int cp, int ls, String column, String kw) {
		List<SendInfo> all = new ArrayList<SendInfo>();
		String sql = " SELECT * FROM SEND_INFO WHERE " + column + " LIKE ? limit ?,?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			pstmt.setInt(2, (cp - 1) * ls);
			pstmt.setInt(3, ls);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SendInfo sendInfo = new SendInfo();
				sendInfo.setSendId(rs.getInt("send_id"));
				sendInfo.setProductOrderId(rs.getInt("product_order_id"));
				sendInfo.setSendState(rs.getString("send_state"));
				sendInfo.setSendTime(rs.getTimestamp("send_time"));
				sendInfo.setSendman(rs.getString("sendman"));

				sendInfo.setMessage(rs.getString("message"));
				all.add(sendInfo);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public int findCount() {
		String sql = " SELECT count(*) FROM SEND_INFO";
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
		String sql = " SELECT count(*) FROM SEND_INFO WHERE " + column + " LIKE ? ";
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
	
	public int countPOID(Integer kw) {
		String sql = " SELECT count(*) FROM SEND_INFO WHERE  product_order_id = ? ";
		int count = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, kw );
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
	public int updateNormalByID(SendInfo sendInfo) {
		String sql = "UPDATE SEND_INFO SET  send_state=?,send_time=?,sendman=? WHERE send_id = ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, sendInfo.getSendState());
			pstmt.setTimestamp(2, sendInfo.getSendTime());
			pstmt.setString(3, sendInfo.getSendman());
			pstmt.setInt(4, sendInfo.getSendId());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


}
