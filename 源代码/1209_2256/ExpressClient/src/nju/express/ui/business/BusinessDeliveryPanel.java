package nju.express.ui.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import nju.express.blservice.Deliveryblservice;
import nju.express.blservice.Postblservice;
import nju.express.po.Delivery;
import nju.express.po.Post;
import nju.express.po.User;
import nju.express.ui.MainFrame;
import nju.express.ui.utils.CommonPanel;
import nju.express.ui.utils.SingleJTable;
import nju.express.util.AutoTextFieldUtil;
import nju.express.util.DateTextField;
import nju.express.util.DateUtil;
import nju.express.util.LoggerUtil;

/**
 * Ӫҵ���ɼ���
 * 
 * @author ���Ƿ�
 * @time 2015��11��3������5:02:40
 */
public class BusinessDeliveryPanel extends CommonPanel {
	static Logger logger = LoggerUtil.getLogger();
	Deliveryblservice deliveryblservice;
	Postblservice postblservice;
	private static final long serialVersionUID = 399745150056689413L;
	private DateTextField textField_deliverydatetime;

	// Jtable������
	Vector columns;
	Vector<String> tips = new Vector<String>();
	private JTextField textField_postId = new JTextField("");
	private JComboBox comboBox;
	private JTextField textField_query;

	/**
	 * Create the panel.
	 */
	public BusinessDeliveryPanel(Deliveryblservice deliveryblservice, Postblservice postblservice) {
		this.setSize(1100, 664);
		this.deliveryblservice = deliveryblservice;
		this.postblservice = postblservice;

		initColumns();
		setViewDatas();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 41, 1088, 296);

		JLabel label_1 = new JLabel("\u6D3E\u9001\u65F6\u95F4");
		label_1.setBounds(410, 355, 48, 18);

		textField_deliverydatetime = new DateTextField("yyyy-MM-dd HH:mm:ss");
		textField_deliverydatetime.setBounds(470, 349, 145, 30);
		textField_deliverydatetime.setColumns(10);

		JButton button_choosetime = new JButton("\u9009\u62E9\u65F6\u95F4");
		button_choosetime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatePicker dp = new DatePicker(textField_deliverydatetime, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_deliverydatetime.getText());

