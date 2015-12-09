package nju.express.ui.utils;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

/**
 * �б�Ļ���
 * 
 *
 */
public class SingleJTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8623729877362242499L;

	public SingleJTable(TableModel dm) {
		super(dm);
		// ���ñ��ֻ��ѡ��һ��
		getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	// ��д����ķ���, ʹ���еĵ�Ԫ�񲻿ɱ༭
	public boolean isCellEditable(int row, int column) {
		if (column == 0) {
			return true;
		} else {
			return false;
		}
	}
}
