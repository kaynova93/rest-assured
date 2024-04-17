package ibs.org.steps;

import ibs.org.pojo.ProductPojo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Допустим;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static ibs.org.spec.Spec.*;
import static io.restassured.RestAssured.given;

public class ApiSteps {
    static String cookie;
    List<ProductPojo> products;

    @Допустим("Отправить запрос на добавление продукта")
    public void addProduct(DataTable dataTable) {
        installSpec(requestSpecification(), responseSpecification(200));
        List<List<String>> table = dataTable.asLists(String.class);
        cookie = given()
                .contentType(ContentType.JSON)
                .when()
                .body(new ProductPojo(table.get(0).get(0),
                        table.get(0).get(1),
                        Boolean.parseBoolean(table.get(0).get(2))))
                .log().all()
                .post("api/food")
                .then()
                .log().all().extract().cookie("JSESSIONID");
    }

    @Допустим("Запросить все продукты")
    public void getProduct() {
        installSpec(requestSpecification(), responseSpecification(200));
        products = given()
                .cookie("JSESSIONID", cookie)
                .contentType(ContentType.JSON)
                .when()
                .get("api/food")
                .then()
                .log().all().extract().body().jsonPath().getList(".", ProductPojo.class);
    }

    @Допустим("Проверить добавленный продукт")
    public void checkAddProduct(DataTable dataTable) {
        List<List<String>> table = dataTable.asLists(String.class);
        Assertions.assertAll(
                () -> Assertions.assertEquals(table.get(0).get(0),
                        products.get(products.size() - 1).getName()),
                () -> Assertions.assertEquals(table.get(0).get(1),
                        products.get(products.size() - 1).getType()),
                () -> Assertions.assertEquals(Boolean.parseBoolean(table.get(0).get(2)),
                        products.get(products.size() - 1).isExotic())
        );
    }
}
