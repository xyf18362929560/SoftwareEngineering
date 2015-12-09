package nju.express;

import java.awt.EventQueue;

import org.apache.log4j.Logger;

import nju.express.ui.MainFrame;
import nju.express.util.LoggerUtil;

public class Main {
	static Logger logger = LoggerUtil.getLogger();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = MainFrame.getInstance();
					frame.setVisible(true);
					logger.info("�ͻ���������...");
					MainFrame.getLoglabel().setText(" �ͻ��������ɹ�");
				} catch (Exception e) {
					logger.error("�ͻ�������ʧ��...");
					e.printStackTrace();
				}
			}
		});

	}

}
