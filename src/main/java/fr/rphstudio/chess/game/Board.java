package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

public class Board {

    private Piece[][] chessBoard = new Piece[8][8];

    /**
     * Initiates the chessboard
     */
    public Board() {

        for (int i=0; i<8; i++){
            chessBoard[IChess.BOARD_POS_Y_WHITE_PAWNS][i]=(new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_PAWN));
            chessBoard[IChess.BOARD_POS_Y_BLACK_PAWNS][i]=(new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_PAWN));
        }

        chessBoard[IChess.BOARD_POS_Y_WHITE_PIECES][IChess.BOARD_POS_X_KING]=(new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KING));
        chessBoard[IChess.BOARD_POS_Y_WHITE_PIECES][IChess.BOARD_POS_X_KINGSIDE_BISHOP]=(new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_BISHOP));
        chessBoard[IChess.BOARD_POS_Y_WHITE_PIECES][IChess.BOARD_POS_X_KINGSIDE_KNIGHT]=(new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KNIGHT));
        chessBoard[IChess.BOARD_POS_Y_WHITE_PIECES][IChess.BOARD_POS_X_KINGSIDE_ROOK]=(new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_ROOK));
        chessBoard[IChess.BOARD_POS_Y_WHITE_PIECES][IChess.BOARD_POS_X_QUEEN]=(new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_QUEEN));
        chessBoard[IChess.BOARD_POS_Y_WHITE_PIECES][IChess.BOARD_POS_X_QUEENSIDE_BISHOP]=(new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_BISHOP));
        chessBoard[IChess.BOARD_POS_Y_WHITE_PIECES][IChess.BOARD_POS_X_QUEENSIDE_KNIGHT]=(new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_KNIGHT));
        chessBoard[IChess.BOARD_POS_Y_WHITE_PIECES][IChess.BOARD_POS_X_QUEENSIDE_ROOK]=(new Piece(IChess.ChessColor.CLR_WHITE, IChess.ChessType.TYP_ROOK));

        chessBoard[IChess.BOARD_POS_Y_BLACK_PIECES][IChess.BOARD_POS_X_KING]=(new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_KING));
        chessBoard[IChess.BOARD_POS_Y_BLACK_PIECES][IChess.BOARD_POS_X_KINGSIDE_BISHOP]=(new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_BISHOP));
        chessBoard[IChess.BOARD_POS_Y_BLACK_PIECES][IChess.BOARD_POS_X_KINGSIDE_KNIGHT]=(new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_KNIGHT));
        chessBoard[IChess.BOARD_POS_Y_BLACK_PIECES][IChess.BOARD_POS_X_KINGSIDE_ROOK]=(new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_ROOK));
        chessBoard[IChess.BOARD_POS_Y_BLACK_PIECES][IChess.BOARD_POS_X_QUEEN]=(new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_QUEEN));
        chessBoard[IChess.BOARD_POS_Y_BLACK_PIECES][IChess.BOARD_POS_X_QUEENSIDE_BISHOP]=(new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_BISHOP));
        chessBoard[IChess.BOARD_POS_Y_BLACK_PIECES][IChess.BOARD_POS_X_QUEENSIDE_KNIGHT]=(new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_KNIGHT));
        chessBoard[IChess.BOARD_POS_Y_BLACK_PIECES][IChess.BOARD_POS_X_QUEENSIDE_ROOK]=(new Piece(IChess.ChessColor.CLR_BLACK, IChess.ChessType.TYP_ROOK));
    }

    /**
     * Get the position of the piece on the chessboard
     * @param p the chess piece position
     * @return the position of the piece on the chessboard
     * @throws OutOfBoardException if the piece is not on the board
     */

    public Piece getPieces(IChess.ChessPosition p) throws OutOfBoardException {
        if (p.y<0 || p.y>7 || p.x<0 || p.x>7 ) {
            throw new OutOfBoardException();
        }
        return chessBoard[p.y][p.x];
    }

    /**
     * Get the position of King
     * @param color of king
     * @return the position of King
     */
    public IChess.ChessPosition getKingPosition(IChess.ChessColor color){
        IChess.ChessPosition kingPosition = new IChess.ChessPosition();
        for(int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (chessBoard[y][x] != null && chessBoard[y][x].getPieceType() == IChess.ChessType.TYP_KING && chessBoard[y][x].getPieceColor() == color) {
                    kingPosition.x = x;
                    kingPosition.y = y;
                }
            }
        }
            return kingPosition;
    }

    /**
     * Set pieces on the board
     * @param piece the type of piece
     * @param pos the position on the chessboard
     */
    public void setPiece(Piece piece, IChess.ChessPosition pos) {
        if(pos != null){
            if(pos.x >= 0 && pos.x <= 7 && pos.y >= 0 && pos.y <= 7 ) {
                chessBoard[pos.y][pos.x] = piece;
            }
        }
    }
}
