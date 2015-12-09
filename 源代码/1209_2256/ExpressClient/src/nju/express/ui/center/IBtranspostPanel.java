package nju.express.ui.center;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.qt.datapicker.DatePicker;

import nju.express.blservice.BItransportblservice;
import nju.express.blservice.Postblservice;
import nju.express.po.BItransport;
import nju.express.po.Department;
import nju.express.po.Driver;
import nju.express.po.Post;
import nju.express.po.Vehicle;
import nju.express.ui.MainFrame;
import nju.express.ui.utils.SingleJTable;
import nju.express.ui.utils.UnEditedJTable;
import nju.express.util.AutoTextFieldUtil;
import nju.express.util.DateTextField;
import nju.express.util.DateUtil;
import nju.express.util.LoggerUtil;

public class IBtranspostPanel extends JPanel {

	static Logger logger = LoggerUtil.getLogger();
	Postblservice postblservice;
	BItransportblservice bItransportblservice;

	Vector BItransportcolumns;
	Vector<Vector> BItransportdatas;

	Vector<Vector> selcetedPostdatas;
	Vector<Vector> unselcetedPostdatas;
	Vector postcolumns;

	Vector<String> tips = new Vector<String>();
	private static final long serialVersionUID = 7118137837343260340L;
	private JTextField textField_BItransportId = new JTextField("");
	private JTextField textField_query;
	private JTable table_BItransport;
	private JTable table_selectedPost;
	private JTable table_unselectedPost;
	private JTextField textField_fare;
	private JTextField textField_info;
	private DateTextField textField_datetime;
	private JComboBox comboBox_startdepartment;
	private JComboBox comboBox_enddepartment;
	private JComboBox comboBox_vehicle;
	private JComboBox comboBox_driver;

