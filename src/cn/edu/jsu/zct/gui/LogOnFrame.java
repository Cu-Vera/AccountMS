package cn.edu.jsu.zct.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import cn.edu.jsu.zct.factory.ServiceFactory;
import cn.edu.jsu.zct.vo.Project;
import cn.edu.jsu.zct.vo.User_;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class LogOnFrame extends JFrame {

	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private User_ user;
	
	private JPanel contentPane;
	private JTextField textFID;
	private JTextField textFPassword;
	private JLabel lblMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogOnFrame frame = new LogOnFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogOnFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);	//窗口居中
		
		JPanel panN = new JPanel();
		contentPane.add(panN, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("\u4E2A\u4EBA\u6536\u652F\u7BA1\u7406\u7CFB\u7EDF");
		lblTitle.setFont(new Font("宋体", Font.PLAIN, 25));
		panN.add(lblTitle);
		
		JPanel panS = new JPanel();
		contentPane.add(panS, BorderLayout.SOUTH);
		
		JButton btnLogOn = new JButton("\u767B\u5F55");
		btnLogOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblMessage.setText("");
				try {
					List<User_> lt = ServiceFactory.getIUser_Instance().listById(textFID.getText());
					if(lt.size()!=1) {
						lblMessage.setText("ID错误");
					}else if(lt.get(0).getPassword().equals(textFPassword.getText())){
						user = lt.get(0);
						showPrimary(user);
						setVisible(false);
//						JOptionPane.showMessageDialog(null, "succeed!");
					}else {
						lblMessage.setText("密码错误");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panS.add(btnLogOn);
		
		JButton btnReg = new JButton("\u6CE8\u518C");
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMessage.setText("");
				String id = textFID.getText();
				String psw = textFPassword.getText();
				try {
					List<User_> lt = ServiceFactory.getIUser_Instance().listById(textFID.getText());
					if(lt.size()>0) {
						lblMessage.setText("ID已存在");
					}else if(!id.matches("\\d*")) {
						lblMessage.setText("ID应由数字组成"+lt.size());
					}else if(!psw.matches("\\w*")) {
						lblMessage.setText("密码应由字母、数字、下划线组成");
					}else if(psw.length()<6) {
						lblMessage.setText("密码应不少于6位");
					}else {
						String name = JOptionPane.showInputDialog("昵称:");
						if(name!=null&&name.length()>0) {
							ServiceFactory.getIUser_Instance().insert(new User_(id,psw,name,"1"));
							ServiceFactory.getIProjectInstance().insert(new Project("全部",id));
							JOptionPane.showMessageDialog(null, "注册成功");
						}
						else {
							lblMessage.setText("昵称不能为空，请重新注册");
						}
					}		
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		panS.add(btnReg);
		
		JPanel panC = new JPanel();
		contentPane.add(panC, BorderLayout.CENTER);
		panC.setLayout(null);
		
		JLabel lblID = new JLabel("ID: ");
		lblID.setFont(new Font("宋体", Font.PLAIN, 20));
		lblID.setBounds(120, 50, 56, 21);
		panC.add(lblID);
		
		textFID = new JTextField();
		textFID.setBounds(190, 47, 96, 27);
		panC.add(textFID);
		textFID.setColumns(10);
		
		JLabel lblpassword = new JLabel("\u5BC6\u7801: ");
		lblpassword.setFont(new Font("宋体", Font.PLAIN, 20));
		lblpassword.setBounds(120, 88, 76, 21);
		panC.add(lblpassword);
		
		textFPassword = new JTextField();
		textFPassword.setBounds(190, 85, 96, 27);
		panC.add(textFPassword);
		textFPassword.setColumns(10);
		
		lblMessage = new JLabel("");
		lblMessage.setBounds(120, 124, 166, 21);
		panC.add(lblMessage);
	}

	public void showPrimary(User_ user) {
		PrimaryFrame pf = new PrimaryFrame(user);
		pf.setVisible(true);
	}
}
