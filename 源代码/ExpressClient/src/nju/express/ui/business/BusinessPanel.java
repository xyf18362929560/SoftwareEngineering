package nju.express.ui.business;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import nju.express.blservice.Vehicleblservice;

/**����Ӫҵ�����棬��tabpanel������ ����˾��������棬 �տ���棬װ���������
 * @author ���Ƿ�
 * @time 2015��11��3������4:46:42
 */
public class BusinessPanel extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2122594159818817163L;

	/**
	 * Create the panel.
	 */
	public BusinessPanel(Vehicleblservice vehicleblservice) {
		this.setSize(1280, 674);
		
		setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setFont(new Font("SansSerif", Font.PLAIN, 18));
		add(tabbedPane, BorderLayout.CENTER);
		
		
		BusinessDriverPanel businessDriverPanel=new BusinessDriverPanel();
		tabbedPane.addTab("˾����Ϣ����", null, businessDriverPanel, "�������鿴���޸ġ�ɾ��˾����Ϣ");
		
		BusinessVehiclePanel businessVehiclePanel=new BusinessVehiclePanel(vehicleblservice);
		tabbedPane.addTab("������Ϣ����", null, businessVehiclePanel, "�������鿴���޸ġ�ɾ��������Ϣ");
		
	}

}
