package io.zetaphase.chewsnap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewDishPopup extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_dish_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.8), (int) (height*0.8));

        Intent intent = getIntent();
        int clickedPosition = Integer.valueOf(intent.getStringExtra("CLICKPOSITION"));

        Dish dish = MainActivity.dishList.get(clickedPosition);

        TextView title = (TextView) findViewById(R.id.viewDishTitle);
        TextView description = (TextView) findViewById(R.id.viewDishDescription);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.viewDishRating);
        ImageView image = (ImageView) findViewById(R.id.viewDishImage);

        title.setText(dish.getTitle());
        description.setText(dish.getDescription());
        ratingBar.setRating(dish.getRating());
        image.setImageBitmap(dish.getBitmap());

                MealAdapter listAdapter = new MealAdapter(this, 0, MainActivity.mealList);

    }
}