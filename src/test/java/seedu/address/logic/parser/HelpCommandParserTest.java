package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.HelpCommand;

public class HelpCommandParserTest {

    private final HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_noArguments_returnsOverviewHelpCommand() {
        assertParseSuccess(parser, "", new HelpCommand());
        assertParseSuccess(parser, "   ", new HelpCommand());
    }

    @Test
    public void parse_validCommandWord_returnsContextualHelpCommand() {
        assertParseSuccess(parser, "add", new HelpCommand(AddCommand.COMMAND_WORD));
        assertParseSuccess(parser, "ADD", new HelpCommand(AddCommand.COMMAND_WORD));
    }

    @Test
    public void parse_linkFlag_returnsLinkHelpCommand() {
        assertParseSuccess(parser, HelpCommand.LINK_FLAG, new HelpCommand(true));
    }

    @Test
    public void parse_unknownCommandWord_returnsContextualHelpCommand() {
        assertParseSuccess(parser, "unknown", new HelpCommand("unknown"));
    }

    @Test
    public void parse_multipleArguments_throwsParseException() {
        assertParseFailure(parser, "add extra",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }
}
