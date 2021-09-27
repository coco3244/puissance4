package Model;


import javafx.scene.paint.Color;

public class Board {

	private Token [] [] board;
	
	public Board(int C, int L) {
		this.board= new Token [C][L];
		
		for(int i=0;i<C;i++) {
			for(int j=0;j<L;j++) {
				board[i][j] = new Token();
			}
		}
	}

	public Token[][] getBoard() {
		return board;
	}

	
	public boolean placeToken(int C, Color color) {
		
		if(this.board[C][0].getState()!=State.Neutral) {
			return false;
		}else {
			for(int i=this.board[C].length-1;i>=0;i--) {
				if(this.board[C][i].getState()==State.Neutral) {
					if(color.equals(Color.RED)) {
						this.board[C][i].setState(State.Red);
						return true;
					}
					if(color.equals(Color.YELLOW)) {
						this.board[C][i].setState(State.Yellow);
						return true;
					}
				}
			}
					
			return false;
		}
		
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		
		for(int i=0;i<this.board.length;i++) {
			for(int j=0;j<this.board[i].length;j++) {
				res.append(this.board[i][j].toString());
			}
			res.append("\n");
		}
		
		return res.toString();
	}
	
	
	
	
	
}
