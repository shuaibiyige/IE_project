package com.example.ie_project;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AskFirstTest
{

    @Rule
    public ActivityTestRule<AskFirst> rule = new ActivityTestRule<>(AskFirst.class,true);


    @Test
    public void PressYes() throws InterruptedException
    {
        onView(withText("yes")).perform(click());
        onView(withText("Questionnaire")).check(matches(isDisplayed()));
    }

    @Test
    public void PressNo() throws InterruptedException
    {
        onView(withText("no")).perform(click());
        onView(withText("Schedule")).check(matches(isDisplayed()));
    }
}