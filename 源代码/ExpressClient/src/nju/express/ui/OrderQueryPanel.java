package nju.express.ui;

import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import nju.express.ui.utils.CommonPanel;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**���Ƕ�����ѯ���棬���Ը��ݼļ�������������ʱ�䣬�����Ų�ѯ�����еĶ���
 * @author ���Ƿ�
 * @time 2015��11��3������4:43:29
 */
public class OrderQueryPanel extends CommonPanel {

	private static final long serialVersionUID = 6482623137009054386L;
	private JTextField textField;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public OrderQueryPanel() {
		this.setSize(1280, 674);

		JLabel label = new JLabel("��ѯ����");

		textField = new JTextField();
		textField.setColumns(10);

		JButton button = new JButton("\u67E5\u8BE2");

		JScrollPane scrollPane = new JScrollPane();
		
		JComboBox comboBox = new JComboBox();
		
		JSeparator separator = new JSeparator();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton button_1 = new JButton("\u786E\u5B9A");
		
		JButton btnNewButton = new JButton("ѡ��ʱ��");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(309)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(581)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(btnNewButton)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_1)
					.addContainerGap(10, Short.MAX_VALUE))
		);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
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
