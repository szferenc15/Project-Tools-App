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
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by G on 2018. 05. 17..
 */
public class RegistrationActivityTest {
    // rossz adatok
    private String invalidFirstName, invalidLastName, invalidUserName, invalidPassword, invalidEmail, invalidPhoneNumber,
            invalidAddress, invalidBirthDay;

    // megfelelő adatok
    private String validFirstName, validLastName, validUserName, validPassword, validEmail, validPhoneNumber,
            validAddress, validBirthDay;

    @Rule
    public ActivityTestRule<RegistrationActivity> mActivityRule = new ActivityTestRule<>(
            RegistrationActivity.class);

    @Before
    public void setUp() throws Exception {
        // rossz adatok
        invalidFirstName = "a b c";
        invalidLastName = "a b c";
        invalidUserName = "a b c";
        invalidPassword = "a b c";
        invalidEmail = "a b c";
        invalidPhoneNumber = "a b c";
        invalidAddress = "a b c";
        invalidBirthDay = "a b c";

        // jó adatok
        validFirstName = "Kis";
        validLastName = "Antal";
        validUserName = "anti";
        validPassword = "Dr123$,.-";
        validEmail = "antal@gmail.com";
        validPhoneNumber = "0630919919";
        validAddress = "Budapest";
        validBirthDay = "1990-01-08";
    }

    @Test
    public void testInvalidRegistrationWithData() {
        Espresso.onView(withId(R.id.edtFirstName)).perform(typeText(invalidFirstName));
        Espresso.onView(withId(R.id.edtLastName)).perform(typeText(invalidLastName));
        Espresso.onView(withId(R.id.edtUserName)).perform(typeText(invalidUserName));
        Espresso.onView(withId(R.id.edtPassword)).perform(typeText(invalidPassword));
        Espresso.onView(withId(R.id.edtEmail)).perform(typeText(invalidEmail));
        Espresso.onView(withId(R.id.edtPhoneNumber)).perform(typeText(invalidPhoneNumber));
        Espresso.onView(withId(R.id.edtCity)).perform(typeText(invalidAddress));
        Espresso.onView(withId(R.id.edtBirthDate)).perform(typeText(invalidBirthDay));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btnRegistration)).perform(click());
    }

    @Test
    public void testInvalidRegistrationWithoutData() {
        Espresso.onView(withId(R.id.btnRegistration)).perform(click());
    }

    @Test
    public void testValidRegistration() {
        Espresso.onView(withId(R.id.edtFirstName)).perform(typeText(validFirstName));
        Espresso.onView(withId(R.id.edtLastName)).perform(typeText(validLastName));
        Espresso.onView(withId(R.id.edtUserName)).perform(typeText(validUserName));
        Espresso.onView(withId(R.id.edtPassword)).perform(typeText(validPassword));
        Espresso.onView(withId(R.id.edtEmail)).perform(typeText(validEmail));
        Espresso.onView(withId(R.id.edtPhoneNumber)).perform(typeText(validPhoneNumber));
        Espresso.onView(withId(R.id.edtCity)).perform(typeText(validAddress));
        Espresso.onView(withId(R.id.edtBirthDate)).perform(typeText(validBirthDay));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btnRegistration)).perform(click());
    }


    @After
    public void tearDown() throws Exception {
    }

}