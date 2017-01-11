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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/*@formatter:off */
public class _01_0_LoadSaveGame_page2 extends Application
{
	public GridPane gridPane ; // komplexes Gitter / Raster mit exakter KomponentenAnordnung
	public Label level, stamina, xperience, gold, strenght, dexterity, kills, defence, amor, saveDate;
	public TextField txtLevel, txtStamina, txtXperience, txtGold, txtStrenght, txtDexterity, txtKills, txtDefence, txtAmor, txtDate, txtTime;
	public static Scene scene;
	public static Button load, back;
	@SuppressWarnings("restriction")
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Spielstand Laden");
		gridPane = new GridPane(); // komplexes Gitter / Raster mit exakter KomponentenAnordnung

		_01_1_TableViewPlayers playerTable = new _01_1_TableViewPlayers();
		playerTable.start(primaryStage);
		VBox playerBox = new VBox(playerTable.vbox);
//________________ Labels-Initialisierung
		level = new Label("Level:");
		stamina = new Label("Ausdauer:");
		xperience = new Label("Erfahrung:");
		gold = new Label("Gold:");
		strenght = new Label("Staerke:");
		dexterity= new Label("Agilitaet:");
		kills= new Label("Kills:");
		defence= new Label("Verteidigung:");
		amor = new Label("Ruestung:");
		saveDate = new Label("Gespeichert:");    saveDate.setFont(Font.font("LucidaConsole", FontWeight.LIGHT, 10));
		txtLevel = new TextField("LevelWert"); txtLevel.setMaxWidth(300); txtLevel.editableProperty().set(false);
		txtStamina = new TextField("AusdauerWert"); txtStamina.setMaxWidth(300); txtStamina.editableProperty().set(false);
		txtXperience = new TextField("ErfahrungsWert"); txtXperience.setMaxWidth(300); txtXperience.editableProperty().set(false);
		txtGold = new TextField("GoldAnzahl"); txtGold.setMaxWidth(150); txtGold.editableProperty().set(false);
		txtStrenght = new TextField("StaerkeWert"); txtStrenght.setMaxWidth(150); txtStrenght.editableProperty().set(false);
		txtDexterity = new TextField("AgilitaetsWert"); txtDexterity.setMaxWidth(150); txtDexterity.editableProperty().set(false);
		txtKills = new TextField("KillsAnzahl"); txtKills.setMaxWidth(150); txtKills.editableProperty().set(false);
		txtDefence = new TextField("VerteidigungsWert"); txtDefence.setMaxWidth(150); txtDefence.editableProperty().set(false);
		txtAmor = new TextField("RuestungWert"); txtAmor.setMaxWidth(150); txtAmor.editableProperty().set(false);
		txtDate = new TextField("Datumsangabe"); txtDate.setMaxWidth(150); txtDate.editableProperty().set(false); txtDate.setFont(Font.font("LucidaConsole", FontWeight.LIGHT, 10));
		txtTime = new TextField("Zeitangabe");txtTime.setMaxWidth(150); txtTime.editableProperty().set(false);  txtTime.setFont(Font.font("LucidaConsole", FontWeight.LIGHT, 10));
		load = new Button("Spielstand laden");
		back = new Button("Zurueck zur Startseite");
//___________________ Layout-GridPane-Komponenten anbringen __________________
		gridPane.setPadding(new Insets(10));
		ColumnConstraints spalte1 = new ColumnConstraints(170);
		ColumnConstraints spalte2 = new ColumnConstraints(115);
		ColumnConstraints spalte3 = new ColumnConstraints(125);
		ColumnConstraints spalte4 = new ColumnConstraints(120);
		ColumnConstraints spalte5 = new ColumnConstraints(155);
		ColumnConstraints spalte6 = new ColumnConstraints(150);
		RowConstraints zeile1 = new RowConstraints(50);
		RowConstraints zeile2 = new RowConstraints(50);
		RowConstraints zeile3 = new RowConstraints(50);
		RowConstraints zeile4 = new RowConstraints(50);
		RowConstraints zeile5 = new RowConstraints(50);
		RowConstraints zeile6 = new RowConstraints(40);
		RowConstraints zeile7 = new RowConstraints(40);
		RowConstraints zeile8 = new RowConstraints(40);
		RowConstraints zeile9 = new RowConstraints(40);
		RowConstraints zeile10= new RowConstraints(40);
		RowConstraints zeile11 = new RowConstraints(40);
		RowConstraints zeile12 = new RowConstraints(25);
		RowConstraints zeile13 = new RowConstraints(50);
		RowConstraints zeile14 = new RowConstraints(50);
		gridPane.getColumnConstraints().addAll(spalte1, spalte2, spalte3, spalte4, spalte5, spalte6);

