package com.azureml.studentperf.main.service;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.azureml.studentperf.pojo.multiple.InputMultipleJson;
import com.azureml.studentperf.pojo.multiple.OutputMultipleJson;
import com.azureml.studentperf.pojo.single.InputJson;
import com.azureml.studentperf.pojo.single.OutputJson;
import com.google.gson.Gson;

@Service
public class PredictResultService {
	
	/*public static void main(String[] args) {
		
		String[] columnNames= {"G2", "G1","failures","studytime","Medu"}; 
		String[] values = {"11", "10", "0", "1", "3"};
		List<String> valueList = Arrays.asList(values);
		List<List<String>> finalValueList = new ArrayList<List<String>>();
		finalValueList.add(valueList);
		Input1 input1 = new Input1();
		input1.setColumnNames(Arrays.asList(columnNames));
		input1.setValues(finalValueList);
		Inputs inputs = new Inputs();
		inputs.setInput1(input1);
		InputJson reqJson = new InputJson();
		reqJson.setInputs(inputs);
		PredictResultService servObj = new PredictResultService();
		OutputJson results = servObj.predictResult(reqJson);
		List<List<String>> predictResults = results.getResults().getOutput1().getValue().getValues();
		for (List<String> singlePredList : predictResults){			
				System.out.println("Scored Label: " + singlePredList.get(0));
				System.out.println("Standard deviation: " + singlePredList.get(1));		
			
		}
	}*/
	


	public OutputJson predictResult(InputJson requestJson){
		
		String strJson = new Gson().toJson(requestJson);
		System.out.println(strJson);

		final String uri = "https://ussouthcentral.services.azureml.net/workspaces/3526fde061f043b68a26f61b5405e501/services/fa2ba9639d674fdb98feea4bfddad185/execute?api-version=2.0&details=true";
		final String apikey = "4mzOj9RUxF6GxRKaGDWKK7uYDtJmsm2h40qD+A2XuaiQtfU7OwXIkQ0UNPuh83fOdGzohJ51MkO+FqaMNmalFw==";
		RestTemplate restTemplate = new RestTemplate();


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ArrayList<MediaType> listOfMediaTypes = new ArrayList<MediaType>();
		listOfMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		headers.setAccept(listOfMediaTypes);
		headers.set("Authorization", ("Bearer "+apikey));

		HttpEntity<InputJson> request = new HttpEntity<InputJson>(requestJson, headers);

		OutputJson finalResult = restTemplate.postForObject(uri, request, OutputJson.class);


		System.out.println(finalResult.getResults().getOutput1().getValue().getValues());
		return finalResult;
	}
	
	
	public OutputMultipleJson predictResultForMultiple(InputMultipleJson requestJson){
		
		String strJson = new Gson().toJson(requestJson);
		System.out.println(strJson);

		final String uri = "https://ussouthcentral.services.azureml.net/workspaces/3526fde061f043b68a26f61b5405e501/services/99cef29be22945f6bf2230109c0137ac/execute?api-version=2.0&details=true";
		final String apikey = "gCIrK9GBF/jG+SpGwtZpiH5B+sVis0Wt+yEgU0lLWzRyIEa0NonXdGMPQKPlE5uPrIMU4F+SQSEoVwPyX25edA==";
		RestTemplate restTemplate = new RestTemplate();


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ArrayList<MediaType> listOfMediaTypes = new ArrayList<MediaType>();
		listOfMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		headers.setAccept(listOfMediaTypes);
		headers.set("Authorization", ("Bearer "+apikey));

		HttpEntity<InputMultipleJson> request = new HttpEntity<InputMultipleJson>(requestJson, headers);

		OutputMultipleJson finalResult = restTemplate.postForObject(uri, request, OutputMultipleJson.class);


		System.out.println(finalResult.getResults().getOutput1().getValue().getValues());
		return finalResult;
	}


}
