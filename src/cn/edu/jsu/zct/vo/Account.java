package cn.edu.jsu.zct.vo;

import java.sql.Date;

public abstract class Account {

	private String id;
	private String prj;
	private Date time;
	private Float rmb;
	private String note;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String id, String prj, Date time, Float rmb, String note) {
		super();
		this.id = id;
		this.prj = prj;
		this.time = time;
		this.rmb = rmb;
		this.note = note;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrj() {
		return prj;
	}
	public void setPrj(String prj) {
		this.prj = prj;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Float getRmb() {
		return rmb;
	}
	public void setRmb(Float rmb) {
		this.rmb = rmb;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Expense [id=" + id + ", prj=" + prj + ", time=" + time + ", rmb=" + rmb + ", note=" + note + "]";
	}
	
}
