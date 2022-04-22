package com.lin.controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.swing.JTextArea;




public class ReceiveMessage implements Runnable{

	private int from;
	private int to;
	private JTextArea jtextarea; 
	private int port;
	DatagramSocket ds;
	DatagramPacket dp;
	String text="";
	public ReceiveMessage(int port,int from,JTextArea jtextarea ) {

		this.from=from;
		this.jtextarea=jtextarea;
		this.port=port;
		try {
			ds=new DatagramSocket(from);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		byte[] buf=new byte[1024];
		
		while (true) {
			try {
				
					dp=new DatagramPacket(buf,buf.length); 
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(dp!=null) {
				try {
					ds.receive(dp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				String str=new String(dp.getData(),0,dp.getLength());
				System.out.println(port+":"+str);
				text=port+":"+str+"\n"+text;
				jtextarea.setText(text);
				if(str.equals("bye"))
				{
					break;
				}
			
				}
			
		}	
			ds.close();
		
	}

}

