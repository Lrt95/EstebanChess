package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class ChessModel implements IChess {

    private static ChessModel instance;
    private Board chessBoard = new Board();
    private LostPiece lostPiece;
    //private ArrayList<LastMove> lastMovesList;
    private boolean isTestPositionsPossible;
    private LastMove lastMove;
    private static Timer timer = new Timer();
    private ArrayList<LastMove> lastMoveslist = new ArrayList<LastMove>();


    private ChessModel(){
    }

    public static IChess getInstance() {
        if(instance==null){
            instance = new ChessModel();
        }
        return instance;
    }

    @Override
    public void reinit() {

        chessBoard = new Board();
        lostPiece = new LostPiece();
        this.lastMove=null;
    }

    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if(chessBoard.getPieces(p)==null){
            throw new EmptyCellException();
        }
        else {
            return chessBoard.getPieces(p).getPieceType();
        }
    }

    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if(chessBoard.getPieces(p)==null){
            throw new EmptyCellException();
        }
        else {
            return chessBoard.getPieces(p).getPieceColor();
        }
    }

    @Override
    public int getNbRemainingPieces(ChessColor color) {

        int nbrPiecesColor=0;
        ChessPosition p = new ChessPosition();
        for(int i=0; i<BOARD_HEIGHT ;i++ ){
            for(int j =0; j<BOARD_WIDTH; j++){
                p.y = i; p.x = j;
                try{
                    if(chessBoard.getPieces(p) != null) {
                        if (chessBoard.getPieces(p).getPieceColor()==color){
                            nbrPiecesColor ++;
                        }
                    }
                }
                catch (NullPointerException | OutOfBoardException e){
                    //e.printStackTrace();
                }
            }
        }

        return nbrPiecesColor;
    }

    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {

        List<ChessPosition> positionPossible;
        List<ChessPosition> positionPossibleSafe = new ArrayList<>();
        isTestPositionsPossible = false;

        try {
            positionPossible = chessBoard.getPieces(p).getMove().getPieceMoves(p,chessBoard);
            ChessColor color = chessBoard.getPieces(p).getPieceColor();
            for (ChessPosition position : positionPossible){
                isTestPositionsPossible = true;
                movePiece(p, position);
                if (getKingState(color) == ChessKingState.KING_SAFE ){
                    positionPossibleSafe.add(position);
                }
                undoLastMove();
            }
            isTestPositionsPossible = false;

        } catch (OutOfBoardException | NullPointerException e) {
            e.printStackTrace();
        }
        return positionPossibleSafe;
    }


    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        Piece removePiece;

        try {

            if (this.chessBoard.getPieces(p0).getPieceType() == ChessType.TYP_KING && (this.chessBoard.getPieces(p0).getCounterMove() == 0 && this.chessBoard.getPieces(new ChessPosition(0, p0.y)).getCounterMove() == 0 ||  this.chessBoard.getPieces(p0).getCounterMove() == 0 && this.chessBoard.getPieces(new ChessPosition(7, p0.y)).getCounterMove() == 0)  && (p1.x == 1 || p1.x == 6)) {
                if (p1.x == 1) {
                    this.lastMoveslist.add( new LastMove(chessBoard.getPieces(p0), p0, p1, chessBoard.getPieces(new ChessPosition(0, p0.y)), (new ChessPosition(0, p0.y)), new ChessPosition(2, p0.y)));
                    this.chessBoard.getPieces(p0).setCounterMove(1);
                    this.chessBoard.setPiece(this.chessBoard.getPieces(p0), p1);
                    this.chessBoard.setPiece(null, p0);
                    this.chessBoard.setPiece(this.chessBoard.getPieces((new ChessPosition(0, p0.y))), (new ChessPosition(2, p0.y)));
                    this.chessBoard.setPiece(null, (new ChessPosition(0, p0.y)));
                } else if (p1.x == 6) {
                    this.lastMoveslist.add(new LastMove(chessBoard.getPieces(p0), p0, p1, chessBoard.getPieces(new ChessPosition(7, p0.y)), new ChessPosition(7, p0.y), (new ChessPosition(5, p0.y))));
                    this.chessBoard.getPieces(p0).setCounterMove(1);
                    this.chessBoard.setPiece(this.chessBoard.getPieces(p0), p1);
                    this.chessBoard.setPiece(null, p0);
                    this.chessBoard.setPiece(this.chessBoard.getPieces((new ChessPosition(7, p0.y))), (new ChessPosition(5, p0.y)));
                    this.chessBoard.setPiece(null, (new ChessPosition(7, p0.y)));
                }
            } else {

                LastMove mouvement = new LastMove(chessBoard.getPieces(p0), p0, p1, chessBoard.getPieces(p1), p1, null);
                removePiece = this.chessBoard.getPieces(p1);
                this.chessBoard.getPieces(p0).setCounterMove(1);
                this.chessBoard.setPiece(this.chessBoard.getPieces(p0), p1);
                this.chessBoard.setPiece(null, p0);
                timer.newTour(chessBoard.getPieces(p1).getPieceColor());
                if (removePiece != null && !isTestPositionsPossible) {
                    lostPiece.addType(removePiece.getPieceType(), removePiece.getPieceColor());
                    mouvement.setPieceEaten(true);
                }
                if (p1.y == 0) {
                    if (this.chessBoard.getPieces(p1).getPieceType() == ChessType.TYP_PAWN && this.chessBoard.getPieces(p1).getPieceColor() == ChessColor.CLR_WHITE) {
                        this.chessBoard.setPiece(new Piece(ChessColor.CLR_WHITE, ChessType.TYP_QUEEN), p1);
                    }
                } else if (p1.y == 7) {
                    if (this.chessBoard.getPieces(p1).getPieceType() == ChessType.TYP_PAWN && this.chessBoard.getPieces(p1).getPieceColor() == ChessColor.CLR_BLACK) {
                        this.chessBoard.setPiece(new Piece(ChessColor.CLR_BLACK, ChessType.TYP_QUEEN), p1);
                    }
                }
                this.lastMoveslist.add(mouvement);
            }
        } catch (OutOfBoardException | NullPointerException e) {
            //e.printStackTrace();
        }
    }

    @Override
    public ChessKingState getKingState(ChessColor color) {

        ChessPosition kingPosition;

        kingPosition = this.chessBoard.getKingPosition(color);

        for(int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                ChessPosition p = new ChessPosition(j,i);
                try {
                    if(chessBoard.getPieces(p) != null) {
                        if (chessBoard.getPieces(p).getPieceColor() != color) { // if is ennemy
                            List<ChessPosition> moves = chessBoard.getPieces(p).getMove().getPieceMoves(p, chessBoard);
                            for (ChessPosition move : moves) {
                                if (move.x == kingPosition.x && move.y == kingPosition.y) {
                                    return ChessKingState.KING_THREATEN;
                                }
                            }
                        }
                    }
                } catch (OutOfBoardException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        return ChessKingState.KING_SAFE;
    }

    @Override
    public List<ChessType> getRemovedPieces(ChessColor color){
        return lostPiece.getList(color);

    }

    @Override
    public boolean undoLastMove() {

        if (this.lastMoveslist != null ){
            System.out.println(this.lastMoveslist.size());
            if(this.lastMoveslist.size()>0){

                LastMove lastOne = this.lastMoveslist.get(this.lastMoveslist.size()-1);


                chessBoard.setPiece(lastOne.getPiece1(), lastOne.getPosition1P1());
                chessBoard.setPiece(lastOne.getPiece2(), lastOne.getPosition1P2());
                try {
                    this.chessBoard.getPieces(lastOne.getPosition1P1()).setCounterMove(-1);
                } catch (OutOfBoardException | NullPointerException e) {
                    e.printStackTrace();
                }

                if(lastOne != null) {
                    if(lastOne.isPieceEaten()){
                        if(lastOne.getPiece2().getPieceColor() == ChessColor.CLR_WHITE){
                            lostPiece.getList(ChessColor.CLR_WHITE).remove(lostPiece.getList(ChessColor.CLR_WHITE).size()-1);
                        }
                        else {
                            lostPiece.getList(ChessColor.CLR_BLACK).remove(lostPiece.getList(ChessColor.CLR_BLACK).size()-1);
                        }
                    }
                }
                this.lastMoveslist.remove(lastMoveslist.size()-1);

                return true;

            }

        }

        return false;
    }

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return timer.timerFor(color, isPlaying);
    }
}
