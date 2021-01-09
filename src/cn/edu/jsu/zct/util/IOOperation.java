package cn.edu.jsu.zct.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import cn.edu.jsu.zct.vo.Account;

public class IOOperation {

	public static void txtExport(List<Account> list) throws IOException {
		String sp = JOptionPane.showInputDialog("��ַ:  ");
		if(sp==null) {
			JOptionPane.showMessageDialog(null, "����ʧ��");
			return;
		}
		File file = new File(sp);
		FileWriter fw = new FileWriter(file);
		for(Account x:list) {
			fw.write(x.getPrj()+"\t"+x.getTime()+"\t"+x.getRmb()+"\t"+x.getNote()+"\r\n");
		}
		JOptionPane.showMessageDialog(null, "�����ɹ�");
		fw.close();
	}
	
}
