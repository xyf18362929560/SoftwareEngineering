package nju.express.ui.courier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import nju.express.blservice.Postblservice;
import nju.express.blservice.Recipientblservice;

import javax.swing.JLabel;

public class CourierPanel extends JPanel {

	private static final long serialVersionUID = 3083436208220174728L;
	PostPanel postPanel;
	RecipientPanel recipientPanel;
	private JPanel controlpanel;
	private JPanel contentpanel;
	private JLabel label_name;
	private JLabel label_job;
	private JLabel label_department;

	/**
	 * Create the panel.
	 */
	/**
	 * 目前因为只写了寄件单界面，只传入了postblservice，后面需要加入其他的blservice
	 * 
	 * @param postblservice
	 */
	public CourierPanel(Postblservice postblservice, Recipientblservice recipientblservice) {
		this.setSize(1280, 664);
		postPanel = new PostPanel(postblservice);

		// TODO 加入业务逻辑服务
		recipientPanel = new RecipientPanel(postblservice, recipientblservice);

		controlpanel = new JPanel();
		controlpanel.setBounds(0, 0, 180, 664);

		contentpanel = postPanel;
		contentpanel.setBounds(180, 0, 1100, 664);
		setLayout(null);

		add(controlpanel);
		controlpanel.setLayout(null);

		add(contentpanel);

		JButton button = new JButton("\u5BC4\u4EF6\u5355");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(postPanel);
			}
		});
		button.setBounds(43, 193, 90, 30);
		controlpanel.add(button);

		JButton button_1 = new JButton("\u6536\u4EF6\u5355");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(recipientPanel);
			}
		});
		button_1.setBounds(43, 246, 90, 30);
		controlpanel.add(button_1);

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
