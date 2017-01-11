package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/*@formatter:off */
public class _03_4_Tab_SpielOptionen_page3 extends Application
{
	public AnchorPane anker;
	public GridPane gridPane ; // komplexes Gitter / Raster mit exakter KomponentenAnordnung
	public static Scene scene;
	public static Button exit, start, load;
	public static RadioButton style1, style2, style3;
	public  static boolean spielOptionSelection = false;
	@SuppressWarnings("restriction")
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Spielansicht");
		gridPane = new GridPane(); // komplexes Gitter / Raster mit exakter KomponentenAnordnung
		// ORIGINAL-Groesse:
		scene = new Scene(gridPane, 900, 700);
//________________ Komponenten-Initialisierung
		start = new Button("Neues Spiel");
		load = new Button("Spielstand laden");
		exit = new Button("Spiel beenden");
		start.setMaxWidth(150);
		load.setMaxWidth(150);
		exit.setMaxWidth(150);
		final ToggleGroup stylesGroup = new ToggleGroup();
		style1 = new RadioButton("Style 1");
		style1.setToggleGroup(stylesGroup);
		style2 = new RadioButton("Style 2");
		style2.setToggleGroup(stylesGroup);
		style3 = new RadioButton("Style 3");
		style3.setToggleGroup(stylesGroup);

//___________________ Layout-GridPane-Komponenten anbringen __________________
		gridPane.setPadding(new Insets(10));
		ColumnConstraints spalte1 = new ColumnConstraints(185);
		ColumnConstraints spalte2 = new ColumnConstraints(75);
		ColumnConstraints spalte3 = new ColumnConstraints(105);
		ColumnConstraints spalte4 = new ColumnConstraints(25);
		ColumnConstraints spalte5 = new ColumnConstraints(140);
		ColumnConstraints spalte6 = new ColumnConstraints(170);
		RowConstraints zeile1 = new RowConstraints(220);
		RowConstraints zeile2 = new RowConstraints(50);
		RowConstraints zeile3 = new RowConstraints(50);
		RowConstraints zeile4 = new RowConstraints(50);
		RowConstraints zeile5 = new RowConstraints(50);
		RowConstraints zeile6 = new RowConstraints(80);
		gridPane.getColumnConstraints().addAll(spalte1, spalte2, spalte3, spalte4, spalte5, spalte6);

		gridPane.getRowConstraints().addAll(zeile1, zeile2, zeile3, zeile4, zeile5, zeile6 );
/************************************************************************************************************************
 * Die einzelnen Elemente passgenau auf das GridPane legen:
 ***********************************************************************************************************************/
/*** Gitter anzeigen um Elemente auszurichten (auskommentieren nachdem Elemente ausgerichtet wurden): gridPane.setGridLinesVisible(true);   **/
/*** >>>>>>>>>>>>> **/
//		gridPane.setGridLinesVisible(true);
/*** <<<<<<<<<<<< **/
/***           Node child       	int columnIndex    int rowIndex       int colspan              int rowspan             */
/***        welches Element  	    welche Spalte      welche Zeile   wieviele Spalten lang    wieviele Zeilen lang        */

gridPane.add(	start, 	  				  3, 				1, 				   2, 						1					);
gridPane.add(	load, 					  3, 				2, 				   2, 						1					);
gridPane.add(	exit, 				      3, 				3, 				   2, 						1					);
gridPane.add(	style1, 				  2, 				5, 			   2, 						1					);
gridPane.add(	style2, 				  4, 				5, 			   2, 						1					);
gridPane.add(	style3, 				  5, 				5, 			   2, 						1					);

GridPane.setHalignment(style2, HPos.LEFT);
//______________________ Button-Activation: _______________________________
	/*** java 8 ActionEvent-Funktionen    ****/
		start.setOnAction(e -> {
			_02_WelcomeNewWorld_page2 newGame_page2 = new _02_WelcomeNewWorld_page2();
			newGame_page2.start(primaryStage);
		});
		load.setOnAction(e -> {
			_01_0_LoadSaveGame_page2 loadPage2 = new _01_0_LoadSaveGame_page2();
			loadPage2.start(primaryStage);
		});
		exit.setOnAction(actionEvent -> Platform.exit());
		style1.setOnAction(e ->
		{
			spielOptionSelection = true;
			_00_Launcher_page1.style1Selected = true;
			_00_Launcher_page1.style2Selected = false;
			_00_Launcher_page1.style3Selected = false;
			_03_4_Tab_SpielOptionen_page3.style1.setSelected(true);
			_03_0_GameView_page3 gameView = new  _03_0_GameView_page3();
			try
			{
				gameView.start(primaryStage);
			} catch(Throwable e1){e1.printStackTrace();}
		});
		style2.setOnAction(e ->
		{
			spielOptionSelection = true;
			_00_Launcher_page1.style2Selected = true;
			_00_Launcher_page1.style1Selected = false;
			_00_Launcher_page1.style3Selected = false;
			_03_4_Tab_SpielOptionen_page3.style2.setSelected(true);
			_03_0_GameView_page3 gameView = new  _03_0_GameView_page3();
			try
			{
				gameView.start(primaryStage);
			} catch(Throwable e1){e1.printStackTrace();}
		});
		style3.setOnAction(e ->
		{
			spielOptionSelection = true;
			_00_Launcher_page1.style1Selected = false;
			_00_Launcher_page1.style2Selected = false;
			_00_Launcher_page1.style3Selected = true;
			_03_4_Tab_SpielOptionen_page3.style3.setSelected(true);
			_03_0_GameView_page3 gameView = new  _03_0_GameView_page3();
			try
			{
				gameView.start(primaryStage);
			} catch(Throwable e1){e1.printStackTrace();}
		});
		if(_00_Launcher_page1.style1Selected){_03_4_Tab_SpielOptionen_page3.style1.setSelected(true);scene.getStylesheets().add(getClass().getResource("Style 1.css").toExternalForm());}
		if(_00_Launcher_page1.style2Selected){_03_4_Tab_SpielOptionen_page3.style2.setSelected(true);scene.getStylesheets().add(getClass().getResource("Style 2.css").toExternalForm());}
		if(_00_Launcher_page1.style3Selected){_03_4_Tab_SpielOptionen_page3.style3.setSelected(true);scene.getStylesheets().add(getClass().getResource("Style 3.css").toExternalForm());}
/***  Stage Obejekt erstellen und anzeigen:   ***/
		primaryStage.getEventDispatcher();
		primaryStage.setOnCloseRequest(e ->	{ System.exit(0);});
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	public static void main(String[] args){launch(args);}
}