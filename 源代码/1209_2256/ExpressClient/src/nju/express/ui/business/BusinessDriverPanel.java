package nju.express.ui.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

import nju.express.blservice.Driverblservice;
import nju.express.po.Department;
import nju.express.po.Driver;
import nju.express.ui.MainFrame;
import nju.express.ui.utils.SingleJTable;
import nju.express.ui.utils.CommonPanel;
import nju.express.util.AutoTextFieldUtil;
import nju.express.util.DateTextField;
import nju.express.util.DateUtil;
import nju.express.util.LoggerUtil;

public class BusinessDriverPanel extends CommonPanel {
	static Logger logger = LoggerUtil.getLogger();
	Driverblservice driverblservice;
	// Jtable������
	Vector columns;
	Vector<String> tips = new Vector<String>();
	/**
	 * 
	 */
	private static final long serialVersionUID = -8995661654021530625L;
	private JTextField textField_driverId = new JTextField("");
	private JTextField textField_query;

	private JTextField textField_name;
	private JTextField textField_num;
	private JTextField textField_idcard;
	private JTextField textField_phone;
	private DateTextField textField_birthday;
	private JComboBox comboBox_showBusiness;
	private JComboBox comboBox_chooseBusiness;
	private JComboBox comboBox_gender;

	/**
	 * Create the panel.
	 */
	public BusinessDriverPanel(Driverblservice driverblservice) {
		this.setSize(1100, 664);
		this.driverblservice = driverblservice;

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

		JLabel label = new JLabel("��ѯ˾�����");
		label.setBounds(365, 12, 72, 18);

		textField_query = new JTextField();
		textField_query.setBounds(449, 6, 295, 30);
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		textField_query.setColumns(10);

		JButton button_query = new JButton("\u67E5\u8BE2");
		button_query.setBounds(756, 6, 93, 30);
		button_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 48, 1088, 312);

		JLabel label_1 = new JLabel("\u9009\u62E9\u8425\u4E1A\u5385");
		label_1.setBounds(49, 12, 60, 18);

