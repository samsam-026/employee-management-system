package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    //using the regex the strings are valdiated to see if they have only the alphabet in it
    public static String validateString(String name) {
        if (name.length() != 0) {
            String regex = "^[a-zA-Z ]+$";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            //using the matcher we can match the expected pattern to the user's input
            Matcher matcher = pattern.matcher(name);
            //matcher.find gives a boolean answer we will be retruning the name based on the result of matcher.find
            if (!matcher.find()) {
                name = "false";
            }
        } else {
            name = "true";
        }
        return name;
    }

    //this function is used to validate address to ensure that some special characters will not be allowed
    public static String validateAddress(String address) {
        String validAddress;
        if (address.length() != 0) {
            String regex = "^[#./0-9a-zA-Z\\s,-]+$";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(address);
            if (matcher.find()) {
                validAddress = address;
            } else {
                validAddress = "true";
            }
        } else {
            validAddress = "false";
        }
        return validAddress;
    }

    //function is used to check if the contact number and ids entered are integers and if it is the contact number only 9 digit phone numbers will be accepted
    public static int validateID(String id, String name) {
        int validID;
        if (name.equals("ContactNo") && (id.length() > 9 || (id.length() < 9 && id.length() > 0))) {
            validID = -2;
        } else {
            if (id.length() != 0) {

                try {
                    validID = Integer.parseInt(id);
                    if (validID <= 0) {
                        validID = 0;
                    }
                } catch (NumberFormatException e) {
                    validID = 0;
                }
            } else {
                validID = -1;
            }

        }
        return validID;
    }

    //the date is also validated to ensure dates like 99/99/2000 are not accepted
    public static String validateDate(String date1) {
        LocalDate date;
        if (date1.length() != 0) {
            //so using a pattern dd/mm/uuuu the date entered is matched to check if it complies with the pattern
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
            try {
                date = LocalDate.parse(date1, format);
            } catch (DateTimeParseException e) {
                date1 = "false";
            }
        } else {
            date1 = "true";
        }
        return date1;

    }

//the email is validated to ensure specail characters other than the @ _ & - * and . are entered
    public static String validEmail(String email) {
        if (!(email.length() == 0)) {
            String format = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(format);
            if (!pattern.matcher(email).matches()) {
                email = "false";
            }
        } else {
            email = "true";

        }
        return email;
    }

    //used to ask the user if they would really want to delete the entry
    public static String alert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the record from the table?");
        Optional<ButtonType> confirmExit=alert.showAndWait();
        if (confirmExit.isPresent() && confirmExit.get() == ButtonType.OK) {
            return "true";
        }
        return "false";
    }
}