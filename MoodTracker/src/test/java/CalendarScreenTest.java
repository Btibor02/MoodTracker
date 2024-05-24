import jdk.jshell.spi.ExecutionControl;
import org.assertj.swing.core.MouseButton;
import org.assertj.swing.core.MouseClickInfo;
import org.assertj.swing.data.TableCell;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JTableFixture;
import static org.assertj.core.api.Assertions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.time.LocalDate;
import java.time.Month;

public class CalendarScreenTest {

    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @Before
    public void setUp() {
        CalendarScreen frame = GuiActionRunner.execute(CalendarScreen::new);
        window = new FrameFixture(frame);
        window.show();
    }

    @Test
    public void testLabels() {
        LocalDate date = LocalDate.now();
        Month month = date.getMonth();
        int year = date.getYear();

        window.label("appNameLabel").requireText("Mood Tracker");
        window.label("welcomeLabel").requireText("Welcome back ");
        window.label("currentMonthLabel").requireText(String.valueOf(month));
    }

    @Test
    public void testPanels() {
        window.panel("mondayPanel").requireVisible();
        window.panel("tuesdayPanel").requireVisible();
        window.panel("wednesdayPanel").requireVisible();
        window.panel("thursdayPanel").requireVisible();
        window.panel("fridayPanel").requireVisible();
        window.panel("saturdayPanel").requireVisible();
        window.panel("sundayPanel").requireVisible();

        window.label("mondayLabel").requireText("M").requireVisible().foreground();
        window.label("tuesdayLabel").requireText("T").requireVisible().foreground();
        window.label("wednesdayLabel").requireText("W").requireVisible().foreground();
        window.label("thursdayLabel").requireText("T").requireVisible().foreground();
        window.label("fridayLabel").requireText("F").requireVisible().foreground();
        window.label("saturdayLabel").requireText("S").requireVisible().foreground();
        window.label("sundayLabel").requireText("S").requireVisible().foreground();
    }

    @Test
    public void testTable() {
        JTableFixture table = window.table("calendarTable");
        table.requireVisible();
        assertThat(table.rowCount()).isEqualTo(5);

        table.click();

    }

    @Test
    public void testButtons() {
        JButtonFixture button = window.button("prevMonthButton");
        button.requireText("<-");
        button.click();
        window.button("nextMonthButton").requireVisible().requireText("->").click();
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }
}
