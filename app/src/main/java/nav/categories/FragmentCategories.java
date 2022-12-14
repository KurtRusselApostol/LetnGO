package nav.categories;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.letngo.R;

import nav.categories.recycleview.Post;
import nav.categories.recycleview.Postlist;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCategories#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCategories extends Fragment implements View.OnClickListener {

    // initialize variables for recycler view


    // variables for changing recycler view
    public static boolean beach;
    public static boolean treehouse;
    public static boolean camping;
    public static boolean caves;
    public static boolean countryside;
    public static boolean cabin;
    public static boolean island;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentCategories() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCategories.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCategories newInstance(String param1, String param2) {
        FragmentCategories fragment = new FragmentCategories();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.categories_fragement, container, false);
        RelativeLayout rl_beach = v.findViewById(R.id.beach);
        RelativeLayout rl_treehouse = v.findViewById(R.id.tree_house);
        RelativeLayout rl_camping = v.findViewById(R.id.camping);
        RelativeLayout rl_caves = v.findViewById(R.id.caves);
        RelativeLayout rl_countryside = v.findViewById(R.id.country_side);
        RelativeLayout rl_cabin = v.findViewById(R.id.cabin);
        RelativeLayout rl_island = v.findViewById(R.id.island);

        rl_beach.setOnClickListener(this::onClick);
        rl_treehouse.setOnClickListener(this::onClick);
        rl_camping.setOnClickListener(this::onClick);
        rl_caves.setOnClickListener(this::onClick);
        rl_countryside.setOnClickListener(this::onClick);
        rl_cabin.setOnClickListener(this::onClick);
        rl_island.setOnClickListener(this::onClick);

        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.beach:
                resetLocation();
                beach = true;
                Intent intent = new Intent(getActivity(), Postlist.class);
                startActivity(intent);
                break;

            case R.id.tree_house:
                resetLocation();
                treehouse = true;
                Intent intent1 = new Intent(getActivity(), Postlist.class);
                startActivity(intent1);
                break;

            case R.id.camping:
                resetLocation();
                camping = true;
                Intent intent2 = new Intent(getActivity(), Postlist.class);
                startActivity(intent2);
                break;

            case R.id.caves:
                resetLocation();
                caves = true;
                Intent intent3 = new Intent(getActivity(), Postlist.class);
                startActivity(intent3);
                break;

            case R.id.country_side:
                resetLocation();
                countryside = true;
                Intent intent4 = new Intent(getActivity(), Postlist.class);
                startActivity(intent4);
                break;
            case R.id.cabin:
                resetLocation();
                cabin = true;
                Intent intent5 = new Intent(getActivity(), Postlist.class);
                startActivity(intent5);
                break;
            case R.id.island:
                resetLocation();
                island = true;
                Intent intent6 = new Intent(getActivity(), Postlist.class);
                startActivity(intent6);
                break;
        }
    }

    private void resetLocation() {
        beach = false;
        treehouse = false;
        camping = false;
        caves = false;
        countryside = false;
        cabin = false;
        island = false;

    }
}