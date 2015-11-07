package nju.express.ui.utils;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * 
 * @author ���Ƿ�
 * @time 2015��11��5������7:18:42
 * 
 */
//��дjtable
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
//table.getColumn("ȫѡ").setHeaderRenderer(check);
//table.getTableHeader().setUpdateTableInRealTime(true);
//table.getTableHeader().addMouseListener(new MouseAdapter() {
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		if (table.getColumnModel().getColumnIndexAtX(e.getX()) == 0) {// ���������ǵ�0�У���checkbox��һ��
//			JCheckBox Checkbox = (JCheckBox) check;
//			boolean b = !check.isSelected();
//			check.setSelected(b);
//			table.getTableHeader().repaint();
//			for (int i = 0; i < table.getRowCount(); i++) {
//				table.getModel().setValueAt(b, i, 0);// ����һ�ж���ɺͱ�ͷһ��
//			}
//		}
//	}
//});
//table.getTableHeader().setReorderingAllowed(false);
//
//public void initColumns() {
//	this.columns = new Vector();
//	this.columns.add("ȫѡ");
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
//				getJTable().getColumn("ȫѡ").setMaxWidth(50);
//				
//���
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
		 setSelected(b.booleanValue()); //��ʾֵ
		 }
		
		
		// ʹ��ѡ���ڵ�Ԫ���ھ�����ʾ
		setHorizontalAlignment((int) 0.5f);
		return this;
	}
}