 package io.zetaphase.chewsnap;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

 public class MealListActivity extends Activity {

    private ListView mealListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);
        mealListView = (ListView) findViewById(R.id.mealList);
        mealListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MealListActivity.this, ViewMealPopup.class);
                intent.putExtra("CLICKPOSITION", ""+position);
                startActivity(intent);
            }
        });
        mealListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Give option to delete meal
                return false;
            }
        });
        mealListView.setAdapter(MainActivity.mealAdapter);
    }
}
