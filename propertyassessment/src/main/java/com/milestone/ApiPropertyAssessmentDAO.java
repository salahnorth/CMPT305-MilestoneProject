/* Name: Salah Mohamed
   Date: 12/03/23
   ID: 3044504
   Course: CMPT 305 Milestone 2
   Program: Processing the property assessment data set through its API.
 */

 // Importing necessary libraries
package com.milestone;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/*Making API calls by creating HttpClient, creating HttpRequest and 
    sending the HttpRequest using the HttpClient and process the HttpResponse’s body*/
public class ApiPropertyAssessmentDAO implements PropertyAssessmentDAO {

    private String ENDPOINT;

    public ApiPropertyAssessmentDAO(String ENDPOINT) {
        this.ENDPOINT = ENDPOINT;
    }
    /*Overriding and returning all propertyAssessments through its API*/
    @Override
    public List<PropertyAssessment> getAll() throws IOException, InterruptedException {
        List<PropertyAssessment> assessments = new ArrayList<>();
        String url = ENDPOINT;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        String[] lines = responseBody.split("\n");
        
        for (int i = 1; i < lines.length; i++) {
            String[] parts = lines[i].split(",");
            PropertyAssessment assessment = new PropertyAssessment();
            assessment.setAccountNumber(parts[0]);
            assessment.setSuite(parts[1]);
            assessment.setHouseNumber(parts[2]);
            assessment.setStreetName(parts[3]);
            assessment.setGarage(parts[4]);
            assessment.setNeighbourhoodID(parts[5]);
            assessment.setNeighbourhoodName(parts[6]);
            assessment.setWard(parts[7]);
            assessment.setAssessed(parts[8]);
            assessment.setLatitude(parts[9]);
            assessment.setLongitude(parts[10]);
            assessment.setPointLocation(parts[11]);
            assessment.setAssessmentClass1(parts[12]);
            assessment.setAssessmentClass2(parts[13]);
            assessment.setAssessmentClass3(parts[14]);
            assessment.setAssessmentClassper1(parts[15]);

            if (parts.length > 16) {
                assessment.setAssessmentClassper2(parts[16]);
            }
            if (parts.length > 17) {
                assessment.setAssessmentClassper3(parts[17]);
            }
            assessments.add(assessment);
        }
        return assessments;
    }
    /*Overriding and returning all propertyAssessments with specified account number through its API*/
    @Override
    public PropertyAssessment getByAccountNumber(String accountNumber) throws IOException, InterruptedException {
        String url = ENDPOINT + "?account_number=" + accountNumber;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        System.out.println("Response body: " + responseBody);

        String[] lines = responseBody.split("\n");
        if (lines.length > 1) {
            String[] parts = lines[1].split(",");
            //System.out.println(parts[16]);
            if (parts.length > 0) {
                PropertyAssessment assessment = new PropertyAssessment();
                assessment.setAccountNumber(parts[0]);
                assessment.setSuite(parts[1]);
                assessment.setHouseNumber(parts[2]);
                assessment.setStreetName(parts[3]);
                assessment.setGarage(parts[4]);
                assessment.setNeighbourhoodID(parts[5]);
                assessment.setNeighbourhoodName(parts[6]);
                assessment.setWard(parts[7]);
                assessment.setAssessed(parts[8]);
                assessment.setLatitude(parts[9]);
                assessment.setLongitude(parts[10]);
                assessment.setPointLocation(parts[11]);
                assessment.setAssessmentClass1(parts[12]);
                assessment.setAssessmentClass2(parts[13]);
                assessment.setAssessmentClass3(parts[14]);
                assessment.setAssessmentClassper1(parts[15]);
                if (parts.length > 16) {
                    assessment.setAssessmentClassper2(parts[16]);
                }
                if (parts.length > 17) {
                    assessment.setAssessmentClassper3(parts[17]);
                }
                return assessment;
            }
        }
        return null;
    }
    /*Overriding and returning all propertyAssessments with specified neighbourhood through its API*/
    public List<PropertyAssessment> getByNeighbourhood(String neighbourhood) throws IOException, InterruptedException {
        List<PropertyAssessment> assessments = new ArrayList<>();
        String url = ENDPOINT + "?neighbourhood=" + neighbourhood;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        String[] lines = responseBody.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] parts = lines[i].split(",");
            PropertyAssessment assessment = new PropertyAssessment();
            assessment.setAccountNumber(parts[0]);
            assessment.setSuite(parts[1]);
            assessment.setHouseNumber(parts[2]);
            assessment.setStreetName(parts[3]);
            assessment.setGarage(parts[4]);
            assessment.setNeighbourhoodID(parts[5]);
            assessment.setNeighbourhoodName(parts[6]);
            assessment.setWard(parts[7]);
            assessment.setAssessed(parts[8]);
            assessment.setLatitude(parts[9]);
            assessment.setLongitude(parts[10]);
            assessment.setPointLocation(parts[11]);
            assessment.setAssessmentClass1(parts[12]);
            assessment.setAssessmentClass2(parts[13]);
            assessment.setAssessmentClass3(parts[14]);
            assessment.setAssessmentClassper1(parts[15]);
            if (parts.length > 16) {
                assessment.setAssessmentClassper2(parts[16]);
            }
            if (parts.length > 17) {
                assessment.setAssessmentClassper3(parts[17]);
            }
            assessments.add(assessment);
        }
        return assessments;
    }
}
