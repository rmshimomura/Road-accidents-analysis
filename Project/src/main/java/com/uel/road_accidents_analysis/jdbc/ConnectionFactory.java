package com.uel.road_accidents_analysis.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public abstract class ConnectionFactory {

    private static ConnectionFactory instance = null;
    protected static String propertiesFile = "application.properties";
    private static String dbServer;

    protected ConnectionFactory() {
    }

    public static ConnectionFactory getInstance()  throws IOException {
        if (instance == null) {
            Properties properties = new Properties();

            try {
                InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream(propertiesFile);
                properties.load(input);
                dbServer = properties.getProperty("server");
                System.out.println("dbServer: " + dbServer);
            } catch (IOException e) {
                System.err.println("Erro ao carregar arquivo de propriedades" + e.getMessage());
                throw new IOException("Erro ao carregar arquivo de propriedades");
            }

            if(getdbServer().equals("postgresql")) {
                instance = new PostgresConnectionFactory();
            } else {
                throw new IOException("Banco de dados não suportado");
            }

        }
        return instance;
    }

    public static String getdbServer() {
        return dbServer;
    }

    public abstract Connection getConnection() throws IOException, SQLException, ClassNotFoundException;

}
