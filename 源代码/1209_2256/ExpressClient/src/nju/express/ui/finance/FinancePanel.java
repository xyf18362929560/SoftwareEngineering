package nju.express.ui.finance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.express.blservice.Accountblservice;
import nju.express.blservice.Paymentblservice;
import nju.express.blservice.Postblservice;

public class FinancePanel extends JPanel {

	private static final long serialVersionUID = 6923231270876654967L;
	private JPanel controlpanel;
	private JPanel contentpanel;

	PaymentPanel paymentPanel;
	FinanceInquirePanel financeInquirePanel;
	AccountPanel accountPanel;

	private JLabel label_name;
	private JLabel label_job;
	private JLabel label_department;
	private JButton button;
	private JButton button_1;
	private JButton button_3;

	/**
	 * Create the panel.
	 */
	public FinancePanel(Paymentblservice paymentblservice,Postblservice postblservice,Accountblservice accountblservice) {
		this.setSize(1280, 664);

		paymentPanel = new PaymentPanel(paymentblservice);
		financeInquirePanel = new FinanceInquirePanel(paymentblservice,postblservice);
		accountPanel = new AccountPanel(accountblservice);

		controlpanel = new JPanel();
		controlpanel.setBounds(0, 0, 180, 664);

		contentpanel = paymentPanel;
		contentpanel.setBounds(180, 0, 1100, 664);
		setLayout(null);

		add(controlpanel);
		controlpanel.setLayout(null);
		
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

		button = new JButton("\u4ED8\u6B3E\u5355");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(paymentPanel);
			}
		});
		button.setBounds(36, 159, 113, 30);
		controlpanel.add(button);

		button_1 = new JButton("\u67E5\u8BE2\u7ECF\u8425\u60C5\u51B5");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(financeInquirePanel);
			}
		});
		button_1.setBounds(36, 208, 113, 30);
		controlpanel.add(button_1);

		button_3 = new JButton("\u8D26\u6237\u7BA1\u7406");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(accountPanel);
			}
		});
		button_3.setBounds(36, 259, 113, 30);
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
