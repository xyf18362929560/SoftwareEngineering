package nju.express.ui.finance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.qt.datapicker.DatePicker;

import nju.express.blservice.Accountblservice;
import nju.express.po.Account;
import nju.express.ui.MainFrame;
import nju.express.ui.utils.CommonPanel;
import nju.express.ui.utils.SingleJTable;
import nju.express.util.DateTextField;
import nju.express.util.DateUtil;
import nju.express.util.LoggerUtil;

public class AccountPanel extends CommonPanel {

	static Logger logger = LoggerUtil.getLogger();
	Accountblservice accountblservice;
	// Jtable������
	Vector columns;
	private static final long serialVersionUID = 4683114088924729514L;
	private JTextField textField_accountId = new JTextField("");
	private JTextField textField_query;
	private JTable table;
	private JTextField textField_accountname;
	private JTextField textField_amount;
	private DateTextField textField_datetime;

	/**
	 * Create the panel.
	 */
	public AccountPanel(Accountblservice accountblservice) {
		this.setSize(1100, 664);
		setLayout(null);

		this.accountblservice = accountblservice;

		initColumns();
		setViewDatas();

		textField_query = new JTextField();
		textField_query.setBounds(465, 13, 205, 30);
		add(textField_query);
		textField_query.setColumns(10);

		JButton button_query = new JButton("\u67E5\u8BE2");
		button_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});
		button_query.setBounds(675, 13, 90, 30);
		add(button_query);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 62, 1076, 268);
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

		JLabel label = new JLabel("\u8D26\u6237\u540D\u79F0\uFF1A");
		label.setBounds(449, 342, 72, 18);
		add(label);

		textField_accountname = new JTextField();
		textField_accountname.setBounds(530, 336, 122, 30);
		add(textField_accountname);
		textField_accountname.setColumns(10);

		textField_amount = new JTextField();
		textField_amount.setEditable(false);
		textField_amount.setBounds(530, 383, 122, 30);
		add(textField_amount);
		textField_amount.setColumns(10);

		JLabel label_1 = new JLabel("\u8D26\u6237\u91D1\u989D\uFF1A");
		label_1.setBounds(449, 389, 74, 18);
		add(label_1);

		JLabel label_2 = new JLabel("\u5EFA\u7ACB\u65F6\u95F4\uFF1A");
		label_2.setBounds(449, 434, 72, 18);
		add(label_2);

		textField_datetime = new DateTextField("yyyy-MM-dd HH:mm:ss");
		textField_datetime.setBounds(530, 428, 140, 30);
		add(textField_datetime);
		textField_datetime.setColumns(10);

		JButton button_save = new JButton("\u4FDD\u5B58");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		button_save.setBounds(413, 497, 90, 30);
		add(button_save);

		JButton button_clear = new JButton("\u6E05\u7A7A");
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		button_clear.setBounds(513, 497, 90, 30);
		add(button_clear);

		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		button_delete.setBounds(616, 497, 90, 30);
		add(button_delete);

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
		button_choosetime.setBounds(675, 428, 90, 30);
		add(button_choosetime);

		JLabel label_3 = new JLabel("\u67E5\u8BE2\u8D26\u6237\u540D\u79F0");
		label_3.setBounds(379, 19, 90, 18);
		add(label_3);

		JButton button_showAll = new JButton("\u663E\u793A\u6240\u6709");
		button_showAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getLoglabel().setText("��ʾ����");
				setViewDatas();
				refreshTable();
			}
		});
		button_showAll.setBounds(777, 13, 90, 30);
		add(button_showAll);
	}

	private void view() {
		int id = getSelectId(getJTable());
		Account account = accountblservice.getById(id);
		MainFrame.getLoglabel().setText("�鿴�˻���Ϣ");
		this.textField_accountId.setText(id + "");
		this.textField_accountname.setText(account.getAccount_name());
		this.textField_amount.setText(account.getAccount_amount() + "");
		this.textField_datetime.setText(DateUtil.getStringByDateTime(account.getAccount_startdatetime()));

	}

	private void query() {
		String text = this.textField_query.getText().trim();
		Vector<Account> accounts;
		Vector<Vector> datas;

		String sql = " select * from account where account_name like '%" + text + "%' ";
		Object[] values = {};
		accounts = accountblservice.getBySql(sql, values);
		datas = changeDatas(accounts);
		this.datas = datas;
		refreshTable();

	}

	private void save() {
		if (this.textField_accountId.getText().equals("")) {
			add();
		} else {
			update();
		}
	}

	private void add() {
		if (isTextExist() && isTextValidate()) {

			Account account = getAccount();
			int result = accountblservice.add(account);
			// ���¶�ȡ����
			setViewDatas();
			// ˢ���б�, ��ձ�
			clear();
			// ֪ͨ��ӽ��
			if (result == 0) {
				MainFrame.getLoglabel().setText("�����˻���Ϣʧ��");
				logger.error("�����˻���Ϣʧ��");
			} else {
				MainFrame.getLoglabel().setText("�����˻���Ϣ�ɹ�");
				logger.info("�����˻���Ϣ�ɹ�");
			}
		}
	}

	private void update() {
		Account account = getAccount();
		int accountId = Integer.parseInt(this.textField_accountId.getText());
		account.setId(accountId);
		boolean result = accountblservice.update(account);
		// ���¶�ȡ����
		setViewDatas();
		// ˢ���б�, ��ձ�
		clear();
		// ֪ͨ���½��
		if (result) {
			MainFrame.getLoglabel().setText("�����˻���Ϣ�ɹ�");
			logger.info("�����˻���Ϣ�ɹ�");
		} else {
			MainFrame.getLoglabel().setText("�����˻���Ϣʧ��");
			logger.error("�����˻���Ϣʧ��");
		}
	}

	private void delete() {
		if (!this.textField_accountId.getText().equals("")) {
			int accountId = Integer.parseInt(this.textField_accountId.getText());
			boolean result = accountblservice.delete(accountId);
			// ���¶�ȡ����
			setViewDatas();
			// ˢ���б�, ��ձ�
			clear();
			if (result) {

				MainFrame.getLoglabel().setText("�h���˻���Ϣ�ɹ�");
				logger.info("�h���˻���Ϣ�ɹ�");
			} else {
				MainFrame.getLoglabel().setText("�h���˻���Ϣʧ��");
				logger.error("�h���˻���Ϣʧ��");
			}
		} else {
			showWarn("δѡ���˻�");
		}
	}

	private Account getAccount() {
		return new Account(this.textField_accountname.getText(), Double.parseDouble(this.textField_amount.getText()),
				new Timestamp(DateUtil.getDateByDateTimeString(this.textField_datetime.getText()).getTime()));
	}

	private boolean isTextExist() {
		if (this.textField_accountname.getText().equals("")) {
			showWarn("δ�����˻�����");
			return false;
		}

		if (this.textField_amount.getText().equals("")) {
			showWarn("δ�����˻����");
			return false;
		}
		if (this.textField_datetime.getText().equals("")) {
			showWarn("δ�����˻�����ʱ��");
			return false;
		}
		return true;
	}

	private boolean isTextValidate() {

		if (!isNumber(this.textField_amount.getText())) {
			showWarn("��������ȷ�Ľ��");
			return false;
		} else {
			if (Double.parseDouble(this.textField_amount.getText()) < 0) {
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
		this.columns.add("�˻�����");
		this.columns.add("�˻����");
		this.columns.add("����ʱ��");

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
		Vector<Account> accounts = accountblservice.getAll();
		// ת����ʾ��ʽ
		Vector<Vector> datas = changeDatas(accounts);
		// ���ø��෽�����ý������
		setDatas(datas);

	}

	private Vector<Vector> changeDatas(Vector<Account> accounts) {
		Vector<Vector> view = new Vector<Vector>();
		for (Account account : accounts) {
			Vector v = new Vector();
			v.add(account.getId());
			v.add(account.getAccount_name());
			v.add(account.getAccount_amount());
			v.add(account.getAccount_startdatetime());
			view.add(v);
		}
		return view;
	}

	@Override
	public void clear() {
		MainFrame.getLoglabel().setText("���");
		refreshTable();

		this.textField_accountId.setText("");
		this.textField_accountname.setText("");
		this.textField_amount.setText("");
		this.textField_datetime.setText("");

	}
}
