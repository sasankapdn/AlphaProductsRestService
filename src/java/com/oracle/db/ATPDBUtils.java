/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import oracle.jdbc.pool.OracleDataSource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author sasanka
 */
public class ATPDBUtils {

    private static String INSTANCE = "";
    private static String CREDENTIALS = "";
    private static String USER = "";
    private static String PASSWORD = "";
    private static String URL = "";

    public static JSONArray getProducts() {
        readProperties();
        JSONArray products = new JSONArray();
        try {
            OracleDataSource ODS = new OracleDataSource();

            //ODS.setURL("jdbc:oracle:thin:@" + INSTANCE + "?TNS_ADMIN=" + CREDENTIALS);
            ODS.setURL(URL);
            ODS.setUser(USER);
            ODS.setPassword(PASSWORD);
            //ODS.getConnection();
            Connection conn = ODS.getConnection();
            System.out.println("Connection test Succeeded. You are connected to ATP as Admin!");
            Statement stmt = conn.createStatement();

            // Select all from the products table
            ResultSet rset = stmt.executeQuery("SELECT * FROM PRODUCTS");
            JSONObject obj;// = new JSONObject();

            while (rset.next()) {
                obj = new JSONObject();
                obj.put("PRODUCT_ID", rset.getInt("PRODUCT_ID"));
                obj.put("PARENT_CATEGORY_ID", rset.getInt("PARENT_CATEGORY_ID"));
                obj.put("CATEGORY_ID", rset.getInt("CATEGORY_ID"));
                obj.put("PRODUCT_NAME", rset.getString("PRODUCT_NAME"));
                obj.put("PRODUCT_STATUS", rset.getString("PRODUCT_STATUS"));
                obj.put("COST_PRICE", rset.getInt("COST_PRICE"));
                obj.put("LIST_PRICE", rset.getInt("LIST_PRICE"));
                obj.put("MIN_PRICE", rset.getInt("MIN_PRICE"));
                obj.put("WARRANTY_PERIOD_MONTHS", rset.getInt("WARRANTY_PERIOD_MONTHS"));
                obj.put("EXTERNAL_URL", rset.getString("EXTERNAL_URL"));
                obj.put("ATTRIBUTE_CATEGORY", rset.getString("ATTRIBUTE_CATEGORY"));
                obj.put("ATTRIBUTE1", rset.getString("ATTRIBUTE1"));
                obj.put("ATTRIBUTE2", rset.getString("ATTRIBUTE2"));
                obj.put("ATTRIBUTE3", rset.getString("ATTRIBUTE3"));
                obj.put("ATTRIBUTE4", rset.getString("ATTRIBUTE4"));
                obj.put("ATTRIBUTE5", rset.getString("ATTRIBUTE5"));
                obj.put("CREATED_BY", rset.getString("CREATED_BY"));
                obj.put("CREATION_DATE", rset.getString("CREATION_DATE"));
                obj.put("LAST_UPDATED_BY", rset.getString("LAST_UPDATED_BY"));
                obj.put("LAST_UPDATE_DATE", rset.getString("LAST_UPDATE_DATE"));
                obj.put("OBJECT_VERSION_ID", rset.getInt("OBJECT_VERSION_ID"));

                products.add(obj);

                System.out.println("Product ID is " + rset.getString(1) + " " + rset.getString(2) + " " + rset.getString(3) + " " + " " + rset.getString(4));

            }
            // Close the RseultSet
            rset.close();
            rset = null;

            // Close the Statement
            stmt.close();
            stmt = null;

            conn.close();
            return products;

        } catch (SQLException e) {
            System.out.println("Connection Unsuccessful with errror " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }

    public static JSONArray getProducts(int prodID) {
        readProperties();
        JSONArray products = new JSONArray();
        try {
            OracleDataSource ODS = new OracleDataSource();

            ODS.setURL("jdbc:oracle:thin:@" + INSTANCE + "?TNS_ADMIN=" + CREDENTIALS);
            ODS.setUser(USER);
            ODS.setPassword(PASSWORD);
            //ODS.getConnection();
            Connection conn = ODS.getConnection();
            System.out.println("Connection test Succeeded. You are connected to ATP as Admin!");
            Statement stmt = conn.createStatement();

            // Select the all columns from the products table for given prodID
            ResultSet rset = stmt.executeQuery("SELECT * FROM PRODUCTS WHERE PRODUCT_ID=" + prodID);
            JSONObject obj;// = new JSONObject();

            // Iterate through the product and insert into JSONObject
            while (rset.next()) {
                obj = new JSONObject();
                obj.put("PRODUCT_ID", rset.getInt("PRODUCT_ID"));
                obj.put("PARENT_CATEGORY_ID", rset.getInt("PARENT_CATEGORY_ID"));
                obj.put("CATEGORY_ID", rset.getInt("CATEGORY_ID"));
                obj.put("PRODUCT_NAME", rset.getString("PRODUCT_NAME"));
                obj.put("PRODUCT_STATUS", rset.getString("PRODUCT_STATUS"));
                obj.put("COST_PRICE", rset.getInt("COST_PRICE"));
                obj.put("LIST_PRICE", rset.getInt("LIST_PRICE"));
                obj.put("MIN_PRICE", rset.getInt("MIN_PRICE"));
                obj.put("WARRANTY_PERIOD_MONTHS", rset.getInt("WARRANTY_PERIOD_MONTHS"));
                obj.put("EXTERNAL_URL", rset.getString("EXTERNAL_URL"));
                obj.put("ATTRIBUTE_CATEGORY", rset.getString("ATTRIBUTE_CATEGORY"));
                obj.put("ATTRIBUTE1", rset.getString("ATTRIBUTE1"));
                obj.put("ATTRIBUTE2", rset.getString("ATTRIBUTE2"));
                obj.put("ATTRIBUTE3", rset.getString("ATTRIBUTE3"));
                obj.put("ATTRIBUTE4", rset.getString("ATTRIBUTE4"));
                obj.put("ATTRIBUTE5", rset.getString("ATTRIBUTE5"));
                obj.put("CREATED_BY", rset.getString("CREATED_BY"));
                obj.put("CREATION_DATE", rset.getString("CREATION_DATE"));
                obj.put("LAST_UPDATED_BY", rset.getString("LAST_UPDATED_BY"));
                obj.put("LAST_UPDATE_DATE", rset.getString("LAST_UPDATE_DATE"));
                obj.put("OBJECT_VERSION_ID", rset.getInt("OBJECT_VERSION_ID"));

                products.add(obj);

                System.out.println("Product ID is " + rset.getString(1) + " " + rset.getString(2) + " " + rset.getString(3) + " " + " " + rset.getString(4));

            }
            // Close the RseultSet
            rset.close();
            rset = null;

            // Close the Statement
            stmt.close();
            stmt = null;

            conn.close();
            return products;

        } catch (SQLException e) {
            System.out.println("Connection Unsuccessful with errror " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }

    public static void saveProducts(String prodname, double listPrice) {
        readProperties();
        try {
            OracleDataSource ODS = new OracleDataSource();

            ODS.setURL("jdbc:oracle:thin:@" + INSTANCE + "?TNS_ADMIN=" + CREDENTIALS);
            ODS.setUser(USER);
            ODS.setPassword(PASSWORD);
            //ODS.getConnection();
            Connection conn = ODS.getConnection();
            System.out.println("Connection test Succeeded. You are connected to ATP as Admin!");
            Statement stmt = conn.createStatement();

            String url = "https://objectstorage.us-ashburn-1.oraclecloud.com/p/L-VE2JhzKArznQyrCcWULYZSXGgP3KYIQoiLKDCnBrY/n/natdcshjumpstartprod/b/AlphaOffice-images/o/1038-Write-Expo_Dry_Erase_12.jpg";
            // Execute update statement
            stmt.executeUpdate("INSERT INTO PRODUCTS (PRODUCT_ID, PARENT_CATEGORY_ID, CATEGORY_ID, PRODUCT_NAME, PRODUCT_STATUS, COST_PRICE, LIST_PRICE, MIN_PRICE, warranty_period_months, EXTERNAL_URL, ATTRIBUTE_CATEGORY, ATTRIBUTE1, ATTRIBUTE2, ATTRIBUTE3, ATTRIBUTE4, ATTRIBUTE5, CREATED_BY, CREATION_DATE, LAST_UPDATED_BY, LAST_UPDATE_DATE, OBJECT_VERSION_ID) VALUES ('', 10003.0, 10012.0, '" + prodname + "', 'AVAILABLE', 3.49, '" + listPrice + "', 1.53, '6', '" + url + "', '', '', '', '', '', '6', '0', to_date('10-JAN-14', 'DD-MON-RR'), '0', to_date('10-JAN-14', 'DD-MON-RR'), 1.0)");

            // Close the Statement
            stmt.close();
            stmt = null;
            conn.close();
        } catch (SQLException e) {
            System.out.println("Connection Unsuccessful with errror " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void deleteProducts(int prodID) {
        readProperties();
        JSONArray products = new JSONArray();
        try {
            OracleDataSource ODS = new OracleDataSource();

            ODS.setURL("jdbc:oracle:thin:@" + INSTANCE + "?TNS_ADMIN=" + CREDENTIALS);
            ODS.setUser(USER);
            ODS.setPassword(PASSWORD);
            //ODS.getConnection();
            Connection conn = ODS.getConnection();
            System.out.println("Connection test Succeeded. You are connected to ATP as Admin!");
            Statement stmt = conn.createStatement();

            // Delete row from products table
            ResultSet rset = stmt.executeQuery("DELETE FROM PRODUCTS WHERE PRODUCT_ID=" + prodID);
            JSONObject obj;//
            // Close the Result set
            rset.close();
            rset = null;

            // Close the Statement
            stmt.close();
            stmt = null;

            conn.close();

        } catch (SQLException e) {
            System.out.println("Connection Unsuccessful with errror " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateProducts(int prodID, String prodname, double listPrice) {
        readProperties();
        try {
            OracleDataSource ODS = new OracleDataSource();

            ODS.setURL("jdbc:oracle:thin:@" + INSTANCE + "?TNS_ADMIN=" + CREDENTIALS);
            ODS.setUser(USER);
            ODS.setPassword(PASSWORD);
            //ODS.getConnection();
            Connection conn = ODS.getConnection();
            System.out.println("Connection test Succeeded. You are connected to ATP as Admin!");
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("update PRODUCTS set LIST_PRICE='" + listPrice + "',PRODUCT_NAME='" + prodname + "' where PRODUCT_ID=" + prodID);

            // Close the Statement
            stmt.close();
            stmt = null;
            conn.close();
        } catch (SQLException e) {
            System.out.println("Connection Unsuccessful with errror " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateProducts(int prodID, double listPrice) {
        readProperties();
        try {
            OracleDataSource ODS = new OracleDataSource();

            ODS.setURL("jdbc:oracle:thin:@" + INSTANCE + "?TNS_ADMIN=" + CREDENTIALS);
            ODS.setUser(USER);
            ODS.setPassword(PASSWORD);
            //ODS.getConnection();
            Connection conn = ODS.getConnection();
            System.out.println("Connection test Succeeded. You are connected to ATP as Admin!");
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("update PRODUCTS set LIST_PRICE='" + listPrice + "' where PRODUCT_ID=" + prodID);
            // Close the Statement
            stmt.close();
            stmt = null;
            conn.close();
        } catch (SQLException e) {
            System.out.println("Connection Unsuccessful with errror " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void readProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = ATPDBUtils.class.getClassLoader().getResourceAsStream("/com/oracle/db/dbconfig.properties");
            //input = WSProductClient.class.getResource("/com.oracle/dbconfig.properties");
            prop.load(input);
            INSTANCE = prop.getProperty("dbinstance");
            USER = prop.getProperty("dbuser");
            PASSWORD = prop.getProperty("dbpassword");
            CREDENTIALS = prop.getProperty("dbcredpath");
            URL = prop.getProperty("url");
        } catch (IOException e) {

        }
    }
}
