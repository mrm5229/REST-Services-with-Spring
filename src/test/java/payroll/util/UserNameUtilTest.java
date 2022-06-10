package payroll.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static payroll.util.UserNameUtil.firstLastNameSplitter;

public class UserNameUtilTest {

    @Test
    public void testfirstLastNameSplitter() {
        // gather test inputs
        final String name = "Samwise Gamgee";

        // perform test
        var result = firstLastNameSplitter(name);

        // assert results
        assertThat(result.getFirstName(), is("Samwise"));
        assertThat(result.getLastName(), is("Gamgee"));
    }

    @Test
    public void testfirstLastNameSplitter_middle_initial() {
        // gather test inputs
        final String name = "Samwise T Gamgee";

        // perform test
        var result = firstLastNameSplitter(name);

        // assert results
        assertThat(result.getFirstName(), is("Samwise"));
        assertThat(result.getLastName(), is("Gamgee"));
    }

    @Test
    public void testfirstLastNameSplitter_suffix_jr() {
        // gather test inputs
        final String name = "Samwise Gamgee Jr";

        // perform test
        var result = firstLastNameSplitter(name);

        // assert results
        assertThat(result.getFirstName(), is("Samwise"));
        assertThat(result.getLastName(), is("Gamgee"));
    }

    @Test
    public void testfirstLastNameSplitter_suffix_jr_with_period() {
        // gather test inputs
        final String name = "Samwise Gamgee Jr.";

        // perform test
        var result = firstLastNameSplitter(name);

        // assert results
        assertThat(result.getFirstName(), is("Samwise"));
        assertThat(result.getLastName(), is("Gamgee"));
    }

    @Test
    public void testfirstLastNameSplitter_suffix_sr() {
        // gather test inputs
        final String name = "Samwise Gamgee Sr";

        // perform test
        var result = firstLastNameSplitter(name);

        // assert results
        assertThat(result.getFirstName(), is("Samwise"));
        assertThat(result.getLastName(), is("Gamgee"));
    }

    @Test
    public void testfirstLastNameSplitter_suffix_sr_with_period() {
        // gather test inputs
        final String name = "Samwise Gamgee Sr.";

        // perform test
        var result = firstLastNameSplitter(name);

        // assert results
        assertThat(result.getFirstName(), is("Samwise"));
        assertThat(result.getLastName(), is("Gamgee"));
    }
}
