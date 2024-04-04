package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.PayrollWrapper;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDate;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that evaluates to true when the person is not archived */
    Predicate<Person> PREDICATE_SHOW_ALL_UNARCHIVED_PERSONS = person -> !person.getArchiveStatus().getArchiveStatus();

    /** {@code Predicate} that evaluates to true when the person is archived */
    Predicate<Person> PREDICATE_SHOW_ALL_ARCHIVED_PERSONS = person -> person.getArchiveStatus().getArchiveStatus();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces schedule data with the data in {@code schedule}.
     */
    void setSchedule(Schedule schedule);

    /** Returns the Schedule */
    Schedule getSchedule();

    /**
     * Returns the schedule dates.
     */
    Set<ScheduleDate> getScheduleDates();

    /**
     * Adds {@code person} on {@code date} to the {@code Schedule} in the model.
     */
    void addPersonToSchedule(Person person, LocalDate date);

    /**
     * Removes {@code person} on {@code date} from the {@code Schedule} in the model.
     */
    void removePersonFromSchedule(Person person, LocalDate date);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered un-archived person list */
    ObservableList<Person> getFilteredUnarchivedPersonList();

    /** Returns an unmodifiable view of the filtered archived person list */
    ObservableList<Person> getFilteredArchivedPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns a person with the given phone number.
     * Returns {@code null} if no person with the given phone number is found.
     */
    Person getPersonByPhoneNumber(Phone phoneNumber);

    /**
     * Updates the details of the specified person in the address book with the details of the updated person.
     * @param personToUpdate The person to be updated.
     */
    void updatePerson(Person personToUpdate);

    void archivePerson(Person personToArchive, Person archivedPerson);

    void unarchivePerson(Person personToUnarchive, Person unarchivedPerson);
    /**
     * Generates the payroll for the specified period.
     * @param startDate The start date of the period.
     * @param endDate The end date of the period.
     */
    void generatePayroll(LocalDate startDate, LocalDate endDate);

    /**
     * Returns the payroll list.
     */
    ObservableList<PayrollWrapper> getPayrollList();
}
