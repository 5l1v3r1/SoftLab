package controller.application.settings;

import database.DBConnection;
import javafx.beans.binding.BooleanBinding;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import media.UserNameMedia;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author HikmatD
 */
public class OrgSettingController implements Initializable {

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private TextField tfOrganizeName;
    @FXML
    private Rectangle retOrgLogo;
    @FXML
    private Button btnAttechLogo;
    @FXML
    private Button btnSaveOrganize;
    private File file;
    private BufferedImage bufferedImage;
    private Image image;
    private String userId;
    private String imagePath;
    private UserNameMedia usrIdMedia;
    @FXML
    private TextField tfWebSite;
    @FXML
    private TextArea taContactNumber;
    @FXML
    private TextArea taAdddress;

    public UserNameMedia getUsrIdMedia() {
        return usrIdMedia;
    }

    public void setUsrIdMedia(UserNameMedia usrIdMedia) {
        userId = usrIdMedia.getId();
        this.usrIdMedia = usrIdMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BooleanBinding boolenBind = tfOrganizeName.textProperty().isEmpty()
                .or(tfWebSite.textProperty().isEmpty()
                        .or(taContactNumber.textProperty().isEmpty())
                        .or(taAdddress.textProperty().isEmpty()));

        btnSaveOrganize.disableProperty().bind(boolenBind);
    }

    @FXML
    private void btnSaveOrganizeOnClick(ActionEvent event) {
        if (isFoundData()) {
            //update
            if (imagePath != null) {
                updateOrganizeWithImage();
            } else {
                updateOrganizeWithOutImage();
            }

        } else {
            //insert
            insertOrganizeWithImage();
        }
    }

    @FXML
    private void btnAttechLogoOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG (Joint Photographic Group)", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG (Portable Network Graphics)", "*.png")
        );

        fileChooser.setTitle("Choise a Image File");

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            System.out.println(file);
            bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            retOrgLogo.setFill(new ImagePattern(image));
            imagePath = file.getAbsolutePath();
        }
    }
    /*
    
     */

    public void showDetails() {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from Organize where Id=?");
            pst.setString(1, "1");
            rs = pst.executeQuery();
            while (rs.next()) {
                tfOrganizeName.setText(rs.getString(2));
                tfWebSite.setText(rs.getString(3));
                taContactNumber.setText(rs.getString(4));
                taAdddress.setText(rs.getString(5));

                Blob blob = rs.getBlob(6);
                if (blob != null) {
                    ByteArrayInputStream in = new ByteArrayInputStream(blob.getBytes(1, (int) blob.length()));
                    image = new Image(in);
                    retOrgLogo.setFill(new ImagePattern(image));
                } else {

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrgSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    
     */

    private boolean isFoundData() {
        boolean dataFound = true;
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from Organize ORDER BY Id ASC LIMIT 1");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Data Found");
                return dataFound;
            }
            System.out.println("Data not found");
            dataFound = false;

        } catch (SQLException ex) {
            Logger.getLogger(OrgSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataFound;
    }
    /*
    
     */

    private void updateOrganizeWithImage() {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("Update Organize set OrgName=?,OrgWeb=?,OrgContactNumbers=?,OrgContactAddress=?,OrgLogo=? where Id=1");

            pst.setString(1, tfOrganizeName.getText());
            pst.setString(2, tfWebSite.getText());
            pst.setString(3, taContactNumber.getText());
            pst.setString(4, taAdddress.getText());
            if (imagePath != null) {
                try {
                    InputStream is = new FileInputStream(new File(imagePath));
                    pst.setBlob(5, is);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(OrgSettingController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                pst.setBlob(5, (Blob) null);
            }

            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("Confirm!!");
            alert.setContentText("Update Data Sucessfuly");
            alert.showAndWait();


        } catch (SQLException ex) {
            Logger.getLogger(OrgSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    
     */

    private void insertOrganizeWithImage() {

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into Organize values(?,?,?,?,?,?,?)");
            pst.setString(1, "1");
            pst.setString(2, tfOrganizeName.getText());
            pst.setString(3, tfWebSite.getText());
            pst.setString(4, taContactNumber.getText());
            pst.setString(5, taAdddress.getText());
            if (imagePath != null) {
                try {
                    InputStream is = new FileInputStream(new File(imagePath));
                    pst.setBlob(6, is);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(OrgSettingController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                pst.setBlob(6, (Blob) null);
            }
            pst.setString(7, userId);
            pst.executeUpdate();


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("Confirm!!");
            alert.setContentText("Insert Data Sucessfuly");
            alert.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(OrgSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    
     */

    private void updateOrganizeWithOutImage() {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("Update Organize set OrgName=?,OrgWeb=?,OrgContactNumbers=?,OrgContactAddress=? where Id=1");

            pst.setString(1, tfOrganizeName.getText());
            pst.setString(2, tfWebSite.getText());
            pst.setString(3, taContactNumber.getText());
            pst.setString(4, taAdddress.getText());

            pst.executeUpdate();


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("Confirm!!");
            alert.setContentText("Success");
            alert.showAndWait();


        } catch (SQLException ex) {
            Logger.getLogger(OrgSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
