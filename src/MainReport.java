import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 5/11/2557.
 */
public class MainReport {
    public static void main(String[] args){
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        JasperDesign jasperDesign;
        Connection conn = null;

        Map parameters = new HashMap();

        parameters.put("FIRSTNAME", "Tossapon");

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/eschool_db?useUnicode=true&characterEncoding=UTF-8&user=root");


            jasperDesign = JRXmlLoader.load("user.jrxml");


            jasperReport = JasperCompileManager.compileReport(jasperDesign);


            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);


            JasperExportManager.exportReportToPdfFile(jasperPrint, "demo.pdf");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        } finally {
            if(conn != null){
                try{conn.close();}
                catch(Exception e){}
            }
        }

    }
}
