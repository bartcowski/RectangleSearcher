# RectangleSearcher
Project made for 'Analysis of algorithms' course.
-----------------------------------------------------
The goal is to find the smallest rectangle (perimeter wise) that bounds all given 'n' points OR its reflections across y=x line, </br>
so basically it has to contain at least one of the points from a pair: <original, reflection>. </br>
All the rectangle's sides have to be parallel to X and Y axis and points' coordinates have to be integers.

Application runs in the console and has 3 ways of launching: </br>
- mode1: coordinates of all points are loaded from given .txt file </br>
- mode2: coordinates are generated randomly, user can define number of points to generate and what should be the biggest/smallest possible coordinate value
- mode3: made for testing purposes, coordinates are also generated randomly, user can define how many points to create at the beginning,
         by how many points should the collection grow with every step, how many of those steps should there be and how many times
         should the time measuring be repeated for the same amount of points (the same step). After warming up JVM and carrying out tests,
         there is a table displayed that shows different information about the results.

Used:
Java SE, Junit 5, Maven 
