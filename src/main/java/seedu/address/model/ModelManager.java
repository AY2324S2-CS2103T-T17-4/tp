package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.PayrollWrapper;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDate;
import seedu.address.model.schedule.ScheduleManager;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final Schedule schedule;
    private final FilteredList<Person> filteredPersons;

    private final ObservableList<PayrollWrapper> payrolls;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, Schedule schedule) {
        requireAllNonNull(addressBook, userPrefs, schedule);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs
                + " and schedule " + schedule);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.schedule = schedule;
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        payrolls = FXCollections.observableArrayList();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new ScheduleManager());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_UNARCHIVED_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void archivePerson(Person target, Person archivedPerson) {
        addressBook.archivePerson(target, archivedPerson);
    }

    @Override
    public void unarchivePerson(Person target, Person unarchivedPerson) {
        addressBook.unarchivePerson(target, unarchivedPerson);
    }

    //=========== Schedule ===================================================================================

    @Override
    public void setSchedule(Schedule schedule) {
        this.schedule.resetData(schedule);
    }

    @Override
    public Schedule getSchedule() {
        return schedule;
    }

    @Override
    public Set<ScheduleDate> getScheduleDates() {
        return schedule.getScheduleDates();
    }

    @Override
    public void addPersonToSchedule(Person person, LocalDate date) {
        requireAllNonNull(person, date);
        schedule.addPerson(person, date);
    }

    @Override
    public void removePersonFromSchedule(Person person, LocalDate date) {
        requireAllNonNull(person, date);
        schedule.deletePerson(person, date);
    }

    //=========== Payroll  ===================================================================================

    @Override
    public void generatePayroll(LocalDate startDate, LocalDate endDate) {
        requireAllNonNull(startDate, endDate);
        payrolls.clear();
        List<PayrollWrapper> newPayrolls = new ArrayList<>();
        Map<Person, Double> hoursWorked = schedule.getHoursWorked(startDate, endDate);
        for (Map.Entry<Person, Double> entry : hoursWorked.entrySet()) {
            newPayrolls.add(new PayrollWrapper(entry.getKey(), entry.getValue()));
        }
        payrolls.setAll(newPayrolls);
    }

    @Override
    public ObservableList<PayrollWrapper> getPayrollList() {
        return payrolls;
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    /**
     *  Returns an unmodifiable view of the list of unarchived {@code Person} backed by the internal list of
     * {@code addressBook}
     */
    @Override
    public ObservableList<Person> getFilteredUnarchivedPersonList() {
        this.updateFilteredPersonList(PREDICATE_SHOW_ALL_UNARCHIVED_PERSONS);
        return filteredPersons;
    }

    /**
     * Returns an unmodifiable view of the list of archived {@code Person} backed by the internal list of
     * {@code addressBook}
     */
    @Override
    public ObservableList<Person> getFilteredArchivedPersonList() {
        this.updateFilteredPersonList(PREDICATE_SHOW_ALL_ARCHIVED_PERSONS);
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

    @Override
    public Person getPersonByPhoneNumber(Phone phoneNumber) {
        requireNonNull(phoneNumber);
        for (Person person : addressBook.getPersonList()) {
            if (person.getPhone().equals(phoneNumber)) {
                return person;
            }
        }
        return null; // Return null if the person is not found
    }

    @Override
    public void updatePerson(Person personToUpdate) {
        requireNonNull(personToUpdate);
        addressBook.setPerson(personToUpdate, personToUpdate);
    }

}
