package com.afreet.campusconnect.Utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afreet.campusconnect.R;


/**
 * As the ProgressDialog is deprecated in the android development from API 26 and above.
 * So, to eradicate that problem, this is the custom progressDialog that behave in same manner.'
 *
 * Firstly, create the object of CustomProgressDialog and call getInstance() function.
 * For example,
 *       private CustomProgressDialog progressDialog = CustomProgressDialog.getInstance();
 *
 * Then, we can call the showProgress and hideProgress in suitable way.
 */
public class CustomProgressDialog {

    public static CustomProgressDialog customProgress = null;
    private Dialog mDialog;
    private ProgressBar mProgressBar;

    public static CustomProgressDialog getInstance() {
        if (customProgress == null) {
            customProgress = new CustomProgressDialog();
        }
        return customProgress;
    }

    /**
     * This function will be used to show the desired dialog.
     * @param context      context take the context of the current activity.
     * @param message      message take the suitable String which will be displayed in the dialog.
     * @param cancelable   cancelable is the boolean which makes if user can dismiss the dialog
     *                     by their own or not.
     *                     true means they cannot dismiss and vice-versa.
     *
     */
    public void showProgress(Context context, String message, boolean cancelable) {
        mDialog = new Dialog(context);
        // no tile for the dialog
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.layout_progress_dialog);
        mProgressBar = (ProgressBar) mDialog.findViewById(R.id.progress_bar);
        //  mProgressBar.getIndeterminateDrawable().setColorFilter(context.getResources()
        // .getColor(R.color.material_blue_gray_500), PorterDuff.Mode.SRC_IN);
        TextView progressText = (TextView) mDialog.findViewById(R.id.progress_text);
        progressText.setText("" + message);
        progressText.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        // you can change or add this line according to your need
        mProgressBar.setIndeterminate(true);
        mDialog.setCancelable(cancelable);
        mDialog.setCanceledOnTouchOutside(cancelable);
        mDialog.show();
    }

    /**
     *this is called when the progressDialog has to be dismissed, like when the progressing process
     * is over.
     */
    public void hideProgress() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
