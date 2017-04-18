 package io.zetaphase.chewsnap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                final CharSequence[] items = { "Delete", "Share", "Cancel" };

                AlertDialog.Builder builder = new AlertDialog.Builder(MealListActivity.this);
                builder.setTitle(MainActivity.mealList.get(pos).getTitle().toUpperCase());
                final int position = pos;
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Delete")) {
                            MainActivity.mealList.remove(position);
                            MainActivity.mealAdapter.updateMealList(MainActivity.mealList);
                            MainActivity.mealAdapter.notifyDataSetChanged();
                        }
                        dialog.dismiss();
                    }
                });
                builder.show();
                return true;
            }
        });
        mealListView.setAdapter(MainActivity.mealAdapter);
    }
}
