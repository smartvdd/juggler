package me.ilich.juggler.usage.navigate.newstate;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.usage.R;
import me.ilich.juggler.usage.StateFactory;
import me.ilich.juggler.usage.fragments.StubContentFragment;
import me.ilich.juggler.usage.fragments.StubToolbarFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class NewActivityContentTitleTestCase extends ActivityInstrumentationTestCase2<JugglerActivity> {

    public NewActivityContentTitleTestCase() {
        super(JugglerActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        setActivityInitialTouchMode(true);
    }

    public void testNull() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, StateFactory.contentBelowToolbarState(null, null));
        setActivityIntent(intent);
        getActivity();
        onView(ViewMatchers.withText(R.string.stub_text_content)).check(doesNotExist());
        onView(withText(R.string.stub_text_title)).check(doesNotExist());
        onView(withText(R.string.stub_text_navigation)).check(doesNotExist());
    }

    public void testContentOnly() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, StateFactory.contentBelowToolbarState(null, StubContentFragment.class));
        setActivityIntent(intent);
        getActivity();
        onView(withText(R.string.stub_text_content)).check(matches(isDisplayed()));
        onView(withText(R.string.stub_text_title)).check(doesNotExist());
        onView(withText(R.string.stub_text_navigation)).check(doesNotExist());
    }

    public void testToolbarOnly() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, StateFactory.contentBelowToolbarState(StubToolbarFragment.class, null));
        setActivityIntent(intent);
        getActivity();
        onView(withText(R.string.stub_text_content)).check(doesNotExist());
        onView(withText(R.string.stub_text_title)).check(matches(isDisplayed()));
        onView(withText(R.string.stub_text_navigation)).check(doesNotExist());
    }

    public void testContentToolbar() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, StateFactory.contentBelowToolbarState(StubToolbarFragment.class, StubContentFragment.class));
        setActivityIntent(intent);
        getActivity();
        onView(withText(R.string.stub_text_content)).check(matches(isDisplayed()));
        onView(withText(R.string.stub_text_title)).check(matches(isDisplayed()));
        onView(withText(R.string.stub_text_navigation)).check(doesNotExist());
    }

}
