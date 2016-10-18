package com.haochen.filepicker;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private ListView listView;
    private Button back;
    private Button confirm;

    private FilePicker filePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.title = (TextView) findViewById(R.id.textView);
        this.listView = (ListView) findViewById(R.id.listView);
        this.back = (Button) findViewById(R.id.button_back);
        this.confirm = (Button) findViewById(R.id.button_confirm);


        initFilePicker();
    }

    private void initFilePicker() {
        this.filePicker = new FilePicker(this, listView, new File("/"), false, false);

        this.filePicker.setOnPathChangeListener(new FilePicker.OnPathChangeListener() {
            @Override
            public void onPathChange(File path) {
                title.setText(path.getAbsolutePath());
                //May do something...
            }
        });

//        this.filePicker.setOnFileChangeListener(new FilePicker.OnFileChangeListener() {
//            @Override
//            public void onFileChange(File selected) {
//                //May do something...
//            }
//        });

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filePicker.back();
            }
        });

        this.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File selected = filePicker.getFile();
                if (selected == null) {
                    Snackbar.make(confirm, "No file selected.", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(confirm, selected.getAbsolutePath(), Snackbar.LENGTH_LONG).show();
                    //And do something...
                }
            }
        });

        filePicker.launch();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
