package com.example.demo;

import java.io.*;
import java.sql.*;
//import java.awt.*;  -
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javafx.stage.*;
public class CSV {
    public static void CSVimport(){
        String databaseName = "scannadb";
        String jdbcURL = "jdbc:mysql://localhost/" + databaseName;
        String username = "root";
        String password = "Scanna123.";


        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        chooser.getExtensionFilters().add(extFilter);
        chooser.setTitle("Select CSV File to Import");




        int batchSize = 20;

        Connection connection = null;



        try {

            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            String sql = "INSERT INTO item_log_history (item_ID, employee_ID, clearance, type, Location, description) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            File fr = chooser.showOpenDialog(null);
            if(fr != null) {
                BufferedReader lineReader = new BufferedReader(new FileReader(fr));

                String lineText = null;

                int count = 0;

                lineReader.readLine(); // skip header line

                while ((lineText = lineReader.readLine()) != null) {
                    String[] data = lineText.split(",");
                    String itemID = data[0];
                    String employeeID = data[1];

                    String sql2 = "SELECT COUNT(employee_id) from employee where employee_ID = '" + employeeID + "'";
                    Statement statement2 = connection.createStatement();
                    ResultSet resultEmpID = statement2.executeQuery(sql2);
                    resultEmpID.next();
                    if(resultEmpID.getInt(1)>0){
                        lineReader.readLine();
                        data = lineText.split(",");
                        itemID = data[0];
                        employeeID = data[1];

                    }

                    String clearance = data[2];
                    String type = data[3];
                    String location = data[4];
                    String description = data[5];
                    // optional example - String comment = data.length == 5 ? data[4] : "";

                    statement.setString(1, itemID);
                    statement.setString(2, employeeID);

                    Integer clear = Integer.parseInt(clearance);
                    statement.setInt(3, clear);
                    //time example for database - Timestamp sqlTimestamp = Timestamp.valueOf(timestamp);
                    //statement.setTimestamp(3, sqlTimestamp);

                    statement.setString(4, type);
                    statement.setString(5, location);
                    statement.setString(6, description);

                    statement.addBatch();

                    if (count % batchSize == 0) {
                        statement.executeBatch();
                    }
                }

                lineReader.close();

                // execute the remaining queries
                statement.executeBatch();

                connection.commit();
                connection.close();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public static void CSVExport(){
        String databaseName = "scannadb";
        String jdbcURL = "jdbc:mysql://localhost/" + databaseName;
        String username = "root";
        String password = "Scanna123.";
        DirectoryChooser chooseDirec= new DirectoryChooser();
        chooseDirec.setTitle("Select Destination Folder to Export");
        String csvName = "ItemLogs-export";
        File csvFilePath = new File(chooseDirec.showDialog(null), addDateTime(csvName) + ".csv");




        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql1 = "SELECT * FROM item_log_history";

            Statement statement = connection.createStatement();

            ResultSet resultILH = statement.executeQuery(sql1);

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

            // write header line containing column names
            fileWriter.write("Barcode ID, Employee ID, Clearance, Type, Location, Description");

            while (resultILH.next()) {
                String itemID = resultILH.getString("item_id");
                String employeeID = resultILH.getString("employee_id");
                int clearance = resultILH.getInt("clearance");
                // Timestamp timestamp = result.getTimestamp("timestamp");
                String type = resultILH.getString("type");
                String location = resultILH.getString("location");
                String description = resultILH.getString("description");

                // have to find the optional columns of the Item Log History
                 /*if (comment == null) {
                     comment = "";   // write empty value for null
                 } else {
                     comment = "\"" + comment + "\""; // escape double quotes
                 }*/

                String line = String.format("%s,%s,%x,%s,%s,%s",
                        itemID, employeeID, clearance, type, location, description);

                fileWriter.newLine();
                fileWriter.write(line);
            }

            statement.close();
            fileWriter.close();

        } catch (SQLException e) {
            System.out.println("Database error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }

    public static String addDateTime(String in){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return in+"_"+dtf.format(now);
    }

}
