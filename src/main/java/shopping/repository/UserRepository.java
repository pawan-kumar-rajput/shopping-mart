package shopping.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import shopping.entity.User;
import shopping.service.DbCon;

public class UserRepository {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public UserRepository() {
		this.con = DbCon.getConnection();
	}

	public User getUser(String email, String password) {
		User user = null;
		try {
			query = "select * from users where email=? and password=?";
			pst = con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setName(rs.getNString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return user;
	}

	public boolean saveUser(User user) {
		int i=0;
		try {
			query = "insert into users(name,email,password) values(?,?,?)";
			pst = con.prepareStatement(query);
			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getPassword());
		    i=pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i==1;
	}
}
