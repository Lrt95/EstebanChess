package fr.rphstudio.chess.game.moves;

import fr.rphstudio.chess.game.Board;
import fr.rphstudio.chess.interf.IChess;

import java.util.List;

public interface IMove {
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition position, Board board);


}
