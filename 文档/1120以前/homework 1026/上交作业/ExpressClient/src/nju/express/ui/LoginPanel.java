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
import nju.express.vo.User;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	UserblService userblService;

	/**
	 * Create the panel.
	 */
	public LoginPanel(UserblService userblService) {
		this.setSize(1280, 684);
		this.userblService = userblService;
		JLabel label = new JLabel("ÕË»§");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		label.setHorizontalAlignment(SwingConstants.CENTER);

		textField = new JTextField();
		textField.setColumns(10);

		JLabel label_1 = new JLabel("ÃÜÂë");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));

		passwordField = new JPasswordField();

		JButton button = new JButton("µÇÂ¼");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login();
			}
		});
		button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));

		JButton button_1 = new JButton("ÃâµÇÂ½²éÑ¯¶©µ¥");
		button_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(458)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup().addComponent(button)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(button_1,
												GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(label_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(passwordField)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))))
				.addContainerGap(547, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(259)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(label, GroupLayout.PREFERRED_SIZE, 29,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(27)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label_1).addComponent(
						passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(31)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(button).addComponent(button_1))
				.addContainerGap(284, Short.MAX_VALUE)));
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
				System.out.println("µÇÂ¼Ê§°Ü");

			} else {
				System.out.println(user.getJob_id_fk());
				this.setVisible(false);
				MainFrame mainFrame=(MainFrame) this.getRootPane().getParent();
				mainFrame.changeCurrentPane(new CourierPanel());
			}

		} catch (Exception e) {

			showWarn(e.getMessage());
		}
	}

	protected int showWarn(String message) {
		return JOptionPane.showConfirmDialog(this, message, "¾¯¸æ", JOptionPane.OK_CANCEL_OPTION);
	}

}
