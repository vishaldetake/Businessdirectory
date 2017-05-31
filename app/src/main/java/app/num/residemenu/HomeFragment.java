package app.num.residemenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.special.ResideMenu.ResideMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class HomeFragment extends Fragment {

    private View parentView;
    private ResideMenu resideMenu;
    GridView mListView;
    private static final String endpoint="http://192.168.0.4/BusinessDirectory/category.php";
    //private static final String endpoint="http://192.168.0.4/navarthkarnti/ws/getEmployee.php";
    private ArrayList<Category> mArraylist;
    private Category_adapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.home, container, false);


        mListView=(GridView)parentView.findViewById(R.id.listView);

        mArraylist=new ArrayList<>();
        feachdata();
        //setUpViews();
        return parentView;
    }

    private void feachdata() {

        String base=endpoint;
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams param=new RequestParams();
        client.post(base, param, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("Exception",new String(responseBody));

                try {
                    JSONObject jsonobject=new JSONObject(new String(responseBody));
                    JSONArray jsonarray=jsonobject.getJSONArray("results");
                    mArraylist.clear();

                    for (int i=0;i<jsonarray.length();i++){

                        JSONObject object=jsonarray.getJSONObject(i);
                        Category mcategory = new Category(object.getString("cid"),object.getString("category_name"),object.getString("img"));
                        //Category mcategory = new Category(object.getString("id"),object.getString("name"),object.getString("img"));
                        mArraylist.add(mcategory);

                    }

                    mAdapter=new Category_adapter(getContext(),R.layout.categorylist,R.id.txtText,mArraylist);
                    mListView.setAdapter(mAdapter);
                 mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (statusCode == 404) {
                    Toast.makeText(getContext(), "Requested resource not found", Toast.LENGTH_LONG).show();

                } else if (statusCode == 500) {

                    Toast.makeText(getContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

//    private void setUpViews() {
//        MainActivity parentActivity = (MainActivity) getActivity();
//        resideMenu = parentActivity.getResideMenu();
//
//        parentView.findViewById(R.id.btn_open_menu).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
//            }
//        });
//
//        // add gesture operation's ignored views
//        FrameLayout ignored_view = (FrameLayout) parentView.findViewById(R.id.ignored_view);
//        resideMenu.addIgnoredView(ignored_view);
//    }

}
