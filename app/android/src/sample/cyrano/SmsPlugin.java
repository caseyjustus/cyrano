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
	public final String ACTION_GET_ALL = "getAll";

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
				result = new PluginResult(Status.OK, this.getInbox());
			} catch (JSONException ex) {
				result = new PluginResult(Status.JSON_EXCEPTION, ex.getMessage());
			}
		}else if(action.equals(ACTION_GET_ALL)){
			try {
				result = new PluginResult(Status.OK, this.getAll());
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
	
	protected JSONArray getInbox() throws JSONException {
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        
        Context context = CyranoApplication.getAppContext();
        Cursor cursor = context.getContentResolver().query(uriSMSURI, null, null, null,null);
                
        JSONArray response = new JSONArray();
        
        while(cursor.moveToNext()){
        	JSONObject messageObj = new JSONObject();
        				
			for(int idx=0;idx<cursor.getColumnCount();idx++){
				messageObj.put(cursor.getColumnName(idx), cursor.getString(idx));
			}
			response.put(messageObj);
        }
        return response;
	}
	
	protected JSONArray getSent() throws JSONException {
        Uri uriSMSURI = Uri.parse("content://sms/sent");
        
        Context context = CyranoApplication.getAppContext();
        Cursor cursor = context.getContentResolver().query(uriSMSURI, null, null, null,null);
                
        JSONArray response = new JSONArray();
        
        while(cursor.moveToNext()){
        	JSONObject messageObj = new JSONObject();
        				
			for(int idx=0;idx<cursor.getColumnCount();idx++){
				messageObj.put(cursor.getColumnName(idx), cursor.getString(idx));
			}
			response.put(messageObj);
        }
        return response;
	}
	
	protected JSONArray getAll() throws JSONException {
        JSONArray array = new JSONArray();
        
        JSONArray inbox = this.getSent();
        JSONArray sent = this.getInbox();
        
        for(int i = 1; i < sent.length(); i++){
        	array.put(sent.get(i));
        }
        
        for(int i = 1; i < inbox.length(); i++){
        	array.put(inbox.get(i));
        }
        
        return array;
	}

}