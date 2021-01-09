package cn.edu.jsu.zct.vo;

public class AccountFactory {

	public static Account getAccountInstance(String type) {
		Account a = null;
		if("Income".equals(type)) {
			a = new Income();
		}
		else if("Expense".equals(type)) {
			a = new Expense();
		}
		return a;
	}
}
