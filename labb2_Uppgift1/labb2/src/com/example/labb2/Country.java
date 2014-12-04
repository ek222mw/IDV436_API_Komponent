package com.example.labb2;

public class Country {
	private long id;
	  private String country;
	  private String year;

	  public Country(long id, String c, String y){
		  this.id = id;
		  country = c;
		  year = y;
	  }
	  
	  public Country(){
		
	  }
	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getTask() {
	    return country;
	  }

	  public void setTask(String task) {
	    this.country = task;
	  }
	  
	  public String getYear()
	  {
		  return year;
	  }
	  
	  public void setYear(String year)
	  {
		  this.year = year;
	  }

	  @Override
	  public String toString() {
	    return country +" "+ year;
	  }
}
