package nju.express.ui.utils;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * 
 * @author 徐亚帆
 * @time 2015年11月5日下午7:18:42
 * 
 */
//复写jtable
//DefaultTableModel model = new DefaultTableModel(getDatas(), this.columns) {
//	@Override
//	public Class<?> getColumnClass(int columnIndex) {
//		Object value = getValueAt(0, columnIndex);
//		if (value != null)
//			return value.getClass();
//		else
//			return super.getClass();
//	}
//};
//JTable table = new CommonJTable(model);
//final MyCheckBoxRenderer check = new MyCheckBoxRenderer();
//table.getColumn("全选").setHeaderRenderer(check);
//table.getTableHeader().setUpdateTableInRealTime(true);
//table.getTableHeader().addMouseListener(new MouseAdapter() {
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		if (table.getColumnModel().getColumnIndexAtX(e.getX()) == 0) {// 如果点击的是第0列，即checkbox这一列
//			JCheckBox Checkbox = (JCheckBox) check;
//			boolean b = !check.isSelected();
//			check.setSelected(b);
//			table.getTableHeader().repaint();
//			for (int i = 0; i < table.getRowCount(); i++) {
//				table.getModel().setValueAt(b, i, 0);// 把这一列都设成和表头一样
//			}
//		}
//	}
//});
//table.getTableHeader().setReorderingAllowed(false);
//
//public void initColumns() {
//	this.columns = new Vector();
//	this.columns.add("全选");
//	
//	private Vector<Vector> changeDatas(Vector<Post> posts) {
//		Vector<Vector> view = new Vector<Vector>();
//		for (Post post : posts) {
//			Vector v = new Vector();
//			v.add(new Boolean(false));
//			v.add(post.getId());
//
//			@Override
//			public void setTableFace() {
//				getJTable().getColumn("全选").setMaxWidth(50);
//				
//获得
//for (int i = 0; i < getJTable().getRowCount(); i++) {
//	System.out.println(getJTable().getModel().getValueAt(i, 0));
//}
//getJTable().getTableHeader().repaint();

public class MyCheckBoxRenderer extends JCheckBox implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7699018350685997003L;

	public MyCheckBoxRenderer() {
		this.setBorderPainted(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		 if (value instanceof Boolean)
		 {
		 Boolean b = (Boolean) value;
		 setSelected(b.booleanValue()); //显示值
		 }
		
		
		// 使复选框在单元格内居中显示
		setHorizontalAlignment((int) 0.5f);
		return this;
	}
}