package com.example.lingcx.demoset;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/4/30 上午9:07
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class GaodeMapActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    private MapView mMapView;
    private AMap mAMap;
    private RecyclerView mRecyclerView;

    LocationSource.OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    /**
     * 是否为第一次加载
     */
    private boolean isFirstLoad = true;
    /**
     * poiSearch相关
     */
    private PoiSearch poiSearch;
    private PoiSearch.Query query;
    /**
     * 是否进行poi搜索
     */
    private boolean isPoiSearched = false;

    private List<PoiItem> mPoiItemList = new ArrayList<>();
    private List<PoiAddressBean> mPoiAddressBeanList = new ArrayList<>();
    private PoiGaodeAdapter mPoiGaodeAdapter;
    /**
     * 当前页码
     */
    private int mCurrentPageIndex = 0;
    /**
     * 每页显示的poi长度
     */
    private int PAGE_SIZE = 20;

    /**
     * 当前定位经纬度
     */
    private double mCurrentLat;
    private double mCurrentLng;
    /**
     * 当前定位位置信息
     */
    private Map<String, String> currentInfo = new HashMap<>();
    /**
     * poi搜索成功代码
     */
    private static final int CODE_SEARCH_POI_SUCCESS = 1000;

    private GeocodeSearch geocoderSearch;
    /**
     * 最终选择的点
     */
    private LatLng mFinalChoosePosition;

    private boolean isHandDrag = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_gaode);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map_view_gaode);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        initRecycleView();

        initMap();
    }

    private void initRecycleView() {
        mRecyclerView = findViewById(R.id.rclv_poi);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPoiGaodeAdapter = new PoiGaodeAdapter(mPoiAddressBeanList);
        mPoiGaodeAdapter.bindToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mPoiGaodeAdapter);
        //点击事件
        mPoiGaodeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPoiGaodeAdapter.setSelectedItem(position);
                isHandDrag = false;
                PoiAddressBean item = (PoiAddressBean) adapter.getItem(position);
                //在地图上添加一个marker，并将地图中移动至此处
                LatLng ll = new LatLng(item.getLatLonPoint().getLatitude(), item.getLatLonPoint().getLongitude());
                CameraUpdate cu = CameraUpdateFactory.newLatLng(ll);
                mAMap.animateCamera(cu);
            }
        });

        mPoiGaodeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCurrentPageIndex++;
                        searchPoi("", mCurrentPageIndex, currentInfo.get("cityCode"), true);
                    }

                }, 1000);
            }
        }, mRecyclerView);

    }

    private void initMap() {
        if (mAMap == null) {
            mAMap = mMapView.getMap();
        }
        // 设置定位监听
        mAMap.setLocationSource(new LocationSourceListener());
        // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
        mAMap.setMaxZoomLevel(mAMap.getMaxZoomLevel());
        //地图加载监听器
        mAMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
            @Override
            public void onMapLoaded() {
                // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
                mAMap.setMyLocationEnabled(true);
                mAMap.moveCamera(CameraUpdateFactory.zoomTo(16f));
            }
        });

        mAMap.setOnCameraChangeListener(new CameraChangeListener());

        //地址监听事件
        mAMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                //存储定位数据
                mCurrentLat = location.getLatitude();
                mCurrentLng = location.getLongitude();
                String[] args = location.toString().split("#");
                for (String arg : args) {
                    String[] data = arg.split("=");
                    if (data.length >= 2) {
                        currentInfo.put(data[0], data[1]);
                    }
                }
                mPoiGaodeAdapter.addData(0, new PoiAddressBean(new LatLonPoint(mCurrentLat, mCurrentLng), currentInfo.get("aoiName"), currentInfo.get("address")));
                mPoiGaodeAdapter.setSelectedItem(0);
                isFirstLoad = false;
                //搜索poi
                searchPoi("", 0, currentInfo.get("cityCode"), true);
            }
        });

        //添加中心标记
        MarkerOptions mMarkerOptions = new MarkerOptions();
        //可拖放性
        mMarkerOptions.draggable(false);
        mMarkerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.lock_center));
        final Marker mCenterMarker = mAMap.addMarker(mMarkerOptions);
        ViewTreeObserver vto = mMapView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mMapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mCenterMarker.setPositionByPixels(mMapView.getWidth() >> 1, mMapView.getHeight() >> 1);
                mCenterMarker.showInfoWindow();
            }
        });

        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearchListener());
        initMyLocation();
    }

    private void initMyLocation() {
        MyLocationStyle myLocationStyle;
        //初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle = new MyLocationStyle();
        //设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息。
        myLocationStyle.showMyLocation(true);
        myLocationStyle.radiusFillColor(0x70f3ff);
        myLocationStyle.strokeColor(0xe3f9fd);
        //连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(2000);
        //设置定位蓝点的Style
        mAMap.setMyLocationStyle(myLocationStyle);
        //设置默认定位按钮是否显示，非必需设置。
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        mAMap.setMyLocationEnabled(true);
    }


    /**
     * 搜索poi
     *
     * @param key      关键字
     * @param pageNum  页码
     * @param cityCode 城市代码，或者城市名称
     * @param nearby   是否搜索周边
     */
    void searchPoi(String key, int pageNum, String cityCode, boolean nearby) {
        Log.e(TAG, key);
        isPoiSearched = true;
        query = new PoiSearch.Query(key, "", cityCode);
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，
        //POI搜索类型共分为以下20种：汽车服务|汽车销售|
        //汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|
        //住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|
        //金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        // 设置每页最多返回多少条poiitem
        query.setPageSize(PAGE_SIZE);
        //设置查询页码
        query.setPageNum(pageNum);
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(new PoiSearchListener());
        if (nearby) {
            //设置周边搜索的中心点以及半径
            poiSearch.setBound(new PoiSearch.SearchBound(convertToLatLonPoint(mFinalChoosePosition), 1500));
        }
        poiSearch.searchPOIAsyn();
    }

    private class LocationSourceListener implements LocationSource {
        @Override
        public void activate(OnLocationChangedListener listener) {
            mListener = listener;
            if (mlocationClient == null) {
                //初始化定位
                mlocationClient = new AMapLocationClient(GaodeMapActivity.this);
                //初始化定位参数
                mLocationOption = new AMapLocationClientOption();
                //设置定位回调监听
                mlocationClient.setLocationListener(new MapLocationListener());
                //设置为高精度定位模式
                mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                //设置定位参数
                mlocationClient.setLocationOption(mLocationOption);
                // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
                // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
                // 在定位结束后，在合适的生命周期调用onDestroy()方法
                // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
                mlocationClient.startLocation();//启动定位
            }
        }

        @Override
        public void deactivate() {
            mListener = null;
            if (mlocationClient != null) {
                mlocationClient.stopLocation();
                mlocationClient.onDestroy();
            }
            mlocationClient = null;
        }
    }

    private class MapLocationListener implements com.amap.api.location.AMapLocationListener {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (mListener != null && amapLocation != null) {
                if (amapLocation != null
                        && amapLocation.getErrorCode() == 0) {
                    mListener.onLocationChanged(amapLocation);
                } else {
                    String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                    Log.e("AmapErr", errText);
                }
            }
        }
    }

    /**
     * 周边poi搜索监听
     */
    private class PoiSearchListener implements PoiSearch.OnPoiSearchListener {
        @Override
        public void onPoiSearched(PoiResult poiResult, int resultCode) {
            if (resultCode == CODE_SEARCH_POI_SUCCESS) {
                if (poiResult != null && poiResult.getQuery() != null) {
                    //填充数据，并更新recycleview
                    ArrayList<PoiItem> result = poiResult.getPois();
                    if (result.size() > 0) {
                        List<PoiAddressBean> mPoiAddressBeanList = new ArrayList<>();
                        PoiAddressBean mPoiAddress;
                        for (PoiItem poiItem : result) {
                            mPoiAddress = new PoiAddressBean();
                            mPoiAddress.setLatLonPoint(poiItem.getLatLonPoint());
                            mPoiAddress.setText(poiItem.getTitle());
                            mPoiAddress.setDetailAddress(poiItem.getSnippet());
                            mPoiAddressBeanList.add(mPoiAddress);
                        }
                        mPoiGaodeAdapter.addData(mPoiAddressBeanList);
                        mPoiGaodeAdapter.loadMoreComplete();
                    } else {
                        mPoiGaodeAdapter.loadMoreEnd();
                    }
                }
            }
        }

        @Override
        public void onPoiItemSearched(PoiItem item, int i) {

        }
    }

    /**
     * 拖动地图监听器
     */
    private class CameraChangeListener implements AMap.OnCameraChangeListener {

        @Override
        public void onCameraChange(CameraPosition position) {

        }

        @Override
        public void onCameraChangeFinish(CameraPosition position) {
            Log.d(TAG, "拖动地图 ");
            isHandDrag = true;
            mFinalChoosePosition = position.target;
            if (isHandDrag) {
                getAddress(mFinalChoosePosition);
            }
        }
    }

    private class GeocodeSearchListener implements GeocodeSearch.OnGeocodeSearchListener {

        @Override
        public void onRegeocodeSearched(RegeocodeResult result, int resultCode) {
            if (resultCode == CODE_SEARCH_POI_SUCCESS) {
                if (result != null && result.getRegeocodeAddress() != null
                        && result.getRegeocodeAddress().getFormatAddress() != null) {
                    // 逆转地里编码不是每次都可以得到对应地图上的opi
                    if (isHandDrag) {
                        String addressName = result.getRegeocodeAddress().getFormatAddress();
                        String cityCode = result.getRegeocodeAddress().getCityCode();
                        Log.d(TAG, "逆地理编码回调  得到的地址：" + addressName);
                        mPoiGaodeAdapter.setNewData(null);
                        mPoiGaodeAdapter.addData(0, new PoiAddressBean(convertToLatLonPoint(mFinalChoosePosition), "位置", addressName));
                        searchPoi("", 0, cityCode, true);
                    }
                }
            }
        }

        @Override
        public void onGeocodeSearched(GeocodeResult result, int i) {

        }
    }

    /**
     * 根据经纬度得到地址
     */
    public void getAddress(final LatLng latLonPoint) {
        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        RegeocodeQuery query = new RegeocodeQuery(convertToLatLonPoint(latLonPoint), 1500, GeocodeSearch.AMAP);
        // 设置同步逆地理编码请求
        geocoderSearch.getFromLocationAsyn(query);
    }

    /**
     * 把LatLonPoint对象转化为LatLon对象
     */
    public LatLng convertToLatLng(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    /**
     * 把LatLng对象转化为LatLonPoint对象
     */
    public static LatLonPoint convertToLatLonPoint(LatLng latlon) {
        return new LatLonPoint(latlon.latitude, latlon.longitude);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
