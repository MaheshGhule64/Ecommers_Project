package com.example.ecommers_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class UserInterface {

    GridPane loginPage;
    GridPane signUpPage;
    HBox headerBar;
    HBox footerBar;

    VBox body;
    Label welcomeLabel;

    ProductList productList = new ProductList();

    VBox productPage;
    Customer loggedInCustomer;
    ObservableList<Product> itemsInCart = FXCollections.observableArrayList();

    Button signInButton;
    Button signUpButton;
    Button placeOrderButton = new Button("Place Order");

   public UserInterface() {
        createLoginPage();
        createHeaderBar();
        createSignupPage();
        createFooterBar();
    }

    BorderPane createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(800, 600);
        //root.getChildren().addAll(loginPage);
        //root.setCenter(body);


        body = new VBox();
        welcomeLabel = new Label();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        productPage = productList.getAllProducts();
        body.getChildren().add(productPage);
        root.setCenter(body);

        root.setTop(headerBar);
        root.setBottom(footerBar);

        return root;
    }


    private void createLoginPage() {

        Text userNameText = new Text("Username");
        TextField userName = new TextField();
        userName.setPromptText("Type Your Username here");

        Text passwordText = new Text("Password");
        PasswordField password = new PasswordField();
        password.setPromptText("Type Your Password here");



        Label messegeLabel = new Label("Hi");


        Button loginButton = new Button("Login");


        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = userName.getText();
                String pass = password.getText();
                Login login = new Login();
                loggedInCustomer = login.customerLogin(name, pass);

                if(loggedInCustomer!=null){
                  //  messegeLabel.setText("Welcome" + loggedInCustomer.getName());
                    welcomeLabel.setText("Welcome" + loggedInCustomer.getName());
                    headerBar.getChildren().add(welcomeLabel);
                    body.getChildren().clear();
                    body.getChildren().add(productPage);
                }
                else{
                    messegeLabel.setText("Login Failed! Try Again Later");
                }

            }
        });


        loginPage = new GridPane();
        //loginPage.setPrefSize(800, 600);
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setVgap(20);
        loginPage.setHgap(20);

        loginPage.add(userNameText, 0, 0);
        loginPage.add(userName, 1, 0);
        loginPage.add(passwordText, 0, 1);
        loginPage.add(password, 1, 1);
        loginPage.add(loginButton, 1, 2);
        loginPage.add(messegeLabel, 1, 3);

    }

            private void createSignupPage(){
                Text nameText = new Text("Full name");
                Text mobileText = new Text("Mobile No.");
                Text emailText = new Text("Email");
                Text passwordText = new Text("Password");


                TextField name = new TextField();
                TextField mobile = new TextField();
                TextField email = new TextField();
                PasswordField password = new PasswordField();

                Label messageLabel = new Label();

                signUpButton = new Button("Sign Up");

                signUpButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try{
                            if(email.getText().isEmpty() || mobile.getText().isEmpty() || name.getText().isEmpty() || password.getText().isEmpty()){
                                messageLabel.setText("All field are required");
                                return;
                            }

                            String uemail = email.getText();
                            String umobile = mobile.getText();
                            String uname = name.getText();

                            MessageDigest md = MessageDigest.getInstance("SHA-512");
                            String hashedPassword = new String(md.digest(password.getText().getBytes(StandardCharsets.UTF_8)));

                            Customer customer = new Customer(uname, uemail, umobile, hashedPassword);
                            boolean res = SignUp.customerLogin(customer);

                            if(res){
                                createDialog("Registered Successfully");
                            }
                            else{
                                createDialog("Registered Failed");
                            }
                        }
                        catch (NoSuchAlgorithmException e){
                            throw new RuntimeException(e);
                        }
                    }
                });


                signUpPage = new GridPane();
                signUpPage.setVgap(10);
                signUpPage.setStyle("-fx-border-color: black;-fx-border-width: 2pt;-fx-padding: 10pt;-fx-max-width: 300pt");
                signUpPage.setAlignment(Pos.CENTER);
                signUpPage.setHgap(10);
                signUpPage.setVgap(10);
                signUpPage.add(nameText,0,0);
                signUpPage.add(name, 1, 0);
                signUpPage.add(emailText, 0,1);
                signUpPage.add(email, 1, 1);
                signUpPage.add(mobileText, 0, 2);
                signUpPage.add(mobile, 1, 2);
                signUpPage.add(passwordText, 0, 3);
                signUpPage.add(password, 1, 3);
                signUpPage.add(messageLabel, 0, 4);
                signUpPage.add(signUpButton, 1, 4);
            }
            private void createHeaderBar() {

                Button homeButton = new Button();
                Image logo = new Image("C:\\Users\\shahaji\\IdeaProjects\\Ecommers_Project\\src\\e-commerce-logo.png");
                ImageView logoView = new ImageView(logo);
                logoView.setFitHeight(20);
                logoView.setFitWidth(85);
                homeButton.setGraphic(logoView);

                TextField searchBar = new TextField();
                searchBar.setPromptText("Search here");
                searchBar.setPrefWidth(280);
                //searchBar.setAlignment(Pos.CENTER);
                Button searchButton = new Button("Search");
                Button cartButton = new Button("Cart");

                signInButton = new Button("Sign In");
                signUpButton = new Button("Sign Up");


                headerBar = new HBox();
                headerBar.setSpacing(10);
                headerBar.setPadding(new Insets(10));

                headerBar.getChildren().addAll(homeButton,searchBar, searchButton, signInButton, signUpButton, cartButton);
                headerBar.setAlignment(Pos.CENTER);

                signInButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        body.getChildren().clear();
                        body.getChildren().add(loginPage);
                        headerBar.getChildren().remove(signInButton);
                        footerBar.setVisible(false);
                    }
                });

                cartButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        VBox cartPage = productList.getProductInCart(itemsInCart);
                        cartPage.setAlignment(Pos.CENTER);
                        cartPage.setSpacing(10);
                        cartPage.getChildren().add(placeOrderButton);
                        body.getChildren().clear();
                        body.getChildren().add(cartPage);
                        footerBar.setVisible(false);
                    }
                });

                placeOrderButton.setAlignment(Pos.CENTER);
                placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(itemsInCart==null){
                            createDialog("Please add items in cart to place order");
                            return;
                        }
                        if(loggedInCustomer==null){
                            createDialog("Please login first to place order");
                            return;
                        }

                        int count = Order.placeMultipleOrder(loggedInCustomer, itemsInCart);
                        if(count!=0){
                            createDialog("Order placed for"+ count +"items successfully");
                        }
                        else{
                            createDialog("Order Failed");
                        }
                    }
                });

                homeButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        body.getChildren().clear();
                        body.getChildren().add(productPage);
                        footerBar.setVisible(true);
                        if(loggedInCustomer == null && !headerBar.getChildren().contains(signInButton))
                            headerBar.getChildren().add(signInButton);
                    }
                });

                signUpButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        body.getChildren().clear();
                        body.getChildren().add(signUpPage);
                    }
                });

            }
                private void createFooterBar(){

                    Button buyNowButton = new Button("Buy Now");
                    Button addToCartButton = new Button("Add To Cart");

                    footerBar = new HBox();

                    footerBar.setSpacing(10);
                    footerBar.setPadding(new Insets(10));

                    footerBar.getChildren().addAll(buyNowButton, addToCartButton);
                    footerBar.setAlignment(Pos.CENTER);

                    buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Product product = productList.getSelectedProducts();

                            if(product==null){
                                createDialog("Please Select product to  place order");
                                return;
                            }
                            if(loggedInCustomer==null){
                                createDialog("Please login first to place an order");
                                return;
                            }

                            boolean count = Order.placeOrder(loggedInCustomer, product);
                            if(count){
                                createDialog("Order placed successfully");
                            }
                            else{
                                createDialog("Order failed");
                            }
                        }
                    });

                    addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Product product = productList.getSelectedProducts();
                            if(product==null){
                                createDialog("please select product to add in cart");
                                return;
                            }

                            itemsInCart.add(product);
                            createDialog("Item added in cart");
                        }
                    });
    }

    void createDialog(String message){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setContentText(message);
                    alert.show();
                }

}