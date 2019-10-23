package pictures;

import java.util.*;

//Class PictureCollection: object to represent a collection of pictures 
public class PictureCollection {
	//class variable list of pictures P: initially empty
	private List<Picture> pictures = new ArrayList<Picture>();

	//adder method addPicture(Picture picture):
	public void addPicture(Picture picture) {
		//Add picture to class variable list 
		this.pictures.add(picture);
	}

	//getter method getPicture(index):
	 public Picture getPicture(int index) {
		//Return picture stored in class variable list a given index pi
		return this.pictures.get(index); 
	}

	//getter method size():
	public int size() {
		//Return number of items in P
		return this.pictures.size();
	}

	//Function after(time):
	public PictureCollection after(int time) {	
		/*
		Output:
		Return a PictureCollection of pictures whose start time is after the input time
		*/	
		
		//Let ret be PictureCollection that will hold the output
		PictureCollection after = new PictureCollection();
 		//for all pi in P:
		for(Picture picture : this.pictures) {		
		//If pi getStartTime() > time:
			if(picture.getStartTime() > time) {
				//ret addPicture pi
				after.addPicture(picture);
			}
		}
		//Return ret
		return after;
	}

	//Function totalPriority():
	public int totalPriority() {	
		/*
		Output:
		Return the total priority for all pictures in P
		*/

		//Let ret be the return value and be initially 0
		int total = 0;
 		//for all pi in P:
		for(Picture picture : this.pictures) {		
			//Add the priority of pito ret
			total += picture.getPriority();
		}
		//Return ret
		return total;
	}

  public void print() {
    for(Picture p : this.pictures){
      System.out.print(p.getPriority() + " " );
      System.out.println(p.getTitle());

    }
  }
}
