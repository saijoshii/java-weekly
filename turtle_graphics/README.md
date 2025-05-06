# Turtle-Graphics-program
Name: Sai Joshi
Section: E, Bsc(hons)computing
Group: CSC (Duncan)

#This project is a Java-based Turtle Graphics application designed to provide a simple and interactive way of drawing 
shapes using turtle commands.

 Key Features

    - user can draw with turtle by executing commands.
    - Users can also save and load their command scripts in TXT format.
    - Pen width is adjustable, and the drawing tools include commands for basic shapes like squares and triangles.
    - The program allows users to save their drawn images as PNG files and load them back into the application.
  
How to use?
By running the program from main file, a interface appears. Here user can implement commands fro various
purposes. These commands are:
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

By using these commands user can run the program easily.