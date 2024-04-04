package seedu.address.model.schedule;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import seedu.address.model.person.Person;

/**
 * Represents a Schedule that maps lists of Persons to dates.
 */
public interface Schedule {
    /**
     * Returns the schedule as a Map.
     */
    Set<ScheduleDate> getScheduleDates();

    /**
     * Adds a Person to the schedule on the given date.
     */
    void addPerson(Person person, LocalDate date);

    /**
     * Deletes a Person from the schedule on the given date.
     */
    void deletePerson(Person person, LocalDate date);

    /**
     * Adds a ScheduleDate to the schedule.
     */
    void addScheduleDate(ScheduleDate scheduleDate);

    /**
     * Resets the existing data of this {@code Schedule} with {@code newData}.
     */
    void resetData(Schedule newData);


    /**
     * Returns the number of hours worked by each person in the schedule between the given dates.
     */
    Map<Person, Double> getHoursWorked(LocalDate startDate, LocalDate endDate);
}
