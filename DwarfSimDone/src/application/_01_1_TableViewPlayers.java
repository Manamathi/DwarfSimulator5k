package application;

/*@formatter:off */
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings({"rawtypes","unchecked"})
public class _01_1_TableViewPlayers extends Application
{

	public VBox vbox;
	public TableView<Player> table = new TableView<Player>();


	@Override
	public void start(Stage stage)
	{
		table.setEditable(true);
		TableColumn name = new TableColumn("Name");
		name.setMinWidth(100);
		name.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(new EventHandler<CellEditEvent<Player, String>>()
		{
			@Override
			public void handle(CellEditEvent<Player, String> t)
			{
				((Player) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
			}
		});

		TableColumn level = new TableColumn("Level");
		level.setMinWidth(100);
		level.setCellValueFactory(new PropertyValueFactory<Player, Integer>("level"));
		level.setCellFactory(TextFieldTableCell.forTableColumn());
		level.setOnEditCommit(new EventHandler<CellEditEvent<Player, Integer>>()
		{
			@Override
			public void handle(CellEditEvent<Player, Integer> t)
			{
				((Player) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLevel(t.getNewValue());
			}
		});

		TableColumn gold = new TableColumn("Gold");
		gold.setMinWidth(200);
		gold.setCellValueFactory(new PropertyValueFactory<Player, Long>("gold"));
		gold.setCellFactory(TextFieldTableCell.forTableColumn());
		gold.setOnEditCommit(new EventHandler<CellEditEvent<Player, Long>>()
		{
			@Override
			public void handle(CellEditEvent<Player, Long> t)
			{
				((Player) t.getTableView().getItems().get(t.getTablePosition().getRow())).setGold(t.getNewValue());
			}
		});

//		table.setItems(data);
		table.getColumns().addAll(name, level, gold);

		final TextField addFirstName = new TextField();
		addFirstName.setPromptText("First Name");
		addFirstName.setMaxWidth(name.getPrefWidth());
		final TextField addLastName = new TextField();
		addLastName.setMaxWidth(level.getPrefWidth());
		addLastName.setPromptText("Last Name");
		final TextField addEmail = new TextField();
		addEmail.setMaxWidth(gold.getPrefWidth());
		addEmail.setPromptText("Email");


		vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(table);
//		((Group) scene.getRoot()).getChildren().addAll(vbox);
//		stage.setScene(scene);
//		stage.show();
	}

	public static class Player
	{
		private final SimpleStringProperty name;
		private final SimpleIntegerProperty level;
		private final SimpleLongProperty gold;
		private Player(String name, int level, long gold)
		{
			this.name = new SimpleStringProperty(name);
			this.level = new SimpleIntegerProperty(level);
			this.gold = new SimpleLongProperty(gold);
		}
		public String getFirstName(){return name.get();}
		public int getLastName(){return level.get();}
		public long getGold(){return gold.get();}
		public void setName(String nameCurrent){name.set(nameCurrent);}
		public void setLevel(int levelCurrent){level.set(levelCurrent);}
		public void setGold(long goldCurrent){gold.set(goldCurrent);}

	}
	public static void main(String[] args){launch(args);}
}