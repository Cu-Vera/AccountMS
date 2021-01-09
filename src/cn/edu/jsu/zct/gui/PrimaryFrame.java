package cn.edu.jsu.zct.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.jsu.zct.vo.User_;

import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import java.awt.Font;

public class PrimaryFrame extends JFrame {

	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private User_ user;
	
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PrimaryFrame() {
		setTitle("\u4E2A\u4EBA\u6536\u652F\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 525);
		setLocationRelativeTo(null);	//窗口居中
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuAccount = new JMenu("   \u6536\u652F   ");
		menuAccount.setFont(new Font("华文细黑", Font.BOLD, 20));
		menuBar.add(menuAccount);
		
		JButton btnaa = new JButton("\u6536\u652F\u6DFB\u52A0");
		menuAccount.add(btnaa);
		
		JButton button = new JButton("\u6536\u652F\u7BA1\u7406");
		menuAccount.add(button);
		
		JButton btnPrj = new JButton("\u9879\u76EE\u7BA1\u7406");
		btnPrj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProjectInternalFrame p = null;
				contentPane.removeAll();
				try {
					p = new ProjectInternalFrame(user);
					p.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				contentPane.add(p,BorderLayout.CENTER);
			}
		});
		menuAccount.add(btnPrj);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountQueryInternalFrame aa = null;
				contentPane.removeAll();
				try {
					aa = new AccountQueryInternalFrame(user);
					aa.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				contentPane.add(aa,BorderLayout.CENTER);
			}
		});
		btnaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountAddInternalFrame aa = null;
				contentPane.removeAll();
				try {
					aa = new AccountAddInternalFrame(user);
					aa.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				contentPane.add(aa,BorderLayout.CENTER);
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	public PrimaryFrame(User_ user) {
		this();
		this.user = user;
	}
}
