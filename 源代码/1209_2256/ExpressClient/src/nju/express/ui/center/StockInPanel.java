package nju.express.ui.center;

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
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.qt.datapicker.DatePicker;

import nju.express.blservice.Postblservice;
import nju.express.blservice.Stockblservice;
import nju.express.po.Post;
import nju.express.po.Stock;
import nju.express.ui.MainFrame;
import nju.express.ui.utils.CommonPanel;
import nju.express.ui.utils.SingleJTable;
import nju.express.util.DateTextField;
import nju.express.util.DateUtil;
import nju.express.util.LoggerUtil;

public class StockInPanel extends CommonPanel {

	static Logger logger = LoggerUtil.getLogger();

	Postblservice postblservice;
	Stockblservice stockblservice;
	private static final long serialVersionUID = -935017859680898906L;

	Vector postcolumns;

	private JTable table_post;
	private JTextField textField_postId = new JTextField("");
	private JTextField textField_area;
	private JTextField textField_row;
	private JTextField textField_shelf;
	private JTextField textField_position;
	private DateTextField textField_stockindatetime;

	/**
	 * Create the panel.
	 */
	public StockInPanel(Postblservice postblservice, Stockblservice stockblservice) {
		this.setSize(1100, 664);

		this.postblservice = postblservice;
		this.stockblservice = stockblservice;
		setLayout(null);

		initColumns();
		setViewDatas();

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 42, 1088, 214);
		add(scrollPane_1);

