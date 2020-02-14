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
    private boolean isTestPositionsPossible;
    private Timer timer = new Timer();
    private ArrayList<LastMove> lastMoveslist = new ArrayList<LastMove>();


    private ChessModel(){
    }

    /**
     * Instance a new Chess
     * @return instance of chess model
     */

    public static IChess getInstance() {
        if(instance==null){
            instance = new ChessModel();
        }
        return instance;
    }

    /**
     * Restart the game
     */

    @Override
    public void reinit() {

        chessBoard = new Board();
        lostPiece = new LostPiece();
        this.lastMoveslist = new ArrayList<LastMove>();
        this.timer = new Timer();


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
                    if(chessBoard.getPieces(p) != null) {
                        if (chessBoard.getPieces(p).getPieceColor()==color){
                            nbrPiecesColor ++;
                        }
                    }
                }
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

    /**
     * Moves the piece
     * @param p0 source position on the board.
     * @param p1 destination position on the board.
     */
    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        LastMove mouvement;
        Piece removePiece;
        try {
            if (this.chessBoard.getPieces(p0).getPieceType() == ChessType.TYP_KING &&
                    this.chessBoard.getPieces(new ChessPosition(0, p0.y)) != null
                            && this.chessBoard.getPieces(p0).getCounterMove() == 0 &&
                            this.chessBoard.getPieces(new ChessPosition(0, p0.y)).getCounterMove() == 0 &&
                            (p1.x == 1 || p1.x == 6)
                            ||  this.chessBoard.getPieces(p0).getPieceType() == ChessType.TYP_KING &&
                    this.chessBoard.getPieces(new ChessPosition(7, p0.y)) != null
                            && this.chessBoard.getPieces(p0).getCounterMove() == 0 &&
                            this.chessBoard.getPieces(new ChessPosition(7, p0.y)).getCounterMove() == 0  &&
                    (p1.x == 1 || p1.x == 6)) {

                if (p1.x == 1) {
                    mouvement = new LastMove(chessBoard.getPieces(p0),
                            p0,
                            p1,
                            chessBoard.getPieces(new ChessPosition(0, p0.y)),
                            new ChessPosition(0, p0.y),
                            new ChessPosition(2, p0.y),
                            this.timer.getTimerBlack(),
                            this.timer.getTimerWhite(),true);
                    this.chessBoard.getPieces(p0).setCounterMove(1);
                    this.chessBoard.getPieces(new ChessPosition(0, p0.y)).setCounterMove(1);
                    this.chessBoard.setPiece(this.chessBoard.getPieces(p0), p1);
                    this.chessBoard.setPiece(null, p0);
                    this.chessBoard.setPiece(this.chessBoard.getPieces((new ChessPosition(0, p0.y))),
                            (new ChessPosition(2, p0.y)));
                    this.chessBoard.setPiece(null, (new ChessPosition(0, p0.y)));
                    this.lastMoveslist.add(mouvement);
                } else if (p1.x == 6) {
                    mouvement = new LastMove(chessBoard.getPieces(p0),
                            p0,
                            p1,
                            chessBoard.getPieces(new ChessPosition(7, p0.y)),
                            new ChessPosition(7, p0.y),
                            new ChessPosition(5, p0.y),
                            this.timer.getTimerBlack(),
                            this.timer.getTimerWhite(), true);
                    this.chessBoard.getPieces(p0).setCounterMove(1);
                    this.chessBoard.getPieces(new ChessPosition(7, p0.y)).setCounterMove(1);
                    this.chessBoard.setPiece(this.chessBoard.getPieces(p0), p1);
                    this.chessBoard.setPiece(null, p0);
                    this.chessBoard.setPiece(this.chessBoard.getPieces((new ChessPosition(7, p0.y)))
                            , (new ChessPosition(5, p0.y)));
                    this.chessBoard.setPiece(null, (new ChessPosition(7, p0.y)));
                    this.lastMoveslist.add(mouvement);
                }
            } else {
                timer.newTour(chessBoard.getPieces(p0).getPieceColor());
                mouvement = new LastMove(chessBoard.getPieces(p0),
                        p0,
                        p1,
                        chessBoard.getPieces(p1),
                        p1,
                        null,
                        this.timer.getTimerBlack(),
                        this.timer.getTimerWhite(), false);
                removePiece = this.chessBoard.getPieces(p1);
                this.chessBoard.getPieces(p0).setCounterMove(1);
                this.chessBoard.setPiece(this.chessBoard.getPieces(p0), p1);
                this.chessBoard.setPiece(null, p0);
                if (removePiece != null && !isTestPositionsPossible) {
                    lostPiece.addType(removePiece.getPieceType(), removePiece.getPieceColor());
                    mouvement.setPieceEaten(true);
                }
                if (p1.y == 0) {
                    if (this.chessBoard.getPieces(p1).getPieceType() == ChessType.TYP_PAWN &&
                            this.chessBoard.getPieces(p1).getPieceColor() == ChessColor.CLR_WHITE) {
                        this.chessBoard.setPiece(new Piece(ChessColor.CLR_WHITE, ChessType.TYP_QUEEN), p1);
                    }
                } else if (p1.y == 7) {
                    if (this.chessBoard.getPieces(p1).getPieceType() == ChessType.TYP_PAWN &&
                            this.chessBoard.getPieces(p1).getPieceColor() == ChessColor.CLR_BLACK) {
                        this.chessBoard.setPiece(new Piece(ChessColor.CLR_BLACK, ChessType.TYP_QUEEN), p1);
                    }
                }
                this.lastMoveslist.add(mouvement);
            }
        } catch (OutOfBoardException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the state of king
     * @param color the requested king color.
     * @return the state of king
     */

    @Override
    public ChessKingState getKingState(ChessColor color) {

        List<ChessPosition> moves;
        ChessPosition kingPosition;

        kingPosition = this.chessBoard.getKingPosition(color);

        for(int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                ChessPosition p = new ChessPosition(j,i);
                try {
                    if(chessBoard.getPieces(p) != null) {
                        if (chessBoard.getPieces(p).getPieceColor() != color) {
                            moves = chessBoard.getPieces(p).getMove().getPieceMoves(p, chessBoard);
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
        LastMove lastOne;

        if (this.lastMoveslist != null ){

            if(this.lastMoveslist.size()>0){


                lastOne = this.lastMoveslist.get(this.lastMoveslist.size()-1);
                try {
                    chessBoard.setPiece(lastOne.getPiece1(), lastOne.getPosition1P1());
                    chessBoard.setPiece(lastOne.getPiece2(), lastOne.getPosition1P2());
                    if (lastOne.isSpecialMove()) {
                        chessBoard.setPiece(null, lastOne.getPosition2P1());
                        chessBoard.setPiece(null, lastOne.getPosition2P2());
                        this.chessBoard.getPieces(lastOne.getPosition1P2()).setCounterMove(-1);
                    }
                    this.chessBoard.getPieces(lastOne.getPosition1P1()).setCounterMove(-1);
                    this.timer.setTimerBlack(lastOne.getTimeBlack());
                    this.timer.setTimerWhite(lastOne.getTimeWhite());
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

    /**
     * Timer for players
     * @param color The color of the player from whom we want to get the current duration.
     * @param isPlaying Indicates if the player color is the one currently playing.
     * @return
     */

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return this.timer.timerFor(color, isPlaying);
    }
}
