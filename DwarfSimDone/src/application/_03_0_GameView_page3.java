package application;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/*@formatter:off */
@SuppressWarnings("restriction")
public class _03_0_GameView_page3 extends Application
{
	public TabPane tabPane;
	public static Scene scene;
	@Override
	public void start(Stage GameViewStage) throws FileNotFoundException
	{
		GameViewStage.setTitle("Spielwelt");
		tabPane = new TabPane();
		_03_1_SpielFeld gameArea = new _03_1_SpielFeld();
// ALLE Tabs NICHT schliessen koennen , falls fehlt, können Tabs mit X geschlossen, ABER NICHT mehr geöffnet werden!!
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
// hierdurch koennen die Tabs individuell selektiert werden (moechte man z.B., dass statt dem Tab 1 ds Tb 3 beim laden diese DREIFACH-TabMenues angezeigt wird, so kann man dies nun explizit auswählen durch: )
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
//		gridPane = new GridPane(); // komplexes Gitter / Raster mit exakter KomponentenAnordnung
		scene = new Scene(tabPane, 900, 700);
		
/**********************************************************************
 * Tab / Reitermenue 1: SPIEL
 ************************************************************************/
		Tab tab1 = new Tab();
		tab1.setText("Spiel");
		tab1.setContent(gameArea);
		
		tab1.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tab1.isSelected()) {
                	//System.out.println("game selected");
                	gameArea.paint();
                }
            }
        });
/**********************************************************************
 * Tab / Reitermenue 2: Profil
************************************************************************/
		_03_2_Profil_page3 profilLayOut = new  _03_2_Profil_page3();
		Tab tab2 = new Tab();
		tab2.setText("Profil");
		profilLayOut.start(GameViewStage);
		tab2.setContent(profilLayOut.gridPane);
		tab2.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tab2.isSelected()) {
                	gameArea.setThreadPassive(); 
                }
            }
        });
/**********************************************************************
 * Tab / Reitermenue 3: Inventar
 ************************************************************************/
		_03_3_Inventar_page3 inventarLayout = new  _03_3_Inventar_page3();
		Tab tab3 = new Tab();
		tab3.setText("Inventar");
		inventarLayout.start(GameViewStage);
		tab3.setContent(inventarLayout.gridPane);
		tab3.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tab3.isSelected()) {
                	gameArea.setThreadPassive(); 
                }
            }
        });
/**********************************************************************
 * Tab / Reitermenue 4: Spiel-Optionen
************************************************************************/
		_03_4_Tab_SpielOptionen_page3 spielOptionen = new  _03_4_Tab_SpielOptionen_page3();
		Tab tab4 = new Tab(); tab4.setText("Spiel-Optionen");
		spielOptionen.start(GameViewStage);
		tab4.setContent(spielOptionen.gridPane);
		tab4.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tab4.isSelected()) {
                	gameArea.setThreadPassive(); 
                }
            }
        });
		/**********************************************************************
		 * ACHTUNG: falls man das Design von der SpielOptionen-Seite aus geaendert hat,
		 * wird sofort wieder zu dieser zurueckgesprungen :-)
		 ************************************************************************/
		if(_03_4_Tab_SpielOptionen_page3.spielOptionSelection)
		{
			_03_4_Tab_SpielOptionen_page3.spielOptionSelection = false;
			selectionModel.select(tab4); //select by object
//			selectionModel.select(1); //select by index starting with 0
//			selectionModel.clearSelection(); //clear your selection
		}
/********************************************************************************
 *  KeyEingabe aktivieren:
 ****************************************************************************/
// KEYBOARD INPUT !!!!
 // this eventFilter does not care about focus. -> immer ansprechbar                                                                                                                  // spielfeld
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> /* System.out.println("Pressed: "+event.getCode())*/ doMove(event.getCode().toString(), gameArea));

/********************************************************************************
 *  Alle Tabs auf das TabMenueLayout schnallen und schliesslich das GUI-Fenster aufsetzen
 ****************************************************************************/
		tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
		if(_00_Launcher_page1.style1Selected){scene.getStylesheets().add(getClass().getResource("Style 1.css").toExternalForm());}
		if(_00_Launcher_page1.style2Selected){scene.getStylesheets().add(getClass().getResource("Style 2.css").toExternalForm());}
		if(_00_Launcher_page1.style3Selected){scene.getStylesheets().add(getClass().getResource("Style 3.css").toExternalForm());}
/***  Stage Obejekt erstellen und anzeigen:   ***/
		GameViewStage.getEventDispatcher();
		GameViewStage.setOnCloseRequest(e ->	{ System.exit(0);});
		GameViewStage.setScene(scene);
		GameViewStage.setResizable(false);
		GameViewStage.show();
	}

	void doMove(String keyCode, _03_1_SpielFeld gameArea)
	{
    	String key = keyCode;
    	if (gameArea.gameOver == false) {
		    if (key.equals("W")){gameArea.moveUP();}
		    if (key.equals("S")){gameArea.moveDN();}
		    if (key.equals("A")){gameArea.moveLE();}
		    if (key.equals("D")){gameArea.moveRI();}
		    if (key.equals("SPACE")){gameArea.reportCo();}
		    return;
    	}
	}
	public static void main(String[] args){launch(args);}
}
