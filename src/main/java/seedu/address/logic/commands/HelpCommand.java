package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Formats help instructions for local display and optionally opens the help window.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a summary of all commands, or help for a "
            + "specific command.\n"
            + "Parameters: [COMMAND_WORD]\n"
            + "Example: " + COMMAND_WORD + " add";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";
    public static final String MESSAGE_UNKNOWN_HELP_TOPIC = "Unknown command for help: %1$s";

    private static final CommandDatabase COMMAND_DATABASE = new CommandDatabase();

    private final String targetCommand;

    /**
     * Creates a help command for the overview.
     */
    public HelpCommand() {
        this(null);
    }

    /**
     * Creates a help command for the specified command word.
     */
    public HelpCommand(String targetCommand) {
        this.targetCommand = targetCommand;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetCommand == null) {
            return new CommandResult(COMMAND_DATABASE.getHelpOverview(), null, true, false);
        }

        return COMMAND_DATABASE.getDetailedHelp(targetCommand)
                .map(CommandResult::new)
                .orElseThrow(() -> new CommandException(String.format(MESSAGE_UNKNOWN_HELP_TOPIC, targetCommand)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof HelpCommand)) {
            return false;
        }

        HelpCommand otherHelpCommand = (HelpCommand) other;
        return Objects.equals(targetCommand, otherHelpCommand.targetCommand);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetCommand", targetCommand)
                .toString();
    }
}
