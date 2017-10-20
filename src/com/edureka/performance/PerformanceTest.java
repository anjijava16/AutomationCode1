package com.edureka.performance;

import org.testng.annotations.Test;
import com.edureka.util.DriverTestCase;
import com.edureka.util.JMeterFromExistingJMX;
import com.edureka.util.SpreadsheetOperations;

public class PerformanceTest extends DriverTestCase{
    private SpreadsheetOperations spreadsheetOperations;
    private JMeterFromExistingJMX jmeter;

    @Test
    public void test_PerformanceTest() throws Exception {

        try {

        	spreadsheetOperations = new SpreadsheetOperations();
        	jmeter = new JMeterFromExistingJMX();
        	
        	//Performance Check for Home Page
        	addLog("Performance Check for Home Page");
        	jmeter.runJMX("HomePage");
        	spreadsheetOperations.getPagePerformance("HomePage");
        	
        	//Performance Check for All Courses Page
        	addLog("Performance Check for All Courses Page");
        	jmeter.runJMX("AllCoursesPage");
        	spreadsheetOperations.getPagePerformance("AllCoursesPage");
        	
        	//Performance Check for CLP - BigData And Hadoop Page
        	addLog("Performance Check for CLP - BigData And Hadoop Page");
        	jmeter.runJMX("BigDataAndHadoop");
        	spreadsheetOperations.getPagePerformance("BigDataAndHadoop");
        	
        	//Performance Check for CLP- data-science Page
        	addLog("Performance Check for CLP- data-science  Page");
        	jmeter.runJMX("DataScience");
        	spreadsheetOperations.getPagePerformance("DataScience");
        	
        	//Performance Check for CLP-apache-spark-scala-training Page
        	addLog("Performance Check for CLP-apache-spark-scala-training  Page");
        	jmeter.runJMX("ApacheSparkScalaTraining");
        	spreadsheetOperations.getPagePerformance("ApacheSparkScalaTraining");
        	
        	//Performance Check for CLP - cloud computing Page
        	addLog("Performance Check for CLP - cloud computing Page");
        	jmeter.runJMX("CloudComputing");
        	spreadsheetOperations.getPagePerformance("CloudComputing");
        	
        	//Performance Check for CLP - PMP Page
        	addLog("Performance Check for CLP - PMP Page");
        	jmeter.runJMX("PMP");
        	spreadsheetOperations.getPagePerformance("PMP");

        }
        catch (final Error e) {
            captureScreenshot("test_PerformanceTest");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_PerformanceTest");
            throw e;
        }
    }
    
}