package getway;

import dal.Brands;
import dal.Supplyer;
import database.DBConnection;
import database.SQL;
import javafx.scene.control.Alert;
import list.ListBrands;

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
public class BrandsGetway {

    SQL sql = new SQL();

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void save(Brands brands) {
        con = dbCon.geConnection();
        brands.supplyrId = sql.getIdNo(brands.supplyerName, brands.supplyrId, "Supplyer", "SupplyerName");

        try {
            pst = con.prepareStatement("insert into Brands values(?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, brands.brandName);
            pst.setString(3, brands.brandComment);
            pst.setString(4, brands.supplyrId);
            pst.setString(5, brands.creatorId);
            pst.setString(6, LocalDate.now().toString());
            pst.executeUpdate();
            con.close();
            pst.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Brand" + "  '" + brands.brandName + "' " + "Added Sucessfuly");

            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void view(Brands brands) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareCall("select * from Brands");
            rs = pst.executeQuery();
            while (rs.next()) {
                brands.id = rs.getString(1);
                brands.brandName = rs.getString(2);
                brands.brandComment = rs.getString(3);
                brands.supplyrId = rs.getString(4);
                brands.creatorId = rs.getString(5);
                brands.date = rs.getString(6);
                brands.supplyerName = sql.getName(brands.supplyrId, brands.supplyerName, "Supplyer");
                brands.creatorName = sql.getName(brands.creatorId, brands.creatorName, "User");
                brands.brandDitails.addAll(new ListBrands(brands.id, brands.brandName, brands.brandComment, brands.supplyerName, brands.creatorName, brands.date));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void selectedView(Brands brands) {
        con = dbCon.geConnection();

        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from Brands where id=?");
            pst.setString(1, brands.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                brands.id = rs.getString(1);
                brands.brandName = rs.getString(2);
                brands.brandComment = rs.getString(3);
                brands.supplyrId = rs.getString(4);
                brands.supplyerName = sql.getName(brands.supplyrId, brands.supplyerName, "Supplyer");
            }
            con.close();
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchView(Brands brands) {
        con = dbCon.geConnection();

        brands.brandDitails.clear();
        System.out.println("name :" + brands.brandName);

        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from Brands where BrandName like ? ORDER BY BrandName");
            System.out.println("Brand name in Brand Object");
            pst.setString(1, "%" + brands.brandName + "%");

            rs = pst.executeQuery();
            while (rs.next()) {
                brands.id = rs.getString(1);
                brands.brandName = rs.getString(2);
                brands.brandComment = rs.getString(3);
                brands.supplyrId = rs.getString(4);
                brands.creatorId = rs.getString(5);
                brands.date = rs.getString(6);
                brands.supplyerName = sql.getName(brands.supplyrId, brands.supplyerName, "Supplyer");
                brands.creatorName = sql.getName(brands.creatorId, brands.creatorName, "User");
                brands.brandDitails.addAll(new ListBrands(brands.id, brands.brandName, brands.brandComment, brands.supplyerName, brands.creatorName, brands.date));
            }
            con.close();
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Brands brands) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("delete from Brands where Id=?");
            pst.setString(1, brands.id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Brands brands) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("update Brands set BrandName=? , Description=?,SupplyerId=? where Id=?");
            pst.setString(1, brands.brandName);
            pst.setString(2, brands.brandComment);
            pst.setString(3, brands.supplyrId);
            pst.setString(4, brands.id);
            pst.executeUpdate();
            con.close();
            pst.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Supplyer" + "  '" + brands.brandName + "' " + "Updated Sucessfuly");

            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isNotUsed(Brands brands) {
        con = dbCon.geConnection();
        boolean inNotUse = false;
        try {
            pst = con.prepareStatement("select * from Catagory where BrandId=?");
            pst.setString(1, brands.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("This brand already used in '" + rs.getString(2) + "' catagory \n delete catagory first");

                alert.showAndWait();

                return inNotUse;
            }
            rs.close();
            pst.close();
            con.close();
            inNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(BrandsGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inNotUse;
    }

}
