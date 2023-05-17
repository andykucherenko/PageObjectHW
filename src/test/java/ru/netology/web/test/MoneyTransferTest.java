package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {


  @Test
  void shouldTransferFromSecondToFirst() {
    int amount =500;
    val loginPage = open("http://localhost:9999", LoginPage.class);
    val authInfo = DataHelper.getAuthInfo();
    val verificationPage = loginPage.validLogin(authInfo);
    val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    val dashboardPage = verificationPage.validVerify(verificationCode);
    int balanceOfFirstCardInitial = DashboardPage.getCurrentBalanceOfFirstCard();
    int balanceOfSecondCardInitial = DashboardPage.getCurrentBalanceOfSecondCard();
    val transferPage = dashboardPage.firstCard();
    val cardInfo = DataHelper.getSecondCardInfo();
    transferPage.transferCard(cardInfo,amount);
    int balanceAfterTransferFirstCard = DataHelper.balancePlusAmount(balanceOfFirstCardInitial, amount);
    int balanceAfterTransferSecondCard = DataHelper.balanceMinusAmount(balanceOfSecondCardInitial, amount);
    int balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
    int balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
    assertEquals(balanceAfterTransferFirstCard, balanceOfFirstCardAfter);
    assertEquals(balanceAfterTransferSecondCard, balanceOfSecondCardAfter);

  }

  @Test
  void shouldTransferFromFirstToSecond() {
    int amount = 1000;
    val loginPage = open("http://localhost:9999", LoginPage.class);
    val authInfo = DataHelper.getAuthInfo();
    val verificationPage = loginPage.validLogin(authInfo);
    val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    val dashboardPage = verificationPage.validVerify(verificationCode);
    int balanceOfFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
    int balanceOfSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
    val transferPage = dashboardPage.secondCard();
    val cardInfo = DataHelper.getFirstCardInfo();
    transferPage.transferCard(cardInfo,amount);
    int balanceAfterTransferFirstCard = DataHelper.balancePlusAmount(balanceOfSecondCardBefore, amount);
    int balanceAfterTransferSecondCard = DataHelper.balanceMinusAmount(balanceOfFirstCardBefore, amount);
    int balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
    int balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
    assertEquals(balanceAfterTransferFirstCard, balanceOfFirstCardAfter);
    assertEquals(balanceAfterTransferSecondCard, balanceOfSecondCardAfter);
  }


  @Test
  void shouldNotTransferMoreThanRestOfBalance() {
    int amount = 20000;
    val loginPage = open("http://localhost:9999", LoginPage.class);
    val authInfo = DataHelper.getAuthInfo();
    val verificationPage = loginPage.validLogin(authInfo);
    val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    val dashboardPage = verificationPage.validVerify(verificationCode);
    int balanceOfFirstCardInitial = DashboardPage.getCurrentBalanceOfFirstCard();
    int balanceOfSecondCardInitial = DashboardPage.getCurrentBalanceOfSecondCard();
    val transferPage = dashboardPage.firstCard();
    val cardInfo = DataHelper.getSecondCardInfo();
    transferPage.transferCard(cardInfo,amount);
    int balanceAfterTransferFirstCard = DataHelper.balancePlusAmount(balanceOfFirstCardInitial, amount);
    int balanceAfterTransferSecondCard = DataHelper.balanceMinusAmount(balanceOfSecondCardInitial, amount);
    int balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
    int balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
    assertEquals(balanceAfterTransferFirstCard, balanceOfFirstCardAfter);
    assertEquals(balanceAfterTransferSecondCard, balanceOfSecondCardAfter);

  }
}