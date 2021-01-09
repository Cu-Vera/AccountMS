package cn.edu.jsu.zct.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.jsu.zct.dao.IUser_DAO;
import cn.edu.jsu.zct.vo.User_;

public class User_DAOImpl implements IUser_DAO {
	
	private Connection conn;
	private PreparedStatement pstmt;

	public User_DAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public boolean doCreate(User_ vo) throws Exception {
		String sql = "INSERT INTO User_(id,password,name,rank)VALUES(?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getId());
		this.pstmt.setString(2, vo.getPassword());
		this.pstmt.setString(3, vo.getName());
		this.pstmt.setString(4, vo.getRank());
		return this.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doUpdate(User_ vopr, User_ vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(User_ vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User_> findById(String id) throws Exception {
		List<User_> list = new ArrayList<User_>();
		String sql = "SELECT id,password,name,rank FROM User_ WHERE id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()) {
			User_ vo = new User_();
			vo.setId(rs.getString(1));
			vo.setPassword(rs.getString(2));
			vo.setName(rs.getString(3));
			vo.setRank(rs.getString(4));
			list.add(vo);
		}
		return list;
	}

	@Override
	public List<User_> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
