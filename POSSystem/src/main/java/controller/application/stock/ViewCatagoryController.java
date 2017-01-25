package controller.application.stock;

import bill.CatagoryBLL;
import custom.CellFactories;
import dal.Catagory;
import database.DBConnection;
import database.SQL;
import getway.CatagoryGetway;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import list.ListCatagory;
import media.UserNameMedia;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author HikmatD
 */
public class ViewCatagoryController implements Initializable {


    @FXML
    public TableColumn<Object, Object> clmSupplyer;
    @FXML
    public TableColumn tablClmBox;
    SQL sql = new SQL();
    Catagory catagory = new Catagory();
    CellFactories cellFactories = new CellFactories();
    CatagoryGetway catagoryGetway = new CatagoryGetway();
    CatagoryBLL catagoryBLL = new CatagoryBLL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private String usrId;
    private String catagoryId;
    private String brandId;
    private String brandName;
    private String creatorId;
    private UserNameMedia media;
    @FXML
    private TableView<ListCatagory> tblCatagory;
    @FXML
    private TableColumn<Object, Object> clmCatagoryId;
    @FXML
    private TableColumn<Object, Object> clmCatagoryName;
    @FXML
    private TableColumn<Object, Object> clmCatagoryBrand;
    @FXML
    private TableColumn<Object, Object> clmCatagoryCreator;
    @FXML
    private TableColumn<Object, Object> clmCatagoryDate;
    @FXML
    private TableColumn<Object, Object> clmCatagoryDescription;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private MenuItem miSearch;
    @FXML
    private MenuItem miUpdate;
    @FXML
    private MenuItem miAddNew;
    @FXML
    private MenuItem miDelete;
    @FXML
    private MenuItem miView;
    @FXML
    private Button btnRefresh;
    @FXML
    private TextField tfSearch;

    public UserNameMedia getMedia() {
        return media;
    }

    public void setMedia(UserNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML
    private void tblCatagoryOnClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            viewDetails();
        } else {
            System.out.println("CLICKED");
        }
    }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
        AddCatagoryController addCatagoryController = new AddCatagoryController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddCategory.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddCatagoryController catagoryController = fxmlLoader.getController();
            media.setId(usrId);
            catagoryController.setMedia(media);
            catagoryController.lblHeaderContent.setText("Add Item");
            catagoryController.btnUpdate.setVisible(false);
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
        if (tblCatagory.getSelectionModel().getSelectedItem() != null) {
            viewDetails();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        ListCatagory selectedCatagory = tblCatagory.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Confirm to delete!!");
        alert.setContentText("Are you sure to delete " + "  '" + selectedCatagory.getCatagoryName() + "' ??");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            catagory.id = selectedCatagory.getId();
            System.out.println(catagory.id + "On hear");
            catagoryBLL.delete(catagory);
//            acContent.setOpacity(1);
            tblCatagory.getItems().clear();
            showDetails();
        } else {
//            acContent.setOpacity(1);
        }
    }

    public void showDetails() {
        tblCatagory.setItems(catagory.catagoryDetails);
        tablClmBox.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmCatagoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmCatagoryName.setCellValueFactory(new PropertyValueFactory<>("catagoryName"));
        clmCatagoryBrand.setCellValueFactory(new PropertyValueFactory<>("brandId"));
        clmSupplyer.setCellValueFactory(new PropertyValueFactory<>("supplyerId"));
        clmCatagoryDescription.setCellValueFactory(new PropertyValueFactory<>("catagoryDescription"));
        clmCatagoryCreator.setCellValueFactory(new PropertyValueFactory<>("creatorId"));
        clmCatagoryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        catagoryGetway.view(catagory);
        tablClmBox.setCellFactory(cellFactories.cellFactoryCheckBox);

    }

    @FXML
    public void tfSearchOnType(Event event) {
        catagory.catagoryDetails.clear();
        catagory.catagoryName = tfSearch.getText().trim();
        tblCatagory.setItems(catagory.catagoryDetails);

        clmCatagoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmCatagoryName.setCellValueFactory(new PropertyValueFactory<>("catagoryName"));
        clmCatagoryBrand.setCellValueFactory(new PropertyValueFactory<>("brandId"));
        clmSupplyer.setCellValueFactory(new PropertyValueFactory<>("supplyerId"));
        clmCatagoryDescription.setCellValueFactory(new PropertyValueFactory<>("catagoryDescription"));
        clmCatagoryCreator.setCellValueFactory(new PropertyValueFactory<>("creatorId"));
        clmCatagoryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        catagoryGetway.searchView(catagory);

    }

    private void viewDetails() {
        if (!tblCatagory.getSelectionModel().isEmpty()) {
            ListCatagory selectedCatagory = tblCatagory.getSelectionModel().getSelectedItem();
            System.out.println("ID is");
            System.out.println(selectedCatagory.getCreatorId());
            String items = selectedCatagory.getId();
            if (!items.equals(null)) {
                AddCatagoryController addCatagoryController = new AddCatagoryController();
                UserNameMedia media = new UserNameMedia();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddCategory.fxml"));
                try {
                    fxmlLoader.load();
                    Parent parent = fxmlLoader.getRoot();
                    Scene scene = new Scene(parent);
                    scene.setFill(new Color(0, 0, 0, 0));
                    AddCatagoryController catagoryController = fxmlLoader.getController();
                    media.setId(usrId);
                    catagoryController.setMedia(media);
                    catagoryController.lblHeaderContent.setText("Catagory Details");
                    catagoryController.btnUpdate.setVisible(true);
                    catagoryController.btnSave.setVisible(false);
                    catagoryController.catagoryId = selectedCatagory.id;
                    catagoryController.showDetails();
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    nStage.initModality(Modality.APPLICATION_MODAL);
                    nStage.initStyle(StageStyle.TRANSPARENT);
                    nStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("empty Selection");
        }


    }

    @FXML
    private void miSearchOnAction(ActionEvent event) {
        tblCatagory.getSelectionModel().clearSelection();
        tfSearch.requestFocus();
    }

    @FXML
    private void miUpdateOnAction(ActionEvent event) {
        btnUpdateOnAction(event);
    }

    @FXML
    private void miAddNewOnAction(ActionEvent event) {
        btnAddOnAction(event);
    }

    @FXML
    private void miDeleteOnAction(ActionEvent event) {
        btnDeleteOnAction(event);
    }

    @FXML
    private void miViewOnAction(ActionEvent event) {
        btnUpdateOnAction(event);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        catagory.catagoryDetails.clear();
        showDetails();
    }

}
