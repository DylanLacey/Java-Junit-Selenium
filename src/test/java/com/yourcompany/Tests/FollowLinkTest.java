package com.yourcompany.Tests;

import com.yourcompany.Pages.*;
import org.junit.Test;
import org.openqa.selenium.InvalidElementStateException;

import static org.junit.Assert.*;

public class FollowLinkTest extends TestBase {

    public FollowLinkTest(){
        super();
    }

    /**
     * Runs a simple test verifying link can be followed.
     * @throws InvalidElementStateException
     */
    @Test
    public void verifyLinkTest() throws InvalidElementStateException {
        GuineaPigPage page = GuineaPigPage.visitPage(driver);

        page.followLink();

        assertFalse(page.isOnPage());
    }
}