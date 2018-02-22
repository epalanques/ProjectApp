package ptdomains.tinderlikeapp.Matches;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ptdomains.tinderlikeapp.R;

public class MatchesActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private RecyclerView.Adapter mMatchesAdapter;
    private RecyclerView.LayoutManager mMatchesLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        mRecycleView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecycleView.setNestedScrollingEnabled(false);
        mRecycleView.setHasFixedSize(true);
        mMatchesLayoutManager = new LinearLayoutManager(MatchesActivity.this);
        mRecycleView.setLayoutManager(mMatchesLayoutManager);
        mMatchesAdapter = new MatchesAdapter(getDatabaseSetMatches(), MatchesActivity.this);
        mRecycleView.setAdapter(mMatchesAdapter);

        for (int i = 0; i<100; i++){
            MatchObject obj = new MatchObject("asd");
            resultsMatches.add(obj);

        }


    }


    private ArrayList<MatchObject> resultsMatches = new ArrayList<MatchObject>();
    public List<MatchObject> getDatabaseSetMatches() {
        return resultsMatches;
    }
}
