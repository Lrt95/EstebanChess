package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class LostPiece {

    private List<IChess.ChessType> lostPiecesW;
    private List<IChess.ChessType> lostPiecesB;

    public LostPiece() {
        lostPiecesB = new ArrayList<>();
        lostPiecesW = new ArrayList<>();
    }

    public void addType(IChess.ChessType chessType, IChess.ChessColor chessColor) {
        if (chessColor == IChess.ChessColor.CLR_WHITE) {
            lostPiecesW.add(chessType);
        }
        else {
            lostPiecesB.add(chessType);
        }
    }

    public List<IChess.ChessType> getList(IChess.ChessColor chessColor) {
        if (chessColor == IChess.ChessColor.CLR_WHITE) {
           return lostPiecesW;
        }
        else {
            return lostPiecesB;
        }
    }

}
