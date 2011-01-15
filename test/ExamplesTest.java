import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class ExamplesTest extends FunctionalTest {

    @Test
    public void testSlideup() {
        Response response = GET("/examples/slideup");
        assertIsOk(response);
        this.assertContentMatch("Click to activate", response);
    }
}