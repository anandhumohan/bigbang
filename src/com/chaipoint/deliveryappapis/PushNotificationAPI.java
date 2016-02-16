package com.chaipoint.deliveryappapis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;

import com.chaipoint.pushnotification.PushNotification;
import com.squareup.okhttp.Response;

@Path("/notification")
public class PushNotificationAPI {
	@Path("/register")
	@POST
	public Response gsmRegister() {
		
		String regId = new PushNotification().getRegId();
		
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // substitute 12345 with the real Google API Project number
        final String regid = gcm.register("12345");
        installation.setDeviceToken(regid);
        
		return null;
	}

	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response gsmSender(String jsonString) {

		final String API_KEY = "API_KEY";
		try

		{
			Object jGcmData = null;
			// Create connection to send GCM Message request.
			URL url = new URL("https://android.googleapis.com/gcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", "key=" + API_KEY);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// Send GCM message content.
			OutputStream outputStream = conn.getOutputStream();
			
			outputStream.write(jGcmData.toString().getBytes());

			// Read GCM response.
			InputStream inputStream = conn.getInputStream();
			String resp = IOUtils.toString(inputStream);
			System.out.println(resp);
			System.out.println("Check your device/emulator for notification or logcat for "
					+ "confirmation of the receipt of the GCM message.");
		} catch (

		IOException e)

		{
			System.out.println("Unable to send GCM message.");
			System.out.println("Please ensure that API_KEY has been replaced by the server "
					+ "API key, and that the device's registration token is correct (if specified).");
			e.printStackTrace();
		}
		return null;
	}

}
