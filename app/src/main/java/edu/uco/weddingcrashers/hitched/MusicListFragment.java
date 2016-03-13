package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by PC User on 3/3/2016.
 */
public class MusicListFragment extends Fragment {
    private EditText nameEditText,artistEditText;
    private Button searchButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_list,container,false);
        nameEditText = (EditText)view.findViewById(R.id.music_name_edit_text);
        artistEditText = (EditText)view.findViewById(R.id.artist_name_edit_text);
        searchButton = (Button)view.findViewById(R.id.submit_music_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MusicActivity.newIntent(getActivity(),nameEditText.getText().toString(),artistEditText.getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }
}
