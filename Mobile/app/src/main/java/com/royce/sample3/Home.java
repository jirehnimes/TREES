package com.royce.sample3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by johnroycepunay on 9/18/2016.
 */

public class Home extends TabActivity {

    DatabaseHelper myDb;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        myDb = new DatabaseHelper(this);

        Resources ressources = getResources();
        final TabHost tabHost = getTabHost();


        // HomeTab
        Intent intentHome = new Intent().setClass(this, Cover.class);
        final TabHost.TabSpec tabSpecHome = tabHost
                .newTabSpec("Android")
                .setIndicator("", ressources.getDrawable(R.drawable.icon_android_config))
                .setContent(intentHome);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            public void onTabChanged(String arg0) {
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#000000"));//unselected state

                }
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#678C3F"));//selected state
            }
        });

        // SelectTab
        Intent intentSelect = new Intent().setClass(this, Cover.class);
        final TabHost.TabSpec tabSpecSelect = tabHost
                .newTabSpec("Apple");
        tabSpecSelect.setIndicator("", ressources.getDrawable(R.drawable.icon_apple_config));
        tabSpecSelect.setContent(intentSelect);

        //CameraTab
        Intent intentCamera = new Intent().setClass(this, Cover.class);
        TabHost.TabSpec tabSpecCamera = tabHost
                .newTabSpec("Windows")
                .setIndicator("", ressources.getDrawable(R.drawable.icon_windows_config))
                .setContent(intentCamera);

        // Setting tab
        Intent intentSetting = new Intent().setClass(this, Cover.class);
        TabHost.TabSpec tabSpecSetting = tabHost
                .newTabSpec("Berry")
                .setIndicator("", ressources.getDrawable(R.drawable.icon_blackberry_config))
                .setContent(intentSetting);

        // Logout tab
        Intent intentLogout = new Intent().setClass(this, Cover.class);
        TabHost.TabSpec tabSpecLogout = tabHost
                .newTabSpec("Cisco")
                .setIndicator("", ressources.getDrawable(R.drawable.icon_cisco_config))
                .setContent(intentLogout);

        // add all tabs
        tabHost.addTab(tabSpecHome);
        tabHost.addTab(tabSpecSelect);
        tabHost.addTab(tabSpecCamera);
        tabHost.addTab(tabSpecSetting);
        tabHost.addTab(tabSpecLogout);


        final int viewtrees = 1;
        getTabWidget().getChildAt(viewtrees).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTabHost().setCurrentTab(viewtrees);
                Cursor res = myDb.getTreeData();
                if (res.getCount() == 0) {
                    //show message
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Tree ID :" + res.getString(0) + "\n");
                    buffer.append("Tree Name :" + res.getString(1) + "\n");
                    buffer.append("Age:" + res.getString(2) + " years" +"\n");
                    buffer.append("Diameter :" + res.getString(3) + " cm" +"\n");
                    buffer.append("Circumference:" + res.getString(6) + " cm" +"\n");
                    buffer.append("Status:" + res.getString(4) + "\n");
                    buffer.append("Date:" + res.getString(5) + "\n");
                }
                //show all data
                showMessage("Tree Information", buffer.toString());
            }
        });

        final int viewcamera = 2;
        getTabWidget().getChildAt(viewcamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTabHost().setCurrentTab(viewcamera);
                final Dialog dialog = new Dialog(Home.this);
                dialog.setContentView(R.layout.choosetrees);
                Typeface myFont = Typeface.createFromAsset(getAssets(),"fonts/Georgia.ttf");
                dialog.setTitle("Choose Trees");
                final TextView valueTextViews = (TextView) dialog.findViewById(R.id.selectedtree);
                final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);
                final MyData items[] = new MyData[4];
                items[0] = new MyData("Select Trees!");
                items[1] = new MyData("Rubber Tree");
                items[2] = new MyData("Mahogany Tree");
                items[3] = new MyData("Narra Tree");


                ArrayAdapter<MyData> adapter = new ArrayAdapter<MyData>(Home.this, android.R.layout.simple_spinner_item, items);
                adapter.setDropDownViewResource(
                        android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(
                                    AdapterView<?> parent,
                                    View view,
                                    final int position,
                                    long id) {
                                final MyData d = items[position];
                                valueTextViews.setText(d.toString());
                                valueTextViews.setVisibility(View.INVISIBLE);

                                Button btnSignIn = (Button) dialog.findViewById(R.id.buttonCamera);
                                btnSignIn.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {

                                        if (spinner.getCount() == 0) {
                                            Toast.makeText(getApplicationContext(), "Please select tree!", Toast.LENGTH_SHORT).show();

                                            return;
                                        }
                                        final String Treename = valueTextViews.getText().toString();

                                        if(Treename.equals("Rubber Tree")) {
                                            Intent intent = new Intent(Home.this, Camera.class);
                                            intent.putExtra("Treename", Treename);
                                            startActivity(intent);
                                            finish();

                                        } else if (Treename.equals("Mahogany Tree")) {
                                            Intent intent1 = new Intent(Home.this, Camera.class);
                                            intent1.putExtra("Treename", Treename);
                                            startActivity(intent1);
                                            finish();


                                        } else if (Treename.equals("Narra Tree")) {
                                            Intent intent2 = new Intent(Home.this, Camera.class);
                                            intent2.putExtra("Treename", Treename);
                                            startActivity(intent2);
                                            finish();

                                        }
                                    }
                                });

                                Button btnCancel = (Button) dialog.findViewById(R.id.buttonCancel);
                                btnCancel.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        Intent intent2 = new Intent(Home.this, Home.class);
                                        startActivity(intent2);
                                        getTabHost().setCurrentTab(viewcamera);
                                        finish();
                                    }
                                });
                            }
                            public void onNothingSelected(AdapterView<?> parent) {
                            }

                        }
                );

                dialog.show();

            }


        });

        final int bluetooth = 3;
        getTabWidget().getChildAt(bluetooth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTabHost().setCurrentTab(bluetooth);
                Intent intent = new Intent(Home.this, Settings.class);
                startActivity(intent);
                finish();
            }


        });

        final int logout = 4;
        getTabWidget().getChildAt(logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTabHost().setCurrentTab(logout);
                new AlertDialog.Builder(Home.this)
                        .setTitle("Really Exit?")
                        .setMessage("Are you sure you want to logout?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent ourIntent2 = new Intent(Home.this, Login.class);
                                startActivity(ourIntent2);
                                finish();
                            }
                        }).create().show();


            }


        });

        //set Windows tab as default (zero based)
        tabHost.setCurrentTab(0);


    }
    class MyData {
        public MyData( String spinnerText ) {
            this.spinnerText = spinnerText;
            //  this.value = value;
        }

        public String getSpinnerText()
        {
            return spinnerText;
        }

        //  public String getValue() {
        //    return value;
        //  }

        public String toString() {
            return spinnerText;
        }

        String spinnerText;
        String value;
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent ourIntent2 = new Intent(Home.this, Login.class);
                        startActivity(ourIntent2);
                        finish();
                    }
                }).create().show();
    }

    public void showMessage(String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton(" Delete Tree",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final Dialog dialog2 = new Dialog(Home.this);
                        dialog2.setContentView(R.layout.deletetrees);
                        dialog2.setTitle("Delete Trees");
                        Typeface myFont = Typeface.createFromAsset(getAssets(),"fonts/Georgia.ttf");
                        final EditText txtTreeID = (EditText) dialog2.findViewById(R.id.etTreeID);
                        Button dTree = (Button) dialog2.findViewById(R.id.deleteTree);
                        txtTreeID.setTypeface(myFont);
                        dTree.setTypeface(myFont);
                        dTree.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                String tree_id = txtTreeID.getText().toString();
                                Integer deletedRows = myDb.deleteData(tree_id);

                                if (tree_id.matches("")) {
                                    Toast.makeText(getApplicationContext(), "Please fill the text field!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if (deletedRows > 0) {
                                    Toast.makeText(getApplicationContext(), "Tree deleted!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Home.this, Home.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "No Tree deleted!", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
                        Button deleteAllTree = (Button) dialog2.findViewById(R.id.etAllTreeID);
                        deleteAllTree.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {

                                new AlertDialog.Builder(Home.this)
                                        .setTitle("Delete All Data")
                                        .setMessage("Are you sure you will delete all of the tree data in Database?")
                                        .setNegativeButton(android.R.string.no, null)
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface arg0, int arg1) {
                                                myDb.deleteAllData();
                                                Toast.makeText(getApplicationContext(), "All Tree's data are deleted!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(Home.this, Home.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }).create().show();


                            }
                        });
                        dialog2.show();


                    }
                });
        builder.setNegativeButton(" Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();


                    }
                });

        builder.show();

    }




}