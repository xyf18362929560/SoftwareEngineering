package nju.express.ui.courier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.qt.datapicker.DatePicker;

import nju.express.blservice.Postblservice;
import nju.express.po.Goods;
import nju.express.po.Post;
import nju.express.po.Receiver;
import nju.express.po.Sender;
import nju.express.po.User;
import nju.express.ui.MainFrame;
import nju.express.ui.utils.CommonPanel;
import nju.express.ui.utils.SingleJTable;
import nju.express.util.AutoTextFieldUtil;
import nju.express.util.DateTextField;
import nju.express.util.DateUtil;
import nju.express.util.LoggerUtil;

/**
 * ��panel�ǿ��Ա�ļ�������
 * 
 * @author ���Ƿ�
 * @time 2015��10��31������1:01:04
 */

public class PostPanel extends CommonPanel {
	static Logger logger = LoggerUtil.getLogger();
	// ��Ҫ��ҵ���߼��ӿ�
	Postblservice postblservice;
	// Jtable������
	Vector columns;
	// �汾��
	private static final long serialVersionUID = -1714135907124920616L;
	// ���ص�id��,��Ϊһ��post�ں���4������ʵ���࣬ÿ������Ҫ���ص�id��洢��id
	private JTextField textField_postId = new JTextField("");
	private JTextField textField_senderId = new JTextField("");
	private JTextField textField_receiverId = new JTextField("");
	private JTextField textField_goodsId = new JTextField("");

	// ʱ���ı���
	private DateTextField textField_query;
	private DateTextField textField_estimatedtime;
	private DateTextField textField_posttime;
	// �����ı���
	private JTextField textField_sender_name;
	private JTextField textField_sender_address;
	private JTextField textField_receiver_name;
	private JTextField textField_receiver_address;
	private JTextField textField_receiver_phone;
	private JTextField textField_sender_info;
	private JTextField textField_receiver_info;
	private JTextField textField_goods_name;
	private JTextField textField_goods_num;
	private JTextField textField_goods_weight;
	private JTextField textField_goods_length;
	private JTextField textField_goods_width;
	private JTextField textField_goods_height;
	private JTextField textField_goods_info;
	private JTextField textField_barcode;
	private JTextField textField_fare;
	private JTextField textField_sender_phone;
	private JTextField textField_volume;
	// �����б�
	private JComboBox comboBox_query;
	private JComboBox comboBox_postType;
	private JComboBox comboBox_courier;
	// RadioButton��ButtonGroup
	private ButtonGroup buttonGroup;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton_2;
	// ��ѯ������ʾ�ַ����ļ���
	Vector<String> tips = new Vector<String>();
	// ���ڸ�ʽ
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Create the panel.
	 */
	public PostPanel(Postblservice postblservice) {

		this.postblservice = postblservice;
		// 1100 664ΪĬ�Ϲ淶��С
		this.setSize(1100, 664);
		// ��ʼ��table������
		initColumns();
		setViewDatas();
		// ����table
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

		// �����ѯ�ı���Ϊ���ƶ����ڸ�ʽ���������tips��Ϊ�������Զ���ȫ
		textField_query = new DateTextField("yyyy-MM-dd");
		textField_query.setBounds(424, 6, 249, 30);
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		textField_query.setColumns(10);

		JButton button_query = new JButton("��ѯ");
		button_query.setBounds(767, 6, 118, 30);
		button_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 42, 1088, 213);

		JLabel label = new JLabel("�ļ�������");
		label.setBounds(6, 267, 60, 18);

		textField_sender_name = new JTextField();
		textField_sender_name.setBounds(72, 261, 255, 30);
		textField_sender_name.setColumns(10);

		JLabel label_1 = new JLabel("�ļ��˵�ַ");
		label_1.setBounds(339, 267, 60, 18);

		textField_sender_address = new JTextField();
		textField_sender_address.setBounds(405, 261, 689, 30);
		textField_sender_address.setColumns(10);

		JLabel label_2 = new JLabel("�ļ����ֻ�");
		label_2.setBounds(6, 303, 60, 18);

		JLabel label_3 = new JLabel("\u6536\u4EF6\u4EBA\u59D3\u540D");
		label_3.setBounds(6, 359, 60, 18);

