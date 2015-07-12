package tictactoe.grid;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.RowBuilder.aRowBuilder;

public class RowTest {
    private static final int NO_OFFSET = 0;

    @Test
    public void identifiesAWinningRow() {
        Row row = aRowBuilder().withHorizontalRow(O, O, O, NO_OFFSET).build();
        assertThat(row.isWinningRow(), is(true));
    }

    @Test
    public void identifiesARowIsNotAWinningRow() {
        Row row = aRowBuilder().withHorizontalRow(O, VACANT, O, NO_OFFSET).build();
        assertThat(row.isWinningRow(), is(false));
    }

    @Test
    public void identifiesARowIsAllVacant() {
        Row row = aRowBuilder().withHorizontalRow(VACANT, VACANT, VACANT, NO_OFFSET).build();
        assertThat(row.isVacant(), is(true));
    }

    @Test
    public void identifiesARowIsNotAllVacant() {
        Row row = aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NO_OFFSET).build();
        assertThat(row.isVacant(), is(false));
    }

    @Test
    public void identifiesIfPositionIsVacant() {
        Row row = aRowBuilder().withHorizontalRow(O, VACANT, O, NO_OFFSET).build();
        assertThat(row.isVacantAt(1), is(true));
    }

    @Test
    public void identifiesTheChosenPositionIsNotVacant() {
        Row row = aRowBuilder().withHorizontalRow(O, VACANT, O, NO_OFFSET).build();
        assertThat(row.isVacantAt(0), is(false));
    }

    @Test
    public void identifiesRowWithTopLeftOccupiedCornerOnly() {
        Row row = aRowBuilder().withHorizontalRow(O, VACANT, VACANT, NO_OFFSET).build();
        assertThat(row.hasOnlyCornerOccupied(O), is(true));
    }

    @Test
    public void identifiesRowWithTopRightOccupiedCornerOnly() {
        Row row = aRowBuilder().withHorizontalRow(VACANT, VACANT, O, NO_OFFSET).build();
        assertThat(row.hasOnlyCornerOccupied(O), is(true));
    }

    @Test
    public void identifiesRowWithBottomLeftOccupiedCornerOnly() {
        Row row = aRowBuilder().withHorizontalRow(O, VACANT, VACANT, BOTTOM_ROW_OFFSET).build();
        assertThat(row.hasOnlyCornerOccupied(O), is(true));
    }

    @Test
    public void identifiesRowWithBottomRightOccupiedCornerOnly() {
        Row row = aRowBuilder().withHorizontalRow(VACANT, VACANT, O, BOTTOM_ROW_OFFSET).build();
        assertThat(row.hasOnlyCornerOccupied(O), is(true));
    }

    @Test
    public void identifiesTheIndexOfAGivenSymbol() {
        Row row = aRowBuilder().withHorizontalRow(VACANT, VACANT, O, BOTTOM_ROW_OFFSET).build();
        assertThat(row.getCellOffsetOf(O), is(8));
    }

    @Test
    public void identifiesThereIsNoWinningMoveToBeMade() {
        Row row = aRowBuilder().withHorizontalRow(X, O, O, BOTTOM_ROW_OFFSET).build();
        assertNull(row.getWinningCellFor(O));
    }

    @Test
    public void identifiesTheCellToMakeAWinningMove() {
        Row row = aRowBuilder().withHorizontalRow(VACANT, O, O, NO_OFFSET).build();
        Cell winningCell = row.getWinningCellFor(O);
        assertThat(winningCell.getOffset(), is(0));
    }

    @Test
    public void putsSymbolAtGivenIndex() {
        Row row = aRowBuilder().withHorizontalRow(VACANT, O, O, NO_OFFSET).build();
        row.putSymbolAt(0, O);

        assertThat(row.getSymbolAt(0), is(O));
    }

    @Test
    public void getsIndexOfFreeCorner() {
        Row row = aRowBuilder().withHorizontalRow(VACANT, O, O, NO_OFFSET).build();
        assertThat(row.getIndexOfVacantCorner(), is(0));
    }

    @Test
    public void noFreeCornerCells() {
        Row row = aRowBuilder().withHorizontalRow(X, O, O, NO_OFFSET).build();
        assertThat(row.getIndexOfVacantCorner(), is(-1));
    }

    @Test
    public void middleOfVacantRowIsOccupiedWithSymbol() {
        Row row = aRowBuilder().withHorizontalRow(VACANT, O, VACANT, NO_OFFSET).build();
        assertThat(row.hasOnlyMiddleCellOccupied(O), is(true));
    }

    @Test
    public void middleOfVacantRowIsNotOccupiedWithSymbol() {
        Row row = aRowBuilder().withHorizontalRow(X, VACANT, VACANT, NO_OFFSET).build();
        assertThat(row.hasOnlyMiddleCellOccupied(O), is(false));
    }

    @Test
    public void resetsRowToEmpty() {
        Row row = aRowBuilder().withHorizontalRow(VACANT, O, O, NO_OFFSET).build();

        row.reset();

        assertThat(row.getSymbolAt(1), is(VACANT));
        assertThat(row.getSymbolAt(2), is(VACANT));
    }
}