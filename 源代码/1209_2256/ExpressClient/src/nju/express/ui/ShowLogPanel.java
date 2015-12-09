package nju.express.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ShowLogPanel extends JPanel {

	private static final long serialVersionUID = 1254967370457620698L;
	private JPanel controlpanel;
	private JPanel contentpanel;
	private JTextPane textPane;

	/**
	 * Create the panel.
	 */
	public ShowLogPanel() {
		this.setSize(1280, 664);
		setLayout(null);

		controlpanel = new JPanel();
		controlpanel.setBounds(0, 0, 300, 664);
		add(controlpanel);
		controlpanel.setLayout(null);

		JButton button = new JButton("\u67E5\u770B\u4FE1\u606F\u65E5\u5FD7");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showinfoLog();
			}
		});
		button.setBounds(93, 186, 132, 30);
		controlpanel.add(button);

		JButton button_1 = new JButton("\u67E5\u770B\u9519\u8BEF\u65E5\u5FD7");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showerrorLog();
			}
		});
		button_1.setBounds(93, 273, 132, 30);
		controlpanel.add(button_1);

		contentpanel = new JPanel();
		contentpanel.setBounds(300, 0, 980, 664);
		add(contentpanel);
		contentpanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 980, 664);
		contentpanel.add(scrollPane);

		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
	}

	private void showinfoLog() {
//		try {
//
//			File file = new File("e:/logs/a.txt");
//			if (file.isFile() && file.exists()) {
//				FileInputStream fi = new FileInputStream(file);
//				InputStreamReader is = new InputStreamReader(fi);
//				BufferedReader br = new BufferedReader(is);
//				String line = null;
//				while ((line = br.readLine()) != null) {
//					System.out.println(line.toString().trim());
//				}
//				br.close();
//				is.close();
//				fi.close();
//			} else {
//				showWarn("找不到指定的文件！");
//			}
//		} catch (Exception e) {
//			showWarn("读取文件内容操作出错");
//			e.printStackTrace();
//		}
		File file = new File("e:\\logs\\log.txt");
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
	}

	private void showerrorLog() {
		try {

			File file = new File("e:/logs/error.txt");
			if (file.isFile() && file.exists()) {
				FileInputStream fi = new FileInputStream(file);
				InputStreamReader is = new InputStreamReader(fi);
				BufferedReader br = new BufferedReader(is);
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line.toString().trim());
				}
				br.close();
				is.close();
				fi.close();
			} else {
				showWarn("找不到指定的文件！");
			}
		} catch (Exception e) {
			showWarn("读取文件内容操作出错");
			e.printStackTrace();
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
		// 颜色
		if (bold == true) {
			StyleConstants.setBold(attrSet, true);
		} // 字体类型
		StyleConstants.setFontSize(attrSet, fontSize);
		// 字体大小
		insert(str, attrSet);
	}

	// 显示警告
	protected int showWarn(String message) {
		return JOptionPane.showConfirmDialog(this, message, "警告", JOptionPane.OK_CANCEL_OPTION);
	}
}