				dp.setSelectedDate(selectedDate);
				dp.start(textField_deliverydatetime);
			}
		});
		button_choosetime.setBounds(621, 349, 76, 30);

		JLabel label_2 = new JLabel("\u6D3E\u9001\u4EBA");
		label_2.setBounds(410, 396, 48, 18);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);

		comboBox = new JComboBox();
		addCourier();
		comboBox.setBounds(470, 391, 145, 28);

		JButton button_save = new JButton("\u4FDD\u5B58");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		button_save.setBounds(489, 437, 110, 30);
		setLayout(null);
		add(scrollPane);

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
		scrollPane.setViewportView(table);
		add(label_2);
		add(label_1);
		add(comboBox);
		add(textField_deliverydatetime);
		add(button_choosetime);
		add(button_save);

		JButton button_refresh = new JButton("\u5237\u65B0");
		button_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getLoglabel().setText("ˢ��");
				setViewDatas();
				refreshTable();
			}
		});
		button_refresh.setBounds(1004, 6, 90, 30);
		add(button_refresh);

		textField_query = new JTextField();
		textField_query.setBounds(744, 6, 154, 30);
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		textField_query.setColumns(10);
		add(textField_query);

		JButton button_query = new JButton("\u67E5\u8BE2");
		button_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});
		button_query.setBounds(910, 6, 90, 30);
		add(button_query);

	}

	private void view() {
		int PostId = getSelectId(getJTable());
		this.textField_postId.setText(PostId+"");
		Post post = postblservice.getById(PostId);
		if (post.getDelivery_id_fk() != 0) {
			this.textField_deliverydatetime
					.setText(DateUtil.getStringByTimeStamp(post.getDelivery().getDelivery_datetime()));
			this.comboBox.setSelectedItem(makeUser(post.getDelivery().getCourier()));
		}
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
		User courier = (User) this.comboBox.getSelectedItem();
		Delivery delivery = new Delivery(
				new Timestamp(DateUtil.getDateByDateTimeString(this.textField_deliverydatetime.getText()).getTime()),
				courier.getId());
		if (!this.textField_postId.getText().equals("")) {
			int postId = Integer.parseInt(this.textField_postId.getText());
			Post post = postblservice.getById(postId);
			if (post.getDelivery_id_fk() != 0) {
				// update delivery
				int deliveryId = post.getDelivery_id_fk();
				delivery.setId(deliveryId);
				boolean result = deliveryblservice.update(delivery);
				if (result) {
					// update success
					String sql = "update post set delivery_id_fk = ? where id = ? ";
					Object[] obs = { deliveryId, postId };
					postblservice.update_fk_id(sql, obs);
					// ���¶�ȡ����
					setViewDatas();
					// ˢ���б�, ��ձ�
					clear();
					// ֪ͨ���½��
					MainFrame.getLoglabel().setText("�����ɼ���Ϣ�ɹ�");
					logger.info("�����ɼ���Ϣ�ɹ�");
				} else {
					// update fail
					MainFrame.getLoglabel().setText("�����ɼ���Ϣʧ��");
					logger.error("�����ɼ���Ϣʧ��");
				}
			} else {
				// add delivery
				int result = deliveryblservice.add(delivery);
				if (result == 0) {
					// add fail
					MainFrame.getLoglabel().setText("�����ɼ���Ϣʧ��");
					logger.error("�����ɼ���Ϣʧ��");
				} else {
					// add success
					String sql = "update post set delivery_id_fk = ? where id = ? ";
					Object[] obs = { result, postId };
					postblservice.update_fk_id(sql, obs);
					// ���¶�ȡ����
					setViewDatas();
					// ˢ���б�, ��ձ�
					clear();
					// ֪ͨ��ӽ��
					MainFrame.getLoglabel().setText("�����ɼ���Ϣ�ɹ�");
					logger.info("�����ɼ���Ϣ�ɹ�");
				}
			}
		}
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

	/**
	 * ��ʼ��table����
	 */
	public void initColumns() {
		this.columns = new Vector();
		this.columns.add("id");
		this.columns.add("�����������");
		this.columns.add("�ռ��˵�ַ");
		this.columns.add("����ʱ��");
		this.columns.add("������");

	}

	@Override
	public void setViewDatas() {
		String sql = "SELECT * from post where IBtransport_id_fk is not null ";
		Vector<Post> posts = postblservice.getListBySql(sql);
		Vector<Vector> datas = changeDatas(posts);
		// ���ø��෽�����ý������
		setDatas(datas);

	}

	private Vector<Vector> changeDatas(Vector<Post> posts) {
		Vector<Vector> view = new Vector<Vector>();
		for (Post post : posts) {
			Vector v = new Vector();
			v.add(post.getId());
			v.add(post.getBarcode());
			v.add(post.getReceiver().getReceiver_address());
			if (post.getDelivery_id_fk() != 0) {
				v.add(post.getDelivery().getDelivery_datetime());
				v.add(post.getDelivery().getCourier().getUser_name());
			} else {
				v.add("");
				v.add("");
			}

			view.add(v);
		}
		return view;
	}

	@Override
	public void clear() {
		MainFrame.getLoglabel().setText("���");
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		this.textField_postId.setText("");
		this.textField_deliverydatetime.setText("");
		refreshTable();

	}

	/**
	 * �����������courier���Ա
	 */
	private void addCourier() {
		int department_id = MainFrame.getInstance().getUser().getDepartment_id_fk();
		Vector<User> users = postblservice.getCourier(department_id);
		for (User user : users) {
			this.comboBox.addItem(makeUser(user));
		}
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

	/**
	 * ������е�tips��ʾ���Զ���ȫ��ѯ�ı���
	 * 
	 * @return
	 */
	private Vector<String> getAllTips() {
		String sql = "SELECT * from post where IBtransport_id_fk is not null ";
		Vector<Post> posts = postblservice.getListBySql(sql);
		tips.clear();
		for (Post post : posts) {
			tips.add(post.getBarcode());

		}
		return tips;
	}
}
