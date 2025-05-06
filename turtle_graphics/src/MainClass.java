import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
public class MainClass 
{
        public static void main(String[] args)
        {
                new MainClass(); // Create instance of MainClass to set up the application
        }

        public MainClass()
        {
                // Create a TurtleGraphics instance
                TurtleGraphics turtleGraphics = new TurtleGraphics();
                
                JFrame MainFrame = new JFrame("Turtle Graphics");  // Create a frame with title
                MainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                MainFrame.setLayout(new FlowLayout());
                MainFrame.add(turtleGraphics);
                MainFrame.pack();
                
                // Add window listener to handle closing event with save prompt
                MainFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        boolean canExit = handleClosing(turtleGraphics);
                        if (canExit) {
                            // If user has saved or chosen not to save, exit the application
                            System.exit(0);
                        }
                    }
                });
                
                MainFrame.setVisible(true);  // Now display it

                // Display initial instructions to the user
                System.out.println("Turtle Graphics System");
                System.out.println("Available commands:");
                System.out.println("  about - Display about information");
                System.out.println("  penup/pendown - Lift or lower the pen");
                System.out.println("  left X/right X - Turn by X degrees");
                System.out.println("  move X/reverse X - Move forward/backward X units");
                System.out.println("  black/red/green/white/blue - Change pen color");
                System.out.println("  saveimage - Save current drawing as image");
                System.out.println("  loadimage - Load a saved drawing");
                System.out.println("  savecmds - Save command history to file");
                System.out.println("  loadcmds - Load and execute commands from file");
                System.out.println("  reset - Returns turtle to center");
                System.out.println("  clear - clear the canvas");
                System.out.println("  help - displays help section");
        }

        /**
         * Handles the window closing event with save prompts
         * @param turtleGraphics The TurtleGraphics instance to check for unsaved changes
         * @return true if it's okay to exit, false if user cancelled
         */
        private boolean handleClosing(TurtleGraphics turtleGraphics) {
            // Check if there are unsaved changes
            if (turtleGraphics.hasUnsavedChanges()) {
                int response = JOptionPane.showConfirmDialog(
                    null,
                    "You have unsaved changes. Would you like to save before exiting?",
                    "Save Changes?",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

                if (response == JOptionPane.YES_OPTION) {
                    turtleGraphics.saveBeforeExit();
                    return !turtleGraphics.hasUnsavedChanges();
                } else if (response == JOptionPane.NO_OPTION) {
                    return true;
                } else {
                    return false;
                }
            }

            // No unsaved changes, okay to exit
            return true;
        }
}