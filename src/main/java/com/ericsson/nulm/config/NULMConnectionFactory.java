package com.ericsson.nulm.config;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public class NULMConnectionFactory {
	
	Connection connection;
	
	public Connection getConnection(){
		try {
			if(connection == null || connection.isClosed()){
				String CLASSJDBC_NAME = "oracle.jdbc.driver.OracleDriver";
				Class.forName(CLASSJDBC_NAME);
				connection = DriverManager.getConnection("jdbc:oracle:thin:@dselilx6827.dev.sdt.ericsson.se:1521:xcom", "bharti_ossrc_stage", "bharti_ossrc_stage");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	//@Bean
	public static DataSource datasource() throws Exception {
		Hashtable<String, String> env = new Hashtable<String, String>();
		DataSource dataSource = null;
		try {
			System.out.println("Before setting initial context");

			Properties prop = new Properties();
			
			/*String CLASSJDBC_NAME = "oracle.jdbc.driver.OracleDriver";
			Class.forName(CLASSJDBC_NAME);
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@dselilx6827.dev.sdt.ericsson.se:1521:xcom", "bharti_ossrc_stage", "bharti_ossrc_stage");
			*/
			InputStream input = NULMConnectionFactory.class.getClassLoader()
					.getResourceAsStream("config.properties");
			prop.load(input);
			env.put(Context.INITIAL_CONTEXT_FACTORY, prop.getProperty("initialContextFactory"));
			env.put(Context.PROVIDER_URL, prop.getProperty("providerUrl"));
			InitialContext ctx = new InitialContext(env);
			/*GraniteSession graniteSession = null;
			GraniteServiceFactory graniteServiceFactory = null;
			try {
				graniteServiceFactory = new EjbGraniteServiceFactory(ctx);

				GraniteCredentials graniteCredentials = new GraniteCredentials();
				graniteCredentials.setUserName(prop.getProperty("UserName"));
				graniteCredentials.setPassword(prop.getProperty("Password"));
				graniteCredentials.setDatabaseHostName(prop.getProperty("DBHostName"));
				graniteCredentials.setDatabaseName(prop.getProperty("DataBaseName"));

				graniteSession = graniteServiceFactory.loginWithGraniteCredentials(graniteCredentials);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}*/
			dataSource = (DataSource) ctx
					.lookup(prop.getProperty("config.jndi"));
			System.out.println("Found datasource: " + dataSource);
			// return dataSource;
		} catch (NamingException ne) {
			System.out.println("Error in getting DataSource:" + ne);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception");
		}
		if (dataSource == null) {
			throw new Exception("DataSource Not Found");
		} else
			return dataSource;

	}
}
