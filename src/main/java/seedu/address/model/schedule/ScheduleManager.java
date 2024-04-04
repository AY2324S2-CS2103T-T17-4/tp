package seedu.address.model.schedule;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import seedu.address.model.person.Person;

/**
 * Represents a Schedule that maps lists of Persons to dates.
 */
public class ScheduleManager implements Schedule {
    // The schedule is a set of ScheduleDates.
    private final Set<ScheduleDate> schedule;

    /**
     * Creates a MonthSchedule with an empty schedule.
     */
    public ScheduleManager() {
        this.schedule = new HashSet<>();
    }

    @Override
    public Set<ScheduleDate> getScheduleDates() {
        return schedule;
    }

    @Override
    public void addPerson(Person person, LocalDate date) {
        for (ScheduleDate scheduleDate : schedule) {
            if (scheduleDate.getDate().equals(date)) {
                if (!scheduleDate.hasPerson(person)) {
                    scheduleDate.addPerson(person);
                }
                return;
            }
        }
        ScheduleDate newScheduleDate = new ScheduleDate(date);
        newScheduleDate.addPerson(person);
        schedule.add(newScheduleDate);
    }

    /**
     * Adds a ScheduleDate to the schedule.
     * Used only by JsonAdaptedSchedule to convert JSON data to a Schedule,
     * so there is no need to check for duplicates.
     * @param scheduleDate the ScheduleDate to add
     */
    @Override
    public void addScheduleDate(ScheduleDate scheduleDate) {
        schedule.add(scheduleDate);
    }

    @Override
    public void deletePerson(Person person, LocalDate date) {
        for (ScheduleDate scheduleDate : schedule) {
            if (scheduleDate.getDate().equals(date)) {
                if (scheduleDate.hasPerson(person)) {
                    scheduleDate.removePerson(person);
                }
                return;
            }
        }
    }

    @Override
    public Map<Person, Double> getHoursWorked(LocalDate startDate, LocalDate endDate) {
        Map<Person, Double> hoursWorked = new HashMap<>();
        for (ScheduleDate scheduleDate : schedule) {
            if (isAfterOrEqual(scheduleDate.getDate(), startDate) && isBeforeOrEqual(scheduleDate.getDate(), endDate)) {
                for (Person person : scheduleDate.getPersons()) {
                    if (!hoursWorked.containsKey(person)) {
                        hoursWorked.put(person, 0.0);
                    }
                    hoursWorked.put(person, hoursWorked.get(person) + 8.0);
                }
            }
        }
        return hoursWorked;
    }

    @Override
    public void resetData(Schedule newData) {
        schedule.clear();
        if (newData != null) {
            schedule.addAll(newData.getScheduleDates());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ScheduleManager // instanceof handles nulls
            && schedule.equals(((ScheduleManager) other).schedule));
    }
    private boolean isAfterOrEqual(LocalDate date1, LocalDate date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.isAfter(date2) || date1.isEqual(date2);
    }

    private boolean isBeforeOrEqual(LocalDate date1, LocalDate date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.isBefore(date2) || date1.isEqual(date2);
    }
}
