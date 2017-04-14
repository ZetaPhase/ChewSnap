package io.zetaphase.chewsnap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by xeliot on 4/6/17.
 */

public class NewMealActivity extends Activity{

    private ListView dishListView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meal);
        Button finishButton = (Button) findViewById(R.id.finishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.dishList.isEmpty()){
                    Toast.makeText(NewMealActivity.this, "Please add some dishes.", Toast.LENGTH_LONG).show();
                }else{
                    boolean a = addMeal();
                    if(!a){return;}
                    resetActivity();
                    Toast.makeText(NewMealActivity.this, "New Meal has been added!", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button addButton = (Button) findViewById(R.id.addButton); //needs to be implemented
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent popIntent = new Intent(NewMealActivity.this, PopDish.class);
                startActivity(popIntent);
            }
        });
        dishListView = (ListView) findViewById(R.id.dishList);
        dishListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("CLICKPOSITION", ""+position);
                Intent intent = new Intent(NewMealActivity.this, ViewDishPopup.class);
                intent.putExtra("CLICKPOSITION", ""+position);
                startActivity(intent);
            }
        });
        MainActivity.dishAdapter = new DishAdapter(this, 0, MainActivity.dishList);
        dishListView.setAdapter(MainActivity.dishAdapter);
    }

    public boolean addMeal(){

        TextView title = (TextView) findViewById(R.id.mealName);
        TextView description = (TextView) findViewById(R.id.description);
        TextView location = (TextView) findViewById(R.id.restaurantName);

        if(title.getText().toString().isEmpty() || description.getText().toString().isEmpty() || location.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill in the remaining fields.", Toast.LENGTH_LONG).show();
            return false;
        }

        Meal meal = new Meal(title.getText().toString(), description.getText().toString(), location.getText().toString());
        meal.setDishes(MainActivity.dishList);
        double averageRating = 0;
        for(Dish dish : MainActivity.dishList){
            averageRating += dish.getRating();
        }
        averageRating = (averageRating*1.0)/MainActivity.dishList.size();
        meal.setNumStars((int) averageRating);
        int randInt = (int)(Math.random()*MainActivity.dishList.size());
        meal.setBitmap(MainActivity.dishList.get(randInt).getBitmap());
        MainActivity.mealList.add(meal);
        MainActivity.mealAdapter.updateMealList(MainActivity.mealList);
        MainActivity.mealAdapter.notifyDataSetChanged();
        Log.d("addMEAL", ""+MainActivity.mealList.get(0).getTitle());
        return true;
    }

    public void resetActivity(){
        MainActivity.dishList = new ArrayList<Dish>();
        MainActivity.dishAdapter = new DishAdapter(this, 0, MainActivity.dishList);
        dishListView.setAdapter(MainActivity.dishAdapter);
        TextView title = (TextView) findViewById(R.id.mealName);
        TextView description = (TextView) findViewById(R.id.description);
        TextView restaurant = (TextView) findViewById(R.id.restaurantName);
        title.setText("");
        description.setText("");
        restaurant.setText("");

    }
}
