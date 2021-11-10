package application;

import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.control.ProgressIndicator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;


public class SampleController {

	//*********************

	//The file location path where the face will be saved & retrieved
	public String filePath="./faces";

	//***********************

	@FXML
	private Button startCam;
	@FXML
	private Button stopBtn;
	@FXML
	private Button saveBtn;
	@FXML
	private Button recogniseBtn;
	@FXML
	private Button stopRecBtn;
	@FXML
	private ImageView frame;
	@FXML
	private TitledPane dataPane;
	@FXML
	private TextField fname;
	@FXML
	private TextField lname;
	@FXML
	private TextField code;
	@FXML
	private TextField age;
	@FXML
	public ListView<String> logList;
	@FXML
	public ListView<String> output;
	@FXML
	public ProgressIndicator pb;
	@FXML
	public Label savedLabel;
	@FXML
	public Label warning;
	@FXML
	public Label title;
//	@FXML
//	public TilePane tile;
//**********************************************************************************************
	FaceDetector faceDetect = new FaceDetector();	//Creating Face detector object									
	Database database = new Database();		//Creating Database object

	ArrayList<String> user = new ArrayList<String>();

	public static ObservableList<String> event = FXCollections.observableArrayList();
	public static ObservableList<String> outEvent = FXCollections.observableArrayList();

	public boolean isDBready = false;

	//**********************************************************************************************

	public void putOnLog(String data) {

		Instant now = Instant.now();

		String logs = now.toString() + ":\n" + data;

		event.add(logs);

		logList.setItems(event);
		System.out.println(logs);
		System.out.println("***************************");

	}

	@FXML
	protected void startCamera() throws SQLException {

		//*******************************************************************************************
		//initializing objects from start camera button event
		faceDetect.init();

		faceDetect.setFrame(frame);

		faceDetect.start();

		if (!database.init()) {

			putOnLog("Error: Database Connection Failed ! ");

		} else {
			isDBready = true;
			putOnLog("Success: Database Connection Succesful ! ");
		}

		//*******************************************************************************************
		//Activating other buttons

		startCam.setVisible(false);
		stopBtn.setVisible(true);
		saveBtn.setDisable(false);

		if (isDBready) {
			recogniseBtn.setDisable(false);
		}
		dataPane.setDisable(false);
		if (stopRecBtn.isDisable()) {
			stopRecBtn.setDisable(false);
		}
		//*******************************************************************************************
		
		putOnLog(" Real Time WebCam Stream Started !");
		
		//**********************************************************************************************
	}
	int count = 0;

	@FXML
	protected void faceRecognise() {
		faceDetect.setIsRecFace(true);
		//Getting detected faces
		user = faceDetect.getOutput();
		if (count > 0) {

			//Retrieved data will be shown in Fetched Data pane

			String t = "********* Face Data: " + user.get(1) + " " + user.get(2) + " *********";
			outEvent.add(t);

			String fc = "Face Code\t\t:\t" + user.get(0);
			outEvent.add(fc);
			output.setItems(outEvent);
			System.out.println("Face Code\t\t:\t"+user.get(0));

			String n1 = "First Name\t\t:\t" + user.get(1);
			outEvent.add(n1);
			output.setItems(outEvent);
			System.out.println("First Name\t\t:\t" +user.get(1));

			String n2 = "Last Name\t\t:\t" + user.get(2);
			outEvent.add(n2);
			output.setItems(outEvent);
			System.out.println("Last Name\t\t:\t" +user.get(2));

			String age = "Age \t\t\t\t:\t" + user.get(3);
			outEvent.add(age);
			output.setItems(outEvent);
			System.out.println("Age \t\t\t:\t" +user.get(3));
		}
		count++;
		putOnLog("Face Recognition Activated !");
		stopRecBtn.setDisable(false);

	}

	@FXML
	protected void stopRecognise() {

		faceDetect.setIsRecFace(false);
		faceDetect.clearOutput();

		this.user.clear();
		stopRecBtn.setDisable(true);

		putOnLog("Face Recognition Deactivated !");

	}

	@FXML
	protected void saveFace() throws SQLException {

		//Input Validation
		if (fname.getText().trim().isEmpty() ||  code.getText().trim().isEmpty()) {

			new Thread(() -> {

				try {
					warning.setVisible(true);

					Thread.sleep(2000);

					warning.setVisible(false);

				} catch (InterruptedException ex) {
				}

			}).start();

		} else {
			//Progressbar
			pb.setVisible(true);

			savedLabel.setVisible(true);

			new Thread(() -> {

				try {

					faceDetect.setFname(fname.getText());
					faceDetect.setLname(lname.getText());
					faceDetect.setAge(Integer.parseInt(age.getText()));
					faceDetect.setCode(Integer.parseInt(code.getText()));

					database.setFname(fname.getText());
					database.setLname(lname.getText());
					database.setAge(Integer.parseInt(age.getText()));
					database.setCode(Integer.parseInt(code.getText()));

					database.insert();
					
					javafx.application.Platform.runLater(new Runnable(){
						
						@Override
						 public void run() {
							pb.setProgress(100);
						 }
						 });

					savedLabel.setVisible(true);
					Thread.sleep(2000);

					//*******************face saved !
					javafx.application.Platform.runLater(new Runnable(){
						
						@Override
						 public void run() {
							pb.setVisible(false);
						 }
						 });
					
					javafx.application.Platform.runLater(new Runnable(){
						
						@Override
						 public void run() {
					 savedLabel.setVisible(false);
						 }
						 });

				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}

			}).start();

			faceDetect.setSaveFace(true);
		}
	}

	@FXML
	protected void stopCam() throws SQLException {

		faceDetect.stop();
		startCam.setVisible(true);
		stopBtn.setVisible(false);

		putOnLog("Cam Stream Stopped!");

		recogniseBtn.setDisable(true);
		saveBtn.setDisable(true);
		dataPane.setDisable(true);
		stopRecBtn.setDisable(true);
		database.db_close();
		putOnLog("Database Connection Closed");
		isDBready=false;
	}
}