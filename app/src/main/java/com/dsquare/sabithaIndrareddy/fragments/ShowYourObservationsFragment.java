package com.dsquare.sabithaIndrareddy.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.dsquare.sabithaIndrareddy.R;
import com.dsquare.sabithaIndrareddy.activities.HomeActivity;
import com.dsquare.sabithaIndrareddy.activities.OtpActivity;
import com.dsquare.sabithaIndrareddy.adapter.PhotoAdapter;
import com.dsquare.sabithaIndrareddy.networks.ApiClient;
import com.dsquare.sabithaIndrareddy.networks.ApiInterface;
import com.dsquare.sabithaIndrareddy.utils.CommonFunction;
import com.dsquare.sabithaIndrareddy.utils.CommonSharePrefrences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by D square on 16-04-2018.
 */

public class ShowYourObservationsFragment extends Fragment implements View.OnClickListener
{
    private ImageView attachments;
    private Button send;
    private EditText name,email,mobileno,address,desc;
    private RecyclerView recyclerView;
    ArrayList<String> selectedPhotos = new ArrayList<>();
    private PhotoAdapter photoAdapter;
    List<String> strings=new ArrayList<>();
    private CommonFunction com;
    private CommonSharePrefrences comShare;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
       View  view = inflater.inflate(R.layout.fragment_show_your_observation,container,false);
        initializeViews(view);
        return view;

    }

    private void initializeViews(View view)
    {
        com = new CommonFunction(getContext());
        comShare = CommonSharePrefrences.getInstance(getContext());
        attachments = (ImageView)view.findViewById(R.id.attachments);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        photoAdapter = new PhotoAdapter(getActivity(), selectedPhotos);
        send = (Button)view.findViewById(R.id.signup_button);
        name = (EditText)view.findViewById(R.id.name);
        email = (EditText)view.findViewById(R.id.email);
        mobileno = (EditText)view.findViewById(R.id.MobileNo);
        address = (EditText)view.findViewById(R.id.address_et);
        desc = (EditText)view.findViewById(R.id.desc);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);

        attachments.setOnClickListener(this);
        send.setOnClickListener(this);

        name.setHint ( Html.fromHtml ( "Name "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        email.setHint ( Html.fromHtml ( "Email"+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        address.setHint ( Html.fromHtml ( "Address "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );
        mobileno.setHint ( Html.fromHtml ( "Mobile No "+"<font color=\"#ff0000\">" + "* " + "</font>" ) );

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.attachments:
                openCamera();
                break;
            case R.id.signup_button:


               // Toast.makeText(getContext(),"Successfully Submitted",Toast.LENGTH_SHORT).show();
                Log.d("tttt","tttt");
//                    if(!name.getText().toString().equals(""))
//                    {
//                        if(!email.getText().toString().equals(""))
//                        {
//                            if(isValidEmail(email.getText().toString()))
//                            {
//                                if (!address.getText().toString().equals(""))
//                                {
//                                    if(!mobileno.getText().toString().equals(""))
//                                    {
//                                        if(mobileno.getText().toString().length()>=6)
//                                        {
                                            if(!desc.getText().toString().equals(""))
                                            {
                                                //addObservations();
                                             //   Toast.makeText(getContext(),"Successfully Submitted",Toast.LENGTH_SHORT).show();

                                                 addObservations();

                                            }
                                            else
                                            {
                                                Toast.makeText(getContext(),"Please enter description",Toast.LENGTH_SHORT).show();
                                            }
//                                        }
//                                        else
//                                        {
//                                            Toast.makeText(getContext(),"Please enter valid mobile",Toast.LENGTH_SHORT).show();
//                                        }
//
//                                    }
//                                    else
//                                    {
//                                        Toast.makeText(getContext(),"Please enter mobile",Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                                else
//                                {
//                                    Toast.makeText(getContext(),"Please enter address",Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                            else
//                            {
//                                Toast.makeText(getContext(),"Please enter valid email",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        else
//                        {
//                            Toast.makeText(getContext(),"Please enter email",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    else
//                    {
//                        Toast.makeText(getContext(),"Please enter name",Toast.LENGTH_SHORT).show();
//                    }
                break;
        }

    }

    /* Check Whether Entered Email is Valid or Not */
    public boolean isValidEmail(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


    private void openCamera()
    {
        Fragment fragment = new CreateTicketFragment();
         selectedPhotos.clear();
        PhotoPicker.builder()
                .setPhotoCount(1
                )
                .setShowCamera(true)
                .setSelected(selectedPhotos)
                .setPreviewEnabled(false)
                .start(this.getActivity(),ShowYourObservationsFragment.this);
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
            strings.clear();

            if (photos != null)
            {
                selectedPhotos.addAll(photos);
                recyclerView.setVisibility(View.VISIBLE);
            }

            photoAdapter.notifyDataSetChanged();

            for(int i=0;i<selectedPhotos.size();i++)
            {
                try
                {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    InputStream is = null;
                    is = new FileInputStream(selectedPhotos.get(i).toString());
                    Log.d("url",selectedPhotos.get(i).toString());
                    BitmapFactory.decodeStream(is,null,options);
                    is.close();
                    is = new FileInputStream(selectedPhotos.get(i).toString());
             // here w and h are the desired width and height
                    options.inSampleSize = Math.max(options.outWidth/460, options.outHeight/288);
              // bitmap is the resized bitmap
                    Bitmap bitmap = BitmapFactory.decodeStream(is,null,options);

                    getStringImage(bitmap);
                }
                catch (IOException e)
                {
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
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        Log.d("url",imageBytes+"");
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        strings.add(encodedImage);
        return encodedImage;
    }

    private void addObservations()
    {
        com.showProgressDialogue();
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        try
        {

        for(int i = 0 ; i < strings.size() ; i++)
        {
            array.put(strings.get(i));
            JSONObject object1 = new JSONObject();
            object1.put("image_name",strings.get(i));
            array.put(object1);
        }

        object.put("observation_name",name.getText().toString());
        object.put("observation_email",email.getText().toString());
        object.put("observation_address",address.getText().toString());
        object.put("observation_mobileno",mobileno.getText().toString());
        object.put("observation_desc",desc.getText().toString());
        object.put("token", comShare.getToken());
        object.put("user_id", comShare.getUserId());
        object.put("observation_images",array);


        } catch (JSONException e)
        {
        e.printStackTrace();
    }

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        RequestBody body =
                RequestBody.create(MediaType.parse("application/json"), object.toString());

        Call<ResponseBody> call = apiService.addObservations(body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                try {
                    com.dismissProgressDialogue();
                    if(response.code()==200)
                    {
                        JSONObject object = null;
                        object = new JSONObject(response.body().string());

                        parseResponse(object);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
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
            if(jsonObject.getInt("status")==1)
            {
                name .setText("");
                email .setText("");
                mobileno .setText("");
                address .setText("");
                desc .setText("");
                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
            else
            {
                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
