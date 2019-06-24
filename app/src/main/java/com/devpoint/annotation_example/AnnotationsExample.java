package com.devpoint.annotation_example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.devpoint.annotation_example.annotations.field.Car;
import com.devpoint.annotation_example.annotations.field.JsonSerializeException;
import com.devpoint.annotation_example.annotations.field.JsonSerializer;
import com.devpoint.annotation_example.annotations.methodExample.BusinessLogic;
import com.devpoint.annotation_example.annotations.methodExample.TodoReport;

import java.lang.reflect.InvocationTargetException;


public class AnnotationsExample extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Car car = new Car("Ford", "F150", "2018");
		JsonSerializer serializer = new JsonSerializer();
		try {
			serializer.serialize(car);
		} catch (JsonSerializeException e) {
			e.printStackTrace();
		}


		BusinessLogic businessLogic = new BusinessLogic(5);
		try {
			TodoReport.getTodoReportForBusinessLogic(businessLogic);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}


	}


}
