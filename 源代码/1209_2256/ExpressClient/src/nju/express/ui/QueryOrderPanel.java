package nju.express.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.apache.log4j.Logger;

import nju.express.blservice.Postblservice;
import nju.express.po.Post;
import nju.express.util.AutoTextFieldUtil;
import nju.express.util.DateUtil;
import nju.express.util.LoggerUtil;

public class QueryOrderPanel extends JPanel {
	static Logger logger = LoggerUtil.getLogger();
	Postblservice postblservice;
	private static final long serialVersionUID = -9075532013394070077L;
	private JPanel controlpanel;
	private JPanel contentpanel;
	private JTextField textField;
	Vector<String> tips = new Vector<String>();
	private JTextPane textPane;

	public QueryOrderPanel(Postblservice postblservice) {
		this.setSize(1280, 664);
		this.postblservice = postblservice;
		setLayout(null);

		controlpanel = new JPanel();
		controlpanel.setBounds(0, 0, 300, 664);
		add(controlpanel);
		controlpanel.setLayout(null);

		JLabel label = new JLabel("\u67E5\u8BE2\u7269\u6D41\u4FE1\u606F");
		label.setBounds(22, 102, 111, 18);
		controlpanel.add(label);

		textField = new JTextField();
		textField.setBounds(22, 154, 170, 30);
		getAllTips();
		AutoTextFieldUtil.setupAutoComplete(textField, tips);
		controlpanel.add(textField);
		textField.setColumns(10);

		JButton button_query = new JButton("\u67E5\u8BE2");
		button_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});
		button_query.setBounds(204, 154, 90, 30);
		controlpanel.add(button_query);
		contentpanel = new JPanel();
		contentpanel.setBounds(300, 0, 980, 664);
		add(contentpanel);
		contentpanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(69, 6, 905, 652);
		contentpanel.add(scrollPane);

		textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
	}

	private void query() {
		if (!this.textField.getText().equals("")) {
			this.textPane.setText("");
			String barcode = this.textField.getText();

			Vector<Post> posts = postblservice.getListByCondition("barcode", barcode);

			if (!posts.isEmpty()) {

				Post post = posts.get(0);

				// textPane.insertIcon(image); ͼƬ
				String tip = "����������ţ�" + post.getBarcode();
				setDocs(tip, Color.BLUE, true, 25);

				String posttime = DateUtil.getStringByTimeStamp(post.getPost_setupdatetime());
				String stringPost = " �ɿ��Ա:" + post.getUser().getUser_name() + " �����ļ���\n" + " �ļ��˵�ַ: "
						+ post.getSender().getSender_address() + "\n �ռ��˵�ַ: "
						+ post.getReceiver().getReceiver_address();
				setDocs(posttime, Color.BLACK, true, 20);
				setDocs(stringPost, Color.BLACK, false, 20);

				if (post.getBItransport_id_fk() != 0) {

					String BItime = DateUtil.getStringByTimeStamp(post.getbItransport().getBItransport_datetime());
					String stringBI = " �� " + post.getbItransport().getStart_department().getDepartment_name() + " ���� "
							+ post.getbItransport().getEnd_department().getDepartment_name();
					setDocs(BItime, Color.BLACK, true, 20);
					setDocs(stringBI, Color.BLACK, false, 20);
				}
				if (post.getStart_stock_id_fk() != 0) {

					String stocktime1 = DateUtil.getStringByTimeStamp(post.getStart_stock().getStockin_datetime());
					String stringstock1 = " �� " + post.getStart_stock().getDepartment().getDepartment_name() + " ���  ";
					setDocs(stocktime1, Color.BLACK, true, 20);
					setDocs(stringstock1, Color.BLACK, false, 20);
				}
				if (post.getIItransport_id_fk() != 0) {

					String IItime = DateUtil.getStringByTimeStamp(post.getiItransport().getIItransport_datetime());
					String stringII = " �� " + post.getiItransport().getStart_department().getDepartment_name()
							+ " ���Ⲣ���� " + post.getiItransport().getEnd_department().getDepartment_name();
					setDocs(IItime, Color.BLACK, true, 20);
					setDocs(stringII, Color.BLACK, false, 20);
				}
				if (post.getEnd_stock_id_fk() != 0) {

					String stocktime2 = DateUtil.getStringByTimeStamp(post.getEnd_stock().getStockin_datetime());
					String stringstock2 = " �� " + post.getEnd_stock().getDepartment().getDepartment_name() + " ��� ";
					setDocs(stocktime2, Color.BLACK, true, 20);
					setDocs(stringstock2, Color.BLACK, false, 20);
				}
				if (post.getIBtransport_id_fk() != 0) {

					String ibtime = DateUtil.getStringByTimeStamp(post.getiBtransport().getBItransport_datetime());
					String stringIb = " ��  " + post.getiBtransport().getStart_department().getDepartment_name()
							+ " ���Ⲣ����  " + post.getiBtransport().getEnd_department().getDepartment_name();
					setDocs(ibtime, Color.BLACK, true, 20);
					setDocs(stringIb, Color.BLACK, false, 20);
				}
				if (post.getDelivery_id_fk() != 0) {
					String deliverytime = DateUtil.getStringByTimeStamp(post.getDelivery().getDelivery_datetime());
					String stringdelivery = " ��  "
							+ post.getDelivery().getCourier().getDepartment().getDepartment_name() + " �ɿ��Ա  "
							+ post.getDelivery().getCourier().getUser_name() + " �ɼ�";
					setDocs(deliverytime, Color.BLACK, true, 20);
					setDocs(stringdelivery, Color.BLACK, false, 20);
				}
				if (post.getRecipient_id_fk() != 0) {
					String recipienttime = DateUtil.getStringByTimeStamp(post.getRecipient().getRecipient_datetime());
					String stringrecipient = " ��  " + post.getRecipient().getRecipient_name() + " ǩ��";
					setDocs(recipienttime, Color.BLACK, true, 20);
					setDocs(stringrecipient, Color.BLACK, false, 20);
				}

			} else {
				// ��ѯ��Post������
				// textPane.insertIcon(image); ͼƬ
				String wrong = "sorry������ѯ�Ķ���������ţ�" + barcode + " ������ ";
				setDocs(wrong, Color.RED, true, 25);
			}

		}
	}

	public void insert(String str, AttributeSet attrSet) {
		Document doc = textPane.getDocument();
		str = "\n" + str;
		try {
			doc.insertString(doc.getLength(), str, attrSet);
		} catch (BadLocationException e) {
			System.out.println("BadLocationException:   " + e);
		}
	}

	public void setDocs(String str, Color col, boolean bold, int fontSize) {
		SimpleAttributeSet attrSet = new SimpleAttributeSet();
		StyleConstants.setForeground(attrSet, col);
		// ��ɫ
		if (bold == true) {
			StyleConstants.setBold(attrSet, true);
		} // ��������
		StyleConstants.setFontSize(attrSet, fontSize);
		// �����С
		insert(str, attrSet);
	}

	private Vector<String> getAllTips() {
		Vector<Post> posts = postblservice.getAll();
		tips.clear();
		for (Post post : posts) {
			tips.add(post.getBarcode());
		}
		return tips;

	}
}
