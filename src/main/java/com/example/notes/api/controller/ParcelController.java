package com.example.notes.api.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.notes.api.entity.Voucher;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping(path = "/parcel/v1")
public class ParcelController {
	
	@GetMapping(path = "/volume")
	public double getVolume(
			@RequestParam double weight, 
			@RequestParam double height, 
			@RequestParam double width, 
			@RequestParam double lenght,
			@RequestParam String voucherCode) throws IOException, InterruptedException{
		
	
	    double discount = getDiscount(voucherCode);
	     
		double cost = 0;
		if(weight > 50) {
			cost = 0.00;
		} else if(weight > 10) {
			cost = 20 * weight;
		} 
		
		double volume = height * width * lenght;
		
		if(volume < 1500) {
			cost = 0.03 * volume;
		} else if(volume > 1500 &&  volume < 2500) {
			cost = 0.04 * volume;
		} else {
			cost = 0.05 * volume;
		}
		
		double discountValue = cost * discount/100;
		
		return (cost - discountValue) ;
	}
	
	private static double getDiscount(String voucherCode) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://mynt-exam.mocklab.io/voucher/MYNT?key=apikey"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new GsonBuilder().create();
        List<Voucher> voucher = gson.fromJson(response.body(), new TypeToken<List<Voucher>>(){}.getType());
        return voucher.get(0).getDiscount();
		
	}

}
