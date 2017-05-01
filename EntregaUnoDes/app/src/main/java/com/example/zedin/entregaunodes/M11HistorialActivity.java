package com.example.zedin.entregaunodes;


import android.os.Bundle;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;


import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class M11HistorialActivity extends AppCompatActivity  {

    private static final String TAG = M11HistorialActivity.class.getSimpleName();
    private LineChart lineChart;
    private LineDataSet setComp1;
    private LineDataSet setComp2;
    private LineDataSet setComp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m11_historial);

        try {


            lineChart = (LineChart) findViewById(R.id.chart);
            Utils.init(getResources());

            List<Entry> calQuemadas = new ArrayList<Entry>();
            
            List<Entry> calConsumidas = new ArrayList<Entry>();
            List<Entry> calDiferencial = new ArrayList<Entry>();

            // datos de las calorias quemadas
            Entry c1e1 = new Entry(0f, 100000f);
            calQuemadas.add(c1e1);
            Entry c1e2 = new Entry(1f, 140000f);
            calQuemadas.add(c1e2);
            Entry c1e3 = new Entry(2f, 120000f);
            calQuemadas.add(c1e3);
            Entry c1e4 = new Entry(3f, 140000f);
            calQuemadas.add(c1e4);


            // datos de la calorias consumidas
            Entry c2e1 = new Entry(0f, 130000f);
            calConsumidas.add(c2e1);
            Entry c2e2 = new Entry(1f, 115000f);
            calConsumidas.add(c2e2);
            Entry c2e3 = new Entry(2f, 90000f);
            calConsumidas.add(c2e3);
            Entry c2e4 = new Entry(3f, 105000f);
            calConsumidas.add(c2e4);



            // datos de las calorias consumidas - quemadas
            Entry c3e1 = new Entry(0f, 30000f);
            calDiferencial.add(c3e1);
            Entry c3e2 = new Entry(1f, -25000f);
            calDiferencial.add(c3e2);
            Entry c3e3 = new Entry(2f, -30000f);
            calDiferencial.add(c3e3);
            Entry c3e4 = new Entry(3f, -35000f);
            calDiferencial.add(c3e4);

            // creo el dataset de caloria quemadas
            setComp1 = new LineDataSet(calQuemadas, "Quemadas");
            setComp1.setColor(Color.BLUE);
            setComp1.setAxisDependency(AxisDependency.LEFT);
            // creo el dataset de caloria consumidas
            setComp2 = new LineDataSet(calConsumidas, "Consumidas");
            setComp2.setColor(Color.YELLOW);
            setComp2.setAxisDependency(AxisDependency.LEFT);
            // creo el dataset de caloria diferencial
            setComp3 = new LineDataSet(calDiferencial, "Diferencial");
            setComp3.setColor(Color.GREEN);
            setComp3.setAxisDependency(AxisDependency.LEFT);

            List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(setComp1);
            dataSets.add(setComp2);
            dataSets.add(setComp3);
            LineData data = new LineData(dataSets);
            lineChart.setData(data);
            YAxis yAxis = lineChart.getAxisLeft();
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
            lineChart.invalidate(); // refresh

        }
        catch (Exception ex) {
            Log.d(TAG, "error al intentar generar la grafica", ex);

        }

    }

    public void activarDesactivarConsumidas(View view) {
        CheckBox cbConsumidas = (CheckBox) findViewById(R.id.cb_consumidas);
        if (!cbConsumidas.isChecked()) {
            LineData lineData = lineChart.getData();
            //ILineDataSet set = lineData.getDataSetByIndex(0);
            ILineDataSet consumidas = lineData.getDataSetByLabel("Consumidas",false);
            lineData.removeDataSet(consumidas);
            lineData.notifyDataChanged();
            lineChart.notifyDataSetChanged();
            lineChart.invalidate();
        }

        else {
            LineData lineData = lineChart.getData();

            lineData.addDataSet(setComp2);
            lineData.notifyDataChanged();
            lineChart.notifyDataSetChanged();
            lineChart.invalidate();
        }
    }

    public void activarDesactivarQuemadas(View view) {
        CheckBox cbQuemadas = (CheckBox) findViewById(R.id.cb_quemadas);
        if (!cbQuemadas.isChecked()){
            LineData lineData = lineChart.getData();
            //ILineDataSet set = lineData.getDataSetByIndex(0);
            ILineDataSet quemadas = lineData.getDataSetByLabel("Quemadas",false);
            lineData.removeDataSet(quemadas);
            lineData.notifyDataChanged();
            lineChart.notifyDataSetChanged();
            lineChart.invalidate();
        }
        else {
            LineData lineData = lineChart.getData();

            lineData.addDataSet(setComp1);
            lineData.notifyDataChanged();
            lineChart.notifyDataSetChanged();
            lineChart.invalidate();
        }
    }


}
