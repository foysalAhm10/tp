package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Theme;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ThemeCommandTest {

    @Test
    public void execute_setsThemeOnModel() {
        Model model = new ModelManager();
        ThemeCommand command = new ThemeCommand(Theme.DARK);

        CommandResult result = command.execute(model);

        assertEquals(Theme.DARK, model.getTheme());
        assertEquals(String.format(ThemeCommand.MESSAGE_SUCCESS, "dark"), result.getFeedbackToUser());
    }

    @Test
    public void equals() {
        ThemeCommand darkCommand = new ThemeCommand(Theme.DARK);
        ThemeCommand anotherDarkCommand = new ThemeCommand(Theme.DARK);
        ThemeCommand lightCommand = new ThemeCommand(Theme.LIGHT);

        assertEquals(darkCommand, anotherDarkCommand);
        org.junit.jupiter.api.Assertions.assertNotEquals(darkCommand, lightCommand);
    }
}
