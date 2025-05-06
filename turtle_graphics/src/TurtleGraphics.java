import java.awt.Color;
import uk.ac.leedsbeckett.oop.LBUGraphics;
import javax.swing.JOptionPane; // Import JOptionPane for pop-up dialogs

// Imports for file handling and image I/O
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import javax.imageio.ImageIO; // Now used
import javax.swing.JFileChooser; // Now used
import javax.swing.filechooser.FileNameExtensionFilter; // Now used
import java.awt.image.BufferedImage; // Now used

public class TurtleGraphics extends LBUGraphics {

    private List<String> commandHistory = new ArrayList<>(); // Now used
    private boolean imageChanged = false;
    private boolean commandsChanged = false;
    // Optional: Remember last save locations
    private File lastImageFile = null; // Now used
    private File lastCommandsFile = null; // Now used
    private Color initialPenColor = Color.BLACK;
    private int initialPenWidth = 1;
    private int currentPenWidth = 1;
    private void showHelp() {
        String helpText = """
        ===== TURTLE GRAPHICS HELP =====
        Type commands in lowercase
        DRAWING:
          square <size>      - Draw a square
          triangle <size>    - Draw equilateral triangle
          triangle <a,b,c>   - Draw custom triangle
        
        APPEARANCE:
          black/red/green    - Change pen color
          pencolour <r,g,b>  - Custom RGB color
          penwidth <size>    - Set pen thickness
          
        MOVEMENT:
          move <distance>    - Move forward (example:, move 100)
          left <degrees>     - Turn left (example:, left 90)
          right <degrees>    - Turn right (example:right 45)
          penup/pendown      - Lift or lower the pen
          
        SYSTEM:
          reset              - Reset turtle position
          clear              - Clear canvas
          about              - Show program info
          help               - Show this message
        
        FILE OPERATIONS:
          saveimage          - Save drawing as PNG
          loadimage          - Load PNG image
          savecmds           - Save command history
          loadcmds           - Run saved commands
        """;


        JOptionPane.showMessageDialog(this, helpText, "Turtle Graphics Help", JOptionPane.INFORMATION_MESSAGE);


        System.out.println("\nAvailable commands:\n" + helpText.split("=====")[1]);
    }


    public TurtleGraphics() {
        super();
        imageChanged = false;
        commandsChanged = false;
        initialPenColor = getPenColour();
    }

    @Override
    public void about() {
        // First call super.about() to perform the original dance
        super.about();
        
        // Append message with my name
        displayMessage("Turtle Graphics System by Sai Joshi");
    }

    @Override
    public void reset() {
        super.reset(); // Use the library's reset
        drawOn(); // Ensure pen is down after reset by default
        
        // Reset pen color and width to initial settings
        setPenColour(initialPenColor);
        setPenWidth(initialPenWidth);
    }


    @Override
    public void processCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: Empty command", "Command Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String originalCommand = command; // Keep original for history if valid
        command = command.trim().toLowerCase();
        String[] parts = command.split("\\s+", 2);
        String cmd = parts[0];
        boolean commandExecutedSuccessfully = false; // Flag to track if command ran
        boolean affectsDrawingOrState = false; // Flag if command changes image/history

