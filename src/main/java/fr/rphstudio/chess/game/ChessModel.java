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
    private static LastMove lastMove;

    private ChessModel(){
    }

    /**
     * Instance a new Chess
     * @return instance of chess model
     */

    public static IChess getInstance() {
        if(ChessModel.instance==null){
            ChessModel.instance = new ChessModel();
        }
        return ChessModel.instance;
    }

    /**
     * Restart the game
     */

    @Override
    public void reinit() {

        chessBoard = new Board();
        lostPiece = new LostPiece();
        lastMove = null;
    }

    /**
     * Get the piece type on the chessboard
     * @param p x/y position on the board where we want to get the piece type.
     * @return the type piece on the chessboard
     * @throws EmptyCellException when the cell is empty
     * @throws OutOfBoardException when no pieces on the board
     */

    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if(chessBoard.getPieces(p)==null){
            throw new EmptyCellException();
        }
        else {
            return chessBoard.getPieces(p).getPieceType();
        }
    }

    /**
     * Get the piece color on the chessboard
     * @param p x/y position on the board where we want to get the piece color.
     * @return the color piece on the chessboard
     * @throws EmptyCellException when the cell is empty
     * @throws OutOfBoardException when no pieces on the board
     */

    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if(chessBoard.getPieces(p)==null){
            throw new EmptyCellException();
        }
        else {
            return chessBoard.getPieces(p).getPieceColor();
        }
    }

    /**
     * Get the number of remaining pieces
     * @param color the requested color of the pieces to count.
     * @return the number of piece color
     */

    @Override
    public int getNbRemainingPieces(ChessColor color) {

        int nbrPiecesColor=0;
        ChessPosition p = new ChessPosition();
        for(int i=0; i<BOARD_HEIGHT ;i++ ){
            for(int j =0; j<BOARD_WIDTH; j++){
                p.y = i; p.x = j;
                try{
                if (chessBoard.getPieces(p).getPieceColor()==color){
                    nbrPiecesColor ++;
                }}
                catch (NullPointerException | OutOfBoardException e){
                    e.printStackTrace();
                }
            }
        }

        return nbrPiecesColor;
    }

    /**
     * List of all possibles positions for a piece
     * @param p requested piece position.
     * @return the list of all possibles position
     */

    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {

        try {
            List<ChessPosition> positionPossible = chessBoard.getPieces(p).getMove().getPieceMoves(p,chessBoard);
            List<ChessPosition> positionPossibleSafe = new ArrayList<>();
            ChessColor color = chessBoard.getPieces(p).getPieceColor();


            for (ChessPosition position : positionPossible){
                movePiece(p, position);
                if (getKingStateForMove(color) == ChessKingState.KING_SAFE ){
                    positionPossibleSafe.add(position);
                }
                undoLastMove();
            }
            return positionPossibleSafe;

        } catch (OutOfBoardException | NullPointerException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Get the moves of the piece when the king state is check
     * @param p the king position
     * @return the list of possible positions
     */

    public List<ChessPosition> getPieceMovesCheckKingState(ChessPosition p) {

        try {
            List<ChessPosition> positionPossible = chessBoard.getPieces(p).getMove().getPieceMoves(p,chessBoard);

            return positionPossible;

        } catch (OutOfBoardException | NullPointerException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Moves the piece
     * @param p0 source position on the board.
     * @param p1 destination position on the board.
     */

    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {

        try {
            if (this.chessBoard.getPieces(p0).getPieceType() == ChessType.TYP_KING && (this.chessBoard.getPieces(p0).isFirstMove() && this.chessBoard.getPieces(new ChessPosition(0, p0.y)).isFirstMove() ||  this.chessBoard.getPieces(p0).isFirstMove() && this.chessBoard.getPieces(new ChessPosition(7, p0.y)).isFirstMove())  && (p1.x == 1 || p1.x == 6)) {
               if (p1.x == 1) {
                    this.chessBoard.getPieces(p0).setFirstMoveFalse();
                    this.chessBoard.setPiece(this.chessBoard.getPieces(p0), p1);
                    this.chessBoard.setPiece(null, p0);
                    this.chessBoard.setPiece(this.chessBoard.getPieces((new ChessPosition(0, p0.y))), (new ChessPosition(2, p0.y)));
                    this.chessBoard.setPiece(null, (new ChessPosition(0, p0.y)));
                } else if (p1.x == 6) {
                    this.chessBoard.getPieces(p0).setFirstMoveFalse();
                    this.chessBoard.setPiece(this.chessBoard.getPieces(p0), p1);
                    this.chessBoard.setPiece(null, p0);
                    this.chessBoard.setPiece(this.chessBoard.getPieces((new ChessPosition(7, p0.y))), (new ChessPosition(5, p0.y)));
                    this.chessBoard.setPiece(null, (new ChessPosition(7, p0.y)));
                }
            } else {
                lastMove = new LastMove(p0, p1, chessBoard.getPieces(p0), chessBoard.getPieces(p1)) ;
                Piece removePiece = this.chessBoard.getPieces(p1);
                this.chessBoard.getPieces(p0).setFirstMoveFalse();
                this.chessBoard.setPiece(this.chessBoard.getPieces(p0), p1);
                this.chessBoard.setPiece(null, p0);
                if (removePiece != null ) {
                    lostPiece.addType(removePiece.getPieceType(), removePiece.getPieceColor());
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
            }
        } catch (OutOfBoardException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the state of king
     * @param color the requested king color.
     * @return
     */

    @Override
    public ChessKingState getKingState(ChessColor color) {

        List<IChess.ChessPosition> warningPos = new ArrayList();
        ChessPosition kingPosition = new ChessPosition();

        for(int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                ChessPosition p = new ChessPosition(j,i);
                try {
                    if(chessBoard.getPieces(p).getPieceColor() != color){
                        warningPos.addAll(getPieceMoves(p));
                    }
                    else if(chessBoard.getPieces(p).getPieceType() == ChessType.TYP_KING){
                        kingPosition=p;
                    }
                } catch (OutOfBoardException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        for (ChessPosition pos: warningPos ) {
            if(pos.x==kingPosition.x && pos.y==kingPosition.y){
                return ChessKingState.KING_THREATEN;
            }
        }
        return ChessKingState.KING_SAFE;
    }

    /**
     * Get the state of king for move
     * @param color
     * @return
     */

    public ChessKingState getKingStateForMove(ChessColor color) {

        List<IChess.ChessPosition> warningPos = new ArrayList();
        ChessPosition kingPosition = new ChessPosition();

        for(int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                ChessPosition p = new ChessPosition(j,i);
                try {
                    if(chessBoard.getPieces(p).getPieceColor() != color){
                        warningPos.addAll(getPieceMovesCheckKingState(p));
                    }
                    else if(chessBoard.getPieces(p).getPieceType() == ChessType.TYP_KING){
                        kingPosition=p;
                    }
                } catch (OutOfBoardException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        for (ChessPosition pos: warningPos ) {
            if(pos.x==kingPosition.x && pos.y==kingPosition.y){
                return ChessKingState.KING_THREATEN;
            }
        }
        return ChessKingState.KING_SAFE;
    }

    /**
     * Get all pieces lost during the game
     * @param color color of the removed pieces
     * @return the list of pieces lost by color
     */

    @Override
    public List<ChessType> getRemovedPieces(ChessColor color){
        return lostPiece.getList(color);

    }

    /**
     * Undo the last move
     * @return boolean
     */

    @Override
    public boolean undoLastMove() {

        if (this.lastMove!=null){
            chessBoard.setPiece(this.lastMove.getPiece1(), this.lastMove.getPosition1());
            chessBoard.setPiece(this.lastMove.getPiece2(), this.lastMove.getPosition2());
            lastMove = null ;
            return true;
        }
        return false;
    }

    /**
     * Timer for players
     * @param color The color of the player from whom we want to get the current duration.
     * @param isPlaying Indicates if the player color is the one currently playing.
     * @return
     */

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {

        return 0;
    }
}
