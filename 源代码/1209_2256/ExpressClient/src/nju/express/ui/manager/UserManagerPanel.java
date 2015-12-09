package nju.express.ui.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import nju.express.blservice.UserblService;
import nju.express.po.Department;
import nju.express.po.Job;
import nju.express.po.User;
import nju.express.ui.MainFrame;
import nju.express.ui.utils.CommonPanel;
import nju.express.ui.utils.SingleJTable;
import nju.express.util.AutoTextFieldUtil;
import nju.express.util.LoggerUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserManagerPanel extends CommonPanel {

	static Logger logger = LoggerUtil.getLogger();
	UserblService userblService;
	Vector columns;
	Vector<String> tips = new Vector<String>();
	private static final long serialVersionUID = -6671587993271050467L;
	private JTextField textField_userId = new JTextField("");
	private JTextField textField_query;
	private JTable table;
	private JTextField textField_name;
	private JComboBox comboBox_job;
	private JComboBox comboBox_department;
	private JPasswordField passwordField;

	public UserManagerPanel(UserblService userblService) {
		this.setSize(1100, 664);
		setLayout(null);

		this.userblService = userblService;

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
		scrollPane.setBounds(6, 46, 1088, 304);
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

		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setBounds(396, 363, 55, 18);
		add(label);

		textField_name = new JTextField();
		textField_name.setBounds(469, 357, 122, 30);
		add(textField_name);
		textField_name.setColumns(10);

		JLabel label_1 = new JLabel("职位：");
		label_1.setBounds(400, 474, 55, 18);
		add(label_1);

		comboBox_job = new JComboBox();
		comboBox_job.setBounds(469, 469, 122, 28);
		add(comboBox_job);
		addJob();

		JLabel label_2 = new JLabel("部门：");
		label_2.setBounds(400, 523, 55, 18);
		add(label_2);

		comboBox_department = new JComboBox();
		comboBox_department.setBounds(469, 518, 208, 28);
		add(comboBox_department);
		addDepartment();

		JButton button_save = new JButton("\u4FDD\u5B58");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		button_save.setBounds(416, 590, 90, 30);
		add(button_save);

		JButton button_clear = new JButton("\u6E05\u7A7A");
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		button_clear.setBounds(516, 590, 90, 30);
		add(button_clear);

		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		button_delete.setBounds(619, 590, 90, 30);
		add(button_delete);

		JLabel label_3 = new JLabel("\u767B\u5F55\u5BC6\u7801\uFF1A");
		label_3.setBounds(396, 418, 80, 18);
		add(label_3);

		passwordField = new JPasswordField();
		passwordField.setBounds(469, 412, 122, 30);
		add(passwordField);

	}

	private void view() {
		int id = getSelectId(getJTable());
		User user = userblService.getById(id);
		MainFrame.getLoglabel().setText("查看职员信息");
		this.textField_userId.setText(id + "");
		this.textField_name.setText(user.getUser_name());
		this.passwordField.setText(user.getUser_password());
		this.comboBox_job.setSelectedItem(user.getJob());
		this.comboBox_department.setSelectedItem(user.getDepartment());
	}

	private void query() {
		String text = this.textField_query.getText().trim();
		Vector<User> users;
		Vector<Vector> datas;
		MainFrame.getLoglabel().setText("查询职员：" + text);
		String sql = " SELECT * from `user`  WHERE user_name  like '%" + text + "%' ";
		Object[] values = { };
		users = userblService.getBySql(sql, values);
		datas = changeDatas(users);
		this.datas = datas;
		refreshTable();

	}

	private void save() {
		if (this.textField_userId.getText().equals("")) {
			add();
		} else {
			update();
		}
	}

	private void add() {
		if (isTextExist()) {

			User user = getUser();
			int result = userblService.add(user);
			// 重新读取数据
			setViewDatas();
			// 刷新列表, 清空表单
			clear();
			// 通知添加结果
			if (result == 0) {
				MainFrame.getLoglabel().setText("新增职员信息失败");
				logger.error("新增职员信息失败");
			} else {
				MainFrame.getLoglabel().setText("新增职员信息成功");
				logger.info("新增职员信息成功");
			}
		}
	}

	private void update() {
		User user = getUser();
		int userId = Integer.parseInt(this.textField_userId.getText());
		user.setId(userId);
		boolean result = userblService.update(user);
		// 重新读取数据
		setViewDatas();
		// 刷新列表, 清空表单
		clear();
		// 通知更新结果
		if (result) {
			MainFrame.getLoglabel().setText("更新职员信息成功");
			logger.info("更新职员信息成功");
		} else {
			MainFrame.getLoglabel().setText("更新职员信息失败");
			logger.error("更新职员信息失败");
		}
	}

	private void delete() {
		if (!this.textField_userId.getText().equals("")) {
			int userId = Integer.parseInt(this.textField_userId.getText());
			boolean result = userblService.delete(userId);
			// 重新读取数据
			setViewDatas();
			// 刷新列表, 清空表单
			clear();
			if (result) {

				MainFrame.getLoglabel().setText("刪除职员信息成功");
				logger.info("刪除职员信息成功");
			} else {
				MainFrame.getLoglabel().setText("刪除职员信息失败");
				logger.error("刪除职员信息失败");
			}
		} else {
			showWarn("未选择职员");
		}
	}

	private User getUser() {
		char[] passes = this.passwordField.getPassword();
		StringBuffer password = new StringBuffer();
		for (char c : passes) {
			password.append(c);
		}
		Job job = (Job) this.comboBox_job.getSelectedItem();
		Department department = (Department) this.comboBox_department.getSelectedItem();
		return new User(this.textField_name.getText(), password.toString(), job.getId(), department.getId());
	}

	private boolean isTextExist() {
		if (this.textField_name.getText().equals("")) {
			showWarn("未输入职员姓名");
			return false;
		}

		if (this.passwordField.getPassword().toString().equals("")) {
			showWarn("未输入登录密码");
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
		this.columns.add("姓名");
		this.columns.add("职位");
		this.columns.add("部门");

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
		Vector<User> users = userblService.getAll();
		// 转换显示格式
		Vector<Vector> datas = changeDatas(users);
		// 调用父类方法设置结果集合
		setDatas(datas);
	}

	private Vector<Vector> changeDatas(Vector<User> users) {
		Vector<Vector> view = new Vector<Vector>();
		for (User user : users) {
			Vector v = new Vector();
			v.add(user.getId());
			v.add(user.getUser_name());
			v.add(makeJob(user.getJob()));
			v.add(makeDepartment(user.getDepartment()));
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
		this.textField_userId.setText("");
		this.textField_name.setText("");

	}

	private void addDepartment() {

		Vector<Department> departments = userblService.getDepartment();

		for (Department department : departments) {

			Department itemDepartment = makeDepartment(department);
			this.comboBox_department.addItem(itemDepartment);

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
	 * 向下拉框添加courier快递员
	 */
	private void addJob() {

		Vector<Job> jobs = userblService.getJob();
		for (Job job : jobs) {
			this.comboBox_job.addItem(makeJob(job));
		}
	}

	/**
	 * @param source
	 * @return
	 */
	private Job makeJob(final Job source) {
		Job job = new Job() {

			public String toString() {
				return source.getJob_name();
			}

			public boolean equals(Object obj) {
				if (obj instanceof Job) {
					Job j = (Job) obj;
					if (getId() == j.getId())
						return true;
				}
				return false;
			}
		};
		job.setId(source.getId());
		return job;
	}

	/**
	 * 获得所有的tips提示，自动补全查询文本框
	 * 
	 * @return
	 */
	private Vector<String> getAllTips() {
		Vector<User> users = userblService.getAll();
		tips.clear();
		for (User user : users) {
			tips.add(user.getUser_name());

		}
		return tips;
	}
}
