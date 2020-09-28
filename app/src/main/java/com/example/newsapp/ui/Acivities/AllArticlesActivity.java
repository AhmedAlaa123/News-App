package com.example.newsapp.ui.Acivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.newsapp.R;
import com.example.newsapp.pojo.Utils;

public class AllArticlesActivity extends AppCompatActivity {

    private EditText editTextQuery;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_articles);

        imageButton = findViewById(R.id.imageButton);
        editTextQuery = findViewById(R.id.editText_query);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextQuery.getText().toString().trim().equals("")) {
                    editTextQuery.setError("Query is Requird");
                    return;
                }
                Intent intent = new Intent(AllArticlesActivity.this, ShowNewsActivity.class);
                intent.putExtra(Utils.OPTION_EXTRA, 5);
                intent.putExtra(Utils.QUERY, editTextQuery.getText().toString());
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}