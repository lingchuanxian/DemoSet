package com.example.lingcx.demoset;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/4/26 下午6:28
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class BaiduMapActivity extends AppCompatActivity implements View.OnClickListener{
    protected final String TAG = this.getClass().getSimpleName();
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private RelativeLayout mProgressBarLoadingContainer;
    public LocationClient mLocationClient = null;

    /**
     * 当前经纬度
     */
    private double mLantitude;
    private double mLongtitude;
    private LatLng mLoactionLatLng;

    /**
     * MapView中央对于的屏幕坐标
     */
    private Point mCenterPoint = null;

    /**
     * 位置列表
     */
    private List<PoiInfo> mInfoList = new ArrayList<>();

    /**
     * 地理编码
     */
    GeoCoder mGeoCoder = null;
    /**
     * 是否是第一次定位
     */
    private boolean isFirstLocation = true;
    /**
     * 当前定位模式
     */
    private RecyclerView mRecyclerView;
    private PoiAdapter mPoiAdapter;
    /**
     * 当前位置信息
     */
    private PoiInfo mCurentInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_baidu);
        initViews();
    }

    private void initViews() {
        initMap();
        initLocation();
    }


    private void initMap() {
        mMapView = findViewById(R.id.map_view);
        mProgressBarLoadingContainer = findViewById(R.id.progress_loading_container);
        //附件位置集合recycleview
        mRecyclerView = findViewById(R.id.rclv_poi);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPoiAdapter = new PoiAdapter(mInfoList);
        mPoiAdapter.bindToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mPoiAdapter);

        mPoiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // 通知是适配器第position个item被选择了
                mPoiAdapter.setSelectedItem(position);

                mBaiduMap.clear();
                PoiInfo info = mPoiAdapter.getItem(position);
                LatLng la = info.location;

                // 动画跳转
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(la);
                mBaiduMap.animateMapStatus(u);
            }
        });

        //不显示缩放比例尺
        mMapView.showZoomControls(false);
        // 不显示百度地图Logo
        mMapView.removeViewAt(1);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setOnMapTouchListener(touchListener);

        // 初始化当前MapView中心屏幕坐标，初始化当前地理坐标
        mCenterPoint = mBaiduMap.getMapStatus().targetScreen;

        mLoactionLatLng = mBaiduMap.getMapStatus().target;

        // 地理编码
        mGeoCoder = GeoCoder.newInstance();
        mGeoCoder.setOnGetGeoCodeResultListener(GeoListener);

        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 改变地图状态，使地图显示在恰当的缩放大小
        MapStatus mMapStatus = new MapStatus.Builder().zoom(16).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
    }

    private void initLocation() {
        mLocationClient = new LocationClient(this);
        mLocationClient.setLocOption(initLocationClientOption());
        mLocationClient.registerLocationListener(new LocationListener());

        mLocationClient.start();
    }

    private LocationClientOption initLocationClientOption() {
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("gcj02");
        //可选，设置返回经纬度坐标类型，默认gcj02
        //gcj02：国测局坐标；
        //bd09ll：百度经纬度坐标；
        //bd09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

        option.setScanSpan(1001);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true

        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5*60*1000);
        //可选，7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        return option;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgv_reset){
            moveToLatLng(mLoactionLatLng);
        }
    }

    public class LocationListener extends BDAbstractLocationListener{
        @Override
        public void onReceiveLocation(BDLocation location) {
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(100)
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();

            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);

            // 设置自己定义图标
            MyLocationConfiguration config = new MyLocationConfiguration(
                    MyLocationConfiguration.LocationMode.NORMAL, false, null);
            mBaiduMap.setMyLocationConfigeration(config);


            mLantitude = location.getLatitude();
            mLongtitude = location.getLongitude();
            mLoactionLatLng = new LatLng(mLantitude, mLongtitude);

            LatLng currentLatLng = new LatLng(mLantitude, mLongtitude);

            if(isFirstLocation){
                moveToLatLng(currentLatLng);
                isFirstLocation = false;
            }
        }
    }

    /**
     * 地理编码监听器
     */
    private OnGetGeoCoderResultListener GeoListener = new OnGetGeoCoderResultListener() {
       @Override
       public void onGetGeoCodeResult(GeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                // 没有检索到结果
            }
            // 获取地理编码结果
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                // 没有找到检索结果
            }else {
                // 获取反向地理编码结果
                // 当前位置信息
                mCurentInfo = new PoiInfo();
                mCurentInfo.address = result.getAddress();
                mCurentInfo.location = result.getLocation();
                mCurentInfo.name = "[位置]";
                mInfoList.clear();
                mInfoList.add(mCurentInfo);

                // 将周边信息增加表
                if (result.getPoiList() != null) {
                    mInfoList.addAll(result.getPoiList());
                }
                // 通知适配数据已改变
                mPoiAdapter.notifyDataSetChanged();
                mPoiAdapter.setSelectedItem(0);
                mRecyclerView.setVisibility(View.VISIBLE);
                mProgressBarLoadingContainer.setVisibility(View.GONE);
            }
        }
    };

    /**
     * 地图触摸事件监听器
     */
    private BaiduMap.OnMapTouchListener touchListener = new BaiduMap.OnMapTouchListener() {
        @Override
        public void onTouch(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (mCenterPoint == null) {
                    return;
                }
                // 获取当前MapView中心屏幕坐标相应的地理坐标
                LatLng currentLatLng = mBaiduMap.getProjection().fromScreenLocation(
                        mCenterPoint);
                // 发起反地理编码检索
                mGeoCoder.reverseGeoCode((new ReverseGeoCodeOption())
                        .location(currentLatLng));
                mRecyclerView.setVisibility(View.GONE);
                mProgressBarLoadingContainer.setVisibility(View.VISIBLE);
            }
        }
    };

    private void moveToLatLng(LatLng mLatLng){
        //获取经纬度
        MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(mLatLng);
        //动画的方式到中间
        mBaiduMap.animateMapStatus(status);
        mGeoCoder.reverseGeoCode((new ReverseGeoCodeOption())
                .location(mLatLng));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        //关闭定位
        mBaiduMap.setMyLocationEnabled(false);
        if(mLocationClient.isStarted()){
            mLocationClient.stop();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

}
