package xmu.com.userInfo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xmu.com.common.utils.BaseServlet;
import xmu.com.common.utils.MD5Util;
import xmu.com.customer.Shop;
import xmu.com.userInfo.dao.RegisterAndLoginDao;

@WebServlet("/login.do")
public class LoginServlet extends BaseServlet {

	private static final long serialVersionUID = -37912240012405082L;

	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String miPass = MD5Util.getInstance().getMD5(req.getParameter("pass")).toString();
		Shop shop = new Shop();
		shop.setMail(req.getParameter("name"));
		shop.setPhone(req.getParameter("name"));
		shop.setPassword(miPass);
		RegisterAndLoginDao rd = new RegisterAndLoginDao();
		List<Shop> s = rd.select(shop);
		if(s.size()==0) {
			req.setAttribute("loginRe", "�޴��ֻ��Ż����䣬�����������");
			try {
				req.getRequestDispatcher("home/login.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}else {
			Shop sd = s.get(0);
			req.getSession().setAttribute("user", sd);
			resp.sendRedirect("index.jsp");//�����jspҳ���Լ��㣬�����޸ĺ���������Ұ���������������ֹ��ͻ��
								  //ע���жϵ����������½�����ֻ��ŵ�½����Ϊ�����½�ֻ���Ϊnull���ֻ��ŵ�½����Ϊnull
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}
