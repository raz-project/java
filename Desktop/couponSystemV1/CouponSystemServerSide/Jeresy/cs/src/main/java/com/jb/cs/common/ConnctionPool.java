package com.jb.cs.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;





public class ConnctionPool {
	
	/*
	 * Singleton is a design pattern which allows only a single instantiation of a class.
	 */
	
    //Fields
	//Static filed who make this class a singleton 
	private static ConnctionPool instance;
	// Url + User name + Pass make connection to the MYSQL
	private static String DB_URL = "jdbc:mysql://localhost:3306/razi?serverTimezone=UTC" ;
	
	private static String USER_NAME = "root" ;
	
	private static String PASS = "Razi!@5624" ;
	
	private static final int MAX_Connction = 10;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Make a the Connection Pool visible in all package this how we can get connection for all 
	 * we want and what make this class for singleton
	 * @return create new connection for use
	 * @throws SystemMalfunctionException
	 */
	public static  synchronized ConnctionPool getInstance() throws SystemMalfunctionException {
		if (instance == null) {
			instance = new ConnctionPool();
		}
		return instance;
	}

	
	/**
	 * Take the number of size of correct LinkedBlockingQueue and manage queue
	 * @throws SystemMalfunctionException
	 */
	private BlockingQueue<Connection> connections;

	private ConnctionPool() throws SystemMalfunctionException {

		// This was LinkedBlockingDeque, and should be LinkedBlockingQueue.
		connections = new LinkedBlockingQueue<Connection>(MAX_Connction);

		int remainingCapacity = connections.remainingCapacity();

		for (int i = 0; i < remainingCapacity; ++i) {
			connections.offer(createConnction());
		}
	}
	
	/**
	 * @return A connection from the pool.
	 * @throws SystemMalfunctionException
	 */
	public synchronized Connection getConnction() throws SystemMalfunctionException {
		try {
			return connections.take();
		} catch (InterruptedException e) {
			throw new SystemMalfunctionException("fatal wait for connction");
		}
	}

	/**
	 * Returns a given connection to the queue.
	 * 
	 * @param connection The given connection you'd like to insert back into the
	 *                   queue.
	 * @throws SystemMalfunctionException
	 */
	public synchronized void returnConnction(Connection connection) throws SystemMalfunctionException {
		try {
			connections.put(connection);
		} catch (InterruptedException e) {
			throw new SystemMalfunctionException("fatal no connction yet");
		}
	}

	/**
	 * Retrieves and removes all the elements of the maintained queue, and closes
	 * all resources.
	 * 
	 * @throws SystemMalfunctionException
	 */
	public void closeAllConnction() throws SystemMalfunctionException {
		Connection connection;
		while ((connection = connections.poll()) != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new SystemMalfunctionException("fatal unable to close connction");
			}
		}

	}

	/**
	 * Create take a connection for db manipulation
	 * @return Connection
	 * @throws SystemMalfunctionException
	 */
	private static Connection createConnction() throws SystemMalfunctionException {
		try {
			return DriverManager.getConnection(DB_URL,USER_NAME,PASS);
		} catch (SQLException e) {
			throw new SystemMalfunctionException("fatal unable to create connction !" +e.getMessage());

		}
	}

}