        switch (cmd) {
            case "about":
                about();
                // 'about' doesn't change state or history
                break;
            case "penup":
                drawOff();
                displayMessage("Pen is up");
                commandExecutedSuccessfully = true;
                affectsDrawingOrState = true; // Changes pen state
                break;
            case "pendown":
                drawOn();
                displayMessage("Pen is down");
                commandExecutedSuccessfully = true;
                affectsDrawingOrState = true; // Changes pen state
                break;
            case "help":
                showHelp();
                commandExecutedSuccessfully = true;
                break;
            case "reset":
                reset();
                displayMessage("Turtle reset to center");
                // Resetting clears history implicitly if we load later
                // Let's mark image as potentially changed
                imageChanged = true;
                commandExecutedSuccessfully = true;
                affectsDrawingOrState = true;
                break;
            case "clear":
                if (!checkUnsavedImage()) return; // Stop if user cancels
                clear();
                displayMessage("Canvas cleared");
                imageChanged = true;
                commandExecutedSuccessfully = true;
                affectsDrawingOrState = true;
                break;
            // Color commands
            case "black": case "red": case "green": case "white":
            case "blue": case "yellow": case "orange": case "pink": case "cyan":
                setPenColour(getColorFromString(cmd));
                displayMessage("Pen color set to " + cmd);
                commandExecutedSuccessfully = true;
                affectsDrawingOrState = true; // Changes pen color state
                break;
            // Parameterized commands
            case "left": case "right": case "move": case "reverse":
                commandExecutedSuccessfully = handleParameterizedCommand(cmd, parts);
                affectsDrawingOrState = commandExecutedSuccessfully;
                break;
            // New shape commands
            case "square":
                commandExecutedSuccessfully = handleSquareCommand(parts);
                affectsDrawingOrState = commandExecutedSuccessfully;
                break;
            case "triangle":
                commandExecutedSuccessfully = handleTriangleCommand(parts);
                affectsDrawingOrState = commandExecutedSuccessfully;
                break;
            // New styling commands
            case "pencolour":
                commandExecutedSuccessfully = handlePenColourCommand(parts);
                affectsDrawingOrState = commandExecutedSuccessfully;
                break;
            case "penwidth":
                commandExecutedSuccessfully = handlePenWidthCommand(parts);
                affectsDrawingOrState = commandExecutedSuccessfully;
                break;
            // Save/Load commands
            case "saveimage":
                saveImage();
                // Saving doesn't count as a drawing command for history
                break;
            case "loadimage":
                loadImage();
                // Loading doesn't count as a drawing command for history
                break;
            case "savecmds":
                saveCommands();
                // Saving doesn't count as a drawing command for history
                break;
            case "loadcmds":
                loadCommands();
                // Loading doesn't count as a drawing command for history
                break;
            default:
                JOptionPane.showMessageDialog(this, "Error: Invalid command '" + cmd + "'", "Command Error", JOptionPane.ERROR_MESSAGE);
                break;
        }