		textField_receiver_name = new JTextField();
		textField_receiver_name.setBounds(72, 353, 256, 30);
		textField_receiver_name.setColumns(10);

		JLabel label_4 = new JLabel("\u6536\u4EF6\u4EBA\u5730\u5740");
		label_4.setBounds(340, 359, 60, 18);

		textField_receiver_address = new JTextField();
		textField_receiver_address.setBounds(405, 353, 689, 30);
		textField_receiver_address.setColumns(10);

		JLabel label_5 = new JLabel("\u6536\u4EF6\u4EBA\u624B\u673A");
		label_5.setBounds(6, 395, 60, 18);

		textField_receiver_phone = new JTextField();
		textField_receiver_phone.setBounds(72, 389, 256, 30);
		textField_receiver_phone.setColumns(10);

		JLabel label_6 = new JLabel("\u5BC4\u4EF6\u4EBA\u4FE1\u606F");
		label_6.setBounds(339, 303, 60, 18);

		textField_sender_info = new JTextField();
		textField_sender_info.setBounds(405, 297, 689, 30);
		textField_sender_info.setColumns(10);

		JLabel label_7 = new JLabel("\u6536\u4EF6\u4EBA\u4FE1\u606F");
		label_7.setBounds(340, 395, 60, 18);

		textField_receiver_info = new JTextField();
		textField_receiver_info.setBounds(405, 389, 689, 30);
		textField_receiver_info.setColumns(10);

		JLabel label_8 = new JLabel("\u8D27\u7269\u540D\u79F0");
		label_8.setBounds(6, 450, 48, 18);

		textField_goods_name = new JTextField();
		textField_goods_name.setBounds(66, 444, 210, 30);
		textField_goods_name.setColumns(10);

		JLabel label_9 = new JLabel("\u8D27\u7269\u6570\u91CF");
		label_9.setBounds(288, 450, 48, 18);

		textField_goods_num = new JTextField();
		textField_goods_num.setBounds(342, 444, 58, 30);
		textField_goods_num.setColumns(10);

		JLabel label_10 = new JLabel("\u8D27\u7269\u91CD\u91CF(g)");
		label_10.setBounds(416, 450, 63, 18);

		textField_goods_weight = new JTextField();
		textField_goods_weight.setBounds(491, 444, 71, 30);
		textField_goods_weight.setColumns(10);

		JLabel lblcm = new JLabel("\u957F(cm)");
		lblcm.setBounds(574, 450, 37, 18);

		textField_goods_length = new JTextField();
		textField_goods_length.setBounds(624, 444, 55, 30);
		textField_goods_length.setColumns(10);
		textField_goods_length.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

