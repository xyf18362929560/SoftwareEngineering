package nju.express.ui.center;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.express.blservice.BItransportblservice;
import nju.express.blservice.IItransportblservice;
import nju.express.blservice.Postblservice;
import nju.express.blservice.Stockblservice;

public class CenterPanel extends JPanel {

	private static final long serialVersionUID = 3036256395652948733L;

	StockInPanel stockInPanel;
	IItransportPanel iItransportPanel;
	IBtranspostPanel iBtranspostPanel;

	private JPanel controlpanel;
	private JPanel contentpanel;
	private JLabel label_name;
	private JLabel label_job;
	private JLabel label_department;
	private JButton button_2;

	/**
	 * Create the panel.
	 */
	public CenterPanel(Postblservice postblservice, Stockblservice stockblservice,
			IItransportblservice iItransportblservice, BItransportblservice bItransportblservice) {
		this.setSize(1280, 664);

		stockInPanel = new StockInPanel(postblservice, stockblservice);
		iItransportPanel = new IItransportPanel(postblservice, iItransportblservice);
		iBtranspostPanel = new IBtranspostPanel(postblservice, bItransportblservice);

		controlpanel = new JPanel();
		controlpanel.setBounds(0, 0, 180, 664);

		contentpanel = stockInPanel;
		contentpanel.setBounds(180, 0, 1100, 664);

		add(controlpanel);
		controlpanel.setLayout(null);
		setLayout(null);

		add(contentpanel);

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

		JButton button = new JButton("\u5165\u5E93");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(stockInPanel);
			}
		});
		button.setBounds(45, 157, 90, 30);
		controlpanel.add(button);

		JButton button_1 = new JButton("发送到中转中心");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iItransportPanel.setViewDatas();
				iItransportPanel.refreshTable();
				changeCurrentPane(iItransportPanel);
			}
		});
		button_1.setBounds(34, 245, 117, 30);
		controlpanel.add(button_1);

		button_2 = new JButton("发送到营业厅");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iBtranspostPanel.setViewDatas();
				iBtranspostPanel.refreshTable();
				changeCurrentPane(iBtranspostPanel);
			}
		});
		button_2.setBounds(34, 282, 117, 30);
		controlpanel.add(button_2);

		JLabel label = new JLabel("装车:");
		label.setBounds(23, 215, 55, 18);
		controlpanel.add(label);

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
