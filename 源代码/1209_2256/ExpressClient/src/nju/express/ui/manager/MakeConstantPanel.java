package nju.express.ui.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import nju.express.blservice.Constantblservice;
import nju.express.po.Department;
import nju.express.po.Fare;
import nju.express.po.Job;
import nju.express.ui.MainFrame;
import nju.express.util.LoggerUtil;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MakeConstantPanel extends JPanel {
	static Logger logger = LoggerUtil.getLogger();
	Constantblservice constantblservice;
	private static final long serialVersionUID = 7890871760611769475L;
	private JTextField textField_managersalary;
	private JTextField textField_couriersalary;
	private JTextField textField_businesssalary;
	private JTextField textField_centersalary;
	private JTextField textField_stocksalary;
	private JTextField textField_financesalary;
	private JTextField textField_economyfare;
	private JComboBox comboBox_endlocation;
	private JComboBox comboBox_startlocation;
	private JTextField textField_standardfare;
	private JTextField textField_specialfare;

	/**
	 * Create the panel.
	 */
	public MakeConstantPanel(Constantblservice constantblservice) {
		this.setSize(1100, 664);
		setLayout(null);
		this.constantblservice = constantblservice;

		JLabel label = new JLabel("总经理");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(32, 66, 85, 18);
		add(label);

		textField_managersalary = new JTextField();
		textField_managersalary.setBounds(130, 60, 122, 30);
		add(textField_managersalary);
		textField_managersalary.setColumns(10);

		JLabel label_1 = new JLabel("\u5FEB\u9012\u5458");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(32, 117, 85, 18);
		add(label_1);

		JLabel label_2 = new JLabel("\u6708\u85AA\u8BBE\u7F6E");
		label_2.setBounds(94, 19, 55, 18);
		add(label_2);

		textField_couriersalary = new JTextField();
		textField_couriersalary.setBounds(130, 111, 122, 30);
		add(textField_couriersalary);
		textField_couriersalary.setColumns(10);

		JLabel label_3 = new JLabel("\u8425\u4E1A\u5385\u4E1A\u52A1\u5458");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(32, 173, 85, 18);
		add(label_3);

		textField_businesssalary = new JTextField();
		textField_businesssalary.setBounds(130, 167, 122, 30);
		add(textField_businesssalary);
		textField_businesssalary.setColumns(10);

		JLabel label_4 = new JLabel("\u4E2D\u8F6C\u4E2D\u5FC3\u4E1A\u52A1\u5458");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(32, 227, 85, 18);
		add(label_4);

		textField_centersalary = new JTextField();
		textField_centersalary.setBounds(130, 221, 122, 30);
		add(textField_centersalary);
		textField_centersalary.setColumns(10);

		JLabel label_5 = new JLabel("\u4ED3\u5E93\u7BA1\u7406\u4EBA\u5458");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(32, 281, 85, 18);
		add(label_5);

		textField_stocksalary = new JTextField();
		textField_stocksalary.setBounds(130, 275, 122, 30);
		add(textField_stocksalary);
		textField_stocksalary.setColumns(10);

		JLabel label_6 = new JLabel("\u8D22\u52A1\u4EBA\u5458");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(32, 331, 85, 18);
		add(label_6);

		textField_financesalary = new JTextField();
		textField_financesalary.setBounds(130, 325, 122, 30);
		add(textField_financesalary);
		textField_financesalary.setColumns(10);

		JLabel label_7 = new JLabel("\u8FD0\u8D39\u8BBE\u7F6E");
		label_7.setBounds(490, 19, 55, 18);
		add(label_7);

		comboBox_startlocation = new JComboBox();
		comboBox_startlocation.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				viewFare();
			}
		});
		comboBox_startlocation.setBounds(524, 61, 139, 28);
		add(comboBox_startlocation);

		JLabel label_8 = new JLabel("起始地址");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(420, 66, 78, 18);
		add(label_8);

		JLabel label_9 = new JLabel("\u7EC8\u6B62\u5730\u5740");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(420, 117, 78, 18);
		add(label_9);

		comboBox_endlocation = new JComboBox();
		comboBox_endlocation.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				viewFare();
			}
		});
		comboBox_endlocation.setBounds(524, 112, 139, 28);
		add(comboBox_endlocation);

		JLabel label_10 = new JLabel("经济快递运费");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(420, 173, 92, 18);
		add(label_10);

		textField_economyfare = new JTextField();
		textField_economyfare.setBounds(524, 167, 139, 30);
		add(textField_economyfare);
		textField_economyfare.setColumns(10);

		JButton button_savesalary = new JButton("\u4FDD\u5B58");
		button_savesalary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savesalary();
			}
		});
		button_savesalary.setBounds(74, 397, 90, 30);
		add(button_savesalary);

		JButton button_savefare = new JButton("\u4FDD\u5B58");
		button_savefare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savefare();
			}
		});
		button_savefare.setBounds(483, 397, 90, 30);
		add(button_savefare);

		JLabel label_11 = new JLabel("\u6807\u51C6\u5FEB\u9012\u8FD0\u8D39");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(420, 227, 92, 18);
		add(label_11);

		JLabel label_12 = new JLabel("\u7279\u5FEB\u4E13\u9012\u8FD0\u8D39");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(420, 281, 92, 18);
		add(label_12);

		textField_standardfare = new JTextField();
		textField_standardfare.setBounds(524, 221, 139, 30);
		add(textField_standardfare);
		textField_standardfare.setColumns(10);

		textField_specialfare = new JTextField();
		textField_specialfare.setBounds(524, 275, 139, 30);
		add(textField_specialfare);
		textField_specialfare.setColumns(10);
		addDepartment();
		refresh();
	}

	private void viewSalary() {
		Vector<Job> jobs = constantblservice.getJob();
		for (Job job : jobs) {
			if (job.getId() == 1) {
				this.textField_managersalary.setText(job.getJob_salary() + "");
			}
			if (job.getId() == 2) {
				this.textField_couriersalary.setText(job.getJob_salary() + "");
			}
			if (job.getId() == 3) {
				this.textField_businesssalary.setText(job.getJob_salary() + "");
			}
			if (job.getId() == 4) {
				this.textField_centersalary.setText(job.getJob_salary() + "");
			}
			if (job.getId() == 5) {
				this.textField_stocksalary.setText(job.getJob_salary() + "");
			}
			if (job.getId() == 6) {
				this.textField_financesalary.setText(job.getJob_salary() + "");
			}
		}

	}

	private void viewFare() {
		if (null != this.comboBox_startlocation.getSelectedItem()
				&& null != this.comboBox_endlocation.getSelectedItem()) {
			Department startdepartment = (Department) this.comboBox_startlocation.getSelectedItem();
			Department enddepartment = (Department) this.comboBox_endlocation.getSelectedItem();
			String sql =" SELECT * FROM fare where start_department_id_fk = ? and end_department_id_fk = ? ";
			Object[]values ={startdepartment.getId(),enddepartment.getId()};
			Vector<Fare> fares = constantblservice.getFareBySql(sql, values);
			MainFrame.getLoglabel().setText(
					"选择起始地：" + startdepartment.getDepartment_name() + " 终止地：" + enddepartment.getDepartment_name());
			 if(fares.size() == 1) {
				 Fare fare=fares.get(0);
				 this.textField_economyfare.setText(fare.getEconomyfare()+"");
				 this.textField_standardfare.setText(fare.getStandardfare()+"");
				 this.textField_specialfare.setText(fare.getSpecialfare()+"");
			 }else{
				 this.textField_economyfare.setText("");
				 this.textField_standardfare.setText("");
				 this.textField_specialfare.setText("");
			 }
		}
	}

	private void refresh() {
		
		viewSalary();
		viewFare();
	}

	private void savesalary() {
		if (isSalaryTextValidate()) {
			// 更新多行数据的SQL语句
			// update job set job_salary = CASE id WHEN 1 THEN 8000 WHEN 2 THEN
			// 2000 END WHERE id IN (1,2)
			String sql = "update job set job_salary = CASE id " + "WHEN 1 THEN ? " + "WHEN 2 THEN ? " + "WHEN 3 THEN ? "
					+ "WHEN 4 THEN ? " + "WHEN 5 THEN ? " + "WHEN 6 THEN ? " + "END " + "WHERE id IN (1,2,3,4,5,6)";
			Object[] values = { Double.parseDouble(this.textField_managersalary.getText()),
					Double.parseDouble(this.textField_couriersalary.getText()),
					Double.parseDouble(this.textField_businesssalary.getText()),
					Double.parseDouble(this.textField_centersalary.getText()),
					Double.parseDouble(this.textField_stocksalary.getText()),
					Double.parseDouble(this.textField_financesalary.getText()), };
			boolean result = constantblservice.updateJobSalary(sql, values);
			if (result) {
				MainFrame.getLoglabel().setText("更新工资信息成功");
				logger.info("更新工资信息成功");
			} else {
				MainFrame.getLoglabel().setText("更新工资信息失败");
				logger.error("更新工资信息失败");
			}
			refresh();
		}
	}

	private void savefare() {
		if (isFareTextValidate()) {
			Department startdepartment = (Department) this.comboBox_startlocation.getSelectedItem();
			Department enddepartment = (Department) this.comboBox_endlocation.getSelectedItem();
			Vector<Fare> fares = constantblservice.getFare();
			boolean isupdate = false;
			for (Fare fare : fares) {
				if (fare.getStart_department_id_fk() == startdepartment.getId()
						&& fare.getEnd_department_id_fk() == enddepartment.getId()) {
					isupdate = true;
				}
			}
			if (isupdate) {
				// update
				String sql = "update fare set economyfare = ? ,standardfare = ? , specialfare = ? where start_department_id_fk = ? and end_department_id_fk = ? ";
				Object[] values = { Double.parseDouble(this.textField_economyfare.getText()),
						Double.parseDouble(this.textField_standardfare.getText()),
						Double.parseDouble(this.textField_specialfare.getText()), startdepartment.getId(),
						enddepartment.getId() };
				boolean result = constantblservice.updateFare(sql, values);
				if (result) {
					MainFrame.getLoglabel().setText("更新运费信息成功");
					logger.info("更新运费信息成功");
				} else {
					MainFrame.getLoglabel().setText("更新运费信息失败");
					logger.error("更新运费信息失败");
				}
			} else {
				Fare fare = new Fare(startdepartment.getId(), enddepartment.getId(),
						Double.parseDouble(this.textField_economyfare.getText()),
						Double.parseDouble(this.textField_standardfare.getText()),
						Double.parseDouble(this.textField_specialfare.getText()));
				int result = constantblservice.addFare(fare);
				// 通知添加结果
				if (result == 0) {
					MainFrame.getLoglabel().setText("新增运费信息失败");
					logger.error("新增运费信息失败");
				} else {
					MainFrame.getLoglabel().setText("新增运费信息成功");
					logger.info("新增运费信息成功");
				}
			}
			refresh();
		}
	}

	/**
	 * 检查某些必填文本框是否为空
	 * 
	 * @return
	 */
	private boolean isSalaryTextValidate() {

		if (this.textField_managersalary.getText().equals("")) {
			showWarn("未输入总经理工资");
			return false;
		}
		if (this.textField_couriersalary.getText().equals("")) {
			showWarn("未输入快递员工资");
			return false;
		}

		if (this.textField_businesssalary.getText().equals("")) {
			showWarn("未输入营业厅业务员工资");
			return false;
		}

		if (this.textField_centersalary.getText().equals("")) {
			showWarn("未输入中转中心业务员工资");
			return false;
		}

		if (this.textField_stocksalary.getText().equals("")) {
			showWarn("未输入库存管理员工资");
			return false;
		}

		if (this.textField_financesalary.getText().equals("")) {
			showWarn("未输入财务人员工资");
			return false;
		}

		if (!isPositiveNumber(this.textField_managersalary.getText())) {
			showWarn("总经理工资格式不正确");
			return false;
		}
		;
		if (!isPositiveNumber(this.textField_couriersalary.getText())) {
			showWarn("快递员工资格式不正确");
			return false;
		}
		;
		if (!isPositiveNumber(this.textField_businesssalary.getText())) {
			showWarn("营业厅业务员工资格式不正确");
			return false;
		}
		;

		if (!isPositiveNumber(this.textField_centersalary.getText())) {
			showWarn("中转中心业务员工资格式不正确");
			return false;
		}
		;

		if (!isPositiveNumber(this.textField_stocksalary.getText())) {
			showWarn("库存管理人员工资格式不正确");
			return false;
		}
		;

		if (!isPositiveNumber(this.textField_financesalary.getText())) {
			showWarn("财务人员工资格式不正确");
			return false;
		}

		return true;

	}

	/**
	 * 检查某些文本框内容是否符合格式
	 * 
	 * @return
	 */
	private boolean isFareTextValidate() {

		if (this.textField_economyfare.getText().equals("")) {
			showWarn("未输入经济快递运费");
			return false;
		}

		if (this.textField_standardfare.getText().equals("")) {
			showWarn("未输入标准快递运费");
			return false;
		}
		;
		if (this.textField_specialfare.getText().equals("")) {
			showWarn("未输入特快专递运费");
			return false;
		}

		if (!isPositiveNumber(this.textField_economyfare.getText())) {
			showWarn("经济快递运费格式不正确");
			return false;
		}

		if (!isPositiveNumber(this.textField_standardfare.getText())) {

			showWarn("标准快递运费格式不正确");
			return false;
		}
		if (!isPositiveNumber(this.textField_specialfare.getText())) {

			showWarn("特快专递运费格式不正确");
			return false;
		}

		return true;
	}

	/*
	 * 检查字符串是否是数字并且大于0
	 * 
	 * @param string
	 * 
	 * @return
	 */
	private boolean isPositiveNumber(String string) {

		try {
			double d = Double.parseDouble(string);
			if (d > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	private void addDepartment() {

		Vector<Department> centers = constantblservice.getCenter();

		for (Department center : centers) {
			Department itemcenter = makeDepartment(center);
			this.comboBox_startlocation.addItem(itemcenter);
			this.comboBox_endlocation.addItem(itemcenter);
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

	// 显示警告
	protected int showWarn(String message) {
		return JOptionPane.showConfirmDialog(this, message, "警告", JOptionPane.OK_CANCEL_OPTION);
	}

}
