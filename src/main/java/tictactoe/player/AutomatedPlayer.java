package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.grid.Cell;
import tictactoe.grid.Grid;
import tictactoe.grid.status.WinStatus;
import tictactoe.prompt.Prompt;

import java.util.List;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.size;
import static com.google.common.collect.Lists.newArrayList;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static java.util.Collections.max;
import static java.util.Collections.min;
import static java.util.Comparator.comparingInt;

public class AutomatedPlayer implements Player {
    private static final int MAX_PLAYER_WINNING_SCORE = 10;
    private static final int MIN_PLAYER_WINNING_SCORE = -10;
    private static final int DRAW_SCORE = 0;

    private static final int ALPHA = -100;
    private static final int BETA = 100;

    private final Symbol symbol;
    private final Prompt prompt;

    public AutomatedPlayer(Symbol symbol, Prompt prompt) {
        this.symbol = symbol;
        this.prompt = prompt;
    }

    @Override
    public int nextMoveOn(Grid grid) {
        boolean isMaxPlayer = true;

        Score bestMove = minimax(grid,
                getNumberOfVacantCells(grid.getCells()),
                ALPHA,
                BETA,
                isMaxPlayer);

        prompt.display(symbol, bestMove.getPosition());
        return bestMove.getPosition();
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }

    //CHECKSTYLE:ParameterAssignment:OFF
    private Score minimax(Grid grid, int depth, int alpha, int beta, boolean isMaxPlayer) {
        List<Score> scores = newArrayList();

        WinStatus gameStatus = grid.winStatus();
        if (isZero(depth) || gameStatus.hasWinner()) {
            return new Score(calculateScore(gameStatus, depth));
        }

        for (Cell possibleMove : getVacantCells(grid.getCells())) {
            grid.update(possibleMove.getOffset(), isMaxPlayer ? symbol : opponent());
            Score value = minimax(grid, depth - 1, alpha, beta, !isMaxPlayer);
            revertMove(grid, possibleMove);

            scores.add(new Score(possibleMove.getOffset(), value.getScore()));
            alpha = isMaxPlayer ? Math.max(value.getScore(), alpha) : alpha;
            beta = isMaxPlayer ? beta : Math.min(value.getScore(), beta);

            if (pruningTreeBranches(alpha, beta)) {
                break;
            }
        }
        return calculateBestMoveFrom(scores, isMaxPlayer);
    }

    private int getNumberOfVacantCells(List<Cell> cells) {
        return size(getVacantCells(cells));
    }

    private boolean isZero(int depth) {
        return depth == 0;
    }

    private int calculateScore(WinStatus gameStatus, int depth) {
        if (gameStatus.hasWinner()) {
            return gameStatus.winningSymbol() == symbol
                    ? MAX_PLAYER_WINNING_SCORE + depth
                    : MIN_PLAYER_WINNING_SCORE - depth;
        }
        return DRAW_SCORE;
    }

    private Iterable<Cell> getVacantCells(List<Cell> cells) {
        return filter(cells, cell -> cell.getSymbol() == VACANT);
    }

    private Symbol opponent() {
        return symbol.equals(X) ? O : X;
    }

    private void revertMove(Grid grid, Cell possibleMove) {
        grid.update(possibleMove.getOffset(), VACANT);
    }

    private boolean pruningTreeBranches(int alpha, int beta) {
        return alpha > beta;
    }

    private Score calculateBestMoveFrom(List<Score> scores, boolean isMaxPlayer) {
        return isMaxPlayer
                ? max(scores, comparingInt(Score::getScore))
                : min(scores, comparingInt(Score::getScore));
    }
}

class Score {
    private int position;
    private int score;

    public Score(int position, int score) {
        this.position = position;
        this.score = score;
    }

    public Score(int score) {
        this.score = score;
    }

    public int getPosition() {
        return position;
    }

    public int getScore() {
        return score;
    }
}
