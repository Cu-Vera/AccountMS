package cn.edu.jsu.zct.vo;

import java.io.Serializable;

public class User_ implements Serializable {

	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String password;
	private String name;
	private String rank;
	public User_() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User_(String id, String password, String name, String rank) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.rank = rank;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", rank=" + rank + "]";
	}
}
