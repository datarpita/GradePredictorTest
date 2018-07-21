package com.azureml.studentperf.main.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.azureml.studentperf.main.service.PredictResultService;
import com.azureml.studentperf.pojo.multiple.Input2;
import com.azureml.studentperf.pojo.multiple.InputMultipleJson;
import com.azureml.studentperf.pojo.multiple.Multiplesub;
import com.azureml.studentperf.pojo.multiple.OutputMultipleJson;
import com.azureml.studentperf.pojo.multiple.Student;
import com.azureml.studentperf.pojo.single.Cand;
import com.azureml.studentperf.pojo.single.Input1;
import com.azureml.studentperf.pojo.single.InputJson;
import com.azureml.studentperf.pojo.single.Inputs;
import com.azureml.studentperf.pojo.single.OutputJson;
import com.azureml.studentperf.pojo.single.Singlesub;
import com.google.gson.Gson;

@Controller
public class PredictResultController {
	
	@Autowired
	PredictResultService servObj;
	
	@PostMapping(value="/singlesub",produces = { MediaType.APPLICATION_JSON_VALUE })
	public void predictSingleSubject(HttpServletRequest request, HttpServletResponse response){
		
		double tempProbability;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String json = "";
			if(br != null){
			    json = br.readLine();
			}
			System.out.println("Json=="+json);
			
			Singlesub candObj = new Gson().fromJson(json, Singlesub.class);
			
			List<List<String>> tempPredictResults = prepareInputsAndCallService(candObj);
			List<List<String>> predictResults = new ArrayList<List<String>>(); 
			for (List<String> singlePredList : tempPredictResults){			
				System.out.println("Scored Label: " + singlePredList.get(0));
				System.out.println("Scored Probability: " + singlePredList.get(1));	
				
				tempProbability = Math.floor(Double.valueOf(singlePredList.get(1)) * 100.0);
				List<String> tempList = new ArrayList<String>();
				tempList.add(0, singlePredList.get(0));
				tempList.add(1, String.valueOf(new Double(tempProbability).intValue()));
				predictResults.add(tempList);
			}
			
			
			String responseJson = new Gson().toJson(predictResults);
			System.out.println("Final Response Json:  " + responseJson);
			
			response.setContentType("application/json");
			response.setHeader("Cache-control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");			
			response.getWriter().println(responseJson);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	private List<List<String>> prepareInputsAndCallService(Singlesub candObj){
		String[] columnNames= {"age", "famrel", "absences", "G1", "G2"}; 
		//String[] values = {"11", "10", "0", "1", "3"};
		List<List<String>> finalValueList = new ArrayList<List<String>>();
		List<Cand> candList = candObj.getCands();
		List<String> valueList = new ArrayList<String>();
		for (Cand canObj : candList){
			valueList.add(canObj.getAge());
			valueList.add(canObj.getFamrel());
			valueList.add(canObj.getAbsences());
			valueList.add(canObj.getGrade1());
			valueList.add(canObj.getGrade2());
			finalValueList.add(valueList);
		}	
		Input1 input1 = new Input1();
		input1.setColumnNames(Arrays.asList(columnNames));
		input1.setValues(finalValueList);
		Inputs inputs = new Inputs();
		inputs.setInput1(input1);
		InputJson reqJson = new InputJson();
		reqJson.setInputs(inputs);
		
		OutputJson results = servObj.predictResult(reqJson);
		List<List<String>> predictResults = results.getResults().getOutput1().getValue().getValues();
		
		return predictResults;
	}
	
	
	
	@PostMapping(value="/multiplesub",produces = { MediaType.APPLICATION_JSON_VALUE })
	public void predictMultipleSubject(HttpServletRequest request, HttpServletResponse response){
		double tempScore1;
		double tempScore2;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String json = "";
			if(br != null){
			    json = br.readLine();
			}
			System.out.println("Json=="+json);
			
			Multiplesub studObj = new Gson().fromJson(json, Multiplesub.class);
			
			List<List<String>> tempPredictResults = prepareInputsAndCallServiceForMultiple(studObj);
			List<List<String>> predictResults = new ArrayList<List<String>>(); 
			for (List<String> multiplePredList : tempPredictResults){			
				System.out.println("Scored Label1: " + multiplePredList.get(0));
				System.out.println("Scored Label2: " + multiplePredList.get(1));	
				tempScore1 = Math.round(Double.valueOf(multiplePredList.get(0)) * 100.0) / 100.0;
				tempScore2 = Math.round(Double.valueOf(multiplePredList.get(1)) * 100.0) / 100.0;
				List<String> tempList = new ArrayList<String>();
				tempList.add(0, new Double(tempScore1).toString());
				tempList.add(1, new Double(tempScore2).toString());
				predictResults.add(tempList);
			}
			
			
			String responseJson = new Gson().toJson(predictResults);
			System.out.println("Final Response Json:  " + responseJson);
			
			response.setContentType("application/json");
			response.setHeader("Cache-control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");			
			response.getWriter().println(responseJson);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	private List<List<String>> prepareInputsAndCallServiceForMultiple(Multiplesub studObj){
		String[] columnNames1= {"age", "famrel", "goout", "absences", "G1", "G2"}; 
		String[] columnNames2= {"traveltime", "famrel", "freetime", "goout", "Dalc", "G1", "G2"}; 
		
		List<List<String>> finalValueList1 = new ArrayList<List<String>>();
		List<List<String>> finalValueList2 = new ArrayList<List<String>>();
		List<Student> studentList = studObj.getStudents();
		List<String> valueList1 = new ArrayList<String>();
		List<String> valueList2 = new ArrayList<String>();
		for (Student tempStudObj : studentList){
			valueList1.add(tempStudObj.getAge());
			valueList1.add(tempStudObj.getFamrel());
			valueList1.add(tempStudObj.getGoout());
			valueList1.add(tempStudObj.getAbsences());
			valueList1.add(tempStudObj.getGradem1());
			valueList1.add(tempStudObj.getGradem2());
			finalValueList1.add(valueList1);
			valueList2.add(tempStudObj.getTraveltime());
			valueList2.add(tempStudObj.getFamrel());
			valueList2.add(tempStudObj.getFreetime());
			valueList2.add(tempStudObj.getGoout());
			valueList2.add(tempStudObj.getDalc());
			valueList2.add(tempStudObj.getGradep1());
			valueList2.add(tempStudObj.getGradep2());
			finalValueList2.add(valueList2);
		}	
		com.azureml.studentperf.pojo.multiple.Input1 input1 = new com.azureml.studentperf.pojo.multiple.Input1();
		input1.setColumnNames(Arrays.asList(columnNames1));
		input1.setValues(finalValueList1);
		
		Input2 input2 = new Input2();
		input2.setColumnNames(Arrays.asList(columnNames2));
		input2.setValues(finalValueList2);
		
		com.azureml.studentperf.pojo.multiple.Inputs inputs = new com.azureml.studentperf.pojo.multiple.Inputs();
		inputs.setInput1(input1);
		inputs.setInput2(input2);
		InputMultipleJson reqJson = new InputMultipleJson();
		reqJson.setInputs(inputs);
		
		OutputMultipleJson results = servObj.predictResultForMultiple(reqJson);
		List<List<String>> predictResults = results.getResults().getOutput1().getValue().getValues();
		
		return predictResults;
	}

}
