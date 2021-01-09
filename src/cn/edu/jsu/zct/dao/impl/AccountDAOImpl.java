package cn.edu.jsu.zct.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.jsu.zct.dao.IAccountDAO;
import cn.edu.jsu.zct.vo.Account;
import cn.edu.jsu.zct.vo.AccountFactory;

public class AccountDAOImpl implements IAccountDAO {
	private Connection conn;
	private String type;
	private PreparedStatement pstmt;
	
	public AccountDAOImpl(Connection conn, String type) {
		super();
		this.conn = conn;
		this.type = type;
	}

	@Override
	public boolean doCreate(Account vo) throws Exception {
		String sql = "INSERT INTO "+type+"(id,prj,time,rmb,note)VALUES(?,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getId());
		this.pstmt.setString(2, vo.getPrj());
		this.pstmt.setDate(3, vo.getTime());
		this.pstmt.setFloat(4, vo.getRmb());
		this.pstmt.setString(5, vo.getNote());
		return this.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doUpdate(Account vopr,Account vo) throws Exception {
		String sql = "UPDATE  "+type+" SET id=?,prj=?,time=?,rmb=?,note=? WHERE id=? AND prj=? AND time=? AND rmb=? AND note=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getId());
		this.pstmt.setString(2, vo.getPrj());
		this.pstmt.setDate(3, vo.getTime());
		this.pstmt.setFloat(4, vo.getRmb());
		this.pstmt.setString(5, vo.getNote());
		this.pstmt.setString(6, vopr.getId());
		this.pstmt.setString(7, vopr.getPrj());
		this.pstmt.setDate(8, vopr.getTime());
		this.pstmt.setFloat(9, vopr.getRmb());
		this.pstmt.setString(10, vopr.getNote());
		return this.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doRemove(Account vo) throws Exception {
		String sql = "DELETE FROM "+type+" WHERE id=? AND prj=? AND time=? AND rmb=? AND note=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getId());
		this.pstmt.setString(2, vo.getPrj());
		this.pstmt.setDate(3, vo.getTime());
		this.pstmt.setFloat(4, vo.getRmb());
		this.pstmt.setString(5, vo.getNote());
		return this.pstmt.executeUpdate()>0;
	}

	@Override
	public List<Account> findById(String id) throws Exception {
		List<Account> list = new ArrayList<Account>();
		String sql = "SELECT id,prj,time,rmb,note FROM "+type+" WHERE id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()) {
			Account vo = AccountFactory.getAccountInstance(type);
			vo.setId(rs.getString(1));
			vo.setPrj(rs.getString(2));
			vo.setTime(rs.getDate(3));
			vo.setRmb(rs.getFloat(4));
			vo.setNote(rs.getString(5));
			list.add(vo);
		}
		return list;
	}

	@Override
	public List<Account> findAll() throws Exception {
		List<Account> list = new ArrayList<Account>();
		String sql = "SELECT id,prj,time,rmb,note FROM "+type;
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()) {
			Account vo = AccountFactory.getAccountInstance(type);
			vo.setId(rs.getString(1));
			vo.setPrj(rs.getString(2));
			vo.setTime(rs.getDate(3));
			vo.setRmb(rs.getFloat(4));
			vo.setNote(rs.getString(5));
			list.add(vo);
		}
		return list;
	}

	@Override
	public boolean doRemoveByPrj(String id, String prj) throws Exception {
		String sql = "DELETE FROM "+type+" WHERE id=? AND prj=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, prj);
		this.pstmt.setString(2, id);
		return this.pstmt.executeUpdate()>0;
	}
}