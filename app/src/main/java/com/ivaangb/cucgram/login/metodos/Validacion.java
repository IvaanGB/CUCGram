package com.ivaangb.cucgram.login.metodos;

import android.content.res.Resources;
import android.support.design.widget.TextInputEditText;

import com.ivaangb.cucgram.R;

/**
 * Created by ivang on 5/6/2018.
 */

public class Validacion {

    public boolean validar(TextInputEditText username, TextInputEditText password){
        if (username.getText().toString().isEmpty()) {
            username.requestFocus();
            username.setError(Resources.getSystem().getString(R.string.errorUsername));
            return false;
        }

        if (password.getText().toString().isEmpty()) {
            password.requestFocus();
            password.setError(Resources.getSystem().getString(R.string.errorPassword));
            return false;
        }


        return true;
    }

}
