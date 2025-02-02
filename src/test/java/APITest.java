import com.google.common.annotations.VisibleForTesting;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest {

    @Test
    public void APICheck() {
        Response response = RestAssured.get("https://api.coindesk.com/v1/bpi/currentprice.json")
                .then().assertThat().statusCode(200).extract().response();
        Assert.assertEquals(response.jsonPath().getMap("bpi").containsKey("USD"), true);
        Assert.assertEquals(response.jsonPath().getMap("bpi").containsKey("GBP"), true);
        Assert.assertEquals(response.jsonPath().getMap("bpi").containsKey("EUR"), true);
        Assert.assertEquals(response.jsonPath().getString("bpi.GBP.description"), "British Pound Sterling");
    }
}
