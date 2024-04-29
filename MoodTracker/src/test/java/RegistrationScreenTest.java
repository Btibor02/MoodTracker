import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_T;

public class RegistrationScreenTest {

    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @Before
    public void setUp() {
        RegistrationScreen frame = GuiActionRunner.execute(RegistrationScreen::new);
        window = new FrameFixture(frame);
        window.show();
    }

    @Test
    public void testLabels() {
        window.label("appNameLabel").requireText("Mood Tracker");
        window.label("loginLabel").requireText("I've an account");
        window.label("passwordLabel").requireText("Password");
        window.label("usernameLabel").requireText("Username");
        window.label("confirmPasswordLabel").requireText("Confirm Password");
        window.label("registerLabel").requireText("Sign up");
    }

    @Test
    public void testRegistration() {
        window.textBox("usernameField").enterText("Test55");
        window.textBox("usernameField").pressAndReleaseKeys(VK_T, VK_E, VK_S, VK_T);
        window.textBox("passwordField").enterText("Test55555");
        window.textBox("confirmPasswordField").enterText("Test55555");

        window.button("loginButton").requireText("Login");
        window.button("loginButton").requireVisible();
        window.button("loginButton").requireEnabled();
        window.button("loginButton").click();
    }

    @Test
    public void testRegisterButton() {
        window.button("registerButton").click();
        window.button("registerButton").requireText("Sign up");
        window.button("registerButton").requireVisible();
        window.button("registerButton").requireEnabled();
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }
}
