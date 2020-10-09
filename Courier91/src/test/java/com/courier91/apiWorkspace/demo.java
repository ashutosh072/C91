package com.courier91.apiWorkspace;

public class demo {

	
	
	
	public static void main(String[] args) 
	{
	
		
		String otpString="7235";
		int num= Integer.parseInt(otpString);
		
		 
		 int n4 = num%10;
		 
		 int n3 = (num-n4)%100/10;
		 int n2 = (num-n4-n3)%1000/100;
		 int n1 = (num-n4-n3-n2)%10000/1000;
		 System.out.println(n4);
		 System.out.println(n3);
		 System.out.println(n2);
		 System.out.println(n1);
		 		
		
		 
	}
}
