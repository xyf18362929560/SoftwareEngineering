package nju.express.ui.courier;

import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import nju.express.ui.utils.CommonPanel;
import nju.express.util.DateTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JTextField;

/**�ý���Ϊ�ռ������棬��Ҫ���Ա����ǩ���˵�������ǩ��ʱ��
 * ���ݲ���������Ա��������Ա�Ķ�����ѡ������
 * @author ���Ƿ�
 * @time 2015��11��3������4:02:47
 */
public class RecipientPanel extends CommonPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4943430054548454122L;
	Vector columns;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public RecipientPanel() {
		this.setSize(1203, 674);
		
		JLabel label = new JLabel("\u67E5\u8BE2\u65B9\u5F0F");
		
		JComboBox comboBox = new JComboBox(new Object[]{});
		
		DateTextField dateTextField = new DateTextField("yyyy-MM-dd");
		dateTextField.setColumns(10);
		
		JButton button = new JButton("\u9009\u62E9\u65E5\u671F");
		
		JButton button_1 = new JButton("\u67E5\u8BE2");
		
		JButton button_2 = new JButton("\u663E\u793A\u6240\u6709");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label_1 = new JLabel("\u7B7E\u6536\u4EBA\u59D3\u540D");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7B7E\u6536\u65F6\u95F4");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton button_3 = new JButton("\u9009\u62E9\u65F6\u95F4");
		
		JButton button_4 = new JButton("\u4FDD\u5B58");
		
		JButton button_5 = new JButton("\u6E05\u7A7A");
		
		JButton button_6 = new JButton("\u5220\u9664");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(208)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dateTextField, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addGap(144))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1191, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(396)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1)
								.addComponent(label_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
									.addGap(454))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_3)
									.addGap(457))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(371)
							.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(81)
							.addComponent(button_5, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
							.addGap(71)
							.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addGap(421)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(button_3)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(button_4)
						.addComponent(button_5)
						.addComponent(button_6))
					.addContainerGap(276, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}

	/**
	 * ��ʼ��table����
	 */
	public void initColumns() {
	//TODO
		this.columns = new Vector();
//		this.columns.add("id");
//		this.columns.add("�����������");
//		this.columns.add("�ļ���");
//		this.columns.add("�ļ��˵�ַ");
//		this.columns.add("�ռ���");
//		this.columns.add("�ռ��˵�ַ");
//		this.columns.add("�������");
//		this.columns.add("����ʱ��");

	}

	@Override
	public Vector<String> getColumns() {
		
		return this.columns;
	}

	@Override
	public void setTableFace() {
		//TODO
//		getJTable().getColumn("id").setMinWidth(-1);
//		getJTable().getColumn("id").setMaxWidth(-1);
//		getJTable().setRowHeight(30);
	}

	@Override
	public void setViewDatas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		refreshTable();
//TODO
	}
}
