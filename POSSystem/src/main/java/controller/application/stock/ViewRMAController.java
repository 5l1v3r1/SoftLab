package controller.application.stock;

import bill.RmaBLL;
import dal.RMA;
import database.DBConnection;
import getway.RmaGetway;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import list.ListRma;
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
public class ViewRMAController implements Initializable {

    RMA rma = new RMA();
    RmaGetway rmaGetway = new RmaGetway();
    RmaBLL rmaBLL = new RmaBLL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private String usrId;
    private String rmaId;
    private UserNameMedia media;
    private String creatorId;
    @FXML
    private TableView<ListRma> tblViewRMA;
    @FXML
    private TableColumn<Object, Object> clmRMAId;
    @FXML
    private TableColumn<Object, Object> clmRMAName;
    @FXML
    private Button btnAddNew;
    @FXML
    private TableColumn<Object, Object> clmRMADayes;
    @FXML
    private TableColumn<Object, Object> clmRMADiscription;
    @FXML
    private TableColumn<Object, Object> clmRMACreator;
    @FXML
    private TableColumn<Object, Object> clmRMADate;
    @FXML
    private Button btnRefresh;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

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
    private void tblViewRMAOnClick(MouseEvent event) {
        if (!tblViewRMA.getSelectionModel().isEmpty()) {
            if (event.getClickCount() == 2) {
                viewDetails();
            }
        }

    }


    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (!tblViewRMA.getSelectionModel().isEmpty()) {
            viewDetails();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if (!tblViewRMA.getSelectionModel().isEmpty()) {
            ListRma selectedRMA = tblViewRMA.getSelectionModel().getSelectedItem();
            String rmaName = selectedRMA.getRmaName();
            rmaId = selectedRMA.getRamId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you sure to delete '" + rmaName + "' ??");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                rma.id = rmaId;

                rmaBLL.delete(rma);
                tfSearchOnKeyRelesh(event);
            } else {
                System.out.println("NULL SELECTED");
            }
        }
    }


    public void showDetails() {
        tblViewRMA.setItems(rma.rmaDetails);
        clmRMAId.setCellValueFactory(new PropertyValueFactory<>("ramId"));
        clmRMAName.setCellValueFactory(new PropertyValueFactory<>("rmaName"));
        clmRMADayes.setCellValueFactory(new PropertyValueFactory<>("rmaDays"));
        clmRMADiscription.setCellValueFactory(new PropertyValueFactory<>("rmaComment"));
        clmRMACreator.setCellValueFactory(new PropertyValueFactory<>("creatorName"));
        clmRMADate.setCellValueFactory(new PropertyValueFactory<>("date"));
        rmaGetway.view(rma);
    }


    @FXML
    private void tblViewRMAOnKeyResele(KeyEvent event) {

    }


    @FXML
    public void btnAddNew(ActionEvent actionEvent) {
        AddRMAController addRMAController = new AddRMAController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddRMA.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddRMAController rmaController = fxmlLoader.getController();
            media.setId(usrId);
            rmaController.setMedia(media);
            rmaController.lblContent.setText("ADD RMA");
            rmaController.btnUpdate.setVisible(false);
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
    public void tfSearchOnKeyRelesh(Event event) {
        rma.rmaDetails.clear();
        rma.rmaName = tfSearch.getText().trim();
        tblViewRMA.setItems(rma.rmaDetails);
        clmRMAId.setCellValueFactory(new PropertyValueFactory<>("ramId"));
        clmRMAName.setCellValueFactory(new PropertyValueFactory<>("rmaName"));
        clmRMADayes.setCellValueFactory(new PropertyValueFactory<>("rmaDays"));
        clmRMADiscription.setCellValueFactory(new PropertyValueFactory<>("rmaComment"));
        clmRMACreator.setCellValueFactory(new PropertyValueFactory<>("creatorName"));
        clmRMADate.setCellValueFactory(new PropertyValueFactory<>("date"));
        rmaGetway.searchView(rma);

    }

    private void viewDetails() {
        if (!tblViewRMA.getSelectionModel().isEmpty()) {
            ListRma selectedRma = tblViewRMA.getSelectionModel().getSelectedItem();
            System.out.println("ID is");
            System.out.println(selectedRma.getRamId());
            String items = selectedRma.getRamId();
            if (!items.equals(null)) {
                AddUnitController addUnitController = new AddUnitController();
                UserNameMedia media = new UserNameMedia();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddRMA.fxml"));
                try {
                    fxmlLoader.load();
                    Parent parent = fxmlLoader.getRoot();
                    Scene scene = new Scene(parent);
                    scene.setFill(new Color(0, 0, 0, 0));
                    AddRMAController rmaController = fxmlLoader.getController();
                    media.setId(usrId);
                    rmaController.setMedia(media);
                    rmaController.lblContent.setText("RMA DETAILS");
                    rmaController.btnUpdate.setVisible(true);
                    rmaController.btnSave.setVisible(true);
                    rmaController.rmaId = selectedRma.getRamId();
                    rmaController.showDetails();
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
    private void btnRefreshOnAction(ActionEvent event) {
        rma.rmaDetails.clear();
        showDetails();
    }


}
