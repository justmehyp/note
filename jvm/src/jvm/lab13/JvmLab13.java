package jvm.lab13;

import java.io.PrintWriter;
import java.sql.*;

public class JvmLab13 {
    public static void main(String[] args) throws Exception {
//        Class.forName("com.mysql.jdbc.Driver"); // 这一行现在可以去掉了，初始化DriverManager时会调用 ServiceLoader.load(Driver.class)

        DriverManager.setLogWriter(new PrintWriter(System.err));

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jvm_lab", "root", "123456");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from jvm_lab13");
        ResultSet resultSet = preparedStatement.executeQuery();


        System.out.println("---------------------");
        while (resultSet.next()) { // 行
            ResultSetMetaData metaData = resultSet.getMetaData();

            for (int i = 1; i <= metaData.getColumnCount(); i++) { // 列
                System.out.println(metaData.getColumnTypeName(i) + " " + metaData.getColumnName(i) + " = " + resultSet.getString(i));
            }
            System.out.println("---------------------");
        }
    }
}
