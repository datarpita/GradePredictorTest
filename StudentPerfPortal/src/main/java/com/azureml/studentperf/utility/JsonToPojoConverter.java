package com.azureml.studentperf.utility;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

public class JsonToPojoConverter {
	public static void convert2JSON(URL inputJson, File outputPojoDirectory, String packageName, String className) throws IOException{  
		JCodeModel codeModel = new JCodeModel();  
		URL source = inputJson;  
		GenerationConfig config = new DefaultGenerationConfig() {  
			@Override  
			public boolean isGenerateBuilders() { // set config option by overriding method  
				return true;  
			}  
			public SourceType getSourceType(){  
				return SourceType.JSON;  
			}  
		};  
		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());  
		mapper.generate(codeModel, className, packageName, source);  
		codeModel.build(outputPojoDirectory);  
	}

	public static void main(String[] args) {
		String packageName="com.azureml.studentperf.pojo.multiple";  
		Path currentRelativePath = Paths.get("");
		String strCurRelPath = currentRelativePath.toAbsolutePath().toString();	         	        
		File outputPojoDirectory=new File(strCurRelPath+File.separator+"src/main/java");  
		outputPojoDirectory.mkdirs();  
		//File inputJson= new File(strCurRelPath+File.separator+"src/main/java/com/azureml/studentperf/utility/inputJson.json");
		//File outputJson= new File(strCurRelPath+File.separator+"src/main/java/com/azureml/studentperf/utility/outputJson.json");
		//File singlesubJson= new File(strCurRelPath+File.separator+"src/main/java/com/azureml/studentperf/utility/singlesub.json");
		File inputMultipleJson= new File(strCurRelPath+File.separator+"src/main/java/com/azureml/studentperf/utility/inputMultipleJson.json");
		File outputMultipleJson= new File(strCurRelPath+File.separator+"src/main/java/com/azureml/studentperf/utility/outputMultipleJson.json");
		File multiplesubJson= new File(strCurRelPath+File.separator+"src/main/java/com/azureml/studentperf/utility/multiplesub.json");
		try {  	              
			//convert2JSON(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", ""));
			//convert2JSON(outputJson.toURI().toURL(), outputPojoDirectory, packageName, outputJson.getName().replace(".json", ""));
			convert2JSON(inputMultipleJson.toURI().toURL(), outputPojoDirectory, packageName, inputMultipleJson.getName().replace(".json", ""));
			convert2JSON(outputMultipleJson.toURI().toURL(), outputPojoDirectory, packageName, outputMultipleJson.getName().replace(".json", ""));
			//convert2JSON(singlesubJson.toURI().toURL(), outputPojoDirectory, packageName, singlesubJson.getName().replace(".json", ""));
			convert2JSON(multiplesubJson.toURI().toURL(), outputPojoDirectory, packageName, multiplesubJson.getName().replace(".json", ""));
		} catch (IOException e) {  
			// TODO Auto-generated catch block  
			System.out.println("Encountered issue while converting to pojo: "+e.getMessage());  
			e.printStackTrace();  
		} 
	}
}
