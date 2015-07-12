
package tictactoe.player;

import com.google.common.base.Predicate;
import tictactoe.Symbol;
import tictactoe.grid.Cell;
import tictactoe.grid.Grid;
import tictactoe.grid.Row;
import tictactoe.grid.status.GameStatus;
import tictactoe.prompt.Prompt;

import java.util.List;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;

public class AutomatedPlayer implements Player {
    private static final int MAX_PLAYER_WINNING_SCORE = 10;
    private static final int MIN_PLAYER_WINNING_SCORE = -10;
    private static final int DRAW_SCORE = 0;

    private final Symbol symbol;
    private final Prompt prompt;

    public AutomatedPlayer(Symbol symbol, Prompt prompt) {
        this.symbol = symbol;
        this.prompt = prompt;
    }

    @Override
    public int nextMoveOn(Grid grid) {
        boolean isMaxPlayer = true;
        Score move = minimax(grid,
                getNumberOfVacantCells(grid.rows()),
                isMaxPlayer);

        return move.getPosition();
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }

    private Score minimax(Grid grid, int depth, boolean isMaxPlayer) {
        List<Score> scores = newArrayList();

        GameStatus gameStatus = grid.evaluateWinningStatus();
        if (isZero(depth) || gameStatus.hasWinner()) {
            return new Score(calculateScore(grid, depth));
        }

        for (Cell possibleMove : getVacantCells(grid.rows())) {
            grid.update(possibleMove.getOffset(), isMaxPlayer ? symbol : opponent());
            Score value = minimax(grid, depth - 1, isMaxPlayer ? false : true);
            grid.update(possibleMove.getOffset(), VACANT);

            scores.add(new Score(possibleMove.getOffset(), value.getScore()));
        }

        return calculateBestMoveFrom(scores, isMaxPlayer);
    }

    private int getNumberOfVacantCells(List<Row> rows) {
        return getVacantCells(rows).size();
    }

    private List<Cell> getVacantCells(List<Row> rows) {
        List<Cell> allVacantCells = newArrayList();
        for (Row row : rows) {
            allVacantCells.addAll(
                    newArrayList(filter(row.getCells(), cell -> cell.getSymbol() == VACANT)));
        }
        return allVacantCells;
    }

    private int calculateScore(Grid grid, int depth) {
        GameStatus gameStatus = grid.evaluateWinningStatus();

        if (gameStatus.hasWinner()) {
            return gameStatus.winningSymbol() == symbol
                    ? MAX_PLAYER_WINNING_SCORE + depth
                    : MIN_PLAYER_WINNING_SCORE - depth;
        }
        return DRAW_SCORE;
    }

    private Symbol opponent() {
        return symbol.equals(X) ? O : X;
    }

    private boolean isZero(int depth) {
        return depth == 0;
    }

    private Score calculateBestMoveFrom(List<Score> scores, boolean isMaxPlayer) {
        return isMaxPlayer ? max(scores) : min(scores);
    }
    
    private Score max(List<Score> scores) {
        int maxScore = -100;
        int bestMoveForMaxPlayer = -1;
        for (Score score : scores) {
            if (score.getScore() > maxScore) {
                maxScore = score.getScore();
                bestMoveForMaxPlayer = score.getPosition();
            }
        }
        return new Score(bestMoveForMaxPlayer, maxScore);
    }

    private Score min(List<Score> scores) {
        int minScore = 100;
        int bestMoveForMinPlayer = -1;

        for (Score score : scores) {
            if (score.getScore() < minScore) {
                minScore = score.getScore();
                bestMoveForMinPlayer = score.getPosition();
            }
        }
        return new Score(bestMoveForMinPlayer, minScore);
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
