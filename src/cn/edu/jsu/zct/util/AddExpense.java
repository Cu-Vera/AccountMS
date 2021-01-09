package cn.edu.jsu.zct.util;

//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import cn.edu.jsu.zct.factory.ServiceFactory;
import cn.edu.jsu.zct.vo.Expense;

public class AddExpense {
	static String[] id = {"000002","000003","000004","000005"};
	static String[] prj = {"��ʳ","����Ʒ","����"};
	static Random random=new Random();
	
	
	public static void main(String[] args) throws Exception {
		for(int i=0; i<10000; i++) {
			Expense ex = getAnExpense();
			ServiceFactory.getIAccountInstance().insert(ex);
			System.out.println(ex+";"+i);
		}
//		System.out.println(id[getAIndex(id)]);
	}
	
	public static Expense getAnExpense() {
		Expense ex = new Expense();
		
		ex.setId(id[getAIndex(id)]);
		ex.setPrj(prj[getAIndex(prj)]);
		ex.setTime(getSqlDate());
		ex.setRmb(-(100*random.nextFloat())%99-1);
		ex.setNote("");
		
		return ex;
	}
	
	public static int getAIndex(String[] o) {
		int x = random.nextInt();
		if(x>=0) {
			return x%(o.length);
		}else {
			return -x%(o.length);
		}
		
	}
	
	public static java.sql.Date getSqlDate() {
		java.util.Date utildate = randomDate("2016-01-01", "2021-01-01"); 
		return new java.sql.Date(utildate.getTime());
	}

	private static Date randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(beginDate);// ���쿪ʼ����
			Date end = format.parse(endDate);// �����������
			// getTime()��ʾ������ 1970 �� 1 �� 1 �� 00:00:00 GMT ������ Date �����ʾ�ĺ�������
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		// ������ص��ǿ�ʼʱ��ͽ���ʱ�䣬��ݹ���ñ������������ֵ
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}

}

