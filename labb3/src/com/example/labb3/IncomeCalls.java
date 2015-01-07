package com.example.labb3;

public class IncomeCalls {

	private long m_id;
	private String m_number;
	
	@Override
	public String toString(){
		return m_number;
	}
	
	
	public void setId(long a_id){
		this.m_id = a_id;
	}
	
	public long getId(){
		return m_id;
	}
	
	
	public String getNumber(){
		return m_number;
	}
	
	public void setNumber(String a_number){
		this.m_number = a_number;
	}
	
	

	
}
