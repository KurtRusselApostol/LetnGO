package nav.categories;
//Fragment for new Categories Fragment
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SearchView;

import com.example.letngo.R;

import java.util.ArrayList;

import nav.categories.recycleview.CatLabel;
import nav.categories.recycleview.MyAdapter2;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeCategories#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeCategories extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<CatLabel> catLabelArrayList;
    private String[] newsHeading;
    private int[] imageResourceID;
    private RecyclerView recyclerView;


    public HomeCategories() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeCategories.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeCategories newInstance(String param1, String param2) {
        HomeCategories fragment = new HomeCategories();
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

        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_categories, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MyAdapter2 myAdapter2 = new MyAdapter2(getContext(), catLabelArrayList);
        recyclerView.setAdapter(myAdapter2);

        myAdapter2.notifyDataSetChanged();

    }


    private void dataInitialize() {

        catLabelArrayList = new ArrayList<>();
        newsHeading = new String[]{
                "Beach",
                "TreeHouse",
                "Camping",
                "Caves",
                "Countryside",
                "Cabin",
                "Island"
        };

        imageResourceID = new int[]{
                R.drawable.beach_pic,
                R.drawable.tree_house,
                R.drawable.camp_pic,
                R.drawable.caves_pic,
                R.drawable.countyside_pic,
                R.drawable.cabin_pic,
                R.drawable.island_pic,
        };

        for(int i=0; i<newsHeading.length; i++){

            CatLabel catLabel = new CatLabel(newsHeading[i], imageResourceID[i]);
            catLabelArrayList.add(catLabel);

        }

    }

}
