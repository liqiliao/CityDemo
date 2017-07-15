package vpn.tangde.citydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner provinceSpinner = null;  //省级（省、直辖市）
    private Spinner citySpinner = null;     //地级市
    private Spinner countySpinner = null;    //县级（区、县、县级市）
    ArrayAdapter<String> provinceAdapter = null;  //省级适配器
    ArrayAdapter<String> cityAdapter = null;    //地级适配器
    ArrayAdapter<String> countyAdapter = null;    //县级适配器
    static int provincePosition = 3;
    private Button btn_sendRequst;


    //省级选项值
    private String[] province = new String[]{"","高质量IP", "北京直辖市", "广东省", "上海直辖市", "浙江省", "河南省", "山东省", "江苏省", "湖北省", "河南省"
            , "辽宁省", "江西省", "安徽省", "四川省", "湖南省", "云南省", "福建省", "吉林省", "山西省"
            , "重庆直辖市", "黑龙江省", "内蒙古自治区", "青海省", "河北省"};

    //地级选项值
    private String[][] city = new String[][]
            {
                    {""},

                    {""},

                    {""},

                    {"", "广州市", "深圳市", "揭阳市", "江门市", "惠州市", "东莞市", "珠海市", "梅州市", "中山市", "河源市"
                            , "佛山市", "湛江市"},
                    {""},

                    {"", "温州市", "嘉兴市", "杭州市", "金华市", "衢州市", "宁波市", "湖州市", "舟山市", "丽水市"},

                    {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""},

                    {""}, {""}, {""}, {""}
            };

    //县级选项值
    private String[][][] county = new String[][][]
            {
                    {   //北京
                            {""}, {"无"}, {"无"}, {"无"}, {"无"}, {"无"}, {"无"}, {"无"}, {"无"}, {"无"},
                            {"无"}, {"无"}, {"无"}, {"无"}, {"无"}, {"无"}, {"无"}, {"无"}
                    },
                    {    //上海
                            {""}, {"无"}, {"无"}, {"无"}, {"无"}
                    },
                    {    //天津
                            {""}, {"无"}, {"无"}, {"无"}, {"无"}, {"无"}, {"无"}, {"无"}, {"无"}, {"无"}
                    },
                    {    //广东
                            {"海珠区", "荔湾区", "越秀区", "白云区", "萝岗区", "天河区", "黄埔区", "花都区", "从化市", "增城市", "番禺区", "南沙区"}, //广州
                            {"宝安区", "福田区", "龙岗区", "罗湖区", "南山区", "盐田区"}, //深圳
                            {"武江区", "浈江区", "曲江区", "乐昌市", "南雄市", "始兴县", "仁化县", "翁源县", "新丰县", "乳源县"}  //韶关
                    }
            };
    private TextView tv_radom;
    private Spinner spin_province;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setSpinner();

    }

    private void setSelectAdress() {

        String province = provinceSpinner.getSelectedItem().toString();

        String city = citySpinner.getSelectedItem().toString();

        if (!province.isEmpty() && province != null) {
            Toast.makeText(this, province, Toast.LENGTH_SHORT).show();
        }
        if (!city.isEmpty() && city != null) {
            Toast.makeText(this, city, Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
//        tv_radom = (TextView) findViewById(R.id.tv_radom);
//        spin_province = (Spinner) findViewById(R.id.spin_province);
//        tv_radom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tv_radom.setVisibility(View.INVISIBLE);
//                spin_province.setVisibility(View.VISIBLE);
//
//            }
//        });

        btn_sendRequst = (Button) findViewById(R.id.btn_sendRequst);
        btn_sendRequst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectAdress();
            }
        });
    }


    /*
     * 设置下拉框
     */
    private void setSpinner() {
        provinceSpinner = (Spinner) findViewById(R.id.spin_province);
        citySpinner = (Spinner) findViewById(R.id.spin_city);
//            countySpinner = (Spinner)findViewById(R.id.spin_county);

        //绑定适配器和值
        provinceAdapter = new ArrayAdapter<>(MainActivity.this,
                R.layout.myspinner, province);
        provinceSpinner.setAdapter(provinceAdapter);
        provinceSpinner.setSelection(0, true);  //设置默认选中项，此处为默认选中第4个值

        cityAdapter = new ArrayAdapter<>(MainActivity.this,
                R.layout.myspinner_city, city[0]);
        citySpinner.setAdapter(cityAdapter);
        citySpinner.setSelection(0, true);  //默认选中第0个

//          countyAdapter = new ArrayAdapter<String>(MainActivity.this,
//          android.R.layout.simple_spinner_item, county[3][0]);
//          countySpinner.setAdapter(countyAdapter);
//          countySpinner.setSelection(0, true);


        //省级下拉框监听
        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            // 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //position为当前省级选中的值的序号

                //将地级适配器的值改变为city[position]中的值
                cityAdapter = new ArrayAdapter<>(
                        MainActivity.this, R.layout.myspinner_city, city[position]);
                // 设置二级下拉列表的选项内容适配器
                citySpinner.setAdapter(cityAdapter);
                provincePosition = position;    //记录当前省级序号，留给下面修改县级适配器时用
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });


//            //地级下拉监听
//            citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//            {
//
//                @Override
//                public void onItemSelected(AdapterView<?> arg0, View arg1,
//                                           int position, long arg3)
//                {
//                    countyAdapter = new ArrayAdapter<String>(MainActivity.this,
//                            android.R.layout.simple_spinner_item, county[provincePosition][position]);
//                    countySpinner.setAdapter(countyAdapter);
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> arg0)
//                {
//
//                }
//            });
    }
}
