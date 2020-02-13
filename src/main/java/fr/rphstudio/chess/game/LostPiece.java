package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class LostPiece {

    private List<IChess.ChessType> lostPiecesW;
    private List<IChess.ChessType> lostPiecesB;

    /**
     * List of all black and white pieces lost
     */

    public LostPiece() {
        lostPiecesB = new ArrayList<>();
        lostPiecesW = new ArrayList<>();
    }

    /**
     * Add the pieces lost during game
     * @param chessType the type of the pieces
     * @param chessColor the color of the pieces
     */

    public void addType(IChess.ChessType chessType, IChess.ChessColor chessColor) {
        if (chessColor == IChess.ChessColor.CLR_WHITE) {
            lostPiecesW.add(chessType);
        }
        else {
            lostPiecesB.add(chessType);
        }
    }

    /**
     * Get the lost pieces lists
     * @param chessColor the color of the pieces
     * @return the list of lost pieces depending on the color
     */
    public List<IChess.ChessType> getList(IChess.ChessColor chessColor) {
        if (chessColor == IChess.ChessColor.CLR_WHITE) {
           return lostPiecesW;
        }
        else {
            return lostPiecesB;
        }
    }

}