		gridPane.getRowConstraints().addAll(zeile1, zeile2, zeile3, zeile4, zeile5, zeile6, zeile7, zeile8, zeile9, zeile10, zeile11, zeile12, zeile13, zeile14 );
/************************************************************************************************************************
 * Die einzelnen Elemente passgenau auf das GridPane legen:
 ***********************************************************************************************************************/
/*** Gitter anzeigen um Elemente auszurichten (auskommentieren nachdem Elemente ausgerichtet wurden!!): gridPane.setGridLinesVisible(true);   **/
/*** >>>>>>>>>>>>> **/
//				gridPane.setGridLinesVisible(true);
/*** <<<<<<<<<<<< **/
/***           Node child       	int columnIndex    int rowIndex       int colspan              int rowspan             */
/***        welches Element  	    welche Spalte      welche Zeile   wieviele Spalten lang    wieviele Zeilen lang        */
gridPane.add(	playerBox, 	  				  1, 				0, 				   4, 						5					);
gridPane.add(	level, 	  				      1, 				5, 				   1, 						1					);
gridPane.add(	stamina, 					  1, 				6, 				   1, 						1					);
gridPane.add(	xperience, 	  		          1, 				7, 				   1, 						1					);
gridPane.add(	gold, 		                  1, 				8, 		           1, 			            1                   );
gridPane.add(	strenght, 				      1, 				9, 				   1, 						1					);
gridPane.add(	dexterity, 				      1, 				10, 			   1, 						1					);
gridPane.add(	kills, 					      3, 				8, 				   1, 						1					);
gridPane.add(	defence, 					  3, 				9, 			       1, 						1					);
gridPane.add(	amor, 					      3, 				10, 			   1, 						1					);
gridPane.add(	txtLevel, 					  2, 				5, 			       2, 						1					);
gridPane.add(	txtStamina, 				  2, 				6, 			       2, 						1					);
gridPane.add(	txtXperience, 				  2, 				7, 			       2, 						1					);
gridPane.add(	txtGold, 					  2, 				8, 			       1, 						1					);
gridPane.add(	txtStrenght, 				  2, 				9, 			       1, 						1					);
gridPane.add(	txtDexterity, 				  2, 				10, 			   1, 						1					);
gridPane.add(	saveDate, 				      1, 				11, 			   1, 						1					);
gridPane.add(	txtKills, 					  4, 				8, 			       1, 						1					);
gridPane.add(	txtDefence, 				  4, 				9, 			       1, 						1					);
gridPane.add(	txtAmor, 					  4, 				10, 			   1, 						1					);
gridPane.add(	txtDate, 					  2, 				11, 			   1, 						1					);
gridPane.add(	txtTime, 					  3, 				11, 			   1, 						1					);
gridPane.add(	load, 					      1, 				13, 			   3, 						1					);
gridPane.add(	back, 					      4, 				13, 			   2, 						1					);

	GridPane.setHalignment(kills, HPos.CENTER);
	GridPane.setHalignment(defence, HPos.CENTER);
	GridPane.setHalignment(amor, HPos.CENTER);

//______________________ Button-Activation: _______________________________
	/*** java 8 NEUE ActionEvent-Funktionen YEAH!!!!!!    ****/
	back.setOnAction(e -> {
		_00_Launcher_page1 loadMainPage1 = new _00_Launcher_page1();
		loadMainPage1.start(primaryStage);
	});
		load.setOnAction(e -> {

			/********************
			 *  Button hier noch mit Funktionen befüllen, um Spieldaten aus den anderen Klassen zu importieren
			 *******************/
		});
/***  Stage Obejekt erstellen und anzeigen:   ***/
	scene = new Scene(gridPane, 900, 700);
	if(_00_Launcher_page1.style1Selected){scene.getStylesheets().add(getClass().getResource("Style 1.css").toExternalForm());}
	if(_00_Launcher_page1.style2Selected){scene.getStylesheets().add(getClass().getResource("Style 2.css").toExternalForm());}
	if(_00_Launcher_page1.style3Selected){scene.getStylesheets().add(getClass().getResource("Style 3.css").toExternalForm());}
		primaryStage.getEventDispatcher();
		primaryStage.setOnCloseRequest(e ->	{ System.exit(0);});
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	public static void main(String[] args){launch(args);}
}