package tictactoe.player.inputvalidation;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class IsInGridBoundaryTest {
    private IsInGridBoundary isInGridBoundary = new IsInGridBoundary();

    @Test
    public void cellIndexWithinTheGridIsValid() {
        assertThat(isInGridBoundary.isValid("1"), is(true));
    }

    @Test
    public void cellIndexLargerThanTheGridIsNotValid() {
        assertThat(isInGridBoundary.isValid("100"), is(false));
    }

    @Test
    public void cellIndexSmallerThanTheGridIsNotValid() {
        assertThat(isInGridBoundary.isValid("-9"), is(false));
    }
}
