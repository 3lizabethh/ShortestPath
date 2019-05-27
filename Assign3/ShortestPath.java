import java.io.IOException;

/**
 * This class prints the number of cells in the shortest path from the initial 
 * cell to customer cell,if path exists.
 * @author Dongzheng (Elizabeth) Xu
 */

public class ShortestPath {

	private Map cityMap; //references city map where WPC and C are located

	/* This constructor displays the map on screen.
	 * @parameter filname to open map
	 */
	public ShortestPath(String filename) {
		try {
			cityMap = new Map(filename);
		} catch (InvalidMapException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Accessor method to get cityMap
	 * @return city map
	 */
	public Map getCityMap() {
		return cityMap;
	}

	/* This method finds the shortest path from WPC to C.
	 * @parameter args
	 */
	public static void main (String[] args) {
		ShortestPath path = new ShortestPath(args[0]);// stores visual display
		DLList<MapCell> list = new DLList<>(); // a dll to store shortest path


		boolean found=false;// keeps track of whether customer was reached

		MapCell start = path.getCityMap().getStart(); //start on the power station 
		MapCell newCell; // holds cell that might be next in path
		
		int distSmalltoInit=0;//holds number from smallest cell to first cell
		int distNeighToStart=0;// holds distance from neighbour to first cell
		
		list.insert(start,start.getDistanceToStart()); // add starting block to path
		start.markInList(); //mark it as in path

		while(!list.isEmpty()&& !found){

			start = list.getSmallest(); //set start as the smallest value
			start.markOutList();//mark it as checked
			
			if(start.isCustomer()){//if is customer stop looking for path
				found=true;//stop looping
			}

			else {
				for(int i=0;i<4;i++) {//check neighbouring cells
					if(path.nextCell(start)!=null) {//if a valid cell
						if(start.getNeighbour(i)!=null && !start.getNeighbour(i).isMarkedInList() && !start.getNeighbour(i).isMarkedOutList()) {
							newCell=path.nextCell(start);// get next cell
			
							distSmalltoInit = 1+ start.getDistanceToStart();

							if(newCell.getDistanceToStart()> distSmalltoInit) {//gets new cell,if shortest,keep track
								newCell.setPredecessor(start);
								newCell.setDistanceToStart(distSmalltoInit);
								
							}

							distNeighToStart = newCell.getDistanceToStart();

							if(newCell.isMarkedInList() &&  distNeighToStart<list.getDataValue(newCell)) { 
								list.changeValue(newCell, distNeighToStart);
							}

							else if(!newCell.isMarkedInList()) {
								list.insert(newCell, distNeighToStart);
								newCell.markInList();

							}
						}

					}
				}
			}
		}
		if(start.isCustomer()) {
			System.out.print("Customer was reached with "+distNeighToStart+" cells in the path.");
		}

		else
			System.out.println("Customer not reached.");

	}

	/* This method
	 * @parameter current cell
	 * @return first unmarked cell that can be added to current path.
	 */
	private MapCell nextCell(MapCell cell) {
		//keeps track of direction [0]north,[1]west etc.. that the current cell can access
		boolean[] direction = {true,true,true,true}; 

		//restricts accessible sides for vertical/horizontal switch
		if(cell.isVerticalSwitch()) {
			direction[1]=false;//can't access west
			direction[3]=false;//can't access east

		}
		if(cell.isHorizontalSwitch()) {
			direction[0]=false;//can't access north
			direction[2]=false;//can't access south

		}

		//check neighbouring cells:customer,omni have no priority over horizontal/vertical switches
		//return the next neighbouring cell

		for(int i=0;i<4;i++) {//check all four neighbours to see if vertical/horizonal with smallest index
			MapCell neighbour = cell.getNeighbour(i);	
			if (neighbour!= null && !neighbour.isMarked() && direction[i]&& neighbour.isCustomer()) { //return cell if has not already marked,has content, and is accessible by current cell
				return neighbour;}

			if (neighbour!=null && !neighbour.isMarked() && direction[i]&&neighbour.isOmniSwitch()) { //return cell if has not already marked,has content, and is accessible by current cell
				return neighbour; }

			if (neighbour!=null && !neighbour.isMarked() && neighbour.isVerticalSwitch() && direction[i] && (i==0 || i==2)) { //if neighbour has not already been checked and is switch
				return neighbour; }

			if (neighbour!=null && !neighbour.isMarked() && neighbour.isHorizontalSwitch() && direction[i] && (i==1 || i==3)) { //if neighbour has not already been checked and is switch
				return neighbour; }

		}

		return null;//returns null if no unmarked adjacent cells

	}
}
