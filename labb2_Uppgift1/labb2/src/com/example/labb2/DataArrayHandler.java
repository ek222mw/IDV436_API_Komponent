package com.example.labb2;

import java.util.ArrayList;

public class DataArrayHandler {

private static ArrayList<String> arrayList;
	
	
	public static ArrayList<String> GetArrayList(){
		
		return arrayList;
	}
	
	public static void SetArrayList(ArrayList<String> array){
		
			arrayList = array;
		
	}
	
}
