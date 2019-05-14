package com.example.indicemasacorporalcasa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // saveInstanceState ...es como si fuera un saquito para guardar cosas

        if (savedInstanceState==null){
            Log.i("MIAPP","Estoy en onCreate la primera vez");

        }else {
            Log.i("MIAPP","Estoy en onCreate con cosas guardadas");
            boolean valor_guardado = savedInstanceState.getBoolean("CARGADA");
            Log.i("MIAPP","Estoy en onCreate: CARGADA = "+ valor_guardado);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // para coger valores String de forma dinamica
        //  IMPORTANTE DE SABER
        String valor = getResources().getString(R.string.imc_tipo);

        Log.i("MIAPP","Estoy en onCrete");
        // DEFINO NUMBERPICKER - PARA PESO Y ALTURA
        NumberPicker numberPicker = findViewById(R.id.editPeso);
        NumberPicker numberPicker1 = findViewById(R.id.editAltura);
        //      DEFINO RANGO DE VALORES
        numberPicker.setMinValue(40);
        numberPicker.setMaxValue(200);
        numberPicker1.setMinValue(140);
        numberPicker1.setMaxValue(220);

        //      DEFINO VALORES POR DEFECTO
                // numberPicker.setValue(72);  TextView tiene en text = 72
        TextView pesoelegido = findViewById(R.id.txtpesoelegido);
        numberPicker.setValue(Integer.parseInt(pesoelegido.getText().toString()));
                // numberPicker1.setValue(182); TextView tiene en text = 182
        TextView alturaelegida = findViewById(R.id.txtalturaelegida);
        numberPicker1.setValue(Integer.parseInt(alturaelegida.getText().toString()));
        //      EJECUTO LOS DOS NUMBERPICKERS
        numberPicker.setOnValueChangedListener(onValueChangeListener);
        numberPicker1.setOnValueChangedListener(onValueChangeListener1);


        // ******************* para leer por programacion un string
                            //String pesoprevio = getString(R.string.pesoprevio);

        // DEFINIR RADIOGROUP CON DOS POSIBLES OPCIONES - HOMBRE , MUJER-

        final RadioGroup radioGroup =  findViewById(R.id.radioGroup);
        final RadioButton hombre = findViewById(R.id.radioButton2);
        RadioButton mujer = findViewById(R.id.radioButton);

              // RESEO INICIAL DE LOS BOTONES
        radioGroup.clearCheck();
        radioGroup.check(hombre.getId());
              // AÑADIR EL LISTENER AL RADIOGROUP
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                      // Si alguno de los dos botones es seleccionado llega aqui

                    // COMPROBAR CUAL DE LOS BOTONES HA SIDO SELECCIONADO
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        RadioButton radioButton = radioGroup.findViewById(i);
                    }
                }
        );

        hombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               simularcalcularIMC();
            }
        });
        mujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            simularcalcularIMC();
            }
        });

        // voy a coger mi imagen
        ImageView imageView =findViewById(R.id.imageView);
        // ahora asignar la imagen
        imageView.setImageResource(R.mipmap.ic_launcher);
        // para ejecutar la simulacion del boton calcular IMC

        // PARA QUE SOLO SE EJECUTE LA PRIMERA VEZ EL METODO SIMULARCALCULARIMC
        if (savedInstanceState == null){
            simularcalcularIMC();
        }
        /**
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = preferences.getBoolean(getString(R.string.alturaprevia),false);
        if (!previouslyStarted){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(getString(R.string.alturaprevia),Boolean.TRUE);
            editor.commit();
            simularcalcularIMC();
        }
        */




    }



NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() {
    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
      //  Toast.makeText(MainActivity.this, "Peso elegido" + numberPicker.getValue(), Toast.LENGTH_SHORT).show();
        // Guarda el valor en elegido en text view
        TextView pesoelegido = findViewById(R.id.txtpesoelegido);
        int valor = numberPicker.getValue();
        pesoelegido.setText(""+valor);
        simularcalcularIMC();
    }
};

