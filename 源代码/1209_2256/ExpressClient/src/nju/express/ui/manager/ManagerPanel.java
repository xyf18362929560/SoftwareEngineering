package nju.express.ui.manager;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.express.blservice.Constantblservice;
import nju.express.blservice.Departmentblservice;
import nju.express.blservice.UserblService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerPanel extends JPanel {

	
	private static final long serialVersionUID = -1189347673894279651L;
	private JPanel controlpanel;
	private JPanel contentpanel;
	
	UserManagerPanel userManagerPanel;
	DepartmentManagePanel departmentManagePanel;
	MakeConstantPanel makeConstantPanel;
	DocumentApprovalPanel documentApprovalPanel;
	
	private JLabel label_name;
	private JLabel label_job;
	private JLabel label_department;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	public ManagerPanel(UserblService userblService,Departmentblservice departmentblservice,Constantblservice constantblservice) {
		this.setSize(1280, 664);
		
		userManagerPanel=new UserManagerPanel(userblService);
		departmentManagePanel=new DepartmentManagePanel( departmentblservice);
		makeConstantPanel=new MakeConstantPanel(constantblservice);
		documentApprovalPanel = new DocumentApprovalPanel();
		
		controlpanel = new JPanel();
		controlpanel.setBounds(0, 0, 180, 664);
		

		contentpanel = documentApprovalPanel;
		contentpanel.setBounds(180, 0, 1100, 664);
		add(contentpanel);
		
		setLayout(null);

		add(controlpanel);
		controlpanel.setLayout(null);

		// 用户信息
		label_name = new JLabel("姓名:");
		label_name.setBounds(23, 28, 157, 18);
		controlpanel.add(label_name);

		label_job = new JLabel("职位:");
		label_job.setBounds(23, 67, 157, 18);
		controlpanel.add(label_job);

		label_department = new JLabel("部门:");
		label_department.setBounds(23, 113, 157, 18);
		controlpanel.add(label_department);
		
		button = new JButton("\u5BA1\u6279\u5355\u636E");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(documentApprovalPanel);
			}
		});
		button.setBounds(51, 183, 90, 30);
		controlpanel.add(button);
		
		button_1 = new JButton("\u4EBA\u5458\u7BA1\u7406");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(userManagerPanel);
			}
		});
		button_1.setBounds(51, 242, 90, 30);
		controlpanel.add(button_1);
		
		button_2 = new JButton("\u673A\u6784\u7BA1\u7406");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(departmentManagePanel);
			}
		});
		button_2.setBounds(51, 299, 90, 30);
		controlpanel.add(button_2);
		
		button_3 = new JButton("\u5236\u5B9A\u5E38\u91CF");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(makeConstantPanel);
			}
		});
		button_3.setBounds(51, 358, 90, 30);
		controlpanel.add(button_3);
	}
	
	public void changeCurrentPane(JPanel jPanel) {

		this.remove(contentpanel);
		// 添加需要显示的JPanel

		// 设置当前的JPanel
		this.contentpanel = jPanel;

		contentpanel.setBounds(180, 0, 1100, 664);
		add(contentpanel);
		this.repaint();

		this.setVisible(true);
	}
	
	public JLabel getLabel_name() {
		return label_name;
	}
	public void setLabel_name(JLabel label_name) {
		this.label_name = label_name;
	}
	public JLabel getLabel_job() {
		return label_job;
	}
	public void setLabel_job(JLabel label_job) {
		this.label_job = label_job;
	}
	public JLabel getLabel_department() {
		return label_department;
	}
	public void setLabel_department(JLabel label_department) {
		this.label_department = label_department;
	}	

}
