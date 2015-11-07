package nju.express.ui.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.qt.datapicker.DatePicker;

import nju.express.blservice.Vehicleblservice;
import nju.express.po.Department;
import nju.express.po.Vehicle;
import nju.express.ui.utils.CommonJTable;
import nju.express.ui.utils.CommonPanel;
import nju.express.util.AutoTextFieldUtil;
import nju.express.util.DateTextField;
import nju.express.util.DateUtil;

public class BusinessVehiclePanel extends CommonPanel {
	Vehicleblservice vehicleblservice;
	// Jtable的列名
	Vector columns;
	Vector<String> tips = new Vector<String>();
	private static final long serialVersionUID = 2203423466356594897L;

	private JTextField textField_vehicleId = new JTextField("");
	private JTextField textField_query;

	private JTextField textField_vehicleNum;
	private JTextField textField_license;
	private DateTextField textField_usetime;
	private JTextField textField_vehicleinfo;
	private JComboBox comboBox_query_choosebusiness;
	private JComboBox comboBox_showBusiness;

	/**
	 * Create the panel.
	 */
	public BusinessVehiclePanel(Vehicleblservice vehicleblservice) {
		this.setSize(1203, 674);
		this.vehicleblservice = vehicleblservice;

		initColumns();
		setViewDatas();

		DefaultTableModel model = new DefaultTableModel(getDatas(), this.columns);
		JTable table = new CommonJTable(model);
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

		JLabel label = new JLabel("\u9009\u62E9\u8425\u4E1A\u5385");

		comboBox_query_choosebusiness = new JComboBox();
		comboBox_showBusiness = new JComboBox();
		addDepartment();
		JLabel label_1 = new JLabel("查询车辆编号");

		textField_query = new JTextField();

		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		textField_query.setColumns(10);

		JButton button_query = new JButton("查询");
		button_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JSeparator separator = new JSeparator();

		JLabel label_2 = new JLabel("\u8F66\u8F86\u6240\u5C5E\u7684\u8425\u4E1A\u5385");

		JLabel label_3 = new JLabel("\u8F66\u8F86\u4EE3\u53F7");

		textField_vehicleNum = new JTextField();
		textField_vehicleNum.setColumns(10);

		JLabel label_4 = new JLabel("\u8F66\u724C\u53F7");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);

		textField_license = new JTextField();
		textField_license.setColumns(10);

		JLabel label_5 = new JLabel("\u8F66\u8F86\u5F00\u59CB\u670D\u5F79\u65F6\u95F4");

		textField_usetime = new DateTextField("yyyy-MM-dd");
		textField_usetime.setColumns(10);

		JButton button_usetime = new JButton("\u9009\u62E9\u65F6\u95F4");
		button_usetime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatePicker dp = new DatePicker(textField_usetime, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_usetime.getText());

