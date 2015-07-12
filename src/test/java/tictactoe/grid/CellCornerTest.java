package tictactoe.grid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.X;
import static tictactoe.grid.Grid.BOTTOM_ROW_OFFSET;
import static tictactoe.grid.Grid.NUMBER_OF_CELLS_IN_ROW;
import static tictactoe.grid.Grid.TOTAL_CELLS;

@RunWith(Parameterized.class)
public class CellCornerTest {
    private final int offset;
    private final boolean isCorner;

    public CellCornerTest(int offset, boolean isCorner) {
        this.offset = offset;
        this.isCorner = isCorner;
    }

    @Parameterized.Parameters
    public static Collection dataSetup() {
        return Arrays.asList(new Object[][]{
                {
                        0,
                        true
                },
                {
                        NUMBER_OF_CELLS_IN_ROW - 1,
                        true
                },
                {
                        BOTTOM_ROW_OFFSET,
                        true

                },
                {
                        TOTAL_CELLS - 1,
                        true
                },
                {
                        NUMBER_OF_CELLS_IN_ROW,
                        false
                }
        });
    }

    @Test
    public void identifiesAValidCorner() {
        Cell cornerCell = new Cell(X, offset);
        assertThat(cornerCell.isCorner(), is(equalTo(isCorner)));
    }


}