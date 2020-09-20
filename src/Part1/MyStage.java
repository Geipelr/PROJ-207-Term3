package Part1;

/*
    Author: Robert Geipel
    Date: 9/20/2020
    Purpose: customized controller for modal dialog
 */

import javafx.stage.Stage;

public class MyStage extends Stage {
    public Agency showAndReturn(ControllerAddEdit myControl) {
        super.showAndWait();
        return myControl.getReturn();
    }
}