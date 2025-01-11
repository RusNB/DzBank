package ru.netology;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {
    String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @BeforeEach
    void setUp() {
        Selenide.open("http://localhost:9999/");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
    }
    @Test
    void shouldTestV1() {
        $("[data-test-id='city'] input").setValue("Краснодар");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Ефимов Александр");
        $("[data-test-id='phone'] input").setValue("+79256678765");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + date), Duration.ofSeconds(15));
    }


}
