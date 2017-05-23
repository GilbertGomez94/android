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
    // variable del grafico
    private LineChart lineChart;
    // setcomp1 contendra el conjunto de datos de la calorias quemadas
    private LineDataSet setComp1;
    // setcomp2 contendra el conjunto de datos de la calorias consumidas
    private LineDataSet setComp2;
    // setcomp3 contendra el conjunto de datos de la diferencia de las calorias quemadas y consumidas
    private LineDataSet setComp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m11_historial);

        try {

            // obtengo el grafico del archivo xml
            lineChart = (LineChart) findViewById(R.id.chart);
            // la documentacion dice que hay que poner esto.. xd
            Utils.init(getResources());
            // declaro las variables donde se guardaran los datos de entrada
            // es decir la cantidad de calorias.
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

            // creo el dataset de caloria quemadas y vinculo una etiqueta
            // al conjunto de datos ademas asignarle color y otra propiedad
            setComp1 = new LineDataSet(calQuemadas, "Quemadas");
            setComp1.setColor(Color.BLUE);
            setComp1.setAxisDependency(AxisDependency.LEFT);
            // creo el dataset de caloria consumidas y vinculo una etiqueta
            // al conjunto de datos ademas asignarle color y otra propiedad
            setComp2 = new LineDataSet(calConsumidas, "Consumidas");
            setComp2.setColor(Color.YELLOW);
            setComp2.setAxisDependency(AxisDependency.LEFT);
            // creo el dataset de caloria diferencial y vinculo una etiqueta
            // al conjunto de datos ademas asignarle color y otra propiedad
            setComp3 = new LineDataSet(calDiferencial, "Diferencial");
            setComp3.setColor(Color.GREEN);
            setComp3.setAxisDependency(AxisDependency.LEFT);

            // los LineDataSets anteriores(setComp1, setComp2 y setComp3)
            // los introduzco en una sola estructura de datos
            List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(setComp1);
            dataSets.add(setComp2);
            dataSets.add(setComp3);
            // creo el objeto LineData que es lo que se le pasa a la vista para que
            // se visualice la grafica
            LineData data = new LineData(dataSets);
            // seteo el grafico en la vista
            lineChart.setData(data);
            // esto son propiedades de la grafica
            YAxis yAxis = lineChart.getAxisLeft();
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
            // esto refresca e inicializa la grafica
            lineChart.invalidate(); // refresh

        }
        catch (Exception ex) {
            Log.d(TAG, "error al intentar generar la grafica", ex);

        }

    }

    /*
    funcion que muestra o elimina la linea que indica las calorias
    consumidas de la grafica. El metodo es llamado con la propiedad
    onClick en el archivo xml
    */
    public void activarDesactivarConsumidas(View view) {
        // obtengo el checkbox que indica si desea ver o no
        // la linea de calorias consumidas
        CheckBox cbConsumidas = (CheckBox) findViewById(R.id.cb_consumidas);
        // pregunto si el check no esta seleccionado
        if (!cbConsumidas.isChecked()) {
            // obtengo la data del grafico
            LineData lineData = lineChart.getData();
            //ILineDataSet set = lineData.getDataSetByIndex(0);

            // obtengo los datos pertenecientes a la linea de consumidas
            ILineDataSet consumidas = lineData.getDataSetByLabel("Consumidas",false);
            // remuevo los datos
            lineData.removeDataSet(consumidas);
            // se actualiza tanto el grafico como la variable que contiene los datos
            lineData.notifyDataChanged();
            lineChart.notifyDataSetChanged();
            // refresco el grafico para que detecte los cambios
            lineChart.invalidate();
        }
        // si el check esta seleccionado
        else {
            // obtengo los datos de la grafica
            LineData lineData = lineChart.getData();
            // agrego los datos pertenecientes a calorias consumidas
            lineData.addDataSet(setComp2);
            // notifico los cambios
            lineData.notifyDataChanged();
            lineChart.notifyDataSetChanged();
            // refresco el grafico para que agarre los cambios
            lineChart.invalidate();
        }
    }

    /*
    funcion que muestra o elimina la linea que indica las calorias
    quemadas de la grafica. El metodo es llamado con la propiedad
    onClick en el archivo xml
    */
    public void activarDesactivarQuemadas(View view) {
        // obtengo el checkbox que indica si desea ver o no
        // la linea de calorias quemadas
        CheckBox cbQuemadas = (CheckBox) findViewById(R.id.cb_quemadas);
        // pregunto si el check no esta seleccionado
        if (!cbQuemadas.isChecked()){
            // obtengo la data del grafico
            LineData lineData = lineChart.getData();
            //ILineDataSet set = lineData.getDataSetByIndex(0);

            // obtengo los datos pertenecientes a la linea de quemadas
            ILineDataSet quemadas = lineData.getDataSetByLabel("Quemadas",false);
            // remuevo los datos
            lineData.removeDataSet(quemadas);
             // se actualiza tanto el grafico como la variable que contiene los datos
            lineData.notifyDataChanged();
            lineChart.notifyDataSetChanged();
            // refresco el grafico para que detecte los cambios
            lineChart.invalidate();
        }
        // si el check esta seleccionado
        else {
            // obtengo los datos de la grafica
            LineData lineData = lineChart.getData();
            // agrego los datos pertenecientes a calorias quemadas
            lineData.addDataSet(setComp1);
            // notifico los cambios
            lineData.notifyDataChanged();
            lineChart.notifyDataSetChanged();
             // refresco el grafico para que agarre los cambios
            lineChart.invalidate();
        }
    }

    /*
    esta funcion ya no va.
    igual que su correspodiente boton del xml
    */
    public void verGraficaAno(View view){
        lineChart.clear();
    }

    /*
    funcion que refrescara la grafica para poder ver los
    ultimos 12 meses de calorias consumidas, quemadas y su diferencia.
    OJO FUNCION NO COMPLETADA. SOLO BORRA LOS DATOS POR AHORA
    */
    public void verGraficoMes(View view){
        lineChart.clear();
    }

    /*
    funcion que refrescara la grafica para poder ver las
    ultimas 4 semanas de calorias consumidas, quemadas y su diferencia.
    OJO FUNCION NO COMPLETADA. SOLO BORRA LOS DATOS POR AHORA
    */
    public void verGraficoSemana(View view){
        lineChart.clear();
    }

     /*
    funcion que refrescara la grafica para poder ver los
    ultimos 7 dias de calorias consumidas, quemadas y su diferencia.
    OJO FUNCION NO COMPLETADA. SOLO BORRA LOS DATOS POR AHORA
    */
    public void verGraficoDia(View view){
        lineChart.clear();
    }


}
