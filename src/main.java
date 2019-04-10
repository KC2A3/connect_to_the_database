import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class main {
  private static final String URL = "jdbc:mysql://localhost:3306/student_examination_sys?useSSL=false";
  private static final String NAME = "root";
  private static final String PASSWORD = "13876599050zs";

  private static Connection getConnect() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("数据库驱动不存在！");
    }

    Connection conn = null;
    try {
      conn = DriverManager.getConnection(URL, NAME, PASSWORD);
    } catch (SQLException e) {
      System.out.println("数据库连接失败！");
    }

    return conn;
  }

  private static Statement getStatement(Connection connection) {
    Statement statement = null;
    try {
      statement = connection.createStatement();
    } catch (SQLException e) {
      System.out.println("创建Statement失败！");
    }

    return statement;
  }

  private static ResultSet executeSQL(Statement statement) {
    ResultSet rs = null;
    try {
      rs = statement.executeQuery("SELECT name,age,sex FROM student");
    } catch (SQLException e) {
      System.out.println("查询失败！");
    }

    return rs;
  }

    public static void main(String[] args) throws SQLException {
    Connection connection = getConnect();
    Statement statement = getStatement(connection);
    ResultSet resultSet = executeSQL(statement);

    while (resultSet.next()) {
      String name = resultSet.getString("name");
      int age = resultSet.getInt("age");
      String sex = resultSet.getString("sex");
      System.out.println("姓名： " + name + ", 年龄： " + age + ", 性别： " + sex);
    }
  }

  private static void closeConnect(ResultSet rs, Statement st, Connection conn) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (st != null) {
      try {
        st.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
