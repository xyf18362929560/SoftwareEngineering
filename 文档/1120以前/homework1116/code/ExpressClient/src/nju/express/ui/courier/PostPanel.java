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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.qt.datapicker.DatePicker;

import nju.express.blservice.Postblservice;
import nju.express.ui.utils.CommonJTable;
import nju.express.ui.utils.CommonPanel;
import nju.express.util.AutoTextFieldUtil;
import nju.express.util.DateTextField;
import nju.express.util.DateUtil;
import nju.express.vo.Collection;
import nju.express.vo.Goods;
import nju.express.vo.Post;
import nju.express.vo.Receiver;
import nju.express.vo.Sender;
import nju.express.vo.User;

/**
 * 该panel是快递员寄件单窗口
 * 
 * @author 徐亚帆
 * @time 2015年10月31日上午1:01:04
 */

public class PostPanel extends CommonPanel {
	// 需要的业务逻辑接口
	Postblservice postblservice;
	// Jtable的列名
	Vector columns;
	// 版本号
	private static final long serialVersionUID = -1714135907124920616L;
	// 隐藏的id框,因为一个post内含有4个其他实体类，每个都需要隐藏的id框存储其id
	private JTextField textField_postId = new JTextField("");
	private JTextField textField_senderId = new JTextField("");
	private JTextField textField_receiverId = new JTextField("");
	private JTextField textField_goodsId = new JTextField("");
	private JTextField textField_collectionId = new JTextField("");
	// 时间文本框
	private DateTextField textField_query;
	private DateTextField textField_estimatedtime;
	private DateTextField textField_posttime;
	// 各种文本框
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
	// 下拉列表
	private JComboBox comboBox_query;
	private JComboBox comboBox_postType;
	private JComboBox comboBox_courier;
	// RadioButton和ButtonGroup
	private ButtonGroup buttonGroup;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton_2;
	// 查询窗口提示字符串的集合
	Vector<String> tips = new Vector<String>();
	// 日期格式
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Create the panel.
	 */
	public PostPanel(Postblservice postblservice) {
		this.postblservice = postblservice;
		// 1203 674为默认规范大小
		this.setSize(1203, 674);
		// 初始化table的数据
		initColumns();
		setViewDatas();
		// 创建table
		DefaultTableModel model = new DefaultTableModel(getDatas(), this.columns);
		JTable table = new CommonJTable(model);
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

		// 定义查询文本框，为其制定日期格式，获得所有tips，为其设置自动补全
		textField_query = new DateTextField("yyyy-MM-dd");
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		textField_query.setColumns(10);

		JButton button_query = new JButton("查询");
		button_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JLabel label = new JLabel("寄件人姓名");

		textField_sender_name = new JTextField();
		textField_sender_name.setColumns(10);

		JLabel label_1 = new JLabel("寄件人地址");

		textField_sender_address = new JTextField();
		textField_sender_address.setColumns(10);

		JLabel label_2 = new JLabel("寄件人手机");

		JLabel label_3 = new JLabel("\u6536\u4EF6\u4EBA\u59D3\u540D");

		textField_receiver_name = new JTextField();
		textField_receiver_name.setColumns(10);

		JLabel label_4 = new JLabel("\u6536\u4EF6\u4EBA\u5730\u5740");

		textField_receiver_address = new JTextField();
		textField_receiver_address.setColumns(10);

		JLabel label_5 = new JLabel("\u6536\u4EF6\u4EBA\u624B\u673A");

		textField_receiver_phone = new JTextField();
		textField_receiver_phone.setColumns(10);

		JLabel label_6 = new JLabel("\u5BC4\u4EF6\u4EBA\u4FE1\u606F");

		textField_sender_info = new JTextField();
		textField_sender_info.setColumns(10);

		JLabel label_7 = new JLabel("\u6536\u4EF6\u4EBA\u4FE1\u606F");

		textField_receiver_info = new JTextField();
		textField_receiver_info.setColumns(10);

		JLabel label_8 = new JLabel("\u8D27\u7269\u540D\u79F0");

		textField_goods_name = new JTextField();
		textField_goods_name.setColumns(10);

		JLabel label_9 = new JLabel("\u8D27\u7269\u6570\u91CF");

		textField_goods_num = new JTextField();
		textField_goods_num.setColumns(10);

		JLabel label_10 = new JLabel("\u8D27\u7269\u91CD\u91CF(g)");

		textField_goods_weight = new JTextField();
		textField_goods_weight.setColumns(10);

		JLabel lblcm = new JLabel("\u957F(cm)");

		textField_goods_length = new JTextField();
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

		textField_goods_width = new JTextField();
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

		textField_goods_height = new JTextField();
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

		textField_goods_info = new JTextField();
		textField_goods_info.setColumns(10);

		JLabel label_12 = new JLabel("\u5FEB\u9012\u7C7B\u578B");

		String[] post_types = { Post.ECONOMY_EXPRESS, Post.STANDARD_EXPRESS, Post.SPECIAL_EXPRESS };
		comboBox_postType = new JComboBox(post_types);
		comboBox_postType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				getandSetCollectionFare();
			}
		});

		JLabel label_13 = new JLabel("\u5305\u88C5\u8D39");

		radioButton = new JRadioButton("纸箱(5元)");

		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getandSetCollectionFare();
			}
		});

		radioButton_1 = new JRadioButton("\u6728\u7BB1(10\u5143)");
		radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getandSetCollectionFare();
			}
		});

		radioButton_2 = new JRadioButton("\u5FEB\u9012\u888B(1\u5143)");
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

		textField_barcode = new JTextField();
		textField_barcode.setColumns(10);

		JLabel label_15 = new JLabel("费用合计(自动计算)");

		JSeparator separator = new JSeparator();

		textField_fare = new JTextField();
		textField_fare.setColumns(10);

		JLabel label_16 = new JLabel("\u9884\u8BA1\u5230\u8FBE\u65F6\u95F4");

		textField_estimatedtime = new DateTextField("yyyy-MM-dd");
		textField_estimatedtime.setColumns(10);

		textField_sender_phone = new JTextField();
		textField_sender_phone.setColumns(10);

		JLabel label_17 = new JLabel("\u5BC4\u4EF6\u5355\u5EFA\u7ACB\u4EBA");

		JLabel label_18 = new JLabel("\u5BC4\u4EF6\u5355\u5EFA\u7ACB\u65F6\u95F4");

		textField_posttime = new DateTextField("yyyy-MM-dd HH:mm:ss");
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

		JButton button_save = new JButton("保存");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		JButton button_clear = new JButton("清空");
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});

		comboBox_courier = new JComboBox();
		addCourier();

		JSeparator separator_2 = new JSeparator();

		JSeparator separator_3 = new JSeparator();

		JLabel label_14 = new JLabel("体积(自动计算)");

		textField_volume = new JTextField();
		textField_volume.setColumns(10);
		String[] queries = { "订单条形码号", "建立时间" };
		comboBox_query = new JComboBox(queries);

		JLabel label_19 = new JLabel("\u67E5\u8BE2\u65B9\u5F0F");

		JButton button_showAll = new JButton("显示所有");
		button_showAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setViewDatas();
				refreshTable();
			}
		});

		JButton btnTest = new JButton("选择时间");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatePicker dp = new DatePicker(textField_posttime, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_posttime.getText());
				dp.setSelectedDate(selectedDate);
				dp.start(textField_posttime);
			}
		});

		JButton button_1 = new JButton("选择时间");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatePicker dp = new DatePicker(textField_estimatedtime, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_estimatedtime.getText());

				dp.setSelectedDate(selectedDate);
				dp.start(textField_estimatedtime);
			}
		});
		// 查询功能日期选择按钮
		JButton button_2 = new JButton("选择日期");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_query.setSelectedItem("建立时间");
				DatePicker dp = new DatePicker(textField_query, Locale.CHINA);
				// previously selected date
				Date selectedDate = dp.parseDate(textField_query.getText());

				dp.setSelectedDate(selectedDate);
				dp.start(textField_query);
			}
		});

		GroupLayout gl_panel = new GroupLayout(this);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(
												scrollPane, GroupLayout.DEFAULT_SIZE, 1191, Short.MAX_VALUE))
										.addGroup(
												gl_panel.createSequentialGroup().addContainerGap()
														.addComponent(separator, GroupLayout.DEFAULT_SIZE, 1191,
																Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup().addGap(223).addComponent(label_19)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(comboBox_query, 0, 144, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(textField_query, GroupLayout.DEFAULT_SIZE, 265,
														Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(button_2)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(button_query, GroupLayout.PREFERRED_SIZE, 118,
														GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(button_showAll, GroupLayout.PREFERRED_SIZE, 115,
										GroupLayout.PREFERRED_SIZE).addGap(154))
						.addGroup(
								gl_panel.createSequentialGroup().addContainerGap()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(label, GroupLayout.PREFERRED_SIZE, 60,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(label_2))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(textField_sender_phone, GroupLayout.DEFAULT_SIZE, 207,
														Short.MAX_VALUE)
												.addComponent(textField_sender_name, GroupLayout.DEFAULT_SIZE, 207,
														Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(label_1)
										.addComponent(label_6))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textField_sender_info).addComponent(textField_sender_address,
												GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(separator_2,
								GroupLayout.DEFAULT_SIZE, 1191, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup().addComponent(label_5)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textField_receiver_phone, GroupLayout.DEFAULT_SIZE, 205,
														Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup().addComponent(label_3)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_receiver_name, GroupLayout.DEFAULT_SIZE, 205,
												Short.MAX_VALUE)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup().addComponent(label_4)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textField_receiver_address, GroupLayout.DEFAULT_SIZE, 842,
														Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup().addComponent(label_7)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textField_receiver_info, GroupLayout.DEFAULT_SIZE, 842,
														Short.MAX_VALUE))))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(separator_3,
								GroupLayout.DEFAULT_SIZE, 1191, Short.MAX_VALUE))
						.addGroup(
								gl_panel.createSequentialGroup().addContainerGap().addComponent(label_8)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textField_goods_name, GroupLayout.PREFERRED_SIZE, 210,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(label_9)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_goods_num, GroupLayout.PREFERRED_SIZE, 93,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(label_10)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_goods_weight, GroupLayout.DEFAULT_SIZE, 102,
												Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblcm)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_goods_length, GroupLayout.PREFERRED_SIZE, 55,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblcm_1)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_goods_width, GroupLayout.PREFERRED_SIZE, 62,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblcm_2)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_goods_height, GroupLayout.PREFERRED_SIZE, 57,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(label_14)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textField_volume,
												GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(label_11)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textField_goods_info, GroupLayout.DEFAULT_SIZE, 1131, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addGroup(gl_panel
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addComponent(label_18)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textField_posttime,
												GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup().addComponent(label_12).addGap(18)
										.addComponent(comboBox_postType, GroupLayout.PREFERRED_SIZE, 118,
												GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup().addComponent(btnTest).addGap(38)
												.addComponent(label_16).addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textField_estimatedtime, GroupLayout.PREFERRED_SIZE, 148,
														GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(button_1))
										.addGroup(gl_panel.createSequentialGroup().addComponent(label_13)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(radioButton)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(radioButton_1)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(radioButton_2)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup().addComponent(label_15)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textField_fare, GroupLayout.PREFERRED_SIZE, 98,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup().addComponent(label_17)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(comboBox_courier, GroupLayout.PREFERRED_SIZE, 98,
														GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(label_barcode)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField_barcode, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(separator_1,
								GroupLayout.DEFAULT_SIZE, 1191, Short.MAX_VALUE))
						.addGroup(
								gl_panel.createSequentialGroup().addGap(394)
										.addComponent(button_save, GroupLayout.PREFERRED_SIZE, 86,
												GroupLayout.PREFERRED_SIZE)
										.addGap(81)
										.addComponent(button_clear, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
										.addGap(71).addComponent(button_delete, GroupLayout.PREFERRED_SIZE, 84,
												GroupLayout.PREFERRED_SIZE)
										.addGap(398)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_19)
								.addComponent(comboBox_query, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_query, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(button_2).addComponent(button_query)
						.addComponent(button_showAll)).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label)
						.addComponent(textField_sender_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_sender_address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_2)
						.addComponent(textField_sender_phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_sender_info, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_3)
						.addComponent(textField_receiver_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_receiver_address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_5)
						.addComponent(textField_receiver_phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7).addComponent(textField_receiver_info, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_8)
						.addComponent(textField_goods_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_9)
						.addComponent(textField_goods_num, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_10)
						.addComponent(textField_goods_weight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_volume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_14)
						.addComponent(textField_goods_height, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblcm_2)
						.addComponent(textField_goods_width, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblcm_1)
						.addComponent(textField_goods_length, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblcm))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_11).addComponent(
						textField_goods_info, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)).addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_12).addComponent(label_15)
						.addComponent(textField_fare, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_barcode)
						.addComponent(textField_barcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_postType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_13).addComponent(radioButton).addComponent(radioButton_1)
						.addComponent(radioButton_2))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_courier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_17).addComponent(label_18)
						.addComponent(textField_posttime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTest)
						.addComponent(textField_estimatedtime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_16).addComponent(button_1)).addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE, false).addComponent(button_delete)
						.addComponent(button_save).addComponent(button_clear)).addGap(9)));

		scrollPane.setViewportView(table);
		setLayout(gl_panel);

	}

	/**
	 * 点击表格会显示详细信息
	 */
	private void view() {

		int id = getSelectId(getJTable());
		Post post = postblservice.getById(id);

		this.textField_postId.setText(id + "");
		this.textField_senderId.setText(post.getSender_id_fk() + "");
		this.textField_receiverId.setText(post.getReceiver_id_fk() + "");
		this.textField_goodsId.setText(post.getGoods_id_fk() + "");
		this.textField_collectionId.setText(post.getCollection_id_fk() + "");

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
		this.textField_fare.setText(post.getCollection().getCollection_amount() + "");
		// timestamp 转化
		this.textField_estimatedtime.setText(DateUtil.getStringByDateTime(post.getPost_estimateddatetime()));
		// 三次连表查询
		// 因为collection需要collectionblservice，连表查询
		this.comboBox_courier.setSelectedItem(makeUser(post.getCollection().getCourier_user()));

		this.textField_posttime.setText(DateUtil.getStringByDateTime(post.getCollection().getCollection_datetime()));

	}

	/**
	 * 查询按钮
	 */
	private void query() {
		// String []queries={"订单条形码号","建立时间"};
		String text = this.textField_query.getText().trim();
		Vector<Post> posts;
		Vector<Vector> datas;
		String sql = "";

		switch (this.comboBox_query.getSelectedItem().toString()) {
		case "订单条形码号":
			posts = postblservice.getListByCondition("barcode", text);
			datas = changeDatas(posts);
			setDatas(datas);
			refreshTable();
			break;

		case "建立时间":
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
	 * 初始化table列名
	 */
	public void initColumns() {
		this.columns = new Vector();

		this.columns.add("id");
		this.columns.add("订单条形码号");
		this.columns.add("寄件人");
		this.columns.add("寄件人地址");
		this.columns.add("收件人");
		this.columns.add("收件人地址");
		this.columns.add("快递类型");
		this.columns.add("寄送时间");

	}

	/*
	 * 设置表格数据
	 * 
	 * @see nju.express.ui.CommonPanel#setViewDatas()
	 */
	public void setViewDatas() {

		Vector<Post> posts = postblservice.getAll();
		// 转换显示格式
		Vector<Vector> datas = changeDatas(posts);
		// 调用父类方法设置结果集合
		setDatas(datas);

	}

	/**
	 * @param posts
	 *            寄件单集合
	 * @return table数据
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
			v.add(DateUtil.getStringByDateTime(post.getCollection().getCollection_datetime()));
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
	 * 清空界面
	 * 
	 * 
	 */
	@Override
	public void clear() {
		refreshTable();
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField_query, tips);
		this.comboBox_query.setSelectedIndex(0);
		this.textField_query.setText("");
		this.textField_postId.setText("");
		this.textField_senderId.setText("");
		this.textField_receiverId.setText("");
		this.textField_goodsId.setText("");
		this.textField_collectionId.setText("");

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
		// 快递方式默认经济快递
		this.comboBox_postType.setSelectedItem("经济快递");

		// radioButton默认是第一个被选中
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
	 * 保存
	 */
	private void save() {
		// 如果postId的文本框(隐藏)的值为空, 则是新增, 否则为修改
		
		System.out.println("save");
		if (this.textField_postId.getText().equals("")) {
			add();
		} else {
			update();
		}
	}

	/**
	 * 添加
	 */
	private void add() {
		if (isTextExist() && isTextValidate()) {
			System.out.println("add");
			Post post = getPost();
			postblservice.add(post);
			// 重新读取数据
			setViewDatas();
			// 刷新列表, 清空表单

			clear();
			System.out.println("add success");
		}

	}

	/**
	 * 更新
	 */
	private void update() {
		if (isTextExist() && isTextValidate()) {
			System.out.println("update");
			Post post = getPost();
			int postId = Integer.parseInt(this.textField_postId.getText());
			int senderId = Integer.parseInt(this.textField_senderId.getText());
			int receiverId = Integer.parseInt(this.textField_receiverId.getText());
			int goodsId = Integer.parseInt(this.textField_goodsId.getText());
			int collectionId = Integer.parseInt(this.textField_collectionId.getText());

			post.setId(postId);

			post.setSender_id_fk(senderId);
			post.getSender().setId(senderId);

			post.setReceiver_id_fk(receiverId);
			post.getReceiver().setId(receiverId);

			post.setGoods_id_fk(goodsId);
			post.getGoods().setId(goodsId);

			post.setCollection_id_fk(collectionId);
			post.getCollection().setId(collectionId);

			postblservice.update(post);
			// 重新读取数据
			setViewDatas();
			// 刷新列表, 清空表单

			clear();
			System.out.println("update success");
		}

	}

	/**
	 * 删除
	 */
	private void delete() {
		if (!this.textField_postId.getText().equals("")) {
			int postId = Integer.parseInt(this.textField_postId.getText());
			postblservice.delete(postId);
			// 重新读取数据
			setViewDatas();
			// 刷新列表, 清空表单

			clear();
		} else {
			showWarn("未选择寄件单");
		}
	}

	/**
	 * 根据文本框获得post
	 * 
	 * @return post
	 */
	private Post getPost() {
		return new Post(this.textField_barcode.getText(), this.comboBox_postType.getSelectedItem().toString(),
				getPackingExpense(), getEstimatedTimeStamp(), getSender(), getReceiver(), getGoods(), getCollection());

	}

	/**
	 * 根据文本框获得sender
	 * 
	 * @return sender
	 */
	private Sender getSender() {
		return new Sender(this.textField_sender_name.getText(), this.textField_sender_address.getText(),
				this.textField_sender_phone.getText(), this.textField_sender_info.getText());
	}

	/**
	 * 根据文本框获得receiver
	 * 
	 * @return 得receiver
	 */
	private Receiver getReceiver() {
		return new Receiver(this.textField_receiver_name.getText(), this.textField_receiver_address.getText(),
				this.textField_receiver_phone.getText(), this.textField_receiver_info.getText());
	}

	/**
	 * @return 获得goods
	 */
	private Goods getGoods() {
		return new Goods(this.textField_goods_name.getText(), Integer.valueOf(this.textField_goods_num.getText()),
				Double.valueOf(this.textField_goods_weight.getText()),
				Double.valueOf(this.textField_goods_length.getText()),
				Double.valueOf(this.textField_goods_width.getText()),
				Double.valueOf(this.textField_goods_height.getText()), this.textField_goods_info.getText());
	}

	/**
	 * @return 获得collection
	 */
	private Collection getCollection() {
		User courier = (User) this.comboBox_courier.getSelectedItem();
		return new Collection(getPostTimeStamp(), getandSetCollectionFare(), courier.getId());
	}

	/**
	 * 检查某些必填文本框是否为空
	 * 
	 * @return
	 */
	private boolean isTextExist() {

		if (this.textField_postId.getText().equals("")) {
			// 不处理
		}
		if (this.textField_sender_name.getText().equals("")) {
			showWarn("未输入寄件人姓名");
			return false;
		}
		;
		if (this.textField_sender_address.getText().equals("")) {
			showWarn("未输入寄件人地址");
			return false;
		}
		;
		if (this.textField_sender_phone.getText().equals("")) {
			showWarn("未输入寄件人手机");
			return false;
		}
		;
		if (this.textField_sender_info.getText().equals("")) {
			// 不处理
		}
		;
		if (this.textField_receiver_name.getText().equals("")) {
			showWarn("未输入收件人姓名");
			return false;
		}
		;
		if (this.textField_receiver_address.getText().equals("")) {
			showWarn("未输入收件人地址");
			return false;
		}
		;
		if (this.textField_receiver_phone.getText().equals("")) {
			showWarn("未输入收件人手机");
			return false;
		}
		;
		if (this.textField_receiver_info.getText().equals("")) {
			// 不处理
		}
		;
		if (this.textField_goods_name.getText().equals("")) {
			showWarn("未输入货物名称");
			return false;
		}
		if (this.textField_goods_num.getText().equals("")) {
			showWarn("未输入货物数量");
			return false;
		}
		if (this.textField_goods_weight.getText().equals("")) {
			showWarn("未输入货物重量");
			return false;
		}
		if (this.textField_goods_length.getText().equals("")) {
			showWarn("未输入货物长度");
			return false;
		}
		if (this.textField_goods_width.getText().equals("")) {
			showWarn("未输入货物宽度");
			return false;
		}
		if (this.textField_goods_height.getText().equals("")) {
			showWarn("未输入货物高度");
			return false;
		}
		if (this.textField_goods_info.getText().equals("")) {
			// 不处理
		}

		if (this.textField_barcode.getText().equals("")) {
			showWarn("未输入订单条形码号");
			return false;
		}
		if (this.textField_posttime.getText().equals("")) {
			showWarn("未输入寄送日期");
			return false;
		}
		if (this.textField_estimatedtime.getText().equals("")) {
			showWarn("未输入预计到达日期");
			return false;
		}
		return true;

	}

	/**
	 * 检查某些文本框内容是否符合格式
	 * 
	 * @return
	 */
	private boolean isTextValidate() {

		if (!isNumber(this.textField_sender_phone.getText())) {
			showWarn("寄件人手机格式不正确");
			return false;
		}
		;
		if (!isNumber(this.textField_receiver_phone.getText())) {
			showWarn("收件人手机格式不正确");
			return false;
		}
		;
		if (!isNumber(this.textField_goods_num.getText())) {
			showWarn("货物数量格式不正确");
			return false;
		}
		;

		if (!isNumber(this.textField_goods_weight.getText())) {
			showWarn("货物重量格式不正确");
			return false;
		}
		;

		if (!isNumber(this.textField_goods_length.getText())) {
			showWarn("货物长度格式不正确");
			return false;
		}
		;

		if (!isNumber(this.textField_goods_width.getText())) {
			showWarn("货物宽度格式不正确");
			return false;
		}
		;

		if (!isNumber(this.textField_goods_height.getText())) {
			showWarn("货物高度格式不正确");
			return false;
		}

		if (!isNumber(this.textField_barcode.getText())) {

			showWarn("订单条形码号格式不正确");
			return false;
		}
		if (this.textField_barcode.getText().toString().length() != 10) {
			showWarn("订单条形码号长度不正确");
			return false;
		}
		if (getEstimatedTimeStamp().before(getPostTimeStamp())) {
			showWarn("选择的预计到达时间不能早于寄件单建立时间");
			return false;
		}
		return true;
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

	/**
	 * 检查字符串是否是日期
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
	 * 获得包装费
	 * 
	 * @return 包装费
	 */
	private int getPackingExpense() {
		if (this.radioButton.isSelected()) {
			return 5;
		}
		if (this.radioButton_1.isSelected()) {
			return 10;
		}
		// 剩下radioButton_2
		return 1;
	}

	/**
	 * @return 寄件单建立时间，timestamp
	 */
	private Timestamp getPostTimeStamp() {
		Date posttime = DateUtil.getDateByDateTimeString(this.textField_posttime.getText());
		return new Timestamp(posttime.getTime());
	}

	/**
	 * @return 预计到达时间, timestamp
	 */
	private Timestamp getEstimatedTimeStamp() {
		Date estimatedtime = DateUtil.getDateByDateTimeString(this.textField_estimatedtime.getText());
		return new Timestamp(estimatedtime.getTime());
	}

	/**
	 * 计算预计到达时间，未实现，目前是寄件单建立时间+1天
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
	 * 计算费用合计,未实现，目前返回包装费 +经济快递10.5，标准快递20，特快专递35
	 * 
	 * @return
	 */
	private double getandSetCollectionFare() {
		double fare = 0;
		switch (this.comboBox_postType.getSelectedItem().toString()) {
		case "经济快递":
			fare = getPackingExpense() + 10.5;
			break;
		case "标准快递":
			fare = getPackingExpense() + 20;
			break;
		case "特快专递":
			fare = getPackingExpense() + 35;
			break;
		default:
			break;
		}
		this.textField_fare.setText(fare + "");
		return fare;

	}

	/**
	 * 计算体积 ，根据体积大小选择单位
	 * 
	 * @return volume 体积
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
	 * 向下拉框添加courier快递员
	 */
	private void addCourier() {
		Vector<User> users = postblservice.getCourier();
		for (User user : users) {
			this.comboBox_courier.addItem(makeUser(user));
		}
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

	/**
	 * 获得所有的tips提示，自动补全查询文本框
	 * 
	 * @return
	 */
	private Vector<String> getAllTips() {
		Vector<Post> posts = postblservice.getAll();
		tips.clear();
		for (Post post : posts) {
			tips.add(post.getBarcode());
			// 只要日期，不需要具体时间
			tips.add(DateUtil.getStringByDate(post.getCollection().getCollection_datetime()));
		}
		return tips;
	}
}
