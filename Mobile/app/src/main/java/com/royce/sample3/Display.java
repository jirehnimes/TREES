package com.royce.sample3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by johnroycepunay on 9/18/2016.
 */
public class Display extends Activity implements View.OnClickListener  {
    DatabaseHelper myDb;
    private static String three;
    Button save;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_layout);
        myDb = new DatabaseHelper(this);


        final  String distance =getIntent().getStringExtra("diameter");
        final EditText editDiameter = (EditText) findViewById(R.id.etDiameter);
        editDiameter.setText(distance);
        editDiameter.setFocusable(false);
        final String tree = getIntent().getStringExtra("Treename");
        final TextView txtTree = (TextView) findViewById(R.id.etTreeName);
        txtTree.setText(tree);
        txtTree.setFocusable(false);
        final String ages = getIntent().getStringExtra("age");
        final TextView txtAge = (TextView) findViewById(R.id.etTreeAge);
        txtAge.setText(ages);
        txtAge.setFocusable(false);
         String treeage = txtAge.getText().toString();
         final double dage = (Double.parseDouble(treeage));
         final String status = "Ready for harvest!";


        if( dage <= 30 && dage >= 25) {
            final EditText editStatus = (EditText) findViewById(R.id.etLocation);
            editStatus.setText(status);
            editStatus.setFocusable(false);

        } else {
            double need = 30 - dage;
            String statuses = "Need more" + " " +(String.format("%.0f", need)) +" " + "age";
            final EditText editStatuses = (EditText) findViewById(R.id.etLocation);
            editStatuses.setText(statuses);
            editStatuses.setFocusable(false);
        }

        final String cir = getIntent().getStringExtra("Circumference");
        final TextView txtCirco = (TextView) findViewById(R.id.etCirco);
        txtCirco.setText(cir);
        txtCirco.setFocusable(false);

        String age = txtAge.getText().toString();
        final String treeName = txtTree.getText().toString();
        final double treeAge = Double.parseDouble(age);
        final double diameter = Double.parseDouble(editDiameter.getText().toString());
        final double circumference = Double.parseDouble(txtCirco.getText().toString());
        three = txtTree.getText().toString();

        save =(Button)findViewById(R.id.saveDatabase);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                    myDb.insertTree(treeName,treeAge, diameter,status,circumference);
                    Toast.makeText(Display.this, "Data is Inserted", Toast.LENGTH_SHORT).show();
                    Intent ourIntent2 = new Intent(Display.this, Home.class);
                    startActivity(ourIntent2);
                    finish();



                }




        });


    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you will not save this to the database?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        String treees = String.valueOf(three);
                        Intent ourIntent2 = new Intent(Display.this, Camera.class);
                        ourIntent2.putExtra("Treename",treees);
                        startActivity(ourIntent2);
                    }
                }).create().show();
    }

    public void onClick (View view){


    }


}
