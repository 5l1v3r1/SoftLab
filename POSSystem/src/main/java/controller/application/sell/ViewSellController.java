package controller.application.sell;

import dal.SellCart;
import getway.SellCartGerway;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import list.ListSold;
import media.UserNameMedia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author HikmatD
 */
public class ViewSellController implements Initializable {

    UserNameMedia nameMedia;

    SellCart sellCart = new SellCart();
    SellCartGerway sellCartGerway = new SellCartGerway();

    String userId;
    @FXML
    private Button btnSellOrder;
    @FXML
    private TableView<ListSold> tblSellView;
    @FXML
    private TableColumn<Object, Object> tblClmSellId;
    @FXML
    private TableColumn<Object, Object> tblClmProductId;
    @FXML
    private TableColumn<Object, Object> tblClmCustomerName;
    @FXML
    private TableColumn<Object, Object> tblClmSoldDate;
    @FXML
    private TableColumn<Object, Object> tblClmPursrsPrice;
    @FXML
    private TableColumn<Object, Object> tblClmSellPrice;
    @FXML
    private TableColumn<Object, Object> tblClmQuantity;
    @FXML
    private TableColumn<Object, Object> tblClmTotalPrice;
    @FXML
    private TableColumn<Object, Object> tblClmWarrenty;
    @FXML
    private TableColumn<Object, Object> tblClmSoldBy;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnRefresh;

    public void setNameMedia(UserNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnSellOrderOnAction(ActionEvent event) {
        System.out.println(userId);
        NewSellController acc = new NewSellController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/view/application/sell/NewSell.fxml"));
        try {
            fXMLLoader.load();
            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            NewSellController newSellController = fXMLLoader.getController();
            media.setId(userId);
            newSellController.setNameMedia(nameMedia);
            newSellController.genarateSellID();
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewDetails() {
        tblSellView.setItems(sellCart.soldList);
        tblClmCustomerName.setCellValueFactory(new PropertyValueFactory("customerName"));
        tblClmSellId.setCellValueFactory(new PropertyValueFactory("sellId"));
        tblClmProductId.setCellValueFactory(new PropertyValueFactory("productGId"));
        tblClmSoldDate.setCellValueFactory(new PropertyValueFactory("sellDate"));
        tblClmPursrsPrice.setCellValueFactory(new PropertyValueFactory("pursesPrice"));
        tblClmSellPrice.setCellValueFactory(new PropertyValueFactory("sellPrice"));
        tblClmQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        tblClmTotalPrice.setCellValueFactory(new PropertyValueFactory("totalPrice"));
        tblClmWarrenty.setCellValueFactory(new PropertyValueFactory("warrentyVoidDate"));
        tblClmSoldBy.setCellValueFactory(new PropertyValueFactory("sellerName"));
        sellCartGerway.firstTenView(sellCart);
    }

    @FXML
    private void tfSearchOnKeyReleased(KeyEvent event) {
        tblSellView.getItems().clear();
        sellCart.sellID = tfSearch.getText();
        sellCartGerway.searchView(sellCart);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        tblSellView.getItems().clear();
        tfSearch.clear();
        sellCart.carts.clear();
        tblSellView.setItems(sellCart.soldList);
        tblClmCustomerName.setCellValueFactory(new PropertyValueFactory("customerName"));
        tblClmSellId.setCellValueFactory(new PropertyValueFactory("sellId"));
        tblClmProductId.setCellValueFactory(new PropertyValueFactory("productGId"));
        tblClmSoldDate.setCellValueFactory(new PropertyValueFactory("sellDate"));
        tblClmPursrsPrice.setCellValueFactory(new PropertyValueFactory("pursesPrice"));
        tblClmSellPrice.setCellValueFactory(new PropertyValueFactory("sellPrice"));
        tblClmQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        tblClmTotalPrice.setCellValueFactory(new PropertyValueFactory("totalPrice"));
        tblClmWarrenty.setCellValueFactory(new PropertyValueFactory("warrentyVoidDate"));
        tblClmSoldBy.setCellValueFactory(new PropertyValueFactory("sellerName"));
        sellCartGerway.view(sellCart);
    }


}
