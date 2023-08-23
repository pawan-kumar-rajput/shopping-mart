package shopping.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shopping.entity.Cart;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		ArrayList<Cart>cartList=new ArrayList<>();
		int id=Integer.parseInt(request.getParameter("id"));
		Cart cart=new Cart();
		cart.setPid(id);
		cart.setQuantity(1);
		HttpSession session =request.getSession();
		if(session.getAttribute("cartList")==null) {
			cartList.add(cart);
			session.setAttribute("cartList",cartList);
			session.setAttribute("cartSize",cartList.size());
			response.sendRedirect("index.jsp");
		}
		
		else {
			cartList=(ArrayList<Cart>) session.getAttribute("cartList");
			boolean exist=cartList.stream().anyMatch(c->c.getPid()==id);
			if(!exist) {
				cartList.add(cart);
				session.setAttribute("cartSize",cartList.size());
				response.sendRedirect("index.jsp");
			}
			else {
				response.sendRedirect("cart.jsp");
			}
		}
		
	}
}
