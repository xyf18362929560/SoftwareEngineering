package nju.express.ui;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import nju.express.blservice.UserblService;
import nju.express.po.User;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	UserblService userblService;

	/**
	 * Create the panel.
	 */
	public LoginPanel(UserblService userblService) {
		this.setSize(1280, 674);
		this.userblService = userblService;

		textField = new JTextField();
		textField.setColumns(10);

		passwordField = new JPasswordField();

		JButton button = new JButton("登录");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login();
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 16));

		JButton button_1 = new JButton("免登陆查询订单");
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));

		JLabel label = new JLabel("\u8D26\u6237");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));

		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addGap(555).addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED).addGroup(
										groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 240,
														Short.MAX_VALUE)
												.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														240, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup()
										.addComponent(button, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(button_1,
												GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)))
						.addGap(419)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(249)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label).addComponent(
						textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(39)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
				.addGap(31)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(button_1).addComponent(button))
				.addGap(262)));
		setLayout(groupLayout);

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
				System.out.println("登录失败");

			} else {
				// TODO 跳转

				this.setVisible(false);
				MainFrame mainFrame = MainFrame.getInstance();
				int user_job_id_fk = user.getJob_id_fk();
				switch (user_job_id_fk) {
				case 1:
					//总经理
					break;
				case 2:
					//快递员
					mainFrame.changeCurrentPane(mainFrame.courierPanel);
					mainFrame.setUser(user);
					mainFrame.setJob(user.getJob());
					mainFrame.setDepartment(user.getDepartment());
					break;
				case 3:
					//营业厅业务员
					mainFrame.changeCurrentPane(mainFrame.businessPanel);
					mainFrame.setUser(user);
					mainFrame.setJob(user.getJob());
					mainFrame.setDepartment(user.getDepartment());
					break;
				case 4:
					//中转中心业务员
					break;
				case 5:
					//仓库管理人员
					break;
				case 6:
					//财务人员
					break;
				case 7:
					//系统管理员
					break;

				default:
					break;
				}

				
			}

		} catch (Exception e) {

			showWarn(e.getMessage());
		}
	}

	protected int showWarn(String message) {
		return JOptionPane.showConfirmDialog(this, message, "警告", JOptionPane.OK_CANCEL_OPTION);
	}

}
