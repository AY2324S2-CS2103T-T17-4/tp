package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Removes a person from the schedule.
 */
public class UnscheduleCommand extends Command {

    public static final String COMMAND_WORD = "unschedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a person from the schedule. "
            + "Parameters: "
            + "PHONE "
            + "DATE in the format yyyy-mm-dd";

    public static final String MESSAGE_SUCCESS = "Schedule: %1$s removed from %2$s";
    private final Phone phoneNumber;
    private final LocalDate date;

    /**
     * Creates an UnscheduleCommand to remove the specified {@code Person} from the schedule on the specified
     * {@code LocalDate}.
     */
    public UnscheduleCommand(Phone phoneNumber, LocalDate date) {
        requireAllNonNull(phoneNumber, date);
        this.phoneNumber = phoneNumber;
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person person = model.getPersonByPhoneNumber(phoneNumber);
        model.removePersonFromSchedule(person, date);
        return new CommandResult(String.format(MESSAGE_SUCCESS, person.getName().value, date), false, false,
                false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnscheduleCommand)) {
            return false;
        }

        UnscheduleCommand otherUnscheduleCommand = (UnscheduleCommand) other;
        return phoneNumber.equals(otherUnscheduleCommand.phoneNumber) && date.equals(otherUnscheduleCommand.date);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("phoneNumber", phoneNumber)
                .add("date", date)
                .toString();
    }
}
