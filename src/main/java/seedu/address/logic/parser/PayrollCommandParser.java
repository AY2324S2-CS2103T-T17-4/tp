package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.time.LocalDate;

import seedu.address.logic.commands.PayrollCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PayrollCommand object
 */
public class PayrollCommandParser implements Parser<PayrollCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PayrollCommand
     * and returns a PayrollCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PayrollCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STARTDATE, PREFIX_ENDDATE);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STARTDATE, PREFIX_ENDDATE);
        if (!arePrefixesPresent(argMultimap, PREFIX_STARTDATE, PREFIX_ENDDATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PayrollCommand.MESSAGE_USAGE));
        }
        LocalDate startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_STARTDATE).get());
        LocalDate endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_ENDDATE).get());
        return new PayrollCommand(startDate, endDate);
    }
}
