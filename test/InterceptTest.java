import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class InterceptTest extends FunctionalTest {

    @Test
    public void testOverlaySettings() {
        Response response = GET("/intercept/d8141a42-18c2-48f0-8f97-2f6c56d6a132/settings");
        assertIsOk(response);
        this.assertContentMatch("frequency", response);

        response = GET("/intercept/400/settings");
        assertIsOk(response);
        this.assertContentMatch("Error locating Intercept", response);

        response = GET("/intercept/settings");
        this.assertIsNotFound(response);
    }
}