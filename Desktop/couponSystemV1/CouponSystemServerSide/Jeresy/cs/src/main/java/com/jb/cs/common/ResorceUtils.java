package com.jb.cs.common;

import java.sql.ResultSet;
import java.sql.Statement;

/*This final class make it possible to use only once*/
public final class ResorceUtils {
	
	
	
	private ResorceUtils() {
		
		throw new AssertionError("non instant able");
	}
	
	
	/**
	 * This function close statements
	 * @param statements
	 * @throws SystemMalfunctionException
	 */
	public static void close(Statement ... statements) throws SystemMalfunctionException {
		try {
			for (int i = 0; i < statements.length; i++) {
				if (statements[i]!= null ) {
					statements[i].close();
					
				}
				
			}
		} catch (Exception e) {
			throw new SystemMalfunctionException(" unable to close satment" + e.getMessage());
		}
	}
	
	
	/**
	 * This function close resultset
	 * @param resultSets
	 * @throws SystemMalfunctionException
	 */
	public static void close(ResultSet ... resultSets) throws SystemMalfunctionException {
		try {
			for (int i = 0; i < resultSets.length; i++) {
				if (resultSets[i]!= null ) {
					resultSets[i].close();
					
				}
				
			}
		} catch (Exception e) {
			throw new SystemMalfunctionException(" unable to close satment" + e.getMessage());
		}
	}
	
	
	

}
