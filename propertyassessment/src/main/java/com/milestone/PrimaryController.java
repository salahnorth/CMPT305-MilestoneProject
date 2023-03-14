/* Name: Salah Mohamed
   Date: 12/03/23
   ID: 3044504
   Course: CMPT 305 Milestone 2
 */

package com.milestone;
import java.io.IOException;
import javafx.fxml.FXML;

/*Java class that defines a controller for the primary view in a JavaFX application*/
public class PrimaryController {
    /*Method is annotated with @FXML, referenced in FXML markup */
    @FXML
    /*Method in the PropertyAssessmentTable class to switch to a different view */
    private void switchToSecondary() throws IOException {
        PropertyAssessmentTable.setRoot("secondary");
    }
}
