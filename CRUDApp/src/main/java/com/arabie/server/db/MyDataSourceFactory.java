package com.arabie.server.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyDataSourceFactory {
    private static MysqlDataSource mysqlDS = null;

    private MyDataSourceFactory() {

    }

    public static DataSource getMySQLDataSource() {

        if (mysqlDS == null) {

            Properties props = new Properties();
            FileInputStream fis = null;

            try {
                fis = new FileInputStream("db.properties");
                props.load(fis);
                mysqlDS = new MysqlDataSource();
                mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
                mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
                mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mysqlDS;
        } else {
            return mysqlDS;
        }
    }
}
