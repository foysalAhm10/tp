package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();
    private final CommandDatabase commandDatabase = new CommandDatabase();

    @Test
    public void execute_overview_success() {
        CommandResult expectedCommandResult = new CommandResult(commandDatabase.getHelpOverview());
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_link_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpCommand.SHOWING_HELP_MESSAGE, null, true, false);
        assertCommandSuccess(new HelpCommand(true), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_contextualHelp_success() {
        String expectedMessage = commandDatabase.getDetailedHelp(AddCommand.COMMAND_WORD).orElseThrow();
        assertCommandSuccess(new HelpCommand(AddCommand.COMMAND_WORD), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unknownCommand_failure() {
        assertCommandFailure(new HelpCommand("unknown"), model,
                String.format(HelpCommand.MESSAGE_UNKNOWN_HELP_TOPIC, "unknown"));
    }
}
