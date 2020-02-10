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

        int nbrPiecesColor=0;

        for(int i=0; i<BOARD_HEIGHT ;i++ ){
            for(int j =0; j<BOARD_WIDTH; j++){
                try{
                if (chessBoard.getChessBoard()[i][j].getPieceColor()==color){
                    nbrPiecesColor ++;
                }}
                catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        }

        return nbrPiecesColor;
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
