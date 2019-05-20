package com.example.parking_garage_management;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;


/**
 * DisplayReceipt displays a dialog box with the retrieved vehicle's info
 * along with the total amount due
 *
 * @author Arthur K. Edouard
 */

public class DisplayReceipt extends AppCompatDialogFragment {

    private String message;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Parking Invoice")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }

    /**
     * Takes in vehicle's information and sets it to message
     * to be displayed in dialog box
     * @param msg
     */

    public void setMessage(String msg){
        message = msg;
    }
}