	public IBtranspostPanel(Postblservice postblservice, BItransportblservice bItransportblservice) {
		this.setSize(1100, 664);
		this.postblservice = postblservice;
		this.bItransportblservice = bItransportblservice;

		initColumns();
		setViewDatas();

		DefaultTableModel model = new DefaultTableModel(BItransportdatas, BItransportcolumns);
		table_BItransport = new SingleJTable(model);
		table_BItransport.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				// 当选择行时鼠标释放时才执行
				if (!event.getValueIsAdjusting()) {
					// 如果没有选中任何一行, 则返回
					if (table_BItransport.getSelectedRowCount() != 1)
						return;
					view();
				}
			}
		});

		DefaultTableModel modelselectedpost = new DefaultTableModel(selcetedPostdatas, postcolumns);
		table_selectedPost = new UnEditedJTable(modelselectedpost);

		DefaultTableModel modelunselectedpost = new DefaultTableModel(unselcetedPostdatas, postcolumns);
		table_unselectedPost = new UnEditedJTable(modelunselectedpost);

		setTableFace();

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 348, 540, 245);
		add(scrollPane_1);
		scrollPane_1.setViewportView(table_selectedPost);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(558, 348, 536, 245);
		add(scrollPane_2);
		scrollPane_2.setViewportView(table_unselectedPost);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 42, 1088, 154);
		add(scrollPane);
		scrollPane.setViewportView(table_BItransport);

		JLabel label = new JLabel("\u67E5\u8BE2\u88C5\u8F66\u4FE1\u606F");
		label.setBounds(612, 12, 83, 18);
		add(label);

		textField_query = new JTextField();
		textField_query.setBounds(687, 6, 208, 30);
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		textField_query.setColumns(10);
		add(textField_query);

		JButton button_query = new JButton("查询");
		button_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});
		button_query.setBounds(899, 6, 90, 30);
		add(button_query);

		JLabel label_8 = new JLabel("\u5DF2\u9009\u62E9\u7684\u8BA2\u5355");
		label_8.setBounds(238, 326, 105, 18);
		add(label_8);

		JLabel label_9 = new JLabel("\u672A\u9009\u62E9\u7684\u8BA2\u5355");
		label_9.setBounds(782, 326, 113, 18);
		add(label_9);

		JButton button_removePost = new JButton("\u79FB\u9664");
		button_removePost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePost();
			}
		});
		button_removePost.setBounds(222, 597, 90, 30);
		add(button_removePost);

		JButton button_addPost = new JButton("\u6DFB\u52A0");
		button_addPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPost();
			}
		});
		button_addPost.setBounds(786, 597, 90, 30);
		add(button_addPost);

		JLabel label_10 = new JLabel("\u88C5\u8F66\u5355\u5217\u8868");
		label_10.setBounds(12, 12, 138, 18);
		add(label_10);

		JButton button_showAll = new JButton("\u663E\u793A\u6240\u6709");
		button_showAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getLoglabel().setText("显示所有");
				setViewDatas();
				refreshTable();
			}
		});
		button_showAll.setBounds(1000, 6, 94, 30);
		add(button_showAll);

		JLabel label_1 = new JLabel("\u8F66\u8F86\u7F16\u53F7");
		label_1.setBounds(16, 256, 48, 18);
		add(label_1);

		comboBox_vehicle = new JComboBox();
		comboBox_vehicle.setBounds(70, 251, 156, 28);
		add(comboBox_vehicle);

		comboBox_driver = new JComboBox();
		comboBox_driver.setBounds(298, 251, 138, 28);
		add(comboBox_driver);

		comboBox_startdepartment = new JComboBox();
		comboBox_startdepartment.setBounds(58, 209, 261, 28);
		add(comboBox_startdepartment);

		comboBox_enddepartment = new JComboBox();

		comboBox_enddepartment.setBounds(379, 209, 280, 28);
		add(comboBox_enddepartment);

		addDepartment();
		addVehicle();
		addDriver();

		JLabel label_2 = new JLabel("\u53F8\u673A\u7F16\u53F7");
		label_2.setBounds(238, 256, 48, 18);
		add(label_2);

		JLabel label_3 = new JLabel("\u8FD0\u8F93\u8D39\u7528");
		label_3.setBounds(448, 256, 48, 18);
		add(label_3);

		textField_fare = new JTextField();
		textField_fare.setColumns(10);
		textField_fare.setBounds(508, 250, 122, 30);
		add(textField_fare);

		JLabel label_4 = new JLabel("\u5907\u6CE8\u4FE1\u606F");
		label_4.setBounds(16, 298, 48, 18);
		add(label_4);

		textField_info = new JTextField();
		textField_info.setColumns(10);
		textField_info.setBounds(76, 292, 1018, 30);
		add(textField_info);

		JLabel label_5 = new JLabel("\u51FA\u53D1\u5730");
		label_5.setBounds(16, 214, 36, 18);
		add(label_5);

		JLabel label_6 = new JLabel("\u76EE\u7684\u5730");
		label_6.setBounds(331, 214, 36, 18);
		add(label_6);

		JLabel label_7 = new JLabel("\u51FA\u53D1\u65F6\u95F4");
		label_7.setBounds(671, 214, 48, 18);
		add(label_7);

		textField_datetime = new DateTextField("yyyy-MM-dd HH:mm:ss");
		textField_datetime.setColumns(10);
		textField_datetime.setBounds(731, 208, 234, 30);
		add(textField_datetime);

		JButton button_choosetime = new JButton("\u9009\u62E9\u65F6\u95F4");
		button_choosetime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatePicker dp = new DatePicker(textField_datetime, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_datetime.getText());

				dp.setSelectedDate(selectedDate);
				dp.start(textField_datetime);
			}
		});
		button_choosetime.setBounds(971, 208, 76, 30);
		add(button_choosetime);

		JButton button_save = new JButton("\u4FDD\u5B58");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		button_save.setBounds(391, 628, 90, 30);
		add(button_save);

		JButton button_clear = new JButton("\u6E05\u7A7A");
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		button_clear.setBounds(511, 628, 90, 30);
		add(button_clear);

		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		button_delete.setBounds(633, 628, 90, 30);
		add(button_delete);
	}

	private void view() {
		int id = getSelectId(table_BItransport);
		BItransport bItransport = bItransportblservice.getById(id);
		MainFrame.getLoglabel().setText("查看装车单");
		this.textField_BItransportId.setText(id + "");
		this.comboBox_startdepartment.setSelectedItem(makeDepartment(bItransport.getStart_department()));
		this.comboBox_enddepartment.setSelectedItem(makeDepartment(bItransport.getEnd_department()));
		this.textField_datetime.setText(DateUtil.getStringByTimeStamp(bItransport.getBItransport_datetime()));
		this.comboBox_vehicle.setSelectedItem(makeVehicle(bItransport.getVehicle()));
		this.comboBox_driver.setSelectedItem(makeDriver(bItransport.getDriver()));
		this.textField_fare.setText(bItransport.getBItransport_fare() + "");
		this.textField_info.setText(bItransport.getBItransport_info());

		setSelectedPostDatas();
		refreshTable();
	}

	private void query() {
		String text = this.textField_query.getText().trim();
		Vector<BItransport> bItransports;
		Vector<Vector> datas;

		MainFrame.getLoglabel().setText("查询装车单(查询条件:" + text + ")");
		logger.info("查询装车单(查询条件:" + text + ")");

		Vector<Department> departments = bItransportblservice.getBusiness();
		departments.addAll(bItransportblservice.getCenter());
		for (Department department : departments) {
			if (department.getDepartment_name().equals(text)) {
				int departmentId = department.getId();
				String sql = "select * from bitransport where ( start_department_id_fk = ? or end_department_id_fk = ? ) and flag = 2 ";
				Object[] values = { departmentId, departmentId };
				bItransports = bItransportblservice.getBySql(sql, values);
				datas = changeBItransportDatas(bItransports);
				this.BItransportdatas = datas;
				refreshTable();
				return;
			}

		}

		if (isDatetime(text)) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = dateFormat.parse(text);
			} catch (Exception e) {
				showWarn("请输入yyyy-MM-dd的格式日期");
				return;
			}
			Date nextDate = DateUtil.getNextDateByDate(date);
			String today = DateUtil.getStringByDateTime(date);
			String tomorrow = DateUtil.getStringByDateTime(nextDate);

			String sql = " select * from bitransport where bitransport_datetime > ?  and bitransport_datetime < ? and flag = 2 ";
			Object[] values = { today, tomorrow };
			bItransports = bItransportblservice.getBySql(sql, values);
			datas = changeBItransportDatas(bItransports);
			this.BItransportdatas = datas;
			refreshTable();
		} else {
			this.BItransportdatas = null;
			refreshTable();
		}

	}

	private void save() {
		if (this.textField_BItransportId.getText().equals("")) {
			add();
		} else {
			update();
		}
	}

	private void add() {
		if (isTextExist() && isTextValidate()) {

			BItransport bItransport = getBItransport();
			int result = bItransportblservice.add(bItransport);

			if (result == 0) {
				MainFrame.getLoglabel().setText("新增装车信息失败");
				logger.error("新增装车信息失败");
			} else {
				int[] AllSelectedPostId = getAllIds(table_selectedPost);
				for (int i = 0; i < AllSelectedPostId.length; i++) {

					String sql = "update post set ibtransport_id_fk = ? where id = ? ";
					Object[] obs = { result, AllSelectedPostId[i] };
					postblservice.update_fk_id(sql, obs);

					Post post = postblservice.getById(AllSelectedPostId[i]);
					if (post.getStart_stock_id_fk() != 0) {

						int start_id_fk = post.getStart_stock_id_fk();
						String sql1 = "update stock set isEmpty = 0 where id = ? ";
						Object[] obs1 = { start_id_fk };
						postblservice.update_fk_id(sql1, obs1);
					}
					if (post.getEnd_stock_id_fk() != 0) {

						int end_id_fk = post.getEnd_stock_id_fk();
						String sql1 = "update stock set isEmpty = 0 where id = ? ";
						Object[] obs1 = { end_id_fk };
						postblservice.update_fk_id(sql1, obs1);
					}

				}
				// 重新读取数据
				setViewDatas();
				setSelectedPostDatas();
				// 刷新列表, 清空表单
				clear();
				// 通知添加结果
				MainFrame.getLoglabel().setText("新增装车信息成功");
				logger.info("新增装车信息成功");
			}
		}
	}

	private void update() {
		BItransport bItransport = getBItransport();
		int BItransportId = Integer.parseInt(this.textField_BItransportId.getText());
		bItransport.setId(BItransportId);
		boolean result = bItransportblservice.update(bItransport);

		if (result) {
			// 先删除，再添加
			Vector<Post> selectedposts = postblservice.getListByCondition("BItransport_id_fk", BItransportId);
			for (Post post : selectedposts) {
				String sql = "update post set ibtransport_id_fk = null where id = ? ";
				Object[] obs = { post.getId() };
				postblservice.update_fk_id(sql, obs);
			}

			int[] AllSelectedPostId = getAllIds(table_selectedPost);
			for (int i = 0; i < AllSelectedPostId.length; i++) {

				String sql = "update post set ibtransport_id_fk = ? where id = ? ";
				Object[] obs = { BItransportId, AllSelectedPostId[i] };
				postblservice.update_fk_id(sql, obs);
			}
			// 重新读取数据
			setViewDatas();
			setSelectedPostDatas();
			// 刷新列表, 清空表单
			clear();
			// 通知更新结果
			MainFrame.getLoglabel().setText("更新装车信息成功");
			logger.info("更新装车信息成功");
		} else {
			MainFrame.getLoglabel().setText("更新装车信息失败");
			logger.error("更新装车信息失败");
		}
	}

	private void removePost() {
		int[] selectedId = getSelectIds(table_selectedPost);
		for (int i = 0; i < selectedId.length; i++) {
			String sql = "update post set ibtransport_id_fk = null where id = ? ";
			Object[] obs = { selectedId[i] };
			postblservice.update_fk_id(sql, obs);
		}
		// 重新读取数据
		setViewDatas();
		setSelectedPostDatas();
		refreshTable();
	}

	private void addPost() {
		if (!this.textField_BItransportId.getText().equals("")) {
			int BItransportId = Integer.parseInt(this.textField_BItransportId.getText());
			int[] unselectedId = getSelectIds(table_unselectedPost);
			for (int i = 0; i < unselectedId.length; i++) {
				String sql = "update post set ibtransport_id_fk = ? where id = ? ";
				Object[] obs = { BItransportId, unselectedId[i] };
				postblservice.update_fk_id(sql, obs);
			}
			// 重新读取数据
			setViewDatas();
			setSelectedPostDatas();
			refreshTable();
		}
	}

	private void delete() {
		if (!this.textField_BItransportId.getText().equals("")) {
			int bItransportId = Integer.parseInt(this.textField_BItransportId.getText());
			boolean result = bItransportblservice.delete(bItransportId);
			// 重新读取数据
			setViewDatas();
			setSelectedPostDatas();
			// 刷新列表, 清空表单
			clear();
			if (result) {

				MainFrame.getLoglabel().setText("刪除装车信息成功");
				logger.info("刪除装车信息成功");
			} else {
				MainFrame.getLoglabel().setText("刪除装车信息失败");
				logger.error("刪除装车信息失败");
			}
		} else {
			showWarn("未选择装车单");
		}
	}

	/**
	 * 初始化table列名
	 */
	public void initColumns() {
		this.BItransportcolumns = new Vector();
		this.BItransportcolumns.add("id");
		this.BItransportcolumns.add("出发地");
		this.BItransportcolumns.add("目的地");
		this.BItransportcolumns.add("出发时间");
		this.BItransportcolumns.add("司机姓名");
		this.BItransportcolumns.add("车辆编号");
		this.BItransportcolumns.add("运费");

		this.postcolumns = new Vector();
		this.postcolumns.add("id");
		this.postcolumns.add("订单条形码号");
		this.postcolumns.add("出发地");
		this.postcolumns.add("目的地");
		this.postcolumns.add("区号");
		this.postcolumns.add("排号");
		this.postcolumns.add("架号");
		this.postcolumns.add("位号");

	}

	public void setTableFace() {
		this.table_BItransport.getColumn("id").setMinWidth(-1);
		this.table_BItransport.getColumn("id").setMaxWidth(-1);
		this.table_BItransport.setRowHeight(30);

		this.table_selectedPost.getColumn("id").setMinWidth(-1);
		this.table_selectedPost.getColumn("id").setMaxWidth(-1);
		this.table_selectedPost.getColumn("订单条形码号").setMinWidth(90);
		this.table_selectedPost.getColumn("订单条形码号").setMaxWidth(90);
		this.table_selectedPost.getColumn("区号").setMinWidth(40);
		this.table_selectedPost.getColumn("区号").setMaxWidth(40);
		this.table_selectedPost.getColumn("排号").setMinWidth(40);
		this.table_selectedPost.getColumn("排号").setMaxWidth(40);
		this.table_selectedPost.getColumn("架号").setMinWidth(40);
		this.table_selectedPost.getColumn("架号").setMaxWidth(40);
		this.table_selectedPost.getColumn("位号").setMinWidth(40);
		this.table_selectedPost.getColumn("位号").setMaxWidth(40);
		this.table_selectedPost.setRowHeight(30);

		this.table_unselectedPost.getColumn("id").setMinWidth(-1);
		this.table_unselectedPost.getColumn("id").setMaxWidth(-1);
		this.table_unselectedPost.getColumn("订单条形码号").setMinWidth(90);
		this.table_unselectedPost.getColumn("订单条形码号").setMaxWidth(90);
		this.table_unselectedPost.getColumn("区号").setMinWidth(40);
		this.table_unselectedPost.getColumn("区号").setMaxWidth(40);
		this.table_unselectedPost.getColumn("排号").setMinWidth(40);
		this.table_unselectedPost.getColumn("排号").setMaxWidth(40);
		this.table_unselectedPost.getColumn("架号").setMinWidth(40);
		this.table_unselectedPost.getColumn("架号").setMaxWidth(40);
		this.table_unselectedPost.getColumn("位号").setMinWidth(40);
		this.table_unselectedPost.getColumn("位号").setMaxWidth(40);
		this.table_unselectedPost.setRowHeight(30);
	}

	public void setViewDatas() {
		int departmentId = MainFrame.getInstance().getUser().getDepartment().getId();
		String sql = " select * from bitransport where flag = ? and start_department_id_fk = ? ";
		Object[] values = { 2, departmentId };
		Vector<BItransport> bItransports = bItransportblservice.getBySql(sql, values);
		// 转换显示格式
		Vector<Vector> bItransportdatas = changeBItransportDatas(bItransports);
		this.BItransportdatas = bItransportdatas;
		String postsql = " SELECT * FROM post WHERE ( start_stock_id_fk is not NULL and IBtransport_id_fk is null and IItransport_id_fk is null) or ( end_stock_id_fk is not NULL and IBtransport_id_fk is null)";
		Vector<Post> unselectedposts = postblservice.getListBySql(postsql);
		Vector<Vector> unselectedpostdatas = changePostDatas(unselectedposts);
		this.unselcetedPostdatas = unselectedpostdatas;

	}

	public void setSelectedPostDatas() {

		if (!this.textField_BItransportId.getText().equals("")) {
			int IBtransportId = Integer.parseInt(this.textField_BItransportId.getText());
			Vector<Post> selectedposts = postblservice.getListByCondition("IBtransport_id_fk", IBtransportId);
			Vector<Vector> selectedpostdatas = changePostDatas(selectedposts);
			this.selcetedPostdatas = selectedpostdatas;
		}

	}

	private Vector<Vector> changeBItransportDatas(Vector<BItransport> bItransports) {
		Vector<Vector> view = new Vector<Vector>();
		for (BItransport bItransport : bItransports) {
			Vector v = new Vector();
			v.add(bItransport.getId());
			v.add(bItransport.getStart_department().getDepartment_name());
			v.add(bItransport.getEnd_department().getDepartment_name());
			v.add(DateUtil.getStringByDateTime(bItransport.getBItransport_datetime()));
			v.add(bItransport.getDriver().getDriver_name());
			v.add(bItransport.getVehicle().getVehicle_num());
			v.add(bItransport.getBItransport_fare());

			view.add(v);
		}
		return view;
	}

	/**
	 * @param posts
	 *            寄件单集合
	 * @return table数据
	 */
	private Vector<Vector> changePostDatas(Vector<Post> posts) {
		Vector<Vector> view = new Vector<Vector>();
		for (Post post : posts) {
			Vector v = new Vector();

			v.add(post.getId());
			v.add(post.getBarcode());
			v.add(post.getSender().getSender_address());
			v.add(post.getReceiver().getReceiver_address());
			v.add(post.getStart_stock().getArea_num());
			v.add(post.getStart_stock().getRow_num());
			v.add(post.getStart_stock().getShelf_num());
			v.add(post.getStart_stock().getPosition_num());

			view.add(v);
		}
		return view;
	}

	public void clear() {
		MainFrame.getLoglabel().setText("清空");
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		this.selcetedPostdatas = null;
		this.textField_BItransportId.setText("");
		this.textField_datetime.setText("");
		this.textField_fare.setText("");
		this.textField_info.setText("");
		refreshTable();
	}

	public void initData() {
		if (null != this.table_BItransport) {
			DefaultTableModel tableModel = (DefaultTableModel) this.table_BItransport.getModel();
			// 将数据设入表格Model中
			tableModel.setDataVector(this.BItransportdatas, this.BItransportcolumns);
		}

		if (null != this.table_selectedPost) {
			DefaultTableModel tableModel2 = (DefaultTableModel) this.table_selectedPost.getModel();
			// 将数据设入表格Model中
			tableModel2.setDataVector(this.selcetedPostdatas, this.postcolumns);
		}

		if (null != this.table_unselectedPost) {
			DefaultTableModel tableModel3 = (DefaultTableModel) this.table_unselectedPost.getModel();
			// 将数据设入表格Model中
			tableModel3.setDataVector(this.unselcetedPostdatas, this.postcolumns);
		}

		// 设置表格样式
		setTableFace();
	}

	/*
	 * 刷新列表的方法
	 */
	public void refreshTable() {
		initData();
		this.table_BItransport.repaint();
		this.table_selectedPost.repaint();
		this.table_unselectedPost.repaint();
	}

	public int getSelectId(JTable table) {
		int row = table.getSelectedRow();
		int column = table.getColumn("id").getModelIndex();
		int id = (int) table.getValueAt(row, column);
		return id;
	}

	public int[] getSelectIds(JTable table) {

		int[] rows = table.getSelectedRows();
		int column = table.getColumn("id").getModelIndex();
		int[] resultId = new int[rows.length];
		for (int i = 0; i < rows.length; i++) {
			resultId[i] = (int) table.getValueAt(rows[i], column);
		}
		return resultId;
	}

	public int[] getAllIds(JTable table) {
		int rowcount = table.getRowCount();
		int[] rows = new int[rowcount];
		for (int i = 0; i < rowcount; i++) {
			rows[i] = i;
		}

		int column = table.getColumn("id").getModelIndex();
		int[] resultId = new int[rows.length];
		for (int i = 0; i < rows.length; i++) {
			resultId[i] = (int) table.getValueAt(rows[i], column);
		}
		return resultId;
	}

	private BItransport getBItransport() {
		Department startDepartment = (Department) this.comboBox_startdepartment.getSelectedItem();
		Department endDepartment = (Department) this.comboBox_enddepartment.getSelectedItem();
		Vehicle vehicle = (Vehicle) this.comboBox_vehicle.getSelectedItem();
		Driver driver = (Driver) this.comboBox_driver.getSelectedItem();
		return new BItransport(2, startDepartment.getId(), endDepartment.getId(),
				new Timestamp(DateUtil.getDateByDateTimeString(this.textField_datetime.getText()).getTime()),
				vehicle.getId(), driver.getId(), Double.parseDouble(this.textField_fare.getText()),
				this.textField_info.getText());

	}

	private boolean isTextExist() {

		if (this.textField_datetime.getText().equals("")) {
			showWarn("未输入装车时间");
			return false;
		}
		if (this.textField_fare.getText().equals("")) {
			showWarn("未输入运输费用");
			return false;
		}

		return true;
	}

	private boolean isTextValidate() {
		if (!isNumber(this.textField_fare.getText())) {
			showWarn("运输费用格式不正确");
			return false;
		}
		if (!isDatetime(this.textField_datetime.getText())) {
			showWarn("装车时间格式不正确");
			return false;
		}
		return true;
	}

	/**
	 * 检查字符串是否是日期
	 * 
	 * @param string
	 * @return
	 */
	private boolean isDatetime(String string) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.parse(string);
			return true;
		} catch (Exception e) {

			return false;
		}
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

	private void addDepartment() {

		Vector<Department> businesses = bItransportblservice.getBusiness();
		Department myDepartment = MainFrame.getInstance().getUser().getDepartment();

		Department itemcenter = makeDepartment(myDepartment);
		this.comboBox_startdepartment.addItem(itemcenter);

		for (Department business : businesses) {
			Department itembusiness = makeDepartment(business);
			this.comboBox_enddepartment.addItem(itembusiness);
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

	private void addVehicle() {

		Vector<Vehicle> vehicles = bItransportblservice
				.getVehicle(((Department) this.comboBox_enddepartment.getSelectedItem()).getId());

		for (Vehicle vehicle : vehicles) {
			Vehicle itemvehicle = makeVehicle(vehicle);
			this.comboBox_vehicle.addItem(itemvehicle);
		}

	}

	private Vehicle makeVehicle(Vehicle source) {
		Vehicle vehicle = new Vehicle() {

			public String toString() {
				return source.getVehicle_num();
			}

			public boolean equals(Object obj) {
				if (obj instanceof Vehicle) {
					Vehicle v = (Vehicle) obj;
					if (getId() == v.getId())
						return true;
				}
				return false;
			}
		};
		vehicle.setId(source.getId());
		return vehicle;
	}

	private void addDriver() {

		Vector<Driver> drivers = bItransportblservice
				.getDriver(((Department) this.comboBox_enddepartment.getSelectedItem()).getId());

		for (Driver driver : drivers) {
			Driver itemdriver = makeDriver(driver);
			this.comboBox_driver.addItem(itemdriver);
		}

	}

	private Driver makeDriver(Driver source) {
		Driver driver = new Driver() {

			public String toString() {
				return source.getDriver_name();
			}

			public boolean equals(Object obj) {
				if (obj instanceof Driver) {
					Driver d = (Driver) obj;
					if (getId() == d.getId())
						return true;
				}
				return false;
			}
		};
		driver.setId(source.getId());
		return driver;
	}

	private Vector<String> getAllTips() {
		Vector<BItransport> bItransports = bItransportblservice.getAll();
		tips.clear();
		for (BItransport bItransport : bItransports) {
			tips.add(bItransport.getStart_department().getDepartment_name());
			tips.add(bItransport.getEnd_department().getDepartment_name());
			tips.add(DateUtil.getStringByDate(bItransport.getBItransport_datetime()));
		}
		return tips;

	}

	// 显示警告
	protected int showWarn(String message) {
		return JOptionPane.showConfirmDialog(this, message, "警告", JOptionPane.OK_CANCEL_OPTION);
	}
}
