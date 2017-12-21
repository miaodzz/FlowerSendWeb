package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.sql.SQLException;

import dao.*;

import entity.*;
import util.DButil;

public class ProductorderDAOImpl implements IProductorderDAO {
	private Connection conn;

	public ProductorderDAOImpl() {
		this.conn = DButil.getConnection();
	}

	public boolean create(Productorder productorder) {
		String sql = "INSERT INTO PRODUCTORDER(order_by,order_to,product_id,send_days,everyday_number,count_price,sendback,purchase_time) values (?,?,?,?,?,?,?,?) ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, productorder.getOrderBy());
			pstmt.setString(2, productorder.getOrderTo());
			pstmt.setInt(3, productorder.getProductId());
			pstmt.setInt(4, productorder.getSendDays());
			pstmt.setInt(5, productorder.getEverydayNumber());
			pstmt.setInt(6, productorder.getCountPrice());
			pstmt.setInt(7, productorder.getSendback());
			pstmt.setTimestamp(8, productorder.getPurchaseTime());
			if (pstmt.executeUpdate() == 1) {
				pstmt.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int deleteByID(Integer product_order_id) {
		String sql = "DELETE FROM PRODUCTORDER WHERE product_order_id= ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, product_order_id);
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateByID(Productorder productorder) {
		String sql = "UPDATE PRODUCTORDER SET order_by=?,order_to=?,product_id=?,send_days=?,everyday_number=?,count_price=?,sendback=?,Purchase_Time=? WHERE product_order_id = ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, productorder.getOrderBy());
			pstmt.setString(2, productorder.getOrderTo());
			pstmt.setInt(3, productorder.getProductId());
			pstmt.setInt(4, productorder.getSendDays());
			pstmt.setInt(5, productorder.getEverydayNumber());
			pstmt.setInt(6, productorder.getCountPrice());
			pstmt.setInt(7, productorder.getSendback());
			pstmt.setTimestamp(8, productorder.getPurchaseTime());
			pstmt.setInt(9, productorder.getProductOrderId());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateSendbackByID(Productorder productorder) {
		String sql = "UPDATE PRODUCTORDER SET sendback=? WHERE product_order_id = ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, productorder.getSendback());
			pstmt.setInt(2, productorder.getProductOrderId());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Productorder findByID(Integer product_order_id) {
		String sql = " SELECT * FROM PRODUCTORDER WHERE product_order_id = ? ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, product_order_id);
			ResultSet rs = pstmt.executeQuery();
			Productorder productorder = null;
			while (rs.next()) {
				productorder = new Productorder();
				productorder.setProductOrderId(rs.getInt("product_order_id"));
				productorder.setOrderBy(rs.getString("order_by"));
				productorder.setOrderTo(rs.getString("order_to"));
				productorder.setProductId(rs.getInt("product_id"));
				productorder.setSendDays(rs.getInt("send_days"));
				productorder.setEverydayNumber(rs.getInt("everyday_number"));
				productorder.setCountPrice(rs.getInt("count_price"));
				productorder.setSendback(rs.getInt("sendback"));
				productorder.setPurchaseTime(rs.getTimestamp("purchase_time"));
			}
			pstmt.close();
			rs.close();
			return productorder;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Productorder> findAll() {
		List<Productorder> all = new ArrayList<Productorder>();
		String sql = " SELECT productorder.product_order_id, productorder.order_by, productorder.order_to, productorder.product_id, productorder.send_days, productorder.count_price, productorder.everyday_number, productorder.sendback, productorder.purchase_time, product.product_name, u1.nickname, u2.nickname FROM productorder, product, `user` AS u1, `user` AS u2 WHERE u1.user_id = productorder.order_by AND u2.user_id = productorder.order_to AND productorder.product_id = product.product_id";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Productorder productorder = new Productorder();
				productorder.setProductOrderId(rs.getInt("productorder.product_order_id"));
				productorder.setOrderBy(rs.getString("productorder.order_by"));
				productorder.setOrderTo(rs.getString("productorder.order_to"));
				productorder.setProductId(rs.getInt("productorder.product_id"));
				productorder.setSendDays(rs.getInt("productorder.send_days"));
				productorder.setEverydayNumber(rs.getInt("productorder.everyday_number"));
				productorder.setCountPrice(rs.getInt("productorder.count_price"));
				productorder.setSendback(rs.getInt("productorder.sendback"));
				productorder.setPurchaseTime(rs.getTimestamp("productorder.purchase_time"));
				productorder.setByNickname(rs.getString("u1.nickname"));
				productorder.setToNickname(rs.getString("u2.nickname"));
				productorder.setProductName(rs.getString("product.product_name"));
				all.add(productorder);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<Productorder> findAll(String column, String kw) {
		List<Productorder> all = new ArrayList<Productorder>();
		String sql = " SELECT productorder.product_order_id, productorder.order_by, productorder.order_to, productorder.product_id, productorder.send_days, productorder.count_price, productorder.everyday_number, productorder.sendback, productorder.purchase_time, product.product_name, u1.nickname, u2.nickname FROM productorder, product, `user` AS u1, `user` AS u2 WHERE u1.user_id = productorder.order_by AND u2.user_id = productorder.order_to AND productorder.product_id = product.product_id AND "
				+ column + " LIKE ?";

		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Productorder productorder = new Productorder();
				productorder.setProductOrderId(rs.getInt("productorder.product_order_id"));
				productorder.setOrderBy(rs.getString("productorder.order_by"));
				productorder.setOrderTo(rs.getString("productorder.order_to"));
				productorder.setProductId(rs.getInt("productorder.product_id"));
				productorder.setSendDays(rs.getInt("productorder.send_days"));
				productorder.setEverydayNumber(rs.getInt("productorder.everyday_number"));
				productorder.setCountPrice(rs.getInt("productorder.count_price"));
				productorder.setSendback(rs.getInt("productorder.sendback"));
				productorder.setPurchaseTime(rs.getTimestamp("productorder.purchase_time"));
				productorder.setByNickname(rs.getString("u1.nickname"));
				productorder.setToNickname(rs.getString("u2.nickname"));
				productorder.setProductName(rs.getString("product.product_name"));
				all.add(productorder);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<Productorder> findAll(String column, String kw, String key, String r) {
		List<Productorder> all = new ArrayList<Productorder>();
		if (key == null || r == null)
			return findAll(column, kw);
		String sql = null;
		if (r.equals("desc") || r.equals("asc")) {
			if (key.equals("count_price")) {
				sql = " SELECT productorder.product_order_id, productorder.order_by, productorder.order_to, productorder.product_id, productorder.send_days, productorder.count_price,productorder.everyday_number, productorder.sendback, productorder.purchase_time, product.product_name, u1.nickname, u2.nickname FROM productorder, product, `user` AS u1, `user` AS u2 WHERE u1.user_id = productorder.order_by AND u2.user_id = productorder.order_to AND productorder.product_id = product.product_id AND "
						+ column + " LIKE ? order by productorder.count_price " + r;
			}
			if (key.equals("purchase_time")) {
				sql = " SELECT productorder.product_order_id, productorder.order_by, productorder.order_to, productorder.product_id, productorder.send_days, productorder.count_price, productorder.everyday_number, productorder.sendback, productorder.purchase_time, product.product_name, u1.nickname, u2.nickname FROM productorder, product, `user` AS u1, `user` AS u2 WHERE u1.user_id = productorder.order_by AND u2.user_id = productorder.order_to AND productorder.product_id = product.product_id AND "
						+ column + " LIKE ? order by productorder.purchase_time " + r;
			}
			if (key.equals("count_amount")) {
				sql = " SELECT productorder.product_order_id, productorder.order_by, productorder.order_to, productorder.product_id, productorder.send_days, productorder.count_price,productorder.everyday_number, productorder.sendback, productorder.purchase_time, product.product_name, u1.nickname, u2.nickname FROM productorder, product, `user` AS u1, `user` AS u2 WHERE u1.user_id = productorder.order_by AND u2.user_id = productorder.order_to AND productorder.product_id = product.product_id AND "
						+ column + " LIKE ? order by productorder.everyday_number*productorder.send_days " + r;
			}
		}
		System.out.println(sql);
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Productorder productorder = new Productorder();
				productorder.setProductOrderId(rs.getInt("productorder.product_order_id"));
				productorder.setOrderBy(rs.getString("productorder.order_by"));
				productorder.setOrderTo(rs.getString("productorder.order_to"));
				productorder.setProductId(rs.getInt("productorder.product_id"));
				productorder.setSendDays(rs.getInt("productorder.send_days"));
				productorder.setEverydayNumber(rs.getInt("productorder.everyday_number"));
				productorder.setCountPrice(rs.getInt("productorder.count_price"));
				productorder.setSendback(rs.getInt("productorder.sendback"));
				productorder.setPurchaseTime(rs.getTimestamp("productorder.purchase_time"));
				productorder.setByNickname(rs.getString("u1.nickname"));
				productorder.setToNickname(rs.getString("u2.nickname"));
				productorder.setProductName(rs.getString("product.product_name"));
				all.add(productorder);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<Productorder> findAll(int cp, int ls, String column, String kw) {
		List<Productorder> all = new ArrayList<Productorder>();
		String sql = " SELECT * FROM PRODUCTORDER WHERE " + column + " LIKE ? limit ?,?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			pstmt.setInt(2, (cp - 1) * ls);
			pstmt.setInt(3, ls);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Productorder productorder = new Productorder();
				productorder.setProductOrderId(rs.getInt("product_order_id"));
				productorder.setOrderBy(rs.getString("order_by"));
				productorder.setOrderTo(rs.getString("order_to"));
				productorder.setProductId(rs.getInt("product_id"));
				productorder.setSendDays(rs.getInt("send_days"));
				productorder.setEverydayNumber(rs.getInt("everyday_number"));
				productorder.setCountPrice(rs.getInt("count_price"));
				productorder.setSendback(rs.getInt("sendback"));
				productorder.setPurchaseTime(rs.getTimestamp("purchase_time"));
				all.add(productorder);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public int findCount() {
		String sql = " SELECT count(*) FROM PRODUCTORDER";
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
		String sql = " SELECT count(*) FROM PRODUCTORDER WHERE " + column + " LIKE ? ";
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

	public int findLastId() {
		String sql = " SELECT last_insert_id()";
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

	public String findTodayStar(int man, int send) {// 0 收 女 1男 送 这里回一个user_id
		List<Productorder> all = new ArrayList<Productorder>();
		String ByOrTo = null, sex = null;
		switch (send) {
		case 0:
			ByOrTo = "order_to";
			break;
		case 1:
			ByOrTo = "order_by";
			break;
		default:
		}
		switch (man) {
		case 0:
			sex = "女";
			break;
		case 1:
			sex = "男";
			break;
		}
		String sql = "select user_id FROM user,productorder " + "WHERE user_id=productorder." + ByOrTo
				+ " and sex like ? " + "and (productorder.purchase_time between ? and ? )" + " group by user_id "
				+ "order by sum(productorder.count_price) DESC LIMIT 1";
		String id = null;
		long curtime = System.currentTimeMillis();
		// long zero = curtime / (1000 * 3600 * 24) * (1000 * 3600 * 24) -
		// TimeZone.getDefault().getRawOffset();
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, sex);
			pstmt.setDate(2, new java.sql.Date(curtime));
			pstmt.setDate(3, new java.sql.Date(curtime + 24 * 60 * 60 * 1000));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getString("user_id");

			}
			pstmt.close();
			rs.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

}
