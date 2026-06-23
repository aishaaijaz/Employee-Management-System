import db.DBConnection;
import ui.MainMenu;
public class Main {
    public static void main(String[] args) {
        try {
            new MainMenu().start();
        } finally {
            DBConnection.closeConnection(); // always close DB on exit
        }
    }
}
