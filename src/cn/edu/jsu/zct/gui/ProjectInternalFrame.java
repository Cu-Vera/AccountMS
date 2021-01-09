package cn.edu.jsu.zct.gui;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cn.edu.jsu.zct.factory.ServiceFactory;
import cn.edu.jsu.zct.vo.Project;
import cn.edu.jsu.zct.vo.User_;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Font;

public class ProjectInternalFrame extends JInternalFrame {

	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private User_ user;
	
	private JPanel panS;
	private JScrollPane sPanC;
	private JTextField txtAdd;
	private JTextField txtQuery;
	private String[] titles = { "项目"};// 定义数组表示表格标题;
	private JTable table;
	private DefaultTableModel model;
	private TableRowSorter sorter;

	
	/**
	 * Create the frame.
	 */
	public ProjectInternalFrame(User_ user) {
		this.user = user;
		
		setBounds(100, 100, 480, 360);
		setClosable(true);
		
		sPanC = new JScrollPane();
		getContentPane().add(sPanC, BorderLayout.CENTER);
		
		panS = new JPanel();
		getContentPane().add(panS, BorderLayout.SOUTH);
		
		JButton btnQuery = new JButton("\u67E5\u8BE2");
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				initTable();
				initRowFilter();
				table.setRowSorter(sorter);
			}
		});
		
		txtAdd = new JTextField();
		txtAdd.setColumns(10);
		panS.add(txtAdd);
		JButton btnAdd = new JButton("\u589E\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtAdd.getText().length()!=0) {
						ServiceFactory.getIProjectInstance().insert(new Project(txtAdd.getText(),user.getId()));
						initTable();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panS.add(btnAdd);
		txtQuery = new JTextField();
		panS.add(txtQuery);
		txtQuery.setColumns(10);
		panS.add(btnQuery);
		
		initTable();
	}
	
	private void initTable() {
		model=new DefaultTableModel(titles, 0);//定义表格数据模型的表格标题和行数(为0行)
		initModel();
		table = new JTable(model){
            public boolean isCellEditable(int row, int column){
            	return false;
            }//表格不允许被编辑
        };   //实例化表格装载表格模型
		table.setFont(new Font("宋体", Font.PLAIN, 19));
		table.setRowHeight(20);
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
							Project prj = getRowProject(row);
							try {
								if (rsi == 0) {
									String sp = JOptionPane.showInputDialog("项目名:");
									if(sp!=null&&sp.length()!=0) {
										ServiceFactory.getIProjectInstance().update(
											prj, new Project(sp,user.getId()));
									}
								} else if (rsi == 1) {
									ServiceFactory.getIProjectInstance().delete(prj);
								}
								initTable();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			            }
			            //从而获得双击选择的行
			        }
			}
		});
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //保证每次只选中一行;
        
		table.setAutoCreateRowSorter(true);//当用户单击列标题时可以自动排序
		
		sorter = new TableRowSorter<DefaultTableModel>(model);//设置排序器
		initRowFilter();
		table.setRowSorter(sorter);		
		
		sPanC.setViewportView(table);
	}
	
	private void initRowFilter() {
		RowFilter<Object, Object> cjFilter = new RowFilter<Object, Object>() {
			public boolean include(Entry<? extends Object, ? extends Object> entry) {
				if (!entry.getStringValue(0).matches(".*"+txtQuery.getText()+".*")) {
					return false;
				}
				return true;
			}
		};
		sorter.setRowFilter(cjFilter);// 设置过滤器
	}
	
	private void initModel() {
		List<String> lt = null;
		try {
			lt = ServiceFactory.getIProjectInstance().listPrjById(this.user.getId());
		}catch (Exception e) {
			// TODO: handle exception
		}
		Iterator<String> iter = lt.iterator();
		while(iter.hasNext()) {
			Vector vc = new Vector();
			String s = iter.next();
			vc.add(0, s);
			model.addRow(vc);
		}
	}
	
	private Project getRowProject(int row) {
		Project pt = new Project((String)table.getValueAt(row, 0),this.user.getId());
		return pt;
	}
}
