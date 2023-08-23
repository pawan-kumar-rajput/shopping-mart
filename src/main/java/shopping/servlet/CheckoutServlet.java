package shopping.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shopping.entity.Cart;
import shopping.entity.Order;
import shopping.entity.User;
import shopping.repository.OrderRepository;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			HttpSession session=request.getSession();
			List<Cart>cartList=(List<Cart>)session.getAttribute("cartList");
			User user = (User)session.getAttribute("user");
			if (user != null && cartList!=null) {
				cartList.forEach(c->{
					Order order=new Order();
					order.setPid(c.getPid());
					order.setUid(user.getUid());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					new OrderRepository().saveOrder(order);
					
				});
				cartList.clear();
				session.setAttribute("cartSize",0);
				response.sendRedirect("order.jsp");
				}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
