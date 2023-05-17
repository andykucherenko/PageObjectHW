package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper.*;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
  private SelenideElement loginField = $("[data-test-id=login] input");
  private SelenideElement passwordField = $("[data-test-id=password] input");
  private SelenideElement continueButton= $("[data-test-id=action-login]");
  public VerificationPage validLogin(AuthInfo info) {
    loginField.setValue(info.getLogin());
    passwordField.setValue(info.getPassword());
    continueButton.click();
    return new VerificationPage();
  }
}