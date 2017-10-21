package com.edureka.util;


import java.io.*;
import java.util.*;


public class PropertyReader
{	
    String path = getFilePath();
    String env = getEnvironment();

    /**
     * This method is used read Application property
     * 
     * @param key
     * @return
     */
    public String readApplicationFile(String key) {
        String value = "";
        try {
            Properties prop = new Properties();
            File f = new File(path +"//TestData//"+ env +"//application.properties");
            if (f.exists()) {
                prop.load(new FileInputStream(f));
                value = prop.getProperty(key);
            }
        } catch (Exception e) {
            System.out
            .println("Failed to read from application.properties file.");
        }
        return value;
    }

    /**
     * This method is used to get file path from local system
     * 
     * @return
     */
    public String getFilePath() {
        String filepath = "";
        File file = new File("");
        String absolutePathOfFirstFile = file.getAbsolutePath();
        filepath = absolutePathOfFirstFile.replaceAll("\\\\+", "/");
        return filepath;
    }

    /**
     * Read Test Data from TestData Property 
     * @param key
     * @return
     */
    public String readTestData(String key) {
        String value = "";
        try {
            Properties prop = new Properties();
            File f = new File(path + "//TestData//"+ env +"//testData.properties");
            if (f.exists()) {
                prop.load(new FileInputStream(f));
                value = prop.getProperty(key);
            }
        } catch (Exception e) {
            System.out.println("Failed to read from testData.propertiesfile.");
        }
        return value;
    }

    /**
     * This method is used update run time value in RunTime property file
     * 
     * @param key
     * @param value
     */
    public void updatePropertyTestData(String key, String value) {
        Properties props = new Properties();
        File f = new File(path + "//TestData//"+ env +"//runTimeValue.properties");
        try {
            final FileInputStream configStream = new FileInputStream(f);
            props.load(configStream);
            configStream.close();

            props.setProperty(key, value);

            final FileOutputStream output = new FileOutputStream(f);
            props.store(output, "");
            output.close();

        } catch (final IOException ex) {
            System.out.println(ex);
        }
    }
    /**
     * Read Test Data from TestData Property 
     * @param key
     * @return
     */
    public String readRunTimeData(String key) {
        String value = "";
        try {
            Properties prop = new Properties();
            File f = new File(path + "//TestData//"+ env +"//runTimeValue.properties");
            if (f.exists()) {
                prop.load(new FileInputStream(f));
                value = prop.getProperty(key);
            }
        } catch (Exception e) {
            System.out.println("Failed to read from runTimeValue.properties");
        }
        return value;
    }


    /**
     * This method is used read Application property
     * 
     * @param key
     * @return
     */
    public double readPerformanceData(String key) {
        String value = "";
        double data = 0.00;
        try {
            Properties prop = new Properties();
            File f = new File(path + "//TestData//"+ env +"//application.properties");
            if (f.exists()) {
                prop.load(new FileInputStream(f));
                value = prop.getProperty(key);
                data = Double.valueOf(value);
            }
        } catch (Exception e) {
            System.out
            .println("Failed to read from application.properties file.");
        }
        return data;
    }

    /**
     *  Update data at run time 
     * @param filename
     * @param key
     * @param value
     */
    public void updateProperty(String filename, String key,String value) {
        final Properties props = new Properties();
        File f = new File(path + "//TestData//"+ env +"//"+ filename);
        try {
            // first load old property file:
            final FileInputStream configStream = new FileInputStream(f);
            props.load(configStream);
            configStream.close();
            props.setProperty(key, value);
            final FileOutputStream output = new FileOutputStream(f);
            props.store(output, "");
            output.close();

        } catch (final IOException ex) {
            System.out.println(ex);
        }
    }

    public String getEnvironment() {
  //       return System.getProperty("env");
    	return "Test";
    }

}