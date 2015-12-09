package nju.express.ui;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import nju.express.blservice.UserblService;
import nju.express.po.User;
import nju.express.util.LoggerUtil;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
	static Logger logger = LoggerUtil.getLogger();
	private static final long serialVersionUID = 1L;
	UserblService userblService;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public LoginPanel(UserblService userblService) {
		this.setSize(1280, 674);
		this.userblService = userblService;

		JButton button = new JButton("��¼");
		button.setBounds(468, 365, 129, 31);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login();
			}
		});
		button.setFont(new Font("΢���ź�", Font.PLAIN, 16));

		JButton button_1 = new JButton("��ѯ������Ϣ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mainFrame = MainFrame.getInstance();
				mainFrame.changeCurrentPane(mainFrame.queryOrderPanel);
			}
		});
		button_1.setBounds(607, 365, 167, 31);
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));

		JLabel label = new JLabel("\u8D26\u6237");
		label.setBounds(468, 250, 47, 22);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		setLayout(null);

		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(468, 312, 47, 22);
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1);
		add(label);
		add(button);
		add(button_1);

		textField = new JTextField();
		textField.setBounds(522, 248, 252, 30);
		add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(522, 310, 252, 30);
		add(passwordField);
		passwordField.setColumns(10);

	}

	public void login() {
		String name = this.textField.getText().trim();
		char[] passes = this.passwordField.getPassword();
		StringBuffer password = new StringBuffer();
		for (char c : passes) {
			password.append(c);
		}
		try {
			User user = userblService.login(name, password.toString());
			if (user == null) {
				MainFrame.getLoglabel().setText("��¼ʧ��");
				logger.error("��¼ʧ��");
				showWarn("��¼ʧ��");
			} else {
				// TODO ��ת

				MainFrame mainFrame = MainFrame.getInstance();
				mainFrame.setUser(user);
				// Thread t=new Thread(){
				// @Override
				// public void run() {
				// // TODO Auto-generated method stub
				// super.run();
				// }
				// };
				// t.start();

				mainFrame.initPanel();
				// t.interrupt();
				int user_job_id_fk = user.getJob_id_fk();
				switch (user_job_id_fk) {
				case 1:
					mainFrame.changeCurrentPane(mainFrame.managerPanel);
					mainFrame.managerPanel.getLabel_name().setText("����: " + user.getUser_name());
					mainFrame.managerPanel.getLabel_job().setText("ְλ: " + user.getJob().getJob_name());
					mainFrame.managerPanel.getLabel_department()
							.setText("����: " + user.getDepartment().getDepartment_name());
					MainFrame.getLoglabel()
							.setText("��½�ɹ�,����: " + user.getUser_name() + " ְλ: " + user.getJob().getJob_name());
					logger.info("��½�ɹ�,����: " + user.getUser_name() + " ְλ: " + user.getJob().getJob_name());
					break;
				case 2:
					// ���Ա
					mainFrame.changeCurrentPane(mainFrame.courierPanel);
					mainFrame.courierPanel.getLabel_name().setText("����: " + user.getUser_name());
					mainFrame.courierPanel.getLabel_job().setText("ְλ: " + user.getJob().getJob_name());
					mainFrame.courierPanel.getLabel_department()
							.setText("����: " + user.getDepartment().getDepartment_name());
					MainFrame.getLoglabel()
							.setText("��½�ɹ�,����: " + user.getUser_name() + " ְλ: " + user.getJob().getJob_name());
					logger.info("��½�ɹ�,����: " + user.getUser_name() + " ְλ: " + user.getJob().getJob_name());
					break;
				case 3:
					// Ӫҵ��ҵ��Ա
					mainFrame.changeCurrentPane(mainFrame.businessPanel);
					mainFrame.businessPanel.getLabel_name().setText("����: " + user.getUser_name());
					mainFrame.businessPanel.getLabel_job().setText("ְλ: " + user.getJob().getJob_name());
					mainFrame.businessPanel.getLabel_department()
							.setText("����: " + user.getDepartment().getDepartment_name());
					MainFrame.getLoglabel()
							.setText("��½�ɹ�,����: " + user.getUser_name() + " ְλ: " + user.getJob().getJob_name());
					logger.info("��½�ɹ�,����: " + user.getUser_name() + " ְλ: " + user.getJob().getJob_name());
					break;
				case 4:
					// ��ת����ҵ��Ա
					mainFrame.changeCurrentPane(mainFrame.centerPanel);
					mainFrame.centerPanel.getLabel_name().setText("����: " + user.getUser_name());
					mainFrame.centerPanel.getLabel_job().setText("ְλ: " + user.getJob().getJob_name());
					mainFrame.centerPanel.getLabel_department()
							.setText("����: " + user.getDepartment().getDepartment_name());
					MainFrame.getLoglabel()
							.setText("��½�ɹ�,����: " + user.getUser_name() + " ְλ: " + user.getJob().getJob_name());
					logger.info("��½�ɹ�,����: " + user.getUser_name() + " ְλ: " + user.getJob().getJob_name());
					break;
				case 5:
					// �ֿ������Ա
					break;
				case 6:
					mainFrame.changeCurrentPane(mainFrame.financePanel);
					mainFrame.financePanel.getLabel_name().setText("����: " + user.getUser_name());
					mainFrame.financePanel.getLabel_job().setText("ְλ: " + user.getJob().getJob_name());
					mainFrame.financePanel.getLabel_department()
							.setText("����: " + user.getDepartment().getDepartment_name());
					MainFrame.getLoglabel()
							.setText("��½�ɹ�,����: " + user.getUser_name() + " ְλ: " + user.getJob().getJob_name());
					logger.info("��½�ɹ�,����: " + user.getUser_name() + " ְλ: " + user.getJob().getJob_name());
					break;
				case 7:
					// ϵͳ����Ա
					break;

				}

			}

		} catch (Exception e) {
			MainFrame.getLoglabel().setText("�û�����������󣬵�¼ʧ��");
			logger.error("�û�����������󣬵�¼ʧ��");
			e.printStackTrace();
			showWarn("�û�����������󣬵�¼ʧ��");
		}
	}

	protected int showWarn(String message) {
		return JOptionPane.showConfirmDialog(this, message, "����", JOptionPane.OK_CANCEL_OPTION);
	}
}
