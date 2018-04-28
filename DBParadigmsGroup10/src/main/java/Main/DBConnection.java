package Main;

public class DBConnection {


    private String IP;
    private String PORT;
    private String USER;
    private String PASSWORD;
    private DBType DBTYPE;



    public static enum DBType{
        Keyvalue, Document, SQL, Graph
    }

    public DBConnection(String ip, String port, String user, String password, DBType dbType){
        this.IP = ip;
        this.PORT = port;
        this.USER = user;
        this.PASSWORD = password;
        this.DBTYPE = dbType;
    }




}