NumberPicker.OnValueChangeListener onValueChangeListener1 = new NumberPicker.OnValueChangeListener() {
    @Override
    public void onValueChange(NumberPicker numberPicker1, int oldVal, int newVal) {
        TextView alturaelegida = findViewById(R.id.txtalturaelegida);
        int altura = numberPicker1.getValue();
        alturaelegida.setText(""+altura);
        simularcalcularIMC();
    }
};

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MIAPP","Estoy en onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MIAPP","Estoy en onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MIAPP","Estoy en onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MIAPP","Estoy en onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MIAPP","Estoy en onDestroy");
    }

    public static double calcularIMC1 (Float peso, Float altura)
    {

        // IMC se calcula con Kg/Metros
        // como altura llega en Centimetros tengo que convertilo
        double alturametros = altura/100;
     double imc = 0;

     imc = peso/(alturametros*alturametros);

     return imc;

    }



    // PARA GUARDAR INFORMACION DE TEXTVIEW DE UNA PANTALLA NORMAL A LANDSCATE Y VICEVERSA
    //            PRIMERA PARTE DE GUARDAR LO QUE QUEREMOS LUEGO MOSTRAR ACTUALIZADO
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("CARGADA",true);  // para indicar

    //    int valor_peso = this.numberPicker.getValue();
    //    int valor_altura = this.numberPicker1.getValue();

        TextView pesoelegido = findViewById(R.id.txtpesoelegido);
        TextView alturaelegida = findViewById(R.id.txtalturaelegida);
        outState.putString("pesoprevio",pesoelegido.getText().toString());
        outState.putString("alturaprevia",alturaelegida.getText().toString());
        Log.i("MIAPP","Estoy en onSaveInstanceState");
    }

    //             SEGUNDA PARTE DE RECUPERAR LOS VALORES EN LA NUEVA VISTA

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("MIAPP","Estoy en onRestoreInstanceState");
        TextView pesoelegido = findViewById(R.id.txtpesoelegido);
        TextView alturaelegida = findViewById(R.id.txtalturaelegida);
        pesoelegido.setText(savedInstanceState.getString("pesoprevio"));
        alturaelegida.setText(savedInstanceState.getString("alturaprevia"));

        NumberPicker numberPicker = findViewById(R.id.editPeso);
        numberPicker.setValue(Integer.parseInt(pesoelegido.getText().toString()));
        NumberPicker numberPicker1 = findViewById(R.id.editAltura);
        numberPicker1.setValue(Integer.parseInt(alturaelegida.getText().toString()));
        simularcalcularIMC();

    }

    public void borrar (View view){
        // AL PULSAR ESTE BOTON RESTAURAMOS LOS VALORES INICIALES DE PESO Y ALTURA
        //             VALORES INICIALES 72 KG Y 182 CMS
        TextView pesoelegido = findViewById(R.id.txtpesoelegido);
        TextView alturaelegida = findViewById(R.id.txtalturaelegida);
        pesoelegido.setText("72");
        alturaelegida.setText("182");
        NumberPicker numberPicker = findViewById(R.id.editPeso);
        numberPicker.setValue(Integer.parseInt(pesoelegido.getText().toString()));
        NumberPicker numberPicker1 = findViewById(R.id.editAltura);
        numberPicker1.setValue(Integer.parseInt(alturaelegida.getText().toString()));
        simularcalcularIMC();

    }

