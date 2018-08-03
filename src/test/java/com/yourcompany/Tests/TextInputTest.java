package com.yourcompany.Tests;

import com.yourcompany.Pages.*;
import org.junit.Test;
import org.openqa.selenium.InvalidElementStateException;
import static org.hamcrest.CoreMatchers.containsString;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.UUID;

import static org.junit.Assert.*;

public class TextInputTest extends TestBase {

    public TextInputTest(String os,
                         String version, String browser, String deviceName, String deviceOrientation) {
            super(os, version, browser, deviceName, deviceOrientation);
    }

    /**
     * Runs a simple test verifying if the comment input is functional.
     * @throws InvalidElementStateException
     */
    @Test
    public void verifyCommentInputTest() throws InvalidElementStateException {
        String commentInputText = UUID.randomUUID().toString();

        GuineaPigPage page = GuineaPigPage.visitPage(driver);
        page.visitPage();
        page.submitComment(commentInputText);

        String sessionID = ((RemoteWebDriver) page.driver).getSessionId().toString();

        // System.out.println("SauceOnDemandSessionID=" + sessionID + "job-name=verifyCommentInputTest[0]");
        System.out.println("SauceOnDemandSessionID=" + "c582f29b2dbd4b859f2d625bf992967f"+ " job-name=verifyCommentInputTest");
        assertThat(page.getSubmittedCommentText(), containsString(commentInputText));
    }

    /**
     * Runs a simple test verifying if the comment input is functional.
     * @throws InvalidElementStateException
     */
    @Test
    public void verifyCommentInputTestAgain() throws InvalidElementStateException {
        String commentInputText = UUID.randomUUID().toString();

        GuineaPigPage page = GuineaPigPage.visitPage(driver);
        page.visitPage();
        page.submitComment(commentInputText);

        String sessionID = ((RemoteWebDriver) page.driver).getSessionId().toString();

        System.out.println("SauceOnDemandSessionID=" + sessionID + "job-name=verifyCommentInputTest[0]");
        assertThat(page.getSubmittedCommentText(), containsString(commentInputText));
    }
}