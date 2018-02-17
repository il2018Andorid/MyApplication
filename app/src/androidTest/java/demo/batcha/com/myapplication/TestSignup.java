package demo.batcha.com.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestSignup {

    Activity mActivity;
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("demo.batcha.com.myapplication", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<Signup> menuActivityTestRule =
            new ActivityTestRule<>(Signup.class, true, true);

    @Test
    public void clickSignUpButtonAfterFillingForm_showProgressAndSuccessScreen() {
        String first_name = "shah.jai75@gmail.com";
        String last_name = "7878787878";
        String emailAddress = "123456789";
        String password = "123456789";

        final EditText et_email = (EditText) menuActivityTestRule.getActivity().findViewById(R.id.et_email);
        final EditText et_mobile = (EditText) menuActivityTestRule.getActivity().findViewById(R.id.et_mobile);
        final EditText et_pwd = (EditText) menuActivityTestRule.getActivity().findViewById(R.id.et_pwd);
        final EditText et_cnf_pwd = (EditText) menuActivityTestRule.getActivity().findViewById(R.id.et_cnf_pwd);

        //find the firstname edit text and type in the first name
        onView(withId(R.id.et_email)).perform(typeText(first_name), closeSoftKeyboard());

        //find the lastname edit text and type in the last name
        onView(withId(R.id.et_mobile)).perform(typeText(last_name), closeSoftKeyboard());

        //find the email address edit text and type in the email address
        onView(withId(R.id.et_pwd)).perform(typeText(emailAddress), closeSoftKeyboard());

        //find the password edit text and type in the password
        onView(withId(R.id.et_cnf_pwd)).perform(typeText(password), closeSoftKeyboard());


        //click the signup button
       // assertEquals("password not matched!",et_pwd.getText().toString(),et_cnf_pwd.getText().toString());

        onView(withId(R.id.btn_signup)).perform(click());

        //check that we can see the success screen with success message
       /* String successString = InstrumentationRegistry.getTargetContext().getString(R.string.text_sign_up_successful);
        onView(withId(R.id.text_view_status_message)).check(matches(allOf(withText(successString), isDisplayed())));*/
    }
}
