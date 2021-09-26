package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Board;
import Model.Token;
import Model.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class MainController implements Initializable{

	@FXML
	Canvas canvas;

	@FXML
	Pane pane;

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
		canvas.setOnMouseClicked(e->{

		});

		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
			final double MOUSEPOSITIONX = e.getX();
			boolean placeTokenOk = true;

			if(MOUSEPOSITIONX>0 && MOUSEPOSITIONX<distanceWidth+padding) {
				placeTokenOk =board.placeToken(0, turn);
				System.out.println(placeTokenOk);
				System.out.println("Colonne 1");
			}
			if(MOUSEPOSITIONX>distanceWidth+padding && MOUSEPOSITIONX<2*(distanceWidth+padding)) {
				placeTokenOk =board.placeToken(1, turn);
				System.out.println("Colonne 2");
			}
			if(MOUSEPOSITIONX>2*(distanceWidth+padding) && MOUSEPOSITIONX<3*(distanceWidth+padding)) {
				placeTokenOk =board.placeToken(2, turn);
				System.out.println("Colonne 3");
			}

			if(MOUSEPOSITIONX>3*(distanceWidth+padding) && MOUSEPOSITIONX<4*(distanceWidth+padding)) {
				placeTokenOk =board.placeToken(3, turn);
				System.out.println("Colonne 4");
			}

			if(MOUSEPOSITIONX>4*(distanceWidth+padding) && MOUSEPOSITIONX<5*(distanceWidth+padding)) {
				placeTokenOk =board.placeToken(4, turn);
				System.out.println("Colonne 5");
			}

			if(MOUSEPOSITIONX>5*(distanceWidth+padding) && MOUSEPOSITIONX<6*(distanceWidth+padding)) {
				placeTokenOk =board.placeToken(5, turn);
				System.out.println("Colonne 6");
			}

			if(MOUSEPOSITIONX>6*(distanceWidth+padding) && MOUSEPOSITIONX<7*(distanceWidth+padding)) {
				placeTokenOk =board.placeToken(6, turn);
				System.out.println("Colonne 7");
			}

			if(placeTokenOk) {
				if(turn.equals(Color.RED)) {
					turn=Color.YELLOW;
				}else if(turn.equals(Color.YELLOW)) {
					turn=Color.RED;
				}else {
					
				}
				
			}
			//System.out.println(this.board);
			this.draw();
		});




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
