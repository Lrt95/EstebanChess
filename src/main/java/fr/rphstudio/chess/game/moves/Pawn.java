package fr.rphstudio.chess.game.moves;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements IMove  {

    @Override
    public List<IChess.ChessPosition> getPieceMoves() {
        return new ArrayList<>();
    }

}
