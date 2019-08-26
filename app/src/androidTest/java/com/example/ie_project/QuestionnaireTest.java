package com.example.ie_project;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.app.AlertDialog;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class QuestionnaireTest
{

    @Rule
    public ActivityTestRule<Questionnaire> rule = new ActivityTestRule<>(Questionnaire.class,true);

    @Test
    public void PressContinueOnPage1() throws InterruptedException
    {
        onView(withId(R.id.next_button1)).perform(click());
        onView(withId(R.id.eatingSpinner)).check(matches(isDisplayed()));
        onView(withId(R.id.previous_button2)).check(matches(isDisplayed()));
    }

    @Test
    public void PressContinueOnPage2() throws InterruptedException
    {
        FragmentManager fragmentManager = rule.getActivity().getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire2()).commit();

        onView(withId(R.id.next_button2)).perform(click());
        onView(withId(R.id.next_button3)).check(matches(isDisplayed()));
        onView(withId(R.id.previous_button3)).check(matches(isDisplayed()));
    }

    @Test
    public void PressSubmitOnPage3() throws InterruptedException
    {
        FragmentManager fragmentManager = rule.getActivity().getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire3()).commit();

        onView(withId(R.id.next_button3)).perform(click());

        AlertDialog.Builder dialog = new AlertDialog.Builder(rule.getActivity());
        dialog.setTitle("Almost there!");

        onView(withText("Almost there!")).check(matches(isDisplayed()));
    }

    @Test
    public void PressPreviousOnPage2() throws InterruptedException
    {
        FragmentManager fragmentManager = rule.getActivity().getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire2()).commit();

        onView(withId(R.id.previous_button2)).perform(click());

        fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire1()).commit();

        onView(withId(R.id.next_button1)).check(matches(isDisplayed()));
    }

    @Test
    public void PressPreviousOnPage3() throws InterruptedException
    {
        FragmentManager fragmentManager = rule.getActivity().getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire3()).commit();

        onView(withId(R.id.previous_button3)).perform(click());

        fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire2()).commit();

        onView(withId(R.id.next_button2)).check(matches(isDisplayed()));
    }

    @Test
    public void PressYesOnPopUp() throws InterruptedException
    {
        FragmentManager fragmentManager = rule.getActivity().getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire3()).commit();

        onView(withId(R.id.next_button3)).perform(click());
        onView(withText("Yes")).perform(click());

        onView(withId(R.id.schedule_finish)).check(matches(isDisplayed()));
    }

    @Test
    public void PressNoOnPopUp() throws InterruptedException
    {
        FragmentManager fragmentManager = rule.getActivity().getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire3()).commit();

        onView(withId(R.id.next_button3)).perform(click());
        onView(withText("No")).perform(click());

        onView(withId(R.id.schedule)).check(matches(isDisplayed()));
    }
}