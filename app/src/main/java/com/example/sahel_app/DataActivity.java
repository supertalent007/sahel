package com.example.sahel_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.io.IOException;
import java.util.ArrayList;
import okhttp3.*;


/* loaded from: classes.dex */
public class DataActivity extends AppCompatActivity {
    private OkHttpClient client;
    private ImageView imageAdvertisements;
    private ImageView imageData;
    private ImageView imageDates;
    private ImageView imageNotifications;
    private ImageView imageServices;
    LayoutInflater inflater;
    View otpLayout;
    View popupLayout;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_data);
        this.client = new OkHttpClient();
        /*
        String stringExtra = getIntent().getStringExtra("EXTRA_OTP_CODE");
        if (stringExtra != null) {
            showPopupNotification(stringExtra);
        }
         */
        showOtpCodeEntryPage();

        this.inflater = LayoutInflater.from(this);
        this.otpLayout = getLayoutInflater().inflate(R.layout.otp_entry_layout, (ViewGroup) null);
        final ViewPager2 viewPager2 = (ViewPager2) findViewById(R.id.pager);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Data_Fragment());
        arrayList.add(new ServicesFragment());
        arrayList.add(new NotificationsFragment());
        arrayList.add(new DatesFragment());
        arrayList.add(new AdvertisementsFragment());
        this.imageData = (ImageView) findViewById(R.id.imageData);
        this.imageDates = (ImageView) findViewById(R.id.imageDates);
        this.imageServices = (ImageView) findViewById(R.id.imageServices);
        this.imageNotifications = (ImageView) findViewById(R.id.imageNotifications);
        this.imageAdvertisements = (ImageView) findViewById(R.id.imageAdvertisement);
        this.imageData.setOnTouchListener(new View.OnTouchListener() { // from class: com.example.sahel_app.DataActivity.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    DataActivity.this.imageData.setColorFilter(Color.argb(255, 0, 0, 255));
                    viewPager2.setCurrentItem(0);
                } else if (action == 1) {
                    DataActivity.this.imageData.clearColorFilter();
                }
                return true;
            }
        });
        this.imageServices.setOnTouchListener(new View.OnTouchListener() { // from class: com.example.sahel_app.DataActivity.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    DataActivity.this.imageServices.setColorFilter(Color.argb(255, 0, 0, 255));
                    viewPager2.setCurrentItem(1);
                } else if (action == 1) {
                    DataActivity.this.imageServices.clearColorFilter();
                }
                return true;
            }
        });
        this.imageNotifications.setOnTouchListener(new View.OnTouchListener() { // from class: com.example.sahel_app.DataActivity.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    DataActivity.this.imageNotifications.setColorFilter(Color.argb(255, 0, 0, 255));
                    viewPager2.setCurrentItem(2);
                } else if (action == 1) {
                    DataActivity.this.imageNotifications.clearColorFilter();
                }
                return true;
            }
        });
        this.imageDates.setOnTouchListener(new View.OnTouchListener() { // from class: com.example.sahel_app.DataActivity.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    DataActivity.this.imageDates.setColorFilter(Color.argb(255, 0, 0, 255));
                    viewPager2.setCurrentItem(3);
                } else if (action == 1) {
                    DataActivity.this.imageDates.clearColorFilter();
                }
                return true;
            }
        });
        this.imageAdvertisements.setOnTouchListener(new View.OnTouchListener() { // from class: com.example.sahel_app.DataActivity.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    DataActivity.this.imageAdvertisements.setColorFilter(Color.argb(255, 0, 0, 255));
                    viewPager2.setCurrentItem(4);
                } else if (action == 1) {
                    DataActivity.this.imageAdvertisements.clearColorFilter();
                }
                return true;
            }
        });
        viewPager2.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), getLifecycle(), this));
    }

    /*
    public void showPopupNotification(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your OTP code is: " + str);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.example.sahel_app.DataActivity.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                DataActivity.this.showOtpCodeEntryPage();
            }
        });
        builder.create().show();
    }
     */

    /* JADX INFO: Access modifiers changed from: private */
    public void showOtpCodeEntryPage() {
        final View inflate = getLayoutInflater().inflate(R.layout.otp_entry_layout, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        create.show();
        Button button = (Button) inflate.findViewById(R.id.submitOTPButton);
        Button button2 = (Button) inflate.findViewById(R.id.cancelOTPButton);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.example.sahel_app.DataActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DataActivity.this.verifyOtpWithServer(((EditText) inflate.findViewById(R.id.otpEditText)).getText().toString(), create);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.example.sahel_app.DataActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DataActivity.this.startActivity(new Intent(DataActivity.this, (Class<?>) com.example.sahel_app.LoginActivity.class));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void verifyOtpWithServer(String str, AlertDialog alertDialog) {
        this.client.newCall(new Request.Builder().url("http://kuwaithackers.dyndns.org:5000/verifyOtp").post(RequestBody.create("{\"otp\":\"" + str + "\"}", MediaType.parse("application/json; charset=utf-8"))).build()).enqueue(new AnonymousClass9(alertDialog));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.example.sahel_app.DataActivity$9, reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass9 implements Callback {
        final /* synthetic */ AlertDialog val$dialog;

        AnonymousClass9(AlertDialog alertDialog) {
            this.val$dialog = alertDialog;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onFailure$0$com-example-sahel_app-DataActivity$9, reason: not valid java name */
        public /* synthetic */ void m217lambda$onFailure$0$comexamplesahel_appDataActivity$9(IOException iOException) {
            Toast.makeText(DataActivity.this, "Verification failed: " + iOException.getMessage(), 0).show();
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, final IOException iOException) {
            DataActivity.this.runOnUiThread(new Runnable() { // from class: com.example.sahel_app.DataActivity$9$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    DataActivity.AnonymousClass9.this.m217lambda$onFailure$0$comexamplesahel_appDataActivity$9(iOException);
                }
            });
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                DataActivity dataActivity = DataActivity.this;
                final AlertDialog alertDialog = this.val$dialog;
                dataActivity.runOnUiThread(new Runnable() { // from class: com.example.sahel_app.DataActivity$9$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataActivity.AnonymousClass9.this.m218lambda$onResponse$1$comexamplesahel_appDataActivity$9(alertDialog);
                    }
                });
                return;
            }
            DataActivity.this.runOnUiThread(new Runnable() { // from class: com.example.sahel_app.DataActivity$9$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    DataActivity.AnonymousClass9.this.m219lambda$onResponse$2$comexamplesahel_appDataActivity$9();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onResponse$1$com-example-sahel_app-DataActivity$9, reason: not valid java name */
        public /* synthetic */ void m218lambda$onResponse$1$comexamplesahel_appDataActivity$9(AlertDialog alertDialog) {
            Toast.makeText(DataActivity.this, "OTP verified successfully", 0).show();
            alertDialog.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onResponse$2$com-example-sahel_app-DataActivity$9, reason: not valid java name */
        public /* synthetic */ void m219lambda$onResponse$2$comexamplesahel_appDataActivity$9() {
            Toast.makeText(DataActivity.this, "Incorrect OTP code. Please try again.", 0).show();
        }
    }
}
