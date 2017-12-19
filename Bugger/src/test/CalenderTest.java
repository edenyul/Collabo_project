package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;

public class CalenderTest /*extends Thread*/{
	public static void main(String[] args) {
	
		Calendar today=Calendar.getInstance();
		int num=0;
		boolean run=true;
		
		while(run) {
			/*try {*/
			today=Calendar.getInstance();
			
			String ampm=(today.get(Calendar.AM_PM)==0? "AM":"PM");
			String hour;
				if(today.get(Calendar.HOUR) == 0) hour = "12"; 
				else if(today.get(Calendar.HOUR) == 12) hour = " 0";
				else hour = (today.get(Calendar.HOUR)<10?" ":"")+today.get(Calendar.HOUR);
			String min = (today.get(Calendar.MINUTE)<10?"0":"")+today.get(Calendar.MINUTE);
			String sec = (today.get(Calendar.SECOND)<10?"0":"")+today.get(Calendar.SECOND);
			System.out.println(ampm+" "+hour+":"+min+":"+sec);

			/*sleep(1000);*/
			
			num++;
			
			if(num==60) {
				
			}
			
			/*} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		
		
	}
}
