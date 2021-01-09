package cn.edu.jsu.zct.test;

import org.junit.jupiter.api.Test;

import cn.edu.jsu.zct.factory.ServiceFactory;
import cn.edu.jsu.zct.util.AddExpense;
import cn.edu.jsu.zct.vo.Account;

import junit.framework.TestCase;

class AccountServiceTest {

	@Test
	void testInsert() {
		Account vo = AddExpense .getAnExpense();
		try {
			TestCase.assertTrue(ServiceFactory.getIAccountInstance().insert(vo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testUpdate() {
		Account vo = AddExpense .getAnExpense();
		Account vopr = null;
		try {
			vopr = ServiceFactory.getIAccountInstance().listById(vo.getId()).get(0);
			TestCase.assertTrue(ServiceFactory.getIAccountInstance().update(vopr, vo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testDelete() {
		Account vo = AddExpense .getAnExpense();
		Account vot = null;
		try {
			vot = ServiceFactory.getIAccountInstance().listById(vo.getId()).get(0);
			TestCase.assertTrue(ServiceFactory.getIAccountInstance().delete(vot));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testListByIdString() {
		Account vo = AddExpense .getAnExpense();
		try {
			TestCase.assertNotNull(ServiceFactory.getIAccountInstance().listById(vo.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
