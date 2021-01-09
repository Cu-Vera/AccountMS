package cn.edu.jsu.zct.dao.impl;

import java.text.SimpleDateFormat;

import cn.edu.jsu.zct.dbc.DatabaseConnection;
import cn.edu.jsu.zct.vo.Income;


public class test1 {

	public static void main(String[] args) {
		DatabaseConnection conn = new DatabaseConnection();
		AccountDAOImpl ad = new AccountDAOImpl(conn.getConnection(), "Income");
		java.sql.Date da = null;
		try {
			da = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-13").getTime());
			ad.doCreate(new Income("001","chi",da,33.5f,"2"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
