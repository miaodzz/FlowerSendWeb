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

public class ProductDAOImpl implements IProductDAO {
	private Connection conn;

	public ProductDAOImpl() {
		this.conn = DButil.getConnection();
	}

	public boolean create(Product product) {
		String sql = "INSERT INTO PRODUCT(product_price,product_name,product_mean,product_type) values (?,?,?,?) ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, product.getProductPrice());
			pstmt.setString(2, product.getProductName());
			pstmt.setString(3, product.getProductMean());
			pstmt.setString(4, product.getProductType());
			if (pstmt.executeUpdate() == 1) {
				pstmt.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int deleteByID(Integer product_id) {
		String sql = "DELETE FROM PRODUCT WHERE product_id= ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, product_id);
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateByID(Product product) {
		String sql = "UPDATE PRODUCT SET product_price=?,product_name=?,product_mean=?,product_type=? WHERE product_id = ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, product.getProductPrice());
			pstmt.setString(2, product.getProductName());
			pstmt.setString(3, product.getProductMean());
			pstmt.setString(4, product.getProductType());
			pstmt.setInt(5, product.getProductId());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Product findByID(Integer product_id) {
		String sql = " SELECT * FROM PRODUCT WHERE product_id = ? ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, product_id);
			ResultSet rs = pstmt.executeQuery();
			Product product = null;
			while (rs.next()) {
				product = new Product();
				product.setProductPrice(rs.getInt("product_price"));
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductPicture(rs.getString("product_picture"));
				product.setProductMean(rs.getString("product_mean"));
				product.setProductType(rs.getString("product_type"));
			}
			pstmt.close();
			rs.close();
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Product> findAll() {
		List<Product> all = new ArrayList<Product>();
		String sql = " SELECT * FROM PRODUCT where product_id > 2";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductPrice(rs.getInt("product_price"));
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductPicture(rs.getString("product_picture"));
				product.setProductMean(rs.getString("product_mean"));
				product.setProductType(rs.getString("product_type"));
				all.add(product);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<Product> findAll(String column, String kw) {
		List<Product> all = new ArrayList<Product>();
		String sql = " SELECT * FROM PRODUCT WHERE " + column + " LIKE ?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductPrice(rs.getInt("product_price"));
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductPicture(rs.getString("product_picture"));
				product.setProductMean(rs.getString("product_mean"));
				product.setProductType(rs.getString("product_type"));
				all.add(product);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public List<Product> findAll(int cp, int ls, String column, String kw) {
		List<Product> all = new ArrayList<Product>();
		String sql = " SELECT * FROM PRODUCT WHERE " + column + " LIKE ? limit ?,?  ";
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kw + "%");
			pstmt.setInt(2, (cp - 1) * ls);
			pstmt.setInt(3, ls);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductPrice(rs.getInt("product_price"));
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductPicture(rs.getString("product_picture"));
				product.setProductMean(rs.getString("product_mean"));
				product.setProductType(rs.getString("product_type"));
				all.add(product);
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	public int findCount() {
		String sql = " SELECT count(*) FROM PRODUCT where product_id > 2";
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
		String sql = " SELECT count(*) FROM PRODUCT WHERE " + column + " LIKE ? ";
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
	public int updatePictureByID(Product product) {
		String sql = "UPDATE PRODUCT SET product_picture=? WHERE product_id = ? ";
		int result = 0;
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, product.getProductPicture());
			pstmt.setInt(2, product.getProductId());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
