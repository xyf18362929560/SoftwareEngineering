package nju.express.ui.finance;

import java.util.Date;
import java.util.Locale;
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

import com.qt.datapicker.DatePicker;

import nju.express.blservice.Driverblservice;
import nju.express.blservice.Paymentblservice;
import nju.express.po.Driver;
import nju.express.po.IItransport;
import nju.express.po.Payment;
import nju.express.po.Post;
import nju.express.po.User;
import nju.express.ui.MainFrame;
import nju.express.ui.utils.CommonPanel;
import nju.express.ui.utils.SingleJTable;
import nju.express.util.AutoTextFieldUtil;
import nju.express.util.DateTextField;
import nju.express.util.DateUtil;
import nju.express.util.LoggerUtil;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class PaymentPanel extends CommonPanel {

	static Logger logger = LoggerUtil.getLogger();
	Paymentblservice paymentblservice;
	// Jtable的列名
	Vector columns;
	
	private static final long serialVersionUID = 5682515611581959821L;
	private JTextField textField_paymentId = new JTextField("");
	private DateTextField textField_paymentdatetime;
	private JTextField textField_paymentamount;
	private DateTextField textField_query;
	private JTable table;
	private JComboBox comboBox_paymentType;
	private JComboBox comboBox_user;

	/**
	 * Create the panel.
	 */
	public PaymentPanel(Paymentblservice paymentblservice) {
		this.setSize(1100, 664);
		setLayout(null);

		this.paymentblservice = paymentblservice;

		initColumns();
		setViewDatas();

		JLabel label = new JLabel("\u4ED8\u6B3E\u7C7B\u578B");
		label.setBounds(393, 370, 55, 18);
		add(label);

		String[] paymentType = { Payment.SALARY, Payment.FREIGHT, Payment.RENT, Payment.BOMUS };
		comboBox_paymentType = new JComboBox(paymentType);
		comboBox_paymentType.setBounds(477, 365, 176, 28);
		add(comboBox_paymentType);

		JLabel label_1 = new JLabel("\u4ED8\u6B3E\u65F6\u95F4");
		label_1.setBounds(393, 469, 55, 18);
		add(label_1);

		textField_paymentdatetime = new DateTextField("yyyy-MM-dd HH:mm:ss");
		textField_paymentdatetime.setBounds(477, 463, 176, 30);
		add(textField_paymentdatetime);
		textField_paymentdatetime.setColumns(10);

		JButton button = new JButton("\u9009\u62E9\u65F6\u95F4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatePicker dp = new DatePicker(textField_paymentdatetime, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_paymentdatetime.getText());

				dp.setSelectedDate(selectedDate);
				dp.start(textField_paymentdatetime);
			}
		});
		button.setBounds(662, 463, 76, 30);
		add(button);

		JLabel label_2 = new JLabel("\u4ED8\u6B3E\u91D1\u989D");
		label_2.setBounds(393, 420, 55, 18);
		add(label_2);

		textField_paymentamount = new JTextField();
		textField_paymentamount.setBounds(477, 414, 176, 30);
		add(textField_paymentamount);
		textField_paymentamount.setColumns(10);

		JLabel label_3 = new JLabel("\u4ED8\u6B3E\u4EBA");
		label_3.setBounds(393, 521, 55, 18);
		add(label_3);

		comboBox_user = new JComboBox();
		addFinancer();
		comboBox_user.setBounds(477, 516, 128, 28);
		add(comboBox_user);

		JLabel label_4 = new JLabel("\u67E5\u8BE2");
		label_4.setBounds(373, 12, 55, 18);
		add(label_4);

		textField_query = new DateTextField("yyyy-MM-dd");
		textField_query.setBounds(415, 6, 251, 30);
		add(textField_query);
		textField_query.setColumns(10);

		JButton button_1 = new JButton("\u9009\u62E9\u65E5\u671F");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatePicker dp = new DatePicker(textField_query, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_query.getText());

				dp.setSelectedDate(selectedDate);
				dp.start(textField_query);
			}
		});
		button_1.setBounds(678, 6, 76, 30);
		add(button_1);

		JButton button_2 = new JButton("\u67E5\u8BE2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});
		button_2.setBounds(761, 6, 90, 30);
		add(button_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 42, 1088, 297);
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

		JButton button_3 = new JButton("\u4FDD\u5B58");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		button_3.setBounds(421, 596, 90, 30);
		add(button_3);

		JButton button_4 = new JButton("\u6E05\u7A7A");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		button_4.setBounds(521, 596, 90, 30);
		add(button_4);

		JButton button_5 = new JButton("\u5220\u9664");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		button_5.setBounds(624, 596, 90, 30);
		add(button_5);

		JButton button_6 = new JButton("\u663E\u793A\u6240\u6709");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getLoglabel().setText("显示所有");
				setViewDatas();
				refreshTable();
			}
		});
		button_6.setBounds(853, 6, 90, 30);
		add(button_6);
	}

	private void view() {
		int id = getSelectId(getJTable());
		Payment payment = paymentblservice.getById(id);
		MainFrame.getLoglabel().setText("查看付款单");
		this.textField_paymentId.setText(id + "");
		this.comboBox_paymentType.setSelectedItem(payment.getPayment_type());
		this.textField_paymentdatetime.setText(DateUtil.getStringByDateTime(payment.getPayment_datetime()));
		this.textField_paymentamount.setText(payment.getPayment_amount() + "");
		this.comboBox_user.setSelectedItem(makeUser(payment.getFinance_user()));

	}

	private void query() {
		String text = this.textField_query.getText().trim();
		Vector<Payment> payments;
		Vector<Vector> datas;
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

		String sql = " select * from payment where payment_datetime > ?  and payment_datetime < ? ";
		Object[] values = { today, tomorrow };
		payments = paymentblservice.getBySql(sql, values);
		datas = changeDatas(payments);
		this.datas = datas;
		refreshTable();

	}

	private void save() {
		if (this.textField_paymentId.getText().equals("")) {
			add();
		} else {
			update();
		}
	}

	private void add() {
		if (isTextExist() && isTextValidate()) {

			Payment payment = getPayment();
			int result = paymentblservice.add(payment);
			// 重新读取数据
			setViewDatas();
			// 刷新列表, 清空表单
			clear();
			// 通知添加结果
			if (result == 0) {
				MainFrame.getLoglabel().setText("新增付款信息失败");
				logger.error("新增付款信息失败");
			} else {
				MainFrame.getLoglabel().setText("新增付款信息成功");
				logger.info("新增付款信息成功");
			}
		}
	}

	private void update() {
		Payment payment = getPayment();
		int paymentId = Integer.parseInt(this.textField_paymentId.getText());
		payment.setId(paymentId);
		boolean result = paymentblservice.update(payment);
		// 重新读取数据
		setViewDatas();
		// 刷新列表, 清空表单
		clear();
		// 通知更新结果
		if (result) {
			MainFrame.getLoglabel().setText("更新付款信息成功");
			logger.info("更新付款信息成功");
		} else {
			MainFrame.getLoglabel().setText("更新付款信息失败");
			logger.error("更新付款信息失败");
		}
	}

	private void delete() {
		if (!this.textField_paymentId.getText().equals("")) {
			int paymentId = Integer.parseInt(this.textField_paymentId.getText());
			boolean result = paymentblservice.delete(paymentId);
			// 重新读取数据
			setViewDatas();
			// 刷新列表, 清空表单
			clear();
			if (result) {

				MainFrame.getLoglabel().setText("刪除付款信息成功");
				logger.info("刪除付款信息成功");
			} else {
				MainFrame.getLoglabel().setText("刪除付款信息失败");
				logger.error("刪除付款信息失败");
			}
		} else {
			showWarn("未选择付款单");
		}
	}

	private Payment getPayment() {
		User user = (User) this.comboBox_user.getSelectedItem();
		return new Payment(
				new Timestamp(DateUtil.getDateByDateTimeString(this.textField_paymentdatetime.getText()).getTime()),
				Double.parseDouble(this.textField_paymentamount.getText()), user.getId(),
				this.comboBox_paymentType.getSelectedItem().toString());
	}

	private boolean isTextExist() {
		if (this.textField_paymentdatetime.getText().equals("")) {
			showWarn("未输入付款单时间");
			return false;
		}

		if (this.textField_paymentamount.getText().equals("")) {
			showWarn("未输入付款单金额");
			return false;
		}
		return true;
	}

	private boolean isTextValidate() {

		if (!isNumber(this.textField_paymentamount.getText())) {
			showWarn("请输入正确的金额");
			return false;
		} else {
			if (Double.parseDouble(this.textField_paymentamount.getText()) < 0) {
				showWarn("金额不能小于0");
				return false;
			}
		}
		return true;
	}

	private boolean isNumber(String string) {

		try {
			Double.parseDouble(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 初始化table列名
	 */
	public void initColumns() {
		this.columns = new Vector();

		this.columns.add("id");
		this.columns.add("付款单类型");
		this.columns.add("付款金额");
		this.columns.add("付款时间");
		this.columns.add("付款人");

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
		Vector<Payment> payments = paymentblservice.getAll();
		// 转换显示格式
		Vector<Vector> datas = changeDatas(payments);
		// 调用父类方法设置结果集合
		setDatas(datas);

	}

	private Vector<Vector> changeDatas(Vector<Payment> payments) {
		Vector<Vector> view = new Vector<Vector>();
		for (Payment payment : payments) {
			Vector v = new Vector();
			v.add(payment.getId());
			v.add(payment.getPayment_type());
			v.add(payment.getPayment_amount());
			v.add(DateUtil.getStringByDateTime(payment.getPayment_datetime()));
			v.add(payment.getFinance_user().getUser_name());
			view.add(v);
		}
		return view;
	}

	@Override
	public void clear() {
		MainFrame.getLoglabel().setText("清空");
		refreshTable();

		this.textField_paymentId.setText("");
		this.textField_paymentamount.setText("");
		this.textField_paymentdatetime.setText("");

	}

	/**
	 * 向下拉框添加财务人员
	 */
	private void addFinancer() {
		User user = MainFrame.getInstance().getUser();

		this.comboBox_user.addItem(makeUser(user));

	}

	/**
	 * 重写User的toString()和equals()方法，然后才能向comboBox里添加
	 * 
	 * @param source
	 * @return
	 */
	private User makeUser(final User source) {
		User user = new User() {

			public String toString() {
				return source.getUser_name();
			}

			public boolean equals(Object obj) {
				if (obj instanceof User) {
					User u = (User) obj;
					if (getId() == u.getId())
						return true;
				}
				return false;
			}
		};
		user.setId(source.getId());
		return user;
	}
}
