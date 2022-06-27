package com.infybuzz.app.reader;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class FirstItemReader implements ItemReader<Integer>{

	List<Integer> list=Arrays.asList(1,2,3,4,5,6,7,8,9,10); 
	int i=0;

	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		System.out.println("inside item reader");
		Integer item ;
		if(i<list.size()) {   //we applied list condition to avoid index out of bound exception 
			item=list.get(i);
			i++;
			return item;
		}
		i=0;   //i is at class level and we are again setting it to 0 for rerunning the application   
		return null ;    //if i>list.size() then it will come out of loop and return null means we are 
                        //		telling item reader that there are no more records to read 
		
	} 
	
}
