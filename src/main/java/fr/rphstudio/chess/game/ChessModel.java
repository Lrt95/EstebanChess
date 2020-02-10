package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ChessModel implements IChess {

    private static ChessModel instance;
    private static Board chessBoard = new Board();

    private ChessModel(){
    }

    public static IChess getInstance() {
        if(ChessModel.instance==null){
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
        if(chessBoard.getChessBoard()[p.y][p.x]==null){
            throw new EmptyCellException();
        }
        else {
            return chessBoard.getChessBoard()[p.y][p.x].getPieceType();
        }
    }

    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if(chessBoard.getChessBoard()[p.y][p.x]==null){
            throw new EmptyCellException();
        }
        else {
            return chessBoard.getChessBoard()[p.y][p.x].getPieceColor();
        }
    }

    @Override
    public int getNbRemainingPieces(ChessColor color) {
        return 0;
    }

    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        return new ArrayList<>();
    }

    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {

    }

    @Override
    public ChessKingState getKingState(ChessColor color) {
        return ChessKingState.KING_SAFE;
    }

    @Override
    public List<ChessType> getRemovedPieces(ChessColor color){
        return new ArrayList<>();
    }

    @Override
    public boolean undoLastMove() {
        return false;
    }

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return 0;
    }
}
