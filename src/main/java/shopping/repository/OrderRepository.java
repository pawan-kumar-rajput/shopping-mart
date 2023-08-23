package shopping.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import shopping.entity.Order;
import shopping.service.DbCon;

public class OrderRepository {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public OrderRepository() {
		this.con = DbCon.getConnection();
	}

	public boolean saveOrder(Order order) {
		boolean result = false;
		try {
			query = "insert into orders (pid, uid, quantity, date) values(?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, order.getPid());
			pst.setInt(2, order.getUid());
			pst.setInt(3, order.getQuantity());
			pst.setString(4, order.getDate());
			pst.executeUpdate();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Order> getOrders(int uid) {
		List<Order> orders = new ArrayList<>();
		try {
			query = "select * from orders inner join products on orders.pid=products.pid where orders.uid=?";
			pst = con.prepareStatement(query);
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("oid"));
				order.setPid(rs.getInt("pid"));
				order.setName(rs.getString("name"));
				order.setCategory(rs.getString("category"));
				order.setPrice(rs.getDouble("price") * rs.getInt("quantity"));
				order.setQuantity(rs.getInt("quantity"));
				order.setDate(rs.getString("date"));
				orders.add(order);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return orders;
	}

	public boolean removeOrder(int oid) {
		int i = 0;
		try {
			query = "delete from orders where oid=?";
			pst = con.prepareStatement(query);
			pst.setInt(1, oid);
			i = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i == 1;
	}
}
