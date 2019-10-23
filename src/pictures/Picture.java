package pictures;

import java.util.Objects;

//Class Picture: Object to represent a picture
public class Picture {
        //class variables for title, Priority, start time, end time
	private String title;
	private int priority;
	private int startTime;
	private int endTime;
	
	/*
	private constructor picture(t, P, s, e): 
		Input: 
		t: picture description i.e location/name
		P: represents the priority of the picture
		s: represents the time you can begin working on the picture
		e: represents the time the picture will be done
	*/ 
	private Picture(String title, int priority, int startTime, int endTime) {
            //Set class variables
            this.title = title;
            this.priority = priority;
            this.startTime = startTime;
            this.endTime = endTime;
        }

    /*
    public build method build(t, P, s, e):
    Input:
    t: picture title i.e location/name
    P: represents the priority of the picture
    s: represents the time you can begin working on the picture
    e: represents the time the picture will be done
     */
    public static final Picture of(String title, int priority, int startTime, int endTime){
        //Perform null checks
        //Return new instance of picture from given parameters
        return new Picture(Objects.requireNonNull(title),
                    Objects.requireNonNull(priority),
                    Objects.requireNonNull(startTime),
                    Objects.requireNonNull(endTime));
    }


    //getter method getStartTime():
    int getStartTime() {
        //return this start time
        return this.startTime;
    }


    //getter method getEndTime():
    int getEndTime() {
        //return this end time
        return this.endTime;
    }


    //getter method getTitle():
    String getTitle() {
        //return this title
        return this.title;
    }

    //getter method getPriority():
    int getPriority() {
        //return this priority
	    return this.priority;
    }
}	
