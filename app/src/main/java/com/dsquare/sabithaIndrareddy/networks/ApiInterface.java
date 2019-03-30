package com.dsquare.sabithaIndrareddy.networks;

import com.dsquare.sabithaIndrareddy.pojos.EventsListResponse.EventResponse;
import com.dsquare.sabithaIndrareddy.pojos.congress.CongressPojo;
import com.dsquare.sabithaIndrareddy.pojos.creatTicketDistrictResponse.DistrictListResponse;
import com.dsquare.sabithaIndrareddy.pojos.createticketStateResponse.Example;
import com.dsquare.sabithaIndrareddy.pojos.createticketlist.ViewPetitionResponse;
import com.dsquare.sabithaIndrareddy.pojos.feedslist.FeedData;
import com.dsquare.sabithaIndrareddy.pojos.publicationtypeResponse.PublicationResponse;
import com.dsquare.sabithaIndrareddy.pojos.questionsResponse.QuestionResponse;
import com.dsquare.sabithaIndrareddy.pojos.registrationResponse.RegistrationResponse;
import com.dsquare.sabithaIndrareddy.pojos.suveryResponse.SurveyResponse;
import com.dsquare.sabithaIndrareddy.pojos.updatemobileotpResponse.UpdateMobileNoOtpResponse;
import com.dsquare.sabithaIndrareddy.pojos.visitrajnivasidtypes.VisitRajNivasIdTypesResponse;
import com.dsquare.sabithaIndrareddy.pojos.volunteerResponse.VolunteerResponse;

import org.json.JSONArray;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by D square on 14-05-2018.
 */

public interface ApiInterface
{
  @POST("events.php")
  @FormUrlEncoded
  Call<EventResponse> eventResponse(@Field("pg") String pg,@Field("events_type") String events_type);

  @POST("news_feeds.php")
  @FormUrlEncoded
  Call<FeedData> feedDataResponse(@Field("pg") String pg);

  @POST("surveys.php")
  @FormUrlEncoded
  Call<SurveyResponse> getSurveys(@Field("pg") String pg,@Field("poll_user_id") String user_id);

  @POST("servey_questions.php")
  @FormUrlEncoded

  Call<QuestionResponse> getQuestions(@Field("survey_id") String survey_id);

  @POST("volunteer_skills.php")
  Call<VolunteerResponse> getVolunteerResponse();

  @POST("add_volunteer.php")
  @FormUrlEncoded
  Call<ResponseBody> addVoluteer(@Field("volunteer_fblink") String volunteer_fblink,@Field("volunteer_twitlink") String volunteer_twitlink,
                                 @Field("volunteer_mobile") String volunteer_mobile,@Field("volunteer_name") String volunteer_name,@Field("volunteer_address") String volunteer_address,
                                 @Field("volunteer_desc") String volunteer_desc,@Field("volunteer_gender") String volunteer_gender,@Field("volunteer_email") String volunteer_email,
                                 @Field("skill_ids")String skill_ids,@Field("user_id")String userId,@Field("token")String token);
  @POST("registration.php")
  @FormUrlEncoded
  Call<RegistrationResponse> getRegistratiionResponse(@Field("user_mobile") String user_mobile);

  @POST("verify_otp.php")
  @FormUrlEncoded
  Call<ResponseBody> verifyOtp(@Field("mobile_verified_otp") String mobile_verified_otp,@Field("user_mobile") String user_mobile);


  @POST("add_observation.php")
  @FormUrlEncoded
  Call<ResponseBody> addObservations(@Field("observation_name") String observation_name,@Field("observation_email") String observation_email,@Field("observation_address") String observation_address,
                                     @Field("observation_mobileno") String observation_mobileno,@Field("observation_desc") String observation_desc,@Field("token") String token,@Field("user_id") String userId,
                                     @Field("observation_images") JSONArray observation_images);

  @POST("add_observation.php")
  Call<ResponseBody> addObservations(@Body RequestBody response);

  @POST("submit_poll.php")
  Call<ResponseBody> submitSurvey(@Body RequestBody response);

