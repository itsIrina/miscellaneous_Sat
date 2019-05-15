/* Name: COMP 151 Lecture 9
 * Date: October 5, 2017
 * Description: Basic student profile program for practicing 
 *      Controls and JavaFX.
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ToreroProfile extends Application {

	Stage stageRef;
    ArrayList<Torero> allProfiles;
    int sceneWidth = 400;
    int sceneHeight = 300;
    

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        stageRef = stage;
        allProfiles = new ArrayList<Torero>();
  
        addProfileScene();
        stage.show();
      }
    
    
    
    //Decscription: Displays the profile of the Torero passed in.

    private void previewProfile(Torero t) {
        // TODO Make this preview look nice
        // Consider using another layout
        // or stick with BorderPane and adjust the spacing

        BorderPane borders = new BorderPane();
        
        ImageView profilePic = new ImageView();
        
        try {
            Image pic = new Image(new FileInputStream(t.getProfilePhotoFilename()), 100, 100, false, false);
            profilePic.setImage(pic);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        // TODO Experiment with some options for making the header look nice
        
        borders.setTop(new Text("New Torero Profile"));
        borders.setLeft(profilePic);
       
        // TODO Experiment with some options for making the full profile look nice
        
        // Format the fields from the new Torero object and display it in a Label
        String fullText = "Name: " + t.getStudentName() + "\n";
        fullText += "Class: " + t.getGradClass() + "\n";
        fullText += "Major: " + t.getMajor() + "\n";
 
        
        if (t.getLivesOnCampus())
            fullText += "Residence: on campus \n";
        else
            fullText += "Residence: off campus \n";
        
        fullText += "Hometown: " + t.getHometown() + "\n";

        
        Label newProfile = new Label(fullText);
        borders.setCenter(newProfile);
        
        Button addProfileButton = new Button("Add a profile");
        addProfileButton.setOnAction( e -> {addProfileScene(); } );
        
        Button viewAllProfilesButton = new Button("View all profiles");
        viewAllProfilesButton.setOnAction( e -> {viewAllScene(); } );
        
        HBox bottomButtons = new HBox(new Button("Edit profile"), addProfileButton, viewAllProfilesButton);
        bottomButtons.setPadding(new Insets(15, 12, 15, 12));
        bottomButtons.setSpacing(10);
        borders.setBottom(bottomButtons);
   		
        Scene scene = new Scene(borders, sceneWidth, sceneHeight);
		stageRef.setScene(scene);
	}

    // Composes a form of Controls so that the user can enter a new profile
    private void addProfileScene() {
        BorderPane addStudent = new BorderPane();
        
        // TODO: update the GridPane properties so that this looks nice
        GridPane newProfileForm = new GridPane();
        
        // TODO delete the line that sets the gridlines to visible when
        // you're happy with how the gridPane looks
        newProfileForm.gridLinesVisibleProperty().set(true);
        newProfileForm.setHgap(40);
        newProfileForm.setVgap(20);
        
        
        // TODO add Controls to collect the profile fields
        //      Read Chapter 16 and choose the right control
        //      for the field based on the values the
        //      field might have
        newProfileForm.add(new Text("Name"),0,0);
        
        TextField nameField = new TextField();
        newProfileForm.add(nameField,1,0);
        
        // added profile fields
        TextField classField = new TextField();
        newProfileForm.add(classField,1,1);
        
        TextField majField = new TextField();
        newProfileForm.add(majField,1,2);
        
        // possibly change campus living arrangements
        // to checkbox since only 2 options
        TextField campusField = new TextField();
        newProfileForm.add(campusField,1,3);
        
        TextField homeField = new TextField();
        newProfileForm.add(homeField,1,4);
        
        newProfileForm.add(new Text("Year of Expected Graduation"),0,1);
        newProfileForm.add(new Text("Major(s)"),0,2);
        newProfileForm.add(new Text("On Campus or Off Campus"),0,3);
        newProfileForm.add(new Text("Hometown"),0,4);
        
        
        Button submitButton = new Button("Submit Profile");
        submitButton.setOnAction( e -> {
            Torero submittedProfile = new Torero(nameField.getText());
            
            submitNewProfile(submittedProfile);
            previewProfile(submittedProfile);
        } );
        
        
        addStudent.setTop(new Text("Create a New Student Profile:"));
        addStudent.setCenter(newProfileForm);
        addStudent.setBottom(submitButton);
        
        Scene scene = new Scene(addStudent, sceneWidth, sceneHeight);
        stageRef.setScene(scene);
    }
    
    private void viewAllScene() {
        // TODO complete this method
        //
        // 1. Use a ListView to show all of the existing student profiles
        //
        // 2. Allow the user to select a student and edit the profile
        
        //
        // Hint: the ArrayList allProfiles has a Torero instance for each profile that
        // was added
        
    	ListView studentLV = new ListView();
    	
    	
        for (Torero t: allProfiles) {
            System.out.println(t.getStudentName());
        }
    }

	private void submitNewProfile(Torero t) {
        allProfiles.add(t);
        System.out.println("A new profile was submitted");
    }
}
