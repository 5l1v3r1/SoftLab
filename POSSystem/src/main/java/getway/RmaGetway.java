package getway;

import dal.RMA;
import dal.Supplyer;
import database.DBConnection;
import database.SQL;
import javafx.scene.control.Alert;
import list.ListRma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author HikmatD
 */
public class RmaGetway {

    SQL sql = new SQL();

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void save(RMA rma) {
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("insert into RMA values(?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, rma.rmaName);
            pst.setString(3, rma.rmaDays);
            pst.setString(4, rma.rmaComment);
            pst.setString(5, rma.creatorId);
            pst.setString(6, LocalDate.now().toString());
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Unit" + "  '" + rma.rmaName + "' " + "Added Sucessfuly");

        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void view(RMA rma) {

        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from RMA");
            rs = pst.executeQuery();
            while (rs.next()) {
                rma.id = rs.getString(1);
                rma.rmaName = rs.getString(2);
                rma.rmaDays = rs.getString(3);
                rma.rmaComment = rs.getString(4);
                rma.creatorId = rs.getString(5);
                rma.date = rs.getString(6);
                rma.creatorName = sql.getName(rma.creatorId, rma.creatorName, "User");
                rma.rmaDetails.addAll(new ListRma(rma.id, rma.rmaName, rma.rmaDays, rma.rmaComment, rma.creatorName, rma.date));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectedView(RMA rma) {
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from RMA where id=?");
            pst.setString(1, rma.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                rma.id = rs.getString(1);
                rma.rmaName = rs.getString(2);
                rma.rmaDays = rs.getString(3);
                rma.rmaComment = rs.getString(4);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchView(RMA rma) {
        rma.rmaDetails.clear();
        System.out.println("name :" + rma.rmaName);
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from RMA where RMAName like ? or RMADays like ? ORDER BY RMAName");

            pst.setString(1, "%" + rma.rmaName + "%");
            pst.setString(2, "%" + rma.rmaName + "%");

            rs = pst.executeQuery();
            while (rs.next()) {
                rma.id = rs.getString(1);
                rma.rmaName = rs.getString(2);
                rma.rmaDays = rs.getString(3);
                rma.rmaComment = rs.getString(4);
                rma.creatorId = rs.getString(5);
                rma.date = rs.getString(6);
                rma.creatorName = sql.getName(rma.creatorId, rma.creatorName, "User");
                rma.rmaDetails.addAll(new ListRma(rma.id, rma.rmaName, rma.rmaDays, rma.rmaComment, rma.creatorName, rma.date));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(RMA rma) {
        try {
            pst = con.prepareStatement("update RMA set RMAName=? , RMADays=?, Comment=? where Id=?");
            pst.setString(1, rma.rmaName);
            pst.setString(2, rma.rmaDays);
            pst.setString(3, rma.rmaComment);
            pst.setString(4, rma.id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Unit" + "  '" + rma.rmaName + "' " + "Updated Sucessfuly");

            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(RMA rma) {
        con = dbCon.geConnection();
        try {
            System.out.println("and i am hear");
            con = dbCon.geConnection();
            pst = con.prepareCall("delete from RMA where Id=?");
            pst.setString(1, rma.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isUniqName(RMA rma) {
        con = dbCon.geConnection();
        boolean uniqRMA = false;
        try {
            pst = con.prepareCall("select * from RMA where RMAName=? or RMADays=?");
            pst.setString(1, rma.rmaName);
            pst.setString(2, rma.rmaDays);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("in not uniq");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText(null);
                alert.setContentText("RMA" + "  '" + rma.rmaName + "/" + rma.rmaDays + "' " + "Already exist");
                alert.showAndWait();

                return uniqRMA;
            }
            rs.close();
            pst.close();
            con.close();
            uniqRMA = true;
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqRMA;
    }

    public boolean isNotUse(RMA rma) {
        con = dbCon.geConnection();
        boolean isNotUse = false;
        try {
            pst = con.prepareStatement("select * from Products where RMAId=?");
            pst.setString(1, rma.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("This RMA use in product'" + rs.getString(2) + "'  \n delete product first");

                return isNotUse;
            }
            rs.close();
            pst.close();
            con.close();
            isNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(RmaGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotUse;

    }
}
