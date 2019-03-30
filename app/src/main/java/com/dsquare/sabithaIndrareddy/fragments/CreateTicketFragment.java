package com.dsquare.sabithaIndrareddy.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.adapter.PhotoAdapter;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiClient1;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.pojos.creatTicketDistrictResponse.DistrictList;
import com.dsquare.sabithaIndrareddy.pojos.creatTicketDistrictResponse.DistrictListResponse;
import com.dsquare.sabithaIndrareddy.pojos.createticketStateResponse.Example;
import com.dsquare.sabithaIndrareddy.pojos.createticketStateResponse.StateList;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;

import static android.app.Activity.RESULT_OK;

/**
 * Created by D square on 12-04-2018.
 */

public class CreateTicketFragment extends Fragment implements View.OnClickListener
{
    private Button attachments;
    private Button send;
    private Spinner spinner1,spinner2;
    private EditText name,address,mobile,email,grievance_details,action_required;
    private TextView gender;
    private List<String> statenameid = new ArrayList<>();
    private List<String> districtid = new ArrayList<>();
    private List<String> stateNameList = new ArrayList<>();
    private List<String> districtNameList = new ArrayList<>();
//    private HashMap<String , String > stateMap = new HashMap<>();
//    private HashMap<String , String > districtMap = new HashMap<>();
    private String stateId="",districtId="";
    ArrayList<String> selectedPhotos = new ArrayList<>();
    private List<String> imageList = new ArrayList<>();
    private PhotoAdapter photoAdapter;
    private RecyclerView recyclerView;
    private String typeString="",stateCode="",image1="",image2="",image3="";
    private CommonSharePrefrences comshare;
    private CommonFunction com;
    private RadioGroup radioGroup,radioGroup1;
    private String genderString="",religion="",citizenString="",differntlyabledString="",confidentialString="";
    private Switch citizen,differentlyabled,confidential;
    public FragmentManager fragmentManager;
    public MainTicketsFragment main;
   // private clickCallback callback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_ticket, container, false);
        InitializeViews(view);
        return view;
    }



    private void InitializeViews(View view)
    {
        com = new CommonFunction(getContext());
        comshare = CommonSharePrefrences.getInstance(getContext());

        main = new MainTicketsFragment();

        send = (Button)view.findViewById(R.id.bt_send);
        attachments = (Button)view.findViewById(R.id.attachments);
        spinner1 = (Spinner)view.findViewById(R.id.spinner1);
        spinner2= (Spinner)view.findViewById(R.id.spinner2);

       // desc = (EditText)view.findViewById(R.id.et_grievance_details);
        name = (EditText) view.findViewById(R.id.et_name);
        address = (EditText)view.findViewById(R.id.address_text);
        mobile = (EditText)view.findViewById(R.id.mobile_no);
        email = (EditText)view.findViewById(R.id.email_no);
        grievance_details = (EditText) view.findViewById(R.id.et_grievance_details);
        action_required = (EditText)view.findViewById(R.id.action_required);

        radioGroup = (RadioGroup)view.findViewById(R.id.radio_sex);
        radioGroup1 = (RadioGroup)view.findViewById(R.id.radio_religion);

        citizen = (Switch)view.findViewById(R.id.citizen_switch);
        differentlyabled = (Switch)view.findViewById(R.id.differntly_switch);
        confidential = (Switch)view.findViewById(R.id.confidential_switch);

        gender = (TextView)view.findViewById(R.id.tv_gender);
        fragmentManager = getFragmentManager();


        name.setHint ( Html.fromHtml ( "Name "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        grievance_details.setHint ( Html.fromHtml ( "Grievance Details"+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        address.setHint ( Html.fromHtml ( "Address "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        mobile.setHint ( Html.fromHtml ( "Mobile No "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
      //  email.setHint ( Html.fromHtml ( "Email ID "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        gender.setText(Html.fromHtml ( "Gender "+"<font color=\"#ff0000\">" + "* " + "</font>" ));


        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_items, stateNameList);
        countriesAdapter.setDropDownViewResource(R.layout.spinner_items);
        spinner1.setAdapter(countriesAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i!=0) {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getContext(), R.color.dark_gray));
                   // stateId = String.valueOf(i);
                    //stateId =statenameid.get(i);
                    System.out.print(stateId + "StateId");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
//

        ArrayAdapter<String> countriesAdapter1 = new ArrayAdapter<String>(getContext(), R.layout.spinner_items, districtNameList);
        countriesAdapter1.setDropDownViewResource(R.layout.spinner_items);
        spinner2.setAdapter(countriesAdapter1);

        getStateList();

        send.setOnClickListener(this);
        attachments.setOnClickListener(this);



     //   db = new DatabaseHelper(getContext());
        //recyclerView
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        photoAdapter = new PhotoAdapter(getActivity(), selectedPhotos);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);

        stateNameList.add("Select Grievance Type");
        districtNameList.add("Select Complaints");



        citizen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    citizenString = "Yes";
                }
                else
                {
                    citizenString = "No";
                }
            }
        });


        differentlyabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    differntlyabledString = "Yes";
                }
                else
                {
                    differntlyabledString = "No";
                }

            }
        });

        confidential.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    confidentialString = "y";
                }
                else
                {
                    confidentialString = "n";
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.radio_male) {
                    genderString = "M";
                }
                else if (i == R.id.radio_female) {
                    genderString = "F";
                }
                else
                {
                    genderString = "O";
                }
            }
        });

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.radio_rural) {
                    religion = "R";
                }
                else if (i == R.id.radio_urban) {
                    religion = "U";
                }
            }
        });



        send.setOnClickListener(this);
        attachments.setOnClickListener(this);

    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.bt_send:
                if(!stateId.equals(""))
                {
                    if(!districtId.equals(""))
                    {
                        if (!grievance_details.equals(""))
                        {
                            getGrievanceRegister();
                         //   Toast.makeText(getContext(), "Submitted Sucessfully", Toast.LENGTH_SHORT).show();

                        }
                        else
                            {
                            Toast.makeText(getContext(), "Please enter Description", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Please select Complients", Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    Toast.makeText(getContext(), "Please select Grievance", Toast.LENGTH_SHORT).show();
                    }






//                if(grievance_details.getText().toString().equals(""))
//                {
//                    //name.setText("");
//                    //email.setText("");
//                    //address.setText("");
//                    //mobile.setText("");
////                    grievance_details.setText("");
////                    action_required.setText("");
//
//                    if(!stateId.equals(""))
//                    {
//                        if(!districtId.equals(""))
//                        {
//
//               // Toast.makeText(getContext(),"Submitted Sucessfully",Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            Toast.makeText(getContext(), "Please select District", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    else
//                    {
//                       Toast.makeText(getContext(), "Please select State", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//                else
//                {
//                    Toast.makeText(getContext(), "Please fill all the mandatory fields", Toast.LENGTH_SHORT).show();
//                }
              break;
            case R.id.attachments:

                openCamera();

                if(selectedPhotos.size()==2)
                {
                    attachments.setVisibility(View.GONE);

                }
                break;

        }

    }



    private void openCamera()
    {
        Fragment fragment = new CreateTicketFragment();
         //selectedPhotos.clear();
        PhotoPicker.builder()
                .setPhotoCount(3)
                .setShowCamera(true)
                .setSelected(selectedPhotos)
                .setPreviewEnabled(false)
                .start(this.getActivity(),CreateTicketFragment.this);
    }

    @Override
    public void onActivityResult(int requestCode, int resuleCode, Intent intent)
    {

        super.onActivityResult(requestCode, resuleCode, intent);

        Log.d("token1","token");

               if (resuleCode == RESULT_OK &&
                    (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE))
            {
                List<String> photos = null;
                if (intent != null)
                {
                    photos = intent.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                }

                selectedPhotos.clear();
                imageList.clear();

                if (photos != null)
                {
                    selectedPhotos.addAll(photos);
                    photoAdapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);

                }



                photoAdapter.notifyDataSetChanged();

                for(int i=0;i<selectedPhotos.size();i++)
                {
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        InputStream is = null;
                        Log.d("url",selectedPhotos.get(i).toString());
//                        Bitmap bitmap = BitmapFactory.decodeStream(is,null,options);
//                        is.close();
                        is = new FileInputStream(selectedPhotos.get(i).toString());
                        Bitmap bitmap = BitmapFactory.decodeStream(is);

                        getStringImage(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


    }

    //convert images into string
    public String getStringImage(Bitmap bmp)
    {
        BitmapFactory.Options options = null;
        options = new BitmapFactory.Options();
        options.inScaled = false;
        options.inSampleSize = 1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 10, baos);
        byte[] imageBytes = baos.toByteArray();
        Log.d("url",imageBytes+"");
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        imageList.add(encodedImage);
        return encodedImage;
    }



    private void getStateList()
    {
        ApiInterface apiResponse = ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call = apiResponse.getStateList();

        call.enqueue(new Callback<Example>()
        {
            @Override
            public void onResponse(Call<Example> call, retrofit2.Response<Example> response)
            {
                if(response.code() == 200)
                {
                    parseStateResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t)
            {

            }
        });

    }

    private void parseStateResponse(Example body)
    {
//        stateNameList.clear();
//        statenameid.clear();
        try
        {

            for(int i =0 ;i<body.getMessage().size();i++)

            {
                stateNameList.add(body.getMessage().get(i).getGrievance_type());
                statenameid.add(body.getMessage().get(i).getId());
                // stateMap.put(body.getMessage().get(i).getId());
            }

            ArrayAdapter<String> countriesAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_items, stateNameList);
            countriesAdapter.setDropDownViewResource(R.layout.spinner_items);
            spinner1.setAdapter(countriesAdapter);

            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                {
                    if(i!=0)
                    {
                        stateId = statenameid.get(i-1);
                         getDistrictList();
                        ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getContext(), R.color.dark_gray));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView)
                {

                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void getDistrictList()
    {


        ApiInterface apiResponse = ApiClient.getClient().create(ApiInterface.class);

        Call<DistrictListResponse> call = apiResponse.getDistrictList(stateId);

        call.enqueue(new Callback<DistrictListResponse>()
        {
            @Override
            public void onResponse(Call<DistrictListResponse> call, retrofit2.Response<DistrictListResponse> response)
            {
                if(response.code() == 200)
                {
                    parseDistrictResponse(response.body());
                   //
                    // districtNameList.add("Select Complaints");

                }
            }

            @Override
            public void onFailure(Call<DistrictListResponse> call, Throwable t) {

            }

        });

    }

    private void parseDistrictResponse(DistrictListResponse body)
    {

        districtId="";
        districtNameList.clear();
        districtNameList.add("Select Complients");
        districtid.clear();
        for(int i =0 ;i<body.getComplaintsList().size();i++)
        try
        {
            districtNameList.add(body.getComplaintsList().get(i).getComplaint_title());
            districtid.add(body.getComplaintsList().get(i).getId());
               // districtMap.put(list.getComplaint_title(),list.getGrievance_id());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        ArrayAdapter<String> countriesAdapter1 = new ArrayAdapter<String>(getContext(), R.layout.spinner_items, districtNameList);
        countriesAdapter1.setDropDownViewResource(R.layout.spinner_items);
        spinner2.setAdapter(countriesAdapter1);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i!=0)
                {
                    districtId =(districtid.get(i-1));
                    // districtId = districtMap.get(districtNameList.get(i));
                    ((TextView) adapterView.getChildAt(0)).setTextColor(ContextCompat.getColor(getContext(), R.color.dark_gray));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }

    private void getGrievanceRegister()
    {
        com.showProgressDialogue();

        if(imageList.size()==1)
        {
            image1 = imageList.get(0);
        }
        else if(imageList.size()==2)
        {
            image1 = imageList.get(0);
            image2 = imageList.get(1);
        }
        else if(imageList.size()==3)
        {
            image1 = imageList.get(0);
            image2 = imageList.get(1);
            image3 = imageList.get(2);
        }

        JSONObject object = new JSONObject();

        try
        {
            object.put("user_id",comshare.getUserId());
            object.put("token",comshare.getToken());
            object.put("grievanceTypeId",stateId);
            object.put("complaintListId",districtId);
            object.put("grievanceDetails",grievance_details.getText().toString());
            object.put("uploadedImage",image1);
            object.put("uploadedImage1",image2);
            object.put("uploadedImage2",image3);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        RequestBody body =
                RequestBody.create(MediaType.parse("application/json"), object.toString());

        Call<ResponseBody> call = apiInterface.getRegisterPetition(comshare.getUserId(),comshare.getToken(),stateId,districtId,
                grievance_details.getText().toString(),image1,image2,image3);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response)
            {
                com.dismissProgressDialogue();
                {
                    if(response.isSuccessful())
                    {
                        JSONObject object = null;
                        try {
                            object = new JSONObject(response.body().string());
                            parseResponse(object);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                com.dismissProgressDialogue();
            }
        });




    }

    private void parseResponse(JSONObject jsonObject)
    {
        try {
            if(jsonObject.getString("status").equals("1"))
            {
               name.setText("");
                email.setText("");
                address.setText("");
                mobile.setText("");
                grievance_details.setText("");
                action_required.setText("");
                spinner1.setSelection(0);
                spinner2.setSelection(0);
                selectedPhotos.clear();
                photoAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("com.dsquare.sabithaIndrareddy.fragments.MainTicketsFragment");

                if(fragment != null && fragment instanceof MainTicketsFragment)
                    ((MainTicketsFragment) fragment).changeFragment();
                else
                    Log.e("ddd", "cannot change tab its null...");
             //   callback.changeFragment();

//                Fragment fragment = new TicketDetailsFragment();
//                FragmentTransaction ft = fragmentManager.beginTransaction();
//                ft.replace(R.id.frame_layout, fragment);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                ft.commit();
            }
            else
            {
                Toast.makeText(getContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT).show();
                //Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

//    @Override
//    public void onAttach(Context activity) {
//        super.onAttach(activity);
//
//        // check if Activity implements listener
//        if (activity instanceof clickCallback) {
//            callback = (clickCallback) activity;
//        } else {
//            throw new RuntimeException(activity.toString()
//                    + " must implement OnChildFragmentToActivityInteractionListener");
//        }
//    }
//
//
//    public interface clickCallback {
//
//        void changeFragment();
//    }

}
