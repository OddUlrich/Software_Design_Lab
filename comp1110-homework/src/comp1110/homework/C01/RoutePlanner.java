package comp1110.homework.C01;

import java.util.ArrayList;
import java.util.List;

public class RoutePlanner {
    ArrayList<Location> locList;

    RoutePlanner() {
        locList = new ArrayList<>();
    }

    void addLocation(Location loc) {
         locList.add(loc);
    }

    void setAllFalse() {
        for (Location loc:locList) {
            loc.isVisited = false;
        }
    }

    public List<Location> calculateRoute(double startX, double startY) {
        // Reset all the visiting label for all location;
        setAllFalse();

        ArrayList<Location> tmpLocList = new ArrayList<>();
        int cnt = 0;

        Location curLoc = new Location(startX, startY);


        while (cnt < locList.size()) {
            Location tmpMinLoc = null;
            double tmpMinDis = 0;
            boolean bFirst = true;

            for (Location tmpLoc: locList) {
                if (!tmpLoc.isVisited) {
                    double curDis = curLoc.distanceTo(tmpLoc);

                    // The first comparison.
                    if (bFirst || curDis < tmpMinDis) {
                        tmpMinDis = curDis;
                        tmpMinLoc = tmpLoc;
                        bFirst = false;
                    }
                }
            }

            if (tmpMinLoc != null) {
                tmpLocList.add(tmpMinLoc);
                curLoc = tmpMinLoc;

                tmpMinLoc.isVisited = true;
                cnt++;
            }
        }

        return tmpLocList;
    }

    public static class Location {
        double xCoord;
        double yCoord;
        boolean isVisited;

        Location(double xCoord, double yCoord) {
            this.xCoord = xCoord;
            this.yCoord = yCoord;
            this.isVisited = false;
        }

        double distanceTo(Location toReachLoc) {
            double x_square = Math.pow((toReachLoc.xCoord - this.xCoord), 2);
            double y_square = Math.pow((toReachLoc.yCoord - this.yCoord), 2);

            return Math.sqrt(x_square + y_square);
        }
    }
}
