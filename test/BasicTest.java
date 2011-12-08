import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Before
    public void set() {
        Fixtures.deleteAllModels();
    }

    @Test
    public void createAndRetrieveUser() {
        // Create a new user and save it
        new User("bob@gmail.com", "secret", "Bob", "McFadden").save();

        // Retrieve the user with e-mail address bob@gmail.com
        User bob = User.find("byEmail", "bob@gmail.com").first();

        // Test
        assertNotNull(bob);
        assertEquals("Bob", bob.firstName);
        assertEquals("McFadden", bob.lastName);
    }

    @Test
    public void tryConnectAsUser() {
        // Create a new user and save it
        new User("bob@gmail.com", "secret", "Bob", "McFadden").save();

        // Test
        assertNotNull(User.connect("bob@gmail.com", "secret"));
        assertNull(User.connect("bob@gmail.com", "badpassword"));
        assertNull(User.connect("tom@gmail.com", "secret"));
    }

    @Test
    public void createSurvey() {
        // Create a new user and save it
        User bob = new User("bob@gmail.com", "secret", "Bob", "McFadden").save();

        // Create a new post
        new Survey(bob, "My first survey", "Hello world").save();

        // Test that the post has been created
        assertEquals(1, Survey.count());

        // Retrieve all posts created by Bob
        List<Survey> bobSurveys = Survey.find("byOwner", bob).fetch();

        // Tests
        assertEquals(1, bobSurveys.size());
        Survey firstSurvey = bobSurveys.get(0);
        assertNotNull(firstSurvey);
        assertEquals(bob, firstSurvey.owner);
        assertEquals("My first survey", firstSurvey.title);
        assertEquals("Hello world", firstSurvey.content);
        assertNotNull(firstSurvey.createdAt);

        Intercept intercept = new Intercept("bbb", firstSurvey, 15.1F, "Test Intercept");
        assertNotNull(intercept);

        String uuid = Intercept.getUUID();
        assertNotNull(uuid);

        InterceptAppearance appearance = new InterceptAppearance( intercept );
        assertNotNull( appearance );
        assertNotNull( appearance.createdAt );
        assertNull( appearance.appearanceAt );
        assertNull( appearance.noResponseAt );
        assertNull( appearance.yesResponseAt );

    }

    @Test
    public void fullTest() {
        Fixtures.loadModels("data.yml");

        // Count things
        assertEquals(2, User.count());
        assertEquals(1, Survey.count());

        // Try to connect as users
        assertNotNull(User.connect("bob@gmail.com", "secret"));
        assertNotNull(User.connect("jeff@gmail.com", "secret"));
        assertNull(User.connect("jeff@gmail.com", "badpassword"));
        assertNull(User.connect("tom@gmail.com", "secret"));

        // Find all of Bob's posts
        List<Survey> bobSurveys = Survey.find("owner.email", "bob@gmail.com").fetch();
        assertEquals(0, bobSurveys.size());

        // Find all of Jeff's surveys
        List<Survey> jeffSurveys = Survey.find("owner.email", "jeff@gmail.com").fetch();
        assertEquals(1, jeffSurveys.size());

        // Find the most recent post
        Survey frontSurvey = Survey.find("order by createdAt desc").first();
        assertNotNull(frontSurvey);
        assertEquals("Loblaws Survey", frontSurvey.title);
    }

}
