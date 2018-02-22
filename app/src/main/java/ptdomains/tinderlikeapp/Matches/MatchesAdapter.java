package ptdomains.tinderlikeapp.Matches;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ptdomains.tinderlikeapp.R;

/**
 * Created by eric on 22/02/18.
 */

public class MatchesAdapter extends RecyclerView.Adapter<MatchesViewHolders>{

    private List<MatchObject> matchList;
    public Context context;

    public MatchesAdapter(List<MatchObject> matchesList, Context context){
        this.matchList = matchesList;
        this.context = context;
    }

    @Override
    public MatchesViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matches, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );
        layoutView.setLayoutParams(lp);
        MatchesViewHolders rev = new MatchesViewHolders((layoutView));

        return rev;
    }

    @Override
    public void onBindViewHolder(MatchesViewHolders holder, int position) {
        holder.mMatchId.setText(matchList.get(position).getUserId());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
