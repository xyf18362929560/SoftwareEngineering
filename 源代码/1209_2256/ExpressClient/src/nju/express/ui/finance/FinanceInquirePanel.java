package nju.express.ui.finance;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.qt.datapicker.DatePicker;

import nju.express.blservice.Paymentblservice;
import nju.express.blservice.Postblservice;
import nju.express.po.IItransport;
import nju.express.po.Payment;
import nju.express.po.Post;
import nju.express.ui.MainFrame;
import nju.express.ui.utils.SingleJTable;
import nju.express.util.DateTextField;
import nju.express.util.DateUtil;
import nju.express.util.LoggerUtil;

import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class FinanceInquirePanel extends JPanel {

	static Logger logger = LoggerUtil.getLogger();
	Paymentblservice paymentblservice;
	Postblservice postblservice;
	private static final long serialVersionUID = 5089197116789669969L;
	private DateTextField textField_startdatetime;
	private DateTextField textField_enddatetime;
	private JTable Paymenttable;
	private JTable Collectiontable;

	Vector paymentcolumns;
	Vector<Vector> paymentdatas;
	Vector collectioncolumns;
	Vector<Vector> collectiondatas;
	private JLabel label_collectionSum;
	private JLabel label_paymentSum;
	private JLabel label_amount;
	private JTextField textField_paymentSum;
	private JTextField textField_collectionSum;
	private JTextField textField_amount;

	public FinanceInquirePanel(Paymentblservice paymentblservice, Postblservice postblservice) {
		this.setSize(1100, 664);
		setLayout(null);

		this.paymentblservice = paymentblservice;
		this.postblservice = postblservice;

		initColumns();

		JLabel label = new JLabel("\u8D77\u59CB\u65F6\u95F4");
		label.setBounds(218, 19, 55, 18);
		add(label);

		textField_startdatetime = new DateTextField("yyyy-MM-dd");
		textField_startdatetime.setBounds(274, 13, 178, 30);
		add(textField_startdatetime);
		textField_startdatetime.setColumns(10);

		JButton button_startdatetime = new JButton("\u9009\u62E9\u65F6\u95F4");
		button_startdatetime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatePicker dp = new DatePicker(textField_startdatetime, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_startdatetime.getText());

				dp.setSelectedDate(selectedDate);
				dp.start(textField_startdatetime);
			}
		});
		button_startdatetime.setBounds(455, 13, 90, 30);
		add(button_startdatetime);

		JLabel label_1 = new JLabel("\u7EC8\u6B62\u65F6\u95F4");
		label_1.setBounds(557, 19, 55, 18);
		add(label_1);

		textField_enddatetime = new DateTextField("yyyy-MM-dd");
		textField_enddatetime.setBounds(614, 13, 178, 30);
		add(textField_enddatetime);
		textField_enddatetime.setColumns(10);

		JButton button_enddatetime = new JButton("\u9009\u62E9\u65F6\u95F4");
		button_enddatetime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatePicker dp = new DatePicker(textField_enddatetime, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_enddatetime.getText());

				dp.setSelectedDate(selectedDate);
				dp.start(textField_enddatetime);
			}
		});
		button_enddatetime.setBounds(796, 13, 90, 30);
		add(button_enddatetime);

		JButton button_query = new JButton("\u67E5\u8BE2");
		button_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});
		button_query.setBounds(515, 56, 90, 30);
		add(button_query);

		JLabel label_2 = new JLabel("\u4ED8\u6B3E\u5355");
		label_2.setBounds(242, 120, 55, 18);
		add(label_2);

		JLabel label_3 = new JLabel("\u6536\u6B3E\u5355");
		label_3.setBounds(796, 120, 55, 18);
		add(label_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 141, 473, 295);
		add(scrollPane);

		DefaultTableModel model = new DefaultTableModel(paymentdatas, paymentcolumns);
		Paymenttable = new SingleJTable(model);
		scrollPane.setViewportView(Paymenttable);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(598, 141, 473, 295);
		add(scrollPane_1);

		DefaultTableModel model1 = new DefaultTableModel(collectiondatas, collectioncolumns);
		Collectiontable = new SingleJTable(model1);
		scrollPane_1.setViewportView(Collectiontable);

		setTableFace();

		label_paymentSum = new JLabel("\u5408\u8BA1\uFF1A");
		label_paymentSum.setBounds(146, 467, 49, 18);
		add(label_paymentSum);

		label_collectionSum = new JLabel("\u5408\u8BA1\uFF1A");
		label_collectionSum.setBounds(710, 467, 55, 18);
		add(label_collectionSum);
		
		label_amount = new JLabel("该时间段收益:");
		label_amount.setBounds(407, 535, 90, 18);
		add(label_amount);
		
		textField_paymentSum = new JTextField();
		textField_paymentSum.setEditable(false);
		textField_paymentSum.setBounds(192, 461, 122, 30);
		add(textField_paymentSum);
		textField_paymentSum.setColumns(10);
		
		textField_collectionSum = new JTextField();
		textField_collectionSum.setEditable(false);
		textField_collectionSum.setBounds(759, 461, 122, 30);
		add(textField_collectionSum);
		textField_collectionSum.setColumns(10);
		
		textField_amount = new JTextField();
		textField_amount.setEditable(false);
		textField_amount.setBounds(497, 529, 122, 30);
		add(textField_amount);
		textField_amount.setColumns(10);
	}

	private void query() {
		
		String startdatetime = this.textField_startdatetime.getText().trim();
		String enddatetime = this.textField_enddatetime.getText().trim();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate = null;
		Date enddate = null;
		try {
			startdate = dateFormat.parse(startdatetime);
			enddate = dateFormat.parse(enddatetime);
		} catch (Exception e) {
			showWarn("请输入yyyy-MM-dd的格式日期");
			return;
		}
		
		Date nextDate = DateUtil.getNextDateByDate(enddate);
		
		String start = DateUtil.getStringByDateTime(startdate);
		String end = DateUtil.getStringByDateTime(nextDate);
		MainFrame.getLoglabel().setText("查询(起始日期"+start+") "+"终止日期("+end+")");
		String sql = " select * from payment where payment_datetime > ?  and payment_datetime < ? ";
		Object[] values = { start, end };
		Vector<Payment> payments = paymentblservice.getBySql(sql, values);
		this.paymentdatas = changePaymentDatas(payments);
		
		String sql1 = " select * from post where post_setupdatetime > ?  and post_setupdatetime < ? ";
		Object[] values1 = { start, end };
		Vector<Post> posts = postblservice.getBySql(sql1, values1);
		this.collectiondatas = changePostDatas(posts);
		double paymentSum=0;
		double collectionSum=0;
		for(Payment payment :payments){
			paymentSum+=payment.getPayment_amount();
		}
		for(Post post :posts){
			collectionSum+=post.getCollectionfare();
		}
		this.textField_paymentSum.setText(paymentSum+"");
		this.textField_collectionSum.setText(collectionSum+"");
		double amount=collectionSum-paymentSum;
		this.textField_amount.setText(amount+"");
		refreshTable();
	}

	/**
	 * 初始化table列名
	 */
	public void initColumns() {
		this.paymentcolumns = new Vector();
		this.paymentcolumns.add("付款单类型");
		this.paymentcolumns.add("付款金额");
		this.paymentcolumns.add("付款时间");
		
		this.collectioncolumns = new Vector();
		this.collectioncolumns.add("订单条形码号");
		this.collectioncolumns.add("所收运费");
		this.collectioncolumns.add("收款时间");
	}

	private Vector<Vector> changePaymentDatas(Vector<Payment> payments) {
		Vector<Vector> view = new Vector<Vector>();
		for (Payment payment : payments) {
			Vector v = new Vector();

			v.add(payment.getPayment_type());
			v.add(payment.getPayment_amount());
			v.add(DateUtil.getStringByTimeStamp(payment.getPayment_datetime()));
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

			
			v.add(post.getBarcode());
			v.add(post.getCollectionfare());
			v.add(DateUtil.getStringByTimeStamp(post.getPost_setupdatetime()));
			view.add(v);
		}
		return view;
	}

	public void setTableFace() {

		this.Paymenttable.setRowHeight(30);
		this.Collectiontable.setRowHeight(30);

	}

	public void initData() {

		if (null != this.Paymenttable) {
			DefaultTableModel tableModel2 = (DefaultTableModel) this.Paymenttable.getModel();
			// 将数据设入表格Model中
			tableModel2.setDataVector(this.paymentdatas, this.paymentcolumns);
		}

		if (null != this.Collectiontable) {
			DefaultTableModel tableModel3 = (DefaultTableModel) this.Collectiontable.getModel();
			// 将数据设入表格Model中
			tableModel3.setDataVector(this.collectiondatas, this.collectioncolumns);
		}

		// 设置表格样式
		setTableFace();
	}

	/*
	 * 刷新列表的方法
	 */
	public void refreshTable() {
		initData();
		this.Paymenttable.repaint();
		this.Collectiontable.repaint();
	}

	// 显示警告
	protected int showWarn(String message) {
		return JOptionPane.showConfirmDialog(this, message, "警告", JOptionPane.OK_CANCEL_OPTION);
	}

}
