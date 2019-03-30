package com.dsquare.sabithaIndrareddy.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;


/**
 * Created by Android on 10/13/2016.
 */
public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = SmsReceiver.class.getSimpleName();
    private static SmsListener mListener;
    private String SMS_ORIGIN = "DSQUAR";
    String OTP_DELIMITER = " is";
    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                SmsMessage currentMessage;
                for (Object aPdusObj : pdusObj) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                         String format = bundle.getString("format");
                         currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj, format);
                    }
                    else {
                         currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj);

                    }
                    String senderAddress = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();

                    Log.e(TAG, "Received SMS:Received SMS: " + message + ", Sender: " + senderAddress);

                    // if the SMS is not from our gateway, ignore the message
                    if (!senderAddress.substring(3).toLowerCase().contains(SMS_ORIGIN.toLowerCase())) {
                        return;
                    }


                    // verification code from sms
                    String verificationCode = getVerificationCode(message);

                    mListener.messageReceived(verificationCode);


                    Log.e(TAG, "OTP received: " + verificationCode);

                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    /**
     * Getting the OTP from sms message body
     * ':' is the separator of OTP from the message
     *
     * @param message
     * @return
     */
    private String getVerificationCode(String message) {
        String code = null;
        int index = message.indexOf(OTP_DELIMITER);

        if (index != -1) {
            int start = index + 4;
            int length = 6;
            code = message.substring(start);
            return code;
        }
        return code;
    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }
}