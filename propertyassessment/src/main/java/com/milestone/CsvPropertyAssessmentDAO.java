/* Name: Salah Mohamed
   Date: 12/03/23
   ID: 3044504
   Course: CMPT 305 Milestone 2
   Program: Processing the property assessment data set through its CSV file.
 */

  // Importing necessary libraries
package com.milestone;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

public class CsvPropertyAssessmentDAO implements PropertyAssessmentDAO {

    //Convert numbers to dollar currency
    static NumberFormat formatter = NumberFormat.getCurrencyInstance();

    // Store the property assessments in a list
    private List<PropertyAssessment> propertyAssessments;

    // Constructor to read data from CSV and populate propertyAssessments list
    public CsvPropertyAssessmentDAO(String csvFileName) {
        propertyAssessments = readData(csvFileName);
    }
    /*Overriding and returning all propertyAssessments through its API*/
    @Override
    public List<PropertyAssessment> getAll() throws IOException, InterruptedException {
        return propertyAssessments;
    }

    //Reading data from a csv file and plugging the columns
    //in their respective variables
    private List<PropertyAssessment> readData(String csvFileName) {
        try {
            List<PropertyAssessment> data = new ArrayList<>();
            Scanner read = new Scanner(new File(csvFileName));
    
            read.nextLine();
            read.useDelimiter(",|\n");
    
            while (read.hasNext()) {
                String accountNumber = read.next().replaceAll("\"", "");
                String suite = read.next().replaceAll("\"", "");
                String houseNumber = read.next().replaceAll("\"", "");
                String streetName = read.next().replaceAll("\"", "");
                String garage = read.next().replaceAll("\"", "");
                String neighbourhoodID = read.next().replaceAll("\"", "");
                String neighbourhood = read.next().replaceAll("\"", "");
                String ward = read.next().replaceAll("\"", "");
                String assessed = read.next().replaceAll("\"", "");
                String latitude = read.next().replaceAll("\"", "");
                String longitude = read.next().replaceAll("\"", "");
                String pointLocation = read.next().replaceAll("\"", "");
                String assessmentClassper1 = read.next().replaceAll("\"", "");
                String assessmentClassper2 = read.next().replaceAll("\"", "");
                String assessmentClassper3 = read.next().replaceAll("\"", "");
                String assessmentClass1 = read.next().replaceAll("\"", "");
                String assessmentClass2 = read.next().replaceAll("\"", "");
                String assessmentClass3 = read.next().replaceAll("\"", "");
    
                // Adding the variables into an arrayList
                data.add(new PropertyAssessment(accountNumber, suite, houseNumber, streetName, garage, neighbourhoodID,
                        neighbourhood, ward, assessed, latitude, longitude, pointLocation, assessmentClassper1,
                        assessmentClassper2, assessmentClassper3, assessmentClass1, assessmentClass2, assessmentClass3));
            }
    
            read.close();
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("Error: Can't open file " + csvFileName);
        }
        return null;
    }

    /*Overriding and returning all propertyAssessments  with specified account number 
    through its CSV File*/
    @Override
    public PropertyAssessment getByAccountNumber(String accountNumber) {
        int accountNumberInt = Integer.valueOf(accountNumber);
        for (int i=0; i<propertyAssessments.size(); i++){
            if (Integer.valueOf(propertyAssessments.get(i).getAccountNumber()) == accountNumberInt){
                return propertyAssessments.get(i);
            }
        }
        return null;
    }

    /*Overriding and returning all propertyAssessments  with specified neighbourhood 
    through its CSV File*/
    @Override
    public List<PropertyAssessment> getByNeighbourhood(String neighbourhoodName) {    
        //Put all the neighbourhoods in an array list
        List<PropertyAssessment> neighbourhoodList = new ArrayList<>();

        int flagNeighbourhood = 0;
        for (int j=0; j<propertyAssessments.size(); j++){
            if (propertyAssessments.get(j).getNeighbourhoodName().compareTo(neighbourhoodName.toUpperCase()) == 0){
                neighbourhoodList.add(propertyAssessments.get(j));
                flagNeighbourhood += 1;
            }
        }
        //If neighbourhood doesn't exist
        if (flagNeighbourhood == 0){
            System.out.println("Data not found");
        }
        else{
            return neighbourhoodList;
        }
        return null;
    }

    /*Overriding and returning all propertyAssessments with specified assessment class 
    through its CSV File*/
    public List<PropertyAssessment> getByAssessmentClass(String assessmentClass) {
        int flagAssessmentClass = 0;

        List<PropertyAssessment> assessmentClassList = new ArrayList<>();
        for (int i=0; i<propertyAssessments.size(); i++){
            if ((propertyAssessments.get(i).getAssessmentClass1().compareTo(assessmentClass.toUpperCase()) == 0) ||
            (propertyAssessments.get(i).getAssessmentClass2().compareTo(assessmentClass.toUpperCase()) == 0) ||
            (propertyAssessments.get(i).getAssessmentClass3().compareTo(assessmentClass.toUpperCase()) == 0)){
                assessmentClassList.add(propertyAssessments.get(i));
                flagAssessmentClass += 1;
            }
        }
        //If assessment class doesn't exist
        if (flagAssessmentClass == 0){
            System.out.println("Data not found");
        }
        else{
            return assessmentClassList;
        }
        return null;
    }
}