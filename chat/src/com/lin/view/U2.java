package com.lin.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.ComponentOrientation;
import javax.swing.event.AncestorListener;

import com.lin.controller.ReceiveMessage;
import com.lin.controller.SendMessage;
import com.lin.domain.GetMessage;
import com.sun.org.apache.bcel.internal.classfile.InnerClass;
import com.sun.xml.internal.ws.resources.SenderMessages;

import javax.swing.event.AncestorEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;

public class U2 extends JFrame {
	
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	
	
	private String message="";//获取文本区域内容
	private String IP="localhost";
	private int from;//接收端口
	private int to;//发送到端口
	private int port;
	private boolean isStar=false;
	private JTextArea textArea_1;
	private JTextArea textArea;
	private GetMessage getMessage;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public int  successful() {
		if(from!=0&&port!=0&&to!=0) {
		return 1;
		}
		else {
			
			return 0;
		}
	}
	
	public U2() {
		getMessage=new GetMessage();
		
		setTitle("简易聊天");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 500, 215);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("楷体", Font.PLAIN, 18));
		textArea.setTabSize(0);
		textArea.setColumns(20);
		textArea.setRows(10);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("发送");
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					byte[] arr=message.getBytes();
				new Thread(new SendMessage(port,to,"localhost",message)).start(); ;
					message="";
					textArea_1.setText("");
			}
		});
		btnNewButton.setBounds(412, 328, 60, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!textField.getText().equals("")&&textField.getText()!=null) {
				getMessage.setTo(Integer.parseInt(textField.getText()));
				to=getMessage.getTo();
				}
				int r=successful();
				if(r==1&&isStar==false) {
					System.out.println("from"+from +" port:"+port+" to:"+to);
					new	Thread(new ReceiveMessage(to,from,textArea)).start();
					isStar=true;
				}
			}
		});
		textField.setBounds(195, 328, 70, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textField_1.getText()!=null&&!textField_1.getText().equals("")) {
					getMessage.setPort(Integer.parseInt(textField_1.getText()));
					port=getMessage.getPort();
					int r=successful();
					if(r==1&&isStar==false) {
						new	Thread(new ReceiveMessage(to,from,textArea)).start();
						isStar=true;
					}
				}
				
			}
		});
	
		
		textField_1.setBounds(50, 328, 70, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("发送端口");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(125, 329, 70, 18);
		contentPane.add(label);
		
		JLabel lblIp = new JLabel("port");
		lblIp.setHorizontalAlignment(SwingConstants.CENTER);
		lblIp.setBounds(12, 329, 42, 18);
		contentPane.add(lblIp);
		
		JLabel label_1 = new JLabel("接收端口");
		label_1.setBounds(274, 329, 55, 18);
		contentPane.add(label_1);
		
		textField_3 = new JTextField();
		textField_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(textField_3.getText()!=null&&!textField_3.getText().equals("")) {
					getMessage.setFrom(Integer.parseInt(textField_3.getText()));
				from=getMessage.getFrom();
				}
				int r=successful();
				if(r==1&&isStar==false) {
					System.out.println("from"+from +" port:"+port+" to:"+to);
					new	Thread(new ReceiveMessage(to,from,textArea)).start();
					isStar=true;
				}
			}
		});
		textField_3.setBounds(333, 328, 70, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("楷体", Font.PLAIN, 18));
	
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		textArea_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			if (textArea_1.getText()!=null&&!textArea_1.getText().equals("")) {
				message=textArea_1.getText();
			}
				
			
			}
		});
	
		textArea_1.setBounds(0, 228, 494, 74);
		contentPane.add(textArea_1);
		this.setResizable(false);
	}
}
