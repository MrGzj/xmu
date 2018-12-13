package xmu.com.userInfo;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xmu.com.common.utils.BaseServlet;
import xmu.com.common.utils.MD5Util;
import xmu.com.customer.Shop;
import xmu.com.userInfo.dao.RegisterAndLoginDao;

@WebServlet("/register.do")
public class RegisterServlet extends BaseServlet {

	private static final long serialVersionUID = 4990061731637159869L;

	public void email(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = UUID.randomUUID().toString();
		String miPass = MD5Util.getInstance().getMD5(req.getParameter("passwd")).toString();
		Shop shop = new Shop();
		shop.setMail(req.getParameter("mail"));System.out.println(123);
		shop.setPassword(miPass);
		shop.setUser_id(id);
		RegisterAndLoginDao rd = new RegisterAndLoginDao();
		rd.addMail(shop);
		Shop sd=new Shop();
		sd.setLoginPerson(0);
		sd.setMail(req.getParameter("mail"));
		sd.setUser_id(id);
		req.getSession().setAttribute("user", sd);
		resp.sendRedirect("index.jsp");
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	public void phone(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = UUID.randomUUID().toString();
		String miPass = MD5Util.getInstance().getMD5(req.getParameter("passwdd")).toString();
		Shop shop = new Shop();
		shop.setPhone(req.getParameter("telenum"));
		shop.setPassword(miPass);
		shop.setUser_id(id);
		RegisterAndLoginDao rd = new RegisterAndLoginDao();
		rd.addPhone(shop);
		Shop sd=new Shop();
		sd.setLoginPerson(0);
		sd.setMail(req.getParameter("telenum"));
		sd.setUser_id(id);
		req.getSession().setAttribute("user", sd);
		resp.sendRedirect("index.jsp");
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
