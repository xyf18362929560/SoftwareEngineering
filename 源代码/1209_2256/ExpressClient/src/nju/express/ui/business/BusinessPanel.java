package nju.express.ui.business;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import nju.express.blservice.BItransportblservice;
import nju.express.blservice.Deliveryblservice;
import nju.express.blservice.Driverblservice;
import nju.express.blservice.Postblservice;
import nju.express.blservice.Vehicleblservice;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 这是营业厅界面，其tabpanel包含有 车辆司机管理界面， 收款单界面，装车管理界面
 * 
 * @author 徐亚帆
 * @time 2015年11月3日下午4:46:42
 */
public class BusinessPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2122594159818817163L;
	BItransportPanel bItransportPanel;

	BusinessCollectionPanel businessCollectionPanel;

	BusinessDeliveryPanel businessDeliveryPanel;

	BusinessDriverPanel businessDriverPanel;

	BusinessVehiclePanel businessVehiclePanel;
	private JPanel controlpanel;
	private JPanel contentpanel;

	private JLabel label_name;
	private JLabel label_job;
	private JLabel label_department;

	/**
	 * Create the panel.
	 */
	public BusinessPanel(Vehicleblservice vehicleblservice, Driverblservice driverblservice,
			BItransportblservice bItransportblservice, Postblservice postblservice,Deliveryblservice deliveryblservice) {
		this.setSize(1280, 664);

		

		bItransportPanel = new BItransportPanel(bItransportblservice, postblservice);

		businessCollectionPanel = new BusinessCollectionPanel();

		businessDeliveryPanel = new BusinessDeliveryPanel(deliveryblservice,postblservice);

		businessDriverPanel = new BusinessDriverPanel(driverblservice);

		businessVehiclePanel = new BusinessVehiclePanel(vehicleblservice);

		controlpanel = new JPanel();
		controlpanel.setBounds(0, 0, 180, 664);
		contentpanel = bItransportPanel;
		setLayout(null);

		add(controlpanel);
		controlpanel.setLayout(null);
		
		contentpanel.setBounds(180, 0, 1100, 664);
		add(contentpanel);

		JButton button = new JButton("装车单");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(bItransportPanel);
			}
		});
		button.setBounds(47, 147, 90, 30);
		controlpanel.add(button);

		JButton button_1 = new JButton("收款单");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(businessCollectionPanel);
			}
		});
		button_1.setBounds(47, 189, 90, 30);
		controlpanel.add(button_1);

		JButton button_2 = new JButton("派件单");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(businessDeliveryPanel);
			}
		});
		button_2.setBounds(47, 231, 90, 30);
		controlpanel.add(button_2);

		JButton button_3 = new JButton("司机信息");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(businessDriverPanel);
			}
		});
		button_3.setBounds(47, 278, 90, 30);
		controlpanel.add(button_3);

		JButton button_4 = new JButton("车辆信息");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(businessVehiclePanel);
			}
		});
		button_4.setBounds(47, 320, 90, 30);

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

		controlpanel.add(button_4);
		

	}

	public void changeCurrentPane(JPanel jPanel) {

		this.remove(contentpanel);

		// 设置当前的JPanel
		this.contentpanel = jPanel;
		add(contentpanel);
		contentpanel.setBounds(180, 0, 1100, 664);
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
