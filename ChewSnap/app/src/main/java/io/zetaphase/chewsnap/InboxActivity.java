package io.zetaphase.chewsnap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


public class InboxActivity extends Activity {

    private ListView inboxListView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
    }

}
