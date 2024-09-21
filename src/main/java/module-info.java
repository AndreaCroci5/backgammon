module it.croci.games.backgammon {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    exports it.croci.games.backgammon.view;
    opens it.croci.games.backgammon.view to javafx.fxml;
}