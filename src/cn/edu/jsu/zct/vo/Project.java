package cn.edu.jsu.zct.vo;

import java.io.Serializable;

public class Project implements Serializable {

	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String id;
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Project(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Project [name=" + name + ", id=" + id + "]";
	}
}
