Reviewer: Wyman Wong (u6726234)
Component: <Task3 , Task 7, TileTest>
Author: Zichuan Ding (u6156881)



Review Comments:

1. RailroadInk.java:68 in isBoardStringWellFormed():
    Use magic number like 1, 5 and 31. They should define as consistent variables with meaningful name.
 
2. RailroadInk.java:78 in isBoardStringWellFormed():
    The first letter in comment is not uppercase as other comments.

3. RailroadInk.java:432,432 in generateDiceRoll():
    The bound of method nextInt will not be achieve, but which is need in the program.
    
4. TileTest.java: 
    Cover every possible categories with variable object tiles.
