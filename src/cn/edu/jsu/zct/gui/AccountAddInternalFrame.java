package cn.edu.jsu.zct.gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import cn.edu.jsu.zct.factory.ServiceFactory;
import cn.edu.jsu.zct.vo.Account;
import cn.edu.jsu.zct.vo.AccountFactory;
import cn.edu.jsu.zct.vo.User_;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class AccountAddInternalFrame extends JInternalFrame {
	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private User_ user;
	
	private JTextField textFRmb;
	private JTextField textFTime;
	private JTextField textFNote;
	private JRadioButton rbIncome;
	private JRadioButton rbExpense;
	private JComboBox<String> comboBoxPrj;
	private JLabel lblMessage;

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public AccountAddInternalFrame(User_ user) throws Exception {
		this.user = user;		
		setBounds(100, 100, 480, 360);
		getContentPane().setLayout(null);
		setClosable(true);
		
		JLabel lblType = new JLabel("\u7C7B\u578B");
		lblType.setFont(new Font("宋体", Font.PLAIN, 18));
		lblType.setBounds(15, 40, 47, 21);
		getContentPane().add(lblType);
		
		rbIncome = new JRadioButton("\u6536\u5165");
		rbIncome.setFont(new Font("宋体", Font.PLAIN, 15));
		rbIncome.setBounds(62, 36, 63, 29);
		getContentPane().add(rbIncome);
		
		rbExpense = new JRadioButton("\u652F\u51FA");
		rbExpense.setFont(new Font("宋体", Font.PLAIN, 15));
		rbExpense.setBounds(124, 36, 69, 29);
		getContentPane().add(rbExpense);
		
		ButtonGroup rbGroup = new ButtonGroup();
		rbGroup.add(rbIncome);
		rbGroup.add(rbExpense);
		
		JLabel lblPrj = new JLabel("\u9879\u76EE");
		lblPrj.setFont(new Font("宋体", Font.PLAIN, 18));
		lblPrj.setBounds(215, 40, 55, 21);
		getContentPane().add(lblPrj);
		
		Vector<String> vt = new Vector<String>();
		vt.addAll(ServiceFactory.getIProjectInstance().listPrjById(user.getId()));
		comboBoxPrj = new JComboBox<String>(vt);
		comboBoxPrj.setBounds(270, 37, 135, 27);
		getContentPane().add(comboBoxPrj);
		
		JLabel lblRmb = new JLabel("\u91D1\u989D\uFF1A");
		lblRmb.setFont(new Font("宋体", Font.PLAIN, 18));
		lblRmb.setBounds(15, 100, 65, 21);
		getContentPane().add(lblRmb);
		
		textFRmb = new JTextField();
		textFRmb.setFont(new Font("宋体", Font.PLAIN, 18));
		textFRmb.setBounds(80, 100, 96, 27);
		getContentPane().add(textFRmb);
		textFRmb.setColumns(10);
		
		JLabel label = new JLabel("\u65F6\u95F4\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(215, 100, 57, 21);
		getContentPane().add(label);
		
		textFTime = new JTextField();
		textFTime.setFont(new Font("宋体", Font.PLAIN, 18));
		textFTime.setBounds(270, 98, 96, 27);
		getContentPane().add(textFTime);
		textFTime.setColumns(10);
		
		JLabel lblNote = new JLabel("\u5907\u6CE8\uFF1A");
		lblNote.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNote.setBounds(15, 160, 65, 21);
		getContentPane().add(lblNote);
		
		textFNote = new JTextField();
		textFNote.setBounds(80, 160, 96, 27);
		getContentPane().add(textFNote);
		textFNote.setColumns(10);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAddPerformed();
			}
		});
		btnAdd.setFont(new Font("宋体", Font.PLAIN, 18));
		btnAdd.setBounds(57, 229, 123, 29);
		getContentPane().add(btnAdd);
		
		lblMessage = new JLabel("");
		lblMessage.setBounds(215, 166, 190, 21);
		getContentPane().add(lblMessage);
	}
	
	public  void btnAddPerformed() {
		Account a = null;
		lblMessage.setText("");
		if(rbIncome.isSelected()) {
			a = AccountFactory.getAccountInstance("Income");
		}else if(rbExpense.isSelected()) {
			a = AccountFactory.getAccountInstance("Expense");
		}else {
			lblMessage.setText("类型不能为空");
			return;
		}
		a.setId(this.user.getId());
		a.setPrj((String)comboBoxPrj.getSelectedItem());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlDate = null;
		java.util.Date utildate = null;
		try {
			utildate = simpleDateFormat.parse(textFTime.getText());
			sqlDate = new java.sql.Date(utildate.getTime());
			a.setTime(sqlDate);
		} catch (ParseException e) {
			lblMessage.setText("日期格式错误，应为yyyy-MM-dd");
			return;
		}
	    Float f = null;
	    try {
	    	f = Float.parseFloat(textFRmb.getText());
	    	if(("Income".equals(getType(a.getClass().getName()))&& f>0)
	    			||("Expense".equals(getType(a.getClass().getName()))&& f<0)) {
	    		a.setRmb(f);
	    	}else {
	    		lblMessage.setText("金额正负与类型不符");
	    		return;
	    	}
	    }catch (NumberFormatException e) {
			lblMessage.setText("金额输入错误");
			return;
		}
	    a.setNote(textFNote.getText());
	    try {
			ServiceFactory.getIAccountInstance().insert(a);
			JOptionPane.showMessageDialog(null, "增加成功");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "增加失败");
		}	      
	}
	
	private String getType(String s) {
		String[] as = null;
		as = s.split("[.]");
		return as[as.length-1];
	}
}
