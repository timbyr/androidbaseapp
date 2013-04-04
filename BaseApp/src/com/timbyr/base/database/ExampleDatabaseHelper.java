package com.timbyr.base.database;

import com.timbyr.lib.database.AbstractDatabaseHelper;

import android.content.Context;

public class ExampleDatabaseHelper extends AbstractDatabaseHelper
{
	// The Android's default system path of your application database.
	private static String DB_NAME = "Something";
	private static final int DATABASE_VERSION = 1;
	
	private static ExampleDatabaseHelper INSTANCE = null;
	
	public static ExampleDatabaseHelper getInstance(Context context){
		if (INSTANCE == null){
			INSTANCE = new ExampleDatabaseHelper(context);
		}
		return INSTANCE;
	}
	
	private ExampleDatabaseHelper(Context context)
	{
		super(context, DB_NAME, null, DATABASE_VERSION);
	}
}