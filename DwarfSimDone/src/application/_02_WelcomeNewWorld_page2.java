package application;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*@formatter:off */
public class _02_WelcomeNewWorld_page2 extends Application
{
	public GridPane gridPane ; // komplexes Gitter / Raster mit exakter KomponentenAnordnung
	public Label welcome;
	public Text instructions;
	public TextField  name;
	public static Scene scene;
	public static Button go, back;
	public  static boolean missingName = false;
	@SuppressWarnings("restriction")
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Neues Spiel beginnen!!!!!11");
		gridPane = new GridPane(); // komplexes Gitter / Raster mit exakter KomponentenAnordnung
		scene = new Scene(gridPane, 900, 700);
//________________ buttons-Initialisierung
		go = new Button("Los geht's!");
		back = new Button("Ups, ach ne doch nicht");
		name = new TextField();
		welcome = new Label("Willkommen in einer neuen Welt!"); // Wie lautet dein Name?");
		String errorMessage = "Bitte gib einen Namen ein, sonst startet das Spiel nicht!!!!";
		String defaultMessage = "Um das Spiel zu beginnen, musst du einen Namen eingeben.\n                  Wie moechtest du dich nennen?";
		if(missingName){instructions = new Text(errorMessage);}
		else{instructions = new Text(defaultMessage);}

//___________________ Layout-GridPane-Komponenten anbringen __________________
			gridPane.setPadding(new Insets(10));
			gridPane.setHgap(0);
			ColumnConstraints spalte1 = new ColumnConstraints(320);
			ColumnConstraints spalte2 = new ColumnConstraints(120);
			ColumnConstraints spalte3 = new ColumnConstraints(125);
			ColumnConstraints spalte4 = new ColumnConstraints(160);
			RowConstraints zeile1 = new RowConstraints(250);
			RowConstraints zeile2 = new RowConstraints(50);
			RowConstraints zeile3 = new RowConstraints(50);
			RowConstraints zeile4 = new RowConstraints(50);
			gridPane.getColumnConstraints().addAll(spalte1, spalte2, spalte3, spalte4);

			gridPane.getRowConstraints().addAll(zeile1, zeile2, zeile3, zeile4 );
/************************************************************************************************************************
* Die einzelnen Elemente passgenau auf das GridPane legen:
 ***********************************************************************************************************************/
/*** Gitter anzeigen um Elemente auszurichten (auskommentieren nachdem Elemente ausgerichtet wurden!!): gridPane.setGridLinesVisible(true);   **/
/*** >>>>>>>>>>>>> **/
//						gridPane.setGridLinesVisible(true);
/*** <<<<<<<<<<<< **/
/***           Node child       	int columnIndex    int rowIndex       int colspan              int rowspan             */
/***        welches Element  	    welche Spalte      welche Zeile   wieviele Spalten lang    wieviele Zeilen lang        */
gridPane.add(	welcome, 	  				  1, 				0, 				   4, 						1					);
gridPane.add(	instructions, 	  			  1, 				1, 				   2, 						1					);
gridPane.add(	name, 					      1, 				2, 				   2, 						1					);
gridPane.add(	go, 	  					  1, 				3, 				   2, 						1					);
gridPane.add(	back, 		          		  1, 				4, 		           2, 			            1                   );
GridPane.setHalignment(instructions, HPos.CENTER);
GridPane.setHalignment(go, HPos.CENTER);
GridPane.setHalignment(back, HPos.CENTER);

//______________________ Button-Activation: _______________________________
	/*** java 8 NEUE ActionEvent-Funktionen    ****/
go.setOnAction(e -> {
	if(!name.getText().equals("") && name.getText()!=null )
	{
		missingName = false;
		_03_0_GameView_page3 GO = new _03_0_GameView_page3();
		try
		{
			GO.start(primaryStage);
		} catch(Throwable e1){e1.printStackTrace();}
	}
	else{missingName = true; start(primaryStage);}
});
back.setOnAction(e -> {
	missingName  = false;
	_00_Launcher_page1 loadMainPage1 = new _00_Launcher_page1();
	loadMainPage1.start(primaryStage);
});
		if(_00_Launcher_page1.style1Selected){scene.getStylesheets().add(getClass().getResource("Style 1.css").toExternalForm());}
		if(_00_Launcher_page1.style2Selected){scene.getStylesheets().add(getClass().getResource("Style 2.css").toExternalForm());}
		if(_00_Launcher_page1.style3Selected){scene.getStylesheets().add(getClass().getResource("Style 3.css").toExternalForm());}
/***  Stage Obejekt erstellen und anzeigen:   ***/
		primaryStage.getEventDispatcher();
		primaryStage.setOnCloseRequest(e ->	{ System.exit(0);});
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	public static void main(String[] args){launch(args);}
}