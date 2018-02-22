package ptdomains.tinderlikeapp.Matches;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ptdomains.tinderlikeapp.R;

/**
 * Created by eric on 22/02/18.
 */

public class MatchesViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView mMatchId;

    public MatchesViewHolders(View itemView){
        super (itemView);
        itemView.setOnClickListener(this);

        mMatchId = (TextView) itemView.findViewById(R.id.Matchid);
    }

    @Override
    public void onClick(View view){

    }
}

