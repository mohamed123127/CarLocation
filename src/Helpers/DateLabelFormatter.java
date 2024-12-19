package Helpers;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JFormattedTextField;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Object stringToValue(String text) throws java.text.ParseException {
        return dateFormat.parse(text);
    }

    @Override
    public String valueToString(Object value) {
        if (value != null) {
            if (value instanceof GregorianCalendar) {
                java.util.Date date = ((GregorianCalendar) value).getTime();
                return dateFormat.format(date);
            }
            return "";
        }
        return "";
    }
}

