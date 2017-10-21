package com.edureka.registration;

import java.util.regex.Pattern;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class Test extends DriverTestCase {

	public static void main(String[] args) {
       
		String a = "abc";
		boolean res = false;
		res = Pattern.matches("a-x",a);
		
		System.out.println(res);
		
	}
 }


