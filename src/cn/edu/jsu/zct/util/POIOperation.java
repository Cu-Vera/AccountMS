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
		String sp = JOptionPane.showInputDialog("地址:  ");
		if(sp==null) {
			JOptionPane.showMessageDialog(null, "导出失败");
			return;
		}
		File file = new File(sp);
		Workbook workbook = new XSSFWorkbook();// 创建工作簿对象
		Sheet sheet = workbook.createSheet("account");// 创建工作表对象
		
		Row row = sheet.createRow(0);// 创建行对象，下标从0开始
		Cell cell = row.createCell(0);// 创建单元格，从0开始
		cell.setCellValue("项目");// 单元格设置内容
		cell = row.createCell(1);
		cell.setCellValue("时间");
		cell = row.createCell(2);
		cell.setCellValue("金额");
		cell = row.createCell(3);
		cell.setCellValue("备注");
		
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
		
		FileOutputStream fos = new FileOutputStream(file);// 创建输出流对象
		workbook.write(fos);// 将内容写入到指定的excel文档
		JOptionPane.showMessageDialog(null, "导出成功");
		fos.close();
	}
	
}
