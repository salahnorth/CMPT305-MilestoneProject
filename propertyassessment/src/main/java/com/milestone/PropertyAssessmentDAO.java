/* Name: Salah Mohamed
   Date: 12/03/23
   ID: 3044504
   Course: CMPT 305 Milestone 2
   Program: An interface for Property Assessment DAO
 */

// Importing necessary libraries
package com.milestone;

import java.io.IOException;
import java.util.*;

public interface PropertyAssessmentDAO {
    /*Returns a PropertyAssessment object based on the specified account number */
    PropertyAssessment getByAccountNumber(String accountNumber) throws IOException, InterruptedException;

    /*Returns a PropertyAssessment object based on the specified neighbourhood */
    List<PropertyAssessment> getByNeighbourhood(String neighbourhood) throws IOException, InterruptedException;

    /*Returns a PropertyAssessment object */
    List<PropertyAssessment> getAll() throws IOException, InterruptedException;
}
