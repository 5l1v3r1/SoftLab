package controller.application.stock;

import bill.BrandBLL;
import dal.Brands;
import database.DBConnection;
import getway.BrandsGetway;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.UserNameMedia;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author HikmatD
 */
public class AddBrandController implements Initializable {
    public Button btnAddSupplyer;
    public String brandId;
    @FXML
    public Button btnUpdate;
    @FXML
    public Label lblHeader;
    @FXML
    public Button btnClose;
    @FXML
    public Button btnAddBrand;
    Brands brands = new Brands();
    BrandsGetway brandsGetway = new BrandsGetway();
    BrandBLL brandBLL = new BrandBLL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private UserNameMedia media;
    private String usrId;
    private String supplyerName;
    private String supplyerId;
    @FXML
    private ComboBox<String> cbSupplyer;
    @FXML
    private TextField tfBrandName;
    @FXML
    private TextArea taDiscription;

    public UserNameMedia getMedia() {
        return media;
    }

    public void setMedia(UserNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnAddBrandOnAction(ActionEvent event) {
        System.out.println(usrId);
        if (isNotNull()) {
            brands.creatorId = usrId;
            brands.brandName = tfBrandName.getText();
            brands.brandComment = taDiscription.getText();
            brands.supplyerName = cbSupplyer.getSelectionModel().getSelectedItem();
            brandBLL.save(brands);
        }

    }

    @FXML
    private void cbSupplyerOnAction(ActionEvent event) {

    }

    @FXML
    private void cbSupplyerOnClick(MouseEvent event) {
        cbSupplyer.getItems().clear();
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from Supplyer order by SupplyerName");
            rs = pst.executeQuery();
            while (rs.next()) {
                supplyerName = rs.getString(2);

                cbSupplyer.getItems().add(supplyerName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddBrandController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        System.out.println();
        if (isNotNull()) {
            brands.id = brandId;
            if (!cbSupplyer.getSelectionModel().isEmpty()) {
                brands.supplyerName = cbSupplyer.getSelectionModel().getSelectedItem();
            } else if (!cbSupplyer.getPromptText().isEmpty()) {
                brands.supplyerName = cbSupplyer.getPromptText();
            }

            brands.brandName = tfBrandName.getText().trim();
            brands.brandComment = taDiscription.getText();
            brandBLL.update(brands);
        }
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public boolean isNotNull() {
        System.out.println(cbSupplyer.getPromptText());
//        System.out.println(cbSupplyer.getSelectionModel().getSelectedItem().isEmpty());
        System.out.println(tfBrandName.getText());
        boolean isNotNull;
        if (tfBrandName.getText().trim().isEmpty()
                || cbSupplyer.getSelectionModel().isEmpty()
                && cbSupplyer.getPromptText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Warning!!!");
            alert.setContentText("Please fill all required field!");
            alert.showAndWait();

            isNotNull = false;
        } else {
            isNotNull = true;
        }
        return isNotNull;
    }

    public void showDetails() {
        brands.id = brandId;
        brandsGetway.selectedView(brands);
        tfBrandName.setText(brands.brandName);
        taDiscription.setText(brands.brandComment);
        cbSupplyer.setPromptText(brands.supplyerName);
    }

    public void btnAddSupplyerOnAction(ActionEvent actionEvent) {
        AddSupplyerController addSupplyerController = new AddSupplyerController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddSupplier.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddSupplyerController supplyerController = fxmlLoader.getController();
            media.setId(usrId);
            supplyerController.setMedia(media);
            supplyerController.lblCaption.setText("Add Item");
            supplyerController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            supplyerController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
