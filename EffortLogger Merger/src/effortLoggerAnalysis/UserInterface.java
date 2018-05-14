package effortLoggerAnalysis;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Callback;


/*******
Author Manpreet and Garvit
Version 2.0
Date 20/94/2018
*/

public class UserInterface {

	/**********************************************************************************************

	Attributes

	**********************************************************************************************/

	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	private TextField text_SourceDirectory = new TextField();
	protected static final Window theStage = null;
	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH/2;

	// These are the application values required by the user interface
	private Label label_DoubleEffortloggerAnalysis = new Label("Effort log Merge and Analysis Tool");
	private Label label_Operand1 = new Label("Source Directory");
	private TextField text_Operand1 = new TextField();


	private Label label_Operand2 = new Label("");
	





	private Button button_Browse = new Button("Browse");
	
	
	private Button button_Quit = new Button("Quit"); 
	private Button button_GAR = new Button("Generate Analysis Report");
	private Label label_errOperand1 = new Label("");                // Label to display specific
	private Label label_errOperand2 = new Label("");                // error messages
	
	private Label label_errOperand1One = new Label("");               // Label to display a error message
	private Label label_errOperand2Two = new Label("");	            // when user tries to perform any function



	private CheckBox checkbox = new CheckBox("Select All");

	private double buttonSpace;
	                                // This is the white space between the operator buttons.

	/* This is the link to the business logic */



	/**********************************************************************************************

	Constructors

	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */



	public UserInterface(Pane theRoot) {
		checkbox.setLayoutX(250);
		checkbox.setLayoutY(120);
		
	          
		// There are five gaps. Compute the button space accordingly.
		buttonSpace = EffortloggerAnalysis.WINDOW_WIDTH / 5;

		// Label theScene with the name of the EffortloggerAnalysis, centered at the top of the pane
		setupLabelUI(label_DoubleEffortloggerAnalysis, "Arial", 20, EffortloggerAnalysis.WINDOW_WIDTH, Pos.CENTER, 0, 10);

		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 16, EffortloggerAnalysis.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 30, 70);
		
		
		setupTextUI(text_Operand1, "Arial", 18, EffortloggerAnalysis.WINDOW_WIDTH-400, Pos.BASELINE_LEFT, 200, 70, true);
		
		setupLabelUI(label_errOperand1One, "Arial", 18, EffortloggerAnalysis.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 45);
		label_errOperand1One.setTextFill(Color.RED);

		
		label_errOperand1.setTextFill(Color.RED);
		label_errOperand1.setAlignment(Pos.BASELINE_LEFT);
		setupLabelUI(label_errOperand1, "Arial", 14, EffortloggerAnalysis.WINDOW_WIDTH, Pos.BASELINE_LEFT, 22, 128);


		// Establish the Browse "" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Browse, "Symbol", 16, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4.0 * buttonSpace-BUTTON_OFFSET, 70);
		
		// CHeckBox List Section
				 // Creating Check box list view
				    ListView<String> listview = new ListView<>();
				       ObservableList<String> list =FXCollections.observableArrayList ();
				       
				       
				       
				       button_Browse.setOnAction(new EventHandler<ActionEvent>() {
				    	   List<File> file;
				           @Override
				           public void handle(ActionEvent event) {
				               FileChooser fileChooser = new FileChooser();
				               
				               //Open directory from existing directory
				               if( file != null ){
				                   if( !file.isEmpty() ){
				                       File existDirectory = file.get(0).getParentFile();
				                       fileChooser.setInitialDirectory(existDirectory);
				                      
				                   }
				               }
				              
				              
				               FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
				               fileChooser.getExtensionFilters().add(extFilter);
				               
				              
				               
				               file = fileChooser.showOpenMultipleDialog(theStage);
				               
				               list.clear();
				               
				               for(int i=0; i<file.size(); i++){
				                   list.add(file.get(i).getAbsolutePath());
				               }
				               
				               listview.setItems(list);
				               
				               listview.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>()
				   		    {
				   		    	public ObservableValue<Boolean> call(String item)
				   		    	{
				   		    		BooleanProperty observable = new SimpleBooleanProperty();
				   		    		observable.addListener((obs, wasSelected, isNowSelected) ->
				   		    		System.out.println("Check box for " + item + "changed from " +wasSelected + " to " + isNowSelected)
				   		    		
				   		    	);
				   		    		
				   		    		return observable;
				   		    		
				   		    	}
				   		    }
				               )
				            		   ); 
				           }
				       }
				       );
				      
						
				       VBox container = new VBox();
				       container.getChildren().addAll(text_SourceDirectory, button_Browse);
				       container.getChildren().add(listview);
				       StackPane root = new StackPane();
				       root.getChildren().add(container);
				    listview.setLayoutX(250);
					listview.setLayoutY(150);
					listview.setMaxSize(300, 200);


		

		
		
		setupButtonUI(button_Quit, "Symbol", 20, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4.0 * buttonSpace-BUTTON_OFFSET, 400);
		button_Quit.setOnAction((event) -> {
			System.exit(0);
		});
		
		
		setupButtonUI(button_GAR, "Symbol", 20, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1.0 * buttonSpace-BUTTON_OFFSET, 400);
		button_GAR.setOnAction(new EventHandler<ActionEvent>() {
	    	   
	    	   public void handle(ActionEvent arg0) {
	    	                                 /*change your directory here*/
	    	    File File = new File("C:\\Users\\Manpreet Singh Kohli\\Desktop\\Project 3 EffortLogger\\Summery Sheet.xlsx");
	    	    if (File.exists())
	    	    {
	    	     if (Desktop.isDesktopSupported())
	    	     {
	    	      try
	    	      {
	    	       Desktop.getDesktop().open(File);
	    	      }
	    	      catch (IOException exception)
	    	      {
	                    exception.printStackTrace();
	                        }
	                      }
	                    }
	                    else
	                         {
	                 System.out.println("File is not exists!");
	                          }
	                       }
	                   });
		
		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_DoubleEffortloggerAnalysis,  label_Operand1, text_Operand1, label_errOperand1,
			label_Operand2,  label_errOperand2,
			  button_Browse,  button_Quit, button_GAR,  label_errOperand1One,
				label_errOperand2Two,listview,checkbox);

	}

	



	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);
	}

	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);
		t.setEditable(e);
	}

	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);
	}

	
	
	
	}











