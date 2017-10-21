package com.edureka.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer {
    private long startTime = 0;
    private long endTime = 0;
    private long timeDiff = 0;

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public void end() {
        this.endTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public long getTotalTime() {
    	timeDiff = (this.endTime - this.startTime)/1000;
    	return timeDiff;
    }
	public long calculateDays(String date1,String date2){
		try {
			date1=date1.split("-")[1]+"/"+date1.split("-")[2].split(" ")[0]+"/"+date1.split("-")[0]+" "+date1.split("-")[2].split(" ")[1];
			date2=date2.split("-")[1]+"/"+date2.split("-")[2].split(" ")[0]+"/"+date2.split("-")[0]+" "+date2.split("-")[2].split(" ")[1];
			System.out.println("Date1 ::: "+date1);
			System.out.println("Date1 ::: "+date2);
			SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");	
			    Date date11 = myFormat.parse(date1);
			    Date date12 = myFormat.parse(date2);
			    long diff = date12.getTime() - date11.getTime();
	            long diffDays = diff / (24 * 60 * 60 * 1000);
			    return diffDays;
			} catch (ParseException e) {
			    e.printStackTrace();
			    return 0;
			}
	}	
	public String getCurrentDate(){
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		return modifiedDate;	
	}
}
