package application;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/*@formatter:off */
@SuppressWarnings({"restriction"})
public class _03_2_Profil_page3 extends Application
{
	public GridPane gridPane ; // komplexes Gitter / Raster mit exakter KomponentenAnordnung
	public Label level, stamina, xperience, gold, strenght, dexterity, kills, defence, amor, saveDate;
	public TextField txtName, txtLevel, txtStamina, txtXperience, txtGold, txtStrenght, txtDexterity, txtKills, txtDefence, txtAmor, txtDate, txtTime;
	public Scene scene;
	private Image tussi;
	public ImageView pic;
	public HBox pictureContainer;
	@Override
	public void start(Stage StageProfil)
	{
		StageProfil.setTitle("Profil");
		gridPane = new GridPane(); // komplexes Gitter / Raster mit exakter KomponentenAnordnung
		tussi = new Image(_03_2_Profil_page3.class.getResourceAsStream("Prinzessin_transparent.png"));
		pic = new ImageView(tussi);
		pic.setFitWidth(120); pic.setFitHeight(180);
		pic.setImage(tussi);
		pictureContainer = new HBox(10);
		pictureContainer.getChildren().addAll(pic);
		txtName = new TextField("Name (NOT editable <aus Spielstand>)"); txtName.setMaxWidth(300); txtName.editableProperty().set(false);
		level = new Label("Level:");
		txtLevel = new TextField("Zahl"); txtLevel.setMaxWidth(90); txtLevel.editableProperty().set(false);
		gold = new Label("Gold:");
		txtGold = new TextField("Anzahl"); txtGold.setMaxWidth(120); txtGold.editableProperty().set(false);
		kills= new Label("Kills:");
		txtKills = new TextField("Anzahl"); txtKills.setMaxWidth(120); txtKills.editableProperty().set(false);
		strenght = new Label("Staerke:");
		txtStrenght = new TextField("Wert"); txtStrenght.setMaxWidth(120); txtStrenght.editableProperty().set(false);
		dexterity= new Label("Agilitae¤t:");
		txtDexterity = new TextField("Wert"); txtDexterity.setMaxWidth(120); txtDexterity.editableProperty().set(false);
		defence= new Label("Verteidigung:");
		txtDefence = new TextField("Wert"); txtDefence.setMaxWidth(120); txtDefence.editableProperty().set(false);
		amor = new Label("Ruestung:");
		txtAmor = new TextField("Wert"); txtAmor.setMaxWidth(120); txtAmor.editableProperty().set(false);
		stamina = new Label("Ausdauer:");
		txtStamina = new TextField("Zahlen/ Zahlen"); txtStamina.setMaxWidth(150); txtStamina.editableProperty().set(false);
		xperience = new Label("Erfahrung:");
		txtXperience = new TextField("Zahlen/ Zahlen"); txtXperience.setMaxWidth(150); txtXperience.editableProperty().set(false);
//___________________ Layout-GridPane-Komponenten anbringen __________________
		gridPane.setPadding(new Insets(10));
		ColumnConstraints spalte1 = new ColumnConstraints(0);
		ColumnConstraints spalte2 = new ColumnConstraints(100);
		ColumnConstraints spalte3 = new ColumnConstraints(90);
		ColumnConstraints spalte4 = new ColumnConstraints(200);
		RowConstraints zeile1 = new RowConstraints(0);
		RowConstraints zeile2 = new RowConstraints(50);
		RowConstraints zeile3 = new RowConstraints(50);
		RowConstraints zeile4 = new RowConstraints(50);
		RowConstraints zeile5 = new RowConstraints(35);
		RowConstraints zeile6 = new RowConstraints(30);
		RowConstraints zeile7 = new RowConstraints(30);
		RowConstraints zeile8 = new RowConstraints(30);
		RowConstraints zeile9 = new RowConstraints(30);
		RowConstraints zeile10= new RowConstraints(30);
		RowConstraints zeile11 = new RowConstraints(30);
		RowConstraints zeile12 = new RowConstraints(30);
		RowConstraints zeile13 = new RowConstraints(30);
		RowConstraints zeile14 = new RowConstraints(30);
		RowConstraints zeile15 = new RowConstraints(30);
		RowConstraints zeile16 = new RowConstraints(80);
		gridPane.getColumnConstraints().addAll(spalte1, spalte2, spalte3, spalte4);

		gridPane.getRowConstraints().addAll(zeile1, zeile2, zeile3, zeile4, zeile5, zeile6, zeile7, zeile8, zeile9, zeile10, zeile11, zeile12, zeile13, zeile14, zeile15, zeile16 );
/************************************************************************************************************************
 * Die einzelnen Elemente passgenau auf das GridPane legen:
 ***********************************************************************************************************************/
/*** Gitter anzeigen um Elemente auszurichten (auskommentieren nachdem Elemente ausgerichtet wurden!!): gridPane.setGridLinesVisible(true);   **/
/*** >>>>>>>>>>>>> **/
//				gridPane.setGridLinesVisible(true);
/*** <<<<<<<<<<<< **/
/***           Node child       	int columnIndex    int rowIndex       int colspan              int rowspan             */
/***        welches Element  	    welche Spalte      welche Zeile   wieviele Spalten lang    wieviele Zeilen lang        */

gridPane.add(	pictureContainer, 	  		  0, 				0, 				   4, 						5					);
gridPane.add(	txtName, 	  				  2, 				1, 				   4, 						1					);
gridPane.add(	level, 	  				      2, 				2, 				   1, 						1					);
gridPane.add(	txtLevel, 					  3, 				2, 			       1, 						1					);
gridPane.add(	gold, 		                  1, 				5, 		           1, 			            1                   );
gridPane.add(	txtGold, 					  2, 				5, 			       1, 						1					);
gridPane.add(	kills, 					      1, 				6, 				   1, 						1					);
gridPane.add(	txtKills, 					  2, 				6, 			       1, 						1					);
gridPane.add(	strenght, 				      1, 				7, 				   1, 						1					);
gridPane.add(	txtStrenght, 				  2, 				7, 			       1, 						1					);
gridPane.add(	dexterity, 				      1, 				8, 			       1, 						1					);
gridPane.add(	txtDexterity, 				  2, 				8, 			       1, 						1					);
gridPane.add(	defence, 					  1, 				9, 			       1, 						1					);
gridPane.add(	txtDefence, 				  2, 				9, 			       1, 						1					);
gridPane.add(	amor, 					      1, 				10, 			   1, 						1					);
gridPane.add(	txtAmor, 					  2, 				10, 			   1, 						1					);
gridPane.add(	stamina, 					  1, 				11, 			   1, 						1					);
gridPane.add(	txtStamina, 				  1, 				12, 			   2, 						1					);
gridPane.add(	xperience, 	  		          1, 				13, 			   1, 						1					);
gridPane.add(	txtXperience, 				  1, 				14, 			   2, 						1					);

GridPane.setValignment(pictureContainer, VPos.TOP);
GridPane.setHalignment(txtName, HPos.RIGHT);
GridPane.setHalignment(level, HPos.CENTER);
GridPane.setHalignment(gold, HPos.CENTER);
GridPane.setHalignment(kills, HPos.CENTER);
GridPane.setHalignment(strenght, HPos.CENTER);
GridPane.setHalignment(dexterity, HPos.CENTER);
GridPane.setHalignment(defence, HPos.CENTER);
GridPane.setHalignment(amor, HPos.CENTER);
GridPane.setHalignment(stamina, HPos.CENTER);
GridPane.setHalignment(xperience, HPos.CENTER);
GridPane.setHalignment(txtStamina, HPos.CENTER);
GridPane.setHalignment(txtXperience, HPos.CENTER);
 //margins around the whole grid
//(                                 top   /right  /bottom  /left
//txtStamina.setPadding(new Insets( 	 500, 	1, 	    5, 	     1));
//txtXperience.setPadding(new Insets(30));
/***  Stage Obejekt erstellen und anzeigen:   ***/
	scene = new Scene(gridPane, 900, 700);
	if(_00_Launcher_page1.style1Selected){scene.getStylesheets().add(getClass().getResource("Style 1.css").toExternalForm());}
	if(_00_Launcher_page1.style2Selected){scene.getStylesheets().add(getClass().getResource("Style 2.css").toExternalForm());}
	if(_00_Launcher_page1.style3Selected){scene.getStylesheets().add(getClass().getResource("Style 3.css").toExternalForm());}
		StageProfil.getEventDispatcher();
		StageProfil.setOnCloseRequest(e ->	{ System.exit(0);});
		StageProfil.setScene(scene);
		StageProfil.setResizable(false);
//		primaryStage.show();
	}
	public static void main(String[] args){launch(args);}
}

