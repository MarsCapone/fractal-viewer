#COMP1206 Coursework

##Completed Sections

* Part 1
* Part 2
* Part 3
* Part 4
* Part 5
* Part 6

##Extensions 

* Ability to jump to specific areas on either set. Controls available are:
    * (X,Y,R) values for Mandelbrot and Julia Sets, where X and Y are the 
    coordinated for the centre of the JPanel. R is the radius of the view 
    (the zoom)
    * Julia Set Constant setter, allows the user to jump to a Julia Set of
    a specific constant. The checking for acceptable inputs is fairly
    permissive. It has this Regular Expression:
    `"\s*((\+?|-)\s*\d*\.?\d+)\s*(\+|-)\s*(\d*\.?\d+)\s*(i|I)?\s*"`
* Ability to set the order of the Multibrot set. i.e The ability to set 'd'
in the generation formula "`f(Z) = Z^d + c`".
* JSplitPanes for the main windows (Mandelbrot set, Julia Set and Settings).
Allows user to resize viewing panes accordingly.
* Scrollbar for Settings panel allows user to make Settings pane even
smaller without loss of functionality.
* Various colouring schemes permitted.
* Ability to generate different fractal types. All settings regarding this
feature are found in `SetAlgorithms.java` which also includes full 
documentation.
* Ability to use the mouse middle button to scroll zoom. This works best 
with a mechanical mouse.

##Broken extensions

* Ability to swap view (swap Mandelbrot and Julia panels) but keep any previous
saved states.
* Functionality for rotation exists. But other things break. To enable, uncomment 
line in ReaxingSettings and in Calculations.