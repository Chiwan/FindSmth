package com.example.user.findsmth;
import android.app.Activity;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
    public String EXTRA_INTERACTION_DEPLACEMENT = "interactionDep";
    @ViewById
    RadioGroup deplacement;
    @ViewById(R.id.accelerometre)
    RadioButton deplacementResult;

    @ViewById
    RadioGroup changeView;
    RadioButton changeViewResult;

    @Click
    public void launchGame(){
        int selectedId = deplacement.getCheckedRadioButtonId();
        deplacementResult = (RadioButton) findViewById(selectedId);
        selectedId = changeView.getCheckedRadioButtonId();
        changeViewResult = (RadioButton) findViewById(selectedId);
        Intent intent = new Intent(MainActivity.this, Game_.class);
        intent.putExtra(EXTRA_INTERACTION_DEPLACEMENT, deplacementResult.getText());
        startActivity(intent);
    }

}

