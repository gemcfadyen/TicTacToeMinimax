package tictactoe.grid;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
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
    public void putsSymbolAtGivenIndex() {
        Row row = aRowBuilder().withHorizontalRow(VACANT, O, O, NO_OFFSET).build();
        row.putSymbolAt(0, O);

        assertThat(row.getSymbolAt(0), is(O));
    }

    @Test
    public void resetsRowToEmpty() {
        Row row = aRowBuilder().withHorizontalRow(VACANT, O, O, NO_OFFSET).build();

        row.reset();

        assertThat(row.getSymbolAt(1), is(VACANT));
        assertThat(row.getSymbolAt(2), is(VACANT));
    }
}