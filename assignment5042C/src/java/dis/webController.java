/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author liyunhong
 */
@Path("webController")
public class webController {
    
    @GET
    @Path("/getData")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<webModel> getDataInJSON() throws ClassNotFoundException, SQLException
    {
        ArrayList<webModel> wmm = new ArrayList<webModel>();
        Connection con = null;
        String username = "fit5042";
        String password = "fit5042";
        String query = "select * from service";
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/webServer", username, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next())
        {
            webModel wm = new webModel();
            wm.setServiceid(rs.getInt("serviceid"));
            wm.setServiceName(rs.getString("servicename"));
            wm.setServiceType(rs.getString("servicetype"));
            wm.setServiceThum(rs.getString("thumbnail"));
            wm.setServiceDes(rs.getString("description"));
            wmm.add(wm);
        }
        return wmm;
    }
        
}
