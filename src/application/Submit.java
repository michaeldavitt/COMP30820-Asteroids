package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.lang.Character;

public class Submit extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }


    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Submit your Information");
        Button button1 = new Button("Submit");
        button1.setLayoutX(200);
        button1.setLayoutY(230);

        Label label1 = new Label("Your name:");
        label1.setLayoutX(100);
        label1.setLayoutY(100);

        Label label2 = new Label("Your score:");
        label2.setLayoutX(100);
        label2.setLayoutY(150);

        TextField textField1 = new TextField();
        textField1.setPromptText("Player Name");
        textField1.setLayoutX(180);
        textField1.setLayoutY(100);

        TextField textField2 = new TextField();
        textField2.setPromptText("Your Score");
        textField2.setLayoutX(180);
        textField2.setLayoutY(150);

        AnchorPane root1 = new AnchorPane();
        root1.getChildren().addAll(button1, label1, label2, textField1, textField2);


        Scene scene1 = new Scene(root1, 500, 300);
        primaryStage.setScene(scene1);
        primaryStage.show();

        button1.setOnAction(e1 -> {
            String score = textField2.getText();
            String player = textField1.getText();


            //  Judge if the score is legal.
            if (score.length() == 0) {
                Label label3 = new Label("Score should not be empty!");
                label3.setLayoutX(80);
                label3.setLayoutY(50);

                Button rewrite = new Button("Rewrite");
                rewrite.setLayoutX(118);
                rewrite.setLayoutY(80);

                rewrite.setOnAction(erw -> {
                    primaryStage.setScene(scene1);
                });

                AnchorPane root2 = new AnchorPane();
                root2.getChildren().addAll(label3, rewrite);

                Scene scene2 = new Scene(root2, 300, 150);
                primaryStage.setScene(scene2);
                return;
            }

            for (int i = 0; i < score.length(); i++) {
                // if the score is an integer string
                if (!Character.isDigit(score.charAt(i))) {
                    Label label3 = new Label("Score should be made up of digits only!");
                    label3.setLayoutX(80);
                    label3.setLayoutY(50);

                    Button rewrite = new Button("Rewrite");
                    rewrite.setLayoutX(160);
                    rewrite.setLayoutY(80);

                    rewrite.setOnAction(erw -> {
                        primaryStage.setScene(scene1);
                    });

                    AnchorPane root2 = new AnchorPane();
                    root2.getChildren().addAll(label3, rewrite);

                    Scene scene2 = new Scene(root2, 380, 150);
                    primaryStage.setScene(scene2);
                    return;
                }
            }

            // Judge if the player's name is legal.
            if (player.length() < 3) {
                Label label3 = new Label("There should be at least three characters in your name");
                label3.setLayoutX(80);
                label3.setLayoutY(50);

                Button rewrite = new Button("Rewrite");
                rewrite.setLayoutX(190);
                rewrite.setLayoutY(80);

                rewrite.setOnAction(erw -> {
                    primaryStage.setScene(scene1);
                });

                AnchorPane root2 = new AnchorPane();
                root2.getChildren().addAll(label3, rewrite);

                Scene scene2 = new Scene(root2, 440, 150);
                primaryStage.setScene(scene2);
                return;
            }

            for (int i = 0; i < player.length(); i++) {
                char char_now = player.charAt(i);
                if ((!(char_now >= 97 && char_now <= 122)) && (!(char_now >= 65 && char_now <= 90)) &&(!(char_now == 32))) {
                    Label label3 = new Label("The characters in name should consist of largercase or lowercase letters or space");
                    label3.setLayoutX(80);
                    label3.setLayoutY(50);

                    Button rewrite = new Button("Rewrite");
                    rewrite.setLayoutX(250);
                    rewrite.setLayoutY(80);

                    rewrite.setOnAction(erw -> {
                        primaryStage.setScene(scene1);
                    });

                    AnchorPane root2 = new AnchorPane();
                    root2.getChildren().addAll(label3, rewrite);

                    Scene scene2 = new Scene(root2, 600, 150);
                    primaryStage.setScene(scene2);
                    return;
                }

                if (char_now == 32 && i != player.length() - 1) {
                    if (player.charAt(i + 1) == 32){
                        Label label3 = new Label("Your name should be separated by only one space");
                        label3.setLayoutX(80);
                        label3.setLayoutY(50);

                        Button rewrite = new Button("Rewrite");
                        rewrite.setLayoutX(190);
                        rewrite.setLayoutY(80);

                        rewrite.setOnAction(erw -> {
                            primaryStage.setScene(scene1);
                        });

                        AnchorPane root2 = new AnchorPane();
                        root2.getChildren().addAll(label3, rewrite);

                        Scene scene2 = new Scene(root2, 430, 150);
                        primaryStage.setScene(scene2);
                        return;
                    }
                }
            }

            Label label4 = new Label("Are you sure you want to upload your record?");
            label4.setLayoutX(130);
            label4.setLayoutY(60);

            Button button_sure = new Button("Yes");
            button_sure.setLayoutX(160);
            button_sure.setLayoutY(100);

            Button button_not_sure = new Button("No");
            button_not_sure.setLayoutX(280);
            button_not_sure.setLayoutY(100);

            AnchorPane root3 = new AnchorPane();
            root3.getChildren().addAll(label4, button_sure, button_not_sure);

//            button_sure.setOnAction(ebs -> {
//                // renew the ranking list
//                // turn to the ranking list
//            });

            button_not_sure.setOnAction(ebns -> {
                Button btn_play_again = new Button("Play Again");
                btn_play_again.setLayoutX(150);
                btn_play_again.setLayoutY(50);


//                btn_play_again.setOnAction(epa -> {
//                   // restart the game
//                });

                Button btn_quit = new Button("Quit Now");
                btn_quit.setLayoutX(290);
                btn_quit.setLayoutY(50);
                btn_quit.setOnAction(eq -> {
                   primaryStage.close();
                });

                AnchorPane root4 = new AnchorPane();
                root4.getChildren().addAll(btn_play_again, btn_quit);

                Scene scene4 = new Scene(root4, 520, 130);
                primaryStage.setScene(scene4);
            });

            Scene scene3 = new Scene(root3, 500, 150);
            primaryStage.setScene(scene3);
        });
    }
}
