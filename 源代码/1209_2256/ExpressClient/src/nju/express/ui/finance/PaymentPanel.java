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
	// Jtable������
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
				MainFrame.getLoglabel().setText("��ʾ����");
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
		MainFrame.getLoglabel().setText("�鿴���");
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
			showWarn("������yyyy-MM-dd�ĸ�ʽ����");
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
		Payment payment = getPayment();
		int paymentId = Integer.parseInt(this.textField_paymentId.getText());
		payment.setId(paymentId);
		boolean result = paymentblservice.update(payment);
		// ���¶�ȡ����
		setViewDatas();
		// ˢ���б�, ��ձ�
		clear();
		// ֪ͨ���½��
		if (result) {
			MainFrame.getLoglabel().setText("���¸�����Ϣ�ɹ�");
			logger.info("���¸�����Ϣ�ɹ�");
		} else {
			MainFrame.getLoglabel().setText("���¸�����Ϣʧ��");
			logger.error("���¸�����Ϣʧ��");
		}
	}

	private void delete() {
		if (!this.textField_paymentId.getText().equals("")) {
			int paymentId = Integer.parseInt(this.textField_paymentId.getText());
			boolean result = paymentblservice.delete(paymentId);
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
			showWarn("δѡ�񸶿");
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
			showWarn("δ���븶�ʱ��");
			return false;
		}

		if (this.textField_paymentamount.getText().equals("")) {
			showWarn("δ���븶����");
			return false;
		}
		return true;
	}

	private boolean isTextValidate() {

		if (!isNumber(this.textField_paymentamount.getText())) {
			showWarn("��������ȷ�Ľ��");
			return false;
		} else {
			if (Double.parseDouble(this.textField_paymentamount.getText()) < 0) {
				showWarn("����С��0");
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
	 * ��ʼ��table����
	 */
	public void initColumns() {
		this.columns = new Vector();

		this.columns.add("id");
		this.columns.add("�������");
		this.columns.add("������");
		this.columns.add("����ʱ��");
		this.columns.add("������");

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
		// ת����ʾ��ʽ
		Vector<Vector> datas = changeDatas(payments);
		// ���ø��෽�����ý������
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
		MainFrame.getLoglabel().setText("���");
		refreshTable();

		this.textField_paymentId.setText("");
		this.textField_paymentamount.setText("");
		this.textField_paymentdatetime.setText("");

	}

	/**
	 * ����������Ӳ�����Ա
	 */
	private void addFinancer() {
		User user = MainFrame.getInstance().getUser();

		this.comboBox_user.addItem(makeUser(user));

	}

	/**
	 * ��дUser��toString()��equals()������Ȼ�������comboBox�����
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
