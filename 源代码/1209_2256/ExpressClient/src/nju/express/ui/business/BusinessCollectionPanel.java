package nju.express.ui.business;

import java.util.Vector;

import nju.express.ui.utils.CommonPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class BusinessCollectionPanel extends CommonPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1578244333552578613L;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public BusinessCollectionPanel() {
		this.setSize(1100, 664);

		JLabel label = new JLabel("\u67E5\u8BE2\u6536\u6B3E\u5355");
		label.setBounds(332, 12, 60, 18);

		textField = new JTextField();
		textField.setBounds(404, 6, 188, 30);
		textField.setColumns(10);

		JButton button = new JButton("\u67E5\u8BE2");
		button.setBounds(598, 6, 98, 30);

		JButton button_1 = new JButton("\u663E\u793A\u6240\u6709");
		button_1.setBounds(708, 6, 102, 30);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 48, 1078, 289);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 349, 1078, 10);

		JLabel label_1 = new JLabel("\u6536\u6B3E\u65F6\u95F4");
		label_1.setBounds(436, 377, 48, 18);

		textField_1 = new JTextField();
		textField_1.setBounds(496, 371, 168, 30);
		textField_1.setColumns(10);

		JButton button_2 = new JButton("\u9009\u62E9\u65F6\u95F4");
		button_2.setBounds(670, 371, 76, 30);

		JLabel label_2 = new JLabel("\u6536\u6B3E\u91D1\u989D");
		label_2.setBounds(436, 419, 48, 18);

		textField_2 = new JTextField();
		textField_2.setBounds(496, 413, 168, 30);
		textField_2.setColumns(10);

		JLabel label_3 = new JLabel("\u6536\u6B3E\u4EBA");
		label_3.setBounds(436, 460, 48, 18);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(496, 455, 168, 28);

		JButton button_3 = new JButton("\u4FDD\u5B58");
		button_3.setBounds(515, 548, 102, 30);

		JLabel label_4 = new JLabel("\u6536\u6B3E\u8425\u4E1A\u5385");
		label_4.setBounds(424, 500, 60, 18);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(496, 495, 168, 28);

		table = new JTable();
		scrollPane.setViewportView(table);
		setLayout(null);
		add(label);
		add(textField);
		add(button);
		add(button_1);
		add(scrollPane);
		add(separator);
		add(button_3);
		add(label_4);
		add(label_3);
		add(label_2);
		add(label_1);
		add(comboBox_1);
		add(comboBox);
		add(textField_2);
		add(textField_1);
		add(button_2);

	}

	@Override
	public Vector<String> getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTableFace() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setViewDatas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}
}
