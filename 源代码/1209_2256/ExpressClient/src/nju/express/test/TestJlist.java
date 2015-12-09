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

		this.setTitle("JList����");
		this.setSize(new Dimension(200, 450));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void init() {
		final JList list = new JList();
		DefaultListModel defaultListModel = new DefaultListModel();

		defaultListModel.addElement("�Ʒɺ�");
		defaultListModel.addElement("��Ԫ��");
		defaultListModel.addElement("Ҧ����");
		defaultListModel.addElement("����");
		defaultListModel.addElement("Ҷ��");
		defaultListModel.addElement("��С��");
		defaultListModel.addElement("������");

		list.setModel(defaultListModel);

		// ��ѡ
		list.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		// ����ѡ���¼�
		list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					int index = list.getSelectedIndex();
					System.out.println(index);
					String selectedItem = list.getSelectedValue().toString();
					System.out.println("ѡ��ֵ:" + selectedItem);

					// ͨ��JList���е�ѡ���ѡ���������
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