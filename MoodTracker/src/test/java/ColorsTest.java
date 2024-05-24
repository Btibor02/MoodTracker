import jdk.jshell.spi.ExecutionControl;
import org.assertj.core.api.Assertions;
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

public class ColorsTest {
    private Color backgroundColor;
    private Color secondaryColor;
    private Color borderColor;
    private Color textColor;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @Before
    public void setUp() {
        Colors colors = new Colors();
        backgroundColor = colors.backgroundColor;
        secondaryColor = colors.secondaryBackgroundColor;
        borderColor = colors.borderColor;
        textColor = colors.textColor;
    }

    @Test
    public void colorsTest() {
        Assertions.assertThat(backgroundColor).isEqualTo(new Color(255, 235, 198));
        Assertions.assertThat(secondaryColor).isEqualTo(new Color(236, 203, 124));
        Assertions.assertThat(borderColor).isEqualTo(new Color(101, 85, 32));
        Assertions.assertThat(textColor).isEqualTo(new Color(101, 85, 32));
    }
}