package com.sac4u;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.rowset.internal.Row;


public class Apps {
	
	
	public String insertApps(String jsonBody){
		MySqlQuery msq = new MySqlQuery();
		JSONArray appsArray;
		try {
			appsArray = new JSONObject(jsonBody).getJSONObject("requestBody").getJSONArray("apps");
		} catch (JSONException e) {
			e.printStackTrace();
			return e.getMessage();//"RequestBody,apps fiels are mandatory.";
		}
		for(int i=0;i<appsArray.length();i++){
			String query;
			try {
				JSONObject app = appsArray.getJSONObject(i);
				query = msq.insertQuery.replace("ph1",app.getString("appName"))
						.replace("ph2", app.getString("category"))
						.replace("ph3", app.getString("downloadLink"))
						.replace("ph4", app.getString("r_code"))
						.replace("ph5", app.getString("iconPath"))
						.replace("ph6", new Date().toString());
			} catch (JSONException e) {
				e.printStackTrace();
				return e.getMessage();
			}
			System.out.println(msq.exModifyQuery(query));
		}
		return "D";
	}
	
	public JSONObject getAllApps(){
		JSONObject result = new JSONObject();
		JSONObject payloadObj = new JSONObject();
		MySqlQuery msq = new MySqlQuery();
		ResultSet rs = msq.exSelectQuery(msq.selectQuery);
		try {
            JSONArray appArray = new JSONArray();
            int count = 0;
			while(rs.next()){
				JSONObject appObj = new JSONObject();
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
					appObj.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));
				}
				appArray.put(appObj);
				count++;
			}
			payloadObj.put("count", count);
			payloadObj.put("apps",appArray);
			result.put("status","success");
			result.put("payload", payloadObj);
		} catch (JSONException | SQLException e) {
			e.printStackTrace();
			try {
				result.put("status", "failure");
				result.put("reason", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	public JSONObject getApp(int id){
		JSONObject result = new JSONObject();
		JSONObject payloadObj = new JSONObject();
		MySqlQuery msq = new MySqlQuery();
		ResultSet rs = msq.exSelectQuery(msq.selectQuery+" where id="+id);
		try {
            JSONArray appArray = new JSONArray();
            int count = 0;
			while(rs.next()){
				JSONObject appObj = new JSONObject();
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
					appObj.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));
				}
				appArray.put(appObj);
				count++;
			}
			payloadObj.put("count", count);
			payloadObj.put("apps",appArray);
			result.put("status","success");
			result.put("payload", payloadObj);
		} catch (JSONException | SQLException e) {
			e.printStackTrace();
			try {
				result.put("status", "failure");
				result.put("reason", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	public String deleteApp(int id){
		MySqlQuery msq = new MySqlQuery();
		JSONObject deletedApp = getApp(id);
		System.out.println(deletedApp);
		if(!msq.exModifyQuery(msq.deleteQuery.replace("<condition>", "id="+id)))
			return "{\"status\":\"success\",\"app\":"+deletedApp.toString()+"}";
		else
			return "{\"status\":\"failure\",\"\":"+deletedApp.toString()+"}";
	}
}