public void calcularIMC (View view) {
    simularcalcularIMC();
}
private void kk() {
    // TODO se ejecuta al presionar el boton llamado button
            // @ ESTA PARTE SE USO INICIALMENTE CUANDO LOS DATOS SE RECOGIAN DE DOS EDITTEXT
            // en caso de no tener nada escrito en peso o altura porner algo
            // defecto
                /**
                EditText  pesoescrito1 = findViewById(R.id.editPeso);
                String peso1 = pesoescrito1.getText().toString();
                if (peso1.isEmpty()){
                    pesoescrito1.setText("72");
                 }
                EditText  alturaescrita1 = findViewById(R.id.editAltura);
                String altura1 = alturaescrita1.getText().toString();
                if (altura1.isEmpty()){
                 alturaescrita1.setText("182");
                 }
                 */
                 // El peso se recoge del edit text llamado editPeso
                 /**
                     EditText  pesoescrito = findViewById(R.id.editPeso);
                     String peso = pesoescrito.getText().toString();
                     Float pesonumerico = Float.parseFloat(peso);
                     // La Altura se recoge del edit text llamado editAltura
                     EditText alturaescrita = findViewById(R.id.editAltura);
                     String altura = alturaescrita.getText().toString();
                     Float alturanumerica = Float.parseFloat(altura);
                     */
                     // Se calcula el IMC y su correpondiente tipo
                     // double imc = calcularIMC1(pesonumerico, alturanumerica);

    // @ OPCION FINAL BASADA EN PICKERNUMBER
    // PARTE 1: RE SE RECOGEN LOS DATOS DE PESO Y ALTURA
    // Hemos guardado el peso y altura elegidas en dos text view diferentes
          // PARA LOS VALORES INICIALES SE HAN USADO DOS TEXTVIEW OCULTOS
          //      CUYOS VALOR "TEXT" TIENEN
          //              -> PESO = "72" -> ALTURA = "182"
          // POSTERIORMENTE ESTA INFORMACION ES ACTUALIZADA CON EL VALOR QUE SE
          // VAYA SELECCIONANDO EN EL PICKERNUMBER
    TextView  pesoescrito = findViewById(R.id.txtpesoelegido);
    String peso = pesoescrito.getText().toString();
    Float peso1 = Float.parseFloat(peso);
    TextView  alturaescrita = findViewById(R.id.txtalturaelegida);
    String altura = alturaescrita.getText().toString();
    Float altura1 = Float.parseFloat(altura);

    // PARTE 2: SE CALCULA EL IMC CON LOS DATOS RECOGIDOS
            // Se calcula el IMC y su correpondiente tipo
    double imc = calcularIMC1((float) peso1, (float) altura1);
            // El IMC se presenta en el textview llamado txtIMC
    // PARTE 3: SE PRESENTA EN EL TEXTVIEW "txtIMC" EL imc OBTENIDO
    TextView imcCalculado = findViewById(R.id.txtIMC);
    imcCalculado.setText(""+ (int) imc);
    // PARTE 4: EL VALOR NUMERICO "imc" SE TRADUCE AL ENUM CORRESPONDIENTE
           // El IMC tipo se presenta en el textview llamado txtIMCtipo
    TextView tipoCalculado = findViewById(R.id.txtIMCTipo);
    TipoIMC tipo = TipoIMC.traduceIMC( imc);


    // PARTE 5: TRANDUCCION AL IDIOMA DEL TERMINAL
    //        ESTA PARTE ESTA PREVISTA PARA ESPAÑOL-INGLES Y VICEVERSA SOLAMENTE.
            // ahora hay que traducir el tipo al idioma en curso

    //String titulo = getResources().getString(R.string.titulo);
    String obeso = getResources().getString(R.string.obeso);
    String sobrepeso = getResources().getString(R.string.sobrepeso);
    String normal = getResources().getString(R.string.normal);
    String bajopeso = getResources().getString(R.string.bajopeso);
    String desnutrido = getResources().getString(R.string.desnutrido);



        String tipotraducido = null;
        switch (tipo) {
            case OBESO:
                tipotraducido = obeso;
                break;
            case SOBREPESO:
                tipotraducido = sobrepeso;
                break;
            case NORMAL:
                tipotraducido = normal;
                break;
            case BAJOPESO:
                tipotraducido = bajopeso;
                break;
            case DESNUTRIDO:
                tipotraducido = desnutrido;
                break;
        }

        tipoCalculado.setText(""+ tipotraducido);


    // PARTE 6: ASIGNACION DE IMAGEN POR TIPO
    asignarimagen(tipo);
}

