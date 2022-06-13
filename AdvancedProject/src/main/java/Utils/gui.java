package Utils;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class gui extends Application {
	
	private static String excelPath = "./data/Example.xlsx";
	private Stage currentStage;
	private String api;
	
	

	
	private void changeMainScene() {
		
		ListView list = new ListView<>();
		System.out.println("stage changed");
		ExcelUtils excel =new ExcelUtils(excelPath);
		int numTables=excel.numOfTables();
		Operations[] op = new Operations[numTables]; //
		for(int i=0; i<numTables ; i++) {  //repeat for each api
			
			list.setPrefSize(200, 300);
			
			FlowPane tables=new FlowPane();
			Scene scene =new Scene(tables,700,700);
			Stage stage=new Stage();
			currentStage=stage;
			op[i] =new Operations();
		
			int startingIndex=excel.getTableIndexStarts().get(i);
			while(((String)excel.getCellData(startingIndex, 0)).toLowerCase().equals("i")||((String)excel.getCellData(startingIndex, 0)).toLowerCase().equals("o")) {
				op[i].storeRowData(startingIndex,excelPath);  //added excelPath as parameter

				startingIndex++;
				excel.closeWorkbook();
			}	
		
			op[i].setArrList(op[i].arrOfRowObjects ,excelPath);   //added excelPath as parameter
			for(ParentList x:op[i].parentsList) {  //each element in api
				
				list.getItems().add(x.toString());
				if(x.StringchildData().size()!=0)list.getItems().add("\t\tCHILDREN");
				list.getItems().addAll(x.StringchildData());
				tables.getChildren().add(list);
				list = new ListView<>();
		
				
			}

			
			currentStage.setTitle("api " +i);
			currentStage.setScene(scene);
			currentStage.show();

		}	
		}
		
	
public void start(final Stage primaryStage) {
	currentStage=primaryStage;
	currentStage.setTitle("API request reader");
	BorderPane pane = new BorderPane();
	VBox centerPane =new VBox(4);
	
	Text welcomeMSG =new Text("Welcome");
	Scene window = new Scene(pane, 500, 500, Color.SKYBLUE);
	final Text filePath =new Text("Welcome To Api chooser select excel file path");
	final FileChooser fileChooser = new FileChooser();
	final Button button =new Button("choose File");
	final ObservableList list =centerPane.getChildren();
	final Button next =new Button("submit");
	currentStage.setResizable(false);
	
	welcomeMSG.setFill(Color.FORESTGREEN);
	welcomeMSG.setX(220);
	welcomeMSG.setY(50);
	welcomeMSG.setFont(Font.font(STYLESHEET_CASPIAN,FontWeight.EXTRA_BOLD,30));
	
	button.setLayoutX(200);
	button.setLayoutY(200);
	
	button.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
		    File file = fileChooser.showOpenDialog(primaryStage);
		    button.getProperties().put("FILE_LOCATION", file.getAbsolutePath());
		    filePath.setText(file.getAbsolutePath());
		    excelPath  = file.getAbsolutePath();
			
		    if(!list.contains(next))		   
		    	list.add(next);
		    
		}
	});
	next.setOnAction(e -> changeMainScene());
	
	
	filePath.setFill(Color.ROYALBLUE);
	filePath.setY(150);
	filePath.xProperty().bind((window.widthProperty().divide(2).subtract(120)));
	pane.setTop(welcomeMSG);
	
	BorderPane.setMargin(welcomeMSG, new Insets(30,30,15,30));
	BorderPane.setAlignment(welcomeMSG, Pos.CENTER);
	
	list.add(filePath);
	list.add(button);
	centerPane.setAlignment(Pos.CENTER);
	
	VBox.setMargin(filePath, new Insets(5,30,15,30));
	VBox.setMargin(button, new Insets(5,30,15,30));
	
	pane.setCenter(centerPane);
	
		
	currentStage.setScene(window);
	currentStage.show();
}

public static void main(String[] args) {
	launch(args);

	

}

}


