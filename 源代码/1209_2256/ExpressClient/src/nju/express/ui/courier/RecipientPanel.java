package nju.express.ui.courier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

import nju.express.blservice.Postblservice;
import nju.express.blservice.Recipientblservice;
import nju.express.po.Post;
import nju.express.po.Recipient;
import nju.express.ui.MainFrame;
import nju.express.ui.utils.CommonPanel;
import nju.express.ui.utils.SingleJTable;
import nju.express.util.DateTextField;
import nju.express.util.DateUtil;
import nju.express.util.LoggerUtil;

/**
 * 该界面为收件单界面，需要快递员输入签收人的姓名和签收时间 根据财务中心人员分配给快递员的订单，选择输入
 * 
 * @author 徐亚帆
 * @time 2015年11月3日下午4:02:47
 */
public class RecipientPanel extends CommonPanel {
	static Logger logger = LoggerUtil.getLogger();
	Postblservice postblservice;
	Recipientblservice recipientblservice;
	private static final long serialVersionUID = -4943430054548454122L;
	Vector columns;
	private JTextField textField_postId = new JTextField();
	private JTable table;
	private JTextField textField_recipientname;
	private DateTextField textField_recipientdatetime;
	private JTextField textField_query;

	/**
	 * Create the panel.
	 */
	public RecipientPanel(Postblservice postblservice, Recipientblservice recipientblservice) {
		this.setSize(1100, 664);
		this.postblservice = postblservice;
		this.recipientblservice = recipientblservice;

		initColumns();
		setViewDatas();

		textField_query = new JTextField();
		textField_query.setBounds(418, 0, 271, 30);
		textField_query.setColumns(10);

		JButton button_query = new JButton("\u67E5\u8BE2");
		button_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});
		button_query.setBounds(696, 0, 118, 30);

		JButton button_refresh = new JButton("刷新");
		button_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getLoglabel().setText("刷新");
				setViewDatas();
				refreshTable();
			}
		});
		button_refresh.setBounds(820, 0, 115, 30);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 34, 1088, 250);

		JLabel label_1 = new JLabel("\u7B7E\u6536\u4EBA\u59D3\u540D");
		label_1.setBounds(396, 296, 60, 18);

		textField_recipientname = new JTextField();
		textField_recipientname.setBounds(468, 290, 244, 30);
		textField_recipientname.setColumns(10);

		JLabel label_2 = new JLabel("\u7B7E\u6536\u65F6\u95F4");
		label_2.setBounds(396, 326, 48, 18);

		textField_recipientdatetime = new DateTextField("yyyy-MM-dd HH:mm:ss");
		textField_recipientdatetime.setBounds(468, 326, 159, 30);
		textField_recipientdatetime.setColumns(10);

		JButton button_choosetime = new JButton("\u9009\u62E9\u65F6\u95F4");
		button_choosetime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatePicker dp = new DatePicker(textField_recipientdatetime, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_recipientdatetime.getText());

				dp.setSelectedDate(selectedDate);
				dp.start(textField_recipientdatetime);
			}
		});
		button_choosetime.setBounds(633, 326, 76, 30);

		JButton button_save = new JButton("\u4FDD\u5B58");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		button_save.setBounds(497, 373, 86, 30);

		DefaultTableModel model = new DefaultTableModel(getDatas(), getColumns());
		table = new SingleJTable(model);
		setJTable(table);
		getJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				// 当选择行时鼠标释放时才执行
				if (!event.getValueIsAdjusting()) {
					if (getJTable().getSelectedRowCount() != 1)
						return;
					textField_postId.setText(getSelectId(table) + "");
					MainFrame.getLoglabel().setText("选择 post 编号为 " + getSelectId(table));
				}
			}
		});
		setTableFace();
		scrollPane.setViewportView(table);
		setLayout(null);
		add(textField_query);
		add(button_query);
		add(button_refresh);
		add(scrollPane);
		add(label_1);
		add(label_2);
		add(textField_recipientname);
		add(textField_recipientdatetime);
		add(button_choosetime);
		add(button_save);

	}

	private void query() {
		String text = this.textField_query.getText().trim();
		String sql = "select * from post where barcode = ? ";
		Object[] values = { text };
		Vector<Post> posts = postblservice.getBySql(sql, values);
		Vector<Vector> datas = changeDatas(posts);
		setDatas(datas);
		refreshTable();
	}

	private void save() {
		if (!this.textField_postId.getText().equals("")) {
			add();
		}
	}

	/**
	 * 添加
	 */
	private void add() {
		if (isTextExist() && isTextValidate()) {
			MainFrame.getLoglabel().setText("保存签收信息");

			Recipient recipient = getRecipient();
			int result = recipientblservice.add(recipient);

			if (result == 0) {
				MainFrame.getLoglabel().setText("保存签收信息失败");
				logger.info("保存签收信息失败");
			} else {
				int PostId = Integer.parseInt(this.textField_postId.getText());

				String sql = "update post set recipient_id_fk = ? where id = ? ";
				Object[] obs = { result, PostId };
				if (postblservice.update_fk_id(sql, obs)) {
					// 通知添加结果
					MainFrame.getLoglabel().setText("保存签收信息成功");
					logger.info("保存签收信息成功");
					// 重新读取数据
					setViewDatas();
					// 刷新列表, 清空表单
					clear();
					return;
				}

			}
		}

	}

	private Recipient getRecipient() {
		return new Recipient(this.textField_recipientname.getText(),
				new Timestamp(DateUtil.getDateByDateTimeString(this.textField_recipientdatetime.getText()).getTime()));
	}

	private boolean isTextExist() {

		if (this.textField_recipientname.getText().equals("")) {
			showWarn("未输入收件人姓名");
			return false;
		}
		if (this.textField_recipientdatetime.getText().equals("")) {
			showWarn("未输入签收时间");
			return false;
		}
		return true;
	}

	private boolean isTextValidate() {

		if (!isDatetime(this.textField_recipientdatetime.getText())) {
			showWarn("入库时间格式不正确");
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
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateFormat.parse(string);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	/**
	 * 初始化table列名
	 */
	public void initColumns() {
		// TODO
		this.columns = new Vector();
		this.columns.add("id");
		this.columns.add("订单条形码号");
		this.columns.add("寄件人");
		this.columns.add("寄件人地址");
		this.columns.add("收件人");
		this.columns.add("收件人地址");

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
		String sql = "SELECT * from post where delivery_id_fk is not null and recipient_id_fk is null ";
		Vector<Post> posts = postblservice.getListBySql(sql);
		Vector<Vector> datas = changeDatas(posts);
		// 调用父类方法设置结果集合
		setDatas(datas);

	}

	private Vector<Vector> changeDatas(Vector<Post> posts) {
		Vector<Vector> view = new Vector<Vector>();
		for (Post post : posts) {
			Vector v = new Vector();
			v.add(post.getId());
			v.add(post.getBarcode());
			v.add(post.getSender().getSender_name());
			v.add(post.getSender().getSender_address());
			v.add(post.getReceiver().getReceiver_name());
			v.add(post.getReceiver().getReceiver_address());

			view.add(v);
		}
		return view;
	}

	@Override
	public void clear() {
		this.textField_postId.setText("");
		this.textField_recipientname.setText("");
		this.textField_recipientdatetime.setText("");
		refreshTable();

	}
}
