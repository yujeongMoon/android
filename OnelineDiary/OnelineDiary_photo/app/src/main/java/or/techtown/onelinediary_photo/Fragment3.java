package or.techtown.onelinediary_photo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

public class Fragment3 extends Fragment {

    PieChart chart;
    BarChart chart2;
    LineChart chart3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_3, container, false);

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {

        /* pie chart */
        chart = rootView.findViewById(R.id.chart1);

        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);

        // 그래프 중심 글씨 설정
        chart.setDrawCenterText(true);
        chart.setCenterText("기분별 비율");

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);


        // 그래프를 클릭했을 때 부각되도록 크기를 키워줌
        chart.setHighlightPerTapEnabled(true);

        // 범례 설정
        Legend legend1 = chart.getLegend();
        // true로 바꾸면 범례가 생김
        legend1.setEnabled(false);

        // 그래프 내용 추가할 때의 라벨의 색과 사이즈
        chart.setEntryLabelColor(Color.WHITE);
        chart.setEntryLabelTextSize(30f);

        setDate1();


        /* Bar Chart */
        chart2 = rootView.findViewById(R.id.chart2);

        chart2.setDrawValueAboveBar(true);

        chart2.getDescription().setEnabled(false);
        chart2.setDrawGridBackground(false);

        XAxis xAxis = chart2.getXAxis();
        xAxis.setEnabled(false);

        YAxis leftAxis = chart2.getAxisLeft();
        leftAxis.setLabelCount(6, false);
        leftAxis.setAxisMinimum(0.0f);

        // Granularity : 세분성
        leftAxis.setGranularityEnabled(true);
        leftAxis.setGranularity(1f);

        YAxis rightAxis = chart2.getAxisRight();
        rightAxis.setEnabled(false);

        // 범례 설정
        Legend legend2 = chart2.getLegend();
        legend2.setEnabled(false);

        chart2.animateXY(1500, 1500);

        setDate2();

        /* Line Chart */
        chart3 = rootView.findViewById(R.id.chart3);

        chart3.getDescription().setEnabled(false);
        chart3.setDrawGridBackground(false);

        chart3.setBackgroundColor(Color.WHITE);
        chart3.setViewPortOffsets(0, 0, 0, 0);

        // 범례 설정
        Legend legend3 = chart3.getLegend();
        legend3.setEnabled(false);

        XAxis xAxis3 = chart3.getXAxis();
        xAxis3.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis3.setTextSize(10f);
        xAxis3.setTextColor(Color.WHITE);
        xAxis3.setDrawAxisLine(false);
        xAxis3.setDrawGridLines(true);
        xAxis3.setTextColor(Color.rgb(255, 192, 56));
        xAxis3.setCenterAxisLabels(true);
        xAxis3.setGranularity(1f);

        YAxis leftAxis3 = chart3.getAxisLeft();
        leftAxis3.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        leftAxis3.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis3.setDrawGridLines(true);
        leftAxis3.setGranularityEnabled(true);
        leftAxis3.setAxisMinimum(0f);
        leftAxis3.setAxisMaximum(170f);
        leftAxis3.setYOffset(-9f);
        leftAxis3.setTextColor(Color.rgb(255, 192, 56));

        YAxis rightAxis3 = chart3.getAxisRight();
        rightAxis3.setEnabled(false);

        setDate3();
    }

    private void setDate3() {
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(24f, 20.0f));
        values.add(new Entry(48f, 50.0f));
        values.add(new Entry(72f, 30.0f));
        values.add(new Entry(96f, 70.0f));
        values.add(new Entry(120f, 90.0f));

        LineDataSet set1 = new LineDataSet(values, "DataSet 1");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        set1.setColor(ColorTemplate.getHoloBlue());

        set1.setValueTextColor(ColorTemplate.getHoloBlue());
        set1.setLineWidth(1.5f);

        // true로 바꾸면 값에 점이 생김
        set1.setDrawCircles(false);

        set1.setDrawValues(false);
        set1.setFillAlpha(65);

        set1.setFillColor(ColorTemplate.getHoloBlue());

        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(false);

        LineData data = new LineData(set1);
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(9f);

        chart3.setData(data);
        chart3.invalidate();
    }

    private void setDate2() {
        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(1.0f, 20.0f,
                getResources().getDrawable(R.drawable.smile1_24)));
        entries.add(new BarEntry(2.0f, 40.0f,
                getResources().getDrawable(R.drawable.smile2_24)));
        entries.add(new BarEntry(3.0f, 60.0f,
                getResources().getDrawable(R.drawable.smile3_24)));
        entries.add(new BarEntry(4.0f, 30.0f,
                getResources().getDrawable(R.drawable.smile4_24)));
        entries.add(new BarEntry(5.0f, 90.0f,
                getResources().getDrawable(R.drawable.smile5_24)));

        BarDataSet dataSet2 = new BarDataSet(entries, "Sinus Function");
        dataSet2.setColor(Color.rgb(240, 120, 124));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.JOYFUL_COLORS) {
            colors.add(c);
        }
        dataSet2.setColors(colors);

        // 아이콘 위치
        dataSet2.setIconsOffset(new MPPointF(0, -10));

        BarData data = new BarData(dataSet2);

        // 그래프 수치 글씨 설정
        // true로 바꾸면 수치가 나타남
        data.setDrawValues(false);

        // 그래프 수치 글씨 크기
        data.setValueTextSize(10f);
        data.setBarWidth(0.8f);

        // 차트에 데이터 설정
        chart2.setData(data);

        // 화면 갱신
        chart2.invalidate();
    }

    private void setDate1() {
        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(20.0f, "", getResources().getDrawable(R.drawable.smile1_24)));
        entries.add(new PieEntry(20.0f, "", getResources().getDrawable(R.drawable.smile2_24)));
        entries.add(new PieEntry(20.0f, "", getResources().getDrawable(R.drawable.smile3_24)));
        entries.add(new PieEntry(20.0f, "", getResources().getDrawable(R.drawable.smile4_24)));
        entries.add(new PieEntry(20.0f, "", getResources().getDrawable(R.drawable.smile5_24)));

        // 범례 지정
        PieDataSet dataSet = new PieDataSet(entries, "기분별 비율");

        dataSet.setDrawIcons(true);

        // 그래프간의 간격 설정
        dataSet.setSliceSpace(3f);

        // 아이콘의 위치
        dataSet.setIconsOffset(new MPPointF(0, -40));

        dataSet.setSelectionShift(5f);


        // 랜덤으로 색깔 지정하기
        ArrayList<Integer> colors = new ArrayList<>();
        for(int c : ColorTemplate.JOYFUL_COLORS){
            colors.add(c);
        }
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);

        // 그래프 수치 글씨 크기
        data.setValueTextSize(22.0f);

        // 그래프 수치 글씨 색상
        data.setValueTextColor(Color.WHITE);

        // 차트에 데이터 설정
        chart.setData(data);
        // 화면 갱신
        chart.invalidate();
    }
}