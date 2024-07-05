package org.framework.rodolfo.freire.git.vertlog.util;

import org.framework.rodolfo.freire.git.vertlog.exception.BuyParseException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Utility class for parsing dates used in the Buy system.
 * <p>
 * This class provides a static method `parseDate` that attempts to parse a String representation of a date into a `LocalDate` object.
 * It uses a predefined date format ("yyyy-MM-dd") for parsing.
 *
 * @see LocalDate
 */
public class BuyParseDate {

    /**
     * Parses a String representation of a date into a `LocalDate` object.
     * <p>
     * This method attempts to parse the provided `dateString` according to the expected format ("yyyy-MM-dd").
     * If the parsing is successful, it returns the corresponding `LocalDate` object.
     *
     * @param dateString The String representation of the date to be parsed.
     * @return The parsed `LocalDate` object if successful, or throws a `BuyParseException` if the parsing fails.
     * @throws BuyParseException Thrown if the provided `dateString` cannot be parsed into a valid `LocalDate` object using the expected format.
     */
    public static Date parseDate(String dateString) throws BuyParseException {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setLenient(false);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            throw new BuyParseException(0, "Invalid date format: " + dateString);
        } catch (IllegalArgumentException e) {
            throw new BuyParseException(0, "Invalid date argument: " + dateString);
        }
    }
    public static Date parseDateApi(String dateString) throws BuyParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            throw new BuyParseException(0, "Invalid date format: " + dateString);
        } catch (IllegalArgumentException e) {
            throw new BuyParseException(0, "Invalid date argument: " + dateString);
        }
    }

}