		DefaultTableModel model = new DefaultTableModel(getDatas(), getColumns());
		table_post = new SingleJTable(model);
		setJTable(table_post);
		getJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				// ��ѡ����ʱ����ͷ�ʱ��ִ��
				if (!event.getValueIsAdjusting()) {
					if (getJTable().getSelectedRowCount() != 1)
						return;
					textField_postId.setText(getSelectId(table_post) + "");
					MainFrame.getLoglabel().setText("ѡ�� post ���Ϊ " + getSelectId(table_post));
				}
			}
		});
		setTableFace();
		scrollPane_1.setViewportView(table_post);

		JLabel label = new JLabel("\u9009\u62E9\u5165\u5E93\u4F4D\u7F6E");
		label.setBounds(6, 281, 82, 18);
		add(label);

		JLabel label_1 = new JLabel("\u533A\u53F7");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(6, 312, 55, 18);
		add(label_1);

		textField_area = new JTextField();
		textField_area.setBounds(73, 306, 122, 30);
		add(textField_area);
		textField_area.setColumns(10);

		JLabel label_2 = new JLabel("\u6392\u53F7");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(230, 312, 55, 18);
		add(label_2);

		textField_row = new JTextField();
		textField_row.setBounds(297, 306, 122, 30);
		add(textField_row);
		textField_row.setColumns(10);

		JLabel label_3 = new JLabel("\u67B6\u53F7");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(468, 312, 55, 18);
		add(label_3);

		textField_shelf = new JTextField();
		textField_shelf.setBounds(535, 306, 122, 30);
		add(textField_shelf);
		textField_shelf.setColumns(10);

		JLabel label_4 = new JLabel("\u4F4D\u53F7");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(701, 312, 55, 18);
		add(label_4);

		textField_position = new JTextField();
		textField_position.setBounds(783, 306, 122, 30);
		add(textField_position);
		textField_position.setColumns(10);

		JLabel label_5 = new JLabel("\u5165\u5E93\u65F6\u95F4");
		label_5.setBounds(6, 366, 55, 18);
		add(label_5);

		textField_stockindatetime = new DateTextField("yyyy-MM-dd HH:mm:ss");
		textField_stockindatetime.setBounds(73, 360, 172, 30);
		add(textField_stockindatetime);
		textField_stockindatetime.setColumns(10);

		JButton button_choosetime = new JButton("\u9009\u62E9\u65F6\u95F4");
		button_choosetime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DatePicker dp = new DatePicker(textField_stockindatetime, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_stockindatetime.getText());
				dp.setSelectedDate(selectedDate);
				dp.start(textField_stockindatetime);
			}

		});
		button_choosetime.setBounds(257, 360, 90, 30);
		add(button_choosetime);

		JButton button_save = new JButton("\u4FDD\u5B58");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		button_save.setBounds(378, 401, 90, 30);
		add(button_save);

		JButton button = new JButton("\u6E05\u7A7A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		button.setBounds(496, 401, 90, 30);
		add(button);

		JButton button_refresh = new JButton("\u5237\u65B0");
		button_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setViewDatas();
				refreshTable();
			}
		});
		button_refresh.setBounds(1004, 6, 90, 30);
		add(button_refresh);

	}

	/**
	 * ��ʼ��table����
	 */
	public void initColumns() {

		this.postcolumns = new Vector();
		this.postcolumns.add("id");
		this.postcolumns.add("�����������");
		this.postcolumns.add("װ����������");
		this.postcolumns.add("�������ת����");
		this.postcolumns.add("�������");
		this.postcolumns.add("����ʱ��");

	}

	public void setViewDatas() {
		int departmentId = MainFrame.getInstance().getUser().getDepartment().getId();
		// String sql = "SELECT * FROM post where BItransport_id_fk is not null
		// and start_stock_id_fk is NULL";
		String sql1 = "select * from post p,bitransport b where p.BItransport_id_fk = b.id and p.BItransport_id_fk is not null and p.start_stock_id_fk is null and b.end_department_id_fk = "
				+ departmentId;

		Vector<Post> biposts = postblservice.getListBySql(sql1);
		Vector<Vector> bipostdatas = changebiPostDatas(biposts);

		String sql2 = "SELECT * FROM post p ,iitransport i where p.iItransport_id_fk = i.id and p.IItransport_id_fk is not null and p.end_stock_id_fk is null and i.end_department_id_fk = "
				+ departmentId;

		Vector<Post> iiposts = postblservice.getListBySql(sql2);
		Vector<Vector> iipostdatas = changeiiPostDatas(iiposts);

		bipostdatas.addAll(iipostdatas);
		Vector<Vector> postdatas = bipostdatas;
		setDatas(postdatas);

	}

	/**
	 * @param posts
	 *            �ļ�������
	 * @return table����
	 */
	private Vector<Vector> changebiPostDatas(Vector<Post> posts) {
		Vector<Vector> view = new Vector<Vector>();
		for (Post post : posts) {
			Vector v = new Vector();

			v.add(post.getId());
			v.add(post.getBarcode());
			v.add(post.getbItransport().getStart_department().getDepartment_name());
			v.add(post.getbItransport().getEnd_department().getDepartment_name());
			v.add(post.getPost_type());
			v.add(DateUtil.getStringByDateTime(post.getPost_setupdatetime()));
			view.add(v);
		}
		return view;
	}

	/**
	 * @param posts
	 *            �ļ�������
	 * @return table����
	 */
	private Vector<Vector> changeiiPostDatas(Vector<Post> posts) {
		Vector<Vector> view = new Vector<Vector>();
		for (Post post : posts) {
			Vector v = new Vector();

			v.add(post.getId());
			v.add(post.getBarcode());
			v.add(post.getiItransport().getStart_department().getDepartment_name());
			v.add(post.getiItransport().getEnd_department().getDepartment_name());
			v.add(post.getPost_type());
			v.add(DateUtil.getStringByDateTime(post.getPost_setupdatetime()));
			view.add(v);
		}
		return view;
	}

	@Override
	public Vector<String> getColumns() {
		return this.postcolumns;
	}

	@Override
	public void clear() {

		this.textField_postId.setText("");
		this.textField_area.setText("");
		this.textField_row.setText("");
		this.textField_shelf.setText("");
		this.textField_position.setText("");
		this.textField_stockindatetime.setText("");
		refreshTable();
	}

	@Override
	public void setTableFace() {
		this.table_post.getColumn("id").setMinWidth(-1);
		this.table_post.getColumn("id").setMaxWidth(-1);
		this.table_post.getColumn("�����������").setMinWidth(90);
		this.table_post.getColumn("�����������").setMaxWidth(90);
		this.table_post.setRowHeight(30);
	}

	/**
	 * ����
	 */
	private void save() {

		if (!this.textField_postId.getText().equals("")) {
			add();
		}
	}

	/**
	 * ���
	 */
	private void add() {
		if (isTextExist() && isTextValidate()) {
			MainFrame.getLoglabel().setText("���������Ϣ");

			Stock stock = getStock();
			int result = stockblservice.add(stock);

			if (result == 0) {
				MainFrame.getLoglabel().setText("���ʧ��");
				logger.info("���ʧ��");
			} else {
				int PostId = Integer.parseInt(this.textField_postId.getText());

				String sql = "update post set start_stock_id_fk = ? where id = ? and start_stock_id_fk is null ";
				Object[] obs = { result, PostId };
				if (postblservice.update_fk_id(sql, obs)) {
					// ֪ͨ��ӽ��
					MainFrame.getLoglabel().setText("��һ�����ɹ�");
					logger.info("��һ�����ɹ�");
					// ���¶�ȡ����
					setViewDatas();
					// ˢ���б�, ��ձ�
					clear();
					return;
				}

				String sql2 = "update post set end_stock_id_fk = ? where id = ? and start_stock_id_fk is not null ";
				Object[] obs2 = { result, PostId };
				if (postblservice.update_fk_id(sql2, obs2)) {
					// ֪ͨ��ӽ��
					MainFrame.getLoglabel().setText("�ڶ������ɹ�");
					logger.info("�ڶ������ɹ�");
				}
				// ���¶�ȡ����
				setViewDatas();
				// ˢ���б�, ��ձ�
				clear();

			}
		}

	}

	private Stock getStock() {
		// ֻ����login�����Department,����ʱ��Ҫ�ɵ�¼�������
		return new Stock(MainFrame.getInstance().getUser().getDepartment().getId(),
				Integer.parseInt(this.textField_area.getText()), Integer.parseInt(this.textField_row.getText()),
				Integer.parseInt(this.textField_shelf.getText()), Integer.parseInt(this.textField_position.getText()),
				1,new Timestamp(DateUtil.getDateByDateTimeString(this.textField_stockindatetime.getText()).getTime()));
	}

	private boolean isTextExist() {

		if (this.textField_area.getText().equals("")) {
			showWarn("δ��������");
			return false;
		}
		if (this.textField_row.getText().equals("")) {
			showWarn("δ�����ź�");
			return false;
		}
		if (this.textField_shelf.getText().equals("")) {
			showWarn("δ����ܺ�");
			return false;
		}
		if (this.textField_position.getText().equals("")) {
			showWarn("δ����λ��");
			return false;
		}
		if (this.textField_stockindatetime.getText().equals("")) {
			showWarn("δ�������ʱ��");
			return false;
		}
		return true;
	}

	private boolean isTextValidate() {
		if (!isNumber(this.textField_area.getText())) {
			showWarn("���Ÿ�ʽ����ȷ");
			return false;
		}
		if (!isNumber(this.textField_row.getText())) {
			showWarn("�źŸ�ʽ����ȷ");
			return false;
		}
		if (!isNumber(this.textField_shelf.getText())) {
			showWarn("�ܺŸ�ʽ����ȷ");
			return false;
		}
		if (!isNumber(this.textField_position.getText())) {
			showWarn("λ�Ÿ�ʽ����ȷ");
			return false;
		}
		if (!isDatetime(this.textField_stockindatetime.getText())) {
			showWarn("���ʱ���ʽ����ȷ");
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

	/**
	 * ����ַ����Ƿ�������
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
}
