package Class;

import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Created by DIEGO CASALLAS on 25/02/2016.
 */
public class General {
    public static  String EMAIL_USER="";
    public static final boolean validateEmpty(EditText[] editTexts) {

        boolean bValidateData = true;
        boolean bReturn = true;
        int i = 0;

        while (bValidateData) {

            if (editTexts.length == i) {
                bValidateData = false;
                bReturn = true;
            } else {

                if (editTexts[i].getText().equals("") || editTexts[i].getText().length() <= 1) {
                    bValidateData = false;
                    bReturn = false;
                }
            }
            i++;

        }
        return bReturn;

    }
    public static final boolean validateTypeString(EditText[] editTexts) {


        boolean bValidateData = true;
        boolean bReturn = true;
        int i = 0;

        while (bValidateData) {


            if (editTexts.length == i) {
                bValidateData = false;
                bReturn = true;
            } else {

                if (!Pattern.matches("[0-9]+", editTexts[i].getText())) {
                    bValidateData = false;
                    bReturn = false;
                }
            }
            i++;

        }

        return bReturn;

    }
}
