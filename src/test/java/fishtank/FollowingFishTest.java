package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FollowingFishTest {
    private Fish follow;
    private FollowingFish follower;

    @Before
    public void setUp() {
        follow = mock(Fish.class);
        //note: this is also why we use getters and setters so much in Java,
        //we wouldn't be able to mock the field itself if it were used instead
        //of the getter.
        when(follow.getX()).thenReturn(5);
        //This syntax is introduced by a library called mockito.
        //You can use it however you want, and it will be installed when we
        //run the grader.
        //See: http://www.vogella.com/tutorials/Mockito/article.html from 4 onwards
        when(follow.getY()).thenReturn(10);

        follower = new FollowingFish(follow);
        FishTank.clearTank();
    }

    @Test
    public void testApproachesFromBottomRight() {
        follower.setLocation(20, 20);
        //it should take exactly 15 updates to get from
        //(20, 20) to (5, 10)
        for (int i = 0; i < 15; i++) {
            follower.update();
        }
        int verticalDist = Math.abs(follower.getY() - follow.getY());
        int horizontalDist = Math.abs(follower.getX() - follow.getX());
        //Follower should be exactly 2 units below follow.
        assertEquals(2, verticalDist);
        assertEquals(0, horizontalDist);
    }

    @Test
    public void testApproachesFromBottomLeft() {
        follower.setLocation(0, 20);
        //it should take exactly 9 updates to get from
        //(0, 20) to (5, 10)
        for (int i = 0; i < 9; i++) {
            follower.update();
        }
        int verticalDist = Math.abs(follower.getY() - follow.getY());
        int horizontalDist = Math.abs(follower.getX() - follow.getX());
        //Follower should be exactly 2 units below follow.
        assertEquals(2, verticalDist);
        assertEquals(0, horizontalDist);
    }

    @Test
    public void testApproachesFromUpperRight() {
        follower.setLocation(10, 0);
        //it should take exactly 9 updates to get from
        //(10, 0) to (5, 10)
        for (int i = 0; i < 9; i++) {
            follower.update();
        }
        int verticalDist = Math.abs(follower.getY() - follow.getY());
        int horizontalDist = Math.abs(follower.getX() - follow.getX());
        //Follower should be exactly 2 units above follow.
        assertEquals(2, verticalDist);
        assertEquals(0, horizontalDist);
    }

    @Test
    public void testApproachesFromUpperLeft() {
        follower.setLocation(0, 0);
        //it should take exactly 9 updates to get from
        //(0, 0) to (5, 10)
        for (int i = 0; i < 9; i++) {
            follower.update();
        }
        int verticalDist = Math.abs(follower.getY() - follow.getY());
        int horizontalDist = Math.abs(follower.getX() - follow.getX());
        //Follower should be exactly 2 units above follow.
        assertEquals(2, verticalDist);
        assertEquals(0, horizontalDist);
    }

    @Test
    public void testApproachesFromRight() {
        follower.setLocation(10, 10);
        //it should take exactly 3 updates to get from
        //(10, 10) to (5, 10)
        for (int i = 0; i < 3; i++) {
            follower.update();
        }
        int verticalDist = Math.abs(follower.getY() - follow.getY());
        int horizontalDist = Math.abs(follower.getX() - follow.getX());
        assertEquals(0, verticalDist);
        assertEquals(2, horizontalDist);
    }

    @Test
    public void testApproachesFromLeft() {
        follower.setLocation(0, 10);
        //it should take exactly 3 updates to get from
        //(0, 10) to (5, 10)
        for (int i = 0; i < 3; i++) {
            follower.update();
        }
        int verticalDist = Math.abs(follower.getY() - follow.getY());
        int horizontalDist = Math.abs(follower.getX() - follow.getX());
        assertEquals(0, verticalDist);
        assertEquals(2, horizontalDist);
    }
}
