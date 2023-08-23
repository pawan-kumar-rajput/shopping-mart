package shopping.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.entity.Cart;

@WebServlet("/quantity")
public class QuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action=request.getParameter("action");
			int pid=Integer.parseInt(request.getParameter("id"));
			List<Cart>cartList=(List<Cart>) request.getSession().getAttribute("cartList");
			if(action.equals("inc")) {
				for(Cart c:cartList) {
					if(c.getPid()==pid) {
						c.setQuantity(c.getQuantity()+1);
						response.sendRedirect("cart.jsp");
					}
				}
			}
			
			else if(action.equals("dec")) {
				for(Cart c:cartList) {
					if(c.getPid()==pid) {
						c.setQuantity(c.getQuantity()-1);
						response.sendRedirect("cart.jsp");
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
