package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class ChessModel implements IChess {

    private static ChessModel instance;
    private static Board chessBoard = new Board();
    private static Timer timer = new Timer();


    private ChessModel() {
    }

    public static IChess getInstance() {
        if (ChessModel.instance == null) {
            ChessModel.instance = new ChessModel();
        }
        return ChessModel.instance;
    }

    @Override
    public void reinit() {

        chessBoard = new Board();

    }

    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if (chessBoard.getPieces(p) == null) {
            throw new EmptyCellException();
        } else {
            return chessBoard.getPieces(p).getPieceType();
        }
    }

    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if (chessBoard.getPieces(p) == null) {
            throw new EmptyCellException();
        } else {
            return chessBoard.getPieces(p).getPieceColor();
        }
    }

    @Override
    public int getNbRemainingPieces(ChessColor color) {

        int nbrPiecesColor = 0;
        ChessPosition p = new ChessPosition();
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                p.y = i;
                p.x = j;
                try {
                    if (chessBoard.getPieces(p).getPieceColor() == color) {
                        nbrPiecesColor++;
                    }
                } catch (NullPointerException | OutOfBoardException e) {
                    //e.printStackTrace();
                }
            }
        }

        return nbrPiecesColor;
    }

    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {

        try {
            return chessBoard.getPieces(p).getMove().getPieceMoves(p, chessBoard);
        } catch (OutOfBoardException e) {
            //e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {


        try {
            this.chessBoard.getPieces(p0).setFirstMoveFalse();
            this.chessBoard.setPiece(this.chessBoard.getPieces(p0), p1);
            this.chessBoard.setPiece(null, p0);

            timer.newTour(chessBoard.getPieces(p1).getPieceColor());

            if (p1.y == 0) {
                if (this.chessBoard.getPieces(p1).getPieceType() == ChessType.TYP_PAWN && this.chessBoard.getPieces(p1).getPieceColor() == ChessColor.CLR_WHITE) {
                    this.chessBoard.getPieces(p1).setPieceType(ChessType.TYP_QUEEN);
                }
            } else if (p1.y == 7) {
                if (this.chessBoard.getPieces(p1).getPieceType() == ChessType.TYP_PAWN && this.chessBoard.getPieces(p1).getPieceColor() == ChessColor.CLR_BLACK) {
                    this.chessBoard.getPieces(p1).setPieceType(ChessType.TYP_QUEEN);
                }
            }
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ChessKingState getKingState(ChessColor color) {
        return ChessKingState.KING_SAFE;
    }

    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        return new ArrayList<>();
    }

    @Override
    public boolean undoLastMove() {
        return false;
    }

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return timer.timerFor(color, isPlaying);
    }
}
