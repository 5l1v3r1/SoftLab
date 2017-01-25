package bill;

import dal.Catagory;
import dal.Supplyer;
import database.DBConnection;
import database.SQL;
import getway.CatagoryGetway;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rifat on 8/15/15.
 */
public class CatagoryBLL {

    SQL sql = new SQL();
    CatagoryGetway catagoryGetway = new CatagoryGetway();
    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    public void save(Catagory catagory) {
        if (isUniqName(catagory)) {
            catagoryGetway.save(catagory);
        }
    }

    public void update(Catagory catagory) {
        if (checkUpdate(catagory)) {
            catagoryGetway.update(catagory);
        } else if (isUniqName(catagory)) {
            catagoryGetway.update(catagory);
        }
    }

    public void delete(Catagory catagory) {
        if (catagoryGetway.isNotUse(catagory)) {
            catagoryGetway.delete(catagory);
        } else {
            //noting
        }
    }

    public boolean checkUpdate(Catagory catagory) {
        boolean isTrueUpdate = false;
        catagory.brandId = sql.getIdNo(catagory.brandName, catagory.brandId, "Brands", "BrandName");
        catagory.supplyerId = sql.getIdNo(catagory.supplyerName, catagory.supplyerId, "Supplyer", "SupplyerName");

        try {
            pst = con.prepareStatement("select * from Catagory where CatagoryName=? and BrandId=? and SupplyerId=? and Id=?");
            pst.setString(1, catagory.catagoryName);
            pst.setString(2, catagory.brandId);
            pst.setString(3, catagory.supplyerId);
            pst.setString(4, catagory.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                return isTrueUpdate = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTrueUpdate;
    }

    public boolean isUniqName(Catagory catagory) {

        boolean uniqSupplyer = false;
        catagory.brandId = sql.getIdNo(catagory.brandName, catagory.brandId, "Brands", "BrandName");
        catagory.supplyerId = sql.getIdNo(catagory.supplyerName, catagory.supplyerId, "Supplyer", "SupplyerName");
        try {
            pst = con.prepareCall("select * from Catagory where CatagoryName=? and BrandId=? and SupplyerId=?");
            pst.setString(1, catagory.catagoryName);
            pst.setString(2, catagory.brandId);
            pst.setString(3, catagory.supplyerId);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("in not uniq");

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Look, a Warning Dialog");
                alert.setContentText("Catagory" + "  '" + catagory.catagoryName + "' " + "Already exist");

                alert.showAndWait();

                return uniqSupplyer;
            }
            uniqSupplyer = true;
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqSupplyer;
    }
}
