package cn.edu.jsu.zct.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.edu.jsu.zct.vo.Account;

public class POIOperation {

	public static void excelExport(List<Account> list) throws IOException {
		String sp = JOptionPane.showInputDialog("��ַ:  ");
		if(sp==null) {
			JOptionPane.showMessageDialog(null, "����ʧ��");
			return;
		}
		File file = new File(sp);
		Workbook workbook = new XSSFWorkbook();// ��������������
		Sheet sheet = workbook.createSheet("account");// �������������
		
		Row row = sheet.createRow(0);// �����ж����±��0��ʼ
		Cell cell = row.createCell(0);// ������Ԫ�񣬴�0��ʼ
		cell.setCellValue("��Ŀ");// ��Ԫ����������
		cell = row.createCell(1);
		cell.setCellValue("ʱ��");
		cell = row.createCell(2);
		cell.setCellValue("���");
		cell = row.createCell(3);
		cell.setCellValue("��ע");
		
		int i=1;
		for(Account x:list) {
			row = sheet.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue(x.getPrj());
			cell = row.createCell(1);
			cell.setCellValue(x.getTime());
			cell = row.createCell(2);
			cell.setCellValue(x.getRmb());
			cell = row.createCell(3);
			cell.setCellValue(x.getNote());
			i++;
		}
		
		FileOutputStream fos = new FileOutputStream(file);// �������������
		workbook.write(fos);// ������д�뵽ָ����excel�ĵ�
		JOptionPane.showMessageDialog(null, "�����ɹ�");
		fos.close();
	}
	
}
