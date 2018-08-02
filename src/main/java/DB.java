import java.sql.*;

public class DB {
    public static final String url = "database_url";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "user";
    public static final String password = "password";

    public Connection conn = null;

    public void DBHelper(String taskId,String action){

        try {
            Class.forName(name);
            conn = DriverManager.getConnection(url, user, password);
            PreparedStatement p = null;
            try {
                p = conn.prepareStatement("insert into library_street_spider(taskId,action,lastrun,status) values(?,?,?,0)");
                p.setString(1,taskId);
                p.setString(2,action);
                p.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                p.executeUpdate();//表示执行PreparedStatement 中封装的sql语句
            } catch (SQLException e) {
                System.out.println("PreparedStatement 对象创建失败 。。。。");
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void close(){
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
