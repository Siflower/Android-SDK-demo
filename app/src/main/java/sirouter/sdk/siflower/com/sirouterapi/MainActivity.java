package sirouter.sdk.siflower.com.sirouterapi;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.LocalApi;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.IFace;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WDSInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WDSScanInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WiFiAdvanceInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.Model.WiFiInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.SFException;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetDeviceDataUsageParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetDeviceRestrictParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.GetFreqIntergrationParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetCustomWiFiParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetDeviceDataUsageParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetDeviceRestrictParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetFreqIntergrationParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetLeaseNetParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetPasswordParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetWanTypeParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.SetWiFiAdvanceInfo;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.param.WifiParam;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.BindRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.DataUsage;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.Device;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetCustomWiFiRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetDeviceDataUsageRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetDeviceRestrictRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetFreqIntergrationRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetLeaseNetRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.GetWanTypeRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetCustomWiFiRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetDeviceDataUsageRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetDeviceRestrictRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetFreqIntergrationRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetLeaseNetRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetPasswordRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWanTypeRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWiFiAdvanceRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.SetWiFiDetailRet;
import sirouter.sdk.siflower.com.locallibrary.siwifiApi.ret.UnbindRet;
import sirouter.sdk.siflower.com.remotelibrary.Listener.RemoteConnectionListener;
import sirouter.sdk.siflower.com.remotelibrary.Listener.SFObjectResponseListener;
import sirouter.sdk.siflower.com.remotelibrary.Listener.SiWiFiListListener;
import sirouter.sdk.siflower.com.remotelibrary.SFClass.Routers;
import sirouter.sdk.siflower.com.remotelibrary.SFUser;
import sirouter.sdk.siflower.com.remotelibrary.SiWiFiManager;

public class MainActivity extends AppCompatActivity {
    private EditText editText;

    private MainActivity mainActivity;

    private SFUser mUser;

    private Routers routers;

    private Button loginExtra;

    private Button bindSiRouter;
    private Button unbindSiRouter;

    private Button getWifiObserve;
    private Button getWiFiAdvanceObserve;
    private Button getCustomWiFiFace;
    private Button getWDSInfo;
    private Button getWDSScan;
    private Button getWDSRelIp;
    private Button getDeviceRestrict;
    private Button getDeviceDataUsage;
    private Button getSiRouterDeviceDetail;
    private Button getFreqIntergration;
    private Button getLeaseNet;
    private Button getRouters;
    private Button getGatewayIp;
    private Button getWanTypeObserve;

    private Button setFreqIntergration;
    private Button setDeviceDataUsage;
    private Switch setFreqIntergration_switch;
    private Button setLeaseNet;
    private Button setWanType;
    private Button setWiFiAdvance;
    private Button setWiFi;
    private Button setAdminPassword;
    private EditText setOldpwd;
    private EditText setNewpwd;
    private Button setDeviceRestrict;
    private Button setCustomWiFi;


    private static final String TAG = "mainActivity";
    private String appKey = "c20ad4d76fe97759aa27a0c99bff6710";
    private String appSecret = "864850023f299568b353d21e55c6c892";
    ProgressDialog progressDialog;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SiWiFiManager.init(this,appKey,appSecret);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("This is ProgressDialog");
        progressDialog.setMessage("Loading...");
        mUser = SFUser.getCacheUser(this);
        mainActivity = this;

        loginExtra = findViewById(R.id.loginExtra);
        editText = findViewById(R.id.editText);
        setOldpwd = findViewById(R.id.setOldpwd);
        setNewpwd = findViewById(R.id.setNewpwd);
        bindSiRouter = findViewById(R.id.bindSiRouter);
        unbindSiRouter = findViewById(R.id.unbindSiRouter);
        getWifiObserve = findViewById(R.id.getWifiObserve);
        getWiFiAdvanceObserve = findViewById(R.id.getWiFiAdvanceObserve);
        getCustomWiFiFace = findViewById(R.id.getCustomWiFiIFace);
        getWDSInfo = findViewById(R.id.getWDSInfo);
        getWDSScan = findViewById(R.id.getWDSScan);
        getWDSRelIp = findViewById(R.id.getWDSRelIp);
        getSiRouterDeviceDetail = findViewById(R.id.getSiRouterDeviceDetail);
        getDeviceDataUsage = findViewById(R.id.getDeviceDataUsage);
        getDeviceRestrict = findViewById(R.id.getDeviceRestrict);
        getFreqIntergration = findViewById(R.id.getFreqIntergration);
        getLeaseNet = findViewById(R.id.getLeaseNet);
        getRouters = findViewById(R.id.getRouters);
        getWanTypeObserve = findViewById(R.id.getWanTypeObserve);
        getGatewayIp = findViewById(R.id.getGatewayIp);

