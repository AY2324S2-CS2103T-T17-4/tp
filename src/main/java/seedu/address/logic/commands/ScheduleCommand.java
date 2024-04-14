package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Adds a person to the address book.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the schedule.\n"
            + "Parameters: "
            + "PHONE "
            + "DATE in the format yyyy-mm-dd\n"
            + "Example: " + COMMAND_WORD + " "
            + "91234567 "
            + "2021-01-01";

    public static final String MESSAGE_SUCCESS = "Schedule: %1$s added on %2$s";
    private final Phone phoneNumber;
    private final LocalDate date;

    /**
     * Creates a ScheduleCommand to add the specified {@code Person} to the schedule on the specified {@code LocalDate}.
     */
    public ScheduleCommand(Phone phoneNumber, LocalDate date) {
        requireAllNonNull(phoneNumber, date);
        this.phoneNumber = phoneNumber;
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person person = model.getPersonByPhoneNumber(phoneNumber);
        if (person == null) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }
        model.addPersonToSchedule(person, date);
        return new CommandResult(String.format(MESSAGE_SUCCESS, person.getName().value, date), false, false,
                false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleCommand)) {
            return false;
        }

        ScheduleCommand otherScheduleCommand = (ScheduleCommand) other;
        return phoneNumber.equals(otherScheduleCommand.phoneNumber) && date.equals(otherScheduleCommand.date);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("phoneNumber", phoneNumber)
                .add("date", date)
                .toString();
    }
}
