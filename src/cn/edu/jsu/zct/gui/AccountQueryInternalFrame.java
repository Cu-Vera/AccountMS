package cn.edu.jsu.zct.gui;

import javax.swing.JInternalFrame;

import cn.edu.jsu.zct.factory.ServiceFactory;
import cn.edu.jsu.zct.util.IOOperation;
import cn.edu.jsu.zct.vo.Account;
import cn.edu.jsu.zct.vo.AccountFactory;
import cn.edu.jsu.zct.vo.User_;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JMenu;

public class AccountQueryInternalFrame extends JInternalFrame {

	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	 
	private User_ user;
	private List<Account> list;
	
	private JPanel panW;
	private JScrollPane sPanC;
	private JRadioButton rbIncome;
	private JRadioButton rbExpense;
	private JRadioButton rbAccount;
	private JTextField txtYear;
	private JTextField txtMon;
	private JTextField txtDay;
	private JComboBox<String> comboBoxPrj;
	private String[] titles = { "项目","时间","金额","备注"};// 定义数组表示表格标题;
	private JTable table;
	private DefaultTableModel model;
	private TableRowSorter sorter;
	private JButton button;
	private JButton btnTxt;
	private JPanel panS;
	private JLabel label;
	private JLabel lblTotal;
	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public AccountQueryInternalFrame(User_ user) throws Exception {
		this.user = user;
		
		setBounds(100, 100, 480, 360);
		setClosable(true);
		
		
		sPanC = new JScrollPane();
		getContentPane().add(sPanC, BorderLayout.CENTER);
		
		panW = new JPanel();
		getContentPane().add(panW, BorderLayout.WEST);
		panW.setLayout(new GridLayout(13, 1, 1, 1));
		
		button = new JButton("\u589E\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddF(user,null);
			}
		});
		panW.add(button);
		
		JButton btnQuery = new JButton("\u67E5\u8BE2");
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnQueryPerformed();
			}
		});
		
		panW.add(btnQuery);
		
		JLabel lblType = new JLabel("\u7C7B\u578B\uFF1A");
		panW.add(lblType);
		
		rbIncome = new JRadioButton("\u6536\u5165");
		panW.add(rbIncome);
		
		rbExpense = new JRadioButton("\u652F\u51FA");
		panW.add(rbExpense);
		
		rbAccount = new JRadioButton("\u5168\u90E8");
		panW.add(rbAccount);
		
		JLabel lblPrj = new JLabel("\u9879\u76EE\uFF1A");
		panW.add(lblPrj);
		
		Vector<String> vt = new Vector<String>();
		vt.addAll(ServiceFactory.getIProjectInstance().listPrjById(user.getId()));
		comboBoxPrj = new JComboBox<String>(vt);
		panW.add(comboBoxPrj);

		JLabel lblTime = new JLabel("\u65F6\u95F4\uFF1A");
		panW.add(lblTime);
		
		txtYear = new JTextField();
		txtYear.setText("yyyy");
		panW.add(txtYear);
		txtYear.setColumns(10);
		
		txtMon = new JTextField();
		txtMon.setText("MM");
		panW.add(txtMon);
		txtMon.setColumns(10);
		
		txtDay = new JTextField();
		txtDay.setText("dd");
		panW.add(txtDay);
		txtDay.setColumns(10);
		
		ButtonGroup rbGroup = new ButtonGroup();
		rbGroup.add(rbIncome);
		rbGroup.add(rbExpense);
		rbGroup.add(rbAccount);
		
		btnTxt = new JButton("\u5BFC\u51FAtxt");
		panW.add(btnTxt);
		btnTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					IOOperation.txtExport(exportList());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "导出失败");
					e.printStackTrace();
				}
			}
		});
		
		panS = new JPanel();
		getContentPane().add(panS, BorderLayout.SOUTH);
		
		label = new JLabel("\u5171\u8BA1\uFF1A");
		panS.add(label);
		
		lblTotal = new JLabel("   ");
		panS.add(lblTotal);
		
		initTable();
	}
	
	private void initTable() {
		model=new DefaultTableModel(titles, 0);//定义表格数据模型的表格标题和行数(为0行)
		initModel(this.list);
		table = new JTable(model){
            public boolean isCellEditable(int row, int column){
            	return false;
            }//表格不允许被编辑
        };   //实例化表格装载表格模型
        table.getTableHeader().setReorderingAllowed(false);
    	//		设置列表头不可别用户重新拖动排列
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				 if (arg0.getClickCount() == 2)
			        {
					 	int row = table.getSelectedRow();
			            if(row>=0) {
			            	String[] sa = {"UPDATE","DELETE"};
							int rsi = JOptionPane.showOptionDialog(null, "Update or Delete", "Operation",
									JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, sa, sa[0]);
							Account acc = getRowAccount(row);
							try {
								if (rsi == 0) {
									ServiceFactory.getIAccountInstance().delete(acc);
									showAddF(user, acc);
								} else if (rsi == 1) {
									ServiceFactory.getIAccountInstance().delete(acc);
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			            }
			            //从而获得双击时选择的行
			        }
			}
		});
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //保证每次只选中一行;
        
		table.setAutoCreateRowSorter(true);//当用户单击列标题时可以自动排序
		
		sorter = new TableRowSorter<DefaultTableModel>(model);//设置排序器
		initRowFilter();
		table.setRowSorter(sorter);
		
		lblTotal.setText(count().toString());
		sPanC.setViewportView(table);
	}
	
	private void btnQueryPerformed() {
		if(rbIncome.isSelected()) {
			this.list = initList("Income");
		}else if(rbExpense.isSelected()) {
			this.list = initList("Expense");
		}else if(rbAccount.isSelected()) {
			this.list = initList("Account");
		}else {
			JOptionPane.showMessageDialog(null, "类型不能为空");
			return;
		}
		if(!yearCheck()||!monCheck()||!dayCheck()){
			JOptionPane.showMessageDialog(null, "日期格式错误，应为 yyyy-MM-dd");
			return;
		}
		initTable();
		JOptionPane.showMessageDialog(null, "查询成功");
	}
	
	private void initRowFilter() {
		RowFilter<Object, Object> cjFilter = new RowFilter<Object, Object>() {
			public boolean include(Entry<? extends Object, ? extends Object> entry) {
				String prj = comboBoxPrj.getSelectedItem().toString();
				if (!entry.getStringValue(0).equals(prj)
						&& !"全部".equals(prj)) {
					return false;
				}
				if(getDate("yyyy",txtYear.getText())!=null &&
						!entry.getStringValue(1).matches(""+txtYear.getText()+".{6}")){
					return false;
				}
				if(getDate("MM",txtMon.getText())!=null &&
						!entry.getStringValue(1).matches(".{5}"+txtMon.getText()+".{3}")){
					return false;
				}
				if(getDate("dd",txtDay.getText())!=null &&
						!entry.getStringValue(1).matches(".{8}"+txtDay.getText())){
					return false;
				}
				return true;
			}
		};
		sorter.setRowFilter(cjFilter);// 设置过滤器
	}

	private void initModel(List<Account> lt) {
		if(lt==null) {
			return;
		}
		Iterator<Account> iter = lt.iterator();
		while(iter.hasNext()) {
			Vector vc = new Vector();
			Account at = iter.next();
			vc.add(0, at.getPrj());
			vc.add(1, at.getTime());
			vc.add(2, at.getRmb());
			vc.add(3, at.getNote());
			model.addRow(vc);
		} 
	}
	private List<Account> initList(String type) {
		List<Account> lt = null;
		try {
			if("Income".equals(type)||"Expense".equals(type)) {
				lt = ServiceFactory.getIAccountInstance().listById(this.user.getId(), type);
			}else if("Account".equals(type)) {
				lt = ServiceFactory.getIAccountInstance().listById(this.user.getId());
			}
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return lt;
	}
	
	private List<Account> exportList(){
		List<Account> list = new ArrayList<Account>();
		for(int i=0; i<table.getRowCount(); i++) {
			Account ac = getRowAccount(i);
			list.add(ac);
		}
//		System.out.println(list);
		return list;
	}
	
	private Account getRowAccount(int row) {
		String type = null;
		Float f = (Float)table.getValueAt(row, 2);
		if(f!=null&&f>0)
		{
			type = "Income";
		}else if(f<0){
			type = "Expense";
		}
		Account at = AccountFactory.getAccountInstance(type);
		at.setId(this.user.getId());
		at.setPrj((String)table.getValueAt(row, 0));
		at.setTime((java.sql.Date)table.getValueAt(row, 1));
		at.setRmb(f);
		at.setNote((String)table.getValueAt(row, 3));
		
		return at;
	}
	
	private Float count() {
		Float f = 0.0f;
		for(int i=0; i<table.getRowCount();i++) {
			f = f + (Float)table.getValueAt(i, 2);
		}
		return f;
	}
	
	private boolean yearCheck() {
		if(txtYear.getText().length()==0 || txtYear.getText().equals("yyyy")) {
			return true;
		}
		if(getDate("yyyy",txtYear.getText())!=null) {
			return true;
		}
		return false;
	}
	
	private boolean monCheck() {
		if(txtMon.getText().length()==0 || txtMon.getText().equals("MM")) {
			return true;
		}
		if(getDate("MM",txtMon.getText())!=null) {
			return true;
		}
		return false;
	}
	
	private boolean dayCheck() {
		if(txtDay.getText().length()==0 || txtDay.getText().equals("dd")) {
			return true;
		} 
		if(getDate("dd",txtDay.getText())!=null) {
			return true;
		}
		return false;
	}
	
	private java.util.Date getDate(String pattern, String source) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		java.util.Date utildate = null;
		try {
			utildate = simpleDateFormat.parse(source);
		} catch (ParseException e) {
		}
		return utildate;
	}
	
	private void showAddF(User_ user,Account acc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountAddF ff = new AccountAddF(user,acc);
					ff.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
