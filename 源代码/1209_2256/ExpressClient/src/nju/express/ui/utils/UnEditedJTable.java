package nju.express.ui.utils;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class UnEditedJTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3167120241052630902L;

	public UnEditedJTable(TableModel dm) {
		super(dm);

	}

	// 重写父类的方法, 使所有的单元格不可编辑
	public boolean isCellEditable(int row, int column) {
		if (column == 0) {
			return true;
		} else {
			return false;
		}
	}
}
