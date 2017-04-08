package io.zetaphase.chewsnap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by xeliot on 4/6/17.
 */

public class NewMealActivity extends Activity{

    private ListView dishListView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meal);
    }
}
