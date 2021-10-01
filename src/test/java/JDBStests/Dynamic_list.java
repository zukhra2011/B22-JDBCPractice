package JDBStests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Dynamic_list {
        String dbURL = "jdbc:oracle:thin:@3.94.92.170:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        @Test
        public void test1() throws SQLException {
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select* from employees");
            ResultSetMetaData rsmd= resultSet.getMetaData();
            List<Map<String,Object>> queryData=new ArrayList<>();
            int colCount=rsmd.getColumnCount();
            while(resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                //some code to fill the dynamically
                for (int i = 1; i <= colCount; i++) {
                    //getting the info dynamically shorter version of getting row by
                    //specifying the row number
                    row.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                //add ready map row to the list
                queryData.add(row); }
            //print each row inside the list
            for (Map<String, Object> row:queryData){
                System.out.println(row.toString());
            resultSet.close();
            connection.close();
            statement.close();

                //with select first_name, last_name, salary, job_id
                // from employees where rownum<6;
                //pritning output is {JOB_ID=AD_PRES, SALARY=24000, LAST_NAME=King, FIRST_NAME=Steven}
                //{JOB_ID=AD_VP, SALARY=17000, LAST_NAME=Kochhar, FIRST_NAME=Neena}
                //{JOB_ID=AD_VP, SALARY=17000, LAST_NAME=De Haan, FIRST_NAME=Lex}
                //{JOB_ID=IT_PROG, SALARY=9000, LAST_NAME=Hunold, FIRST_NAME=Alexander}
                //{JOB_ID=IT_PROG, SALARY=6000, LAST_NAME=Ernst, FIRST_NAME=Bruce}

                //select * from employees prints all the rows
/*{JOB_ID=AD_PRES, EMPLOYEE_ID=100, SALARY=24000, HIRE_DATE=2003-06-17 00:00:00.0, DEPARTMENT_ID=90, LAST_NAME=King, EMAIL=SKING, PHONE_NUMBER=515.123.4567, FIRST_NAME=Steven, COMMISSION_PCT=null, MANAGER_ID=null}
{JOB_ID=AD_VP, EMPLOYEE_ID=101, SALARY=17000, HIRE_DATE=2005-09-21 00:00:00.0, DEPARTMENT_ID=90, LAST_NAME=Kochhar, EMAIL=NKOCHHAR, PHONE_NUMBER=515.123.4568, FIRST_NAME=Neena, COMMISSION_PCT=null, MANAGER_ID=100}
{JOB_ID=AD_VP, EMPLOYEE_ID=102, SALARY=17000, HIRE_DATE=2001-01-13 00:00:00.0, DEPARTMENT_ID=90, LAST_NAME=De Haan, EMAIL=LDEHAAN, PHONE_NUMBER=515.123.4569, FIRST_NAME=Lex, COMMISSION_PCT=null, MANAGER_ID=100}
{JOB_ID=IT_PROG, EMPLOYEE_ID=103, SALARY=9000, HIRE_DATE=2006-01-03 00:00:00.0, DEPARTMENT_ID=60, LAST_NAME=Hunold, EMAIL=AHUNOLD, PHONE_NUMBER=590.423.4567, FIRST_NAME=Alexander, COMMISSION_PCT=null, MANAGER_ID=102}
{JOB_ID=IT_PROG, EMPLOYEE_ID=104, SALARY=6000, HIRE_DATE=2007-05-21 00:00:00.0, DEPARTMENT_ID=60, LAST_NAME=Ernst, EMAIL=BERNST, PHONE_NUMBER=590.423.4568, FIRST_NAME=Bruce, COMMISSION_PCT=null, MANAGER_ID=103}
{JOB_ID=IT_PROG, EMPLOYEE_ID=105, SALARY=4800, HIRE_DATE=2005-06-25 00:00:00.0, DEPARTMENT_ID=60, LAST_NAME=Austin, EMAIL=DAUSTIN, PHONE_NUMBER=590.423.4569, FIRST_NAME=David, COMMISSION_PCT=null, MANAGER_ID=103}
{JOB_ID=IT_PROG, EMPLOYEE_ID=106, SALARY=4800, HIRE_DATE=2006-02-05 00:00:00.0, DEPARTMENT_ID=60, LAST_NAME=Pataballa, EMAIL=VPATABAL, PHONE_NUMBER=590.423.4560, FIRST_NAME=Valli, COMMISSION_PCT=null, MANAGER_ID=103}
{JOB_ID=IT_PROG, EMPLOYEE_ID=107, SALARY=4200, HIRE_DATE=2007-02-07 00:00:00.0, DEPARTMENT_ID=60, LAST_NAME=Lorentz, EMAIL=DLORENTZ, PHONE_NUMBER=590.423.5567, FIRST_NAME=Diana, COMMISSION_PCT=null, MANAGER_ID=103}
......so on...

 */


}}}