        // Add to history only if it was a valid, executed command that affects state/drawing
        if (commandExecutedSuccessfully && affectsDrawingOrState) {
             commandHistory.add(originalCommand); // Add the original command string
             commandsChanged = true; // Mark commands as changed
        }
    }

    private boolean handleParameterizedCommand(String cmd, String[] parts) {
        // Parameter validation (using JOptionPane as before)
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: Missing parameter for '" + cmd + "'", "Command Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String paramStr = parts[1].trim();
        int parameter;
        try {
            parameter = Integer.parseInt(paramStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Invalid numeric parameter for '" + cmd + "': " + paramStr, "Command Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (parameter < 0 && !(cmd.equals("reverse") || cmd.equals("move"))) {
             JOptionPane.showMessageDialog(this, "Error: Parameter cannot be negative for '" + cmd + "'", "Command Error", JOptionPane.ERROR_MESSAGE);
             return false;
        }
        if (parameter == 0 && (cmd.equals("move") || cmd.equals("reverse") || cmd.equals("left") || cmd.equals("right"))) {
             JOptionPane.showMessageDialog(this, "Error: Parameter must be non-zero for '" + cmd + "'", "Command Error", JOptionPane.ERROR_MESSAGE);
             return false;
        }

        // Execute the command using library methods
        try {
            switch (cmd) {
                case "left":
                    left(parameter);
                    displayMessage("Turned left by " + parameter + " degrees");
                    break;
                case "right":
                    right(parameter);
                    displayMessage("Turned right by " + parameter + " degrees");
                    // Turning itself doesn't change the image until a move
                    break;
                case "move":
                    forward(parameter);
                    displayMessage("Moved forward by " + parameter + " units");
                    imageChanged = true; // Moving changes the image
                    break;
                case "reverse":
                    forward(-parameter);
                    displayMessage("Moved backward by " + parameter + " units");
                    imageChanged = true; // Moving changes the image
                    break;
                default:
                     // Should not happen if called correctly
                     JOptionPane.showMessageDialog(this, "Internal Error: Unknown parameterized command '" + cmd + "'", "Internal Error", JOptionPane.ERROR_MESSAGE);
                     return false;
            }
            return true; // Indicate success
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error executing command '" + cmd + "': " + e.getMessage(), "Execution Error", JOptionPane.ERROR_MESSAGE);
            return false; // Indicate failure
        }
    }

    private boolean handleSquareCommand(String[] parts) {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: Missing parameter for 'square'", "Command Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int sideLength = Integer.parseInt(parts[1].trim());
            if (sideLength <= 0) {
                JOptionPane.showMessageDialog(this, "Error: Side length must be positive for 'square'", "Command Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            drawSquare(sideLength);
            displayMessage("Square drawn with side length " + sideLength);
            imageChanged = true;
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Invalid numeric parameter for 'square': " + parts[1], "Command Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean handleTriangleCommand(String[] parts) {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: Missing parameters for 'triangle'", "Command Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String[] params = parts[1].trim().split(",");

        if (params.length == 1) {
            // Equilateral triangle: "triangle size"
            try {
                int size = Integer.parseInt(params[0].trim());
                if (size <= 0) {
                    JOptionPane.showMessageDialog(this, "Error: Size must be positive for 'triangle'", "Command Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                drawEquilateralTriangle(size);
                displayMessage("Equilateral triangle drawn with side length " + size);
                imageChanged = true;
                return true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error: Invalid size for 'triangle': " + params[0], "Command Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else if (params.length == 3) {
            // Custom triangle: "triangle side1,side2,side3"
            try {
                int side1 = Integer.parseInt(params[0].trim());
                int side2 = Integer.parseInt(params[1].trim());
                int side3 = Integer.parseInt(params[2].trim());

                if (side1 <= 0 || side2 <= 0 || side3 <= 0) {
                    JOptionPane.showMessageDialog(this, "Error: Side lengths must be positive for 'triangle'", "Command Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                if (drawTriangle(side1, side2, side3)) {
                    displayMessage("Triangle drawn with sides " + side1 + ", " + side2 + ", " + side3);
                    imageChanged = true;
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Invalid side lengths for 'triangle' (violation of triangle inequality)", "Command Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error: Invalid numeric parameters for 'triangle'", "Command Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error: 'triangle' requires either 1 parameter (equilateral) or 3 parameters (custom)", "Command Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    private boolean handlePenColourCommand(String[] parts) {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: Missing parameters for 'pencolour'", "Command Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String[] rgbValues = parts[1].trim().split(",");
        if (rgbValues.length == 3) {
            // RGB format: "pencolour r,g,b"
            try {
                int r = Integer.parseInt(rgbValues[0].trim());
                int g = Integer.parseInt(rgbValues[1].trim());
                int b = Integer.parseInt(rgbValues[2].trim());
                
                // Validate RGB values (0-255)
                if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
                    JOptionPane.showMessageDialog(this, "Error: RGB values must be between 0 and 255", "Command Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                
                // Set custom RGB color
                setPenColour(new Color(r, g, b));
                displayMessage("Pen color set to RGB: " + r + "," + g + "," + b);
                return true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error: Invalid numeric RGB values for 'pencolour'", "Command Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            // Named color format: "pencolour red"
            String colorName = parts[1].trim().toLowerCase();
            Color color = getColorFromString(colorName);
            if (color != null) {
                setPenColour(color);
                displayMessage("Pen color set to " + colorName);
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Error: Invalid color name or format for 'pencolour'", "Command Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }


    private boolean handlePenWidthCommand(String[] parts) {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: Missing parameter for 'penwidth'", "Command Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int width = Integer.parseInt(parts[1].trim());
            if (width <= 0) {
                JOptionPane.showMessageDialog(this, "Error: Width must be positive for 'penwidth'", "Command Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            setPenWidth(width);
            displayMessage("Pen width set to " + width);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Invalid numeric parameter for 'penwidth': " + parts[1], "Command Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    private Color getColorFromString(String colorName) {
        switch (colorName) {
            case "black": return Color.BLACK;
            case "red": return Color.RED;
            case "green": return Color.GREEN;
            case "white": return Color.WHITE;
            case "blue": return Color.BLUE;
            case "yellow": return Color.YELLOW;
            case "orange": return Color.ORANGE;
            case "pink": return Color.PINK;
            case "cyan": return Color.CYAN;
            default: return null; // Return null if unknown
        }
    }



    private void saveImage() {

        JFileChooser fileChooser = new JFileChooser(lastImageFile != null ? lastImageFile.getParentFile() : null);
        fileChooser.setDialogTitle("Save Image As");
        // Filter for PNG files
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images (*.png)", "png"));
        // Suggest a default filename
        fileChooser.setSelectedFile(new File(lastImageFile != null ? lastImageFile.getName() : "turtle_drawing.png"));
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Ensure .png extension
            if (!fileToSave.getName().toLowerCase().endsWith(".png")) {
                fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".png");
            }
            // Confirm overwrite if file exists
            if (fileToSave.exists()) {
                int response = JOptionPane.showConfirmDialog(this,
                        "File '" + fileToSave.getName() + "' already exists.\nOverwrite?",
                        "Confirm Overwrite",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.NO_OPTION) {
                    displayMessage("Save cancelled.");
                    return; // Don't save
                }
            }
            try {
                BufferedImage image = getBufferedImage(); // Get image from LBUGraphics
                if (ImageIO.write(image, "png", fileToSave)) { // Save as PNG
                    displayMessage("Image saved to " + fileToSave.getName());
                    imageChanged = false; // Mark image as saved
                    lastImageFile = fileToSave; // Remember this file location
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Could not save image (ImageIO returned false).", "Save Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving image: " + ex.getMessage(), "Save I/O Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this, "An unexpected error occurred during image save: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            displayMessage("Save image cancelled.");
        }
    }

    private void loadImage() {
        // Check for unsaved image changes before loading
        if (!checkUnsavedImage()) {
            displayMessage("Load image cancelled.");
            return; // User cancelled save prompt
        }

        JFileChooser fileChooser = new JFileChooser(lastImageFile != null ? lastImageFile.getParentFile() : null);
        fileChooser.setDialogTitle("Load Image");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images (*.png)", "png"));

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(fileToLoad); // Read the image file
                if (image != null) {

                    commandHistory.clear();
                    commandsChanged = false;

                    setBufferedImage(image);
                    displayMessage("Image loaded from " + fileToLoad.getName());
                    imageChanged = false;
                    lastImageFile = fileToLoad; // Remember location
                    repaint(); // Ensure the new image is displayed
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Could not read image file (is it a valid PNG?).", "Load Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading image: " + ex.getMessage(), "Load I/O Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this, "An unexpected error occurred during image load: " + ex.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            displayMessage("Load image cancelled.");
        }
    }
    private void saveCommands() {

        JFileChooser fileChooser = new JFileChooser(lastCommandsFile != null ? lastCommandsFile.getParentFile() : null);
        fileChooser.setDialogTitle("Save Commands As");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));
        fileChooser.setSelectedFile(new File(lastCommandsFile != null ? lastCommandsFile.getName() : "turtle_commands.txt"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
             // Ensure .txt extension
            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".txt");
            }

            // Confirm overwrite
            if (fileToSave.exists()) {
                int response = JOptionPane.showConfirmDialog(this,
                        "File '" + fileToSave.getName() + "' already exists.\nOverwrite?",
                        "Confirm Overwrite",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.NO_OPTION) {
                    displayMessage("Save commands cancelled.");
                    return; // Don't save
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                for (String command : commandHistory) {
                    writer.write(command);
                    writer.newLine();
                }
                displayMessage("Commands saved to " + fileToSave.getName());
                commandsChanged = false;
                lastCommandsFile = fileToSave; // Remember location
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving commands: " + ex.getMessage(), "Save I/O Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this, "An unexpected error occurred during command save: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
             displayMessage("Save commands cancelled.");
        }
    }

     private void loadCommands() {
        // Check for unsaved changes (both image and commands) before loading new commands
        if (!checkUnsavedChanges()) {
            displayMessage("Load commands cancelled.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser(lastCommandsFile != null ? lastCommandsFile.getParentFile() : null);
        fileChooser.setDialogTitle("Load Commands");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            List<String> loadedCommands = new ArrayList<>();
            // Use try-with-resources for automatic closing
            try (BufferedReader reader = new BufferedReader(new FileReader(fileToLoad))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim(); // Trim whitespace
                    if (!line.isEmpty() && !line.startsWith("//") && !line.startsWith("#")) { // Ignore empty lines and comments
                        loadedCommands.add(line);
                    }
                }

                // Clear current state before executing
                clear(); // Clear the drawing
                reset(); // Reset turtle position/angle
                commandHistory.clear(); // Clear current history
                imageChanged = false; // Start fresh image state
                commandsChanged = false; // Start fresh command state

                displayMessage("Loading and executing commands from " + fileToLoad.getName() + "...");
                System.out.println("--- Executing Loaded Commands ---"); // Output to console


                for (String command : loadedCommands) {
                    System.out.println("> " + command); // Show command on console
                    processLoadedCommand(command);

                    try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                }

                System.out.println("--- Finished Loading Commands ---");
                displayMessage("Commands loaded and executed.");
                imageChanged = false;
                lastCommandsFile = fileToLoad; // Remember location

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading or reading commands file: " + ex.getMessage(), "Load I/O Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(this, "An unexpected error occurred during command load/execution: " + ex.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            displayMessage("Load commands cancelled.");
        }
    }

     private void processLoadedCommand(String command) {
         // Simplified version of processCommand, doesn't add to history or check unsaved
         if (command == null || command.trim().isEmpty()) return;

         String originalCommand = command;
         command = command.trim().toLowerCase();
         String[] parts = command.split("\\s+", 2);
         String cmd = parts[0];

         switch (cmd) {
             case "about": about(); break; // Can still call about if in file
             case "penup": drawOff(); displayMessage("Pen is up"); break;
             case "pendown": drawOn(); displayMessage("Pen is down"); break;
             case "reset": reset(); displayMessage("Turtle reset to center"); break;
             case "clear": clear(); displayMessage("Canvas cleared"); break;
             case "black": case "red": case "green": case "white":
             case "blue": case "yellow": case "orange": case "pink": case "cyan":
                 setPenColour(getColorFromString(cmd));
                 displayMessage("Pen color set to " + cmd);
                 break;
             case "left": case "right": case "move": case "reverse":
                 // Use handleParameterizedCommand, but ignore its return for history
                 handleParameterizedCommand(cmd, parts);
                 break;
             // Ignore save/load commands within a loaded file
             case "saveimage": case "loadimage": case "savecmds": case "loadcmds":
                 System.out.println("Skipping '" + cmd + "' command found in loaded file.");
                 break;
             default:
                 // Still show errors for invalid commands found in the file
                 JOptionPane.showMessageDialog(this, "Error in loaded file: Invalid command '" + cmd + "'", "Load Command Error", JOptionPane.ERROR_MESSAGE);
                 break;
         }
         commandHistory.add(originalCommand); // Add command to history after processing it
     }

    // --- Unsaved Changes Check Methods ---

    private boolean checkUnsavedImage() {
        if (imageChanged) {
            int response = JOptionPane.showConfirmDialog(this,
                    "The current image has unsaved changes.\nDo you want to save them first?",
                    "Unsaved Image",
                    JOptionPane.YES_NO_CANCEL_OPTION, // Provide Yes, No, Cancel
                    JOptionPane.WARNING_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                saveImage(); // Attempt to save
                return !imageChanged;
            } else return response == JOptionPane.NO_OPTION;
        }
        return true; // No unsaved changes, okay to proceed
    }

    private boolean checkUnsavedCommands() {
        if (commandsChanged) {
            int response = JOptionPane.showConfirmDialog(this,
                    "The current command history has unsaved changes.\nDo you want to save them first?",
                    "Unsaved Commands",
                    JOptionPane.YES_NO_CANCEL_OPTION, // Provide Yes, No, Cancel
                    JOptionPane.WARNING_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                saveCommands();
                // Proceed only if save was successful (commandsChanged is now false)
                return !commandsChanged;
            } else if (response == JOptionPane.NO_OPTION) {
                return true;
            } else {
                return false; // User cancelled the operation
            }
        }
        return true; // No unsaved changes, okay to proceed
    }

    private boolean checkUnsavedChanges() {
        if (!checkUnsavedCommands()) {
            return false;
        }
        if (!checkUnsavedImage()) {
             return false;
        }
        return true;
    }


    public boolean hasUnsavedChanges() {
        return imageChanged || commandsChanged;
    }


    public void saveBeforeExit() {
        if (commandsChanged) {
            saveCommands();
        }
        
        // Then check image
        if (imageChanged) {
            saveImage();
        }
    }


    @Override
    public void displayMessage(String message) {
        System.out.println(message);
        super.displayMessage(message);
    }


    private void drawEquilateralTriangle(int sideLength) {
        for (int i = 0; i < 3; i++) {
            forward(sideLength);
            right(120); // 120 degrees external angle for equilateral triangle
        }
    }


    private boolean drawTriangle(int side1, int side2, int side3) {
        // Check if the sides can form a valid triangle
        if (side1 + side2 <= side3 || side1 + side3 <= side2 || side2 + side3 <= side1) {
            return false; // Invalid triangle (doesn't satisfy triangle inequality)
        }

        // Calculate angles using the law of cosines
        double angleA = Math.acos((side2*side2 + side3*side3 - side1*side1) / (2.0 * side2 * side3));
        double angleB = Math.acos((side1*side1 + side3*side3 - side2*side2) / (2.0 * side1 * side3));
        double angleC = Math.acos((side1*side1 + side2*side2 - side3*side3) / (2.0 * side1 * side2));

        // Convert to degrees
        angleA = Math.toDegrees(angleA);
        angleB = Math.toDegrees(angleB);
        angleC = Math.toDegrees(angleC);

        // Draw the triangle
        forward(side1);
        right(String.valueOf((int)(180 - angleA))); // Convert to String
        forward(side2);
        right(String.valueOf((int)(180 - angleB))); // Convert to String
        forward(side3);
        right(String.valueOf((int)(180 - angleC))); // Convert to String

        return true;
    }


    private void drawSquare(int sideLength) {
        for (int i = 0; i < 4; i++) {
            forward(sideLength);
            right(90); // 90 degree turns for a square
        }
    }


    public int getPenWidth() {
        return currentPenWidth;
    }

    private void setPenWidth(int width) {
        currentPenWidth = width;
        displayMessage("Note: Pen width set to " + width );
    }
}