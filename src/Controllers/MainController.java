package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Board;
import Model.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainController implements Initializable{

	@FXML
	Canvas canvas;

	@FXML
	Pane pane;

	@FXML
	Menu menu;

	@FXML
	Button resetButton;
	
	@FXML
	MenuItem about,htp;

	@FXML
	VBox turnColorVbox;
	final int C =7;
	final int L = 6;

	private Board board;
	private int distanceHeight;
	private int distanceWidth;
	private int padding;
	private int size;
	private Color turn;
	private GraphicsContext gc;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = canvas.getGraphicsContext2D();
		turn=Color.RED;
		board = new Board(C,L);
		
		pane.widthProperty().addListener(evt -> draw());
		pane.heightProperty().addListener(evt -> draw());
		pane.setStyle("-fx-background-color: blue");
		gc.setFill(Color.WHITE);
		changeVboxTurnColor(turn);
		about.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				launchNewWindow("/about.fxml","About");

			}
		});
		resetButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e->{
			this.reset();
		});
		htp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				launchNewWindow("/howtoplay.fxml","How to play");

			}
		});


		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
			final double MOUSEPOSITIONX = e.getX();
			boolean placeTokenOk = true;

			if(MOUSEPOSITIONX>0 && MOUSEPOSITIONX<distanceWidth+padding) {
				placeTokenOk =board.placeToken(0, turn);
			}
			if(MOUSEPOSITIONX>distanceWidth+padding && MOUSEPOSITIONX<(2*distanceWidth)+padding) {
				placeTokenOk =board.placeToken(1, turn);
			}
			if(MOUSEPOSITIONX>2*(distanceWidth)+padding && MOUSEPOSITIONX<3*(distanceWidth)+padding) {
				placeTokenOk =board.placeToken(2, turn);
			}

			if(MOUSEPOSITIONX>3*(distanceWidth)+padding && MOUSEPOSITIONX<4*(distanceWidth)+padding) {
				placeTokenOk =board.placeToken(3, turn);
			}

			if(MOUSEPOSITIONX>4*(distanceWidth)+padding && MOUSEPOSITIONX<5*(distanceWidth)+padding) {
				placeTokenOk =board.placeToken(4, turn);
			}

			if(MOUSEPOSITIONX>5*(distanceWidth)+padding && MOUSEPOSITIONX<6*(distanceWidth)+padding) {
				placeTokenOk =board.placeToken(5, turn);
			}

			if(MOUSEPOSITIONX>6*(distanceWidth)+padding && MOUSEPOSITIONX<7*(distanceWidth)+padding) {
				placeTokenOk =board.placeToken(6, turn);
			}

			if(placeTokenOk) {
				this.changeTurnColor(turn);

			}
			//System.out.println(this.board);
			this.draw();
		});




	}

	private void changeTurnColor(Color color) {
		if(color.equals(Color.RED)) {
			turn=Color.YELLOW;			
		}
		if(color.equals(Color.YELLOW)) {
			turn=Color.RED;			
		}
		changeVboxTurnColor(turn);
	}
	
	private void changeVboxTurnColor(Color color) {
		if(color.equals(Color.RED)) {		
			turnColorVbox.setStyle("-fx-background-color:red;");
		}
		if(color.equals(Color.YELLOW)) {
			turnColorVbox.setStyle("-fx-background-color:yellow;");
		}
	}
	
	private void launchNewWindow(String path,String title) {
		Stage stage = new Stage();
		Parent root2=null;
		FXMLLoader loader = new FXMLLoader();
		URL fxmlFileUrl = getClass().getResource(path);
		if (fxmlFileUrl == null) {
			System.out.println("Impossible de charger le fichier fxml");
			System.exit(-1);
		}
		loader.setLocation(fxmlFileUrl);
		try {
			root2 = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}

		Scene scene = new Scene(root2);

		stage.setResizable(false);
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}

	public void setSceneListener(Scene scene) {
		scene.setOnKeyPressed(e->{
			switch (e.getCode()) {
			case F5:
				this.reset();
				turn=Color.RED;
				break;

			default:
				break;
			}
		});
	}

	private void reset() {
		for(int i=0;i<C;i++) {
			for(int j=0;j<L;j++) {
				board.getBoard()[i][j].setState(State.Neutral);
			}
		}
		this.changeTurnColor(Color.YELLOW);
		this.draw();
	}
	private void draw() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		canvas.setWidth(pane.getWidth());
		canvas.setHeight(pane.getHeight());
		//System.out.println("Hauteur: "+canvas.getHeight());
		//System.out.println("Largeur: "+canvas.getWidth());
		Rectangle2D screenBounds =Screen.getPrimary().getBounds();


		final int DEFAULTWIDTH = 700;

		if(screenBounds.getHeight()<canvas.getWidth()) {
			distanceHeight= (int) ((screenBounds.getHeight()*100+(canvas.getWidth()-screenBounds.getHeight())*5)/DEFAULTWIDTH);
			distanceWidth= (int) ((screenBounds.getHeight()*100+(canvas.getWidth()-screenBounds.getHeight())*50)/DEFAULTWIDTH);
			size = (int) ((screenBounds.getHeight()*100/DEFAULTWIDTH)) ;
			padding=(int) ((canvas.getWidth()-screenBounds.getHeight())/4);
		}else {
			distanceHeight= (int) (((canvas.getWidth()*100))/DEFAULTWIDTH);
			distanceWidth=distanceHeight;
			size = (int) ((canvas.getWidth()*100)/DEFAULTWIDTH) ;
			padding=0;
		}


		for(int i=0;i<C;i++) {
			for(int j=0;j<L;j++) {
				switch (this.board.getBoard()[i][j].getState()) {
				case Neutral:
					gc.setFill(Color.LIGHTGRAY);
					break;

				case Red:
					gc.setFill(Color.RED);
					break;

				case Yellow:
					gc.setFill(Color.YELLOW);
					break;
				}
				gc.fillOval(i*distanceWidth+padding,j*distanceHeight+5,size, size);

			}
		}
	}
}
