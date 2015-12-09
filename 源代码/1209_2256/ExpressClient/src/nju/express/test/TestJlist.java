package nju.express.test;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TestJlist extends JFrame {
	public TestJlist() {
		init();

		this.setTitle("JList例子");
		this.setSize(new Dimension(200, 450));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void init() {
		final JList list = new JList();
		DefaultListModel defaultListModel = new DefaultListModel();

		defaultListModel.addElement("黄飞鸿");
		defaultListModel.addElement("霍元甲");
		defaultListModel.addElement("姚家兴");
		defaultListModel.addElement("陈真");
		defaultListModel.addElement("叶问");
		defaultListModel.addElement("李小龙");
		defaultListModel.addElement("方世玉");

		list.setModel(defaultListModel);

		// 单选
		list.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		// 加入选择事件
		list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					int index = list.getSelectedIndex();
					System.out.println(index);
					String selectedItem = list.getSelectedValue().toString();
					System.out.println("选中值:" + selectedItem);

					// 通过JList，有单选或多选的两个情况
					//
					// int getSelectedIndex()
					// Returns the smallest selected cell index; the selection
					// when only
					// a single item is selected in the list.
					// int[] getSelectedIndices()
					// Returns an array of all of the selected indices, in
					// increasing
					// order.
					// Object getSelectedValue()
					// Returns the value for the smallest selected cell index;
					// the selected value when only a single item is selected in
					// the
					// list.
					// Object[] getSelectedValues()
					// Returns an array of all the selected values, in
					// increasing order based on their indices in the list.
				}

			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		this.add(scrollPane);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestJlist();

	}

}