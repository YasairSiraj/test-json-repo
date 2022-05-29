package org.csvtojson.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class GetEmployeeDetails {
	public static void main(String[] args) throws IOException, JSONException {
//		Scanner sc = new Scanner(new File("/home/qualian/employee.csv"));
//		sc.useDelimiter(",");
//		while (sc.hasNext()) {
//			String string = (String) sc.next();
//			System.out.println(string);
//		}
		String line ="";
		List<Employee> employee = new ArrayList<Employee>();
		BufferedReader br = new BufferedReader(new FileReader("Employee_table.csv"));
		int i=0;
		
		while ((line = br.readLine())!= null) {
			
			String[] emp = line.split(",");
			Employee empObj = new Employee();
			empObj.setEmployeeId(Long.parseLong(emp[0]));
			empObj.setEmployeeName(emp[1]);
			employee.add(empObj);
			i++;
		}
		String jsonContent ="[/n";
		JSONArray arr = new JSONArray();
		for (Employee employee2 : employee) {
			JSONObject obj = new JSONObject();
			obj.put("EmployeeId", employee2.getEmployeeId());
			obj.put("EmployeeName", employee2.getEmployeeName());	
			arr.put(obj);
		}
		FileWriter output = new FileWriter("output.json");
		output.write(arr.toString());
		output.close();
		System.out.println(jsonContent);
	}
}
