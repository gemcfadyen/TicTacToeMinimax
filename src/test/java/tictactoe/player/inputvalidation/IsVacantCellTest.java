package tictactoe.player.inputvalidation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tictactoe.grid.Grid;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IsVacantCellTest {
    @Mock private Grid grid;
    private IsVacantCell isVacantCell;

    @Before
    public void setup() {
        isVacantCell = new IsVacantCell(grid);
    }

    @Test
    public void indexOfVacantCellIsValid() {
        when(grid.isEmptyAt(1)).thenReturn(true);
        assertThat(isVacantCell.isValid("1"), is(true));
    }

    @Test
    public void indexOfOccupiedCellIsNotValid() {
        when(grid.isEmptyAt(1)).thenReturn(false);
        assertThat(isVacantCell.isValid("1"), is(false));
    }
}