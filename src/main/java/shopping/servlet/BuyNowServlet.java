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
import shopping.service.CartService;

@WebServlet("/buy-now")
public class BuyNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();

			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				int pid = Integer.parseInt(request.getParameter("id"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				if (quantity <= 0) {
					quantity = 1;
				}

				Order order = new Order();
				order.setPid(pid);
				order.setUid(user.getUid());
				order.setQuantity(quantity);
				order.setDate(formatter.format(date));

				OrderRepository orderRepository = new OrderRepository();
				boolean result = orderRepository.saveOrder(order);
				if (result) {
					HttpSession session=request.getSession();
					List<Cart>cartList=(List<Cart>) session.getAttribute("cartList");
					cartList=new CartService().removeItem(cartList, pid);
					session.setAttribute("cartList", cartList);
					session.setAttribute("cartSize",cartList.size());
					response.sendRedirect("order.jsp");
				} else {
					System.out.println("order failed");
				}

			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
