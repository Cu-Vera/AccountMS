package cn.edu.jsu.zct.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.jsu.zct.dao.IProjectDAO;
import cn.edu.jsu.zct.vo.Project;

public class ProjectDAOImpl implements IProjectDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	public ProjectDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public boolean doCreate(Project vo) throws Exception {
		String sql = "INSERT INTO Project(name,id)VALUES(?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getName());
		this.pstmt.setString(2, vo.getId());
		return this.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doUpdate(Project vopr, Project vo) throws Exception {
		String sql = "UPDATE Project SET name=?,id=? WHERE name=? AND id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getName());
		this.pstmt.setString(2, vo.getId());
		this.pstmt.setString(3, vopr.getName());
		this.pstmt.setString(4, vopr.getId());
		return this.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doRemove(Project vo) throws Exception {
		String sql = "DELETE FROM Project WHERE name=? AND id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getName());
		this.pstmt.setString(2, vo.getId());
		return this.pstmt.executeUpdate()>0;
	}

	@Override
	public List<Project> findById(String id) throws Exception {
		List<Project> list = new ArrayList<Project>();
		String sql = "SELECT name,id FROM Project WHERE id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()) {
			Project vo = new Project();
			vo.setName(rs.getString(1));
			vo.setId(rs.getString(2));
			list.add(vo);
		}
		return list;
	}

	@Override
	public List<Project> findAll() throws Exception {
		List<Project> list = new ArrayList<Project>();
		String sql = "SELECT name,id FROM Project";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()) {
			Project vo = new Project();
			vo.setName(rs.getString(1));
			vo.setId(rs.getString(2));
			list.add(vo);
		}
		return list;
	}
}