        setFreqIntergration = findViewById(R.id.setFreqIntergration);
        setFreqIntergration_switch=findViewById(R.id.setFreqIntergration_switch);
        setDeviceDataUsage = findViewById(R.id.setDeviceDataUsage);
        setLeaseNet = findViewById(R.id.setLeaseNet);
        setWanType = findViewById(R.id.setWanType);
        setAdminPassword = findViewById(R.id.setAdminPassword);
        setDeviceRestrict = findViewById(R.id.setDeviceRestrict);
        setCustomWiFi = findViewById(R.id.setCustomWiFi);
        setWiFi = findViewById(R.id.setWiFi);
        setWiFiAdvance =  findViewById(R.id.setWiFiAdvance);



        if (mUser != null && !mUser.getLoginkey().equals("")) {
            SFUser.loginByKey(this, mUser.getLoginkey(), new SFObjectResponseListener<SFUser>() {

                @Override
                public void onSuccess(SFUser sfUser) {
                    Log.e(TAG, "login success" + new Gson().toJson(sfUser));
                    MainActivity.this.mUser = sfUser;
                    if (sfUser.getBinder() != null) {
                        if (sfUser.getBinder().size() != 0) {
                            Log.e(TAG, "not zero"+new Gson().toJson(sfUser.getBinder()));
                            routers = sfUser.getBinder().get(0);
                        }
                    }

                    SiWiFiManager.getInstance().createRemoteConnection(sfUser, new RemoteConnectionListener() {
                        @Override
                        public void onConnectSuccess() {

                            Log.e(TAG, "on connection success");
                        }

                        @Override
                        public void onConnectionClose(int code, String reason) {
                            Log.e(TAG, "on connection close");
                        }

                        @Override            
                        public void onFailure(Exception ex) {
                            Log.e(TAG, "on Failure");
                        }
                    });
                }

                @Override
                public void onError(SFException ex) {
                    Log.e(TAG, "login error" + ex.getMessage());
                }
            });
        } else {
            SFUser.loginByExtra(this, "999556", new SFObjectResponseListener<SFUser>() {
                @Override
                public void onSuccess(SFUser sfUser) {
                    Log.e(TAG, "login success" + new Gson().toJson(sfUser));
                    MainActivity.this.mUser = sfUser;
                    if (sfUser.getBinder() != null) {
                        if (sfUser.getBinder().size() != 0) {
                            Log.e(TAG, "not zero"+new Gson().toJson(sfUser.getBinder()));
                            routers = sfUser.getBinder().get(0);
                        }

                    }
                    SiWiFiManager.getInstance().createRemoteConnection(sfUser, new RemoteConnectionListener() {
                        @Override
                        public void onConnectSuccess() {
                            Log.e(TAG, "on connection success");
                        }

                        @Override
                        public void onConnectionClose(int code, String reason) {
                            Log.e(TAG, "on connection close");
                        }

                        @Override
                        public void onFailure(Exception ex) {
                            Log.e(TAG, "on Failure");
                        }
                    });
                }

                @Override
                public void onError(SFException ex) {
                    Log.e(TAG, "login error" + ex.getMessage());
                }
            });
        }



        bindSiRouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                SiWiFiManager.getInstance().bindSiRouter(MainActivity.this, LocalApi.DEFAULT_APP_API_VERSION, mUser, new SingleObserver<BindRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(BindRet bindRet) {
                        Log.e(TAG, "bind success ");
                        Toast.makeText(MainActivity.this, new Gson().toJson(bindRet), Toast.LENGTH_SHORT).show();
                        SiWiFiManager.getInstance().getRouters(mUser, new SiWiFiListListener<Routers>() {
                            @Override
                            public void onSuccess(List<Routers> objlist) {
                                for (Routers routers : objlist) {
                                    if (routers.getObjectId().equals(bindRet.getRouterobjectid())) {
                                        MainActivity.this.routers = routers;
                                    }
                                }
                            }

                            @Override
                            public void onError(int code, String msg) {

                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "bind error " + e.getMessage());
                    }
                });
            }
        });


        getWifiObserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SiWiFiManager.getInstance().getWifiObserve(routers, mUser, new SingleObserver<List<WiFiInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<WiFiInfo> wiFiInfos) {
                        progressDialog.dismiss();
                        Log.d(TAG, "wifiinfos: " + new Gson().toJson(wiFiInfos));
                        Toast.makeText(MainActivity.this, new Gson().toJson(wiFiInfos), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.d(TAG, "wifiinfos: " + e.getMessage());
                    }
                });
            }
        });


        loginExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editText.getText().toString();
                Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();

            }
        });


        getCustomWiFiFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SiWiFiManager.getInstance().getCustomWiFiIFace(routers, mUser, new SingleObserver<GetCustomWiFiRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetCustomWiFiRet getCustomWiFiRet) {
                        progressDialog.dismiss();
                        Log.d(TAG, "getCustomWiFiRet" + new Gson().toJson(getCustomWiFiRet));
                        Toast.makeText(MainActivity.this, new Gson().toJson(getCustomWiFiRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.d(TAG, "getCustomWiFiRet" + e.getMessage());
                    }
                });
            }
        });


        getWDSInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SiWiFiManager.getInstance().getWDSInfo(routers, mUser, new SingleObserver<List<WDSInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<WDSInfo> wdsInfos) {
                        progressDialog.dismiss();
                        Log.d(TAG, "wdsinfos: " + new Gson().toJson(wdsInfos));
                        Toast.makeText(MainActivity.this, new Gson().toJson(wdsInfos), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.d(TAG, "wdsinfos: " + e.getMessage());
                    }
                });
            }
        });


        getSiRouterDeviceDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SiWiFiManager.getInstance().getSiRouterDeviceDetail(routers, mUser, SiWiFiManager.a.a, new SingleObserver<List<Device>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Device> devices) {
                        progressDialog.dismiss();
                        Log.d(TAG, "devices: " + new Gson().toJson(devices));
                        Toast.makeText(MainActivity.this, new Gson().toJson(devices), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.d(TAG, "devices: " + e.getMessage());
                    }
                });
            }
        });


        getDeviceDataUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                GetDeviceDataUsageParam param = new GetDeviceDataUsageParam("V10");
                param.setMac("A0_86_C6_9D_28_7D");
                SiWiFiManager.getInstance().getDeviceDataUsage(routers, mUser, param, new SingleObserver<GetDeviceDataUsageRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetDeviceDataUsageRet getDeviceDataUsageRet) {
                        progressDialog.dismiss();
                        Log.d(TAG, "getDeviceDataUsageRet" + new Gson().toJson(getDeviceDataUsageRet));
                        Toast.makeText(MainActivity.this, new Gson().toJson(getDeviceDataUsageRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.d(TAG, "getDeviceDataUsageRet" + e.getMessage());
                    }
                });
            }
        });


        getLeaseNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SiWiFiManager.getInstance().getLeaseNet(routers, mUser, new SingleObserver<GetLeaseNetRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetLeaseNetRet getLeaseNetRet) {
                        progressDialog.dismiss();
                        Log.d(TAG, "getLeaseNet" + new Gson().toJson(getLeaseNetRet));
                        Toast.makeText(MainActivity.this, new Gson().toJson(getLeaseNetRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.d(TAG, "getLeaseNet" + e.getMessage());
                    }
                });
            }
        });


        getWDSScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                String band = "2.4G";
                SiWiFiManager.getInstance().getWDSScan(routers, mUser, band, new SingleObserver<List<WDSScanInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<WDSScanInfo> wdsScanInfos) {
                        progressDialog.dismiss();
                        Log.d(TAG, "wdsScanInfos: " + new Gson().toJson(wdsScanInfos));
                        Toast.makeText(MainActivity.this, new Gson().toJson(wdsScanInfos), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.d(TAG, "wdsScanInfos: " + e.getMessage());
                    }
                });
            }
        });


        getWDSRelIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                String band = "2.4G";
                SiWiFiManager.getInstance().getWDSRelIp(routers, mUser, band, new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        progressDialog.dismiss();
                        Log.d(TAG, "s: " + new Gson().toJson(s));
                        Toast.makeText(MainActivity.this, new Gson().toJson(s), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.d(TAG, "s: " + e.getMessage());
                    }
                });
            }
        });


        getDeviceRestrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                GetDeviceRestrictParam param = new GetDeviceRestrictParam("V10");
                param.setMac("A0_86_C6_9D_28_7D");
                SiWiFiManager.getInstance().getDeviceRestrict(routers, mUser, param, new SingleObserver<GetDeviceRestrictRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetDeviceRestrictRet getDeviceRestrictRet) {
                        progressDialog.dismiss();
                        Log.d(TAG, "getDeviceRestrictRet" + new Gson().toJson(getDeviceRestrictRet));
                        Toast.makeText(MainActivity.this, new Gson().toJson(getDeviceRestrictRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.d(TAG, "getDeviceRestrictRet" + e.getMessage());
                    }
                });
            }
        });


        getFreqIntergration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                GetFreqIntergrationParam param = new GetFreqIntergrationParam("V17");

                SiWiFiManager.getInstance().getFreqIntergration(routers, mUser, param, new SingleObserver<GetFreqIntergrationRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetFreqIntergrationRet getFreqIntergrationRet) {
                        progressDialog.dismiss();
                        Log.d(TAG, " getFreqIntergrationRet" + new Gson().toJson(getFreqIntergrationRet));
                        Toast.makeText(MainActivity.this, new Gson().toJson(getFreqIntergrationRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.d(TAG, " getFreqIntergrationRet" + e.getMessage());
                    }
                });
            }
        });


        getRouters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SiWiFiManager.getInstance().getRouters(mUser, new SiWiFiListListener<Routers>() {
                    @Override
                    public void onSuccess(List<Routers> list) {
                        progressDialog.dismiss();
                        Log.d(TAG, "  getRouters" + new Gson().toJson(list));
                        Toast.makeText(MainActivity.this, new Gson().toJson(list), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(int i, String s) {
                        progressDialog.dismiss();
                        Log.d(TAG, " getFreqIntergrationRet" + s);
                    }
                });
            }
        });


        getWanTypeObserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SiWiFiManager.getInstance().getWanTypeObserve(routers, mUser, new SingleObserver<GetWanTypeRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(GetWanTypeRet getWanTypeRet) {
                        progressDialog.dismiss();
                        Log.d(TAG, "getWanTypeRet)" + new Gson().toJson(getWanTypeRet));
                        Toast.makeText(MainActivity.this, new Gson().toJson(getWanTypeRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.d(TAG, "getWanTypeRet)" + e.getMessage());
                    }
                });
            }
        });


        getWiFiAdvanceObserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SiWiFiManager.getInstance().getWifiAdvanceObserve(routers, mUser, new SingleObserver<List<WiFiAdvanceInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<WiFiAdvanceInfo> wiFiAdvanceInfos) {
                        progressDialog.dismiss();
                        Log.d(TAG, "wiFiAdvanceInfos: " + new Gson().toJson(wiFiAdvanceInfos));
                        Toast.makeText(MainActivity.this, new Gson().toJson(wiFiAdvanceInfos), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.d(TAG, "wiFiAdvanceInfos: " + e.getMessage());
                    }
                });
            }
        });


        getGatewayIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                Log.d(TAG, "getGateWayIp" + (SiWiFiManager.getInstance().getGatewayIp(mainActivity)));
                Toast.makeText(MainActivity.this, new Gson().toJson((SiWiFiManager.getInstance().getGatewayIp(mainActivity))), Toast.LENGTH_SHORT).show();
            }
        });


        setFreqIntergration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SetFreqIntergrationParam param = new SetFreqIntergrationParam("V17");
                if(setFreqIntergration_switch.isChecked()){
                    param.setEnable(1);
                }else {
                    param.setEnable(0);
                }
                Toast.makeText(mainActivity, "开始请求", Toast.LENGTH_SHORT).show();
                SiWiFiManager.getInstance().setFreqIntergration(routers, mUser, param, new SingleObserver<SetFreqIntergrationRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(SetFreqIntergrationRet setFreqIntergrationRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setFreqIntergrationRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        setDeviceDataUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SetDeviceDataUsageParam param = new SetDeviceDataUsageParam("V14");
                param.setMac("A0_86_C6_9D_28_7D");
                List<DataUsage> list = new ArrayList<DataUsage>();
                DataUsage dataUsage = new DataUsage();
                dataUsage.setCount(100);
                dataUsage.setType(1);
                dataUsage.setWeek("1,7");
                list.add(dataUsage);
                param.setSetlist(list);
                param.setChange(100);
                param.setUsageenable(1);
                SiWiFiManager.getInstance().setDeviceDataUsage(routers,mUser,param).subscribe(new SingleObserver<SetDeviceDataUsageRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetDeviceDataUsageRet setDeviceDataUsageRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setDeviceDataUsageRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        setLeaseNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SetLeaseNetParam param = new SetLeaseNetParam("V14");
                param.setEnable(true);
                param.setSsid("liuxiaopeng-5G");
                param.setLimitdownload(100);
                SiWiFiManager.getInstance().setLeaseNet(routers, mUser, true, "liuxiaopeng-5G", 100, new SingleObserver<SetLeaseNetRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetLeaseNetRet setLeaseNetRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setLeaseNetRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        setWanType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SetWanTypeParam param = new SetWanTypeParam("V14");
                param.setType(0);
                SiWiFiManager.getInstance().setWanType(routers, mUser, param, new SingleObserver<SetWanTypeRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetWanTypeRet setWanTypeRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setWanTypeRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        setAdminPassword.setOnClickListener(new View.OnClickListener() {
            String a,b;
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SetPasswordParam param = new SetPasswordParam("V14");
                param.setOldpwd(setOldpwd.getText().toString());
                param.setNewpwd(setNewpwd.getText().toString());
                SiWiFiManager.getInstance().setAdminPassword(routers, mUser, setOldpwd.getText().toString(), setNewpwd.getText().toString(), new SingleObserver<SetPasswordRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetPasswordRet setPasswordRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setPasswordRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        setDeviceRestrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SetDeviceRestrictParam param = new SetDeviceRestrictParam("V14");
                param.setMac("A0_86_C6_9D_28_7D");
                param.setSocial(0);
                param.setVideo(0);
                param.setGame(1);
                param.setRestrictenable(0);
                SiWiFiManager.getInstance().setDeviceRestrict(routers, mUser, param).subscribe(new SingleObserver<SetDeviceRestrictRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetDeviceRestrictRet setDeviceRestrictRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setDeviceRestrictRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        setCustomWiFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SetCustomWiFiParam param = new SetCustomWiFiParam("V14");
                List<IFace> list = new ArrayList<IFace>();
                IFace iFace = new IFace();
                iFace.setBand("2.4G");
                iFace.setRemainingtime(0);
                iFace.setSsid("liuxiaopeng-2.4G");
                iFace.setEnable(true);
                iFace.setLimittimetype(0);
                iFace.setOpen(false);
                iFace.setLimitupload(10);
                iFace.setPeriodicaltime("1-6");
                iFace.setKey("123456789");
                iFace.setLimitdownload(10);
                iFace.setLocalaccess(false);
                iFace.setLimittime(false);
                list.add(iFace);
                param.setIfaces(list);
                SiWiFiManager.getInstance().setCustomWiFi(routers, mUser, param, new SingleObserver<SetCustomWiFiRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetCustomWiFiRet setCustomWiFiRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setCustomWiFiRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        setWiFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                List<WifiParam>list = new ArrayList<WifiParam>();
                WifiParam wifiParam = new WifiParam();
                wifiParam.enable = 1;
                wifiParam.encryption = "psk2+ccmp";
                wifiParam.signalmode = 0;
                wifiParam.channel = 1;
                wifiParam.password = "12345678";
                wifiParam.oldssid = "siwifi-8340-5G";
                wifiParam.newssid = "LIU-2.4G";
                list.add(wifiParam);
                SiWiFiManager.getInstance().setWiFi(routers, mUser, list, new SingleObserver<SetWiFiDetailRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetWiFiDetailRet setWiFiDetailRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setWiFiDetailRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        setWiFiAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;
                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();;
                List<SetWiFiAdvanceInfo>list = new ArrayList<SetWiFiAdvanceInfo>();

                SetWiFiAdvanceInfo param24 = new SetWiFiAdvanceInfo();
                param24.country = "CN";
                param24.rts = 0;
                param24.distance = 0;
                param24.enable = 1;
                param24.encryption = "psk2+ccmp";
                param24.fragment = 0;
                param24.signalmode = 0;

                SetWiFiAdvanceInfo param5 = new SetWiFiAdvanceInfo();
                param5.country = "CN";
                param5.rts = 0;
                param5.distance = 0;
                param5.enable = 1;
                param5.encryption = "psk2+ccmp";
                param5.fragment = 0;
                param5.signalmode = 0;

                list.add(param24);
                list.add(param5);

                SiWiFiManager.getInstance().setWiFiAdvance(routers, mUser, list, new SingleObserver<SetWiFiAdvanceRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SetWiFiAdvanceRet setWiFiAdvanceRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(setWiFiAdvanceRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        unbindSiRouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser == null) {
                    return;

                }

                if (routers == null) {
                    return;
                }
                progressDialog.show();
                SiWiFiManager.getInstance().unbindSiRouter(routers, mUser, new SingleObserver<UnbindRet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(UnbindRet unbindRet) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, new Gson().toJson(unbindRet), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

