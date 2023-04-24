package com.example.ecommers_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ProductList {

     TableView<Product> productTable;

    private VBox createTable(ObservableList<Product> data){
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, String> image = new TableColumn<>("Image");
        image.setCellValueFactory(new PropertyValueFactory<>("imageName"));

        image.setCellFactory(col ->{
            return new TableCell<>(){
                @Override
                protected void updateItem(String imageName, boolean empty){
                    super.updateItem(imageName, empty);
                    if(empty || imageName==null){
                        setGraphic(null);
                    }
                    else{
                        ImageView imageView = new ImageView("C:\\Users\\shahaji\\IdeaProjects\\Ecommers_Project\\src\\e-commerce-logo.png");
                        imageView.setFitWidth(150);
                        imageView.setFitHeight(150);
                        setGraphic(imageView);
                    }
                }
            };
        });


        // DATA - Dummy Data

        productTable = new TableView<>();
        //productTable.setItems(data);
        productTable.getColumns().addAll(id, name, price);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(productTable);
        return vbox;
    }

//    public VBox getDummyTable(){
//
//        ObservableList<Product> data = FXCollections.observableArrayList();
//        data.add(new Product(2, "Laptop", 85000 ));
//        data.add(new Product(3, "Iphone", 110000));
//        data.add(new Product(5, "Boat earphone", 1999));
//        data.add(new Product(7, "LED TV", 30000));
//
//        return createTable(data);
//    }

    VBox getAllProducts(){
         ObservableList<Product> data = Product.getProductTable();
        return createTable(data);
    }

    Product getSelectedProducts(){
        return productTable.getSelectionModel().getSelectedItem();
    }

    VBox getProductInCart(ObservableList<Product> data){
        return createTable(data);

    }
}
