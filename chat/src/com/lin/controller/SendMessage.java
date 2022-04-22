package com.lin.controller;

	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.net.DatagramPacket;
	import java.net.DatagramSocket;
	import java.net.InetAddress;
	import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JTextArea;



	public class SendMessage implements Runnable {
			private	String name;
			private int from;
			private int to;
			private String ip;
			DatagramSocket ds;
			String Message="";
		
			
			
			
		public SendMessage(int from,int to,String ip,String message) {
			
			this.from=from;
			this.to=to;
			this.ip=ip;
			this.Message=message;
			try {
				ds=new DatagramSocket(from);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		DatagramPacket dp=null;
		@Override
		public void run() {
					
				try {
					
					String str=Message;
					byte[] arr=str.getBytes();
					dp=new DatagramPacket(arr,0,arr.length,InetAddress.getByName(ip),to);
					ds.send(dp);
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			ds.close();
			
		}


	}


