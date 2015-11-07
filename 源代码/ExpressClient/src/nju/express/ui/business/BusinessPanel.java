package nju.express.ui.business;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import nju.express.blservice.Vehicleblservice;

/**这是营业厅界面，其tabpanel包含有 车辆司机管理界面， 收款单界面，装车管理界面
 * @author 徐亚帆
 * @time 2015年11月3日下午4:46:42
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
		tabbedPane.addTab("司机信息管理", null, businessDriverPanel, "新增、查看、修改、删除司机信息");
		
		BusinessVehiclePanel businessVehiclePanel=new BusinessVehiclePanel(vehicleblservice);
		tabbedPane.addTab("车辆信息管理", null, businessVehiclePanel, "新增、查看、修改、删除车辆信息");
		
	}

}
