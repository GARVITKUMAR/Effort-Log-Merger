package effortLoggerAnalysis;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*******
 Author Manpreet and Garvit
 Version 2.0
 Date 20/94/2018
 */

public class EffortloggerAnalysis extends Application {
	public final static double WINDOW_WIDTH = 800;
	public final static double WINDOW_HEIGHT = 600;

	public UserInterface theGUI;

	/**********
	 * This is the start method that is called once the application has been loaded
	 * into memory and is ready to get to work.
	 *
	 *
	 */
	public void start(Stage theStage) throws Exception {

		theStage.setTitle("Manpreet Singh"); // Label the stage (a window)

		Pane theRoot = new Pane(); // Create a pane within the window

		theGUI = new UserInterface(theRoot); // Create the Graphical User Interface

		Scene theScene = new Scene(theRoot, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene

		theStage.setScene(theScene); // Set the scene on the stage

		theStage.show(); // Show the stage to the user

		// When the stage is shown to the user, the pane within the window is visible.
		// This means that the
		// labels, fields, and buttons of the Graphical User Interface (GUI) are
		// visible//
	}

	/*******************************************************************************************************
	 * This is the method that launches the JavaFX application
	 *
	 */
	public static void main(String[] args) { // This method may not be required
		launch(args); // for all JavaFX applications using
	} // other IDEs.

}
