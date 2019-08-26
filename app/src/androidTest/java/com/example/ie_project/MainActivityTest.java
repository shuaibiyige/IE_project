package com.example.ie_project;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class MainActivityTest
{

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class,true);

    @Test
    public void PressQuestionnaire() throws InterruptedException
    {
        Intent intent = new Intent();
        intent.putExtra("new user", true);
        rule.launchActivity(intent);
        onView(withText("Questionnaire")).perform(click());
        onView(withText("Tell us about you")).check(matches(isDisplayed()));
    }

    @Test
    public void PressSchedule() throws InterruptedException
    {
        Intent intent = new Intent();
        intent.putExtra("new user", false);
        rule.launchActivity(intent);
        onView(withText("Schedule")).perform(click());
        onView(withId(R.id.schedule_finish)).check(matches(isDisplayed()));
    }
}