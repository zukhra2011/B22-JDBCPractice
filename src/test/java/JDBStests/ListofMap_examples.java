package JDBStests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListofMap_examples {
    String dbURL = "jdbc:oracle:thin:@3.94.92.170:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * From departments");
        //craeting list for keeping all the rows maps
        List<Map<String, Object>> queryData = new ArrayList<>();
        Map<String, Object> row1 = new HashMap<>();
        row1.put("first_name", "Steven");
        row1.put("last_name", "King");
        row1.put("salary", 24000);
        row1.put("job_id", "AD_PRES");//putting in information in the first row.

        System.out.println(row1.toString());

        Map<String, Object> row2 = new HashMap<>();
        row2.put("first_name", "Neena");
        row2.put("last_name", "Kochnar");
        row2.put("salary", "17000");
        row2.put("job_id", "AD_VP");
        System.out.println(row2.toString());

        //adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);
        //get the steven  lastname directly from the list
        //0 row getting last name
        System.out.println(queryData.get(0).get("last_name"));
        // 1 row getting salary
        System.out.println(queryData.get(1).get("salary"));
        resultSet.close();
        connection.close();
        statement.close();

        //printed statement
        /*
        {job_id=AD_PRES, last_name=King, salary=24000, first_name=Steven}
        {job_id=AD_VP, last_name=Kochnar, salary=17000, first_name=Neena}
         King
        17000*/
    }

    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select first_name, last_name, salary, job_id\n" +
                "from employees where rownum<6");
       //In order to get column names we need resultsetmetadata
        ResultSetMetaData rsmd= resultSet.getMetaData();
        //Move to the first row
        resultSet.next();
        //creating list for keeping all the rows maps
        List<Map<String, Object>> queryData = new ArrayList<>();
        Map<String, Object> row1 = new HashMap<>();
        //reason for changing is from static to dynamic
        row1.put(rsmd.getColumnName(1),resultSet.getString(1));
        row1.put(rsmd.getColumnName(2),resultSet.getString(2));
        row1.put(rsmd.getColumnName(3),resultSet.getString(3));
        row1.put(rsmd.getColumnName(4),resultSet.getString(4));
        System.out.println(row1.toString());
        //before doing anything need to move to the next row first
        resultSet.next();
        Map<String, Object> row2 = new HashMap<>();
        row2.put(rsmd.getColumnName(1),resultSet.getString(1));
        row2.put(rsmd.getColumnName(2),resultSet.getString(2));
        row2.put(rsmd.getColumnName(3),resultSet.getString(3));
        row2.put(rsmd.getColumnName(4),resultSet.getString(4));
        System.out.println(row2.toString());


//printing output
      //  {JOB_ID=AD_PRES, SALARY=24000, LAST_NAME=King, FIRST_NAME=Steven}
       // {JOB_ID=AD_VP, SALARY=17000, LAST_NAME=Kochhar, FIRST_NAME=Neena}

    }
}