				dp.setSelectedDate(selectedDate);
				dp.start(textField_usetime);
			}
		});

		JLabel label_6 = new JLabel("\u8F66\u8F86\u4FE1\u606F");

		textField_vehicleinfo = new JTextField();
		textField_vehicleinfo.setColumns(10);

		JButton button_save = new JButton("\u4FDD\u5B58");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		JButton button_clear = new JButton("\u6E05\u7A7A");
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});

		JButton button_showAll = new JButton("\u663E\u793A\u6240\u6709");
		button_showAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setViewDatas();
				refreshTable();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
						.createParallelGroup(
								Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(72)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(comboBox_query_choosebusiness, GroupLayout.PREFERRED_SIZE, 232,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(textField_query, GroupLayout.PREFERRED_SIZE, 295,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(button_query, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(button_showAll,
										GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(scrollPane,
								GroupLayout.DEFAULT_SIZE, 1191, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(separator,
								GroupLayout.DEFAULT_SIZE, 1191, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(label_2)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(comboBox_showBusiness,
										GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup().addComponent(label_6)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(textField_vehicleinfo))
								.addGroup(groupLayout.createSequentialGroup().addComponent(label_3)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textField_vehicleNum, GroupLayout.PREFERRED_SIZE, 174,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 87,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textField_license, GroupLayout.PREFERRED_SIZE, 146,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(label_5).addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textField_usetime, GroupLayout.PREFERRED_SIZE, 137,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(button_usetime)))))
				.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(436, Short.MAX_VALUE)
						.addComponent(button_save, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addGap(54)
						.addComponent(button_clear, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addGap(72)
						.addComponent(button_delete, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
						.addGap(388)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(6).addComponent(label))
								.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(
										comboBox_query_choosebusiness, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(6).addComponent(label_1))
						.addComponent(textField_query, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(button_query)
								.addComponent(button_showAll)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label_2)
								.addComponent(comboBox_showBusiness, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label_3)
						.addComponent(textField_vehicleNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4)
						.addComponent(textField_license, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5)
						.addComponent(textField_usetime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(button_usetime))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(label_6).addComponent(
						textField_vehicleinfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)).addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(button_delete)
						.addComponent(button_clear).addComponent(button_save)).addContainerGap(135, Short.MAX_VALUE)));

		scrollPane.setViewportView(table);
		setLayout(groupLayout);

	}

	private void view() {
		int id = getSelectId(getJTable());
		Vehicle vehicle = vehicleblservice.getById(id);

		this.textField_vehicleId.setText(id + "");
		this.comboBox_showBusiness.setSelectedItem(makeDepartment(vehicle.getDepartment()));
		this.textField_vehicleNum.setText(vehicle.getVehicle_num());
		this.textField_license.setText(vehicle.getLicense_plate_number());
		this.textField_usetime.setText(DateUtil.getStringByDate(vehicle.getUsetime()));
		this.textField_vehicleinfo.setText(vehicle.getVehicle_info());
	}

	private void query() {
		String text = this.textField_query.getText().trim();
		Vector<Vehicle> vehicles;
		Vector<Vector> datas;
		String departmentName = this.comboBox_query_choosebusiness.getSelectedItem().toString();
		String sql = "";
		if (departmentName.equals("所有")) {
			sql = "select * from vehicle where vehicle_num = '" + text + "' or license_plate_number = '" + text + "' ";

			vehicles = vehicleblservice.getBySql(sql);
			datas = changeDatas(vehicles);
			setDatas(datas);
			refreshTable();
		} else {
			sql = "select * from vehicle , department  where (vehicle.department_id_fk=department.id ) " + " and ("
					+ "department.department_name = '" + departmentName + "' )" + "and (vehicle_num = '" + text
					+ "' or license_plate_number = '" + text + "' ) ";
			vehicles = vehicleblservice.getBySql(sql);
			datas = changeDatas(vehicles);
			setDatas(datas);
			refreshTable();
		}

	}

	private void save() {
		if (this.textField_vehicleId.getText().equals("")) {
			add();
		} else {
			update();
		}
	}

	private void add() {
		if (isTextExist() && isTextValidate()) {
			System.out.println("add");
			Vehicle vehicle = getVehicle();
			vehicleblservice.add(vehicle);
			// 重新读取数据
			setViewDatas();
			// 刷新列表, 清空表单

			clear();
			System.out.println("add success");
		}
	}

	private void update() {
		System.out.println("update");
		Vehicle vehicle = getVehicle();
		
		int vehicleId = Integer.parseInt(this.textField_vehicleId.getText());
		vehicle.setId(vehicleId);
		
//		Department department = (Department) this.comboBox_showBusiness.getSelectedItem();
//		System.out.println(department);
//		vehicle.setDepartment(department);
//		vehicle.setDepartment_id_fk(department.getId());
		//说明：这是关联关系而不是包含关系，所以不需要

		vehicleblservice.update(vehicle);
		// 重新读取数据
		setViewDatas();
		// 刷新列表, 清空表单

		clear();
		System.out.println("update success");

	}

	private void delete() {
		if (!this.textField_vehicleId.getText().equals("")) {
			int vehicleId = Integer.parseInt(this.textField_vehicleId.getText());
			vehicleblservice.delete(vehicleId);
			// 重新读取数据
			setViewDatas();
			// 刷新列表, 清空表单

			clear();
		} else {
			showWarn("未选择寄件单");
		}
	}

	private boolean isTextExist() {

		if (this.textField_vehicleNum.getText().equals("")) {
			showWarn("未输入车辆编号");
			return false;
		}
		if (this.textField_license.getText().equals("")) {
			showWarn("未输入车牌号");
			return false;
		}
		if (this.textField_usetime.getText().equals("")) {
			showWarn("未输入车辆开始服役时间");
			return false;
		}
		return true;
	}

	private boolean isTextValidate() {
		if (!isNumber(this.textField_vehicleNum.getText())) {
			showWarn("车辆编号格式不正确");
			return false;
		}
		if (this.textField_vehicleNum.getText().length() != 9) {
			showWarn("车辆编号长度不正确");
			return false;
		}
		return true;
	}

	/*
	 * 检查字符串是否是数字
	 * 
	 * @param string
	 * 
	 * @return
	 */
	private boolean isNumber(String string) {

		try {
			Double.parseDouble(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private Vehicle getVehicle() {
		Department department = (Department) this.comboBox_showBusiness.getSelectedItem();
		return new Vehicle(department.getId(), this.textField_vehicleNum.getText(), this.textField_license.getText(),
				new java.sql.Date(DateUtil.getDateByDateString(this.textField_usetime.getText()).getTime()),
				this.textField_vehicleinfo.getText());
	}

	/**
	 * 初始化table列名
	 */
	public void initColumns() {
		this.columns = new Vector();

		this.columns.add("id");
		this.columns.add("车辆编号");
		this.columns.add("车牌号");
		this.columns.add("使用时间");
		this.columns.add("所属营业厅");

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
		Vector<Vehicle> vehicles = vehicleblservice.getAll();
		// 转换显示格式
		Vector<Vector> datas = changeDatas(vehicles);
		// 调用父类方法设置结果集合
		setDatas(datas);
	}

	private Vector<Vector> changeDatas(Vector<Vehicle> vehicles) {
		Vector<Vector> view = new Vector<Vector>();
		for (Vehicle vehicle : vehicles) {
			Vector v = new Vector();
			v.add(vehicle.getId());
			v.add(vehicle.getVehicle_num());
			v.add(vehicle.getLicense_plate_number());
			v.add(DateUtil.getStringByDate(vehicle.getUsetime()));
			v.add(vehicle.getDepartment().getDepartment_name());
			view.add(v);
		}
		return view;
	}

	@Override
	public void clear() {
		refreshTable();
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		this.textField_vehicleId.setText("");
		this.comboBox_showBusiness.removeAllItems();
		addDepartment();
		this.textField_vehicleNum.setText("");
		this.textField_license.setText("");
		this.textField_usetime.setText("");
		this.textField_vehicleinfo.setText("");

	}

	private void addDepartment() {
		// 加入的部门为营业厅

		Vector<Department> departments = vehicleblservice.getBusiness();

		this.comboBox_query_choosebusiness.addItem("所有");
		for (Department department : departments) {

			Department itemDepartment = makeDepartment(department);
			this.comboBox_query_choosebusiness.addItem(itemDepartment);
			this.comboBox_showBusiness.addItem(itemDepartment);

		}

	}

	private Department makeDepartment(Department source) {
		Department department = new Department() {

			public String toString() {
				return source.getDepartment_name();
			}

			public boolean equals(Object obj) {
				if (obj instanceof Department) {
					Department d = (Department) obj;
					if (getId() == d.getId())
						return true;
				}
				return false;
			}
		};
		department.setId(source.getId());
		return department;
	}

	/**
	 * 获得所有的tips提示，自动补全查询文本框
	 * 
	 * @return
	 */
	private Vector<String> getAllTips() {
		Vector<Vehicle> vehicles = vehicleblservice.getAll();
		tips.clear();
		for (Vehicle vehicle : vehicles) {
			tips.add(vehicle.getVehicle_num());
			tips.add(vehicle.getLicense_plate_number());
		}
		return tips;
	}
}
