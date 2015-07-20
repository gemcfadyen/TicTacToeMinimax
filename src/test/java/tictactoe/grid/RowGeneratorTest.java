package tictactoe.grid;

import org.junit.Before;
import org.junit.Test;
import tictactoe.Symbol;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static tictactoe.Symbol.O;
import static tictactoe.Symbol.VACANT;
import static tictactoe.Symbol.X;
import static tictactoe.grid.RowGenerator.generateRowsForAllDirectionsFrom;

public class RowGeneratorTest {
    private List<List<Cell>> allRowsInGrid;

    @Before
    public void setup() {
        Grid grid = GridFactory.createGridWith(
                X, O, X,
                VACANT, O, VACANT,
                X, VACANT, O);

        allRowsInGrid = generateRowsForAllDirectionsFrom(grid.getCells());
    }

    @Test
    public void generatesTheCorrectNumberOfRowsFromGrid() {
        assertThat(allRowsInGrid.size(), is(8));
    }

    @Test
    public void generatesCorrectDiagonalRows() {
        List<List<Symbol>> rowsOfSymbols = transformToRowsOfSymbols(allRowsInGrid);

        assertThat(rowsOfSymbols.get(0), contains(X, O, X));
        assertThat(rowsOfSymbols.get(1), contains(O, O, X));
    }

    @Test
    public void generatesCorrectVerticalRows() {
        List<List<Symbol>> rowsOfSymbols = transformToRowsOfSymbols(allRowsInGrid);

        assertThat(rowsOfSymbols.get(2), contains(X, VACANT, X));
        assertThat(rowsOfSymbols.get(3), contains(O, O, VACANT));
        assertThat(rowsOfSymbols.get(4), contains(X, VACANT, O));
    }

    @Test
    public void generatesCorrectHorizontalRows() {
        List<List<Symbol>> rowsOfSymbols = transformToRowsOfSymbols(allRowsInGrid);

        assertThat(rowsOfSymbols.get(5), contains(X, O, X));
        assertThat(rowsOfSymbols.get(6), contains(VACANT, O, VACANT));
        assertThat(rowsOfSymbols.get(7), contains(X, VACANT, O));
    }

    private List<List<Symbol>> transformToRowsOfSymbols(List<List<Cell>> cells) {
        List<List<Symbol>> eachRowAsSymbols = newArrayList();

        for (List<Cell> cellsInOneRow : cells) {
            List<Symbol> symbolsFromRow = newArrayList();

            for (Cell cell : cellsInOneRow) {
                symbolsFromRow.add(cell.getSymbol());
            }
            eachRowAsSymbols.add(symbolsFromRow);
        }
        return eachRowAsSymbols;
    }
}
