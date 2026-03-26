package seedu.address.logic.parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Locale;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a static utility class that can convert between Strings and LocalDate
 */
public class DateParser {
    public static final String MESSAGE_WRONG_DATE_FORMAT = "Not a valid date! Try using these formats:\n"
            + "yyyy-MM-dd, yyyy/MM/dd, d-M-yyyy, d/M/yyyy,\n"
            + "d-M-yy, d/M/yy, d-M, d/M, day of the week (e.g. Thu or Thursday).";

    private static final DateTimeFormatter DATE_FORMATTERS = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
            .appendOptional(DateTimeFormatter.ofPattern("d-M-yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/M/d"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yy"))
            .appendOptional(DateTimeFormatter.ofPattern("d-M-yy"))
            .toFormatter();

    private static final DateTimeFormatter MONTH_DAY_FORMATTERS = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M"))
            .appendOptional(DateTimeFormatter.ofPattern("d-M"))
            .toFormatter();

    private static final List<DateTimeFormatter> DAY_OF_WEEK_FORMATTER = List.of(
            DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH)
    );

    /**
     * Reads a string and converts it to a Date object
     *
     * @param input String to be parsed into a date
     * @return a LocalDate representation of the input
     * @throws IllegalValueException if the string cannot fit into any DateTimeFormat
     */
    public static LocalDate parse(String input) throws IllegalValueException {
        input = input.trim().toLowerCase();

        if (input.isEmpty()) {
            throw new IllegalValueException(MESSAGE_WRONG_DATE_FORMAT);
        }

        // Tries to match input with date formatter with year
        try {
            return LocalDate.parse(input, DATE_FORMATTERS);
        } catch (DateTimeParseException e) {
            // doesn't match but ignore since exception is thrown below
        }

        LocalDate today = LocalDate.now();
        if (input.equals("today")) {
            return today;
        }

        // Tries to match input with date formatter with no year (just day/month)
        try {
            MonthDay parsed = MonthDay.parse(input, MONTH_DAY_FORMATTERS);
            return parseMonthDay(today, parsed);
        } catch (DateTimeParseException e) {
            //ignore since exception is thrown below
        }

        // capitalize just the first letter (Assuming it is day of the week)
        input = formatDayOfWeek(input);
        // Tries to match input with date formatter with day of the week e.g. Tue, Thur, Friday
        for (DateTimeFormatter formatter : DAY_OF_WEEK_FORMATTER) {
            try {
                DayOfWeek day = DayOfWeek.from(formatter.parse(input));
                return today.with(TemporalAdjusters.nextOrSame(day));
            } catch (DateTimeParseException e) {
                //ignore since exception is thrown below
            }
        }

        throw new IllegalValueException(MESSAGE_WRONG_DATE_FORMAT);
    }

    /**
     * Converts a LocalDate object back into a string to be stored on in data files
     *
     * @param date LocalDate object to be converted
     * @return a string representation of date/datetime
     */
    public static String dateToDataString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Converts a LocalDate into the output format of dates to users
     *
     * @param date LocalDate to convert
     * @return a String to be printed to user that looks good
     */
    public static String dateToPrettyString(LocalDate date) {
        if (date == null) {
            return "";
        }
        return date.format(DateTimeFormatter.ofPattern("EEEE, d MMM yyyy"));
    }

    /**
     * Converts a day and month to LocalDate by taking the nearest upcoming date
     *
     * @param today      LocalDate representing the current date
     * @param dateParsed MonthDay to be converted
     * @return The date with year adjusted
     */
    public static LocalDate parseMonthDay(LocalDate today, MonthDay dateParsed) {
        LocalDate dateThisYear = dateParsed.atYear(today.getYear());

        // the date has passed, so should refer to next year
        if (dateThisYear.isBefore(today)) {
            return dateThisYear.plusYears(1);
        }
        // date has not passed yet
        return dateThisYear;
    }

    /**
     * Converts a string to all lowercase except the first character, which is capitalized
     */
    private static String formatDayOfWeek(String input) {
        assert (input != null);
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