  @POST("add_visitor.php")
  @FormUrlEncoded
  Call<ResponseBody> addVisitors(@Field("visit_group_type") String visit_group_type,@Field("visit_date") String visit_date,@Field("visit_audults") String visit_adults,
                                 @Field("visit_children") String visit_childern,@Field("visit_name") String visit_name,@Field("visit_nationality") String visit_nationality,
                                 @Field("visit_dob") String visit_dob,@Field("visit_gender") String visit_gender,@Field("visit_address") String visit_address,
                                 @Field("visit_mobile") String visit_mobile,@Field("visit_email") String visit_email,@Field("visit_state") String visit_state,
                                 @Field("visit_id_type") String visit_id_type,@Field("visit_id_number") String visit_id_number,@Field("visit_samll_group_desc")
                                 String visit_small_group_desc,@Field("a_person1") String a_person1,@Field("a_person2") String a_person2,@Field("a_person3") String a_person3,
                                 @Field("a_person4") String a_person4,@Field("token") String token,@Field("user_id") String userID);
  @POST("token_register.php")
  @FormUrlEncoded
  Call<ResponseBody> updateDeviceToken(@Field("device_token") String token,@Field("device_id") String deviceId,@Field("member_id") String id,@Field("device_type") String device_type);

  @GET("grievanceTypes")
  Call<Example>  getStateList();

  @GET("complaintsList")
  Call<DistrictListResponse> getDistrictList(@Query("grievance_id") String grievance_id);


   @POST("grievanceRegister")
   @FormUrlEncoded
   Call<ResponseBody> getRegisterPetition(@Field("user_id") String user_id,@Field("token") String token,@Field("grievanceTypeId") String grievacneTypeId,@Field("complaintListId") String complaintListId,
                                          @Field("grievanceDetails")String grievanceDetails,@Field("uploadedImage")String uploadedImage,@Field("uploadedImage1") String uploadImage1,@Field("uploadedImage2") String uploadImage2);

  @POST("grievanceRegister")
  Call<ResponseBody> getRegisterPetition(@Body RequestBody body);

  @GET("viewPetitionStatus")
  Call<ResponseBody> getViewPetitionDetails(@Query("grievance_number") String code);

   @GET("viewpetitionlist.php")
   Call<ViewPetitionResponse> getViewPetitionList(@Query("user_id") String user_id);

  @POST("publication_types.php")
  Call<PublicationResponse> getPublicationTypes();

  @POST("publications.php")
  @FormUrlEncoded
  Call<PublicationResponse> getPublicationTypes(@Field("type_id") String type_id,@Field("pg") String pg);

//  @POST("update_profile.php")
//  @FormUrlEncoded
//  Call<ResponseBody> getUpdateprofile(@Field("user_id") String user_id,@Field("user_name")String user_name,@Field("user_email")String user_email);

  @POST("user_profile.php")
  @FormUrlEncoded
  Call<ResponseBody> getuserProfile(@Field("user_id") String user_id);

  @POST("id_types.php")
  Call<VisitRajNivasIdTypesResponse> getVisitorIdtypes();

  @POST("validate_user.php")
  @FormUrlEncoded
  Call<ResponseBody> getValidateUser(@Field("user_id") String user_id);

  @GET("INC.json")
  Call<CongressPojo> getCongressResponse();

//  @POST("grievanceRegister")
//  Call<ResponseBody> getRegisterPetition (@Body );

  @POST("updateProfile")
  @FormUrlEncoded
  Call<ResponseBody> getUpdateProfile (@Field("user_id") String user_id,@Field("user_name") String user_name,@Field("user_mobile") String user_mobile,@Field("user_email") String user_email,@Field("user_dob") String user_dob,@Field("user_gender") String user_gender,
                                       @Field("user_aadhaar_card") String user_aadhaar_card,@Field("user_voter_id") String user_voter_id,@Field("user_address1") String user_address1,@Field("user_address2") String user_address2);
  @GET("liveStreams")
    Call<ResponseBody> getLiveStrems();

  @POST("getOTP")
  @FormUrlEncoded
  Call<UpdateMobileNoOtpResponse> getOtp (@Field("user_mobile") String user_mobile);

   @POST("updatemobile")
   @FormUrlEncoded
   Call<ResponseBody> getupdateMobilenumber (@Field("user_id") String user_id,@Field("new_mobile") String new_mobile);

   @GET("about_us.php")
  Call<ResponseBody> getaboutSabitha ();










}
