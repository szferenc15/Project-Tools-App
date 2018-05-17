package hu.application.sportmates.controller;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import hu.application.sportmates.R;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by G on 2018. 05. 17..
 */
public class LoginActivityTest {

    //activity elindítása
    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    // Rossz adatok
    private String badName = "tony";
    private String badPassword = "tony";

    // Megfelelő adatok
    private String goodName ="feri";
    private String goodPassword ="Dr123$,.-";


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testInvalidLogin() {
        Espresso.onView(withId(R.id.edtUserName)).perform(typeText(badName));
        Espresso.onView(withId(R.id.edtPassword)).perform(typeText(badPassword));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btnLogin)).perform(click());
    }

    @Test
    public void testValidLogin() {
        Espresso.onView(withId(R.id.edtUserName)).perform(typeText(goodName));
        Espresso.onView(withId(R.id.edtPassword)).perform(typeText(goodPassword));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btnLogin)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
    }

}