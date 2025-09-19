package com.dsquare.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;

import lombok.Setter;
import okhttp3.Headers;
import okhttp3.OkHttpClient;

public class FootballApi {

	@Value("${FOOTBALL_API_KEY}")
	private String apiKey;
	
	public FootballApi() {
		var client = new OkHttpClient();
		var myHeaders = new Headers.Builder();
		myHeaders.add("x-rapidapi-key", apiKey);
		myHeaders.add("x-rapidapi-host", "v3.football.api-sports.io");

		try {
			client.newCall(new okhttp3.Request.Builder().url("https://v3.football.api-sports.io/").get().headers(myHeaders.build()).build()).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
