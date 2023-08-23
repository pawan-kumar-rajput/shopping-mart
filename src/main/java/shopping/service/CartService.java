package shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import shopping.entity.Cart;
import shopping.entity.Product;
import shopping.repository.ProductRepository;

public class CartService {
	public List<Cart> getCartProducts(List<Cart>cartList){
		List<Cart>products=new ArrayList<Cart>();
		cartList.forEach(c->{
			ProductRepository pr=new ProductRepository();
			Product p=pr.getProduct(c.getPid());
			Cart cart=new Cart();
			cart.setPid(p.getPid());
			cart.setName(p.getName());
			cart.setCategory(p.getCategory());
			cart.setPrice(p.getPrice()*c.getQuantity());
			cart.setQuantity(c.getQuantity());
			products.add(cart);
		});
		
		return products;
	}
	
	public double getTotalCartPrice(List<Cart>cartList) {
		double sum=0;
		
		try {
			List<Cart>products=getCartProducts(cartList);
			if(products.size()>0)
			sum=(products.stream().map(Cart::getPrice).reduce((p1,p2)->p1+p2)).get();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return sum;
	}
	
	public List<Cart> removeItem(List<Cart>cartList,int pid){
		cartList=cartList.stream().filter(c->c.getPid()!=pid).collect(Collectors.toList());
		return cartList;
	}
}
