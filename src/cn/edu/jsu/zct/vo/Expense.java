package cn.edu.jsu.zct.vo;

import java.io.Serializable;
import java.sql.Date;

public class Expense extends Account implements Serializable {

	/**
	 * default serialVersionUID
	 */  
	private static final long serialVersionUID = 1L;
	
	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Expense(String id, String prj, Date time, Float rmb, String note) {
		super(id, prj, time, rmb, note);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Expense [getId()=" + getId() + ", getPrj()=" + getPrj() + ", getTime()=" + getTime() + ", getRmb()="
				+ getRmb() + ", getNote()=" + getNote() + "]";
	}
}
