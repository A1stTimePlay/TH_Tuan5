import Model.LichGiangDay;
import Utils.Database;
import com.mysql.cj.exceptions.PasswordExpiredException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Connection con = Database.getConnection();

    @FXML
    TableView<LichGiangDay> tableView;
    @FXML
    TableColumn<LichGiangDay, String> STT;
    @FXML
    TableColumn<LichGiangDay, String> TenGV;
    @FXML
    TableColumn<LichGiangDay, String> MaLop;
    @FXML
    TableColumn<LichGiangDay, String> TenMon;
    @FXML
    TableColumn<LichGiangDay, String> SiSo;
    @FXML
    TableColumn<LichGiangDay, String> BatDau;
    @FXML
    TableColumn<LichGiangDay, String> KetThuc;
    @FXML
    TableColumn<LichGiangDay, String> SoTiet;

    @FXML
    private TextField tfTenGV;

    @FXML
    private TextField tfMaLop;

    @FXML
    private TextField tfTenMon;

    @FXML
    private TextField tfSiSo;

    @FXML
    private TextField tfBatDau;

    @FXML
    private TextField tfKetThuc;

    @FXML
    private TextField tfSoTiet;

    @FXML
    private Label lbTongSoLop;

    @FXML
    private Label lbMin;

    @FXML
    private Label lbMax;

    @FXML
    private Label lbDangGiangDay;

    @FXML
    private Label lbDaKetThuc;

    private ObservableList<LichGiangDay> lichGiangDayList;

    public List<LichGiangDay> getLichGiangDay(){
        List<LichGiangDay> temp = new ArrayList<>() ;
        try {
            String query = "SELECT * from LichGiangDay";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                LichGiangDay item = new LichGiangDay(resultSet);
                temp.add(item);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return temp;
    }

    @FXML
    public void deleteRow(ActionEvent event){
        LichGiangDay currSelectedItem = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().removeAll(currSelectedItem);
        try {
            String query = "Delete from LichGiangDay where STT = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, Integer.toString(currSelectedItem.getSTT()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateRow(ActionEvent event){
        LichGiangDay currSelectedItem = tableView.getSelectionModel().getSelectedItem();

    }

    @FXML
    public void addRow(ActionEvent event){
        String TenGV = tfTenGV.getText().toString();
        if (TenGV.length()== 0){
            System.out.println("Nhap ten giang vien");
            return;
        }
        String MaLop = tfMaLop.getText().toString();
        if (MaLop.length()== 0){
            System.out.println("Nhap ma lop");
            return;
        }
        String TenMon = tfTenMon.getText().toString();
        if (TenMon.length()== 0){
            System.out.println("Nhap ten mon");
            return;
        }
        String SiSo = tfSiSo.getText().toString();
        if (SiSo.length()== 0){
            System.out.println("Nhap ten si so");
            return;
        }
        String BatDau =tfBatDau.getText().toString();
        if (BatDau.length()== 0){
            System.out.println("Nhap ngay bat dau");
            return;
        }
        String KetThuc = tfKetThuc.getText().toString();
        if (KetThuc.length()== 0){
            System.out.println("Nhap ngay ket thuc");
            return;
        }
        String SoTiet = tfSoTiet.getText().toString();
        if (SoTiet.length()== 0){
            System.out.println("Nhap so tiet");
            return;
        }
        else {
            try {
                String query = "insert into LichGiangDay(TenGV, MaLop, TenMon, SiSo, BatDau, KetThuc, SoTiet) values( ? , ? , ? , ? , ? , ? , ?)";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, TenGV);
                statement.setString(2, MaLop);
                statement.setString(3, TenMon);
                statement.setString(4, SiSo);
                statement.setString(5, BatDau);
                statement.setString(6, KetThuc);
                statement.setString(7, SoTiet);
                statement.executeUpdate();
                // lấy phần tử cuối cùng của bảng
                tableView.getSelectionModel().selectLast();
                LichGiangDay last = tableView.getSelectionModel().getSelectedItem();
                // thêm phần tử mới có STT = STT phần tử cuối + 1
                LichGiangDay temp = new LichGiangDay(last.getSTT()+1, TenGV, MaLop, TenMon, Integer.parseInt(SiSo), BatDau, KetThuc, Integer.parseInt(SoTiet));
                lichGiangDayList.add(temp);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        STT.setCellValueFactory(new PropertyValueFactory<LichGiangDay, String>("STT"));
        TenGV.setCellValueFactory(new PropertyValueFactory<LichGiangDay, String>("TenGV"));
        MaLop.setCellValueFactory(new PropertyValueFactory<LichGiangDay, String>("MaLop"));
        TenMon.setCellValueFactory(new PropertyValueFactory<LichGiangDay, String>("TenMon"));
        SiSo.setCellValueFactory(new PropertyValueFactory<LichGiangDay, String>("SiSo"));
        BatDau.setCellValueFactory(new PropertyValueFactory<LichGiangDay, String>("BatDau"));
        KetThuc.setCellValueFactory(new PropertyValueFactory<LichGiangDay, String>("KetThuc"));
        SoTiet.setCellValueFactory(new PropertyValueFactory<LichGiangDay, String>("SoTiet"));
        lichGiangDayList = FXCollections.observableArrayList(getLichGiangDay());
        tableView.setItems(lichGiangDayList);

        tableView.setRowFactory(new Callback<TableView<LichGiangDay>, TableRow<LichGiangDay>>() {
            @Override
            public TableRow<LichGiangDay> call(TableView<LichGiangDay> param) {
                TableRow<LichGiangDay> row = new TableRow<LichGiangDay>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if ((event.getClickCount() == 2)) {
                            //create a new Item and intialize it ...
                            LichGiangDay myItem = new LichGiangDay();
                            tableView.getItems().add(myItem);
                        }
                    }
                });
                return row;
            }
        });

        tableView.setEditable(true);
        TenGV.setCellFactory(TextFieldTableCell.forTableColumn());
        MaLop.setCellFactory(TextFieldTableCell.forTableColumn());

        lbTongSoLop.setText(Integer.toString(lichGiangDayList.size()));
        LopCoSiSoMin();
        LopCoSiSoMax();
        SoLopConDay();
        SoLopDaKetThuc();
    }

    public void LopCoSiSoMin(){
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("Select MaLop from LichGiangDay where SiSo = (select Min(SiSo) from LichGiangDay)");
            while (resultSet.next()){
                lbMin.setText(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void LopCoSiSoMax(){
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("Select MaLop from LichGiangDay where SiSo = (select Max(SiSo) from LichGiangDay)");
            while (resultSet.next()){
                lbMax.setText(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SoLopConDay(){
        try {
            int KetQua =0;
            DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
            Date end = dtf.parse("2018-05-28");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select KetThuc from LichGiangDay");
            while (resultSet.next()){
                Date date = dtf.parse(resultSet.getString(1));
                if (date.compareTo(end)>0) KetQua ++;
            }
            lbDangGiangDay.setText(Integer.toString(KetQua));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void SoLopDaKetThuc(){
        try {
            int KetQua =0;
            DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
            Date end = dtf.parse("2018-05-28");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select KetThuc from LichGiangDay");
            while (resultSet.next()){
                Date date = dtf.parse(resultSet.getString(1));
                if (date.compareTo(end)<0) KetQua ++;
            }
            lbDaKetThuc.setText(Integer.toString(KetQua));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void onEditChanged(TableColumn.CellEditEvent<LichGiangDay, String> lichGiangDayStringCellEditEvent) {
        LichGiangDay lichGiangDay = tableView.getSelectionModel().getSelectedItem();
        lichGiangDay.setTenGV(lichGiangDayStringCellEditEvent.getNewValue());
        lichGiangDay.setMaLop(lichGiangDayStringCellEditEvent.getNewValue());
    }
}
