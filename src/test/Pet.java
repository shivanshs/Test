package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Pet {

	public final String POST_PARAMS = "{\"id\":9999,\"category\":{\"id\":1,\"name\":\"doge\"},\"name\":\"bungus\",\"photoUrls\":[],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";
	public final String PUT_PARAMS = "{\"id\":9999,\"category\":{\"id\":1,\"name\":\"doge\"},\"name\":\"Changedbungus\",\"photoUrls\":[],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";

	public void getPet(int id, String postorputordel) throws IOException {
		URL url = new URL("https://petstore.swagger.io/v2/pet/" + id);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);

		int status = con.getResponseCode();
		System.out.println("GET Response Code :  " + status);
		System.out.println("GET Response Message : " + con.getResponseMessage());

		if (postorputordel.equalsIgnoreCase("del")) {
			if (status == 404)
				System.out.println("----> The record deletion was successful");
			else
				System.out.println("----> The record deletion was not successful");
		}

		if (postorputordel.equalsIgnoreCase("post") | postorputordel.equalsIgnoreCase("put")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			con.disconnect();
			System.out.println("Record :  " + content);

			if (status == 200)
				System.out.println("----> The record has been successfully retrieved with status code " + status);
			else
				System.out.println("----> Unsuccessful Retrieval");

			if (postorputordel.equalsIgnoreCase("post")) {
				if (content.toString().contentEquals(POST_PARAMS))
					System.out.println("----> The record was created with correct data");
				else
					System.out.println("----> The record was created with incorrect data");
			} else if (postorputordel.equalsIgnoreCase("put")) {
				if (content.toString().contentEquals(PUT_PARAMS))
					System.out.println("----> The record was updated with new name");
				else
					System.out.println("----> The record was updated with new name");
			}
		}
	}

	public void postPet() throws IOException {

		URL obj = new URL("https://petstore.swagger.io/v2/pet");
		HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		postConnection.setRequestMethod("POST");
		postConnection.setRequestProperty("Content-Type", "application/json");
		postConnection.setDoOutput(true);
		OutputStream os = postConnection.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();

		int responseCode = postConnection.getResponseCode();
		System.out.println("POST Response Code :  " + responseCode);
		System.out.println("POST Response Message : " + postConnection.getResponseMessage());
		if (responseCode == 200) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			System.out.println("Record is created with details:  " + response);

		} else {
			System.out.println("Record was not created successfully");
		}
	}

	public void putPet() throws IOException {

		URL obj = new URL("https://petstore.swagger.io/v2/pet");
		HttpURLConnection putConnection = (HttpURLConnection) obj.openConnection();
		putConnection.setRequestMethod("PUT");
		putConnection.setRequestProperty("Content-Type", "application/json");
		putConnection.setDoOutput(true);
		OutputStream os = putConnection.getOutputStream();
		os.write(PUT_PARAMS.getBytes());
		os.flush();
		os.close();
		int responseCode = putConnection.getResponseCode();
		System.out.println("PUT Response Code :  " + responseCode);
		System.out.println("PUT Response Message : " + putConnection.getResponseMessage());
		if (responseCode == 200) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(putConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			System.out.println("Record is updated with details:  " + response);
		} else {
			System.out.println("Record was not created successfully");
		}
	}

	public void delPet(int id) throws IOException {

		URL obj = new URL("https://petstore.swagger.io/v2/pet/" + id);
		HttpURLConnection delConnection = (HttpURLConnection) obj.openConnection();
		delConnection.setRequestMethod("DELETE");
		delConnection.setRequestProperty("Content-Type", "application/json");
		int responseCode = delConnection.getResponseCode();
		System.out.println("DELETE Response Code :  " + responseCode);
		System.out.println("DELETE Response Message : " + delConnection.getResponseMessage());
		if (responseCode == 200)
			System.out.println("----> The record has been successfully deleted with status code " + responseCode);
		else
			System.out.println("----> Unsuccessful deletion");

	}
}
