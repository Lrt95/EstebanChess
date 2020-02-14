package fr.rphstudio.chess.game.moves;

import fr.rphstudio.chess.game.Board;
import fr.rphstudio.chess.interf.IChess;

import java.util.List;

public interface IMove {

    /**
     * Interface for mouvement piece
     * @param position
     * @param board
     * @return
     */
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition position, Board board);


}
