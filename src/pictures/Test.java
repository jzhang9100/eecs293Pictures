package pictures;

import org.junit.*;

import java.security.cert.PKIXCertPathValidatorResult;

import static org.junit.Assert.*;

public class Test {
    private Picture GoT;
    private Picture TC;
    private Picture WSM;
    private Picture CMA;
    private Picture SH;
    private Picture GB;
    private PictureCollection pictureCollection;


    @Before
    public void initialize() {
        this.GoT = Picture.of("Guardians of Transportation", 20, 480, 660);
        this.TC = Picture.of("Tower City", 40, 540, 780);
        this.WSM = Picture.of("West Side Market", 40, 720, 840);
        this.CMA = Picture.of("Cleveland Museum of Art", 70, 600, 1080);
        this.SH = Picture.of("Severance Hall", 20, 900, 1020);
        this.GB = Picture.of("Glennan Building", 10, 960, 1140);
        this.pictureCollection = new PictureCollection();
    }

    //PictureCollection.java tests
    @org.junit.Test
    public void addPictureTest() {
        assertEquals(this.pictureCollection.size(), 0);
        this.pictureCollection.addPicture(GoT);
        assertEquals(this.pictureCollection.size(), 1);
    }

    @org.junit.Test
    public void getPictureTest() {
        this.pictureCollection.addPicture(GoT);
        this.pictureCollection.addPicture(CMA);
        assertEquals(this.pictureCollection.size(),  2);
        //B1 index < size()
        assertEquals(this.pictureCollection.getPicture(1), CMA);
        //B2 index >= size()
        assertTrue(5 > this.pictureCollection.size());
        try {
            this.pictureCollection.getPicture(5);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException i){
            assertEquals(i.getMessage(), "No Picture Exists at this Index");
        }
    }

    @org.junit.Test
    public void afterTest() {
        int time = 540;

        //test1
        this.pictureCollection.addPicture(CMA);
        PictureCollection afterT1 = pictureCollection.after(time);
        assertEquals(this.pictureCollection.size(), afterT1.size());

        //test2
        this.pictureCollection = new PictureCollection();
        this.pictureCollection.addPicture(TC);
        PictureCollection afterT2 = pictureCollection.after(time);
        assertTrue(afterT2.size() < pictureCollection.size());

        //test3
        this.pictureCollection = new PictureCollection();
        this.pictureCollection.addPicture(GoT);
        PictureCollection afterT3 = pictureCollection.after(time);
        assertTrue(afterT3.size() < pictureCollection.size());


        //test4
        this.pictureCollection = new PictureCollection();
        PictureCollection afterT4 = pictureCollection.after(time);
        assertEquals(afterT4.size(), 0);
    }

    @org.junit.Test
    public void totalPriorityTest() {
        //B1
        assertEquals(this.pictureCollection.totalPriority(), 0);
        //CC
        this.pictureCollection.addPicture(SH);
        this.pictureCollection.addPicture(GB);
        assertEquals(this.pictureCollection.totalPriority(),30);
    }


    //scheduler.java tests
    @org.junit.Test
    public void optimalPictureSchedulerTest() {
        assertEquals(scheduler.optimalPictureScheduler(pictureCollection).totalPriority(), 0);
        this.pictureCollection.addPicture(GoT);
        this.pictureCollection.addPicture(TC);
        this.pictureCollection.addPicture(WSM);
        this.pictureCollection.addPicture(CMA);
        this.pictureCollection.addPicture(SH);
        this.pictureCollection.addPicture(GB);
        assertEquals(scheduler.optimalPictureScheduler(pictureCollection).totalPriority(), 80);
    }

    @org.junit.Test
    public void findMaxPosTest() {
        PictureCollection[] picColectionArray = new PictureCollection[3];

        //pictureCollection1
        PictureCollection pictureCollection1 = new PictureCollection();
        pictureCollection1.addPicture(GB); // totalPriority 10
        picColectionArray[0] = pictureCollection1; //pos = 0

        //pictureCollection2
        PictureCollection pictureCollection2 = new PictureCollection();
        pictureCollection2.addPicture(GB);
        pictureCollection2.addPicture(WSM); // tptalPriority 50
        picColectionArray[1] = pictureCollection2; //pos = 1

        //pictureCollection3
        PictureCollection pictureCollection3 = new PictureCollection();
        pictureCollection2.addPicture(GB);
        pictureCollection2.addPicture(TC); // tptalPriority 50
        picColectionArray[2] = pictureCollection3; //pos = 2
        //Test1
        assertEquals(1, scheduler.findMaxPos(picColectionArray));
        //Test2 and Test3
        assertEquals(picColectionArray[scheduler.findMaxPos(picColectionArray)], pictureCollection2);
    }

    @org.junit.Test
    public void optimalScheduleGivenInitialPictureTest() {
        PictureCollection temp = new PictureCollection();
        this.pictureCollection.addPicture(GoT);
        //Test 1
        assertEquals(scheduler.optimalScheduleGivenInitialPhoto(temp, pictureCollection).size(), temp.size());
        this.pictureCollection.addPicture(TC);
        this.pictureCollection.addPicture(WSM);
        this.pictureCollection.addPicture(CMA);
        this.pictureCollection.addPicture(SH);
        this.pictureCollection.addPicture(GB);
        //Test2
        assertTrue(scheduler.optimalScheduleGivenInitialPhoto(pictureCollection.after(GoT.getStartTime()), temp).size() > temp.size());

        //Test3
        assertEquals(SH, scheduler.optimalScheduleGivenInitialPhoto(pictureCollection.after(GoT.getStartTime()), temp).getPicture(0));

        //Test4
        assertNotEquals(GB, scheduler.optimalScheduleGivenInitialPhoto(pictureCollection.after(GoT.getStartTime()), temp).getPicture(0));
    }
}