public void asignarimagen(TipoIMC tipo) {
    // asignar imagen en funcion del tipo
    // voy a coger mi imagen
    ImageView imageView =findViewById(R.id.imageView);

    // ver si el idioma asignado en el terminal es español o ingles
    RadioGroup radioGroup =  findViewById(R.id.radioGroup);
    RadioButton hombre = findViewById(R.id.radioButton2);
    RadioButton mujer = findViewById(R.id.radioButton);

    if (tipo == TipoIMC.OBESO ){

        // ahora asignar la imagen
        if (hombre.isChecked() == true){
            imageView.setImageResource(R.drawable.obesocaricatura);
        }else {
            imageView.setImageResource(R.drawable.gorda2);
        }

    } else {
        if (tipo == TipoIMC.SOBREPESO) {

            // ahora asignar la imagen
            if (hombre.isChecked() == true){
                imageView.setImageResource(R.drawable.sobrepesocaricatura);
            }else {
                imageView.setImageResource(R.drawable.gorda);
            }
        }
        else {
            if (tipo == TipoIMC.NORMAL){

                // ahora asignar la imagen
                if (hombre.isChecked() == true){
                    imageView.setImageResource(R.drawable.normalcaricatura);
                }else {
                    imageView.setImageResource(R.drawable.normalmujer);
                }

            }
            else {
                if (tipo == TipoIMC.BAJOPESO){

                    // ahora asignar la imagen
                    if (hombre.isChecked() == true){
                        imageView.setImageResource(R.drawable.delgadocaricatura);
                    }else {
                        imageView.setImageResource(R.drawable.delgada);
                    }


                }else {
                    if (tipo == TipoIMC.DESNUTRIDO ){

                        // ahora asignar la imagen
                        if (hombre.isChecked() == true){
                            imageView.setImageResource(R.drawable.desnutridocaricatura);
                        }else {
                            imageView.setImageResource(R.drawable.desnutrida);
                        }

                    }
                }
            }
        }
    }
}


    public void simularcalcularIMC ( )
    {

        // TODO se ejecuta al presionar el boton llamado button

        Log.i("MIAPP","Estoy en simularcalcularIMC");

        // en caso de no tener nada escrito en peso o altura porner algo
        // defecto
        /**
         EditText  pesoescrito1 = findViewById(R.id.editPeso);
         String peso1 = pesoescrito1.getText().toString();
         if (peso1.isEmpty()){
         pesoescrito1.setText("72");
         }
         EditText  alturaescrita1 = findViewById(R.id.editAltura);
         String altura1 = alturaescrita1.getText().toString();
         if (altura1.isEmpty()){
         alturaescrita1.setText("182");
         }
         */

        // usando NumberPicker
        // como hemos guardado el peso y altura elegidas en dos text view diferentes
        // ahora hay que recoger el valor de esos valore
        TextView  pesoescrito = findViewById(R.id.txtpesoelegido);
        String peso = pesoescrito.getText().toString();
         float peso1 = Float.parseFloat(peso);
        Log.i("MIAPP","peso1 : "+peso1);

        TextView  alturaescrita = findViewById(R.id.txtalturaelegida);
        String altura = alturaescrita.getText().toString();
        Float altura1 = Float.parseFloat(altura);
        Log.i("MIAPP","altura1 : "+altura1);
        /** ahora esto ya no se usa
         NumberPicker numberPicker = findViewById(R.id.editPeso);
         int peso1 = numberPicker.getValue();
         NumberPicker numberPicker1 = findViewById(R.id.editAltura);
         int altura1 = numberPicker1.getValue();
         */

        // El peso se recoge del edit text llamado editPeso
        /**
         EditText  pesoescrito = findViewById(R.id.editPeso);
         String peso = pesoescrito.getText().toString();
         Float pesonumerico = Float.parseFloat(peso);
         // La Altura se recoge del edit text llamado editAltura
         EditText alturaescrita = findViewById(R.id.editAltura);
         String altura = alturaescrita.getText().toString();
         Float alturanumerica = Float.parseFloat(altura);
         */



        // Se calcula el IMC y su correpondiente tipo
        // double imc = calcularIMC1(pesonumerico, alturanumerica);
        double imc = calcularIMC1((float) peso1, (float) altura1);

        // El IMC se presenta en el textview llamado txtIMC
        TextView imcCalculado = findViewById(R.id.txtIMC);
        imcCalculado.setText(""+ (int) imc);

        // El IMC tipo se presenta en el textview llamado txtIMCtipo
        TextView tipoCalculado = findViewById(R.id.txtIMCTipo);

        TipoIMC tipo = TipoIMC.traduceIMC( imc);

        // ahora hay que traducir el tipo al idioma en curso
        // carga estados tipo de las variables string
        // la respuesta es un string con el tipo en el idioma seleccionado
        String tipotraducido = cargarStrings(tipo);

        tipoCalculado.setText(""+ tipotraducido);        // asignar imagen en funcion del tipo
        asignarimagen(tipo);
    }


    public enum TipoIMC {DESNUTRIDO, BAJOPESO, NORMAL, SOBREPESO, OBESO;
        public static TipoIMC traduceIMC (double imc)
        {
            TipoIMC devuelvo = null;
            if (imc<16)
                devuelvo = TipoIMC.DESNUTRIDO;
            else if (imc>=16 && imc<18.5)
                devuelvo = TipoIMC.BAJOPESO;
            else if (imc>=18.5 && imc < 25)
                devuelvo = TipoIMC.NORMAL;
            else if (imc>=25 && imc < 31)
                devuelvo = TipoIMC.SOBREPESO;
            else devuelvo = TipoIMC.OBESO;
            return devuelvo;
        }
    }

    public String cargarStrings(TipoIMC tipo){
        // ahora hay que traducir el tipo al idioma en curso
        // usamos string dinamicas
        String obeso = getResources().getString(R.string.obeso);
        String sobrepeso = getResources().getString(R.string.sobrepeso);
        String normal = getResources().getString(R.string.normal);
        String bajopeso = getResources().getString(R.string.bajopeso);
        String desnutrido = getResources().getString(R.string.desnutrido);



        String tipotraducido = null;
        switch (tipo) {
            case OBESO:
                tipotraducido = obeso;
                break;
            case SOBREPESO:
                tipotraducido = sobrepeso;
                break;
            case NORMAL:
                tipotraducido = normal;
                break;
            case BAJOPESO:
                tipotraducido = bajopeso;
                break;
            case DESNUTRIDO:
                tipotraducido = desnutrido;
                break;
        }

        return tipotraducido;
    }
}

