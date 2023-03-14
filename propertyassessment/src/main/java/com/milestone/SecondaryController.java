/* Name: Salah Mohamed
   Date: 12/03/23
   ID: 3044504
   Course: CMPT 305 Milestone 2
 */

 // Importing necessary libraries
package com.milestone;
import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    /*Method that sets the root view to the "primary" view */
    @FXML
    private void switchToPrimary() throws IOException {
        PropertyAssessmentTable.setRoot("primary");
    }
}
