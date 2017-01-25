package controller.application.stock;

import bill.CatagoryBLL;
import dal.Catagory;
import database.DBConnection;
import database.SQL;
import getway.CatagoryGetway;
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

/**
 * FXML Controller class
 *
 * @author HikmatD
 */
public class AddCatagoryController implements Initializable {

    public String supplyerId;
    public String supplyerName;
    public String catagoryId;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    @FXML
    public Label lblHeaderContent;
    @FXML
    public Button btnClose;
    Catagory catagory = new Catagory();
    CatagoryGetway catagoryGetway = new CatagoryGetway();
    CatagoryBLL catagoryBLL = new CatagoryBLL();
    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    private String userId;
    private String brandId;
    private String brnadName;
    private UserNameMedia media;
    @FXML
    private ComboBox<String> cbBrandName;
    @FXML
    private TextField tfCatagoryName;
    @FXML
    private TextArea taCatagoryDescription;
    @FXML
    private ComboBox<String> cbSupplyerName;
    @FXML
    private Button btnAddSupplyer;
    @FXML
    private Button btnAddBrand;

    public UserNameMedia getMedia() {
        return media;
    }

    public void setMedia(UserNameMedia media) {
        userId = media.getId();
        this.media = media;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }


    @FXML
    private void btnSaveCatagory(ActionEvent event) {
        if (isNotNull()) {
            catagory.brandName = cbBrandName.getSelectionModel().getSelectedItem();
            catagory.supplyerName = cbSupplyerName.getSelectionModel().getSelectedItem();
            catagory.catagoryName = tfCatagoryName.getText().trim();
            catagory.catagoryDescription = taCatagoryDescription.getText().trim();
            catagory.creatorId = userId;
            catagoryBLL.save(catagory);

        }
    }

    @FXML
    private void btnAddSupplyerOnAction(ActionEvent event) {
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
            media.setId(userId);
            supplyerController.setMedia(media);
            supplyerController.lblCaption.setText("Add Supplyer");
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

    @FXML
    private void btnAddBrandOnAction(ActionEvent event) {
        AddBrandController addSupplyerController = new AddBrandController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddBrand.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddBrandController supplyerController = fxmlLoader.getController();
            media.setId(userId);
            supplyerController.setMedia(media);
            supplyerController.lblHeader.setText("Add Brand");
            supplyerController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        System.out.println("Clicked");
        if (isNotNull()) {
            catagory.id = catagoryId;
            if (!cbBrandName.getSelectionModel().isEmpty()) {
                catagory.brandName = cbBrandName.getSelectionModel().getSelectedItem();
            } else if (!cbBrandName.getPromptText().isEmpty()) {
                catagory.brandName = cbBrandName.getPromptText();
            }
            if (!cbSupplyerName.getSelectionModel().isEmpty()) {
                catagory.supplyerName = cbSupplyerName.getSelectionModel().getSelectedItem();
            } else if (!cbSupplyerName.getPromptText().isEmpty()) {
                catagory.supplyerName = cbSupplyerName.getPromptText();
            }
            catagory.catagoryName = tfCatagoryName.getText().trim();
            catagory.catagoryDescription = taCatagoryDescription.getText().trim();
            catagoryBLL.update(catagory);
        }
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public boolean isNotNull() {
        boolean isNotNull;
        if (tfCatagoryName.getText().trim().isEmpty()
                || cbSupplyerName.getSelectionModel().isEmpty()
                && cbSupplyerName.getPromptText().isEmpty()
                || cbBrandName.getSelectionModel().isEmpty()
                && cbBrandName.getPromptText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warn");
            alert.setHeaderText("Warn");
            alert.setContentText("Please fill all requre field");
            alert.showAndWait();

            isNotNull = false;
        } else {
            isNotNull = true;
        }
        return isNotNull;
    }

    @FXML
    private void cbSupplyerNameOnClick(MouseEvent event) {
        cbBrandName.getItems().clear();
        cbBrandName.setPromptText(null);
        try {
            pst = con.prepareStatement("select * from Supplyer");
            rs = pst.executeQuery();
            while (rs.next()) {
                supplyerName = rs.getString(2);
                cbSupplyerName.getItems().remove(supplyerName);
                cbSupplyerName.getItems().add(supplyerName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cbBrandNameOnClick(MouseEvent event) throws SQLException {
        cbBrandName.getItems().clear();
        supplyerName = cbSupplyerName.getSelectionModel().getSelectedItem();
        supplyerId = sql.getIdNo(supplyerName, supplyerId, "Supplyer", "SupplyerName");

        pst = con.prepareStatement("select * from Brands where SupplyerId=?");
        pst.setString(1, supplyerId);
        rs = pst.executeQuery();
        while (rs.next()) {
            cbBrandName.getItems().add(rs.getString(2));
        }
    }

    public void showDetails() {
        catagory.id = catagoryId;
        catagoryGetway.selectedView(catagory);
        tfCatagoryName.setText(catagory.catagoryName);
        taCatagoryDescription.setText(catagory.catagoryDescription);
        cbBrandName.setPromptText(catagory.brandName);
        cbSupplyerName.setPromptText(catagory.supplyerName);
    }
}
