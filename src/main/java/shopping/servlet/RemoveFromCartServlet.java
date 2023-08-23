package shopping.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shopping.entity.Cart;
import shopping.service.CartService;
@WebServlet("/remove-item")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int pid=Integer.parseInt(request.getParameter("id"));
			HttpSession session=request.getSession();
			List<Cart>cartList=(List<Cart>) session.getAttribute("cartList");
			cartList=new CartService().removeItem(cartList, pid);
			session.setAttribute("cartList", cartList);
			session.setAttribute("cartSize",cartList.size());
			response.sendRedirect("cart.jsp");
	}
}
