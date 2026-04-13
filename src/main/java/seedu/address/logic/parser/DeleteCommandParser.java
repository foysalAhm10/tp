package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] indexTokens = trimmedArgs.split("\\s+");

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        for (String indexToken : indexTokens) {
            if (!isInteger(indexToken)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            }
        }

        List<Index> indexes = new ArrayList<>();
        for (String indexToken : indexTokens) {
            Index index;
            try {
                index = ParserUtil.parseIndex(indexToken);
            } catch (ParseException pe) {
                if (indexTokens.length > 1) {
                    throw new ParseException(DeleteCommand.MESSAGE_INVALID_INDEXES, pe);
                }
                throw pe;
            }
            indexes.add(index);
        }

        return new DeleteCommand(indexes);
    }

    private static boolean isInteger(String token) {
        return token.matches("[+-]?\\d+");
    }

}
