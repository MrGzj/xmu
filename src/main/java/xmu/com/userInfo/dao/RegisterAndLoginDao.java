package xmu.com.userInfo.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import xmu.com.common.utils.DbUtil;
import xmu.com.customer.Shop;

public class RegisterAndLoginDao {
	
	private QueryRunner qr = null;
	
	public void init() {
		qr = new QueryRunner(DbUtil.getDataSource());
	}

	/**
	 * ����û��������û���
	 * @param shop
	 */
	public void addMail(Shop shop) {
		init();
		String sql = "insert into user(user_id,password,mail) values (?,?,?)";
		try {
			qr.insert(sql, new BeanListHandler<Shop>(Shop.class),shop.getUser_id(),shop.getPassword(),shop.getMail());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����û����ֻ��û���
	 */
	public void addPhone(Shop shop) {
		init();
		String sql = "insert into user(user_id,password,phone) values (?,?,?)";
		try {
			qr.insert(sql, new BeanListHandler<Shop>(Shop.class),shop.getUser_id(),shop.getPassword(),shop.getPhone());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ѯ�Ƿ������䣬�û������ֻ���,��������ԣ����ɹ�����true
	 * @param shop
	 */
	public List<Shop> select(Shop shop) {
		init();
		String sql = "select * from user where password=? and mail=?";
		List<Shop> s = null;
		try {
			s = qr.query(sql, new BeanListHandler<Shop>(Shop.class), shop.getPassword(),shop.getMail());
			if(s.size()!=0) {
				Shop ss=s.get(0);
				ss.setLoginPerson(0);
				s.clear();
				s.add(ss);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(s.size()==0) {
			sql = "select * from user where password=? and phone=?";
			try {
				s = qr.query(sql, new BeanListHandler<Shop>(Shop.class), shop.getPassword(),shop.getPhone());
				Shop ss=s.get(0);
				ss.setLoginPerson(1);
				s.clear();
				s.add(ss);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return s;
	}
}
