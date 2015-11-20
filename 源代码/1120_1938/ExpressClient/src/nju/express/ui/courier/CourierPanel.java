package nju.express.ui.courier;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import nju.express.blservice.Postblservice;
import nju.express.po.User;

public class CourierPanel extends JPanel {
	
	private static final long serialVersionUID = 3083436208220174728L;

	/**
	 * Create the panel.
	 */
	/**
	 * Ŀǰ��Ϊֻд�˼ļ������棬ֻ������postblservice��������Ҫ����������blservice
	 * 
	 * @param postblservice
	 */
	public CourierPanel(Postblservice postblservice) {
		this.setSize(1280, 674);
		
		setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setFont(new Font("SansSerif", Font.PLAIN, 18));
		add(tabbedPane, BorderLayout.CENTER);
		
		PostPanel postPanel = new PostPanel(postblservice);
		tabbedPane.addTab("�ļ���", null, postPanel, "�½���鿴�ļ���");
		//TODO ����ҵ���߼�����
		RecipientPanel recipientPanel = new RecipientPanel();
		tabbedPane.addTab("�ռ���", null, recipientPanel, "��д�ռ���");

	}

}
