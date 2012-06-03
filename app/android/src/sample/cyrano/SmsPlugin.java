package sample.cyrano;

import java.io.File;
import java.util.ArrayList;

import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsManager;
import android.widget.ListView;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;
import sample.cyrano.*;

public class SmsPlugin extends Plugin {
	public final String ACTION_SEND_SMS = "sendSMS";
	public final String ACTION_GET_INBOX = "getInbox";
	
	static Integer PHONE_NUMBER_COLUMN_ID = 2;
	static Integer PERSON_COLUMN_ID = 3;
	static Integer BODY_COLUMN_ID = 13;
	
	@Override
	public PluginResult execute(String action, JSONArray arg1, String callbackId) {
		PluginResult result = new PluginResult(Status.INVALID_ACTION);

		if (action.equals(ACTION_SEND_SMS)) {
			try {
				String phoneNumber = arg1.getString(0);
				String message = arg1.getString(1);
				sendSMS(phoneNumber, message);
				result = new PluginResult(Status.OK);
			}
			catch (JSONException ex) {
				result = new PluginResult(Status.JSON_EXCEPTION, ex.getMessage());
			}
		} else if(action.equals(ACTION_GET_INBOX)){
			try {
				result = new PluginResult(Status.OK, this.getSmsInbox());
			} catch (JSONException ex) {
				result = new PluginResult(Status.JSON_EXCEPTION, ex.getMessage());
			}
		}

		return result;
	}

	protected void sendSMS(String phoneNumber, String message) {
		SmsManager manager = SmsManager.getDefault();

        PendingIntent sentIntent = PendingIntent.getActivity(this.ctx.getContext(), 0, new Intent(), 0);  

		manager.sendTextMessage(phoneNumber, null, message, sentIntent, null);
	}
	
	/**
	* Gets the Directory listing for file, in JSON format
	* @param file The file for which we want to do directory listing
	* @return JSONObject representation of directory list. 
	*  e.g {"filename":"/sdcard","isdir":true,"children":[{"filename":"a.txt","isdir":false},{..}]}
	* @throws JSONException
	*/
	protected JSONArray getSmsInbox() throws JSONException {
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        
        Context context = CyranoApplication.getAppContext();
        Cursor cursor = context.getContentResolver().query(uriSMSURI, null, null, null,null);
        
        String sms = "";
        
        JSONArray response = new JSONArray();
        
        while(cursor.moveToNext()){
        	JSONObject messageObj = new JSONObject();
        	
        	//messageArray.add(new SmsMessage(cursor.getString(PHONE_NUMBER_COLUMN_ID), cursor.getString(BODY_COLUMN_ID)));
			
			for(int idx=0;idx<cursor.getColumnCount();idx++){
				messageObj.put(cursor.getColumnName(idx), cursor.getString(idx));
				//sms += cursor.getColumnName(idx) + ":" + cursor.getString(idx) + "\n";
			}
			response.put(messageObj);
        	//sms += cursor.getString(PHONE_NUMBER_COLUMN_ID) + ": " + cursor.getString(BODY_COLUMN_ID) + "\n";
        	//ListView listViewMessages = (ListView) findViewById(R.id.textMessageList);
        	//listViewMessages.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        	//listViewMessages.setAdapter(new SmsMessageAdapter(CyranoActivity.this, R.layout.sms_message_listitem, messageArray));
        }
        return response;
	}

}