				updateList();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateList();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateList();

			}

			private void updateList() {
				if (!textField_goods_height.getText().equals("")) {
					if (isNumber(textField_goods_length.getText())) {
						getandSetVolume();
					}
				}

			}

		});

		JLabel lblcm_1 = new JLabel("\u5BBD(cm)");
		lblcm_1.setBounds(683, 450, 37, 18);

		textField_goods_width = new JTextField();
		textField_goods_width.setBounds(732, 444, 62, 30);
		textField_goods_width.setColumns(10);
		textField_goods_width.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

				updateList();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateList();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateList();

			}

			private void updateList() {
				if (!textField_goods_height.getText().equals("")) {

					if (isNumber(textField_goods_width.getText())) {
						getandSetVolume();
					}
				}
			}

		});

		JLabel lblcm_2 = new JLabel("\u9AD8(cm)");
		lblcm_2.setBounds(806, 450, 37, 18);

		textField_goods_height = new JTextField();
		textField_goods_height.setBounds(855, 444, 57, 30);
		textField_goods_height.setColumns(10);
		textField_goods_height.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

				updateList();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateList();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateList();

			}

			private void updateList() {
				if (!textField_goods_height.getText().equals("")) {

					if (isNumber(textField_goods_height.getText())) {
						getandSetVolume();
					}
				}
			}

		});

		JLabel label_11 = new JLabel("\u8D27\u7269\u4FE1\u606F");
		label_11.setBounds(6, 492, 48, 18);

		textField_goods_info = new JTextField();
		textField_goods_info.setBounds(66, 486, 1028, 30);
		textField_goods_info.setColumns(10);

		JLabel label_12 = new JLabel("\u5FEB\u9012\u7C7B\u578B");
		label_12.setBounds(6, 548, 48, 18);

		String[] post_types = { Post.ECONOMY_EXPRESS, Post.STANDARD_EXPRESS, Post.SPECIAL_EXPRESS };
		comboBox_postType = new JComboBox(post_types);
		comboBox_postType.setBounds(72, 543, 118, 28);
		comboBox_postType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				getandSetCollectionFare();
			}
		});

		JLabel label_13 = new JLabel("\u5305\u88C5\u8D39");
		label_13.setBounds(257, 548, 36, 18);

		radioButton = new JRadioButton("ֽ��(5Ԫ)");
		radioButton.setBounds(299, 548, 73, 18);

		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getandSetCollectionFare();
			}
		});

		radioButton_1 = new JRadioButton("\u6728\u7BB1(10\u5143)");
		radioButton_1.setBounds(378, 548, 80, 18);
		radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getandSetCollectionFare();
			}
		});

		radioButton_2 = new JRadioButton("\u5FEB\u9012\u888B(1\u5143)");
		radioButton_2.setBounds(464, 548, 85, 18);
		radioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getandSetCollectionFare();
			}
		});

		buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButton);
		buttonGroup.add(radioButton_1);
		buttonGroup.add(radioButton_2);
		radioButton.setSelected(true);

		JLabel label_barcode = new JLabel("\u8BA2\u5355\u6761\u5F62\u7801\u53F7");
		label_barcode.setBounds(905, 548, 72, 18);

		textField_barcode = new JTextField();
		textField_barcode.setBounds(983, 542, 111, 30);
		textField_barcode.setColumns(10);

		JLabel label_15 = new JLabel("���úϼ�(�Զ�����)");
		label_15.setBounds(685, 548, 104, 18);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 528, 1088, 8);

		textField_fare = new JTextField();
		textField_fare.setBounds(795, 542, 98, 30);
		textField_fare.setColumns(10);

		JLabel label_16 = new JLabel("\u9884\u8BA1\u5230\u8FBE\u65F6\u95F4");
		label_16.setBounds(371, 590, 72, 18);

		textField_estimatedtime = new DateTextField("yyyy-MM-dd");
		textField_estimatedtime.setBounds(449, 584, 148, 30);
		textField_estimatedtime.setColumns(10);

		textField_sender_phone = new JTextField();
		textField_sender_phone.setBounds(72, 297, 255, 30);
		textField_sender_phone.setColumns(10);

		JLabel label_17 = new JLabel("\u5BC4\u4EF6\u5355\u5EFA\u7ACB\u4EBA");
		label_17.setBounds(717, 590, 72, 18);

		JLabel label_18 = new JLabel("\u5BC4\u4EF6\u5355\u5EFA\u7ACB\u65F6\u95F4");
		label_18.setBounds(6, 590, 84, 18);

		textField_posttime = new DateTextField("yyyy-MM-dd HH:mm:ss");
		textField_posttime.setBounds(96, 584, 155, 30);
		textField_posttime.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateList();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateList();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateList();
			}

			private void updateList() {
				if (!textField_posttime.getText().equals("")) {
					if (isDatetime(textField_posttime.getText())) {
						getandSetEstimatedDatetime();
					}
				}
			}
		});

		textField_posttime.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 627, 1088, 2);

		JButton button_save = new JButton("����");
		button_save.setBounds(340, 635, 86, 30);
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		JButton button_clear = new JButton("���");
		button_clear.setBounds(494, 635, 84, 30);
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.setBounds(636, 635, 84, 30);
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});

		comboBox_courier = new JComboBox();
		comboBox_courier.setBounds(795, 585, 98, 28);
		addCourier();

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(6, 339, 1088, 2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(6, 431, 1088, 7);

		JLabel label_14 = new JLabel("���(�Զ�����)");
		label_14.setBounds(915, 450, 80, 18);

		textField_volume = new JTextField();
		textField_volume.setBounds(1000, 444, 94, 30);
		textField_volume.setColumns(10);
		String[] queries = { "�����������", "����ʱ��" };
		comboBox_query = new JComboBox(queries);
		comboBox_query.setBounds(283, 7, 129, 28);

		JLabel label_19 = new JLabel("\u67E5\u8BE2\u65B9\u5F0F");
		label_19.setBounds(223, 12, 48, 18);

		JButton button_showAll = new JButton("��ʾ����");
		button_showAll.setBounds(897, 6, 115, 30);
		button_showAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getLoglabel().setText("��ʾ����");
				setViewDatas();
				refreshTable();
			}
		});

		JButton btnTest = new JButton("ѡ��ʱ��");
		btnTest.setBounds(257, 584, 76, 30);
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatePicker dp = new DatePicker(textField_posttime, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_posttime.getText());
				dp.setSelectedDate(selectedDate);
				dp.start(textField_posttime);
			}
		});

		JButton button_1 = new JButton("ѡ��ʱ��");
		button_1.setBounds(603, 584, 76, 30);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatePicker dp = new DatePicker(textField_estimatedtime, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_estimatedtime.getText());

				dp.setSelectedDate(selectedDate);
				dp.start(textField_estimatedtime);
			}
		});
		// ��ѯ��������ѡ��ť
		JButton button_2 = new JButton("ѡ������");
		button_2.setBounds(679, 6, 76, 30);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_query.setSelectedItem("����ʱ��");
				DatePicker dp = new DatePicker(textField_query, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_query.getText());

				dp.setSelectedDate(selectedDate);
				dp.start(textField_query);
			}
		});

		scrollPane.setViewportView(table);
		setLayout(null);
		add(separator);
		add(separator_2);
		add(label_5);
		add(textField_receiver_phone);
		add(label_3);
		add(textField_receiver_name);
		add(label_4);
		add(textField_receiver_address);
		add(label_7);
		add(textField_receiver_info);
		add(separator_3);
		add(label_18);
		add(textField_posttime);
		add(label_12);
		add(comboBox_postType);
		add(btnTest);
		add(label_16);
		add(textField_estimatedtime);
		add(button_1);
		add(label_13);
		add(radioButton);
		add(radioButton_1);
		add(radioButton_2);
		add(label_15);
		add(textField_fare);
		add(label_17);
		add(comboBox_courier);
		add(label_barcode);
		add(textField_barcode);
		add(separator_1);
		add(button_save);
		add(button_clear);
		add(button_delete);
		add(label_19);
		add(comboBox_query);
		add(textField_query);
		add(button_2);
		add(button_query);
		add(button_showAll);
		add(label_11);
		add(textField_goods_info);
		add(label_8);
		add(textField_goods_name);
		add(label_9);
		add(textField_goods_num);
		add(label_10);
		add(textField_goods_weight);
		add(lblcm);
		add(textField_goods_length);
		add(lblcm_1);
		add(textField_goods_width);
		add(lblcm_2);
		add(textField_goods_height);
		add(label_14);
		add(textField_volume);
		add(scrollPane);
		add(label);
		add(label_2);
		add(textField_sender_phone);
		add(textField_sender_name);
		add(label_1);
		add(label_6);
		add(textField_sender_info);
		add(textField_sender_address);

	}

	/**
	 * ���������ʾ��ϸ��Ϣ
	 */
	private void view() {

		int id = getSelectId(getJTable());
		Post post = postblservice.getById(id);
		MainFrame.getLoglabel().setText("�鿴����(�������:" + post.getBarcode() + ")");

		this.textField_postId.setText(id + "");
		this.textField_senderId.setText(post.getSender_id_fk() + "");
		this.textField_receiverId.setText(post.getReceiver_id_fk() + "");
		this.textField_goodsId.setText(post.getGoods_id_fk() + "");

		this.textField_sender_name.setText(post.getSender().getSender_name());
		this.textField_sender_address.setText(post.getSender().getSender_address());
		this.textField_sender_phone.setText(post.getSender().getSender_phone());
		this.textField_sender_info.setText(post.getSender().getSender_info());

		this.textField_receiver_name.setText(post.getReceiver().getReceiver_name());
		this.textField_receiver_address.setText(post.getReceiver().getReceiver_address());
		this.textField_receiver_phone.setText(post.getReceiver().getReceiver_phone());
		this.textField_receiver_info.setText(post.getReceiver().getReceiver_info());

		this.textField_goods_name.setText(post.getGoods().getGoods_name());
		this.textField_goods_num.setText(post.getGoods().getGoods_num() + "");
		this.textField_goods_weight.setText(post.getGoods().getGoods_weight() + "");
		this.textField_goods_length.setText(post.getGoods().getGoods_length() + "");
		this.textField_goods_width.setText(post.getGoods().getGoods_width() + "");
		this.textField_goods_height.setText(post.getGoods().getGoods_height() + "");
		this.textField_goods_info.setText(post.getGoods().getGoods_info());
		getandSetVolume();

		this.comboBox_postType.setSelectedItem(post.getPost_type());

		switch (post.getPackingexpense()) {
		case 5:
			this.radioButton.setSelected(true);
			break;
		case 10:
			this.radioButton_1.setSelected(true);
			break;
		case 1:
			this.radioButton_2.setSelected(true);
			break;

		}

		this.textField_barcode.setText(post.getBarcode());
		this.textField_fare.setText(post.getCollectionfare() + "");
		// timestamp ת��
		this.textField_estimatedtime.setText(DateUtil.getStringByDateTime(post.getPost_estimateddatetime()));
		// ���������ѯ
		// ��Ϊcollection��Ҫcollectionblservice�������ѯ
		this.comboBox_courier.setSelectedItem(makeUser(post.getUser()));

		this.textField_posttime.setText(DateUtil.getStringByDateTime(post.getPost_setupdatetime()));

	}

	/**
	 * ��ѯ��ť
	 */
	private void query() {
		// String []queries={"�����������","����ʱ��"};

		String text = this.textField_query.getText().trim();
		Vector<Post> posts;
		Vector<Vector> datas;
		String sql = "";
		String queryString = this.comboBox_query.getSelectedItem().toString();

		MainFrame.getLoglabel().setText("��ѯ����(��ѯ����:" + queryString + " " + text + ")");
		logger.info("��ѯ����(��ѯ����:" + queryString + " " + text + ")");

		switch (queryString) {
		case "�����������":
			posts = postblservice.getListByCondition("barcode", text);
			datas = changeDatas(posts);
			setDatas(datas);
			refreshTable();
			break;

		case "����ʱ��":
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

			sql = " select * from post , collection where post.collection_id_fk = collection.id "
					+ " and collection.collection_datetime > '" + today + "' and collection.collection_datetime < '"
					+ tomorrow + "' ";
			posts = postblservice.getListBySql(sql);
			datas = changeDatas(posts);
			setDatas(datas);
			refreshTable();
			break;

		}

	}

	/**
	 * ��ʼ��table����
	 */
	public void initColumns() {
		this.columns = new Vector();

		this.columns.add("id");
		this.columns.add("�����������");
		this.columns.add("�ļ���");
		this.columns.add("�ļ��˵�ַ");
		this.columns.add("�ռ���");
		this.columns.add("�ռ��˵�ַ");
		this.columns.add("�������");
		this.columns.add("����ʱ��");

	}

	/*
	 * ���ñ������
	 * 
	 * @see nju.express.ui.CommonPanel#setViewDatas()
	 */
	public void setViewDatas() {

		Vector<Post> posts = postblservice.getAll();
		// ת����ʾ��ʽ
		Vector<Vector> datas = changeDatas(posts);
		// ���ø��෽�����ý������
		setDatas(datas);

	}

	/**
	 * @param posts
	 *            �ļ�������
	 * @return table����
	 */
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
			v.add(post.getPost_type());
			v.add(DateUtil.getStringByDateTime(post.getPost_setupdatetime()));
			view.add(v);
		}
		return view;
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

	/*
	 * ��ս���
	 * 
	 * 
	 */
	@Override
	public void clear() {
		MainFrame.getLoglabel().setText("��ղ��½�");

		refreshTable();
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		this.comboBox_query.setSelectedIndex(0);
		this.textField_query.setText("");
		this.textField_postId.setText("");
		this.textField_senderId.setText("");
		this.textField_receiverId.setText("");
		this.textField_goodsId.setText("");

		this.textField_sender_name.setText("");
		this.textField_sender_address.setText("");
		this.textField_sender_phone.setText("");
		this.textField_sender_info.setText("");

		this.textField_receiver_name.setText("");
		this.textField_receiver_address.setText("");
		this.textField_receiver_phone.setText("");
		this.textField_receiver_info.setText("");

		this.textField_goods_name.setText("");
		this.textField_goods_num.setText("");
		this.textField_goods_weight.setText("");
		this.textField_goods_length.setText("");
		this.textField_goods_width.setText("");
		this.textField_goods_height.setText("");
		this.textField_goods_info.setText("");
		this.textField_volume.setText("");
		// ��ݷ�ʽĬ�Ͼ��ÿ��
		this.comboBox_postType.setSelectedItem("���ÿ��");

		// radioButtonĬ���ǵ�һ����ѡ��
		this.radioButton.setSelected(true);
		this.textField_fare.setText(getandSetCollectionFare() + "");

		this.textField_barcode.setText("");
		this.textField_fare.setText("");
		this.textField_estimatedtime.setText("");

		this.comboBox_courier.removeAllItems();
		addCourier();

		this.textField_posttime.setText("");

	}

	/**
	 * ����
	 */
	private void save() {
		// ���postId���ı���(����)��ֵΪ��, ��������, ����Ϊ�޸�

		if (this.textField_postId.getText().equals("")) {
			add();
		} else {
			update();
		}
	}

	/**
	 * ���
	 */
	private void add() {
		if (isTextExist() && isTextValidate()) {
			MainFrame.getLoglabel().setText("��������");

			Post post = getPost();
			int result = postblservice.add(post);
			// ���¶�ȡ����
			setViewDatas();
			// ˢ���б�, ��ձ�
			clear();
			// ֪ͨ��ӽ��
			if (result == 0) {
				MainFrame.getLoglabel().setText("��������ʧ��");
				logger.error("��������ʧ��");
			} else {
				MainFrame.getLoglabel().setText("���������ɹ�");
				logger.info("���������ɹ�");
			}
		}

	}

	/**
	 * ����
	 */
	private void update() {
		if (isTextExist() && isTextValidate()) {
			MainFrame.getLoglabel().setText("���¶���(�������" + this.textField_barcode.getText() + ")");
			Post post = getPost();
			int postId = Integer.parseInt(this.textField_postId.getText());
			int senderId = Integer.parseInt(this.textField_senderId.getText());
			int receiverId = Integer.parseInt(this.textField_receiverId.getText());
			int goodsId = Integer.parseInt(this.textField_goodsId.getText());

			post.setId(postId);

			post.setSender_id_fk(senderId);
			post.getSender().setId(senderId);

			post.setReceiver_id_fk(receiverId);
			post.getReceiver().setId(receiverId);

			post.setGoods_id_fk(goodsId);
			post.getGoods().setId(goodsId);

			boolean result = postblservice.update(post);
			// ���¶�ȡ����
			setViewDatas();
			// ˢ���б�, ��ձ�

			clear();
			// ֪ͨ���½��
			if (result) {
				MainFrame.getLoglabel().setText("���¶����ɹ�");
				logger.info("���¶����ɹ�");
			} else {
				MainFrame.getLoglabel().setText("���¶���ʧ��");
				logger.error("���¶���ʧ��");
			}
		}

	}

	/**
	 * ɾ��
	 */
	private void delete() {
		if (!this.textField_postId.getText().equals("")) {
			MainFrame.getLoglabel().setText("ɾ������(�������" + this.textField_barcode.getText() + ")");
			int postId = Integer.parseInt(this.textField_postId.getText());
			boolean result = postblservice.delete(postId);
			// ���¶�ȡ����
			setViewDatas();
			// ˢ���б�, ��ձ�
			clear();
			// ֪ͨɾ�����
			if (result) {
				MainFrame.getLoglabel().setText("ɾ�������ɹ�");
				logger.info("ɾ�������ɹ�");
			} else {
				MainFrame.getLoglabel().setText("ɾ������ʧ��");
				logger.error("ɾ������ʧ��");
			}
		} else {
			showWarn("δѡ��ļ���");
		}
	}

	/**
	 * �����ı�����post
	 * 
	 * @return post
	 */
	private Post getPost() {

		User courier = (User) this.comboBox_courier.getSelectedItem();
		return new Post(this.textField_barcode.getText(), courier.getId(),
				this.comboBox_postType.getSelectedItem().toString(), getPackingExpense(), getandSetCollectionFare(),
				getPostTimeStamp(), getEstimatedTimeStamp(), getSender(), getReceiver(), getGoods());
	}

	/**
	 * �����ı�����sender
	 * 
	 * @return sender
	 */
	private Sender getSender() {

		return new Sender(this.textField_sender_name.getText(), this.textField_sender_address.getText(),
				this.textField_sender_phone.getText(), this.textField_sender_info.getText());
	}

	/**
	 * �����ı�����receiver
	 * 
	 * @return ��receiver
	 */
	private Receiver getReceiver() {
		return new Receiver(this.textField_receiver_name.getText(), this.textField_receiver_address.getText(),
				this.textField_receiver_phone.getText(), this.textField_receiver_info.getText());
	}

	/**
	 * @return ���goods
	 */
	private Goods getGoods() {
		return new Goods(this.textField_goods_name.getText(), Integer.valueOf(this.textField_goods_num.getText()),
				Double.valueOf(this.textField_goods_weight.getText()),
				Double.valueOf(this.textField_goods_length.getText()),
				Double.valueOf(this.textField_goods_width.getText()),
				Double.valueOf(this.textField_goods_height.getText()), this.textField_goods_info.getText());
	}

	/**
	 * ���ĳЩ�����ı����Ƿ�Ϊ��
	 * 
	 * @return
	 */
	private boolean isTextExist() {

		if (this.textField_postId.getText().equals("")) {
			// ������
		}
		if (this.textField_sender_name.getText().equals("")) {
			showWarn("δ����ļ�������");
			return false;
		}
		;
		if (this.textField_sender_address.getText().equals("")) {
			showWarn("δ����ļ��˵�ַ");
			return false;
		}
		;
		if (this.textField_sender_phone.getText().equals("")) {
			showWarn("δ����ļ����ֻ�");
			return false;
		}
		;
		if (this.textField_sender_info.getText().equals("")) {
			// ������
		}
		;
		if (this.textField_receiver_name.getText().equals("")) {
			showWarn("δ�����ռ�������");
			return false;
		}
		;
		if (this.textField_receiver_address.getText().equals("")) {
			showWarn("δ�����ռ��˵�ַ");
			return false;
		}
		;
		if (this.textField_receiver_phone.getText().equals("")) {
			showWarn("δ�����ռ����ֻ�");
			return false;
		}
		;
		if (this.textField_receiver_info.getText().equals("")) {
			// ������
		}
		;
		if (this.textField_goods_name.getText().equals("")) {
			showWarn("δ�����������");
			return false;
		}
		if (this.textField_goods_num.getText().equals("")) {
			showWarn("δ�����������");
			return false;
		}
		if (this.textField_goods_weight.getText().equals("")) {
			showWarn("δ�����������");
			return false;
		}
		if (this.textField_goods_length.getText().equals("")) {
			showWarn("δ������ﳤ��");
			return false;
		}
		if (this.textField_goods_width.getText().equals("")) {
			showWarn("δ���������");
			return false;
		}
		if (this.textField_goods_height.getText().equals("")) {
			showWarn("δ�������߶�");
			return false;
		}
		if (this.textField_goods_info.getText().equals("")) {
			// ������
		}

		if (this.textField_barcode.getText().equals("")) {
			showWarn("δ���붩���������");
			return false;
		}
		if (this.textField_posttime.getText().equals("")) {
			showWarn("δ�����������");
			return false;
		}
		if (this.textField_estimatedtime.getText().equals("")) {
			showWarn("δ����Ԥ�Ƶ�������");
			return false;
		}
		return true;

	}

	/**
	 * ���ĳЩ�ı��������Ƿ���ϸ�ʽ
	 * 
	 * @return
	 */
	private boolean isTextValidate() {

		if (!isNumber(this.textField_sender_phone.getText())) {
			showWarn("�ļ����ֻ���ʽ����ȷ");
			return false;
		}
		;
		if (!isNumber(this.textField_receiver_phone.getText())) {
			showWarn("�ռ����ֻ���ʽ����ȷ");
			return false;
		}
		;
		if (!isNumber(this.textField_goods_num.getText())) {
			showWarn("����������ʽ����ȷ");
			return false;
		}
		;

		if (!isNumber(this.textField_goods_weight.getText())) {
			showWarn("����������ʽ����ȷ");
			return false;
		}
		;

		if (!isNumber(this.textField_goods_length.getText())) {
			showWarn("���ﳤ�ȸ�ʽ����ȷ");
			return false;
		}
		;

		if (!isNumber(this.textField_goods_width.getText())) {
			showWarn("�����ȸ�ʽ����ȷ");
			return false;
		}
		;

		if (!isNumber(this.textField_goods_height.getText())) {
			showWarn("����߶ȸ�ʽ����ȷ");
			return false;
		}

		if (!isNumber(this.textField_barcode.getText())) {

			showWarn("����������Ÿ�ʽ����ȷ");
			return false;
		}
		if (this.textField_barcode.getText().toString().length() != 10) {
			showWarn("����������ų��Ȳ���ȷ");
			return false;
		}
		if (getEstimatedTimeStamp().before(getPostTimeStamp())) {
			showWarn("ѡ���Ԥ�Ƶ���ʱ�䲻�����ڼļ�������ʱ��");
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
			dateFormat.parse(string);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	/**
	 * ��ð�װ��
	 * 
	 * @return ��װ��
	 */
	private int getPackingExpense() {
		if (this.radioButton.isSelected()) {
			return 5;
		}
		if (this.radioButton_1.isSelected()) {
			return 10;
		}
		// ʣ��radioButton_2
		return 1;
	}

	/**
	 * @return �ļ�������ʱ�䣬timestamp
	 */
	private Timestamp getPostTimeStamp() {
		Date posttime = DateUtil.getDateByDateTimeString(this.textField_posttime.getText());
		return new Timestamp(posttime.getTime());
	}

	/**
	 * @return Ԥ�Ƶ���ʱ��, timestamp
	 */
	private Timestamp getEstimatedTimeStamp() {
		Date estimatedtime = DateUtil.getDateByDateTimeString(this.textField_estimatedtime.getText());
		return new Timestamp(estimatedtime.getTime());
	}

	/**
	 * ����Ԥ�Ƶ���ʱ�䣬δʵ�֣�Ŀǰ�Ǽļ�������ʱ��+1��
	 * 
	 * @return
	 */
	private Timestamp getandSetEstimatedDatetime() {
		Date postdate = DateUtil.getDateByDateTimeString(this.textField_posttime.getText());
		Date newdate = DateUtil.getNextDateByDate(postdate);
		Timestamp estimatedtime = new Timestamp(newdate.getTime());
		this.textField_estimatedtime.setText(DateUtil.getStringByDateTime(estimatedtime));
		return estimatedtime;
	}

	/**
	 * ������úϼ�,δʵ�֣�Ŀǰ���ذ�װ�� +���ÿ��10.5����׼���20���ؿ�ר��35
	 * 
	 * @return
	 */
	private double getandSetCollectionFare() {
		// TODO
		double fare = 0;
		switch (this.comboBox_postType.getSelectedItem().toString()) {
		case "���ÿ��":
			fare = getPackingExpense() + 10.5;
			break;
		case "��׼���":
			fare = getPackingExpense() + 20;
			break;
		case "�ؿ�ר��":
			fare = getPackingExpense() + 35;
			break;
		default:
			break;
		}
		this.textField_fare.setText(fare + "");
		return fare;

	}

	/**
	 * ������� �����������Сѡ��λ
	 * 
	 * @return volume ���
	 */
	private double getandSetVolume() {
		double volume = Double.valueOf(this.textField_goods_length.getText())
				* Double.valueOf(this.textField_goods_width.getText())
				* Double.valueOf(this.textField_goods_height.getText());
		if (volume > 1000000) {
			this.textField_volume.setText(volume / 1000000 + " m(3)");
		} else if (volume > 1000) {
			this.textField_volume.setText(volume / 1000 + " dm(3)");
		} else {
			this.textField_volume.setText(volume + " cm(3)");
		}

		return volume;

	}

	/**
	 * �����������courier���Ա
	 */
	private void addCourier() {
		int department_id = MainFrame.getInstance().getUser().getDepartment_id_fk();
		Vector<User> users = postblservice.getCourier(department_id);
		for (User user : users) {
			this.comboBox_courier.addItem(makeUser(user));
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
		Vector<Post> posts = postblservice.getAll();
		tips.clear();
		for (Post post : posts) {
			tips.add(post.getBarcode());
			// ֻҪ���ڣ�����Ҫ����ʱ��
			tips.add(DateUtil.getStringByDate(post.getPost_setupdatetime()));
		}
		return tips;
	}
}