		comboBox_chooseBusiness = new JComboBox();
		comboBox_chooseBusiness.setBounds(121, 7, 232, 28);
		comboBox_chooseBusiness.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Autoquery();
			}
		});

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 366, 1088, 2);

		JLabel label_2 = new JLabel("\u53F8\u673A\u6240\u5C5E\u7684\u8425\u4E1A\u5385");
		label_2.setBounds(6, 385, 96, 18);

		comboBox_showBusiness = new JComboBox();
		comboBox_showBusiness.setBounds(108, 380, 235, 28);
		// ΪcomboBox_chooseBusiness��comboBox_showBusiness���ѡ��
		addDepartment();

		JLabel label_3 = new JLabel("\u59D3\u540D");
		label_3.setBounds(6, 426, 96, 18);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);

		textField_name = new JTextField();
		textField_name.setBounds(101, 420, 122, 30);
		textField_name.setColumns(10);

		JLabel label_4 = new JLabel("\u7F16\u53F7");
		label_4.setBounds(238, 426, 69, 18);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);

		textField_num = new JTextField();
		textField_num.setBounds(329, 420, 122, 30);
		textField_num.setColumns(10);

		JLabel label_5 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7");
		label_5.setBounds(463, 426, 117, 18);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);

		textField_idcard = new JTextField();
		textField_idcard.setBounds(592, 420, 161, 30);
		textField_idcard.setColumns(10);

		JLabel label_6 = new JLabel("\u624B\u673A\u53F7\u7801");
		label_6.setBounds(792, 426, 97, 18);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);

		textField_phone = new JTextField();
		textField_phone.setBounds(901, 420, 193, 30);
		textField_phone.setColumns(10);

		JLabel label_7 = new JLabel("\u6027\u522B");
		label_7.setBounds(41, 468, 24, 18);
		String[] genders = { "��", "Ů" };
		comboBox_gender = new JComboBox(genders);
		comboBox_gender.setBounds(108, 463, 72, 28);

		JLabel label_8 = new JLabel("\u51FA\u8EAB\u65E5\u671F");
		label_8.setBounds(259, 468, 48, 18);

		textField_birthday = new DateTextField("yyyy-MM-dd");
		textField_birthday.setBounds(329, 462, 152, 30);
		textField_birthday.setColumns(10);

		JButton button_choosetime = new JButton("\u9009\u62E9\u65F6\u95F4");
		button_choosetime.setBounds(487, 462, 76, 30);
		button_choosetime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatePicker dp = new DatePicker(textField_birthday, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_birthday.getText());

				dp.setSelectedDate(selectedDate);
				dp.start(textField_birthday);
			}
		});

		JButton button_save = new JButton("\u4FDD\u5B58");
		button_save.setBounds(378, 518, 86, 30);
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		JButton button_clear = new JButton("\u6E05\u7A7A");
		button_clear.setBounds(549, 518, 83, 30);
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.setBounds(702, 518, 84, 30);
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});

		JButton button_showAll = new JButton("\u663E\u793A\u6240\u6709");
		button_showAll.setBounds(861, 6, 106, 30);
		button_showAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getLoglabel().setText("��ʾ����");
				setViewDatas();
				refreshTable();
			}
		});
		setLayout(null);

		scrollPane.setViewportView(table);
		add(scrollPane);
		add(label_1);
		add(comboBox_chooseBusiness);
		add(label);
		add(textField_query);
		add(button_query);
		add(button_showAll);
		add(separator);
		add(label_3);
		add(label_2);
		add(label_7);
		add(comboBox_showBusiness);
		add(textField_name);
		add(comboBox_gender);
		add(label_4);
		add(label_8);
		add(textField_num);
		add(label_5);
		add(textField_idcard);
		add(label_6);
		add(textField_phone);
		add(textField_birthday);
		add(button_choosetime);
		add(button_save);
		add(button_clear);
		add(button_delete);
	}

	/**
	 * ��ʼ��table����
	 */
	public void initColumns() {
		this.columns = new Vector();

		this.columns.add("id");
		this.columns.add("˾�����");
		this.columns.add("˾������");
		this.columns.add("���֤��");
		this.columns.add("˾����ϵ��ʽ");
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
		Vector<Driver> drivers = driverblservice.getAll();
		// ת����ʾ��ʽ
		Vector<Vector> datas = changeDatas(drivers);
		// ���ø��෽�����ý������
		setDatas(datas);
	}

	private Vector<Vector> changeDatas(Vector<Driver> drivers) {
		Vector<Vector> view = new Vector<Vector>();
		for (Driver driver : drivers) {
			Vector v = new Vector();
			v.add(driver.getId());
			v.add(driver.getDriver_num());
			v.add(driver.getDriver_name());
			v.add(driver.getDriver_idcard());
			v.add(driver.getDriver_phone());
			v.add(driver.getDepartment().getDepartment_name());
			view.add(v);
		}
		return view;
	}

	private void view() {
		int id = getSelectId(getJTable());
		Driver driver = driverblservice.getById(id);
		MainFrame.getLoglabel().setText("�鿴˾��(˾������:" + driver.getDriver_name() + ")");
		this.textField_driverId.setText(id + "");
		this.comboBox_showBusiness.setSelectedItem(makeDepartment(driver.getDepartment()));
		this.textField_name.setText(driver.getDriver_name());
		this.textField_num.setText(driver.getDriver_num());
		this.textField_idcard.setText(driver.getDriver_idcard());
		this.textField_phone.setText(driver.getDriver_phone());
		this.comboBox_gender.setSelectedItem(driver.getDriver_gender());
		this.textField_birthday.setText(DateUtil.getStringByDate(driver.getDriver_birthday()));
	}

	private void query() {

		String text = this.textField_query.getText().trim();
		Vector<Driver> drivers;
		Vector<Vector> datas;
		String departmentName = this.comboBox_chooseBusiness.getSelectedItem().toString();

		MainFrame.getLoglabel().setText("��ѯ˾��(" + departmentName + " " + text + ")");
		logger.info("��ѯ˾��(" + departmentName + " " + text + ")");

		if (departmentName.equals("����")) {
			String sql = "select * from driver where driver_name = ? or driver_num = ? or driver_idcard = ?  ";
			Object[] values = { text, text, text };
			drivers = driverblservice.getBySql(sql, values);
			datas = changeDatas(drivers);
			setDatas(datas);
			refreshTable();
		} else {
			Vector<Department> departments = driverblservice.getBusiness();
			for (Department department : departments) {
				if (department.getDepartment_name().equals(departmentName)) {
					int departmentId = department.getId();
					String sql = "select * from driver where ( department_id_fk = ? ) and ( driver_name = ? or driver_num = ? or driver_idcard = ? ) ";
					Object[] values = { departmentId, text, text, text };
					drivers = driverblservice.getBySql(sql, values);
					datas = changeDatas(drivers);
					setDatas(datas);
					refreshTable();
				}
			}

		}
	}

	private void Autoquery() {

		Vector<Driver> drivers;
		Vector<Vector> datas;
		String departmentName = this.comboBox_chooseBusiness.getSelectedItem().toString();

		if (departmentName.equals("����")) {

			drivers = driverblservice.getAll();
			datas = changeDatas(drivers);
			setDatas(datas);
			refreshTable();
		} else {
			Vector<Department> departments = driverblservice.getBusiness();
			for (Department department : departments) {
				if (department.getDepartment_name().equals(departmentName)) {
					String sql = "select * from driver where department_id_fk = ? ";
					Object[] values = { department.getId() };
					drivers = driverblservice.getBySql(sql, values);
					datas = changeDatas(drivers);
					setDatas(datas);
					refreshTable();
				}
			}

		}
	}

	private void save() {
		if (this.textField_driverId.getText().equals("")) {
			add();
		} else {
			update();
		}
	}

	private void add() {
		if (isTextExist() && isTextValidate()) {

			Driver driver = getDriver();
			int result = driverblservice.add(driver);
			// ���¶�ȡ����
			setViewDatas();
			// ˢ���б�, ��ձ�
			clear();
			// ֪ͨ��ӽ��
			if (result == 0) {
				MainFrame.getLoglabel().setText("����˾����Ϣʧ��");
				logger.error("����˾����Ϣʧ��");
			} else {
				MainFrame.getLoglabel().setText("����˾����Ϣ�ɹ�");
				logger.info("����˾����Ϣ�ɹ�");
			}
		}
	}

	private void update() {

		// getvehicle�����Ѿ�������department
		Driver driver = getDriver();
		int driverId = Integer.parseInt(this.textField_driverId.getText());
		driver.setId(driverId);
		boolean result = driverblservice.update(driver);
		// ���¶�ȡ����
		setViewDatas();
		// ˢ���б�, ��ձ�
		clear();
		// ֪ͨ���½��
		if (result) {
			MainFrame.getLoglabel().setText("����˾����Ϣ�ɹ�");
			logger.info("����˾����Ϣ�ɹ�");
		} else {
			MainFrame.getLoglabel().setText("����˾����Ϣʧ��");
			logger.error("����˾����Ϣʧ��");
		}
	}

	private void delete() {
		if (!this.textField_driverId.getText().equals("")) {
			int driverId = Integer.parseInt(this.textField_driverId.getText());
			boolean result = driverblservice.delete(driverId);
			// ���¶�ȡ����
			setViewDatas();
			// ˢ���б�, ��ձ�
			clear();
			if (result) {

				MainFrame.getLoglabel().setText("�h��˾����Ϣ�ɹ�");
				logger.info("�h��˾����Ϣ�ɹ�");
			} else {
				MainFrame.getLoglabel().setText("�h��˾����Ϣʧ��");
				logger.error("�h��˾����Ϣʧ��");
			}
		} else {
			showWarn("δѡ��˾��");
		}
	}

	private boolean isTextExist() {

		if (this.textField_num.getText().equals("")) {
			showWarn("δ����˾�����");
			return false;
		}
		if (this.textField_name.getText().equals("")) {
			showWarn("δ����˾������");
			return false;
		}
		if (this.textField_idcard.getText().equals("")) {
			showWarn("δ����˾�����֤��");
			return false;
		}
		if (this.textField_phone.getText().equals("")) {
			showWarn("δ����˾����ϵ��ʽ");
			return false;
		}
		if (this.textField_birthday.getText().equals("")) {
			showWarn("δ����˾������");
			return false;
		}
		return true;
	}

	private boolean isTextValidate() {
		if (!isNumber(this.textField_idcard.getText())) {
			showWarn("˾�����֤�Ÿ�ʽ����ȷ");
			return false;
		}
		if (!isNumber(this.textField_num.getText())) {
			showWarn("˾����Ÿ�ʽ����ȷ");
			return false;
		}
		if (this.textField_num.getText().length() != 9) {
			showWarn("˾����ų��Ȳ���ȷ");
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

	@Override
	public void clear() {
		MainFrame.getLoglabel().setText("���");
		refreshTable();
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		this.textField_driverId.setText("");
		this.textField_num.setText("");
		this.textField_name.setText("");
		this.textField_idcard.setText("");
		this.textField_birthday.setText("");
		this.textField_phone.setText("");

	}

	private Driver getDriver() {
		Department department = (Department) this.comboBox_showBusiness.getSelectedItem();
		return new Driver(department.getId(), this.textField_name.getText(), this.textField_num.getText(),
				(String) this.comboBox_gender.getSelectedItem(),
				new java.sql.Date(DateUtil.getDateByDateString(this.textField_birthday.getText()).getTime()),
				this.textField_idcard.getText(), this.textField_phone.getText());
	}

	private void addDepartment() {
		// ����Ĳ���ΪӪҵ��

		Vector<Department> departments = driverblservice.getBusiness();

		this.comboBox_chooseBusiness.addItem("����");
		for (Department department : departments) {

			Department itemDepartment = makeDepartment(department);
			this.comboBox_chooseBusiness.addItem(itemDepartment);
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
		Vector<Driver> drivers = driverblservice.getAll();
		tips.clear();
		for (Driver driver : drivers) {
			tips.add(driver.getDriver_num());
			tips.add(driver.getDriver_idcard());
			tips.add(driver.getDriver_name());
		}
		return tips;
	}
}
