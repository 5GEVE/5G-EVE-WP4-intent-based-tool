package com.wings.intentbased.Intent.yamlFiles;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import blueprints.AGVBp;

public class AGVToYaml {

	
	public AGVToYaml() {
		
	}
	
	public void exportToYaml(AGVBp agv) {
		
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		try {
			AGVYaml agvBP = mapper.readValue(new File("C:\\Users\\GL-WINGS\\eclipse-workspace\\Intent\\ASTI_BP.yaml"), AGVYaml.class);
			for(int i = 0; i < agvBP.getParameters().length; i++)
			{
				if(agvBP.getParameters()[i].toString().toLowerCase().contains("no-of-robots=null"))
					agvBP.getParameters()[i].replace("no-of-robots", agv.getNumOfAgvs());
			}
			mapper.writeValue(new File("C:\\Users\\GL-WINGS\\eclipse-workspace\\Intent\\AGV_VSD.yaml"), agvBP);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
