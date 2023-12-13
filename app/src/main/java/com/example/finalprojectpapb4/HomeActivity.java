package com.example.finalprojectpapb4;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Dummy data for testing
        List<ReviewModel> dummyReviewList = getDummyReviewList();

        // Set up RecyclerView and Adapter
        RecyclerView recyclerView = findViewById(R.id.recyclerViewReview);
        ReviewAdapter reviewAdapter = new ReviewAdapter(this, dummyReviewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(reviewAdapter);
    }

    private List<ReviewModel> getDummyReviewList() {
        List<ReviewModel> dummyList = new ArrayList<>();

        dummyList.add(new ReviewModel("Bromo, Malang", "22 Nov 2023", "Shani Indira", "Pemandangannya keren, tapi..."));
        dummyList.add(new ReviewModel("Another Location", "23 Nov 2023", "John Doe", "Lorem ipsum dolor sit amet."));
        // Add more dummy data as needed

        dummyList.add(new ReviewModel("Bromo, Malang", "22 Nov 2023", "Shani Indira", "Pemandangannya keren, tapi..."));
        dummyList.add(new ReviewModel("Another Location", "23 Nov 2023", "John Doe", "Lorem ipsum dolor sit amet."));
        // Add more dummy data as needed

        dummyList.add(new ReviewModel("Bromo, Malang", "22 Nov 2023", "Shani Indira", "Pemandangannya keren, tapi..."));
        dummyList.add(new ReviewModel("Another Location", "23 Nov 2023", "John Doe", "Lorem ipsum dolor sit amet."));
        // Add more dummy data as needed

        dummyList.add(new ReviewModel("Bromo, Malang", "22 Nov 2023", "Shani Indira", "Pemandangannya keren, tapi..."));
        dummyList.add(new ReviewModel("Another Location", "23 Nov 2023", "John Doe", "Lorem ipsum dolor sit amet."));
        // Add more dummy data as needed

        dummyList.add(new ReviewModel("Bromo, Malang", "22 Nov 2023", "Shani Indira", "Pemandangannya keren, tapi..."));
        dummyList.add(new ReviewModel("Another Location", "23 Nov 2023", "John Doe", "Lorem ipsum dolor sit amet."));
        // Add more dummy data as needed
        dummyList.add(new ReviewModel("Bromo, Malang", "22 Nov 2023", "Shani Indira", "Pemandangannya keren, tapi..."));
        dummyList.add(new ReviewModel("Another Location", "23 Nov 2023", "John Doe", "Lorem ipsum dolor sit amet."));
        // Add more dummy data as needed

        dummyList.add(new ReviewModel("Bromo, Malang", "22 Nov 2023", "Shani Indira", "Pemandangannya keren, tapi..."));
        dummyList.add(new ReviewModel("Another Location", "23 Nov 2023", "John Doe", "Lorem ipsum dolor sit amet."));
        // Add more dummy data as needed

        dummyList.add(new ReviewModel("Bromo, Malang", "22 Nov 2023", "Shani Indira", "Pemandangannya keren, tapi..."));
        dummyList.add(new ReviewModel("Another Location", "23 Nov 2023", "John Doe", "Lorem ipsum dolor sit amet."));
        // Add more dummy data as needed



        return dummyList;
    }
}
