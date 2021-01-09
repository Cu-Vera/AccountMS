package cn.edu.jsu.zct.factory;

import java.sql.Connection;

import cn.edu.jsu.zct.dao.IAccountDAO;
import cn.edu.jsu.zct.dao.IProjectDAO;
import cn.edu.jsu.zct.dao.IUser_DAO;
import cn.edu.jsu.zct.dao.impl.AccountDAOImpl;
import cn.edu.jsu.zct.dao.impl.ProjectDAOImpl;
import cn.edu.jsu.zct.dao.impl.User_DAOImpl;

public class DAOFactory {

	public static IAccountDAO getIAccountDAOInstance(Connection conn,String type) {
		return new AccountDAOImpl(conn,type);
	}
	
	public static IProjectDAO getIProjectDAOInstance(Connection conn) {
		return new ProjectDAOImpl(conn);
	}
	
	public static IUser_DAO getIUser_DAOInstance(Connection conn) {
		return new User_DAOImpl(conn);
	}
}
