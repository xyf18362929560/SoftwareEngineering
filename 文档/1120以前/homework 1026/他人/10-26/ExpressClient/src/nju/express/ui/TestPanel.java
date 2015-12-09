package nju.express.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TestPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public TestPanel() {
		this.setSize(1280, 684);
//		setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);

		JButton btnSave = new JButton("±£´æ");
//		btnSave.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		panel_1.add(btnSave);

		JButton btnUpdate = new JButton("¸üÐÂ");
//		btnUpdate.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		panel_1.add(btnUpdate);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);

		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");

		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("New check box");

		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(chckbxNewCheckBox)
						.addComponent(chckbxNewCheckBox_1).addComponent(chckbxNewCheckBox_2)
						.addGroup(gl_panel.createSequentialGroup().addGap(101).addComponent(textField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(1108, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(91).addComponent(chckbxNewCheckBox)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(chckbxNewCheckBox_1)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxNewCheckBox_2)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(textField, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(437, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);

	}
}
