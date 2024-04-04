package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.PayrollWrapper;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDate;
import seedu.address.testutil.PersonBuilder;

public class ScheduleCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        Phone phoneNumber = new Phone("92345678");
        LocalDate date = LocalDate.now();
        assertThrows(NullPointerException.class, () -> new ScheduleCommand(null, null));
        assertThrows(NullPointerException.class, () -> new ScheduleCommand(null, date));
        assertThrows(NullPointerException.class, () -> new ScheduleCommand(phoneNumber, null));
    }

    @Test
    public void execute_personNotFound_throwsCommandException() {
        Person person = new PersonBuilder().build();
        ModelStubWithGetPersonByPhoneNumber modelStub = new ModelStubWithGetPersonByPhoneNumber(person);
        Phone phoneNumber = new Phone("92315678");
        LocalDate date = LocalDate.now();

        assertThrows(CommandException.class, () -> new ScheduleCommand(phoneNumber, date).execute(modelStub));
    }

    @Test
    public void equals() {
        Phone phoneNumber1 = new Phone("92345678");
        Phone phoneNumber2 = new Phone("91345678");
        LocalDate date = LocalDate.now();
        ScheduleCommand scheduleCommand1 = new ScheduleCommand(phoneNumber1, date);
        ScheduleCommand scheduleCommand2 = new ScheduleCommand(phoneNumber2, date);

        // same object -> returns true
        assertTrue(scheduleCommand1.equals(scheduleCommand1));

        // same values -> returns true
        ScheduleCommand scheduleCommand1Copy = new ScheduleCommand(phoneNumber1, date);
        assertTrue(scheduleCommand1.equals(scheduleCommand1Copy));

        // different types -> returns false
        assertFalse(scheduleCommand1.equals(1));

        // null -> returns false
        assertFalse(scheduleCommand1.equals(null));

        // different person -> returns false
        assertFalse(scheduleCommand1.equals(scheduleCommand2));
    }

    @Test
    public void toStringMethod() {
        Phone phoneNumber = new Phone("92345678");
        LocalDate date = LocalDate.now();
        ScheduleCommand scheduleCommand = new ScheduleCommand(phoneNumber, date);

        String expected = ScheduleCommand.class.getCanonicalName() + "{phoneNumber=92345678, date=" + date + "}";
        assertEquals(expected, scheduleCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void updatePerson(Person personToUpdate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public Person getPersonByPhoneNumber(Phone phoneNumber) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredUnarchivedPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredArchivedPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Schedule getSchedule() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Set<ScheduleDate> getScheduleDates() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPersonToSchedule(Person person, LocalDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removePersonFromSchedule(Person person, LocalDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void archivePerson(Person personToArchive, Person archivedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unarchivePerson(Person personToUnarchive, Person unarchivedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void generatePayroll(LocalDate startDate, LocalDate endDate) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public ObservableList<PayrollWrapper> getPayrollList() {
            throw new AssertionError("This method should not be called.");
        };
    }

    private class ModelStubWithGetPersonByPhoneNumber extends ModelStub {
        private final Person person;

        ModelStubWithGetPersonByPhoneNumber(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public Person getPersonByPhoneNumber(Phone phoneNumber) {
            if (!person.getPhone().equals(phoneNumber)) {
                return null;
            }
            return person;
        }
    }

    private class ModelStubWithScheduleDate extends ModelStub {
        private final Set<ScheduleDate> scheduleDates = new HashSet<>();

        ModelStubWithScheduleDate(Person person, LocalDate date) {
            requireNonNull(person);
            ScheduleDate scheduleDate = new ScheduleDate(date);
            scheduleDates.add(scheduleDate);
        }

        @Override
        public void addPersonToSchedule(Person person, LocalDate date) {
            requireNonNull(person);
            ScheduleDate scheduleDate = new ScheduleDate(date);
            scheduleDates.add(scheduleDate);
        }

        @Override
        public Set<ScheduleDate> getScheduleDates() {
            return scheduleDates;
        }
    }

    /**
     * A Model stub that always accept the person being added to the schedule.
     */
    private class ModelStubAcceptingScheduleUpdate extends ModelStub {
        final Set<ScheduleDate> scheduleDates = new HashSet<>();
        final Person person;

        ModelStubAcceptingScheduleUpdate(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public void addPersonToSchedule(Person person, LocalDate date) {
            requireNonNull(person);
            ScheduleDate scheduleDate = new ScheduleDate(date);
            scheduleDates.add(scheduleDate);
        }

        @Override
        public Set<ScheduleDate> getScheduleDates() {
            return scheduleDates;
        }

        @Override
        public Person getPersonByPhoneNumber(Phone phoneNumber) {
            if (!person.getPhone().equals(phoneNumber)) {
                return null;
            }
            return person;
        }
    }

}
