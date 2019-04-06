package com.afreet.campusconnect.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.afreet.campusconnect.R;

/**
 * In this class, we are adding the functionality of auto- login in the app.
 * SharedPreference is used to store some information in the app data.
 * we wanted the auto-login feature for the app, so this class helps us to do that.
 *
 * we are manipulating two variable:
 *  1)  loginState  : It is a boolean to store whether user is logged in or not.
 *  2)  loginUserId : It is a String to store the logged in user's id so the
 *                      required details and information could be fetched using this user id.
 */
public class SharedPreferenceConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    /**
     * Constructor :
     *      take the context for the calling activity
     * @param context
     *
     * Here, the shared preference is initialized in private mode
     *
     */
    public SharedPreferenceConfig( Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_preference), Context.MODE_PRIVATE);
    }

    /**
     * In this function,
     *      the login status is changed
     *      and
     *      the login user id is changed
     * @param status
     * @param userId
     *
     * It is used when user is logging in or logging out.
     */
    public void writeLoginStatus(boolean status, String userId){

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(context.getResources().getString(R.string.login_status_preference),status);
        editor.putString(context.getResources().getString(R.string.login_userId_preference), userId);
        editor.apply();
    }

    /**
     * returns the current loginStatus.
     *
     * It is the main function to make user logging in.
     * @return status
     */
    public boolean readLoginStatus(){
        boolean status = false;
        status = sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_preference),false);
        return status;
    }


    /**
     * returns the current loginUserId.
     *
     * It is the main function to to get the userId which is logged In.
     * @return loginUserId
     */
    public String readLoginUserId(){
        String loginUserId = "";
        loginUserId = sharedPreferences.getString(context.getResources().getString(R.string.login_userId_preference), context.getResources().getString(R.string.user_id_placeholder));
        return loginUserId;
    }






}
