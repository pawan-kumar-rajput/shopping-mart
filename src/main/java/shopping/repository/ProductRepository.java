package shopping.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import shopping.entity.Product;
import shopping.service.DbCon;

public class ProductRepository {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductRepository() {
		this.con=DbCon.getConnection();
	}
	
	public List<Product> getProducts(){
		List<Product>products=new ArrayList<>();
		try {
			query="select * from products";
			pst=con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Product product=new Product();
				product.setPid(rs.getInt("pid"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getDouble("price"));
				product.setImage(rs.getString("image"));
				products.add(product);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return products;
	}
	
	public Product getProduct(int pid) {
		Product product=null;
		try {
			query="select * from products where pid=?";
			pst=con.prepareStatement(query);
			pst.setInt(1,pid);
			rs=pst.executeQuery();
			if(rs.next()) {
				product=new Product();
				product.setPid(pid);
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getDouble("price"));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return product;
	}
}
