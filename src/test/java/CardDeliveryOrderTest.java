import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {
    LocalDate currentDate = LocalDate.now();

    @Test
            void shouldSubmitRequest() {
        LocalDate date = currentDate.plusDays(3);
        String appointmentDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open ("http://0.0.0.0:9999");
        $x("//input[@placeholder=\"Город\"]").setValue("Казань");
        $ ("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $ ("[placeholder=\"Дата встречи\"]").setValue(appointmentDate);
        $ ("[name=\"name\"]").setValue("Иван Петров");
        $ ("[name=\"phone\"]").setValue("+23400009876");
        $ ("[role=\"presentation\"]").click();
        $x("//*[text()=\"Забронировать\"]").click();
        $(withText("Встреча успешно забронирована на")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

}
