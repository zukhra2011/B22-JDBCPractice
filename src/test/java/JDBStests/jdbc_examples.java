package JDBStests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

        String dbURL="jdbc:oracle:thin:@3.94.92.170:1521:xe";
        String dbUsername="hr";
        String dbPassword="hr";

        @Test
        public void test1() throws SQLException {
                Connection connection= DriverManager.getConnection(dbURL,dbUsername,dbPassword);
                Statement statement =connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet resultSet=statement.executeQuery("Select * From departments");
                resultSet.next(); //moves to the first row
                System.out.println(resultSet.getString(2));
                //display departments table in 10-Administration-200-1700 format
                while (resultSet.next()){
                System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)
                    +" "+resultSet.getInt(3)+" "+resultSet.getInt(4));
                }
              resultSet=statement.executeQuery("select * from regions");
              while(resultSet.next()){
              System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)); }

            resultSet.close();
            connection.close();
            statement.close();


    }
    @DisplayName("Result set methods")
    @Test
    public void test2()throws SQLException{
        Connection connection= DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement =connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet=statement.executeQuery("Select * From departments");
        resultSet.last();//move to the last row
        int rowCount= resultSet.getRow();
        System.out.println(rowCount);  //get the roww count

        // print LL SECOND Column information
        while(resultSet.next()){
            System.out.println(resultSet.getString(2));}
        resultSet.close();
        connection.close();
        statement.close();

    }
    @DisplayName("Metadata practice")
    @Test
    public void test3()throws SQLException{
        Connection connection= DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement =connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet=statement.executeQuery("Select * From departments");

     //get teh database related data inside the dbMetaData object
      /*DatabaseMetaData dbMetadata=connection.getMetaData();
        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        System.out.println("dbMetadata.getDriverVersion() = " + dbMetadata.getDriverVersion()); */
        //get the resultsetmetadata//rsmd
        ResultSetMetaData rstMetaData= resultSet.getMetaData();
        //how many columns we have;
        int colCount=rstMetaData.getColumnCount();
        System.out.println("colCount = " + colCount);
        //getting column names
        System.out.println(rstMetaData.getColumnName(1));
        System.out.println(rstMetaData.getColumnName(2));
      // print all the column names dynamically
        for (int i=1;i<=colCount;i++ ){
            System.out.println(rstMetaData.getColumnName(i));
        }



        resultSet.close();
        connection.close();
        statement.close();


}}