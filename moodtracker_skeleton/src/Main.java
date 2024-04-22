//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;

public class MoodTrackerApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Mood Tracker");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
public class LoadingScreenController implements Initializable {

    @FXML
    private Label loadingLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize loading screen
        loadingLabel.setText("Loading...");
        // Perform loading tasks (e.g., loading data)
        // Transition to next screen (e.g., calendar)
    }
}
public class CalendarScreenController implements Initializable {

    @FXML
    private DatePicker datePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize calendar screen
        // Set up date picker and show mood entries for selected date
        datePicker.setOnAction(event -> {
            System.out.println("Selected date: " + datePicker.getValue());
            // Implement logic to display mood entries for selected date
        });
    }
}
public class AnalyticsScreenController implements Initializable {

    @FXML
    private BarChart<String, Integer> moodChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize analytics screen
        // Set up mood chart and display mood statistics
        xAxis.setLabel("Mood");
        yAxis.setLabel("Count");

        // Add sample data to the chart
        moodChart.getData().addAll(
                new BarChart.Series<>("Mood Count", new BarChart.Data<>("Happy", 10),
                        new BarChart.Data<>("Sad", 5),
                        new BarChart.Data<>("Neutral", 8)
                )
        );
    }
}
