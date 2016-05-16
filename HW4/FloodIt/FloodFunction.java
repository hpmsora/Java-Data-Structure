package FloodIt;

import java.util.LinkedList;
import java.util.List;

public class FloodFunction {
    private Driver driver;
    //here floodList is declared as a LinkedList
    //it is declared as public (a bad practice), but it is needed by the Driver class
    public List<Coord> floodList = new LinkedList<Coord>();
    
    //constructor  
    public FloodFunction(Driver newDriver) {
        driver = newDriver;
        //when the game starts, only the cell at the left/top corner is filled
        floodList.add(new Coord(0, 0));
    }
    
    //flood function is to be implemented by students
    public void flood(int colorToFlood) {
      int f_size = floodList.size();
      //check all elements in floodList
      for (int i = 0; i < f_size; i++) {
        Coord curr = floodList.get(i);
        //check four side
        if (inbound(right(curr)) && (colorOfCoord(right(curr)) == colorToFlood))
          if (!floodList.contains(right(curr))) {
          floodList.add(right(curr));
          flood(colorToFlood);
        }
        //check left side
        if (inbound(left(curr)) && (colorOfCoord(left(curr)) == colorToFlood))
          if (!floodList.contains(left(curr))) {
          floodList.add(left(curr));
          flood(colorToFlood);
        }
        //check up side
        if (inbound(up(curr)) && (colorOfCoord(up(curr)) == colorToFlood))
          if (!floodList.contains(up(curr))) {
          floodList.add(up(curr));
          flood(colorToFlood);
        }
        //check down side
        if (inbound(down(curr)) && (colorOfCoord(down(curr)) == colorToFlood))
          if (!floodList.contains(down(curr))) {
          floodList.add(down(curr));
          flood(colorToFlood);
        }
      }
    }
    
    //is input cell (specified by coord) on the board?
    public boolean inbound(final Coord coord) {
        final int x = coord.x;
        final int y = coord.y;
        final int size = this.driver.size;
        return x > -1 && x < size && y > -1 && y < size;
    }
    //return the coordinate of the cell on the top of the given cell (coord) 
    public Coord up(final Coord coord) {
        return new Coord(coord.x, coord.y-1);
    }
    //return the coordinate of the cell below the given cell (coord)
    public Coord down(final Coord coord) {
        return new Coord(coord.x, coord.y+1);
    }
    //return the coordinate of the cell to the left of the given cell (coord)
    public Coord left(final Coord coord) {
        return new Coord(coord.x-1, coord.y);
    }
    //return the coordinate of the cell to the right of the given cell (coord)
    public Coord right(final Coord coord) {
        return new Coord(coord.x + 1, coord.y);
    }
    //get the color of a cell (coord)
    public int colorOfCoord(final Coord coord) {
        return this.driver.getColor(coord);
    }
}
