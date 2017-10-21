package com.edureka.util;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import java.io.FileInputStream;

public class JMeterFromExistingJMX {

    public  void runJMX(String fileName) throws Exception {
        // JMeter Engine
        StandardJMeterEngine jmeter = new StandardJMeterEngine();
        PropertyReader propObj = new PropertyReader();
        // Initialize Properties, logging, locale, etc.
        String path = System.getenv("JMETER_HOME");
        JMeterUtils.loadJMeterProperties(path+"/bin/jmeter.properties");
        JMeterUtils.setJMeterHome(path);
        JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
        JMeterUtils.initLocale();

        // Initialize JMeter SaveService
        SaveService.loadProperties();

        // Load existing .jmx Test Plan
        FileInputStream in = new FileInputStream(propObj.getFilePath()+"\\JMX\\"+fileName+".jmx");
        @SuppressWarnings("deprecation")
		    HashTree testPlanTree = SaveService.loadTree(in);
        in.close();
        
        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (summariserName.length() > 0) {
            summer = new Summariser(summariserName);
        }
        
     // Store execution results into a .jtl file, we can save file as csv also
        String reportFile = propObj.getFilePath()+"\\JMX\\report.jtl";
        String csvFile = propObj.getFilePath()+"\\JMX\\report.csv";
        ResultCollector logger = new ResultCollector(summer);
        logger.setFilename(reportFile);
        ResultCollector csvlogger = new ResultCollector(summer);
        csvlogger.setFilename(csvFile);
        testPlanTree.add(testPlanTree.getArray()[0], logger);
        testPlanTree.add(testPlanTree.getArray()[0], csvlogger);

        // Run JMeter Test
        jmeter.configure(testPlanTree);
        jmeter.run();
    }
}
