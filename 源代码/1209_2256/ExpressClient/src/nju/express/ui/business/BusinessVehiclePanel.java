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

import org.apache.log4j.Logger;

import com.qt.datapicker.DatePicker;

import nju.express.blservice.Vehicleblservice;
import nju.express.po.Department;
import nju.express.po.Vehicle;
import nju.express.ui.MainFrame;
import nju.express.ui.utils.SingleJTable;
import nju.express.ui.utils.CommonPanel;
import nju.express.util.AutoTextFieldUtil;
import nju.express.util.DateTextField;
import nju.express.util.DateUtil;
import nju.express.util.LoggerUtil;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class BusinessVehiclePanel extends CommonPanel {

	static Logger logger = LoggerUtil.getLogger();
	Vehicleblservice vehicleblservice;
	// Jtable������
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
		this.setSize(1100, 664);
		this.vehicleblservice = vehicleblservice;

		initColumns();
		setViewDatas();

		DefaultTableModel model = new DefaultTableModel(getDatas(), this.columns);
		JTable table = new SingleJTable(model);
		setJTable(table);
		setTableFace();
		// Ϊtable��Ӽ���
		getJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				// ��ѡ����ʱ����ͷ�ʱ��ִ��
				if (!event.getValueIsAdjusting()) {
					// ���û��ѡ���κ�һ��, �򷵻�
					if (getJTable().getSelectedRowCount() != 1)
						return;
					view();
				}
			}
		});

		JLabel label = new JLabel("\u9009\u62E9\u8425\u4E1A\u5385");
		label.setBounds(72, 12, 60, 18);

		comboBox_query_choosebusiness = new JComboBox();
		comboBox_query_choosebusiness.setBounds(144, 7, 232, 28);
		comboBox_query_choosebusiness.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Autoquery();
			}
		});
		comboBox_showBusiness = new JComboBox();
		comboBox_showBusiness.setBounds(114, 363, 243, 28);
		addDepartment();
		JLabel label_1 = new JLabel("��ѯ�������");
		label_1.setBounds(388, 12, 72, 18);

		textField_query = new JTextField();
		textField_query.setBounds(472, 6, 295, 30);

		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		textField_query.setColumns(10);

		JButton button_query = new JButton("��ѯ");
		button_query.setBounds(779, 6, 93, 30);
		button_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 42, 1088, 313);

		JLabel label_2 = new JLabel("\u8F66\u8F86\u6240\u5C5E\u7684\u8425\u4E1A\u5385");
		label_2.setBounds(6, 368, 96, 18);

		JLabel label_3 = new JLabel("\u8F66\u8F86\u4EE3\u53F7");
		label_3.setBounds(6, 409, 48, 18);

		textField_vehicleNum = new JTextField();
		textField_vehicleNum.setBounds(66, 403, 174, 30);
		textField_vehicleNum.setColumns(10);

		JLabel label_4 = new JLabel("\u8F66\u724C\u53F7");
		label_4.setBounds(258, 409, 87, 18);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);

		textField_license = new JTextField();
		textField_license.setBounds(357, 403, 146, 30);
		textField_license.setColumns(10);

		JLabel label_5 = new JLabel("\u8F66\u8F86\u5F00\u59CB\u670D\u5F79\u65F6\u95F4");
		label_5.setBounds(521, 409, 96, 18);

		textField_usetime = new DateTextField("yyyy-MM-dd");
		textField_usetime.setBounds(629, 403, 137, 30);
		textField_usetime.setColumns(10);

		JButton button_usetime = new JButton("\u9009\u62E9\u65F6\u95F4");
		button_usetime.setBounds(772, 403, 76, 30);
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
		label_6.setBounds(6, 445, 48, 18);

		textField_vehicleinfo = new JTextField();
		textField_vehicleinfo.setBounds(66, 445, 782, 30);
		textField_vehicleinfo.setColumns(10);

		JButton button_save = new JButton("\u4FDD\u5B58");
		button_save.setBounds(333, 487, 86, 30);
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		JButton button_clear = new JButton("\u6E05\u7A7A");
		button_clear.setBounds(473, 487, 83, 30);
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.setBounds(628, 487, 84, 30);
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});

		JButton button_showAll = new JButton("\u663E\u793A\u6240\u6709");
		button_showAll.setBounds(884, 6, 103, 30);
		button_showAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getLoglabel().setText("��ʾ����");
				setViewDatas();
				refreshTable();
			}
		});

		scrollPane.setViewportView(table);
		setLayout(null);
		add(label);
		add(comboBox_query_choosebusiness);
		add(label_1);
		add(textField_query);
		add(button_query);
		add(button_showAll);
		add(scrollPane);
		add(label_2);
		add(comboBox_showBusiness);
		add(label_6);
		add(textField_vehicleinfo);
		add(label_3);
		add(textField_vehicleNum);
		add(label_4);
		add(textField_license);
		add(label_5);
		add(textField_usetime);
		add(button_usetime);
		add(button_save);
		add(button_clear);
		add(button_delete);

	}

	private void view() {
		int id = getSelectId(getJTable());
		Vehicle vehicle = vehicleblservice.getById(id);
		MainFrame.getLoglabel().setText("�鿴����(�������:" + vehicle.getVehicle_num() + ")");
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

		MainFrame.getLoglabel().setText("��ѯ����(" + departmentName + " " + text + ")");
		logger.info("��ѯ����(" + departmentName + " " + text + ")");

		if (departmentName.equals("����")) {
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

	private void Autoquery() {

		Vector<Vehicle> vehicles;
		Vector<Vector> datas;
		String departmentName = this.comboBox_query_choosebusiness.getSelectedItem().toString();

		if (departmentName.equals("����")) {

			vehicles = vehicleblservice.getAll();
			datas = changeDatas(vehicles);
			setDatas(datas);
			refreshTable();
		} else {
			Vector<Department> departments = vehicleblservice.getBusiness();
			for (Department department : departments) {
				if (department.getDepartment_name().equals(departmentName)) {

					vehicles = vehicleblservice.getByCondition("department_id_fk", department.getId());
					datas = changeDatas(vehicles);
					setDatas(datas);
					refreshTable();
				}
			}

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

			Vehicle vehicle = getVehicle();
			int result = vehicleblservice.add(vehicle);
			// ���¶�ȡ����
			setViewDatas();
			// ˢ���б�, ��ձ�
			clear();
			// ֪ͨ��ӽ��
			if (result == 0) {
				MainFrame.getLoglabel().setText("����������Ϣʧ��");
				logger.error("����������Ϣʧ��");
			} else {
				MainFrame.getLoglabel().setText("����������Ϣ�ɹ�");
				logger.info("����������Ϣ�ɹ�");
			}
		}
	}

	private void update() {

		// getvehicle�����Ѿ�������department
		Vehicle vehicle = getVehicle();
		int vehicleId = Integer.parseInt(this.textField_vehicleId.getText());
		vehicle.setId(vehicleId);
		boolean result = vehicleblservice.update(vehicle);
		// ���¶�ȡ����
		setViewDatas();
		// ˢ���б�, ��ձ�
		clear();
		// ֪ͨ���½��
		if (result) {
			MainFrame.getLoglabel().setText("���³�����Ϣ�ɹ�");
			logger.info("���³�����Ϣ�ɹ�");
		} else {
			MainFrame.getLoglabel().setText("���³�����Ϣʧ��");
			logger.error("���³�����Ϣʧ��");
		}

	}

	private void delete() {
		if (!this.textField_vehicleId.getText().equals("")) {
			int vehicleId = Integer.parseInt(this.textField_vehicleId.getText());
			boolean result = vehicleblservice.delete(vehicleId);
			// ���¶�ȡ����
			setViewDatas();
			// ˢ���б�, ��ձ�
			clear();
			if (result) {
				MainFrame.getLoglabel().setText("�h��������Ϣ�ɹ�");
				logger.info("�h��������Ϣ�ɹ�");
			} else {
				MainFrame.getLoglabel().setText("�h��������Ϣʧ��");
				logger.error("�h��������Ϣʧ��");
			}
		} else {
			showWarn("δѡ����");
		}
	}

	private boolean isTextExist() {

		if (this.textField_vehicleNum.getText().equals("")) {
			showWarn("δ���복�����");
			return false;
		}
		if (this.textField_license.getText().equals("")) {
			showWarn("δ���복�ƺ�");
			return false;
		}
		if (this.textField_usetime.getText().equals("")) {
			showWarn("δ���복����ʼ����ʱ��");
			return false;
		}
		return true;
	}

	private boolean isTextValidate() {
		if (!isNumber(this.textField_vehicleNum.getText())) {
			showWarn("������Ÿ�ʽ����ȷ");
			return false;
		}
		if (this.textField_vehicleNum.getText().length() != 9) {
			showWarn("������ų��Ȳ���ȷ");
			return false;
		}
		return true;
	}

	/*
	 * ����ַ����Ƿ�������
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
	 * ��ʼ��table����
	 */
	public void initColumns() {
		this.columns = new Vector();

		this.columns.add("id");
		this.columns.add("�������");
		this.columns.add("���ƺ�");
		this.columns.add("ʹ��ʱ��");
		this.columns.add("����Ӫҵ��");

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
		// ת����ʾ��ʽ
		Vector<Vector> datas = changeDatas(vehicles);
		// ���ø��෽�����ý������
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
		MainFrame.getLoglabel().setText("���");
		refreshTable();
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		this.textField_vehicleId.setText("");

		this.textField_vehicleNum.setText("");
		this.textField_license.setText("");
		this.textField_usetime.setText("");
		this.textField_vehicleinfo.setText("");

	}

	private void addDepartment() {
		// ����Ĳ���ΪӪҵ��

		Vector<Department> departments = vehicleblservice.getBusiness();

		this.comboBox_query_choosebusiness.addItem("����");
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
	 * ������е�tips��ʾ���Զ���ȫ��ѯ�ı���
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
