package nju.express.ui.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import nju.express.blservice.Departmentblservice;
import nju.express.po.Department;
import nju.express.po.Job;
import nju.express.po.User;
import nju.express.ui.MainFrame;
import nju.express.ui.utils.CommonPanel;
import nju.express.ui.utils.SingleJTable;
import nju.express.util.AutoTextFieldUtil;
import nju.express.util.LoggerUtil;

public class DepartmentManagePanel extends CommonPanel {

	static Logger logger = LoggerUtil.getLogger();
	Departmentblservice departmentblservice;
	Vector columns;
	Vector<String> tips = new Vector<String>();
	private static final long serialVersionUID = -7765981555515940026L;
	private JTextField textField_departmentId = new JTextField("");
	private JTextField textField_query;
	private JTable table;
	private JTextField textField_name;
	private JTextField textField_address;
	private JComboBox comboBox;

	public DepartmentManagePanel(Departmentblservice departmentblservice) {
		this.setSize(1100, 664);
		setLayout(null);

		this.departmentblservice = departmentblservice;

		initColumns();
		setViewDatas();

		textField_query = new JTextField();
		textField_query.setBounds(455, 13, 189, 30);
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		add(textField_query);
		textField_query.setColumns(10);

		JButton button_query = new JButton("\u67E5\u8BE2");
		button_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});
		button_query.setBounds(645, 13, 90, 30);
		add(button_query);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 46, 1076, 304);
		add(scrollPane);

		DefaultTableModel model = new DefaultTableModel(getDatas(), this.columns);
		table = new SingleJTable(model);
		setJTable(table);
		setTableFace();
		// 为table添加监听
		getJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				// 当选择行时鼠标释放时才执行
				if (!event.getValueIsAdjusting()) {
					// 如果没有选中任何一行, 则返回
					if (getJTable().getSelectedRowCount() != 1)
						return;
					view();
				}
			}
		});
		scrollPane.setViewportView(table);

		JButton button_showAll = new JButton("\u663E\u793A\u6240\u6709");
		button_showAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getLoglabel().setText("显示所有");
				setViewDatas();
				refreshTable();
			}
		});
		button_showAll.setBounds(742, 13, 90, 30);
		add(button_showAll);

		JLabel label = new JLabel("部门名称：");
		label.setBounds(396, 363, 71, 18);
		add(label);

		textField_name = new JTextField();
		textField_name.setBounds(469, 357, 197, 30);
		add(textField_name);
		textField_name.setColumns(10);

		JLabel label_1 = new JLabel("部门地址：");
		label_1.setBounds(396, 409, 71, 18);
		add(label_1);

		JButton button_save = new JButton("\u4FDD\u5B58");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		button_save.setBounds(420, 534, 90, 30);
		add(button_save);

		JButton button_clear = new JButton("\u6E05\u7A7A");
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		button_clear.setBounds(520, 534, 90, 30);
		add(button_clear);

		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		button_delete.setBounds(623, 534, 90, 30);
		add(button_delete);

		textField_address = new JTextField();
		textField_address.setBounds(469, 403, 197, 30);
		add(textField_address);
		textField_address.setColumns(10);

		JLabel label_2 = new JLabel("\u90E8\u95E8\u7C7B\u578B\uFF1A");
		label_2.setBounds(396, 456, 71, 18);
		add(label_2);
		String[] types = { "营业厅", "中转中心", "总部" };
		comboBox = new JComboBox(types);
		comboBox.setBounds(469, 451, 197, 28);
		add(comboBox);

	}

	private void view() {
		int id = getSelectId(getJTable());
		Department department = departmentblservice.getById(id);
		MainFrame.getLoglabel().setText("查看部门信息");
		this.textField_departmentId.setText(id + "");
		this.textField_name.setText(department.getDepartment_name());
		this.textField_address.setText(department.getDepartment_location());
		this.comboBox.setSelectedIndex(department.getDepartment_type() - 1);
	}

	private void query() {
		String text = this.textField_query.getText().trim();
		Vector<Department> departments;
		Vector<Vector> datas;
		MainFrame.getLoglabel().setText("查询部门：" + text);
		String sql = " SELECT * from department WHERE department_name  like '%" + text + "%' ";
		Object[] values = { };
		departments = departmentblservice.getBySql(sql, values);
		datas = changeDatas(departments);
		this.datas = datas;
		refreshTable();

	}

	private void save() {
		if (this.textField_departmentId.getText().equals("")) {
			add();
		} else {
			update();
		}
	}

	private void add() {
		if (isTextExist()) {

			Department department = getDepartment();
			int result = departmentblservice.add(department);
			// 重新读取数据
			setViewDatas();
			// 刷新列表, 清空表单
			clear();
			// 通知添加结果
			if (result == 0) {
				MainFrame.getLoglabel().setText("新增部门信息失败");
				logger.error("新增部门信息失败");
			} else {
				MainFrame.getLoglabel().setText("新增部门信息成功");
				logger.info("新增部门信息成功");
			}
		}
	}

	private void update() {
		Department department = getDepartment();
		int departmentId = Integer.parseInt(this.textField_departmentId.getText());
		department.setId(departmentId);
		boolean result = departmentblservice.update(department);
		// 重新读取数据
		setViewDatas();
		// 刷新列表, 清空表单
		clear();
		// 通知更新结果
		if (result) {
			MainFrame.getLoglabel().setText("更新部门信息成功");
			logger.info("更新部门信息成功");
		} else {
			MainFrame.getLoglabel().setText("更新部门信息失败");
			logger.error("更新部门信息失败");
		}
	}

	private void delete() {
		if (!this.textField_departmentId.getText().equals("")) {
			int departmentId = Integer.parseInt(this.textField_departmentId.getText());
			boolean result = departmentblservice.delete(departmentId);
			// 重新读取数据
			setViewDatas();
			// 刷新列表, 清空表单
			clear();
			if (result) {

				MainFrame.getLoglabel().setText("刪除部门信息成功");
				logger.info("刪除部门信息成功");
			} else {
				MainFrame.getLoglabel().setText("刪除部门信息失败");
				logger.error("刪除部门信息失败");
			}
		} else {
			showWarn("未选择部门");
		}
	}

	private Department getDepartment() {
		return new Department(this.textField_name.getText(), this.textField_address.getText(),
				this.comboBox.getSelectedIndex() + 1);
	}

	private boolean isTextExist() {
		if (this.textField_name.getText().equals("")) {
			showWarn("未输入部门名称");
			return false;
		}
		if (this.textField_address.getText().equals("")) {
			showWarn("未输入部门地址");
			return false;
		}
		return true;
	}

	/**
	 * 初始化table列名
	 */
	public void initColumns() {
		this.columns = new Vector();

		this.columns.add("id");
		this.columns.add("部门名称");
		this.columns.add("部门地址");

	}

	@Override
	public Vector<String> getColumns() {
		return this.columns;
	}

	@Override
	public void setTableFace() {
		getJTable().getColumn("id").setMinWidth(-1);
		getJTable().getColumn("id").setMaxWidth(-1);
		getJTable().setRowHeight(30);
	}

	@Override
	public void setViewDatas() {
		Vector<Department> departments = departmentblservice.getAll();
		// 转换显示格式
		Vector<Vector> datas = changeDatas(departments);
		// 调用父类方法设置结果集合
		setDatas(datas);
	}

	private Vector<Vector> changeDatas(Vector<Department> departments) {
		Vector<Vector> view = new Vector<Vector>();
		for (Department department : departments) {
			Vector v = new Vector();
			v.add(department.getId());
			v.add(department.getDepartment_name());
			v.add(department.getDepartment_location());

			view.add(v);
		}
		return view;
	}

	@Override
	public void clear() {
		MainFrame.getLoglabel().setText("清空");
		refreshTable();
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		this.textField_departmentId.setText("");
		this.textField_name.setText("");
		this.textField_address.setText("");

	}

	/**
	 * 获得所有的tips提示，自动补全查询文本框
	 * 
	 * @return
	 */
	private Vector<String> getAllTips() {
		Vector<Department> departments = departmentblservice.getAll();
		tips.clear();
		for (Department department : departments) {
			tips.add(department.getDepartment_name());

		}
		return tips;
	}
}
