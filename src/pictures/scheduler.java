package pictures;

public class scheduler {

    //Algorithm pictureScheduler(pictureCollection): main algorithm that optimizes pictures to take
    public static final PictureCollection optimalPictureScheduler(PictureCollection pictureCollection) {
	/*
	Input:
	pictureCollection: object PictureCollection which holds a collection of pictures

	Output:
	A PictureCollection that represents an optimized schedule of picture objects from the input that don’t overlap and maximize priority
	*/
        if(pictureCollection.size() == 0) {
            return new PictureCollection();
        }
        //Let A be an array of PictureCollections with the same length as elements in pictureCollection, each ai will be a PictureCollection
        //that maximizes the priority with the ith element of pictureCollection as the first item in the schedule
        PictureCollection[] allOptimalSchedules = new PictureCollection[pictureCollection.size()];

        //for all pi in pictureCollection:
        for (int i = 0; i < pictureCollection.size(); i++) {
            //Let B be a PictureCollection object which will hold the output,
            //with the first element being picture
            Picture picture = pictureCollection.getPicture(i);
            PictureCollection temp = new PictureCollection();
            temp.addPicture(picture);
            //A append scheduleHelper(items in pictureCollection after pi, B)i
            temp = optimalScheduleGivenInitialPhoto(pictureCollection.after(picture.getEndTime()), temp);
            temp.addPicture(pictureCollection.getPicture(i));
            allOptimalSchedules[i] = temp;
        }
        int maxPosition = findMaxPos(allOptimalSchedules);
        return allOptimalSchedules[maxPosition];
    }
    //Return ai in A such that ai has the greatest total priority across all of A
    static int findMaxPos(PictureCollection[] allOptimalSchedules) {
        int maxPriority = 0;
        int maxPosition = 0;
        for (int n = 0; n < allOptimalSchedules.length; n++) {
            PictureCollection currentPictureCollection = allOptimalSchedules[n];
            if (currentPictureCollection.totalPriority() > maxPriority) {
                maxPriority = currentPictureCollection.totalPriority();
                maxPosition = n;
            }
        }
        return maxPosition;
    }

    //Helper scheduleHelper(pictureCollection, B): recursively iterate and create optimal schedule
    static PictureCollection optimalScheduleGivenInitialPhoto(PictureCollection possiblePictures, PictureCollection pictureCollection) {
		/*
		Input:
		after: object PictureCollection which holds a collection of pictures with start times after the end of finishing picture
		pictureCollection: PictureCollection which only contains picture and is otherwise empty

		Output:
		A PictureCollection that represents an optimized schedule of picture objects from the input that don’t overlap and maximize priority with picture as the first element
		*/

        //If the size of after is 0:
        if (possiblePictures.size() == 0) {
            //Return B
            return possiblePictures;
        } else {
		 	/*
			Var max = 0 to denote the maximum total priority for all of its children
			Var maxPos = 0 to denote the position Var max
			Var maxB = the PictureCollection with total priority max at position maxPos
			*/
            int maxPriority = 0;
            int maxPos = 0;
            PictureCollection mostOptimalSubTree = new PictureCollection();
            //For each pi in pictureCollection:
            for (int i = 0; i < possiblePictures.size(); i++) {
                //Let A be the recursive call scheduleHelper(items after pi, B)
                PictureCollection subTree = optimalScheduleGivenInitialPhoto(
                        possiblePictures.after(possiblePictures.getPicture(i).getEndTime()), pictureCollection);
                //Let P denote the total priority of A
                int totalChildrenPriority = subTree.totalPriority();
                //If P  > max
                if (totalChildrenPriority > maxPriority) {
					/*
					max = P
					maxB  = A
                    mosPos = i
					*/
                    maxPos = i;
                    maxPriority = totalChildrenPriority;
                    mostOptimalSubTree = subTree;
                }
            }
            //Return maxB:
            mostOptimalSubTree.addPicture(possiblePictures.getPicture(maxPos));
            return mostOptimalSubTree;
        }
    }
}